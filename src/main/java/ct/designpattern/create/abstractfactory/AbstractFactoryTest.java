package ct.designpattern.create.abstractfactory;


/**
 * @program: CTProject
 * @description: 抽象工厂模式
 * 优点：
 * 可以在类的内部对产品族中相关联的多等级产品共同管理，而不必专门引入多个新的类来进行管理。
 * 当增加一个新的产品族时不需要修改原代码，满足开闭原则。
 * 缺点：
 * 当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改。
 * @author: chentao
 * @create: 2020-08-13 08:53
 **/

public class AbstractFactoryTest {
    public static void main(String[] args) {
        try {
            Product1 a;
            AbstractFactory af;
            af = new ConcreteFactory1();
            a = af.newProduct1();
            a.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

//抽象产品：提供了产品的接口
interface Product1 {
    public void show();
}

interface Product2 {
    public void show();
}

//具体产品11：实现抽象产品中的抽象方法
class ConcreteProduct11 implements Product1 {
    public void show() {
        System.out.println("具体产品11显示...");
    }
}

//具体产品21：实现抽象产品中的抽象方法
class ConcreteProduct21 implements Product2 {
    public void show() {
        System.out.println("具体产品21显示...");
    }
}

//具体产品12：实现抽象产品中的抽象方法
class ConcreteProduct12 implements Product1 {
    public void show() {
        System.out.println("具体产品12显示...");
    }
}

//具体产品22：实现抽象产品中的抽象方法
class ConcreteProduct22 implements Product2 {
    public void show() {
        System.out.println("具体产品22显示...");
    }
}

//抽象工厂：提供了厂品的生成方法
interface AbstractFactory {
    public Product1 newProduct1();

    public Product2 newProduct2();
}

//具体工厂1：实现了厂品的生成方法
class ConcreteFactory1 implements AbstractFactory {
    public Product1 newProduct1() {
        System.out.println("具体工厂 1 生成-->具体产品 11...");
        return new ConcreteProduct11();
    }

    public Product2 newProduct2() {
        System.out.println("具体工厂 1 生成-->具体产品 21...");
        return new ConcreteProduct21();
    }
}

//具体工厂2：实现了厂品的生成方法
class ConcreteFactory2 implements AbstractFactory {
    public Product1 newProduct1() {
        System.out.println("具体工厂 2 生成-->具体产品 12...");
        return new ConcreteProduct12();
    }

    public Product2 newProduct2() {
        System.out.println("具体工厂 2 生成-->具体产品 22...");
        return new ConcreteProduct22();
    }
}
