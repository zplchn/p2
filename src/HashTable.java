import java.util.*;

/**
 * Created by zplchn on 9/5/16.
 */
public class HashTable {

    //36
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return false;
        for (int i = 0; i < 9; ++i){
            Set<Integer> hs = new HashSet<>();

        }


    }

    //170
    class TwoSum {
        private Map<Integer, Integer> hm = new HashMap<>();

        // Add the number to an internal data structure.
        public void add(int number) {
            hm.put(number, hm.containsKey(number) ? 2 : 1);
        }

        // Find if there exists any pair of numbers which sum is equal to the value.
        public boolean find(int value) {
            for (int i : hm.keySet()){
                int t = value - i;
                if (hm.containsKey(t) && ((i != t) || (i == t && hm.get(i) > 1))) //remind the i!=t condition
                    return true;
            }
            return false;
        }
    }

    //187
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        //need to remove dup so use map
        Map<String, Boolean> hm = new HashMap<>();
        for (int i = 0; i <= s.length() - 10; ++i){
            String str = s.substring(i, i + 10);
            if (hm.containsKey(str)){
                if (!hm.get(str)){
                    hm.put(str, true);
                    res.add(str);
                }
            }
            else
                hm.put(str, false);
        }
        return res;
    }

    //205
    public boolean isIsomorphic(String s, String t) {
        if (s == null)
            return t == null;
        if (t == null || s.length() != t.length())
            return false;
        Map<Character,Character> hm = new HashMap<>();

        for (int i = 0; i < t.length(); ++i){
            if (hm.containsKey(t.charAt(i))){
                if (hm.get(t.charAt(i)) != s.charAt(i))
                    return false;
            }
            else {
                if (hm.containsValue(s.charAt(i)))
                    return false;
                hm.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;

    }

    //217
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;
        Set<Integer> hs = new HashSet<>();
        for (int i : nums){
            if (hs.contains(i))
                return true;
            hs.add(i);
        }
        return false;
    }

    //219
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return false;
        //sliding window
        Set<Integer> hs = new HashSet<>();
        for (int i = 0; i < nums.length; ++i){
            if (hs.contains(nums[i]))
                return true;
            hs.add(nums[i]);
            if (hs.size() > k)
                hs.remove(nums[i - k]);
        }
        return false;
    }

    //266
    public boolean canPermutePalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            hm.put(c, hm.containsKey(c) ? hm.get(c) + 1 : 1);
        }
        boolean hasOdd = false;
        for (int i : hm.values()){
            if (i % 2 == 1){
                if (hasOdd)
                    return false;
                hasOdd = true;
            }
        }
        return true;
    }

    //290
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || pattern.length() == 0 || str == null || str.length() == 0)
            return false;
        String[] tokens = str.split("\\s+"); //+ means 1 and more(as many as ) \s is space
        if (pattern.length() != tokens.length) //note!!
            return false;
        Map<Character, String> hm = new HashMap<>();

        for (int i = 0; i < pattern.length(); ++i){
            if (hm.containsKey(pattern.charAt(i))){
                if (!hm.get(pattern.charAt(i)).equals(tokens[i]))
                    return false;
            }
            else if (hm.containsValue(tokens[i])) //containsValue is o(n) operation
                return false;
            else
                hm.put(pattern.charAt(i), tokens[i]);
        }
        return true;
    }
}
