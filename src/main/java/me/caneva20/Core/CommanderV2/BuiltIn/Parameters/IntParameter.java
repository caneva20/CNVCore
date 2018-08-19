package me.caneva20.Core.CommanderV2.BuiltIn.Parameters;

import me.caneva20.Core.CommanderV2.ParameterProcessor.BaseParameter;

public class IntParameter extends BaseParameter<Integer> {
    public IntParameter() {
        super("integer");
    }

    @Override
    public boolean process(String input) {
        try {
            int i = Integer.parseInt(input);
            setValue(i);

            return true;
        } catch (NumberFormatException ignored) {
        }

        return false;
    }
}
