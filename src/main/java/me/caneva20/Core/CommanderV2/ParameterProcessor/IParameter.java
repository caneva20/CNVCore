package me.caneva20.Core.CommanderV2.ParameterProcessor;

public interface IParameter {
    String getType();

    void setType(String type);

    String getName();

    void setName(String name);

    boolean isRequired();

    void setRequired(boolean required);

    boolean process(String input);

    <T> T get();

    String getErrorMessage(String input);
}
