package ct.designpattern.structural.decorator;

/**
 * @program: CTProject
 * @description: 抽象装饰角色
 * @author: chentao
 * @create: 2020-08-18 15:04
 **/

public class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}
