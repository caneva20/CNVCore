package me.caneva20.Core.CNVActions.Paramenters;

public class ActionLong extends ActionParameter {
    private long defaultValue;

    public ActionLong() {
    }

    public ActionLong(boolean isRequired) {
        super(isRequired);
    }

    public ActionLong(boolean isRequired, long defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionLong(long defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Long getValue(String in) {
        try {
            return Long.parseLong(in);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
