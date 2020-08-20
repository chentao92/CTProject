package ct.designpattern.behavioral.strategy;

/**
 * @program: CTProject
 * @description: 环境类
 * @author: chentao
 * @create: 2020-08-19 10:09
 **/

public class Context {

    private Strategy strategy;
    public Strategy getStrategy()
    {
        return strategy;
    }
    public void setStrategy(Strategy strategy)
    {
        this.strategy=strategy;
    }
    public void strategyMethod()
    {
        strategy.strategyMethod();
    }

}
