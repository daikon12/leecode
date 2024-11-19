package easy;

/**
 * @author: kanedai
 * @date: 2024/11/20
 */
public class _4_prime {

    public static void main(String[] args) {
        int n = 100;
        int count = eratosthenes(n);
        System.out.println(count);

        int countBf = bf(n);
        System.out.println(countBf);
    }

    /**
     * 暴力求素数个数
     * @param n
     * @return
     */
    private static int bf(int n) {
        int count = 0;
        for (int i = 2; i <= n; i++) {
            count += isPrime(i) ? 1 : 0;
        }
        return count;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 埃式筛选法, 计算n以内的素数个数
     *
     * @param n
     * @return
     */
    private static int eratosthenes(int n) {
        // todo 5 有问题
        // 默认值false, 是素数
        boolean[] isPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                count++;
                // 优化 int j = i * i
                for (int j = i * 2; j < n; j += i) {
                    isPrime[j] = true;
                }
            }
        }
        return count;
    }
}