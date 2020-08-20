package ct.designpattern.create.singleton;

/**
 * @program: CTProject
 * @description: 饿汉式单例
 * 饿汉式单例在类创建的同时就已经创建好一个静态的对象供系统使用，以后不再改变，所以是线程安全的，可以直接用于多线程而不会出现问题。
 * @author: chentao
 * @create: 2020-08-13 08:45
 **/

public class HungrySingleton {
    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {} //private 避免类在外部被实例化

    public static HungrySingleton getInstance() {
        return instance;
    }
}
