package online.xiaohei.project.practice.stream;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * 来源
 * https://www.jianshu.com/p/44add11a6fa0
 */
public class TestStreamAPI {
    /**
     * 1
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢？
     * ，给定【1，2，3，4，5】， 应该返回【1，4，6，25】。
     */
    @Test
    public void test1() {
        Integer[] nums = new Integer[]{1, 2, 3, 4, 5};
        Stream<Integer> stream = Stream.of(nums);
        stream.map(n -> n * n).forEach(System.out::println);
    }

    /**
     * 2
     * 怎样用 map 和 reduce 方法数一数流中有多少个Employee呢？
     */
    List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66, Status.BUSY),
            new Employee(101, "张三", 18, 9999.99, Status.FREE),
            new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
            new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(104, "赵六", 8, 7777.77, Status.FREE),
            new Employee(105, "田七", 38, 5555.55, Status.BUSY)
    );

    @Test
    public void test2() {
        Optional<Integer> nums = emps.stream().map(e -> 1).reduce(Integer::sum);

        System.out.println();
    }


    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
     * 3 找出2011年发生的所有交易， 并按交易额排序（从低到高）
     */
    @Test
    public void test3() {
        transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted((a, b) -> Integer.compare(a.getValue(), b.getValue()))
                .forEach(System.out::println);
        transactions.stream()
                .filter(x -> x.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
    }


    /**
     * 4 交易员都在哪些不同的城市工作过？
     */
    @Test
    public void test4() {
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 5 查找所有来自剑桥的交易员，并按姓名排序
     */
    @Test
    public void test5() {
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .sorted((x, y) -> x.getTrader().getName().compareTo(y.getTrader().getName()))
                .distinct()
                .forEach(System.out::println);
//        transactions.stream()
//                .filter(t->t.getTrader().getCity().equals("Cambridge"))
//                .sorted(Comparator.comparing(Transaction::getTrader))
    }

    /**
     * 6 返回所有交易员的姓名字符串，按字母顺序排序
     *
     * @output Alan
     * Brian
     * Mario
     * Mario
     * Raoul
     * Raoul
     * ------------------
     * AlanBrianMarioMarioRaoulRaoul------------------
     * aaaaaAaBiiilllMMnnoooorRRrruu
     */
    @Test
    public void test6() {
        transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
        System.out.println("------------------");
        String str = transactions.stream()
                .map((t) -> t.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.print(str);
        System.out.println("------------------");
        transactions.stream()
                .map(t -> t.getTrader().getName())
                .flatMap(TestStreamAPI::filterCharacter)
                .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                .forEach(System.out::print);
    }

    public static Stream<String> filterCharacter(String str) {
        List<String> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }

    /**
     * 7 有没有交易员是在米兰工作的？
     */
    public void test7() {
        boolean b = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(b);
    }

    /**
     * 8 打印生活在剑桥的交易员的所有交易额
     */
    public void test8() {
        Optional<Integer> sum = transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
    }

    /**
     * 9 所有交易中，最高的交易额是多少
     */
    public void test9() {
        Optional<Integer> max = transactions.stream()
                .map(t -> t.getValue())
                .max(Integer::compare);
        System.out.println(max.get());
    }


    /**
     * 10 找到交易额的最小的交易
     */
    @Test
    public void test10() {
        Optional<Transaction> op1 = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        System.out.println("op1 " + op1.get());

        Optional<Transaction> op2 = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));
        System.out.println("op2 " + op2.get());
    }


}
