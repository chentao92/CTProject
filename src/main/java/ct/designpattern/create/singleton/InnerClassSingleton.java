package ct.designpattern.create.singleton;

/**
 * @program: CTProject
 * @description: 静态内部类单例模式
 * 通过静态内部类的方式实现单例模式是线程安全的，同时静态内部类不会在Singleton类加载时就加载，而是在调用getInstance()方法时才进行加载，达到了懒加载的效果。
 * @author: chentao
 * @create: 2020-08-19 09:13
 **/

public class InnerClassSingleton {

    private static class SingletonHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private InnerClassSingleton() {} //private 避免类在外部被实例化

    public static InnerClassSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
