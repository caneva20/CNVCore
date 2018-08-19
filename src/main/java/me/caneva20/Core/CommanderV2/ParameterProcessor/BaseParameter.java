package me.caneva20.Core.CommanderV2.ParameterProcessor;

public abstract class BaseParameter<T> implements IParameter {
    private String type;
    private String name = null;
    private boolean isRequired;
    private T value;

    public BaseParameter(String type, String name) {
        this.type = type;
        this.isRequired = true;
        this.name = name.toLowerCase();
    }

    public BaseParameter(String type) {
        this(type, type);
    }

    protected void setValue(T value) {
        this.value = value;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean isRequired() {
        return isRequired;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public String getErrorMessage(String input) {
        return String.format("<par>%s</par> is not a valid <par>%s</par>", input, type);
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }
}
