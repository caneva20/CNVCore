package me.caneva20.Core.VariableProcessor.Variables;

import me.caneva20.Core.Util.Random;
import me.caneva20.Core.VariableProcessor.Variable;

public class RandomVariable implements Variable {
    @Override
    public String process(String input) {
        String[] split = input.split(",");

        if (split.length != 2) {
            return "!!INVALID VALUES!!";
        }

        int min = 0;
        int max = 0;

        try {
            min = Integer.parseInt(split[0]);
            max = Integer.parseInt(split[1]);
        } catch (NumberFormatException ignore) {}

        return Random.range(min, max) + "";
    }
}





















