package easy;

public class _9_huiwen {

    public static void main(String[] args) {

        System.out.println(isPalindrome(12321));

    }
    public static boolean isPalindrome(int x) {

        if(x < 0) return false;
        if(x < 10) return true;

        boolean res = true;
        int w = 0;
        while (Math.pow(10, w) <= x){
            w++;
        }

        int l = w /2;
        int r = l + 1;
        if(w % 2 != 0) {
            r++;
        }

        for(; l >0; l--,r++){
            int numl = (int)(x / Math.pow(10, l - 1)) % 10;
            int num2 = (int)(x / Math.pow(10, r - 1)) % 10;

            if(numl != num2)
                return false;
        }

        //不可取 1000021 第二轮直接变成2 返回错误的true
//        if ((int)(x / Math.pow(10, w - 1)) != x % 10)
//            return false;
//        else isPalindrome((int)(x-(x / Math.pow(10, w - 1) * Math.pow(10, w - 1))) / 10);

        return res;
    }
}
