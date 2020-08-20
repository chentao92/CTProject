package ct.designpattern.structural.decorator;

/**
 * @program: CTProject
 * @description: 装饰模式
 * @author: chentao
 * @create: 2020-08-18 14:58
 **/

public class DecoratorTest {

    public static void main(String[] args) {
        Component p = new ConcreteComponent();
        p.operation();
        System.out.println("---------------------------------");
        Component d = new ConcreteDecorator(p);
        d.operation();
    }

}
