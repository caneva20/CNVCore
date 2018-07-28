package me.caneva20.Core.CNVActions.Paramenters;

public class ActionObject extends ActionParameter {
    private Object defaultValue;

    public ActionObject() {
    }

    public ActionObject(boolean isRequired) {
        super(isRequired);
    }

    public ActionObject(boolean isRequired, Object defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionObject(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
