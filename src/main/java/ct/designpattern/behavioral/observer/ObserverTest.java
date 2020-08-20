package ct.designpattern.behavioral.observer;

/**
 * @program: CTProject
 * @description: 观察者模式
 * @author: chentao
 * @create: 2020-08-19 11:49
 **/

public class ObserverTest {

    public static void main(String[] args) {
        Subject subject = new ConcreteSubject();
        Observer obs1 = new ConcreteObserver1();
        Observer obs2 = new ConcreteObserver2();
        subject.add(obs1);
        subject.add(obs2);
        subject.notifyObserver();
    }

}
