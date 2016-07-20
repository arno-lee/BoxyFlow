package com.boxyflow.misc;

import com.boxyflow.base.Signal;

import java.util.function.Function;

/**
 * Created by yari on 20.07.16.
 */
public class FHelper {
    public static Function<Signal, Signal> fHelper1(String id, int param) {
        return (i) -> {
            int inp = (int)i.getData()[0];

            System.out.print(id+" engaged with "+inp);
            System.out.println(" | param is "+param);
            int res = inp*param;
            System.out.println(id+" result "+res);

            return new Signal(new Integer[]{res});
        };
    }

    public static Function<Signal, Signal> fHelper3(String id, int param1,
                                                    int param2, int param3) {
        return (i) -> {
            int[] inp = new int[3];
            for (int v = 0; v < 3; v++) {
                inp[v] = (int)i.getData()[v];
            }

            System.out.print(id+" engaged with "+inp[0]+" "+inp[1]+" "+inp[2]);
            System.out.println(" | params are "+param1+" "+param2+" "+param3);
            int res = inp[0]*param1+inp[1]*param2+inp[2]*param3;
            System.out.println(id+" result "+res);

            return new Signal(new Integer[]{res});
        };
    }
}
