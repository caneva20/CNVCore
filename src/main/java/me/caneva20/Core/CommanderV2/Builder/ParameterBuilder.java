package me.caneva20.Core.CommanderV2.Builder;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.CommanderV2.ParameterProcessor.ParameterAccessor;

import java.util.UUID;

public class ParameterBuilder {
    private IParameter parameter;
    private BaseCommandBuilder commandBuilder;

    private boolean hasName;

    public ParameterBuilder(IParameter parameter, BaseCommandBuilder commandBuilder) {
        this.parameter = parameter;
        this.commandBuilder = commandBuilder;
    }

    public ParameterBuilder required() {
        parameter.setRequired(true);

        return this;
    }

    public ParameterBuilder optional() {
        parameter.setRequired(false);

        return this;
    }

    public ParameterBuilder name(String name) {
        parameter.setName(name);
        hasName = true;

        return this;
    }

    public IParameter build() {
        return parameter;
    }

    public <T> ParameterAccessor<T> getAccessor() {
        if (!hasName) {
            name(UUID.randomUUID().toString());
        }

        ParameterAccessor<T> accessor = new ParameterAccessor<>(parameter.getName());

        commandBuilder.registerAccessor(accessor);

        return accessor;
    }
}
