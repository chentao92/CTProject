package ct.designpattern.structural.flyweight;

/**
 * @program: CTProject
 * @description: 非享元角色
 * @author: chentao
 * @create: 2020-08-18 16:32
 **/

public class UnsharedConcreteFlyweight {

    private String info;
    UnsharedConcreteFlyweight(String info)
    {
        this.info=info;
    }
    public String getInfo()
    {
        return info;
    }
    public void setInfo(String info)
    {
        this.info=info;
    }

}
