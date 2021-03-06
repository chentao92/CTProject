package ct.designpattern.behavioral.memento;

/**
 * @program: CTProject
 * @description: 备忘录
 * @author: chentao
 * @create: 2020-08-20 09:05
 **/

public class Memento {

    private String state;
    public Memento(String state)
    {
        this.state=state;
    }
    public void setState(String state)
    {
        this.state=state;
    }
    public String getState()
    {
        return state;
    }

}
