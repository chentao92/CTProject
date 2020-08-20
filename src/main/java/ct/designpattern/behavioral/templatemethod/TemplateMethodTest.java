package ct.designpattern.behavioral.templatemethod;

/**
 * @program: CTProject
 * @description: 模板方法
 * @author: chentao
 * @create: 2020-08-19 10:01
 **/

public class TemplateMethodTest {

    public static void main(String[] args) {
        AbstractClass tm = new ConcreteClass();
        tm.TemplateMethod();
    }

}
