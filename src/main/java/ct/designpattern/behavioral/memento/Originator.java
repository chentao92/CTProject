package ct.designpattern.behavioral.memento;

/**
 * @program: CTProject
 * @description: 发起人
 * @author: chentao
 * @create: 2020-08-20 09:05
 **/

public class Originator {

    private String state;
    public void setState(String state)
    {
        this.state=state;
    }
    public String getState()
    {
        return state;
    }
    public Memento createMemento()
    {
        return new Memento(state);
    }
    public void restoreMemento(Memento m)
    {
        this.setState(m.getState());
    }
}
