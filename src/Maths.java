import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zplchn on 9/8/16.
 */
public class Maths {

    //7
    public int reverse(int x) {
        long res = 0;
        while (x != 0){
            res = res * 10 + x % 10;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE)
            return 0;
        return (int)res;
    }

    //9
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int div =1;
        while (x / div >= 10)
            div *= 10;
        while (x != 0){
            if (x/div != x%10) //the last bit is mod 10. 2%1 = 0
                return false;
            x = x % div / 10;
            div /= 100;
        }
        return true;
    }

    //13
    public int romanToInt(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> hm = new HashMap<>();
        hm.put('I', 1);
        hm.put('V', 5);
        hm.put('X', 10);
        hm.put('L', 50);
        hm.put('C', 100);
        hm.put('D', 500);
        hm.put('M', 1000);
        int res = hm.get(s.charAt(s.length() - 1));
        int cur, last = res;
        for (int i = s.length() - 2; i>= 0; --i){
            cur = hm.get(s.charAt(i));
            if (cur < last)
                res -= cur;
            else
                res += cur;
            last = cur;
        }
        return res;
    }

    //66
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return digits;
        int i = digits.length - 1;
        while (i >= 0 && digits[i] == 9)
            digits[i--] = 0;
        if (i >= 0){
            digits[i]++;
            return digits;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    //67
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0)
            return b;
        if (b == null || b.length() == 0)
            return a;
        int i = a.length() -1, j = b.length()-1, carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0 || carry != 0){
            int sum = (i >= 0 ? a.charAt(i--) - '0' : 0) + (j >= 0 ? b.charAt(j--) - '0' : 0) + carry;
            sb.append(sum % 2);
            carry = sum / 2;
        }
        return sb.reverse().toString();
    }

    class Point{
        int x, y;
        Point() {x = 0; y = 0;}
        Point(int i, int j){x = i; y = j;}
    }

    //149
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0)
            return 0;
        int max = 1;
        for (int i = 0; i < points.length -1; ++i){

            Point a = points[i];
            int same = 0;
            Map<Double, Integer> hm = new HashMap<>();

            for (int j = i + 1; j < points.length; ++j){
                Point b = points[j];
                if (a.x == b.x && a.y == b.y)
                    ++same;
                else if (a.x == b.x){
                    hm.put(Double.MAX_VALUE, hm.containsKey(Double.MAX_VALUE) ? hm.get(Double.MAX_VALUE) + 1 : 2 );
                }
                else if (a.y == b.y){
                    hm.put(0.0, hm.containsKey(0.0) ? hm.get(0.0) + 1 : 2);
                }
                else {
                    double slope = (double)(a.y - b.y)/(a.x - b.x);
                    hm.put(slope, hm.containsKey(slope) ? hm.get(slope) + 1 : 2);
                }
            }
            int lmax = 1;
            for (int k : hm.values())
                lmax = k > lmax ? k : lmax;
            max = Math.max(max, lmax + same);
        }
        return max;
    }

    //168
    public String convertToTitle(int n) {
        if (n < 1)
            return "";
        StringBuilder sb = new StringBuilder();
        while (--n >= 0){
            sb.append((char)(n % 26 + 'A'));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    //171
    public int titleToNumber(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < s.length(); ++i){
            res = res * 26 + s.charAt(i) - 'A' + 1;
        }
        return res;
    }

    //202
    public boolean isHappy(int n) {
        if (n <= 0)
            return false;
        Set<Integer> hs = new HashSet<>();
        while (!hs.contains(n)){
            if (n == 1)
                return true;
            hs.add(n);
            int t = 0;
            while (n != 0){
                int x = n % 10;
                n /= 10;
                t += x * x;
            }
            n = t;
        }
        return false;
    }

    //258
    public int addDigits(int num) {
        if (num <= 0)
            return 0;
        return (num - 1)%9 + 1; //1-based, first minus 1, every 9 so mod 9, and at end + 1 back
    }

    //263
    public boolean isUgly(int num) {
        if (num <= 0)
            return false;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;

        return num == 1;

    }
}
