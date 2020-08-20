package ct.designpattern.behavioral.mediator;

/**
 * @program: CTProject
 * @description: 中介者模式
 * @author: chentao
 * @create: 2020-08-19 14:17
 **/

public class MediatorTest {

    public static void main(String[] args)
    {
        Mediator md=new ConcreteMediator();
        Colleague c1,c2;
        c1=new ConcreteColleague1();
        c2=new ConcreteColleague2();
        md.register(c1);
        md.register(c2);
        c1.send();
        System.out.println("-------------");
        c2.send();
    }


}
