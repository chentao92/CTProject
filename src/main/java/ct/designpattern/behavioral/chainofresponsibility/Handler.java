package ct.designpattern.behavioral.chainofresponsibility;

/**
 * @program: CTProject
 * @description: 抽象处理者角色
 * @author: chentao
 * @create: 2020-08-19 10:47
 **/

public abstract class Handler {

    private Handler next;
    public void setNext(Handler next)
    {
        this.next=next;
    }
    public Handler getNext()
    {
        return next;
    }
    //处理请求的方法
    public abstract void handleRequest(String request);

}
