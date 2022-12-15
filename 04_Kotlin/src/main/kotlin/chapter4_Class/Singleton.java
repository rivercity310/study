package chapter4_Class;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    private static List<Integer> arr;

    public static List<Integer> of() {
        if (arr == null) arr = new ArrayList<>();
        return arr;
    }
}
