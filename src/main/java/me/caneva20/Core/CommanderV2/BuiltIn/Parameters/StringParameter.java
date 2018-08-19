package me.caneva20.Core.CommanderV2.BuiltIn.Parameters;

import me.caneva20.Core.CommanderV2.ParameterProcessor.BaseParameter;

public class StringParameter extends BaseParameter<String> {
    public StringParameter() {
        super("string");
    }

    @Override
    public boolean process(String input) {
        setValue(input);
        return true;
    }

    @Override
    public String getErrorMessage(String input) {
        return String.format("<par>%s</par> is not a valid string/text", input);
    }
}
