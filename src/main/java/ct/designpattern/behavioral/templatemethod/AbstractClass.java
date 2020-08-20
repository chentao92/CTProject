package ct.designpattern.behavioral.templatemethod;

/**
 * @program: CTProject
 * @description: 抽象类
 * @author: chentao
 * @create: 2020-08-19 10:02
 **/

public abstract class AbstractClass {

    //模板方法
    public void TemplateMethod() {
        SpecificMethod();
        abstractMethod1();
        abstractMethod2();
    }

    //具体方法
    public void SpecificMethod() {
        System.out.println("抽象类中的具体方法被调用...");
    }

    public abstract void abstractMethod1(); //抽象方法1

    public abstract void abstractMethod2(); //抽象方法2
}
