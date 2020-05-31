package medium;

/**
 * Created by daikai on 2020/5/10.
 */
public class _7_myAtoi {

    public static void main(String [] args){
        String raw =   "42";
        System.out.println(myAtoi(raw));
        System.out.println(Character.isDigit('c'));

    }

    public static int myAtoi(String str){
        int res = 0;
        StringBuilder tmp = new StringBuilder();
        if(str.startsWith(" ")){
            str = str.trim();
        }

        if(str.startsWith("+")){
            str = str.replaceFirst("\\+", "");
            if(str.length()>0 && !isNum(str.charAt(0))){
                return 0;
            }
        }

        if(str.startsWith("-")){
            tmp.append("-");
            str = str.replaceFirst("-", "");
            if(str.length()>0 && !isNum(str.charAt(0)) ){
                return 0;
            }
        }



        for(char c : str.toCharArray()){
            if(isNum(c)){
                tmp.append(c);
            }
            else
                break;
        }

        if( "-".equalsIgnoreCase(tmp.toString()) || tmp.length() == 0)
            return 0;

        try{
            res = Integer.parseInt(tmp.toString());
        } catch (Exception e){
            if(tmp.toString().startsWith("-")){
                res = Integer.MIN_VALUE;
            }
            else {
                res = Integer.MAX_VALUE;
            }
        }
        return res;
    }

    public static boolean isFlag(char c){
        if(c == '-')
            return true;
        else
            return false;
    }

    public static boolean isNum(char c){
        if(c >= '0' && c <= '9')
            return true;
        else
            return false;
    }


    /////////////////
    public int myAtoi1(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }
        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative? -ans : ans;
    }

}
