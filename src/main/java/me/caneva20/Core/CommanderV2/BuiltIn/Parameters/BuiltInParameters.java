package me.caneva20.Core.CommanderV2.BuiltIn.Parameters;

import me.caneva20.Core.CommanderV2.ParameterProcessor.IParameter;

public class BuiltInParameters {
    public static final Class<? extends IParameter> String = StringParameter.class;
    public static final Class<? extends IParameter> Int = IntParameter.class;
    public static final Class<? extends IParameter> Float = FloatParameter.class;
    public static final Class<? extends IParameter> Player = PlayerParameter.class;
}
