package medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by daikai on 2020/4/1.
 */
public class _3_lengthOfLongestSubstring {
    public static void main(String[] args) {

        String test = "abba";
        System.out.println(lengthOfLongestSubstring1(test));
    }


    // O(N*N)
    public static int lengthOfLongestSubstring(String s) {

        // 考虑 null 的情况
        if(s == null || s.length() == 0){
            return 0;
        }
        //考虑含空格字符串的情况

        int res =0;
        int length = s.length();
        for(int i =0 ; i < length ; i++){
            Map<Character, String> map = new HashMap<>();
            for(int j = i ; j<length ; j++){
                if(!map.containsKey(s.charAt(j))){
                    map.put(s.charAt(j), null);
                } else {
                    // 这个break很重要，不符合直接跳出当此循环,且res不可再此赋值，会忽略最后是有效字符情况
                    //res = Math.max(res, map.size());
                    break;
                }
            }
            // 应该在此赋值
            res = Math.max(res, map.size());
        }

        return res;
    }

    // O(2*N)
    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                // i 调整为 i+1
                // 一次减少一个，直到变成 j'
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

// O(N)
    public  static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        // 我们可以定义字符到索引的映射，而不是使用集合来判断一个字符是否存在。
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // 如果 s[j] 在 [i, j) 范围内有与 s[j]重复的字符 s[j']，我们不需要逐渐增加 i ,
        // 我们可以直接跳过 [i，j'] 范围内的所有元素，并将 i变为 j' + 1
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                // i 变化为 j
                // 为啥是max
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    // O(N)
    public  static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        // 我们可以定义字符到索引的映射，对应str下标。
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // 如果 s[j] 在 [i, j) 范围内有与 s[j]重复的字符 s[j']，我们不需要逐渐增加 i ,
        // 我们可以直接跳过 [i，j'] 范围内的所有元素，
        // 并将 i变为 j' + 1，但是需要考虑滑动区间i变化，所以是max (“abba”最后a时)
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                // i 变化为 (j'+ 1)
                i = Math.max(map.get(s.charAt(j)) + 1, i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j);
        }
        return ans;
    }


}
