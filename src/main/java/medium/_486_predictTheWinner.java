package medium;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;

/**
 * @author: kanedai
 * @date: 2026/10/11
 * 给你一个整数数组 nums。
 *
 * 玩家 1 和玩家 2 基于这个数组设计了一个游戏。
 *
 * 玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。
 *
 * 每一回合，玩家从数组的 任意一端 取一个数字（即，nums[0] 或 nums[nums.length - 1]），取到的数字将会从数组中 移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。
 *
 * 如果玩家 1 能成为赢家，返回 true 。
 *
 * 注意，如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,2]
 * 输出：false
 * 解释：初始分数是 0 。玩家 1 取 1 ，得分变成 1 。玩家 2 取 2 ，得分变成 2 。玩家 1 取 5 ，得分变成 6 。玩家 2 取 5 ，得分变成 7 。玩家 1 取 2 ，得分变成 8 。玩家 2 取 1 ，得分变成 9 ，游戏结束。1 + 2 = 3 ，3 + 7 = 10 ，玩家 2 得分更高，所以玩家 1 不是赢家且返回 false 。
 */
public class _486_predictTheWinner {

    public boolean predictTheWinner(int[] nums) {
        int score = maxScore(nums, 0, nums.length - 1);
        return score >= Arrays.stream(nums).sum() - score;
    }

    /**
     * 关键转化：玩家 1 当前能拿到的最大"得分差" = 自己选的数 − 对手在剩余区间里能拿到的得分差。这就是递归式 max(nums[i] - f(i+1,j), nums[j] - f(i,j-1)) 的来源。
     * 朴素递归
     * | 复杂度 | 每个分支递归两次，指数级 O(2ⁿ)，且存在大量重复子问题
     * 时间复杂度：O(n²) 空间复杂度：O(n)
     * 递归分支数 4
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int maxScore(int[] nums, int l, int r) {
        if (l == r) return nums[l];
        int leftScore = 0, rightScore = 0;
        if(r - l == 1) {
            leftScore = nums[l];
            rightScore = nums[r];
        } else {
            // 这里存在大量重复计算
            leftScore = nums[l] + Math.min(maxScore(nums, l + 1, r - 1), maxScore(nums, l + 2, r));
            rightScore = nums[r] + Math.min(maxScore(nums, l, r - 2), maxScore(nums, l + 1, r - 1));
        }
        return Math.max(leftScore, rightScore);
    }

    /**
     * 差值递归
     * 优化 较少了递归次数
     * 并且还能知道最后玩家1最终能赢多少分
     * 直接维护"当前先手玩家相对于对手的得分差"。选左端则自己 +nums[l]，对手在 [l+1,r] 成为先手，其相对差就是玩家 1 接下来要减去的部分。
     * 减少了递归次数，但仍是指数 O(2ⁿ)
     * 时间复杂度：O(n²) 空间复杂度：O(n)
     * 递归分支数 2
     *
     * @param nums
     * @param l
     * @param r
     * @return
     */
    private int maxScore_1(int[] nums, int l, int r) {
        if (l == r) return nums[l];
        int chazhiL = nums[l] - maxScore_1(nums, l +1, r);
        int chazhiR = nums[r] - maxScore_1(nums, l, r - 1);
        return Math.max(chazhiL, chazhiR);
    }

    /**
     * 用动态规划的方式来实现
     * 把实现二的递归改成自底向上填表。dp[i][j] = 在区间 [i,j] 先手玩家的分差。依赖关系 dp[i][j] 只用 dp[i+1][j] 和 dp[i][j-1]（都在其左下方），所以逆序 i、顺序 j 即可正确填表
     * @param nums
     * @return
     * 时间复杂度：O(n²) 空间复杂度：O(n²)
     */
    private boolean maxScore_dp(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }

        return dp[0][n - 1] >= 0;
    }




    public static void main(String[] args) {
        _486_predictTheWinner solution = new _486_predictTheWinner();
        System.out.println(solution.predictTheWinner(new int[]{1, 5, 2}));
        System.out.println(solution.predictTheWinner(new int[]{1, 5, 233, 7}));
        System.out.println(solution.maxScore_dp(new int[]{1, 5, 2}));
        System.out.println(solution.maxScore_dp(new int[]{1, 5, 233, 7}));
    }

}