package ct.designpattern.behavioral.iterator;

/**
 * @program: CTProject
 * @description: 抽象迭代器
 * @author: chentao
 * @create: 2020-08-19 17:44
 **/

public interface Iterator {

    Object first();

    Object next();

    boolean hasNext();
}
