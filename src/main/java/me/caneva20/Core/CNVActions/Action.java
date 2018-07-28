package me.caneva20.Core.CNVActions;

import me.caneva20.Core.CNVActions.Paramenters.ActionParameter;
import me.caneva20.Core.Core;
import me.caneva20.Core.Util.KeyValuePair;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class Action {
    private String name;
    //    private String raw;
    private Map<String, ActionParameter> parameters = new HashMap<>();

    private Map<String, Object> params = new HashMap<>();
    //    private Map<String, Boolean> validations = new HashMap<>();
    private Map<String, KeyValuePair<Boolean, String>> validations = new HashMap<>();

    public Action(String name, Map<String, ActionParameter> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public Action(String name) {
        this.name = name;
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public Map<String, ActionParameter> getParameters() {
        return parameters;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(Map<String, ActionParameter> parameters) {
        this.parameters = parameters;
    }

    public void addParameter(String name, ActionParameter parameter) {
        parameters.put(name, parameter);
    }

    //Public
    public void process(Map<String, Object> params, String raw) {
        if (!validate(params)) {
            Core.getMainLogger().errorConsole("Invalid action. Some required parameters are not defined");
            Core.getMainLogger().errorConsole("RAW Action: <par>" + raw + "</par>");
            Core.getMainLogger().errorConsole("---------------------(Details)-----------------------");

            getValidation(params).forEach((param, validation) -> Core.getMainLogger().errorConsole(
                    "PARAM: <par>" + param + "</par>: " + (validation.getKey() ? "&aOK" : "&cInvalid, Required") + " > " + validation.getValue()
            ));

            Core.getMainLogger().errorConsole("------------------------------------------------------");
            Core.getMainLogger().errorConsole("");
            return;
        }

//        this.params = params;
        process();
    }

    /**
     * Should ONLY be called by the action itself
     */
    protected abstract void process();

    protected boolean validate(Map<String, Object> params) {
        final boolean[] valid = {true};

        parameters.forEach((param, actionParameter) -> {
            if (actionParameter.isRequired()) {
                Object value = params.get(param);

                if (value == null || value == actionParameter.getDefaultValue()) {
                    valid[0] = false;
                }
            }
        });

        this.params = params;
        return valid[0] && validate();
    }

    protected abstract boolean validate();

    protected Map<String, KeyValuePair<Boolean, String>> getValidation(Map<String, Object> params) {
        this.params = params;
//        Map<String, Boolean> validations = new HashMap<>();

        parameters.forEach((param, actionParameter) -> {
            if (actionParameter.isRequired()) {
                Object value = get(param);

                if (value == null || value == actionParameter.getDefaultValue()) {
                    validation(param, false, "The return value is null or its default");
                } else {
                    validation(param, true, "Param valid!");
                }
            }
        });

        //Gets the children's validation result
        getValidation();

        return validations;
    }

    protected abstract void getValidation();

    protected <T> T get(Map<String, Object> params, String key) {
        return (T) params.get(key);
    }

    protected <T> T get(String key) {
        return (T) params.get(key);
    }

    protected void validation(String key, Boolean validation, String reason) {
        validations.put(key, new KeyValuePair<>(validation, reason));
    }
}