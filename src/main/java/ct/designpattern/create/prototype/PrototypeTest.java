package ct.designpattern.create.prototype;

/**
 * @program: CTProject
 * @description: 原型模式测试
 * @author: chentao
 * @create: 2020-08-13 08:53
 **/

public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Realizetype obj1 = new Realizetype();
        Realizetype obj2 = (Realizetype) obj1.clone();
        System.out.println("obj1==obj2?" + (obj1 == obj2));
    }
}
