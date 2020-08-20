package ct.designpattern.behavioral.interpreter;

/**
 * @program: CTProject
 * @description: 解释器模式
 * @author: chentao
 * @create: 2020-08-20 09:07
 **/

public class InterpreterTest {

    public static void main(String[] args) {
        Context bus = new Context();
        bus.freeRide("韶关的老人");
        bus.freeRide("韶关的年轻人");
        bus.freeRide("广州的妇女");
        bus.freeRide("广州的儿童");
        bus.freeRide("山东的儿童");
    }

}
