package online.xiaohei.project.practice.bytedance20180825;

import com.mysql.fabric.xmlrpc.base.Array;
import org.junit.Test;

import java.util.*;

/*
Scanner in = new Scanner(System.in);
            while (in.hasNextInt()) {//注意while处理多个case
                int a = in.nextInt();
                int b = in.nextInt();
                System.out.println(a + b);
            }
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = 1;
        int number = 0;
        int xNumber = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while(scanner.hasNextInt()){
            int tmp = scanner.nextInt();
            if(tmp==0){
                if(x>n){
                    break;
                }
                if(!map.containsKey(x)){
                    map.put(x,number++);
                }
                x++;
                if (!map.containsKey(x)) {
                    xNumber = number++;
                    map.put(x,xNumber);
                } else {
                    xNumber = map.get(x);
                }
                continue;
            }else{
                if (map.containsKey(tmp)) {
                    if (map.get(tmp) == xNumber) continue;
                    else {
                        int tmpNumber = map.get(tmp);
                        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                            if (entry.getValue() == tmpNumber) {
                                map.put(entry.getKey(), xNumber);
                            }
                        }
                    }
                } else {
                    map.put(tmp, xNumber);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer,Integer> entry: map.entrySet()) {
            if(entry.getKey()<=n)
                set.add(entry.getValue());
        }
        System.out.println(set.size());
    }

    public static void a(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int number = 0;
        int xNumber = 0;
        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            while (scanner.hasNextInt()) {
                int tmp = scanner.nextInt();
                if (tmp == 0) {
                    break;
                } else {
                    list.add(tmp);
                }
            }
            lists.add(list);
        }
        for (int x = 0; x < n; x++) {
            ArrayList<Integer> list = lists.get(x);
            if (!map.containsKey(x)) {
                xNumber = number++;
            } else {
                xNumber = map.get(x);
            }
            for (int tmp : list) {
                if (map.containsKey(tmp)) {
                    if (map.get(tmp) == xNumber) continue;
                    else {
                        int tmpNumber = map.get(tmp);
                        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                            if (entry.getValue() == tmpNumber) {
                                map.put(entry.getKey(), xNumber);
                            }
                        }
                    }
                } else {
                    map.put(tmp, xNumber);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i : map.values()) {
            set.add(i);
        }
        System.out.println(set.size());
    }

    //
    public static void b(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = scanner.nextInt();
        }
        // first li keyi qu de zui chang zi xulie
        if (t == 0) {
            System.out.println(0);
        } else if (t == 1) {
            System.out.println(find(s));
        } else if (t >= 2) {
            int[] s1 = new int[2 * n];
            for (int i = 0; i < n; i++) {
                s1[i] = s[i];
                s1[i + n] = s[i];
            }
            System.out.println(find(s1) + t - 2);
        }
    }

    public static int find(int[] a) {
        int n = a.length;
        int num[] = new int[n];
        int i, j;
        for (i = 0; i < n; i++)
        {
            num[i] = 1;
            for (j = 0; j < i; j++) {
                if (a[j] < a[i] && num[j] + 1 > num[i])
                    num[i] = num[j] + 1;
            }
        }
        int max = 0;
        for (i = 0; i < n; i++)
            if (max < num[i])
                max = num[i];
        return max;
    }

    public static void c(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        // each ()->++,+-,--,2digital
        // each number->+.-

    }
}