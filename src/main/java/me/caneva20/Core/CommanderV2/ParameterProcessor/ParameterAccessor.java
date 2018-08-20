package me.caneva20.Core.CommanderV2.ParameterProcessor;

import me.caneva20.Core.CommanderV2.Arguments;

public class ParameterAccessor<T> {
    private final String name;
    private Arguments args;

    public ParameterAccessor(String name) {
        this.name = name;
    }

    public void process(Arguments args) {
        this.args = args;
    }

    public void dispose() {
        this.args = null;
    }

    public T get() {
        return args.get(name);
    }

    public boolean has() {
        return args.has(name);
    }

    public boolean sent() {
        return has();
    }

    public boolean provided() {
        return has();
    }
}
