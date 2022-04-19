package day1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Demo6 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> lists = new ArrayList<>();
        for(int i  = 1; i <= 10; i ++){
            lists.add(i);
        }

//        lists.add("daaa");
        Class<? extends ArrayList> c = lists.getClass();
        Method add = c.getDeclaredMethod("add", Object.class);
        //add.setAccessible(true);
        add.invoke(lists, "dad");

        System.out.println(lists);

    }
}
