package ct.designpattern.behavioral.interpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: CTProject
 * @description: 终结符表达式类
 * @author: chentao
 * @create: 2020-08-20 09:10
 **/

public class TerminalExpression implements Expression {

    private Set<String> set = new HashSet<String>();

    public TerminalExpression(String[] data) {
        for (int i = 0; i < data.length; i++) set.add(data[i]);
    }

    public boolean interpret(String info) {
        if (set.contains(info)) {
            return true;
        }
        return false;
    }
}
