package me.caneva20.CNVCore.VariableProcessor.Variables;

import me.caneva20.CNVCore.Util.Random;
import me.caneva20.CNVCore.VariableProcessor.Variable;

public class PickRandomVariable implements Variable {
    @Override
    public String process(String input) {
        String[] split = input.split(",");

        if (split.length == 0) {
            return "!!INVALID VALUES!!";
        }

        return split[Random.range(0, split.length)];
    }
}
