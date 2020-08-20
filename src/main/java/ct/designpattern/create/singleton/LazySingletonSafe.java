package ct.designpattern.create.singleton;

/**
 * @program: CTProject
 * @description: 双重校验懒汉模式单例
 * @author: chentao
 * @create: 2020-08-19 09:08
 **/

public class LazySingletonSafe {

    private static volatile LazySingletonSafe singleton;

    private LazySingletonSafe() {
    }

    public static LazySingletonSafe getInstance() {
        if (singleton == null) {
            synchronized (LazySingletonSafe.class) {
                if (singleton == null) {
                    singleton = new LazySingletonSafe();
                }
            }
        }
        return singleton;
    }
}
