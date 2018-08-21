package online.xiaohei.project.practice.offer;

import org.bukkit.material.Tree;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


public class Solution {
    public static void main(String[] args) {
        int[][] m = {{1, 2, 3}, {1, 2, 3}};
        printMatrix(m);
    }

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        int row = matrix.length;
        if (row == 0) {
            return list;
        }
        int col = matrix[0].length;
        int rowStart = 0, colStart = 0, i = 0, j = 0;
        while (true) {
            if (rowStart > row / 2) rowStart--;
            if (colStart > col / 2) colStart--;
            i = rowStart;
            j = colStart;
            // ---->
            for (i = rowStart, j = colStart; j <= col - 1 - colStart; j++) {
                list.add(matrix[i][j]);
            }
            // |
            // V
            for (j = col - 1 - colStart; i <= row - 1 - rowStart; i++) {
                if (i > rowStart && i <= row - 1 - rowStart) {
                    list.add(matrix[i][j]);
                }
            }
            // <---
            for (i = row - 1 - rowStart; j >= colStart; j--) {
                if (j >= colStart && j < col - 1 - colStart) {
                    list.add(matrix[i][j]);
                }
            }
            // ^
            // |
            for (j = colStart; i > rowStart; i--) {
                if (i > rowStart && i < row - 1 - rowStart) {
                    list.add(matrix[i][j]);
                }
            }
            rowStart++;
            colStart++;
            if (list.size() == (row + 1) * (col + 1)) {
                break;
            }
        }
        return list;
    }

    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (str.length == 0) {
            return false;
        }
        boolean[] f = new boolean[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            f[i] = false;
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i * cols + j] == str[0]) {
                    f[i * cols + j] = true;
                    if (helper(matrix, f, rows, cols, i, j, str, 0)) {
                        return true;
                    }
                    f[i * cols + j] = false;
                }
            }
        }
        return false;
    }

    public static boolean helper(char[] matrix, boolean[] f, int rows, int cols, int i, int j, char[] str, int strIndex) {//strIndex is preOne
        // current char is strIndex+1
        if (strIndex + 1 >= str.length) {
            return true;
        }
        // up
        if (i - 1 >= 0 && !f[(i - 1) * cols + j] && matrix[(i - 1) * cols + j] == str[strIndex + 1]) {
            f[(i - 1) * cols + j] = true;
            if (!helper(matrix, f, rows, cols, i - 1, j, str, strIndex + 1)) {
                f[(i - 1) * cols + j] = false;
            } else {
                return true;
            }

        }
        // down
        if (i + 1 < rows && !f[(i + 1) * cols + j] && matrix[(i + 1) * cols + j] == str[strIndex + 1]) {

            f[(i + 1) * cols + j] = true;
            if (!helper(matrix, f, rows, cols, i + 1, j, str, strIndex + 1)) {
                f[(i + 1) * cols + j] = false;
            } else {
                return true;
            }

        }
        // left
        if (j - 1 >= 0 && !f[i * cols + j - 1] && matrix[i * cols + j - 1] == str[strIndex + 1]) {
            f[i * cols + j - 1] = true;
            if (!helper(matrix, f, rows, cols, i, j - 1, str, strIndex + 1)) {
                f[i * cols + j - 1] = false;
            } else {
                return true;
            }
        }
        // right
        if (j + 1 < cols && !f[i * cols + j + 1] && matrix[i * cols + j + 1] == str[strIndex + 1]) {
            f[i * cols + j + 1] = true;
            if (!helper(matrix, f, rows, cols, i, j + 1, str, strIndex + 1)) {
                f[i * cols + j + 1] = false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean HasSubtree(TreeNode A, TreeNode B) {
        // if B is A's child tree, return true.
        if (B == null || A == null) {
            return false;
        }
        return helper(A, B);
    }

    public static boolean helper(TreeNode A, TreeNode B) {
        if (A != null && B != null) {
            if (A.val == B.val && helper(A.left, B.left) && helper(A.right, B.right)) {
                return true;
            } else {
                if (helper(A.left, B))
                    return true;
                if (helper(A.right, B))
                    return true;
            }
        } else if (A == null && B != null) {
            return false;
        } else {
            return true;
        }
        return false;
    }


    public static ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (k > input.length) {
            return list;
        }
        for (int i = k / 2 - 1; i >= 0; i--) {
            // 大根堆
            adjustHeap(input, i, k - 1);
        }
        for (int i = k; i < input.length; i++) {
            if (input[i] < input[0]) {
                input[0] = input[i];
                adjustHeap(input, 0, k - 1);
            }
        }

        for (int i = k - 1; i >= 0; i--) {
            list.add(input[i]);
        }
        return list;
    }

    public static void adjustHeap(int[] heap, int low, int high) {
        int tmp = heap[low];
        for (int i = low * 2; i <= high; i *= 2) {
            if (i < high && heap[i + 1] > heap[i]) {
                i++;
            }
            if (tmp >= heap[i]) {
                break;
            }
            heap[low] = heap[i];
            low = i;
        }
        heap[low] = tmp;
    }

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

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        ArrayList<Integer> list = new ArrayList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            list.add(current.val);
            if (current.left != null) {
                q.offer(current.left);
            }
            if (current.right != null) {
                q.offer(current.right);
            }
        }
        return list;
    }

    @Test
    public void test(){
        int[] a = {7,4,6,5};
        System.out.println(VerifySquenceOfBST(a));
    }

    public boolean VerifySquenceOfBST(int [] sequence) {
        return helper(sequence, 0, sequence.length-1);
    }

    public boolean helper(int[] a, int start, int end){
        int root = a[end];
        if(start == end){
            return true;
        }
        int rightChildFirstIndex = end;
        for(int i = start; i < end; i++){
            if(a[i]>root){
                rightChildFirstIndex = i;
                break;
            }
        }
        if(rightChildFirstIndex > start){
            // have left child
            for(int i = start; i <= rightChildFirstIndex-1;i++){
                if(root<a[i]){
                    return false;
                }
            }
            if(!helper(a, start, rightChildFirstIndex - 1)){
                return false;
            }
        }
        if(rightChildFirstIndex < end){
            for(int i = rightChildFirstIndex; i < end;i++){
                if(root>a[i]){
                    return false;
                }
            }
            if(!helper(a, rightChildFirstIndex, end-1)){
                return false;
            }
        }
        return true;
    }

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        helper(root, target, 0, lists, list);
        Collections.sort(lists, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                return o2.size()-o1.size();
            }
        });
        return lists;
    }

    public void helper(TreeNode current, int target, int cValue, ArrayList<ArrayList<Integer>> lists, ArrayList<Integer> list){
        cValue += current.val;
        if(cValue<=target) {
            list.add(current.val);
            if (cValue == target && current.left==null && current.right==null) {
                ArrayList<Integer> dest = new ArrayList<Integer>();
                for (int i : list) {
                    dest.add(i);
                }
                lists.add(dest);
            }
            if (current.left != null) {
                helper(current.left, target, cValue, lists, list);
            }
            if (current.right != null) {
                helper(current.right, target, cValue, lists, list);
            }
            Integer obj = current.val;
            list.remove(obj);
        }
    }


    public RandomListNode Clone(RandomListNode pHead)
    {
        RandomListNode head, p=pHead, q;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        head =  new RandomListNode(p.label);
        q=head;
        p=p.next;
        while(p != null ){
            q.next = new RandomListNode(p.label);
            map.put(p,q);
            q=q.next;
            p=p.next;
        }
        p=pHead;
        q=head;
        while(p!=null){
            q.random = map.get(p.random);
            p=p.next;
            q=q.next;
        }
        return head;
    }

    /**
     * 二叉搜索树与双向链表
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * @param pRootOfTree
     * @return
     * root的左指针要改成左子树的最右最后节点
     */
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree==null){
            return null;
        }
        //===deal nodes
        TreeNode leftTmp, rightTmp, head;
        if(pRootOfTree.left!=null){
            leftTmp=Convert(pRootOfTree.left);
            head = leftTmp;
            while(leftTmp.right!=null){
                leftTmp=leftTmp.right;
            }
            leftTmp.right=pRootOfTree;
            pRootOfTree.left=leftTmp;
        }else{
            head = pRootOfTree;
        }
        if(pRootOfTree.right!=null){
            rightTmp=Convert(pRootOfTree.right);
            pRootOfTree.right=rightTmp;
            rightTmp.left=pRootOfTree;
        }
        return head;
    }

    public boolean IsPopOrder(int [] pushA,int [] popA) {
        ArrayList<Integer> list = new ArrayList<>();
        int pos = 0;
        for(int i : pushA){
            if(i==popA[pos]){
                pos++;
                while(!list.isEmpty() && list.get(list.size()-1)==popA[pos]){
                    pos++;
                    list.remove(list.size()-1);
                }
            }else{
                list.add(i);
            }
        }
        if(list.size()==0 && pos==popA.length){
            return true;
        }else{
            return false;
        }
    }

    @Test
    public void testPermutation(){
        Permutation("ab");
    }

    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if(str.length()==0) return list;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < str.length(); i++){
            helper(set, 0, swap(0, i, str));
        }
        list.addAll(set);
        Collections.sort(list);
        return list;
    }

    public void helper(Set<String> set, int k, String str){// to kth has fixed
        if(k==str.length()-1){
            set.add(str);
        }else{
            for(int i = k+1; i<str.length(); i++){
                helper(set, k+1, swap(k+1, i, str));
            }
        }
    }

    public String swap(int i, int j, String str){
        char[] arr = str.toCharArray();
        char tmp = arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
        return new String(arr);
    }

    public int MoreThanHalfNum_Solution(int [] array) {
        if(array.length==0) return 0;
        int index  = partition(array, 0, array.length-1);
        int middle = array.length>>1;
        while(index!=middle){
            if(index>middle)
                index = partition(array, 0, index-1);
            else
                index = partition(array, index+1, array.length-1);
        }
        int result = array[index], times = 0;
        for(int i : array){
            if(i==result) times++;
        }
        return (times>middle)?result:0;
    }

    public int partition(int[] a, int start, int end){
        int pivot = a[start], pStart = start, pEnd = end;
        while(pStart<pEnd){
            while(pStart<pEnd && a[pEnd]>=pivot) pEnd--;
            a[pStart]=a[pEnd];
            while(pStart<pEnd && a[pStart]<=pivot) pStart++;
            a[pEnd]=a[pStart];
        }
        a[pStart]=pivot;
        return pStart;
    }

    /**
     * 连续子数组的最大和  包含负数
     * 
     * 链接：https://www.nowcoder.com/questionTerminal/459bd355da1549fa8a49e350bf3df484
     来源：牛客网

     使用动态规划
     F（i）：以array[i]为末尾元素的子数组的和的最大值，子数组的元素的相对位置不变
     F（i）=max（F（i-1）+array[i] ， array[i]）
     res：所有子数组的和的最大值
     res=max（res，F（i））

     如数组[6, -3, -2, 7, -15, 1, 2, 2]
     初始状态：
     F（0）=6
     res=6
     i=1：
     F（1）=max（F（0）-3，-3）=max（6-3，3）=3
     res=max（F（1），res）=max（3，6）=6
     i=2：
     F（2）=max（F（1）-2，-2）=max（3-2，-2）=1
     res=max（F（2），res）=max（1，6）=6
     i=3：
     F（3）=max（F（2）+7，7）=max（1+7，7）=8
     res=max（F（2），res）=max（8，6）=8
     i=4：
     F（4）=max（F（3）-15，-15）=max（8-15，-15）=-7
     res=max（F（4），res）=max（-7，8）=8
     以此类推
     最终res的值为8

     public  int FindGreatestSumOfSubArray(int[] array) {
     int res = array[0]; //记录当前所有子数组的和的最大值
     int max=array[0];   //包含array[i]的连续数组最大值
     for (int i = 1; i < array.length; i++) {
     max=Math.max(max+array[i], array[i]);
     res=Math.max(max, res);
     }
     return res;
     }

     */
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}


class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}