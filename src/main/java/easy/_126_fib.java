package easy;

/**
 * @author: kanedai
 * @date: 2026/7/14
 * 斐波那契数 （通常用 F(n) 表示）形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和
 * 答案需要取模 1e9+7(1000000007) ，如计算初始结果为：1000000008，请返回 1。
 */
public class _126_fib {
    public static void main(String[] args) {
        System.out.println(new _126_fib().fib(45));
        System.out.println(new _126_fib().fib_1(45));
        System.out.println(new _126_fib().fib_2(45));

    }
    // 取模值：1e9+7
    private static final int MOD = 1000000007;

    // 递归版：加记忆化，避免 n 大时重复计算导致的极慢/栈溢出
    private int[] memo;
    public int fib(int n) {
        memo = new int[n + 1];
        return dfs(n);
    }
    private int dfs(int n) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        // 先减一个 MOD 再相加，防止中间结果溢出 int
        memo[n] = (dfs(n - 1) + dfs(n - 2)) % MOD;
        return memo[n];
    }

    // 当n=45时 取模后返回 134903163
    public int fib_1(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 取模：两者都小于 MOD，相加最多 2*MOD-2，先减一个 MOD 再 % 保证不溢出
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
        return dp[n];
    }

    public int fib_2(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = (a + b) % MOD;
            a = b;
            b = temp;
        }
        return b;
    }
}