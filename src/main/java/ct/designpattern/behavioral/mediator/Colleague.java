package ct.designpattern.behavioral.mediator;

/**
 * @program: CTProject
 * @description: 抽象同事类
 * @author: chentao
 * @create: 2020-08-19 14:18
 **/

public abstract class Colleague {

    protected Mediator mediator;
    public void setMedium(Mediator mediator)
    {
        this.mediator=mediator;
    }
    public abstract void receive();
    public abstract void send();
}
