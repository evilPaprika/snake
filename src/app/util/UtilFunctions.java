package app.util;

import java.util.Collection;

public class UtilFunctions {
    public static <T> boolean containsInstanceOf (Collection<?> arrayList, Class<T> c) {
        for(Object o : arrayList)
            if (o != null && o.getClass() == c)
                return true;
        return false;
    }
    
    public static int mod(int a, int b) { return (a%b + b)%b; }
}
