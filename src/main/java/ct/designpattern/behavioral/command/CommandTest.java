package ct.designpattern.behavioral.command;

/**
 * @program: CTProject
 * @description: 命令模式
 * @author: chentao
 * @create: 2020-08-19 10:31
 **/

public class CommandTest {

    public static void main(String[] args) {
        Command cmd = new ConcreteCommand();
        Invoker ir = new Invoker(cmd);
        System.out.println("客户访问调用者的call()方法...");
        ir.call();
    }

}
