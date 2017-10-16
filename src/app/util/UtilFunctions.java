package app.util;

import java.util.Collection;

public class UtilFunctions {
    public static <T> boolean containsInstanceOf (Collection<?> arrayList, Class<T> c) {
        return arrayList.stream()
                .anyMatch(c::isInstance);
    }

    public static int mod(int a, int b) { return (a%b + b)%b; }
}
