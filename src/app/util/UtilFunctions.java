package app.util;

import java.util.Collection;
import java.util.stream.Stream;

public class UtilFunctions {
    public static <T> boolean containsInstanceOf (Collection<?> arrayList, Class<T> c) {
        return arrayList.stream()
                .anyMatch(c::isInstance);
        for(Object o : arrayList)
            if (o != null && o.getClass() == c)
                return true;
        return false;
    }

    public static int mod(int a, int b) { return (a%b + b)%b; }
}
