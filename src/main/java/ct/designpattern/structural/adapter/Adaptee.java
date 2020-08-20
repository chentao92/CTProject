package ct.designpattern.structural.adapter;

/**
 * @program: CTProject
 * @description: 适配者接口
 * @author: chentao
 * @create: 2020-08-13 11:42
 **/

public class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}
