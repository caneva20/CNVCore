package me.caneva20.Core.CommanderV2;

import javafx.util.Pair;
import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.Core;

import java.util.HashMap;
import java.util.Map;

public class CommandArgument {
    private final ICommand command;
    private Map<String, Pair<IParameter, Boolean>> parameters = new HashMap<>(); //Boolean = has been processed

    public CommandArgument(IParameter[] parameters, ICommand command) {
        this.command = command;
        setParameters(parameters);
    }

    private void setParameter(IParameter parameter, Boolean processed) {
        parameters.put(parameter.getName().toLowerCase(), new Pair<>(parameter, processed));

        if (!processed) {
            Core.logger().debug(Strings.parameterAddedToArgumets(parameter, command));
        }
    }

    private void setParameters(IParameter[] parameters) {
        for (IParameter parameter : parameters) {
            setParameter(parameter, false);
        }
    }

    private boolean hasParameter(String name) {
        return parameters.containsKey(name.toLowerCase());
    }

    private boolean hasParameter(IParameter parameter) {
        return hasParameter(parameter.getName());
    }

    private Pair<IParameter, Boolean> getPair(String name) {
        return parameters.get(name.toLowerCase());
    }

    private Pair<IParameter, Boolean> getPair(IParameter parameter) {
        return getPair(parameter.getName());
    }

    private IParameter getParameter(String name) {
        return getPair(name).getKey();
    }

    private boolean isProcessed(IParameter parameter) {
        return hasParameter(parameter) && getPair(parameter).getValue();
    }

    public void addProcessed(IParameter parameter) {
        if (!hasParameter(parameter)) {
            Core.logger().errorConsole(Strings.parameterCantBeProcessed(parameter));

            return;
        }

        Core.logger().debug(Strings.parameterProcessed(parameter, command));

        setParameter(parameter, true);
    }

    public boolean has(String name) {
        return hasParameter(name) && isProcessed(getParameter(name));
    }

    public <T> T get(String name) {
        if (!hasParameter(name)) {
            Core.logger().errorConsole(Strings.parameterDoesNotExist(name, command));

            return null;
        }

        IParameter parameter = getParameter(name);

        if (!isProcessed(parameter)) {
            Core.logger().errorConsole(Strings.parameterFoundButNotProcessed(parameter));
            Core.logger().errorConsole(Strings.youShouldCallHasBeforeGet(parameter));

            return null;
        }

        return parameter.get();
    }
}
