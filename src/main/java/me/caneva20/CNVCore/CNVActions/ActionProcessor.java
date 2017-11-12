package me.caneva20.CNVCore.CNVActions;

import me.caneva20.CNVCore.CNVActions.Actions.*;
import me.caneva20.CNVCore.Util.KeyValuePair;
import me.caneva20.CNVCore.VariableProcessor.VariableProcessor;

import java.util.HashMap;
import java.util.Map;

public class ActionProcessor {
    //Singleton
    private static ActionProcessor instance;

    public static ActionProcessor get() {
        if (instance == null) {
            instance = new ActionProcessor(true);
        }

        return instance;
    }
    //End singleton

    //Makes it only instantiable from this package
    private ActionProcessor(boolean withDefaults) {
        if (withDefaults) {
            addAction(new ItemAction());
            addAction(new MoneyAction());
            addAction(new PermissionAction());
            addAction(new MessageAction());
            addAction(new GlobalMessageAction());
            addAction(new CommandAction());
        }
    }

    /**
     * You should ONLY create a new manager if don't want the default actions
     * */
    public static ActionProcessor create () {
        return new ActionProcessor(false);
    }

    private Map<String, Action> actions = new HashMap<>();

    public void addAction(Action action) {
        actions.put(action.getName(), action);
    }

    public void processAction(String in, Map<String, Object> extraParams) {
        actions.forEach((name, action) -> {
            //If the in string matches ACTION_NAME:[PARAMS]
            if (in.matches("^" + name + ":\\[.*]$")) {
                //Get only what is inside [] and split them at the ;
                //The is all the parameters
                String[] params = in.replaceAll("^" + name + ":\\[(.*)]$", "$1").split(";");

                //Holds the list of all parameters with its value
                Map<String, Object> actionParams = new HashMap<>();

                //For each parameter of this action...
                action.getParameters().forEach((paramName, actionParameter) -> {
                    //For each parameter of this action we first se its default value, in case it is not provided
                    actionParams.put(paramName, actionParameter.getDefaultValue());

                    for (String param : params) {
                        if (param.matches("^" + paramName + ":(.*)$")) {
                            String value = param.replaceAll("^" + paramName + ":(.*)$", "$1");
                            value = VariableProcessor.get().process(value);

                            actionParams.put(paramName, actionParameter.getValue(value));
                        }
                    }
                });

                //And at last we put all the extra parameters in the list, it may override some already defined value
                actionParams.putAll(extraParams);

                //Then, we call the action with its parameters
                action.process(actionParams, in);
            }
        });
    }

    public void processAction(String in) {
        processAction(in, new HashMap<>());
    }

    @SafeVarargs
    public final void processAction(String in, KeyValuePair<String, Object>... extra) {
        Map<String, Object> map = new HashMap<>();

        for (KeyValuePair pair : extra) {
            map.put((String) pair.getKey(), pair.getValue());
        }

        processAction(in, map);
    }

    public void removeAction(String actionName) {
        actions.remove(actionName);
    }
}























