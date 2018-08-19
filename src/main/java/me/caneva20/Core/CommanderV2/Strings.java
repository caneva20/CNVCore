package me.caneva20.Core.CommanderV2;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class Strings {
    //CommandArgument
    public static String parameterCantBeProcessed(IParameter parameter) {
        return String.format("Parameter <par>%s</par> can't be added as processed because it is not part of the parameters list", parameter.getName());
    }

    public static String parameterDoesNotExist(String name, ICommand command) {
        return String.format("Parameter <par>%s</par> does not exist for command <par>%s</par> of <par>%s</par>", name, command.getName(), command.getCommander().getName());
    }

    public static String parameterFoundButNotProcessed(IParameter parameter) {
        return String.format("Parameter <par>%s</par> was found but it is was processed because it is optional and was not provided by the sender.", parameter.getName());
    }

    public static String youShouldCallHasBeforeGet(IParameter parameter) {
        return String.format("You should call <par>args.has(\"%s\")</par> before trying to get its value to"
                + " check whether or not the sender has sent this parameter", parameter.getName());
    }

    //BuilderCommand

//    static String _1() {}

    public static String _1() {
        return "";
    }

    public static String commandAlreadyHasNameDefined(String commandName, String newName) {
        return String.format("<par>%s</par> already has a name defined, " +
                "but it is trying to change it to <par>%s</par>. " +
                "Please call the builder with empty constructor or call <par>commandName()</par> only once", commandName, newName);
    }

    public static String commandAlreadySetToConsole(String commandName) {
        return String.format("Command <par>%s</par> is already set to only console. %s", commandName, thisActionWillBeIgnored());
    }

    public static String commandAlreadySetToPlayer(String commandName) {
        return String.format("Command <par>%s</par> is already set to only players. %s", commandName, thisActionWillBeIgnored());
    }

    public static String thisActionWillBeIgnored() {
        return "This action will be ignored. You may use only one of <par>onlyConsole()</par> OR <par>onlyPlayers()</par>";
    }

    public static String parameterCantBeAddedCauseHasOptional(IParameter parameter, String commandName) {
        return String.format("Required parameter of type <par>%s</par> can't be added to <par>%s</par> because the last one is optional",
                parameter.getType(), commandName);
    }

    public static String parameterAddedToCommand(IParameter parameter, String commandName) {
        return String.format("Parameter <par>%s</par> of type <par>%s</par> was added to <par>%s</par> as <par>%s</par>",
                parameter.getName(), parameter.getType(), commandName, parameter.isRequired() ? "<required>" : "[optional]");
    }

    public static String noRunActionDefined(String commandName) {
        return String.format("No run action was defined for <par>%s</par>. Your command won't be registered", commandName);
    }

    public static String noDescriptionProvided(String commandName) {
        return String.format("No description provided for <par>%s</par>", commandName);
    }

    public static String commandHasXAliases(String commandName, Commander commander, List<String> aliases) {
        String alias = StringUtils.join(aliases, ", ");

        return String.format("Command <par>%s</par> of <par>%s</par> has <par>%s</par> alias(es), [<par>%s</par>]",
                commandName, commander.getName(), aliases.size(), alias);
    }

    public static String parameterProcessed(IParameter parameter, ICommand command) {
        return String.format("Parameter <par>%s</par> was processed for <par>%s</par>", parameter.getName(), command.getName());
    }

    public static String parameterAddedToArgumets(IParameter parameter, ICommand command) {
        return String.format("Parameter <par>%s</par> was added to <par>%s</par>", parameter.getName(), command.getName());
    }
}
