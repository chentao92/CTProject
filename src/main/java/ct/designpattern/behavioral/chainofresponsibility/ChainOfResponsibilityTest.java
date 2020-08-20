package ct.designpattern.behavioral.chainofresponsibility;

/**
 * @program: CTProject
 * @description: 职责链模式
 * @author: chentao
 * @create: 2020-08-19 10:39
 **/

public class ChainOfResponsibilityTest {

    public static void main(String[] args)
    {
        //组装责任链
        Handler handler1=new ConcreteHandler1();
        Handler handler2=new ConcreteHandler2();
        handler1.setNext(handler2);
        //提交请求
        handler1.handleRequest("two");
    }

}
