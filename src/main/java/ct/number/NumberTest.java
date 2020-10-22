package ct.number;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

/**
 * @program: CTProject
 * @description: 数字测试类
 * @author: chentao
 * @create: 2020-10-21 11:39
 **/

public class NumberTest {

    public static void main(String[] args) {

        NumberTest t = new NumberTest();
        //保留小数
        t.keepDecimal();
        t.calculate();
        t.stringToNumber();
    }

    /**
     * 保留小数
     */
    public void keepDecimal(){
        Double d = 1111.123456;
        System.out.println(String.format("%.2f", d));
        System.out.println(new DecimalFormat("#.00").format(d));

        //当数字为null时，方法1会返回错误的结果，而方法2会抛异常
        Double d1 = null;
        System.out.println(String.format("%.2f", d1));
        System.out.println(new DecimalFormat("#.00").format(d1));
    }

    /**
     * 计算
     */
    public void calculate(){
        Double a = 1.1;
        System.out.println(a);
        System.out.println(a.intValue()); //直接舍去小数位
        System.out.println(Math.round(a)); //四舍五入

        Double b = 0.11;
        Double c = a+b; // 浮点数之间的计算会有精度问题，绝大部分数计算起来没有问题，产生没有问题的假象，必须注意。
        System.out.println(c);

        BigDecimal bigDecimal1 = new BigDecimal("5.7");//推荐用字符串创建实例
        BigDecimal bigDecimal2 = new BigDecimal(Double.toString(2.3));//double使用这种方式创建实例
        System.out.println(bigDecimal1.add(bigDecimal2));
        System.out.println(bigDecimal1.subtract(bigDecimal2));
        System.out.println(bigDecimal1.multiply(bigDecimal2));
        System.out.println(bigDecimal1.divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP));
    }

    /**
     * 字符串转数字
     */
    public void stringToNumber(){
        String str = "11.63456";
        System.out.println(Double.parseDouble(str));
        BigDecimal bigDecimal = new BigDecimal(str);
        System.out.println(bigDecimal.intValue());  //向下取整
        System.out.println(Math.round(bigDecimal.doubleValue())); //四舍五入
    }
}
