package ct.designpattern.behavioral.strategy;

/**
 * @program: CTProject
 * @description: 策略模式
 * @author: chentao
 * @create: 2020-08-19 10:06
 **/

public class StrategyTest {


    public static void main(String[] args)
    {
        Context c=new Context();
        Strategy s=new ConcreteStrategyA();
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("-----------------");
        s=new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }

}
