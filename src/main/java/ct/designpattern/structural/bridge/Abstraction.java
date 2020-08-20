package ct.designpattern.structural.bridge;

/**
 * @program: CTProject
 * @description: 抽象化角色
 * @author: chentao
 * @create: 2020-08-17 17:45
 **/

public abstract class Abstraction {

    protected Implementor imple;

    protected Abstraction(Implementor imple) {
        this.imple = imple;
    }

    public abstract void Operation();
}
