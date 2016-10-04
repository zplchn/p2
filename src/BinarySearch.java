/**
 * Created by zplchn on 9/10/16.
 */
public class BinarySearch {

    //33
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1;
        while (l <= r){
            int m = l + ((r-l) >> 1);
            if (target == nums[m])
                return m;
            else if (nums[m] < nums[r]){
                if (target > nums[m] && target < nums[r]){
                    l = m + 1;
                }
                else
                    r = m - 1;
            }
            else {
                if (target >= nums[l] && target < nums[m])
                    r = m - 1;
                else
                    l = m + 1;

            }
        }
        return -1;
    }

    //34
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        if (nums == null || nums.length == 0)
            return res;
        int l = 0, r = nums.length - 1, m;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (nums[m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        int t = l;
        l = 0;
        r = nums.length - 1;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (nums[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        if (t > r)
            return res;
        res[0] = t;
        res[1] = r;
        return res;

    }

    //35
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (nums[m] < target)
                l = m + 1;
            else if (nums[m] > target)
                r = m - 1;
            else
                return m;
        }
        return l;
    }
    //50
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        double t = myPow(x, n / 2);
        if (n % 2 == 0)
            return t * t;
        else if (n > 0)
            return t * t * x;
        else
            return t * t / x;
    }

    //74
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int l = 0, r = matrix.length - 1, m;
        while (l <= r){
            m = l + ((r - l) >> 1);
            int t = matrix[m][matrix[m].length - 1];
            if (target > t)
                l = m + 1;
            else if (target < t)
                r = m - 1;
            else
                return true;
        }
        if (l >= matrix.length)
            return false; //special case when l is great then all elem and then the row will be out of bound
        int row = l;
        l = 0;
        r = matrix[row].length - 1;
        while (l <= r){
            m = l + ((r - l) >> 1);
            if (target > matrix[row][m])
                l = m + 1;
            else if (target < matrix[row][m])
                r = m - 1;
            else
                return true;
        }
        return false;

    }

    //81
    public boolean searchDup(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int l = 0, r = nums.length - 1, m;
        while (l <= r){
            m = l + ((r-l) >> 1);
            if (nums[m] == target)
                return true;
            else if (nums[m] < nums[r]){
                if (target > nums[m] && target <= nums[r])
                    l = m + 1;
                else
                    r = m - 1;
            }
            else if (nums[m] > nums[r]){
                if (target >= nums[l] && target < nums[m])
                    r = m - 1;
                else
                    l = m + 1;
            }
            else
                --r;
        }
        return false;
    }

    //153
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;
        while (l < r){
            m = l + ((r - l) >> 1);
            if (nums[m] > nums[r])
                l = m + 1;
            else
                r = m;
        }
        return nums[l];
    }

    //154
    public int findMinDup(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length - 1, m;
        while (l < r){
            m = l + ((r - l) >> 1);
            if (nums[m] > nums[r])
                l = m +1;
            else if (nums[m] < nums[r])
                r = m;
            else
                --r;
        }
        return nums[l];
    }

    //162
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return -1;
        int l = 0, r = nums.length-1, m;
        while (l < r){
            m = l + ((r - l) >> 1);
            if ((m == 0 || nums[m] > nums[m - 1]) && (m == nums.length - 1 || nums[m] > nums[m + 1]))
                return m;
            else if (nums[m] < nums[m + 1])
                l = m + 1;
            else
                r = m - 1;
        }
        return nums[l];
    }
}
