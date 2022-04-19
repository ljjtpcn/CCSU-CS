package Function;

import java.util.function.Function;

public class FunctionDemo {
    public static void main(String[] args) {
        int ans = useFunction("林青霞,30", s -> Integer.parseInt(s.split(",")[1]),
                i -> i + 70);
        System.out.println(ans);
    }

    //返回值integer
    public static int useFunction(String s, Function<String, Integer> fun1, Function<Integer, Integer> fun2){
        return fun1.andThen(fun2).apply(s);
    }
}
