package com.boxyflow;

import com.boxyflow.base.*;
import com.boxyflow.misc.*;



public class Main {

    public static void main(String[] args) {
        System.out.println("Testing Boxes With Wires ...");

        Wire inputToA = new Wire();
        Wire wireAtoB = new Wire();
        Wire wireBtoC123 = new Wire();
        Wire wireC1toG = new Wire();
        Wire wireC2toG = new Wire();
        Wire wireC3toG = new Wire();
        Wire outputFromG = new Wire();

        Box boxA = new Box(inputToA, "A",
                FHelper.fHelper1("A", 10), wireAtoB);
        Box boxB = new Box(boxA.getOutput(), "B",
                FHelper.fHelper1("B", 1000), wireBtoC123);
        Box boxC1 = new Box(boxB.getOutput(), "C1",
                FHelper.fHelper1("C1", 2), wireC1toG);
        Box boxC2 = new Box(boxB.getOutput(), "C2",
                FHelper.fHelper1("C2", 3), wireC2toG);
        Box boxC3 = new Box(boxB.getOutput(), "C3",
                FHelper.fHelper1("C3", 5), wireC3toG);
        Box boxG = new Box(new Wire[]{wireC1toG, wireC2toG, wireC3toG}, "G",
                FHelper.fHelper3("G", 7, 11, 17), outputFromG);

        inputToA.addDownstream(boxA);

        wireAtoB.setUpstream(boxA);
        wireAtoB.addDownstream(boxB);

        wireBtoC123.setUpstream(boxB);
        wireBtoC123.addDownstream(boxC1);
        wireBtoC123.addDownstream(boxC2);
        wireBtoC123.addDownstream(boxC3);

        wireC1toG.setUpstream(boxC1);
        wireC1toG.addDownstream(boxG);

        wireC2toG.setUpstream(boxC2);
        wireC2toG.addDownstream(boxG);

        wireC3toG.setUpstream(boxC3);
        wireC3toG.addDownstream(boxG);


        // Test the setup
        inputToA.feed(new Signal(new Integer[]{5}));
    }
}
