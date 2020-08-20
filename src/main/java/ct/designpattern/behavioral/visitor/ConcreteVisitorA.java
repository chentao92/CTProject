package ct.designpattern.behavioral.visitor;

/**
 * @program: CTProject
 * @description: 具体访问者A类
 * @author: chentao
 * @create: 2020-08-20 08:58
 **/

public class ConcreteVisitorA implements Visitor{

    public void visit(ConcreteElementA element)
    {
        System.out.println("具体访问者A访问-->"+element.operationA());
    }
    public void visit(ConcreteElementB element)
    {
        System.out.println("具体访问者A访问-->"+element.operationB());
    }

}
