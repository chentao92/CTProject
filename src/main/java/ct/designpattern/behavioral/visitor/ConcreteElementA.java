package ct.designpattern.behavioral.visitor;

/**
 * @program: CTProject
 * @description: 具体元素A类
 * @author: chentao
 * @create: 2020-08-20 09:00
 **/

public class ConcreteElementA implements Element {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String operationA() {
        return "具体元素A的操作。";
    }
}
