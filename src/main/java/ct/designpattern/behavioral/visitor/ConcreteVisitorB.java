package ct.designpattern.behavioral.visitor;

/**
 * @program: CTProject
 * @description: 具体访问者B类
 * @author: chentao
 * @create: 2020-08-20 08:59
 **/

public class ConcreteVisitorB implements Visitor{

    public void visit(ConcreteElementA element)
    {
        System.out.println("具体访问者B访问-->"+element.operationA());
    }
    public void visit(ConcreteElementB element)
    {
        System.out.println("具体访问者B访问-->"+element.operationB());
    }
}
