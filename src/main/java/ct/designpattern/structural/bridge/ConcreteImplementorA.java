package ct.designpattern.structural.bridge;

/**
 * @program: CTProject
 * @description: 具体实现化角色
 * @author: chentao
 * @create: 2020-08-17 17:45
 **/

public class ConcreteImplementorA implements Implementor {

    public void OperationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问");
    }
}
