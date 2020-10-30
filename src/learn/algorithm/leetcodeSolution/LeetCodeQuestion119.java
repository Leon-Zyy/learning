package learn.algorithm.leetcodeSolution;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @Description leetCode 912. 排序数组
 * @Author Leon.Zhao
 * @Date 2020/3/23 10:48
 * @Version 1.0
 */
public class LeetCodeQuestion119 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入杨辉三角的行数：");
        int n = sc.nextInt();

        int[][] triangle = new int[n][];
        for (int i = 0; i < triangle.length; i++) {
            triangle[i] = new int[i + 1];

            for (int j = 0; j < triangle[i].length; j++) {
                if (i == 0 || j == 0 || i == j) {
                    triangle[i][j] = 1;
                } else {
                    triangle[i][j] = triangle[i - 1][j] + triangle[i - 1][j - 1];
                }
                System.out.print(triangle[i][j] + " ");
            }
            System.out.println();
        }

    }

}
