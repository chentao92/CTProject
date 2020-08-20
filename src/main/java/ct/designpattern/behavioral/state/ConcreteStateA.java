package ct.designpattern.behavioral.state;

/**
 * @program: CTProject
 * @description: 具体状态A类
 * @author: chentao
 * @create: 2020-08-19 11:41
 **/

public class ConcreteStateA extends State {
    public void handle(Context context) {
        System.out.println("当前状态是 A.");
        context.setState(new ConcreteStateB());
    }
}
