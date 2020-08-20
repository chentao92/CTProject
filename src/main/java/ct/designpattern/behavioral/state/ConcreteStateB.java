package ct.designpattern.behavioral.state;

/**
 * @program: CTProject
 * @description: 具体状态B类
 * @author: chentao
 * @create: 2020-08-19 11:42
 **/

public class ConcreteStateB extends State {

    public void handle(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new ConcreteStateA());
    }
}
