package planner;



public enum ConstantInfo {
    SINGLE("Один раз"),
    DAILY("Ежедневно"),
    WEEKLY("Еженедельно"),
    MONTHLY("Раз в месяц"),
    ANNUALLY("Раз в год");

    private final String repeatIndex;

    ConstantInfo(String repeatIndex) {
        this.repeatIndex = repeatIndex;
    }

    public String getRepeatIndex() {
        return repeatIndex;
    }

}
