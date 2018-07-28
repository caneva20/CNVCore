package me.caneva20.Core.VariableProcessor;

import me.caneva20.Core.VariableProcessor.Variables.PickRandomVariable;
import me.caneva20.Core.VariableProcessor.Variables.RandomVariable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableProcessor {
    //Singleton
    private static VariableProcessor instance;

    public static VariableProcessor get() {
        if (instance == null) {
            instance = new VariableProcessor();
        }

        return instance;
    }
    //End singleton
    private VariableProcessor() {
        addVariable("RANDOM", new RandomVariable());
        addVariable("PICK_RANDOM", new PickRandomVariable());
    }

    private Map<String, Variable> variables = new HashMap<>();

    public void addVariable (String name, Variable variable) {
        variables.put(name, variable);
    }

    public String process(String in) {
        String regex = "\\{\\{(\\w+)(?:\\(([^}}]+)\\))?}}";
        if (in.matches(".*" + regex + ".*")) {
            Matcher m = Pattern.compile(regex).matcher(in);

            while (m.find()) {
                String group = m.group();
                String varName = group.replaceAll(regex, "$1");
                String varValue = group.replaceAll(regex, "$2");

                if (variables.containsKey(varName)) {
                    String result = variables.get(varName).process(varValue);

                    in = in.replace(group, result);
                }
            }
        }

        return in;
    }
}
