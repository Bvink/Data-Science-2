package wildtornado.org.util;

import java.util.List;

public class Util {

    //Return the value from an object as a double.
    public static double getVal(Object obj) {
        double d = 0d;
        try {
            d = Double.parseDouble(obj.toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return d;
    }

    //Return length without null values.
    public static int getLength(List<Double> l) {
        int count = 0;
        for (Double d : l)
            if (d != null)
                ++count;
        return count;
    }
}
