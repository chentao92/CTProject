package ct.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CTProject
 * @description: 抽象目标
 * @author: chentao
 * @create: 2020-08-19 11:59
 **/

public abstract class Subject {

    protected List<Observer> observers = new ArrayList<Observer>();

    //增加观察者方法
    public void add(Observer observer) {
        observers.add(observer);
    }

    //删除观察者方法
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    public abstract void notifyObserver(); //通知观察者方法
}
