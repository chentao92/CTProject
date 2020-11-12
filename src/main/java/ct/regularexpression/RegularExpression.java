package ct.regularexpression;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @program: CTProject
 * @description: 正则表达式枚举
 * @author: chentao
 * @create: 2020-10-22 09:23
 **/

public enum RegularExpression {



    PLACEHOLDER("\\$\\{(\\S+)\\}"),
    EXCEL_CELL("([A-Z]+)([0-9]+)"),
    PHONE_NUMBER("((?:13[0-9])|(?:14[0,1,4-9])|(?:15[0-3,5-9])|(?:16[2,5,6,7])|(?:17[0-8])|(?:18[0-9])|(?:19[0-3,5-9]))(\\d{8})"),
    ID_NUMBER("(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{2})(\\d{1})([0-9]|X)"),
    EMAIL("([\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*)@((?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?)");

    private final String pattern;

    RegularExpression(String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }

    /**
     * 判断输入字符串是否完全匹配正则，字符串中有部分匹配也会返回false
     * @param input
     * @return
     */
    public boolean match(String input) {
        return Pattern.matches(pattern, input);
    }

    /**
     * 获取第一个输入字符串匹配正则的部分
     * @param input 输入字符串
     * @return
     */
    public String matcherFirst(String input) {
        return matcherFirst(input ,0);
    }

    /**
     * 获取输入字符串匹配正则的部分
     * @param input 输入字符串
     * @return
     */
    public List<String> matcher(String input) {
        return matcher(input ,0);
    }

    /**
     * 获取第一个输入字符串匹配正则某分组的部分
     * @param input 输入字符串
     * @param groupIndex 分组下标
     * @return
     */
    public String matcherFirst(String input, int groupIndex) {
        List<String> list = matcher(input ,groupIndex);
        if(list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取输入字符串匹配正则某分组的部分
     * @param input 输入字符串
     * @param groupIndex 分组下标
     * 从正则表达式左侧开始，每出现一个左括号"("记做一个分组，分组编号从 1 开始。0 代表整个表达式。
     * 在左括号后"("紧跟 ?:，而后再加上正则表达式，构成非捕获组，不计入分组
     * @return
     */
    public List<String> matcher(String input ,int groupIndex) {
        Matcher matcher =Pattern.compile(pattern).matcher(input);
        List<String> matchStrs = new ArrayList<>();
        while (matcher.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
            matchStrs.add(matcher.group(groupIndex));//获取当前匹配的值
        }
        return matchStrs;
    }
}
