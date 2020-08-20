package ct.designpattern.create.singleton;


/**
 * @program: CTProject
 * @description: 枚举式单例
 * 最佳的单例实现模式就是枚举模式。防止反序列化及反射
 * @author: chentao
 * @create: 2020-08-19 08:48
 */
public enum EnumSingleton {

    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }

}
