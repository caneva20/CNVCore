package me.caneva20.Core.CNVActions.Paramenters;

public abstract class ActionParameter {
    protected boolean isRequired;

    public ActionParameter() {}

    public ActionParameter(boolean isRequired) {
        this.isRequired = isRequired;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public Object getValue(String in) {
        return null;
    }

    public Object getDefaultValue() {
        return null;
    }
}
