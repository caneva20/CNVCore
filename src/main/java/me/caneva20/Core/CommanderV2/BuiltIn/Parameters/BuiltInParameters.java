package me.caneva20.Core.CommanderV2.BuiltIn.Parameters;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;

public class BuiltInParameters {
    public static final Class<? extends IParameter<String>> String = StringParameter.class;
    public static final Class<? extends IParameter<Integer>> Int = IntParameter.class;
    public static final Class<? extends IParameter<Float>> Float = FloatParameter.class;
    public static final Class<? extends IParameter<org.bukkit.entity.Player>> Player = PlayerParameter.class;
}
