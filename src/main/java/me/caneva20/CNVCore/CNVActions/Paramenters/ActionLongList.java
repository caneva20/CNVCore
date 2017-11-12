package me.caneva20.CNVCore.CNVActions.Paramenters;

import java.util.ArrayList;
import java.util.List;

public class ActionLongList extends ActionParameter {
    private Long[] defaultValue = new Long[0];

    public ActionLongList() {
    }

    public ActionLongList(boolean isRequired) {
        super(isRequired);
    }

    public ActionLongList(boolean isRequired, Long[] defaultValue) {

        super(isRequired);
        this.defaultValue = defaultValue;
    }

    public ActionLongList(Long[] defaultValue) {
        this.defaultValue = defaultValue;
    }

    @Override
    public Long[] getValue(String in) {
        String[] split = in.split(",,");
        List<Long> longs = new ArrayList<>();

        for (String s : split) {
            try {
                longs.add(Long.parseLong(s));
            } catch (NumberFormatException ignored) {
            }
        }

        return longs.toArray(new Long[0]);
    }

    @Override
    public Long[] getDefaultValue() {
        return defaultValue;
    }
}
