package ct.designpattern.create.prototype;

/**
 * @program: CTProject
 * @description: 原型模式
 * @author: chentao
 * @create: 2020-08-13 08:51
 **/

public class Realizetype implements Cloneable {
    Realizetype() {
        System.out.println("具体原型创建成功！");
    }

    public Object clone() throws CloneNotSupportedException {
        System.out.println("具体原型复制成功！");
        return super.clone(); //浅克隆
    }
}
