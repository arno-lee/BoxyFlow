package com.boxyflow.base;

import java.util.ArrayList;

/**
 * Created by yari on 20.07.16.
 */
public class Signal {
    private Object[] data;
    public Object[] getData() {
        return data;
    }

    public Signal() {}

    public Signal(Object[] data) {
        this.data = data;
    }

    public static Signal compose(ArrayList<Signal> data) {
        Signal composed = new Signal();
        composed.data = new Object[data.size()];

        int index = 0;
        for (Signal s : data) {
            composed.data[index++] = (s.data.length == 1)
                    ? s.data[0] : s.data;
        }

        return composed;
    }
}
