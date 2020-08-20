package ct.designpattern.structural.adapter;

/**
 * @program: CTProject
 * @description: 类适配器模式
 * @author: chentao
 * @create: 2020-08-13 11:35
 **/

public class ClassAdapterTest {
    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }
}

//类适配器类
class ClassAdapter extends Adaptee implements Target {
    public void request() {
        specificRequest();
    }
}
