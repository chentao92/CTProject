package ct.designpattern.behavioral.visitor;

/**
 * @program: CTProject
 * @description: 访问者模式
 * @author: chentao
 * @create: 2020-08-20 08:57
 **/

public class VisitorTest {

    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor = new ConcreteVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor = new ConcreteVisitorB();
        os.accept(visitor);
    }

}
