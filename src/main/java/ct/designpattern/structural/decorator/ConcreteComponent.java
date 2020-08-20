package ct.designpattern.structural.decorator;

/**
 * @program: CTProject
 * @description: 具体构件角色
 * @author: chentao
 * @create: 2020-08-18 15:03
 **/

public class ConcreteComponent implements Component{

    public ConcreteComponent() {
        System.out.println("创建具体构件角色");
    }

    public void operation() {
        System.out.println("调用具体构件角色的方法operation()");
    }
}
