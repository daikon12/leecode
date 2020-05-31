package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daikai on 2020/5/8.
 */
public class _6Zconvert {

    public static void main(String[] args){
        String s = "LEETCODEISHIRING";
        int numRows = 4;
        System.out.println(convert(s,numRows));
    }
    public static String convert(String s, int numRows) {

        if (numRows < 2)
            return s;

        StringBuilder res = new StringBuilder();
        List<StringBuilder> rows = new ArrayList<>();
        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());

        int index = 0;
        int flag = -1;
        for(char c : s.toCharArray()){
            rows.get(index).append(c);
            if(index==0 || index == (numRows - 1))
                flag = - flag;
            index += flag;
        }
        for(StringBuilder row : rows){
            res.append(row);
        }

        return res.toString();


//        if(numRows < 2) return s;
//        List<StringBuilder> rows = new ArrayList<StringBuilder>();
//        for(int i = 0; i < numRows; i++) rows.add(new StringBuilder());
//        int i = 0, flag = -1;
//        for(char c : s.toCharArray()) {
//            rows.get(i).append(c);
//            if(i == 0 || i == numRows -1) flag = - flag;
//            i += flag;
//        }
//        StringBuilder res = new StringBuilder();
//        for(StringBuilder row : rows) res.append(row);
//        return res.toString();


    }
}
