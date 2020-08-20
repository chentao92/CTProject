package ct.designpattern.behavioral.iterator;

import java.util.List;

/**
 * @program: CTProject
 * @description: 具体迭代器
 * @author: chentao
 * @create: 2020-08-19 17:45
 **/

public class ConcreteIterator implements Iterator
{
    private List<Object> list=null;
    private int index=-1;
    public ConcreteIterator(List<Object> list)
    {
        this.list=list;
    }
    public boolean hasNext()
    {
        if(index<list.size()-1)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Object first()
    {
        index=0;
        Object obj=list.get(index);
        return obj;
    }
    public Object next()
    {
        Object obj=null;
        if(this.hasNext())
        {
            obj=list.get(++index);
        }
        return obj;
    }
}
