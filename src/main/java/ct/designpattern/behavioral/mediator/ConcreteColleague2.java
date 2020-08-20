package ct.designpattern.behavioral.mediator;

/**
 * @program: CTProject
 * @description: 具体同事类
 * @author: chentao
 * @create: 2020-08-19 14:19
 **/

public class ConcreteColleague2 extends Colleague{

    public void receive()
    {
        System.out.println("具体同事类2收到请求。");
    }
    public void send()
    {
        System.out.println("具体同事类2发出请求。");
        mediator.relay(this); //请中介者转发
    }
}
