package wildtornado.org.clustering.util;

import java.util.Arrays;
import java.util.Collections;

public class RandomIntegers {

    //Returns x integers in random order.
    public Integer[] rng(int x) {
        Integer[] arr = new Integer[x];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
        return arr;
    }
}
