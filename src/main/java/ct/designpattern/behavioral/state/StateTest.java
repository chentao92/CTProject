package ct.designpattern.behavioral.state;

/**
 * @program: CTProject
 * @description: 状态模式
 * @author: chentao
 * @create: 2020-08-19 11:15
 **/

public class StateTest {

    public static void main(String[] args) {
        Context context = new Context();    //创建环境
        context.handle();    //处理请求
        context.handle();
        context.handle();
        context.handle();
    }

}
