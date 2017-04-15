package wildtornado.org.util;

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
}
