package ct.designpattern.behavioral.iterator;

/**
 * @program: CTProject
 * @description: 抽象聚合
 * @author: chentao
 * @create: 2020-08-19 17:43
 **/

public interface Aggregate {

    public void add(Object obj);

    public void remove(Object obj);

    public Iterator getIterator();
}
