import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zplchn on 9/4/16.
 */
public class DP {
    //5
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0)
            return s;
        boolean[][] dp = new boolean[s.length()][s.length()];
        int max = 0, l = 0, r = 0;
        for (int i = s.length() - 1; i >= 0; --i){
            for (int j = i; j < s.length(); ++j){
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        l = i;
                        r = j;
                    }
                }
            }
        }
        return s.substring(l, r+1);
    }

    //44
    public boolean isMatch(String s, String p) {
        if (s == null)
            return p == null;
        if (p == null)
            return false;
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int j = 1; j <= p.length(); ++j)
            dp[0][j] = p.charAt(j-1) == '*' && dp[0][j-1];
        for (int i = 1; i <= s.length(); ++i)
            for (int j = 1; j <= p.length(); ++j){
                if (p.charAt(j-1) != '*'){
                    dp[i][j] = (p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '?') && dp[i-1][j-1];
                }
                else {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    //53
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int sum = 0, max = Integer.MIN_VALUE;
        for (int i : nums){
            if (sum < 0)
                sum = 0;
            sum += i;
            max = Math.max(max, sum);
        }
        return max;
    }

    //62
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        int[][] dp = new int[m][n];
        for (int j = 0; j < n; ++j)
            dp[0][j] = 1;
        for (int i = 1; i < m; ++i)
            dp[i][0] = 1;
        for (int i = 1; i < m; ++i){
            for (int j = 1; j < n; ++j){
                dp[i][j] = dp[i-1][j] + dp [i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    //63
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1)
            return 0;
        obstacleGrid[0][0] = 1;
        for (int j = 1; j < obstacleGrid[0].length; ++j){
            if (obstacleGrid[0][j] == 1)
                obstacleGrid[0][j] = 0;
            else
                obstacleGrid[0][j] = obstacleGrid[0][j-1];
        }
        for (int i = 1; i < obstacleGrid.length; ++i){
            if (obstacleGrid[i][0] == 1)
                obstacleGrid[i][0] = 0;
            else
                obstacleGrid[i][0] = obstacleGrid[i-1][0];
        }
        for (int i = 1; i < obstacleGrid.length; ++i){
            for (int j = 1; j < obstacleGrid[0].length; ++j){
                if (obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
            }
        }
        return obstacleGrid[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    //64
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        for (int j = 1; j < grid[0].length; ++j)
            grid[0][j] += grid[0][j-1];
        for (int i = 1; i < grid.length; ++i)
            grid[i][0] += grid[i-1][0];
        for (int i = 1; i < grid.length; ++i){
            for (int j = 1; j < grid[0].length; ++j){
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    //70
    public int climbStairs(int n) {
        if (n <= 0)
            return 0;
        if (n < 2)
            return 1;
        int t1 = 1, t2 = 1, res = 0;
        while (n-- >= 2){
            res = t2 + t1;
            t1 = t2;
            t2 = res;
        }
        return res;
    }

    //72
    public int minDistance(String word1, String word2) {
        //a-b, ab-a, a-ab
        if (word1 == null || word2 == null)
            return -1;
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int j = 0; j < dp[0].length; ++j)
            dp[0][j] = j;
        for (int i = 0; i < dp.length; ++i)
            dp[i][0] = i;
        for (int i = 1; i < dp.length; ++i){
            for (int j = 1; j < dp[0].length; ++j){
                if (word1.charAt(i-1) == word2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1],dp[i-1][j-1]))+1;
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    //91
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = dp[1] = 1;
        for (int i = 1; i < s.length(); ++i){
            dp[i+1] = s.charAt(i) == '0' ? 0 : dp[i];
            int t = Integer.parseInt(s.substring(i-1,i+1));
            if ( t >= 10 && t <= 26)
                dp[i+1] += dp[i-1];
        }
        return dp[dp.length - 1];
    }

    //115
    public int numDistinct(String s, String t) {
        if (s == null || t == null)
            return 0;
        int[][] dp = new int[s.length()+1][t.length()+1];
        for (int i = 0; i < dp.length; ++i)
            dp[i][0] = 1; //delete all has 1 seq
        for (int i = 1; i < dp.length; ++i){
            for (int j = 1; j < dp[0].length; ++j){
                dp[i][j] = dp[i-1][j] + (s.charAt(i-1) == t.charAt(j-1) ? dp[i-1][j-1] : 0);
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    //120
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0)
            return 0;
        for (int i = triangle.size() - 2; i >= 0; --i){
            for (int j = 0; j < triangle.get(i).size(); ++j)
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i+1).get(j), triangle.get(i).get(j+1)) );
        }
        return triangle.get(0).get(0);
    }

    //131
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; --i){
            for (int j = i; j < s.length(); ++j){
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i+1][j-1]))
                    dp[i][j] = true;
            }
        }
        partitionHelper(s, 0, res, dp, new ArrayList<String>());
        return res;
    }

    private void partitionHelper(String s, int k, List<List<String>> res, boolean[][] dp, List<String> combi){
        if (k == s.length()){
            res.add(new ArrayList<>(combi));
            return;
        }
        for (int i = k; i < s.length(); ++i){
            if (dp[k][i]){
                combi.add(s.substring(k, i+1));
                partitionHelper(s,i+1, res, dp, combi);
                combi.remove(combi.size()-1);
            }
        }
    }

    //132
    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; --i){
            for (int j = i; j < s.length(); ++j){
                if (s.charAt(i) == s.charAt(j) && ((j - i <= 2 || dp[i+1][j-1])))
                    dp[i][j] = true;
            }
        }
        int[] dp1 = new int[s.length() + 1];
        Arrays.fill(dp1, Integer.MAX_VALUE);
        dp1[0] = 0;
        for (int i = 0; i < dp.length; ++i){
            for (int j = i; j < dp[0].length; ++j){
                if (dp[i][j])
                    dp1[j+1] = Math.min(dp1[j+1], dp1[i] + 1);
            }
        }
        return dp1[dp1.length - 1] - 1;

    }

    //256
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length != 3)
            return 0;
        int[][] dp = new int[costs.length + 1][costs[0].length];
        for (int i = 1; i < dp.length; ++i){
            for (int j = 0; j < dp[0].length; ++j){
                dp[i][j] = costs[i-1][j] + Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
            }
        }
        return Math.min(dp[dp.length - 1][0], Math.min(dp[dp.length - 1][1],dp[dp.length - 1][2]));
    }

    //279
    public int numSquares(int n) {
        if (n <= 0)
            return 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp, 4); //4-sqare theory, a integer can be constructed by adding up to 4 squares numbers
        dp[0] = 0;//dont forget initial 0 is 0
        for (int i = 0; i <=n; ++i){
            for (int j = 1; i + j*j <= n; ++j)
                dp[i + j*j] = Math.min(dp[i + j*j], dp[i]+1);
        }
        return dp[dp.length - 1];
    }
}
