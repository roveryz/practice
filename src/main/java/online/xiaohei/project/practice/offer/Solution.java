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