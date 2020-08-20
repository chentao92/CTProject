package ct.designpattern.structural.proxy;

/**
 * @program: CTProject
 * @description: 代理模式
 * @author: chentao
 * @create: 2020-08-13 11:10
 **/

public class ProxyTest {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}

//抽象主题
interface Subject {
    void request();
}

//真实主题
class RealSubject implements Subject {
    public void request() {
        System.out.println("访问真实主题方法...");
    }
}

//代理
class Proxy implements Subject {
    private RealSubject realSubject;

    public void request() {
        if (realSubject == null) {
            realSubject = new RealSubject();
        }
        preRequest();
        realSubject.request();
        postRequest();
    }

    public void preRequest() {
        System.out.println("访问真实主题之前的预处理。");
    }

    public void postRequest() {
        System.out.println("访问真实主题之后的后续处理。");
    }
}