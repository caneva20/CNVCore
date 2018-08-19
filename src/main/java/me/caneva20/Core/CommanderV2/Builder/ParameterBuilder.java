package me.caneva20.Core.CommanderV2.Builder;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;
import me.caneva20.Core.CommanderV2.ParameterProcessor.ParameterAccessor;

import java.util.UUID;

public class ParameterBuilder<T> {
    private IParameter<T> parameter;
    private BaseCommandBuilder commandBuilder;

    private boolean hasName;

    public ParameterBuilder(IParameter<T> parameter, BaseCommandBuilder commandBuilder) {
        this.parameter = parameter;
        this.commandBuilder = commandBuilder;
    }

    public ParameterBuilder<T> required() {
        parameter.setRequired(true);

        return this;
    }

    public ParameterBuilder<T> optional() {
        parameter.setRequired(false);

        return this;
    }

    public ParameterBuilder<T> name(String name) {
        parameter.setName(name);
        hasName = true;

        return this;
    }

    public IParameter<T> build() {
        return parameter;
    }

    public ParameterAccessor<T> getAccessor() {
        if (!hasName) {
            name(UUID.randomUUID().toString());
        }

        ParameterAccessor<T> accessor = new ParameterAccessor<>(parameter.getName());

        commandBuilder.registerAccessor(accessor);

        return accessor;
    }
}
