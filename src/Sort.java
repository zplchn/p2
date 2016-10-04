import java.util.Arrays;

/**
 * Created by zplchn on 9/17/16.
 */
public class Sort {
    //179
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return "";
        String[] ns = new String[nums.length];
        for (int i = 0; i < nums.length; ++i)
            ns[i] = String.valueOf(nums[i]);
        Arrays.sort(ns, (s1, s2) -> (s2 + s1).compareTo(s1 + s2));
        StringBuilder sb = new StringBuilder();
        for (String s : ns)
            sb.append(s);
        //if nums is 0, 0, 0...
        while (sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return sb.toString();
    }

    //242
    public boolean isAnagram(String s, String t) {
        if (s == null)
            return t == null;
        if (t == null)
            return false;
        if (s.length() != t.length())
            return false;
        char[] sa = s.toCharArray();
        char[] ta = t.toCharArray();
        Arrays.sort(sa);
        Arrays.sort(ta);

        return Arrays.equals(sa, ta);

    }
}
