package ct.designpattern.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CTProject
 * @description: 具体中介者
 * @author: chentao
 * @create: 2020-08-19 14:18
 **/

public class ConcreteMediator extends Mediator{

    private List<Colleague> colleagues=new ArrayList<Colleague>();
    public void register(Colleague colleague)
    {
        if(!colleagues.contains(colleague))
        {
            colleagues.add(colleague);
            colleague.setMedium(this);
        }
    }
    public void relay(Colleague cl)
    {
        for(Colleague ob:colleagues)
        {
            if(!ob.equals(cl))
            {
                ((Colleague)ob).receive();
            }
        }
    }
}
