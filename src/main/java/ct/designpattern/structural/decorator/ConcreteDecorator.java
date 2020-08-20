package ct.designpattern.structural.decorator;

/**
 * @program: CTProject
 * @description: 具体装饰角色
 * @author: chentao
 * @create: 2020-08-18 15:04
 **/

public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation() {
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("为具体构件角色增加额外的功能addedFunction()");
    }
}
