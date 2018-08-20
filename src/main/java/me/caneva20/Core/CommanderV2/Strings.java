package me.caneva20.Core.CommanderV2;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import org.apache.commons.lang.StringUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Strings {
    //Arguments
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

//    public static String _1() {
//        return "";
//    }

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

    public static String noDescriptionGiven(String commandName) {
        return String.format("No description given for <par>%s</par>", commandName);
    }

    public static String parameterProcessed(IParameter parameter, ICommand command) {
        return String.format("Parameter <par>%s</par> was processed for <par>%s</par>", parameter.getName(), command.getName());
    }

    public static String parameterAddedToArguments(IParameter parameter, ICommand command) {
        return String.format("Parameter <par>%s</par> was added to <par>%s</par>", parameter.getName(), command.getName());
    }


    //Commander
//    public static String _1() {
//        return "";
//    }

    public static String commandRegistered(ICommand command, Commander commander) {
        String aliases = StringUtils.join(command.getAliases(), ", ");

        return String.format("Command <par>%s</par> registered for <par>%s</par> with <par>%s</par> aliases, [<par>%s</par>]",
                command.getName(), commander.getName(), command.getAliases().size(), aliases);
    }

    public static String commandNotFoundInPluginYml(String name) {
        return String.format("Command <par>%s</par> was not found in plugin.yml", name);
    }

    public static String commandRegistered(String name) {
        return String.format("Command <par>%s</par> registered", name);
    }

    public static String pluginTriedToRegisterNullCommand(JavaPlugin plugin) {
        return String.format("<par>%s</par> tried to register a <par>null</par> command", plugin.getName());
    }

    public static String yourCommandCantBeExecuted() {
        return "Sorry, but your command could not be executed";
    }

    public static String commandTookToExecute(ICommand command, long totalNs, boolean hasRun) {
        return String.format("Command <par>%s</par> took <par>%s</par>ms (<par>%s</par>ns) to process. "
                + "(With%s command.run())", command.getName(), totalNs / 1000000D, totalNs, hasRun ? "" : "out");
    }

    public static String invalidCommand() {
        return "Invalid command";
    }

    public static String commandNotFound(String name) {
        return String.format("Command <par>%s</par> not found", name);
    }

    public static String noPermissionToRunCommand() {
        return "Sorry, you don't have permission to run this command";
    }

    public static String commandOnlyExecutableByPlayers() {
        return "This command can only be executed by players";
    }

    public static String commandOnlyExecutableByConsole() {
        return "This command can only be executed through the console";
    }

    //ParameterProcessor
    public static String yourCommandCantBeProcessedByWrongLength(int min, int max, int size) {
        return String.format("Sorry, but your command can't be processed because it requires <par>%s</par>" +
                " to <par>%s</par> parameters and you gave <par>%s</par>", min, max, size);
    }
}
