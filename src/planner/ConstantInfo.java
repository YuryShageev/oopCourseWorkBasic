package planner;



public enum ConstantInfo {
    SINGLE(0),
    DAILY(+1),
    WEEKLY(+7),
    MONTHLY(+30),
    ANNUALLY(+365);

    private final int repeatIndex;

    ConstantInfo(int repeatIndex) {
        this.repeatIndex = repeatIndex;
    }

    public int getRepeatIndex() {
        return repeatIndex;
    }

}
