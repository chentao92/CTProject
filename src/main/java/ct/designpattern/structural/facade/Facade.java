package ct.designpattern.structural.facade;

/**
 * @program: CTProject
 * @description: 外观角色
 * @author: chentao
 * @create: 2020-08-18 15:34
 **/

public class Facade {

    private SubSystem01 obj1=new SubSystem01();
    private SubSystem02 obj2=new SubSystem02();
    public void method()
    {
        obj1.method1();
        obj2.method2();
    }
}
