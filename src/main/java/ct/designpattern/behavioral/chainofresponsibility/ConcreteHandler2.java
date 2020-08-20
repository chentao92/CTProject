package ct.designpattern.behavioral.chainofresponsibility;

/**
 * @program: CTProject
 * @description: 具体处理者角色2
 * @author: chentao
 * @create: 2020-08-19 10:49
 **/

public class ConcreteHandler2 extends Handler {

    public void handleRequest(String request) {
        if (request.equals("two")) {
            System.out.println("具体处理者2负责处理该请求！");
        } else {
            if (getNext() != null) {
                getNext().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }
}
