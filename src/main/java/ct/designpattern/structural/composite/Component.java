package ct.designpattern.structural.composite;


//抽象构件
public interface Component {

    void add(Component c);

    void remove(Component c);

    Component getChild(int i);

    void operation();
}
