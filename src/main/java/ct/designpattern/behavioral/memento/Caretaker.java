package ct.designpattern.behavioral.memento;

/**
 * @program: CTProject
 * @description: 管理者
 * @author: chentao
 * @create: 2020-08-20 09:06
 **/

public class Caretaker {

    private Memento memento;
    public void setMemento(Memento m)
    {
        memento=m;
    }
    public Memento getMemento()
    {
        return memento;
    }
}
