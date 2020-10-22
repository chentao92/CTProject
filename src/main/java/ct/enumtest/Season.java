package ct.enumtest;

public  enum Season {

    SPRING("spring", "春暖花开"),
    SUMMER("summer", "夏日炎炎"),
    AUTUMN("autumn", "秋高气爽"),
    WINTER("winter", "白雪皑皑");


    private final String seasonName;
    private final String seasonDesc;

    private Season (String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }
    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season [seasonName=" + seasonName + ", seasonDesc=" + seasonDesc + "]";
    }

    public void show() {
        System.out.println("这是一个"+this.getSeasonDesc()+"的季节");
    }

}
