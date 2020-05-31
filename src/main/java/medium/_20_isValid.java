package medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;


/**
 * Created by daikai on 2020/5/16.
 */
public class _20_isValid {

    public static void main(String[] args){

        String s = "{[]}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {

        Stack stack = new Stack();

        for(char c : s.toCharArray()){
            if(!isLeftKey(c) && !isRightKey(c)) continue;
            else if(isLeftKey(c)){
                stack.push(c);
            }
            else {
                if(stack.size() == 0)
                    return false;
                char L = (char)stack.pop();
                if(!Valid(L,c)) return false;
            }
        }
        if(stack.size() != 0)
            return false;
        return true;

    }

    public static boolean isLeftKey(char c){
        if(c == '(' || c =='{' || c =='[')
            return true;
        else
            return false;
    }

    public static boolean isRightKey(char c){
        if(c == ')' || c =='}' || c ==']')
            return true;
        else
            return false;
    }


    public static boolean Valid(char L,char R){

        if(L == '(' && R == ')') return true;
        else if(L == '[' && R == ']') return true;
        else if(L == '{' && R == '}') return true;
        else return false;
    }


    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid1(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }

}
