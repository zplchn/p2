import java.util.*;

/**
 * Created by zplchn on 9/4/16.
 */
public class Array {



    class Interval {
        int start, end;

        Interval() {
            start = end = 0;
        }

        Interval(int x, int y) {
            start = x;
            end = y;
        }
    }

    //56
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0)
            return res;
        intervals.sort((i1, i2) -> i1.start - i2.start);
        int i = 1;
        res.add(intervals.get(0));
        while (i < intervals.size()){
            if (intervals.get(i).start <= res.get(res.size() - 1).end){
                res.get(res.size() - 1).end  = Math.max(res.get(res.size() - 1).end, intervals.get(i).end);
            }
            else
                res.add(intervals.get(i));
            ++i;
        }
        return res;
    }


    //57
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        if (intervals == null || newInterval == null)
            return intervals;
        int i = 0;
        while(i < intervals.size() && newInterval.start > intervals.get(i).end){
            res.add(intervals.get(i++));
        }
        while (i < intervals.size() && newInterval.end >= intervals.get(i).start){
            newInterval.start = Math.min(newInterval.start, intervals.get(i).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
            ++i;
        }
        res.add(newInterval);
        while (i < intervals.size())
            res.add(intervals.get(i++));
        return res;
    }

    //73
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        boolean firstRow = false, firstCol = false;
        for (int j = 0; j < matrix[0].length; ++j)
            if (matrix[0][j] == 0){
                firstRow = true;
                break;
            }
        for (int i = 0; i < matrix.length; ++i)
            if (matrix[i][0] == 0){
                firstCol = true;
                break;
            }
        for (int i = 1; i < matrix.length; ++i){
            for (int j = 1; j < matrix[0].length; ++j){
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < matrix.length; ++i){
            for (int j = 1; j < matrix[0].length; ++j){
                if (matrix[0][j] == 0 || matrix[i][0] == 0){
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRow){
            for (int j = 0; j < matrix[0].length; ++j)
                matrix[0][j] = 0;
        }
        if (firstCol){
            for (int i = 0; i < matrix.length; ++i)
                matrix[i][0] = 0;
        }
    }

    //78
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null)
            return res;
        res.add(new ArrayList<>());
        for (int i : nums){
            int size = res.size();
            for (int j = 0; j < size; ++j){
                List<Integer> combi = new ArrayList<>(res.get(j));
                combi.add(i);
                res.add(combi);
            }
        }
        return res;
    }

    //90
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        Arrays.sort(nums);
        res.add(new ArrayList<>());
        int last = 0;
        for (int i = 0; i < nums.length; ++i){
            int start = 0;
            if (i > 0 && nums[i] == nums[i-1])
                start = last;
            last = res.size();
            for (int j = start; j < last; ++j){
                List<Integer> l = new ArrayList<>(res.get(j));
                l.add(nums[i]);
                res.add(l);
            }
        }
        return res;
    }

    //118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows <= 0)
            return res;
        res.add(Arrays.asList(1));

        while (--numRows > 0){
            List<Integer> combi = new ArrayList<>();
            List<Integer> pre = res.get(res.size() - 1);
            for (int i = 0; i <= pre.size(); ++i)
                combi.add((i == pre.size() ? 0 : pre.get(i)) + (i == 0 ? 0 : pre.get(i-1)));
            res.add(combi);
        }
        return res;
    }

    //121
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int min = prices[0], max = 0;
        for (int i = 1; i < prices.length; ++i){
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    //152
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int lmax = nums[0], res = nums[0], lmin = nums[0];
        for (int i = 1; i < nums.length; ++i){
            int t = lmax;
            lmax = Math.max(nums[i], Math.max(lmax * nums[i], lmin * nums[i]));
            lmin = Math.min(nums[i], Math.min(t * nums[i], lmin * nums[i]));
            res = Math.max(lmax, res);
        }
        return res;
    }

    //163
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0){
            res.add(findMissingRangesHelper(lower-1, upper+1)); //need to move edge inorder to include
            return res;
        }
        if (nums[0] > lower)
            res.add(findMissingRangesHelper(lower-1, nums[0]));//edge
        for (int i = 1; i < nums.length; ++i){
            if (nums[i] - nums[i-1] > 1)
                res.add(findMissingRangesHelper(nums[i-1], nums[i]));
        }
        if (nums[nums.length - 1] < upper)
            res.add(findMissingRangesHelper(nums[nums.length - 1], upper+1));
        return res;
    }

    private String findMissingRangesHelper(int l, int u){
        if (l + 2 == u){
            return String.valueOf(l + 1);
        }
        else
            return (l + 1) + "->" + (u - 1);
    }

    //169
    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int cnt = 1, mj = nums[0];
        for (int i = 1; i < nums.length; ++i){
            if (nums[i] == mj)
                ++cnt;
            else if (--cnt == 0){
                cnt = 1;
                mj = nums[i];
            }
        }
        return mj;
    }

    //189
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0)
            return;
        k %= nums.length;
        while (k-- > 0){

        }

    }

    //215
    public int findKthLargest(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < k)
            return 0;
        Queue<Integer> pq = new PriorityQueue<>();
        for (int i : nums){
            pq.offer(i);
            if (pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    //228
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        int l = 0, r = 0;
        while (r < nums.length){
            if (r != nums.length - 1 && nums[r] + 1 == nums[r+1])
                ++r;
            else {
                if (l == r){
                    res.add(Integer.toString(nums[l]));
                }
                else {
                    res.add(nums[l] + "->" + nums[r]);
                }
                l = r = r + 1;
            }
        }
        return res;
    }

    //238
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;
        int[] res = new int[nums.length];
        res[0] = 1;
        for (int i = 1; i < nums.length; ++i)
            res[i] = res[i-1] * nums[i-1];
        int right = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; --i){
            res[i] *= right;
            right *= nums[i];
        }
        return res;
    }

    //239
    public int[] maxSlidingWindow(int[] nums, int k) {
        //first clear left, than add right, make sure window is decreasing!! 3120 k = 3
        if (nums == null || nums.length == 0 || k <= 0)
            return nums;
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 0, j = 0; i < nums.length; ++i){
                if (!queue.isEmpty() && i >= k && queue.peek() == nums[i-k])
                    queue.poll();
                while (!queue.isEmpty() && nums[i] > queue.peekLast())
                    queue.pollLast();
                queue.offer(nums[i]);
                if (i >= k-1)
                    res[j++] = queue.peek();
        }
        return res;
    }

    //274
    public int hIndex(int[] citations) {
        //look from right to left, the last n[i] >= # of elem >= n[i]. 0,100,200. #=2 return #
        if (citations == null || citations.length == 0)
            return 0;
        Arrays.sort(citations);
        int i = 0;
        while (i < citations.length && citations[i] < citations.length - i)
            ++i;
        return citations.length - i;
    }

    //289
    public void gameOfLife(int[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        // 2 : 1-> 0 , 3: 0 -> 1
        int[] xo = {-1,-1,-1,0,0,1,1,1};
        int[] yo = {-1,0,1,-1,1,-1,0,1};

        for (int i = 0; i < board.length; ++i){
            for (int j = 0; j < board[0].length; ++j){
                //8
                int cnt = 0;
                for (int k = 0; k < xo.length; ++k){
                    int x = i + xo[k], y = j + yo[k];
                    if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && (board[x][y] == 1 || board[x][y] == 2))
                        ++cnt; //note here is before change so include 1 and 2
                }
                if (board[i][j] == 1 && (cnt < 2 || cnt > 3))
                    board[i][j] = 2;
                else if (board[i][j] == 0 && cnt == 3)
                    board[i][j] = 3;
            }
        }
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[0].length; ++j) {
                board[i][j] %= 2;
            }
        }
    }
}
