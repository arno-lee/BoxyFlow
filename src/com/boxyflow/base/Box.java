package com.boxyflow.base;

import java.util.ArrayList;
import java.util.function.Function;


/**
 * Created by yari on 20.07.16.
 */
public class Box {
    private String boxID;
    public String getID() { return boxID; }
    public Box setID(String id) {
        this.boxID = id;
        return this;
    }

    private Wire[] inputs;
    private Wire output;
    public Wire getOutput() {
        return output;
    }

    private ArrayList<Signal> arrivedSignals;

    private Function<Signal, Signal> action;

    public Box(Wire[] inputs, String id, Function<Signal, Signal> action, Wire output) {
        boxID = id;

        this.inputs = inputs;
        this.action = action;
        this.output = output;

        arrivedSignals = new ArrayList<Signal>();
    }

    public Box(Wire input, String id, Function<Signal, Signal> action, Wire output) {
        this(new Wire[]{input}, id, action, output);
    }

    public void process(Signal input) {
        arrivedSignals.add(input);
        if (inputs.length == arrivedSignals.size()) {
            Signal composedInput = Signal.compose(arrivedSignals);

            Signal actionResult = action.apply(composedInput);
            output.feed(actionResult);
        }
    }
}
