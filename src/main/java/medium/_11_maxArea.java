package medium;

/**
 * Created by daikai on 2020/5/10.
 */
public class _11_maxArea {
    public static void main(String[] args){
        int [] height = new int []{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
        int i = 2;
        // 表达式内就算都为i，表达式后面才i++
        System.out.println(i * height[i++]);
    }

    public static int maxArea(int[] height) {
        int length = height.length;
        int i = 0; int j = length - 1;

        int res = Math.min(height[j], height[i]) * (j - i);
        while(i < j){
            if(height[i] < height[j]){
                i++;
                res = Math.max(res, Math.min(height[j], height[i]) * (j - i) );
            } else {
                j--;
                res = Math.max(res, Math.min(height[j], height[i]) * (j - i) );
            }
        }

        return res;
    }

    ////////////////////
    public int maxArea1(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j){
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

}
