package ct.designpattern.behavioral.interpreter;

/**
 * @program: CTProject
 * @description: 非终结符表达式类
 * @author: chentao
 * @create: 2020-08-20 09:11
 **/

public class AndExpression implements Expression {

    private Expression city = null;
    private Expression person = null;

    public AndExpression(Expression city, Expression person) {
        this.city = city;
        this.person = person;
    }

    public boolean interpret(String info) {
        String s[] = info.split("的");
        return city.interpret(s[0]) && person.interpret(s[1]);
    }
}
