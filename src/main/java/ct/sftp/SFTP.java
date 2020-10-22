package ct.sftp;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.*;

/**
 * @program: CTProject
 * @description: sftp文件上传下载
 * @author: chentao
 * @create: 2020-08-21 15:02
 **/

public class SFTP {

    private ChannelSftp sftp;

    private Session session;
    /**
     * SFTP 登录用户名
     */
    private String username;
    /**
     * SFTP 登录密码
     */
    private String password;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * SFTP 服务器地址IP地址
     */
    private String host;
    /**
     * SFTP 端口
     */
    private int port;

    /**
     * 构造基于密码认证的sftp对象
     */
    public SFTP(String username, String password, String host, int port) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
    }

    /**
     * 构造基于秘钥认证的sftp对象
     */
    public SFTP(String username, String host, int port, String privateKey) {
        this.username = username;
        this.host = host;
        this.port = port;
        this.privateKey = privateKey;
    }

    /**
     * 连接sftp服务器
     */
    public void login() {
        try {
            JSch jsch = new JSch();
            if (privateKey != null) {
                jsch.addIdentity(privateKey);// 设置私钥
            }

            session = jsch.getSession(username, host, port);

            if (password != null) {
                session.setPassword(password);
            }
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");

            session.setConfig(config);
            session.connect();

            Channel channel = session.openChannel("sftp");
            channel.connect();

            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接 server
     */
    public void logout() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    /**
     * base64转InputStream
     *
     * @param base64
     * @return
     */
    public InputStream base64ToInputStream(String base64) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        return new ByteArrayInputStream(bytes);
    }


    /**
     * 将base64文件流上传
     *
     * @param directory
     * @param sftpFileName
     * @param base64
     * @return
     * @throws SftpException
     */
    public boolean upload(String directory, String sftpFileName, String base64) {
        try {
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            InputStream is = base64ToInputStream(base64);
            sftp.put(is, sftpFileName); // 上传文件
            return true;
        } catch (SftpException e) {
            return false;
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
     *
     * @param directory    上传到该目录
     * @param sftpFileName sftp端文件名
     */
    public boolean upload(String directory, String sftpFileName, InputStream input) throws SftpException {
        try {
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            sftp.put(input, sftpFileName); // 上传文件
            return true;
        } catch (SftpException e) {
            return false;
        }
    }

    /**
     * 进入目录
     *
     * @param directory
     * @throws SftpException
     */
    public void cd(String directory) throws SftpException {
        if (directory != null && !"".equals(directory) && !"/".equals(directory)) {
            sftp.cd(directory);
        }

    }

    /**
     * 下载文件。
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     */
    public void download(String directory, String downloadFile, String saveFile) {

        File file = null;
        try {
            if (directory != null && !"".equals(directory)) {
                sftp.cd(directory);
            }
            file = new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (SftpException e) {
            e.printStackTrace();
            if (file != null) {
                file.delete();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            if (file != null) {
                file.delete();
            }
        }

    }

    /**
     * 下载文件字节数组
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     * @throws SftpException
     * @throws IOException
     */
    public byte[] download(String directory, String downloadFile) throws SftpException, IOException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        InputStream is = sftp.get(downloadFile);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        int buf_size = 1024;
        byte[] buffer = new byte[buf_size];
        int len = 0;
        while (-1 != (len = is.read(buffer, 0, buf_size))) {
            bos.write(buffer, 0, len);
        }
        return bos.toByteArray();
    }

    /**
     * 下载文件base64
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件名
     * @return 字节数组
     */
    public String downloadBase64(String directory, String downloadFile) throws SftpException, IOException {
        return byteToBase64(download(directory, downloadFile));
    }


    /**
     * 下载文件夹下所有文件base64
     *
     * @param directory
     * @throws SftpException
     * @throws IOException
     */
    public List<String> recursiveFolderDownload(String directory) throws SftpException, IOException {
        Vector<ChannelSftp.LsEntry> fileAndFolderList = sftp.ls(directory); // Let list of folder content

        List<String> list = new ArrayList<String>();
        //Iterate through list of folder content
        for (ChannelSftp.LsEntry item : fileAndFolderList) {
            if (!(".".equals(item.getFilename()) || "..".equals(item.getFilename()))) {//不为  ‘.’或‘..’
                if (item.getAttrs().isReg()) {//文件
                    list.add(downloadBase64(directory, item.getFilename()));
                } else if (item.getAttrs().isDir()) {//路径
                    list.addAll(recursiveFolderDownload(directory + "/" + item.getFilename()));
                }
            }
        }
        return list;
    }

    /**
     * Base64加密
     *
     * @param b
     * @return
     */
    public String byteToBase64(byte[] b) {
        String str = null;
        if (null != b) {
            Base64.Encoder encoder = Base64.getEncoder();
            str = encoder.encodeToString(b);
        }
        return str;
    }

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     */
    public void delete(String directory, String deleteFile) throws SftpException {
        if (directory != null && !"".equals(directory)) {
            sftp.cd(directory);
        }
        sftp.rm(deleteFile);
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    public Vector<?> listFiles(String directory) throws SftpException {

        return sftp.ls(directory);
    }

    /**
     * 判断路径是否存在
     *
     * @param directory
     * @return
     */
    public boolean isDirExist(String directory) {
        boolean isDirExistFlag = false;
        try {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        } catch (Exception e) {
            if (e.getMessage().toLowerCase().equals("no such file")) {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * 创建路径
     *
     * @param createpath
     * @throws Exception
     */
    public void createDir(String createpath) throws Exception {
        try {
            if (isDirExist(createpath)) {
                sftp.cd(createpath);
                return;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry) {
                if (path.equals("")) {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString())) {
                    sftp.cd(filePath.toString());
                } else {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            sftp.cd(createpath);
        } catch (SftpException e) {
            throw new Exception("创建路径错误：" + createpath);
        }
    }

    /**
     * 判断文件是否存在
     *
     * @param directory
     * @param fileName
     * @return
     */
    public boolean isExistsFile(String directory, String fileName) {

        List<String> findFilelist = new ArrayList();
        ChannelSftp.LsEntrySelector selector = new ChannelSftp.LsEntrySelector() {
            @Override
            public int select(ChannelSftp.LsEntry lsEntry) {
                if (lsEntry.getFilename().equals(fileName)) {
                    findFilelist.add(fileName);
                }
                return 0;
            }
        };

        try {
            sftp.ls(directory, selector);
        } catch (SftpException e) {
            e.printStackTrace();
        }

        if (findFilelist.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
