package ct.enumtest;


public class TestSeason {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring);
        spring.show();
        System.out.println(spring.getSeasonName());

        // 1. values() 方法：返回所有值
        Season[] seasons = Season.values();
        for (int i=0; i<seasons.length;i++) {
            System.out.println(seasons[i]);
        }

        // 2. valueOf() 方法：返回指定对象的值（要求传入的形参name是枚举类对象的名字）
        // 否则，报 java.lang.IllegalArgumentException 异常
        String str = "SPRING";
        Season sea = Season.valueOf(str);
        System.out.println(sea);
    }
}