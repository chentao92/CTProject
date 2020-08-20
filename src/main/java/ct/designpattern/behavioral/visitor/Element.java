package ct.designpattern.behavioral.visitor;

/**
 * @program: CTProject
 * @description: 抽象元素类
 * @author: chentao
 * @create: 2020-08-20 08:59
 **/

public interface Element {

    void accept(Visitor visitor);
}
