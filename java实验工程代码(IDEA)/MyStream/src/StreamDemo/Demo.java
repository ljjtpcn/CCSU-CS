package StreamDemo;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * @author 16083
 */
public class Demo {
    public static void main(String[] args) throws MalformedURLException {

        ArrayList<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("aaabbb");
        list.add("a");
        list.add("baabbb");
        list.add("caabbbb");
        //生成stream流
        Stream<String> stream = list.stream();
        stream.filter(s -> s.startsWith("a")).filter(s->s.length() >= 2).forEach(System.out::println);
    }
}
