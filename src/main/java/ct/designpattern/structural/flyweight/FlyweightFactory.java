package ct.designpattern.structural.flyweight;

import java.util.HashMap;

/**
 * @program: CTProject
 * @description: 享元工厂角色
 * @author: chentao
 * @create: 2020-08-18 16:53
 **/

public class FlyweightFactory {

    private HashMap<String, Flyweight> flyweights=new HashMap<String, Flyweight>();
    public Flyweight getFlyweight(String key)
    {
        Flyweight flyweight=(Flyweight)flyweights.get(key);
        if(flyweight!=null)
        {
            System.out.println("具体享元"+key+"已经存在，被成功获取！");
        }
        else
        {
            flyweight=new ConcreteFlyweight(key);
            flyweights.put(key, flyweight);
        }
        return flyweight;
    }
}
