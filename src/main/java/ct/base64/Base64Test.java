package ct.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @program: CTProject
 * @description: base64测试类
 * @author: chentao
 * @create: 2020-10-21 11:14
 **/

public class Base64Test {

    public static void main(String[] args) throws UnsupportedEncodingException {

        //jdk1.8
        String string = "1111";
        byte[] bytes = string.getBytes("UTF-8");

        String base64Str = Base64.getEncoder().encodeToString(bytes);
        System.out.println(base64Str);

        byte[] bytes1 =  Base64.getDecoder().decode(base64Str);
        String string1 = new String(bytes1, "UTF-8");

        System.out.println(string1);

    }

}
