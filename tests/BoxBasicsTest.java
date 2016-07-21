import com.boxyflow.base.Box;
import com.boxyflow.base.Signal;
import com.boxyflow.base.Wire;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.boxyflow.misc.FHelper;

import java.util.function.Function;


/**
 * Created by yari on 20.07.16.
 *
 * Basic functionality test
 */

public class BoxBasicsTest {
    private static Function<Signal, Signal> fHelper1(String id, int param, int assertVal) {
        return (i) -> {
            int inp = (int)i.getData()[0];

            //System.out.print(id+" engaged with "+inp);
            //System.out.println(" | param is "+param);
            int res = inp*param;
            //System.out.println(id+" result "+res);

            assertEquals("In box "+id, res, assertVal);

            return new Signal(new Integer[]{res});
        };
    }

    private Function<Signal, Signal> fHelper3(String id, int param1,
                                              int param2, int param3, int assertVal) {
        return (i) -> {
            int[] inp = new int[3];
            for (int v = 0; v < 3; v++) {
                inp[v] = (int)i.getData()[v];
            }

            //System.out.print(id+" engaged with "+inp[0]+" "+inp[1]+" "+inp[2]);
            //System.out.println(" | params are "+param1+" "+param2+" "+param3);
            int res = inp[0]*param1+inp[1]*param2+inp[2]*param3;
            //System.out.println(id+" result "+res);

            assertEquals("In box "+id, res, assertVal);

            return new Signal(new Integer[]{res});
        };
    }

    @Test
    public void testBasics() {

        int expectedResult = 6600000;

        Wire inputToA = new Wire();
        Wire wireAtoB = new Wire();
        Wire wireBtoC123 = new Wire();
        Wire wireC1toG = new Wire();
        Wire wireC2toG = new Wire();
        Wire wireC3toG = new Wire();
        Wire outputFromG = new Wire();

        Box boxA = new Box(inputToA, "A",
                fHelper1("A", 10, 50), wireAtoB);
        Box boxB = new Box(boxA.getOutput(), "B",
                fHelper1("B", 1000, 50000), wireBtoC123);
        Box boxC1 = new Box(boxB.getOutput(), "C1",
                fHelper1("C1", 2, 100000), wireC1toG);
        Box boxC2 = new Box(boxB.getOutput(), "C2",
                fHelper1("C2", 3, 150000), wireC2toG);
        Box boxC3 = new Box(boxB.getOutput(), "C3",
                fHelper1("C3", 5, 250000), wireC3toG);
        Box boxG = new Box(new Wire[]{wireC1toG, wireC2toG, wireC3toG}, "G",
                fHelper3("G", 7, 11, 17, 6600000), outputFromG);

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