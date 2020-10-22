package ct.regularexpression;

/**
 * @program: CTProject
 * @description: 正则表达式测试类
 * @author: chentao
 * @create: 2020-10-22 09:23
 **/

public class RegularExpressionTest {

    public static void main(String[] args) {
        String input = "1315450620@qq.com";
        System.out.println(RegularExpression.EMAIL.match(input));
        System.out.println(RegularExpression.EMAIL.matcherFirst(input));
        System.out.println(RegularExpression.EMAIL.matcherFirst(input,2));
        input = "18696115196";
        System.out.println(RegularExpression.PHONE_NUMBER.match(input));
        System.out.println(RegularExpression.PHONE_NUMBER.matcherFirst(input));
        System.out.println(RegularExpression.PHONE_NUMBER.matcherFirst(input,1));
        input = "421126199112283818";
        System.out.println(RegularExpression.ID_NUMBER.match(input));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input,1));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input,2));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input,3));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input,4));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input,5));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input,6));
        System.out.println(RegularExpression.ID_NUMBER.matcherFirst(input,7));
    }
}
