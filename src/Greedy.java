import java.util.Arrays;

/**
 * Created by zplchn on 9/6/16.
 */
public class Greedy {
    //55
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0)
            return true;
        int max = 0;
        for (int i = 0; i < nums.length; ++i){
            if (i > max || max >= nums.length - 1) //note eitehr cannot move or already can
                break;
            max = Math.max(max, nums[i]+i);
        }
        return max >= nums.length - 1;
    }

    //122
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int res = 0;
        for (int i = 1; i < prices.length; ++i){
            if (prices[i] > prices[i-1])
                res += prices[i] - prices[i-1];
        }
        return res;
    }

    //134
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length != cost.length)
            return -1;
        int start = 0, total = 0, sum = 0;
        for (int i = 0; i < gas.length; ++i){
            sum += gas[i] - cost[i];
            total += gas[i] - cost[i];
            if (sum < 0) { // == 0 is fine cuz it can still move to the next stop
                start = i + 1;
                sum = 0;
            }
        }
        return total >= 0 ? start : -1;
    }

    //135
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0)
            return 0;
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 1; i < candies.length; ++i){
            if (ratings[i] > ratings[i-1])
                candies[i] = candies[i-1] + 1;
        }
        int res = candies[candies.length - 1];
        for (int i = candies.length - 2; i >= 0; --i){
            if (ratings[i] > ratings[i+1] && candies[i] <= candies[i+1])
                candies[i] = candies[i+1] + 1;
            res += candies[i];
        }
        return res;
    }
}
