package ct.designpattern.behavioral.visitor;

/**
 * @program: CTProject
 * @description: 抽象访问者
 * @author: chentao
 * @create: 2020-08-20 08:57
 **/

public interface Visitor {

    void visit(ConcreteElementA element);
    void visit(ConcreteElementB element);

}
