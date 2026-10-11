package easy;

/**
 * @author: kanedai
 * @date: 2026/9/10
 * 贪心算法
 * 局部最优解法
 */
public class _860_change {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                } else {
                    five--;
                    ten++;
                }
            } else {
                if (five > 0 && ten > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
       int [] bills = {5,5,5,20};
       System.out.println(new _860_change().lemonadeChange(bills));

    }

}