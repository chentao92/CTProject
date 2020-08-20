package ct.designpattern.behavioral.strategy;

/**
 * @program: CTProject
 * @description: 具体策略类A
 * @author: chentao
 * @create: 2020-08-19 10:07
 **/

public class ConcreteStrategyA implements Strategy {
    public void strategyMethod() {
        System.out.println("具体策略A的策略方法被访问！");
    }
}
