package ct.designpattern.behavioral.observer;

/**
 * @program: CTProject
 * @description: 具体目标
 * @author: chentao
 * @create: 2020-08-19 12:00
 **/

public class ConcreteSubject extends Subject {


    public void notifyObserver() {
        System.out.println("具体目标发生改变...");
        System.out.println("--------------");

        for (Object obs : observers) {
            ((Observer) obs).response();
        }

    }
}
