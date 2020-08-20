package ct.designpattern.structural.bridge;

/**
 * @program: CTProject
 * @description: 桥接模式
 * @author: chentao
 * @create: 2020-08-17 15:01
 **/

public class BridgeTest {
    public static void main(String[] args) {
        Implementor imple = new ConcreteImplementorA();
        Abstraction abs = new RefinedAbstraction(imple);
        abs.Operation();
    }
}



