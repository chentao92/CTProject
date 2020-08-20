package ct.designpattern.structural.composite;

import java.util.ArrayList;

/**
 * @program: CTProject
 * @description: 树枝构件
 * @author: chentao
 * @create: 2020-08-18 18:22
 **/

public class Composite implements Component {

    private ArrayList<Component> children = new ArrayList<Component>();

    public void add(Component c) {
        children.add(c);
    }

    public void remove(Component c) {
        children.remove(c);
    }

    public Component getChild(int i) {
        return children.get(i);
    }

    public void operation() {
        for (Object obj : children) {
            ((Component) obj).operation();
        }
    }

}
