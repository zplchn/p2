import java.util.*;

/**
 * Created by zplchn on 9/5/16.
 */
public class Backtracking {

    //17
    private static final String[] phone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return res;
        letterCombinationsHelper(digits, 0, res, new StringBuilder());
        return res;
    }

    private void letterCombinationsHelper(String digits, int i, List<String> res, StringBuilder sb){
        if (i == digits.length()){
            res.add(new String(sb));
            return;
        }
        String token = phone[digits.charAt(i) - '0'];
        for (int k = 0; k < token.length(); ++k){
            sb.append(token.charAt(k));
            letterCombinationsHelper(digits, i + 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    //22
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 1)
            return res;
        generateParenthesisHelper(n, 0, 0, res, new StringBuilder());
        return res;
    }

    private void generateParenthesisHelper(int n, int l, int r, List<String> res, StringBuilder sb){
        if (r == n){
            res.add(new String(sb));
            return;
        }
        if (l < n){
            sb.append('(');
            generateParenthesisHelper(n, l+1, r, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (r < l){
            sb.append(')');
            generateParenthesisHelper(n, l, r + 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args){
        Backtracking bt = new Backtracking();
        bt.generateParenthesis(2);
    }

    //39
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        combinationSumHelper(candidates, 0, res, new ArrayList<Integer>(), target);
        return res;
    }

    private void combinationSumHelper(int[] candidates, int k, List<List<Integer>> res, List<Integer> combi, int target){
        if (0 == target){
            res.add(new ArrayList<>(combi));
            return;
        }
        for (int i = k; i < candidates.length; ++i){
            if (i > k && candidates[i] == candidates[i-1])
                continue;
            if (target >= candidates[i]){
                combi.add(candidates[i]);
                combinationSumHelper(candidates, i, res, combi, target - candidates[i]);
                combi.remove(combi.size()-1);
            }
            else
                break;
        }
    }

    //40
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        combinationSum2Helper(candidates, target, 0, 0, res, new ArrayList<>());
        return res;
    }

    private void combinationSum2Helper(int[]candidates, int target, int start, int sum, List<List<Integer>> res, List<Integer> combi){
        if (sum == target){
            res.add(new ArrayList<>(combi));
            return;
        }
        if (start > candidates.length){
            return;
        }
        for (int i = start; i < candidates.length && sum + candidates[i] <= target; ++i){
            if (i > start && candidates[i] == candidates[i-1])
                continue;
            combi.add(candidates[i]);
            combinationSum2Helper(candidates, target, i + 1, sum + candidates[i], res, combi);
            combi.remove(combi.size() - 1);
        }
    }

    //46
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        permuteHelper(nums, 0, res, new ArrayList<Integer>(), new boolean[nums.length]);
        return res;
    }

    private void permuteHelper(int[] nums, int i, List<List<Integer>> res, List<Integer> combi, boolean[] used){
        if (i == nums.length){
            res.add(new ArrayList<>(combi));
            return;
        }
        for (int k = 0; k < nums.length; ++k){
            if (used[k])
                continue;
            used[k] = true;
            combi.add(nums[k]);
            permuteHelper(nums, i + 1, res, combi, used);
            combi.remove(combi.size() - 1);
            used[k] = false;
        }
    }

    //77
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 0 || k < 0)
            return res;
        combiHelper(n, k, 1, res, new ArrayList<Integer>());
        return res;
    }

    private void combiHelper(int n, int k, int start, List<List<Integer>> res, List<Integer> combi){

        if (k == 0){
            res.add(new ArrayList<>(combi));
            return;
        }
        if (start > n)
            return;// this needs to after the end condition. becuase the last iter the start can > n when k == 0, cuz it's not a valid iter
        for (int i = start; i <= n; ++i){
            combi.add(i);
            combiHelper(n, k - 1, i + 1, res, combi);
            combi.remove(combi.size() - 1);
        }
    }

    //79
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null || board.length == 0 || board[0].length == 0 || word.length() == 0)
            return false;
        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[0].length; ++j){
                if (existHelper(board, i, j, new boolean[board.length][board[0].length], word, 0))
                    return true;
            }
        }
        return false;
    }

    private boolean existHelper(char[][] board, int i, int j, boolean[][] visit, String word, int k){
        if (k == word.length())
            return true;
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visit[i][j] || word.charAt(k) != board[i][j])
            return false;
        visit[i][j] = true;
        if (existHelper(board, i - 1, j, visit, word, k + 1) ||
                existHelper(board, i + 1, j, visit, word, k + 1) ||
                existHelper(board, i, j - 1, visit, word, k + 1) ||
                existHelper(board, i, j + 1, visit, word, k + 1))
            return true;
        visit[i][j] = false;
        return false;
    }

    、、93

    //216
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if(k <= 0)
            return res;
        combinationSum3Helper(k, n, 0, 0, 1, res, new ArrayList<Integer>());
        return res;
    }

    private void combinationSum3Helper(int k, int n, int i, int sum, int start, List<List<Integer>> res, List<Integer> combi){
        if (i == k){
            if (sum == n){
                res.add(new ArrayList<>(combi));
            }
            return;
        }
        if (sum >= n)
            return;
        for (int x = start; x <= 9; ++x){
            if (sum + x <= n){
                combi.add(x);
                combinationSum3Helper(k, n, i+1, sum + x, x + 1, res, combi);
                combi.remove(combi.size()-1);
            }
        }
    }



    //291
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern == null || str == null)
            return false;
        if (pattern.length() == 0)
            return str.length() == 0;
        if (str.length() == 0)
            return false;
        Map<Character, String> hm = new HashMap<>();
        return wordPatternMatchHelper(pattern, 0, str, 0, hm);
    }

    private boolean wordPatternMatchHelper(String pattern, int pi, String str, int si, Map<Character, String> hm){
        if (pi == pattern.length())
            return si == str.length();
        else if (si == str.length())
            return false;
        if (hm.containsKey(pattern.charAt(pi))){
            if (!str.startsWith(hm.get(pattern.charAt(pi)), si))
                return false;
            return wordPatternMatchHelper(pattern, pi + 1, str, si + hm.get(pattern.charAt(pi)).length(), hm);
        }
        else {
            for (int i = si + 1; i <= str.length(); ++i){
                String s = str.substring(si, i);
                if (hm.containsValue(s))
                    continue;
                hm.put(pattern.charAt(pi), s);
                if (wordPatternMatchHelper(pattern, pi + 1, str, i, hm))
                    return true;
                hm.remove(pattern.charAt(pi));
            }
        }
        return false;
    }
}
