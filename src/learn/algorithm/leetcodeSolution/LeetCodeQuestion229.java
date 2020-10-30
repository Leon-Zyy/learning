package learn.algorithm.leetcodeSolution;

import java.util.*;

/**
 * @Description leetCode问题
 * @Author Leon.Zhao
 * @Date 2020/3/10 16:25
 * @Version 1.0
 */
public class LeetCodeQuestion229 {

    /**
     * leetCode. 229 求众数
     * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/3 ⌋ 的元素。
     * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
     * 示例 1:
     * 输入: [3,2,3]
     * 输出: 3
     * 示例 2:
     * 输入: [1,1,1,3,3,2,2,2]
     * 输出: 1,2
     * <p>
     * <p>
     * 做这个题之前可以先看一下169题，投票算法，169题，找一个众数，那就只要一个候选，他是保证一定有一个是众数的，直接投票就好
     * 但是这个题没有保证有一个元素一定出现 n/3以上。
     * <p>
     * 首先我们得明确，n/k的众数最多只有k-1个，为什么呢？假设有k个众数，n/k * k=n, 这k个元素都是众数，还要不同，怎么可能啊。
     * 那么对于这个题，超过n/3的数最多只能有3-1 = 2 个，我们可以先选出两个候选人A,B。 遍历数组，分三种情况：
     * <p>
     * 候选1：> n/3
     * 候选2：> n/3
     * 其他 ：< n/3
     * 写代码三步走
     * 1、如果投A（当前元素等于A），则A的票数++;
     * 2、如果投B（当前元素等于B），B的票数++；
     * 3、如果A,B都不投（即当前与A，B都不相等）,那么检查此时A或B的票数是否减为0，如果为0,则当前元素成为新的候选人；
     * 如果A,B两个人的票数都不为0，那么A,B两个候选人的票数均减一。
     * <p>
     * 最后会有这么几种可能：有2个大于 n/3，有1个大于 n/3，有0个大于 n/3
     * 遍历结束后选出了两个候选人，但是这两个候选人是否满足 > n/3，还需要再遍历一遍数组，
     * 找出两个候选人的具体票数，因为题目没有像169题保证一定有。
     *
     * @param nums
     * @return
     */
    public static List<Integer> majorityElement(int[] nums) {
        int candidate1 = 0, count1 = 0;
        int candidate2 = 0, count2 = 0;

        for (int num : nums) {
            if (candidate1 == num) {
                count1++;
                continue;
            }

            if (candidate2 == num) {
                count2++;
                continue;
            }

            if (candidate1 == 0) {
                candidate1 = num;
                count1++;
                continue;
            }

            if (candidate2 == 0) {
                candidate2 = num;
                count2++;
                continue;
            }

            count1--;
            count2--;
        }
        count1 = count2 = 0;

        List<Integer> result = new ArrayList<>();
        for (int num : nums) {
            if (num == candidate1) {
                count1++;
            } else if (num == candidate2) {
                count2++;
            }
        }

        int n = nums.length;
        if (count1 > n / 3) {
            result.add(candidate1);
        }
        if (count2 > n / 3) {
            result.add(candidate2);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 1, 1};
        List<Integer> result = majorityElement(nums);
        System.out.println(result);
    }
}
