package ct.designpattern.behavioral.mediator;

/**
 * @program: CTProject
 * @description: 抽象中介者
 * @author: chentao
 * @create: 2020-08-19 14:17
 **/

public abstract class Mediator {

    public abstract void register(Colleague colleague);
    public abstract void relay(Colleague cl); //转发
}
