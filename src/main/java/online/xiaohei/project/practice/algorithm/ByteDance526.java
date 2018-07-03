package online.xiaohei.project.practice.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ByteDance526 {
// n 个正整数，a1~an，分割成m段，
    //二分+贪心
    // k段，每一段的和是s，当前分割中的最大值段为sum，求所有分割方案里最小的sum

}
//
//class Main {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        double[] num = new double[n];
//        for (int i = 0; i < n; i++) {
//            num[i] = scanner.nextInt();
//        }
////        int n = 5, m = 3;
////        int[] num = {1, 4, 2, 3, 5};
//        System.out.println((long)minSum(num, m));
//    }
//
//    public static double minSum(double[] num, int k) {
//        double low = Integer.MIN_VALUE, high = 0;
//        for (double i : num) {
//            low += i;
//            if (high < i) high = i;
//        }
//        while (low < high) {
//            double mid = low + (high - low) / 2;
//            double tmp = 0;
//            int n = 1;
//            for (int i = 0; i < num.length; i++) {
//                tmp += num[i];
//                if (tmp > mid) {
//                    tmp = num[i];
//                    n++;
//                }
//            }
//            if (n <= k) {
//                high = mid;
//            } else {
//                low = mid + 1;
//            }
//        }
//        return low;
//    }
//}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] A = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char a = scanner.next().toCharArray()[0];
                switch (a) {
                    case '0':
                        A[i][j] = 0;
                        break;
                    case '1':
                        A[i][j] = 1;
                        break;
                    case '2':
                        A[i][j] = 2;
                        break;
                    case '3':
                        A[i][j] = 3;
                        break;
                    case '4':
                        A[i][j] = 4;
                        break;
                    case '5':
                        A[i][j] = 5;
                        break;
                    case '6':
                        A[i][j] = 6;
                        break;
                    case '7':
                        A[i][j] = 7;
                        break;
                    case '8':
                        A[i][j] = 8;
                        break;
                    case '9':
                        A[i][j] = 9;
                        break;
                    case 'a':
                        A[i][j] = 10;
                        break;
                    case 'b':
                        A[i][j] = 11;
                        break;
                    case 'c':
                        A[i][j] = 12;
                        break;
                    case 'd':
                        A[i][j] = 13;
                        break;
                    case 'e':
                        A[i][j] = 14;
                        break;
                    case 'f':
                        A[i][j] = 15;
                        break;
                }
            }
        }
        new Main().findMinZeroPath(A, n, m);


    }

    class Node {
        String str;
        long total;
        Node down;
        Node right;

        Node() {
        }

        Node(long total1) {
            total = total1;
        }
    }

    public void findMinZeroPath(int[][] A, int n, int m) {
        int i = 1, j = 1;
        Node root = new Node();
        root.total = A[0][0];
        root.str="";
        int[] minNum = new int[1];
        String[] path = new String[1];
        getBtree(root, A, n, m, 0, 0, minNum, path);
        System.out.println(minNum[0]);
        System.out.println(path[0]);
    }

    public void getBtree(Node node, int[][] A, int n, int m, int i, int j, int minNum[], String[] path) {
        if (!(i == n - 1 && j == m - 1)) {
            if (i < n - 1) {
                Node downNode = new Node(node.total * A[i + 1][j]);
                if(i==1&&j==0){
                    downNode.str="V";
                }else{
                    downNode.str += "V";
                }
                node.down = downNode;
                getBtree(downNode, A, n, m, i + 1, j, minNum, path);
            }
            if (j < m - 1) {
                Node rightNode = new Node(node.total * A[i][j + 1]);
                if(i==1&&j==0){
                    rightNode.str = ">";
                }else{
                    rightNode.str += ">";
                }
                node.right = rightNode;
                getBtree(rightNode, A, n, m, i, j + 1, minNum, path);
            }
        } else {
            int zNum = getHexZeroNum(node.total);
            if (zNum < minNum[0]) {
                minNum[0] = zNum;
                path[0] = node.str;
            }
        }
    }

    public static int getHexZeroNum(long l) {
        char[] c = Long.toHexString(l).toCharArray();
        int count = 0;
        for (int i = c.length - 1; i >= 0; i--) {
            if (c[i] != '0') {
                return count;
            }
            count++;
        }
        return count;
    }
}