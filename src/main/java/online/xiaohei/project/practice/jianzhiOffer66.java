package online.xiaohei.project.practice;

import online.xiaohei.project.practice.bytedance20180825.Solution;

import java.util.*;

//===========================数据结构===============================
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
//=================================================================

public class jianzhiOffer66 {

    /*
    1. （数组）二维数组中的查找
    在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
    每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，
    判断数组中是否含有该整数。
     */
    // Solution:从左下到右上，右边的都比当前点大，上边的都比当前点小
    public boolean Find(int target, int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return false;
        }
        // from zuo xia
        int x = array.length - 1, y = 0;
        while (true) {
            // 右边的都比当前点大，上边的都比当前点小
            if (array[x][y] == target) {
                return true;
            } else if (array[x][y] > target) {
                x--;
                if (x < 0) {
                    return false;
                }
            } else {
                y++;
                if (y >= array[0].length) {
                    return false;
                }
            }
        }
    }

    /*
    2. （字符串）替换空格
    请实现一个函数，将一个字符串中的每个空格替换成“%20”。
    例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    // Solution:先遍历一遍，计算出替换后的长度，然后从后往前替换
    public String replaceSpace(StringBuffer str) {
        int length = str.length();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                length += 2;
            }
        }
        int preLength = str.length();
        str.setLength(length);
        for (int i = length - 1, j = preLength - 1; i > -1 && j > -1; i--, j--) {

            if (str.charAt(j) == ' ') {
                str.setCharAt(i, '0');
                i--;
                str.setCharAt(i, '2');
                i--;
                str.setCharAt(i, '%');
            } else {
                str.setCharAt(i, str.charAt(j));
            }

        }
        return str.toString();
    }

    /*
    3. （链表）从尾到头打印链表
    输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
     */
    // Solution: 1. 反转链表  2.递归呀！
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<>();
        if (listNode == null) {
            return list;
        }

        if (listNode.next == null) {
            list.add(listNode.val);
            return list;
        }
        ListNode p = listNode, q = listNode.next;
        p.next = null;
        while (q != null) {
            listNode = q.next;
            q.next = p;
            p = q;
            q = listNode;
        }
        ListNode head = p;

        while (p != null) {
            list.add(p.val);
            p = p.next;
        }
        return list;
    }

    /*
    4.重建二叉树
    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
     */
    // Solution:用先序来切分中序
    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                root.left = helper(pre, in, 1, i, 0, i - 1);
                root.right = helper(pre, in, i + 1, pre.length - 1, i + 1, in.length - 1);
                break;
            }

        }
        return root;
    }

    public static TreeNode helper(int[] pre, int[] in, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd) {
            return null;
        } else if (preStart == preEnd) {
            return new TreeNode(pre[preStart]);
        } else {
            TreeNode cNode = new TreeNode(pre[preStart]);
            for (int i = inStart; i <= inEnd; i++) {
                if (in[i] == pre[preStart]) {
                    cNode.left = helper(pre, in, preStart + 1, preStart + i - inStart, inStart, i - 1);
                    cNode.right = helper(pre, in, preEnd - inEnd + i + 1, preEnd, i + 1, inEnd);
                }
            }
            return cNode;
        }
    }

    /*
    5. 用两个栈实现队列
    用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
     */
    //Solution:push就直接往stack1里push，pop的时候从stack2pop, if stack2 empty, let stack1 push into stack2 then pop
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    // suppose s1 is tmp, s2 is queue sequence, each push ,make all in s1, each pop, make all in s2
    public void push(int node) {
        // if s1里有东西 if s1里没东西
        while (!s2.isEmpty()) {
            int tmp = s2.pop();
            s1.push(tmp);
        }
        s1.push(node);
        while (!s1.isEmpty()) {
            int tmp = s1.pop();
            s2.push(tmp);
        }

    }

    public int pop() {
        if (!s2.isEmpty()) {
            return s2.pop();
        } else {
            return -1;
        }
    }

    /*
    6. 旋转数组的最小数字
    把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
    例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */
    // Solution:二分查找
    public int minNumberInRotateArray(int[] array) {
        // binary search
        int lowIndex = 0, highIndex = array.length - 1, midIndex;
        while (lowIndex <= highIndex) {
            if (lowIndex == highIndex) {
                return array[lowIndex];
            }
            midIndex = (lowIndex + highIndex) / 2;

            if (array[0] < array[midIndex]) {
                lowIndex = midIndex + 1;
            } else if (array[0] > array[midIndex]) {
                highIndex = midIndex;
            } else if (array[0] == array[midIndex]) {
                return 0;
            }

        }
        return 0;
    }

    /*
    7.斐波那契数列
    大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
    n<=39
     */
    //Solution:d[n]=d[n-1]+d[n-2],1,1,2,3,5,...
    public int Fibonacci(int n) {
        int[] d = new int[n + 1];
        d[0] = 0;
        if (n > 0) d[1] = 1;
        if (n > 1) d[2] = 1;
        if (n > 2) {
            for (int i = 3; i < n + 1; i++) {
                d[i] = d[i - 1] + d[i - 2];
            }
        }
        return d[n];
    }

    /*
    8.跳台阶
    一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
     */
    //Solution:d[n]=d[n-1](在n-1的基础上，跳一个台阶）+d[n-2]（在n-2的基础上，跳两个台阶）
    public int JumpFloor(int target) {
        int[] d = new int[target + 1];
        d[0] = 0;
        if (target > 0) d[1] = 1;
        if (target > 1) d[2] = 2;
        if (target > 2) {
            for (int i = 3; i < target + 1; i++) {
                d[i] = d[i - 1] + d[i - 2];
            }
        }
        return d[target];
    }

    /*
    9.变态跳台阶
    一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     */
    // Solution:d[n]=d[0]+d[1]+...+d[n-1]
    public int JumpFloorII(int target) {
        int[] d = new int[target + 1];
        d[0] = 1;
        if (target > 0) d[1] = 1;
        if (target > 1) d[2] = 2;
        if (target > 2) {
            for (int i = 3; i < target + 1; i++) {
                for (int j = 0; j < i; j++) {
                    d[i] += d[j];
                }
            }
        }
        return d[target];
    }

    /*
    10.矩形覆盖
    我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
    请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
     */
    // Solution: d(n)=d(n-1)(横着）+d(n-2)(竖着）
    public int RectCover(int n) {
        // d(n)=d(n-1)+d(n-2)
        int[] d = new int[n + 1];
        d[0] = 0;
        if (n >= 1) {
            d[1] = 1;
        }
        if (n >= 2) {
            d[2] = 2;
        }
        if (n >= 3) {
            for (int i = 3; i <= n; i++) {
                d[i] = d[i - 1] + d[i - 2];
            }
        }
        return d[n];
    }

    /*
    11.二进制中1的个数
    输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
     */
    // Solution:用1（1自身左移运算，其实后来就不是1了）和n的每位进行位与，来判断1的个数
    // 1的二进制是 前面都是0，最后一位为1，也就是只有一个1，每次向左移位一下，
    // 使得flag的二进制表示中始终只有一个位为1，每次与n做位与操作，这样就相当于逐个检测n的每一位是否是1了。
    public int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((flag & n) != 0) {
                count++;
            }
            flag <<= 1;
        }
        return count;
    }

    /*
    12.数值的整数次方
    给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
     */
    // Solution:
    // 举例:10^1101 = 10^0001*10^0100*10^1000。
    // 通过&1和>>1来逐位读取1101，为1时将该位代表的乘数累乘到最终结果。
    // 考虑1、base为0，exponent<0，无效的输入，2、指数为正，3、指数为负,4、指数为0四种情况即可
    public double Power(double base, int exponent) {
        double re = 1;
        if (exponent == 0) {
            return 1;
        }
        for (int i = Math.abs(exponent); i != 0; i = (i >> 1)) {
            if ((i & 1) == 1) {
                re *= base;
            }
            base *= base;// 翻倍
        }
        if (exponent < 0) {
            if (base == 0) {
                return 0;
            }
            return 1 / re;
        }
        return re;
    }
}
