package me.caneva20.Core.CommanderV2.BuiltIn.Parameters;

import me.caneva20.Core.CommanderV2.ParameterProcessor.BaseParameter;

public class FloatParameter extends BaseParameter<Float> {
    public FloatParameter() {
        super("float");
    }

    @Override
    public boolean process(String input) {
        try {
            setValue(Float.parseFloat(input));

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
