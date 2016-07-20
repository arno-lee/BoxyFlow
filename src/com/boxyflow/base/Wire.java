package com.boxyflow.base;

import java.util.ArrayList;

/**
 * Created by yari on 20.07.16.
 */
public class Wire {
    private Box upstreamBox;
    private ArrayList<Box> downstreamBoxes;

    public void setUpstream(Box upstreamBox) {
        this.upstreamBox = upstreamBox;
    }

    public void addDownstream(Box downstreamBox) {
        this.downstreamBoxes.add(downstreamBox);
    }

    public Wire() {
        this.downstreamBoxes = new ArrayList<Box>();
    }

    public Wire(Box upstreamBox, Box[] downstreamBoxes) {
        this.upstreamBox = upstreamBox;
        this.downstreamBoxes = new ArrayList<Box>();

        if (downstreamBoxes != null) {
            for (Box downstreamBox : downstreamBoxes) {
                this.downstreamBoxes.add(downstreamBox);
            }
        }
    }

    public void feed(Signal input) {
        for (Box downstreamBox : downstreamBoxes) {
            downstreamBox.process(input);
        }
    }
}
