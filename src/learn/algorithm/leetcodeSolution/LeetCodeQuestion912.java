package learn.algorithm.leetcodeSolution;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description leetCode 912. 排序数组
 * @Author Leon.Zhao
 * @Date 2020/3/23 10:48
 * @Version 1.0
 */
public class LeetCodeQuestion912 {

    private static final int INSERTION_SORT_THRESHOLD = 7;

    private static final Random RANDOM = new Random();

    private static final int OFFSET = 50000;

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1, 9, 6, 4, 10, 3};
        LeetCodeQuestion912 sort = new LeetCodeQuestion912();
        int[] result = sort.selectSortArray(nums);
        System.out.println(Arrays.toString(result));
    }

    /**
     * 排序数组
     * 1. 选择排序
     * 算法思想 1：贪心算法：每一次决策只看当前，当前最优，则全局最优。注意：这种思想不是任何时候都适用。
     * <p>
     * 算法思想 2：减治思想：外层循环每一次都能排定一个元素，问题的规模逐渐减少，
     * 直到全部解决，即「大而化小，小而化了」。运用「减治思想」很典型的算法就是大名鼎鼎的「二分查找」。
     * <p>
     * 优点：交换次数最少
     * 步骤：每次循环找出最小的，放到开头位置
     */
    public int[] selectSortArray(int[] nums) {
        int len = nums.length;

        // 循环不变量：[0, i) 有序，且该区间里所有元素就是最终排定的样子
        for (int i = 0; i < len - 1; i++) {

            // 选择区间 [i, len - 1] 里最小的元素的索引，交换到下标 i
            int minIndex = i;

            for (int j = i + 1; j < len; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
        return nums;
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 排序数组
     * 2. 插入排序
     * 每次将一个数字插入一个有序的数组里，成为一个长度更长的有序数组，有限次操作以后，数组整体有序
     */
    public int[] insertSortArray(int[] nums) {
        int len = nums.length;

        // 循环不变量：将 nums[i] 插入到区间 [0, i) 使之成为有序数组
        for (int i = 1; i < len; i++) {
            // 先暂存这个元素，然后之前元素逐个后移，留出空位
            int temp = nums[i];
            int j = i;
            // 注意边界 j > 0
            while (j > 0 && nums[j - 1] > temp) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }

    /**
     * Determine alternation base for merge
     * 3. 归并排序
     *
     * @param nums
     * @return
     */
    public int[] mergeSortArray(int[] nums) {
        int len = nums.length;
        int[] temp = new int[len];
        mergeSort(nums, 0, len - 1, temp);
        return nums;
    }

    /**
     * 对数组 nums 的子区间 [left, right] 进行归并排序
     * <p>
     * 基本思路：借助额外空间，合并两个有序数组，得到更长的有序数组。例如：「力扣」第 88 题：合并两个有序数组。
     * 算法思想：分而治之（分治思想）。「分而治之」思想的形象理解是「曹冲称象」、MapReduce，在一定情况下可以并行化
     *
     * @param nums
     * @param left
     * @param right
     * @param temp  用于合并两个有序数组的辅助数组，全局使用一份，避免多次创建和销毁
     */
    private void mergeSort(int[] nums, int left, int right, int[] temp) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        int mid = left + (right - left) / 2;
        // Java 里有更优的写法，在 left 和 right 都是大整数时，即使溢出，结论依然正确
        // int mid = (left + right) >>> 1;

        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        // 如果数组的这个子区间本身有序，无需合并
        if (nums[mid] <= nums[mid + 1]) {
            return;
        }
        mergeOfTwoSortedArray(nums, left, mid, right, temp);
    }

    /**
     * 对数组 arr 的子区间 [left, right] 使用插入排序
     *
     * @param arr   给定数组
     * @param left  左边界，能取到
     * @param right 右边界，能取到
     */
    private void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i;
            while (j > left && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    /**
     * 合并两个有序数组：先把值复制到临时数组，再合并回去
     *
     * @param nums
     * @param left
     * @param mid   [left, mid] 有序，[mid + 1, right] 有序
     * @param right
     * @param temp  全局使用的临时数组
     */
    private void mergeOfTwoSortedArray(int[] nums, int left, int mid, int right, int[] temp) {
        System.arraycopy(nums, left, temp, left, right + 1 - left);

        int i = left;
        int j = mid + 1;

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                nums[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                nums[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                // 注意写成 < 就丢失了稳定性（相同元素原来靠前的排序以后依然靠前）
                nums[k] = temp[i];
                i++;
            } else {
                // temp[i] > temp[j]
                nums[k] = temp[j];
                j++;
            }
        }
    }

    /**
     * 4. 快速排序
     * 分治思想进行排序
     *
     * @param nums
     * @return
     */
    public int[] quickSortArray(int[] nums) {
        int len = nums.length;
        quickSort(nums, 0, len - 1);
        return nums;
    }

    /**
     * 快速排序
     * 基本思路：快速排序每一次都排定一个元素（这个元素呆在了它最终应该呆的位置），
     * 然后递归地去排它左边的部分和右边的部分，依次进行下去，直到数组有序；
     * <p>
     * 算法思想：分而治之（分治思想），与「归并排序」不同，「快速排序」在「分」这件事情上不想「归并排序」无脑地一分为二，
     * 而是采用了 partition 的方法（书上，和网上都有介绍，就不展开了），因此就没有「合」的过程。
     * <p>
     * 实现细节（注意事项）：（针对特殊测试用例：顺序数组或者逆序数组）一定要随机化选择切分元素（pivot）
     * ，否则在输入数组是有序数组或者是逆序数组的时候，快速排序会变得非常慢（等同于冒泡排序或者「选择排序」）；
     *
     * @param nums
     * @param left
     * @param right
     */
    private void quickSort(int[] nums, int left, int right) {
        // 小区间使用插入排序
        if (right - left <= INSERTION_SORT_THRESHOLD) {
            insertionSort(nums, left, right);
            return;
        }

        int pIndex = partition(nums, left, right);
        quickSort(nums, left, pIndex - 1);
        quickSort(nums, pIndex + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        int randomIndex = RANDOM.nextInt(right - left + 1) + left;
        swap(nums, left, randomIndex);

        // 基准值
        int pivot = nums[left];
        int lt = left;
        // 循环不变量：
        // all in [left + 1, lt] < pivot
        // all in [lt + 1, i) >= pivot
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                lt++;
                swap(nums, i, lt);
            }
        }
        swap(nums, left, lt);
        return lt;
    }

    /**
     * 将数组整理成堆（堆有序）
     * 5.堆排序
     *
     * @param nums
     */
    public int[] heapSortArray(int[] nums) {
        int len = nums.length;
        // 将数组整理成堆
        heapify(nums);

        // 循环不变量：区间 [0, i] 堆有序
        for (int i = len - 1; i >= 1; ) {
            // 把堆顶元素（当前最大）交换到数组末尾
            swap(nums, 0, i);
            // 逐步减少堆有序的部分
            i--;
            // 下标 0 位置下沉操作，使得区间 [0, i] 堆有序
            siftDown(nums, 0, i);
        }
        return nums;
    }

    /**
     * 将数组整理成堆（堆有序）
     * 堆排序是选择排序的优化，选择排序需要在未排定的部分里通过「打擂台」的方式选出最大的元素（复杂度
     * O(N)O(N)），而「堆排序」就把未排定的部分构建成一个「堆」，这样就能以O(logN)O(logN) 的方式选出最大元素；
     * 堆是一种相当有意思的数据结构，它在很多语言里也被命名为「优先队列」。
     * 它是建立在数组上的「树」结构，类似的数据结构还有「并查集」「线段树」等
     * <p>
     * 「优先队列」是一种特殊的队列，按照优先级顺序出队，从这一点上说，与「普通队列」无差别。
     * 「优先队列」可以用数组实现，也可以用有序数组实现，但只要是线性结构，复杂度就会高，
     * 因此，「树」结构就有优势，「优先队列」的最好实现就是「堆」
     *
     * @param nums
     */
    private void heapify(int[] nums) {
        int len = nums.length;
        // 只需要从 i = (len - 1) / 2 这个位置开始逐层下移
        for (int i = (len - 1) / 2; i >= 0; i--) {
            siftDown(nums, i, len - 1);
        }
    }

    /**
     * @param nums
     * @param k    当前下沉元素的下标
     * @param end  [0, end] 是 nums 的有效部分
     */
    private void siftDown(int[] nums, int k, int end) {
        while (2 * k + 1 <= end) {
            int j = 2 * k + 1;
            if (j + 1 <= end && nums[j + 1] > nums[j]) {
                j++;
            }
            if (nums[j] > nums[k]) {
                swap(nums, j, k);
            } else {
                break;
            }
            k = j;
        }
    }

    /**
     * 希尔排序
     * 思想来源：插入排序的优化。在插入排序里，如果靠后的数字较小，它来到前面就得交换多次。「希尔排序」改进了这种做法。带间隔地使用插入排序，直到最后「间隔」为
     * 1的时候，就是标准的「插入排序」，此时数组里的元素已经「几乎有序」了
     *
     * @param nums
     * @return
     */
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        int h = 1;

        // 使用 Knuth 增量序列
        // 找增量的最大值
        while (3 * h + 1 < len) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // insertion sort
            for (int i = h; i < len; i++) {
                insertionForDelta(nums, h, i);
            }
            h = h / 3;
        }
        return nums;
    }

    /**
     * 将 nums[i] 插入到对应分组的正确位置上，其实就是将原来 1 的部分改成 gap
     *
     * @param nums
     * @param gap
     * @param i
     */
    private void insertionForDelta(int[] nums, int gap, int i) {
        int temp = nums[i];
        int j = i;
        // 注意：这里 j >= deta 的原因
        while (j >= gap && nums[j - gap] > temp) {
            nums[j] = nums[j - gap];
            j -= gap;
        }
        nums[j] = temp;
    }

    /**
     * 冒泡
     * <p>
     * 基本思想：外层循环每一次经过两两比较，把每一轮未排定部分最大的元素放到了数组的末尾；
     * 「冒泡排序」有个特点：在遍历的过程中，提前检测到数组是有序的，
     * 从而结束排序，而不像「选择排序」那样，即使输入数据是有序的，
     * 「选择排序」依然需要「傻乎乎」地走完所有的流程
     *
     * @param nums
     * @return
     */
    public int[] sortChangeArray(int[] nums) {
        int len = nums.length;
        for (int i = len - 1; i >= 0; i--) {
            // 先默认数组是有序的，只要发生一次交换，就必须进行下一轮比较，
            // 如果在内层循环中，都没有执行一次交换操作，说明此时数组已经是升序数组
            boolean sorted = true;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
        return nums;
    }

    /**
     * 计数排序
     * 「计数排序」是这三种排序算法里最好理解的，从名字就可以看出。
     * 把每个出现的数值都做一个计数，然后根据计数从小到大输出得到有序数组。
     * <p>
     * 这种做法丢失了稳定性，如果是本题这种基本数据类型的话没有关系。如果是对象类型，就不能这么做了。
     * <p>
     * 保持稳定性的做法是：先对计数数组做前缀和，在第 2 步往回赋值的时候，
     * 根据原始输入数组的数据从后向前赋值，前缀和数组保存了每个元素存放的下标信息（这里没有说得太细，本来这一点就不重要，也不难理解）
     *
     * @param nums
     * @return
     */
    public int[] sortCountArray(int[] nums) {
        int len = nums.length;
        // 由于 -50000 <= A[i] <= 50000
        // 因此"桶" 的大小为 50000 - (-50000) = 10_0000
        // 并且设置偏移 OFFSET = 50000，目的是让每一个数都能够大于等于 0
        // 这样就可以作为 count 数组的下标，查询这个数的计数
        int size = 10_0000;

        // 计数数组
        int[] count = new int[size];
        // 计算计数数组
        for (int num : nums) {
            count[num + OFFSET]++;
        }

        // 把 count 数组变成前缀和数组
        for (int i = 1; i < size; i++) {
            count[i] += count[i - 1];
        }

        // 先把原始数组赋值到一个临时数组里，然后回写数据
        int[] temp = new int[len];
        System.arraycopy(nums, 0, temp, 0, len);

        // 为了保证稳定性，从后向前赋值
        for (int i = len - 1; i >= 0; i--) {
            int index = count[temp[i] + OFFSET] - 1;
            nums[index] = temp[i];
            count[temp[i] + OFFSET]--;
        }
        return nums;
    }

    /**
     * 基本思路：也称为基于关键字的排序，例如针对数值排序，个位、十位、百位就是关键字。
     * 针对日期数据的排序：年、月、日、时、分、秒就是关键字。
     * <p>
     * 「基数排序」用到了「计数排序」
     *
     * @param nums
     * @return
     */
    public int[] sortBaseNumArray(int[] nums) {
        int len = nums.length;

        // 预处理，让所有的数都大于等于 0，这样才可以使用基数排序
        for (int i = 0; i < len; i++) {
            nums[i] += OFFSET;
        }

        // 第 1 步：找出最大的数字
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }

        // 第 2 步：计算出最大的数字有几位，这个数值决定了我们要将整个数组看几遍
        int maxLen = getMaxLen(max);

        // 计数排序需要使用的计数数组和临时数组
        int[] count = new int[10];
        int[] temp = new int[len];

        // 表征关键字的量：除数
        // 1 表示按照个位关键字排序
        // 10 表示按照十位关键字排序
        // 100 表示按照百位关键字排序
        // 1000 表示按照千位关键字排序
        int divisor = 1;
        // 有几位数，外层循环就得执行几次
        for (int i = 0; i < maxLen; i++) {

            // 每一步都使用计数排序，保证排序结果是稳定的
            // 这一步需要额外空间保存结果集，因此把结果保存在 temp 中
            countingSort(nums, temp, divisor, len, count);

            // 交换 nums 和 temp 的引用，下一轮还是按照 nums 做计数排序
            int[] t = nums;
            nums = temp;
            temp = t;

            // divisor 自增，表示采用低位优先的基数排序
            divisor *= 10;
        }

        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = nums[i] - OFFSET;
        }
        return res;
    }

    /**
     * 排序方法
     *
     * @param nums
     * @param res
     * @param divisor
     * @param len
     * @param count
     */
    private void countingSort(int[] nums, int[] res, int divisor, int len, int[] count) {
        // 1、计算计数数组
        for (int i = 0; i < len; i++) {
            // 计算数位上的数是几，先取个位，再十位、百位
            int remainder = (nums[i] / divisor) % 10;
            count[remainder]++;
        }

        // 2、变成前缀和数组
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 3、从后向前赋值
        for (int i = len - 1; i >= 0; i--) {
            int remainder = (nums[i] / divisor) % 10;
            int index = count[remainder] - 1;
            res[index] = nums[i];
            count[remainder]--;
        }

        // 4、count 数组需要设置为 0 ，以免干扰下一次排序使用
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
    }

    /**
     * 获取一个整数的最大位数
     *
     * @param num
     * @return
     */
    private int getMaxLen(int num) {
        int maxLen = 0;
        while (num > 0) {
            num /= 10;
            maxLen++;
        }
        return maxLen;
    }

    /**
     * 桶排序
     * 基本思路：一个坑一个萝卜，也可以一个坑多个萝卜，对每个坑排序，再拿出来，整体就有序
     *
     * @param nums
     * @return
     */
    public int[] sortTableArray(int[] nums) {
        int len = nums.length;
        // 第 1 步：将数据转换为 [0, 10_0000] 区间里的数
        for (int i = 0; i < len; i++) {
            nums[i] += OFFSET;
        }

        // 第 2 步：观察数据，设置桶的个数
        // 步长：步长如果设置成 10 会超出内存限制
        int step = 1000;
        // 桶的个数
        int bucketLen = 10_0000 / step;

        int[][] temp = new int[bucketLen + 1][len];
        int[] next = new int[bucketLen + 1];

        // 第 3 步：分桶
        for (int num : nums) {
            int bucketIndex = num / step;
            temp[bucketIndex][next[bucketIndex]] = num;
            next[bucketIndex]++;
        }

        // 第 4 步：对于每个桶执行插入排序
        for (int i = 0; i < bucketLen + 1; i++) {
            insertionSort(temp[i], next[i] - 1);
        }

        // 第 5 步：从桶里依次取出来
        int[] res = new int[len];
        int index = 0;
        for (int i = 0; i < bucketLen + 1; i++) {
            int curLen = next[i];
            for (int j = 0; j < curLen; j++) {
                res[index] = temp[i][j] - OFFSET;
                index++;
            }
        }
        return res;
    }

    private void insertionSort(int[] arr, int endIndex) {
        for (int i = 1; i <= endIndex; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] > temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
}
