import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by zplchn on 9/4/16.
 */
public class Strings {

    //14
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";

        int min = strs[0].length();
        for (int i = 1; i < strs.length; ++i){
            int j = 0;
            for (; j < Math.min(min, strs[i].length()); ++j){
                if (strs[i].charAt(j) != strs[0].charAt(j)){
                    break;
                }
            }
            min = Math.min(min, j);
        }
        return strs[0].substring(0, min);
    }

    //20
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); ++i){
            switch(s.charAt(i)){
                case '(':
                case '[':
                case '{':
                    stack.push(s.charAt(i));
                    break;
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(')
                        return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[')
                        return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{')
                        return false;
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }

    //38
    public String countAndSay(int n) {
        if (n <= 0)
            return "";
        StringBuilder sb = new StringBuilder("1");
        while (n-- > 1){
            int i = 1, cnt = 1;
            StringBuilder t = new StringBuilder();
            while (i < sb.length()){
                if (sb.charAt(i) != sb.charAt(i - 1)){
                    t.append(cnt);
                    t.append(sb.charAt(i - 1));
                    cnt = 1;
                }
                else
                    ++cnt;
                ++i;
            }
            t.append(cnt);
            t.append(sb.charAt(i - 1));

            sb = t;
        }
        return sb.toString();
    }

    //58
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        s = s.trim();
        String[] tokens =  s.split("\\s+"); //if s = "" and use \\s+ will return a one element "" in the array
        return tokens[tokens.length - 1].length();

    }

    public static void main(String[] args){
        Strings s = new Strings();
        s.lengthOfLastWord("   ");
    }

    //150
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        String op = "+-*/";
        for (String s : tokens){
            if (!op.contains(s))
                stack.push(Integer.parseInt(s));
            else {
                int y = stack.pop();
                int x = stack.pop();
                switch(s){
                    case "+":
                        stack.push(x + y);
                        break;
                    case "-":
                        stack.push(x - y);
                        break;
                    case "*":
                        stack.push(x * y);
                        break;
                    case "/":
                        stack.push(x / y);
                        break;
                    default:
                        break;
                }
            }
        }
        return stack.pop();
    }

    //161
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null)
            return false;
        int ns = s.length(), nt = t.length();
        if (ns > nt)
            return isOneEditDistance(t, s);
        int diff = nt - ns;
        if (diff > 1)
            return false;
        int i = 0, dist = 0;
        while (i < s.length() && s.charAt(i) == t.charAt(i)){
            ++i;
        }
        if (i == s.length())
            return diff == 1; //here notice!!
        if (diff == 0)
            ++i;
        while (i < s.length() && s.charAt(i) == t.charAt(i + diff))
            ++i;
        return i == s.length();
    }

    //165
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null)
            return 0;
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int diff = 0;
        for (int i = 0; i < Math.max(v1.length, v2.length); ++i){
            diff = (i < v1.length ? Integer.parseInt(v1[i]) : 0) - (i < v2.length ? Integer.parseInt(v2[i]) : 0);
            if (diff != 0)
                return diff > 0 ? 1 : -1;
        }
        return 0;
    }

    //271
    class Codec {

        // Encodes a list of strings to a single string.
        public String encode(List<String> strs) {
            if (strs == null || strs.size() == 0)
                return "";
            StringBuilder sb = new StringBuilder();
            for (String s : strs){
                sb.append(s.length());
                sb.append('#');
                sb.append(s);
            }
            return sb.toString();
        }

        // Decodes a single string to a list of strings.
        public List<String> decode(String s) {
            List<String> res = new ArrayList<>();
            if (s == null || s.length() == 0)
                return res;
            int i = 0;
            while (i < s.length()){
                int t = s.indexOf('#', i), n = 1 + t + Integer.parseInt(s.substring(i, t));
                res.add(s.substring(t + 1, n));
                i = n;
            }
            return res;
        }
    }

}
