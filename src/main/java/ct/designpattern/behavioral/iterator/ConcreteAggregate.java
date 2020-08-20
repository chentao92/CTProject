package ct.designpattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CTProject
 * @description: 具体聚合
 * @author: chentao
 * @create: 2020-08-19 17:44
 **/

public class ConcreteAggregate implements Aggregate {


    private List<Object> list = new ArrayList<Object>();

    public void add(Object obj) {
        list.add(obj);
    }

    public void remove(Object obj) {
        list.remove(obj);
    }

    public Iterator getIterator() {
        return (new ConcreteIterator(list));
    }
}
