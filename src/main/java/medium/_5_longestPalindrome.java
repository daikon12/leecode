package medium;

/**
 * Created by daikai on 2020/5/5.
 */
public class _5_longestPalindrome {
    public static void main(String [] args){
        String raw = "abcba";
        System.out.println(longestPalindromeDP(raw));
    }

    // 中心扩展算法，确定一个中心，然后向左右两侧延伸，一共确定 2n-1 个中心
    // 时间：n*n 空间：1
    public static String longestPalindrome(String s) {
        // 此处要注意边界条件 “”
        if (s == null || s.length() < 1) return "";
        int start = 0;
        int end = 0;

        for(int center = 0; center < s.length(); center++){
            // abc
            int len1 = lengthAroundCenter(s, center, center);
            //abba
            int len2 = lengthAroundCenter(s, center, center+1);
            int lenMax = Math.max(len1, len2);

            if(lenMax > end - start +1){
                start = center - (lenMax -1)/2;
                end = center + lenMax/2;
            }

        }

        return  s.substring(start, end + 1);
    }

    public static int lengthAroundCenter(String s, int left, int right){
        int L = left;
        int R = right;

        while(L>=0 && R<s.length() && s.charAt(L) == s.charAt(R)){
            L--;
            R++;
        }

        // 最后R-L会比实际长度多2
        return R - L - 1;
    }

    // 用动态规划思想解决
    //P(i,j)=(P(i+1,j−1)&&S[i]==S[j])
    // 时间 ：n*n 空间 ：n*n
    public static String longestPalindromeDP(String s){

        String res = "";
        int length = s.length();
        boolean [][] dp = new boolean[length][length];
        initDP(dp, s);
        for(int row = length - 1; row >= 0 ; row--){
            for(int col = row + 2; col < length; col++){
                dp[row][col] = dp[row+1][col-1] && s.charAt(row)==s.charAt(col);
            }
        }

        for(int i = 0; i < length; i++){
            for(int j = i; j < length; j++){
                if(dp[i][j] && (j - i + 1 > res.length())){
                    res = s.substring(i, j+1);
                }

            }
        }

        return  res;
    }

    private static void initDP(boolean[][] dp, String s) {
        // size = 1
        int length = dp.length;
        for(int i = 0; i<length; i++){
            dp[i][i] = true;

            // size = 2
            if(i<length - 1 && s.charAt(i) == s.charAt(i+1)){
                dp[i][i+1] = true;
            }
            if (i<length - 1 && s.charAt(i) != s.charAt(i+1)){
                dp[i][i+1] = false;
            }
        }
    }


    public String longestPalindromeDP1(String s) {
        int n = s.length();
        String res = "";
        boolean[][] dp = new boolean[n][n];
        // 优化后，一边赋值可以一边出结果
        // 按照公式P(i,j)=(P(i+1,j−1)&&S[i]==S[j])，需要先算出左下角的数据，所以是先--，再++
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]); //j - i 代表长度减去 1
                if (dp[i][j] &&  j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

}
