package ct.designpattern.behavioral.templatemethod;

/**
 * @program: CTProject
 * @description: 具体子类
 * @author: chentao
 * @create: 2020-08-19 10:03
 **/

public class ConcreteClass extends AbstractClass {

    public void abstractMethod1() {
        System.out.println("抽象方法1的实现被调用...");
    }

    public void abstractMethod2() {
        System.out.println("抽象方法2的实现被调用...");
    }
}
