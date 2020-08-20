package ct.designpattern.behavioral.visitor;

/**
 * @program: CTProject
 * @description: 具体元素B类
 * @author: chentao
 * @create: 2020-08-20 09:00
 **/

public class ConcreteElementB implements Element {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationB() {
        return "具体元素B的操作。";
    }

}
