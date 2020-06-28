package easy;

public class _7_reverse {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(reverse(1646324359));
        System.out.println(reverse1(-2149));

    }

    private static int reverse1(int x) {
        int res = 0;
        int tmp = 0;
        int t = 0;
        while(x != 0 ){
            t = x % 10;
            tmp = res * 10 + t;

            // 判断溢出
            if((tmp - t) / 10 != res)
                return 0;

            res = tmp;

            x = x /10;
        }

        return res;
    }

    public static int reverse(int x) {
        StringBuilder builder = new StringBuilder();
        if(x < 0){
            builder.append("-");
            x = Math.abs(x);
        }

        if(x == 0){
            return 0;
        }

        while(x % 10 == 0){
            x = x / 10;
        }

        while( x != 0){
            builder.append(x % 10);
            x = x / 10;
        }

        int res = 0;

        try {
            res = Integer.parseInt(builder.toString());
        } catch (Exception e){

        }
        return res;
    }


}
