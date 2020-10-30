package learn.algorithm.leetcodeSolution;

public class test {

    public static boolean isPalindrome (int x) {
        //分别比较x的左边和右边的数字：不同，返回false；相同，则去掉左右两边的数字继续比较
        if (x < 0) {
            return false;
        }
        int div = 1;
        while (x / div >= 10) {//如x=1221，则div=1000
            div *= 10;
        }
        while (x > 0) {
            int left = x / div; //取左边的数字
            int right = x % 10;//取右边的数字
            if (left != right) {
                return false;
            }
            x = x % div / 10; //去掉左边和右边的数字，继续比较
            div /= 100; //如x去掉左右两边的数字后，x=22，div=10
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(484));
    }
}
