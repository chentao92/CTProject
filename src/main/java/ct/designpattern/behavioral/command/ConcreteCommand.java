package ct.designpattern.behavioral.command;

/**
 * @program: CTProject
 * @description: 具体命令
 * @author: chentao
 * @create: 2020-08-19 10:23
 **/

public class ConcreteCommand implements Command {

    private Receiver receiver;

    ConcreteCommand() {
        receiver = new Receiver();
    }

    public void execute() {
        receiver.action();
    }
}
