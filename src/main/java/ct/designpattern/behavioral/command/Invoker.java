package ct.designpattern.behavioral.command;

/**
 * @program: CTProject
 * @description: 调用者
 * @author: chentao
 * @create: 2020-08-19 10:21
 **/

public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void call() {
        System.out.println("调用者执行命令command...");
        command.execute();
    }
}
