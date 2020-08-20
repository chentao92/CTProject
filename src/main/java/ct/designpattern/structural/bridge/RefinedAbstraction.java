package ct.designpattern.structural.bridge;

/**
 * @program: CTProject
 * @description: 扩展抽象化角色
 * @author: chentao
 * @create: 2020-08-17 17:47
 **/

public class RefinedAbstraction extends Abstraction {

    protected RefinedAbstraction(Implementor imple) {
        super(imple);
    }

    public void Operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        imple.OperationImpl();
    }
}
