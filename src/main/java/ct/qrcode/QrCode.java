package ct.qrcode;


import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * @program: CTProject
 * @description: 二维码
 * @author: chentao
 * @create: 2020-08-24 16:46
 **/

public class QrCode {

    @SneakyThrows
    public static void main(String[] args) {
        String str = QrCode.creatQrCode("1111",300,300,"D:\\32x32.png");
        System.out.println(str);
    }

    /**
     *  生成二维码
     * @param contents  二维码内容
     * @param width   二维码宽度
     * @param height  二维码高度
     * @return
     */
    public static String creatQrCode(String contents, int width, int height) throws WriterException, IOException {
        String binary = null;
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(
                contents, BarcodeFormat.QR_CODE, width, height, hints);
        // 1、读取文件转换为字节数组
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedImage image = toBufferedImage(bitMatrix);
        //转换成png格式的IO流
        ImageIO.write(image, "png", out);
        byte[] bytes = out.toByteArray();

        // 2、将字节数组转为二进制
        binary = Base64.getEncoder().encodeToString(bytes);

        return binary;
    }

    /**
     * image流数据处理
     * @param matrix
     * @return
     */
    public static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    /**
     * 生成二维码(QRCode)图片带logo
     * @param contents 二维码图片的内容
     * @param width 生成二维码图片宽度
     * @param height 生成二维码图片高度
     * @param ccbPath  二维码图片中间的logo路径
     *
     */
    public static String creatQrCode(String contents, int width, int height, String ccbPath) throws IOException, WriterException {
      return creatQrCode( contents,  width,  height,  ccbPath,  null,  null,  null);
    }

    /**
     * 生成二维码(QRCode)图片带logo
     * @param contents 二维码图片的内容
     * @param width 生成二维码图片宽度
     * @param height 生成二维码图片高度
     * @param ccbPath  二维码图片中间的logo路径
     * @param logopart  二维码图片大小为logo大小倍数
     *
     */
    public static String creatQrCode(String contents, int width, int height, String ccbPath, Integer logopart) throws IOException, WriterException {
        return creatQrCode( contents,  width,  height,  ccbPath,  logopart,  null,  null);
    }

    /**
     * 生成二维码(QRCode)图片带logo
     * @param contents 二维码图片的内容
     * @param width 生成二维码图片宽度
     * @param height 生成二维码图片高度
     * @param ccbPath  二维码图片中间的logo路径
     * @param logopart  二维码图片大小为logo大小倍数
     * @param border  logo边框宽度
     * @param color  logo边框颜色
     *
     */
    public static String creatQrCode(String contents, int width, int height, String ccbPath, Integer logopart, Integer border, Color color) throws IOException, WriterException {
        String binary = null;
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(
                contents, BarcodeFormat.QR_CODE, width, height, hints);

        BufferedImage image = toBufferedImage(bitMatrix);

        Graphics2D graph = image.createGraphics();

        // 2、读取logo图片
        BufferedImage logo = ImageIO.read(new File(ccbPath));

        if(logopart == null || logopart <= 2 ){
            logopart = 6;
        }
        if(border == null || border <= 0 ){
            border = 1;
        }
        if(color == null){
            color = Color.WHITE;
        }

        int widthLogo = image.getWidth() / logopart;
        int heightLogo = image.getHeight() / logopart;

        // 3、计算图片放置的位置
        int x = (image.getWidth() - widthLogo) / 2;
        int y = (image.getHeight() - heightLogo) / 2;

        // 4、绘制图片
        graph.drawImage(logo, x, y, widthLogo, heightLogo, null);
        graph.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
        graph.setStroke(new BasicStroke(border));
        graph.setColor(color);
        graph.drawRect(x, y, widthLogo, heightLogo);

        graph.dispose();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);


        byte[] bytes = out.toByteArray();

        // 2、将字节数组转为二进制
        binary = Base64.getEncoder().encodeToString(bytes);
        return binary;
    }

    /**
     * 解析二维码
     *
     * @param codePath 二维码存放全路径
     *
     */
    public static void readQrCodeByPath(String codePath) throws IOException, NotFoundException {

        File QrCode = new File(codePath);
        BufferedImage image = ImageIO.read(QrCode);
        readQrCode( image);
    }

    /**
     * 解析二维码
     *
     * @param base64 二维码base64
     *
     */
    public static void readQrCodeByBase64(String base64) throws IOException, NotFoundException {

        BufferedImage image = ImageIO.read(baseToInputStream(base64));
        readQrCode(image);
    }

    /**
     * 解析二维码
     *
     * @param inputStream 二维码输入流
     *
     */
    public static void readQrCode(InputStream inputStream) throws IOException, NotFoundException {

        BufferedImage image = ImageIO.read(inputStream);
        readQrCode(image);
    }

    /**
     * 解析二维码
     *
     * @param image 二维码图片对象
     *
     */
    public static void readQrCode(BufferedImage image) throws NotFoundException {

            MultiFormatReader formatReader = new MultiFormatReader();

            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));

            // 设置二维码的参数
            HashMap hints = new HashMap();

            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

            Result result = formatReader.decode(binaryBitmap, hints);

            // 打印解析结果
            System.out.println(result.toString());

            // 打印二维码格式
            System.out.println(result.getBarcodeFormat());

            // 二维码文本内容
            System.out.println(result.getText());

    }

    private static InputStream baseToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            byte[] bytes =  Base64.getDecoder().decode(base64string);
            stream = new ByteArrayInputStream(bytes);
        } catch (Exception e) {
        }
        return stream;
    }

}
