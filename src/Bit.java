/**
 * Created by zplchn on 9/10/16.
 */
public class Bit {
    //136
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = 0;
        for (int i : nums)
            res ^= i;
        return res;
    }

    //137
    public int singleNumber2(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; ++i){
            int cnt = 0;
            for (int k : nums)
                cnt += ((k >> i) & 1);
            res |= (cnt % 3) << i;
        }
        return res;
    }

    //191
    public int hammingWeight(int n) {
        int cnt = 0;
        for (int i = 0; i < 32; ++i){
            cnt += (n & 1);
            n >>>= 1;
        }
        return cnt;
    }

    //201
    public int rangeBitwiseAnd(int m, int n) {
        if (m > n)
            return 0;
        int shift = 0;
        while (m != n){
            m >>= 1;
            n >>= 1;
            ++shift;
        }
        return m << shift;
    }

    //231
    public boolean isPowerOfTwo(int n) {
        return (n > 0 && (n & (n - 1)) == 0);
    }

}
