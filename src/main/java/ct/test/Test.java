package ct.test;

import java.util.regex.Pattern;

/**
 * @program: CTProject
 * @description: 测试
 * @author: chentao
 * @create: 2020-08-25 14:27
 **/

public class Test {

    public static void main(String[] args) {
//        System.out.println(7*24*60*60*1000L);
        String pattern = "^[0-9]+(.[0-9]{0,1})?$";

//        boolean isMatch = Pattern.matches(pattern, "1134.1");
//        System.out.println(isMatch);


        if(Pattern.matches(pattern, "111")){
            System.out.println(String.format("%.2f", Double.valueOf("111")));
        }
    }

}
