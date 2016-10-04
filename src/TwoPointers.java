import java.util.*;

/**
 * Created by zplchn on 9/4/16.
 */
public class TwoPointers {

    //1
    public int[] twoSum(int[] nums, int target) {
        int[] res= {-1,-1};
        if (nums == null || nums.length < 2)
            return res;
        Map<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < nums.length; ++i){
            if (hm.containsKey(target - nums[i])){
                res[0] = hm.get(target - nums[i]);
                res[1] = i;
            }
            hm.put(nums[i], i);
        }
        return res;
    }

    //3
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int max = 1, start = 0;
        Map<Character, Integer> hm = new HashMap<>();
        for (int i = 0; i < s.length(); ++i){
            if (hm.containsKey(s.charAt(i)) && hm.get(s.charAt(i)) >= start){
                start = hm.get(s.charAt(i)) + 1;
            } else
                max = Math.max(max, i - start + 1);
            hm.put(s.charAt(i), i);
        }
        return max;
    }

    //11
    public int maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        int l = 0, r = height.length - 1, res = 0;
        while (l < r){
            int min = Math.min(height[l], height[r]);
            res = Math.max(res, min * (r - l));
            if (min == height[l]) {
                while (l < r && height[l] <= min)
                    ++l;
            }
            else {
                while (l < r && height[r] <= min)
                    --r;
            }
        }
        return res;
    }

    //15
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3)
            return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1, r = nums.length - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < 0)
                    ++l;
                else if (sum > 0)
                    --r;
                else {
                    res.add(Arrays.asList(nums[i], nums[l++], nums[r--]));
                    while (l < r && nums[l] == nums[l-1])
                        ++l;
                    while (l < r && nums[r] == nums[r+1])
                        --r;
                }
            }
        }
        return res;
    }

    //16
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return 0;
        int res = 0, delta = Integer.MAX_VALUE;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            int l = i + 1, r = nums.length - 1, sum = 0;
            while (l < r){
                sum = nums[i] + nums[l] + nums[r];
                if (Math.abs(sum - target) < delta){
                    delta = Math.abs(sum - target);
                    res = sum;
                }
                if (sum < target)
                    ++l;
                else if (sum > target)
                    --r;
                else
                    break; //only when == needs remove dup here and here does not have any
            }
        }
        return res;
    }

    //18
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 4)
            return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length-3; ++i){
            if (i > 0 && nums[i] == nums[i-1])
                continue;
            for (int j = i+1; j < nums.length -2; ++j){
                if (j > i+1 && nums[j] == nums[j-1]) //j>i+1 is must becuase the first dup is still valid
                    continue;
                int l = j + 1, r = nums.length - 1;
                while (l < r){
                    int sum = nums[i] + nums[j] + nums[l] + nums[r];
                    if (sum < target)
                        ++l;
                    else if (sum > target)
                        --r;
                    else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l++], nums[r--]));
                        while (l < r && nums[l] == nums[l-1])
                            ++l;
                        while (l < r && nums[r] == nums[r+1])
                            --r;
                    }
                }
            }
        }
        return res;
    }

    //26
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int l = 0, r = 1;
        while (r < nums.length){
            if (nums[l] != nums[r])
                nums[++l] = nums[r];
            ++r;
        }
        return l + 1;
    }

    //27
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int l = 0, r = 0;
        while (r < nums.length){
            if (nums[r] != val)
                nums[l++] = nums[r];
            ++r;
        }
        return l;
    }

    //42
    public int trap(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int l = 0, r = height.length - 1, res = 0, t;
        while (l < r){
            if (height[l] <= height[r]){
                t = height[l++];
                while (l < r && height[l] <= t)
                    res += t - height[l++];
            }
            else {
                t = height[r--];
                while (l < r && height[r] <= t)
                    res += t - height[r--];
            }
        }
        return res;
    }

    //75
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        int l0 = 0,l1 = 0, l2 = nums.length - 1;
        while (l1 <= l2){
            if (nums[l1] == 1)
                ++l1;
            else if (nums[l1] == 0){
                int t = nums[l0];
                nums[l0] = nums[l1];
                nums[l1] = t;
                ++l0;
                ++l1;
            }
            else {
                nums[l1] = nums[l2];
                nums[l2--] = 2;
            }
        }
    }

    //80
    public int removeDuplicates2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int l = 0, r = 1, cnt = 1;
        while (r < nums.length){
            if (nums[l] != nums[r]){
                nums[++l] = nums[r++];
                cnt = 1;
            }
            else if (cnt == 1){
                nums[++l] = nums[r++];
                cnt = 2;
            }
            else
                ++r;
        }
        return l+1;
    }

    //88
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null || m < 0 || n < 0)
            return;
        int i = m + n - 1;
        --m;
        --n;
        while (m >= 0 && n >= 0){
            nums1[i--] = nums1[m] > nums2[n] ? nums1[m--] : nums2[n--];
        }
        while (n >= 0){
            nums1[i--] = nums2[n--];
        }
    }

    //125
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0)
            return true;
        int l = 0, r = s.length() - 1;
        while (l < r){
            if (!Character.isLetterOrDigit(s.charAt(l)))
                ++l;
            else if (!Character.isLetterOrDigit(s.charAt(r)))
                --r;
            else if (Character.toLowerCase(s.charAt(l++)) != Character.toLowerCase(s.charAt(r--)))
                return false;
        }
        return true;
    }

    //167
    public int[] twoSum2(int[] numbers, int target) {
        int[] res = {-1,-1};
        if (numbers == null || numbers.length < 2)
            return res;
        int l = 0, r = numbers.length - 1;
        while (l < r){
            int sum = numbers[l] + numbers[r];
            if (sum < target)
                ++l;
            else if (sum > target)
                --r;
            else {
                res[0] = l+1;
                res[1] = r+1;
                break;
            }
        }
        return res;
    }

    //209
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int res = nums.length + 1, l = 0, sum = 0;
        for (int i = 0; i < nums.length; ++i){
            sum += nums[i];
            if (sum >= s) {
                while (l <= i && sum - nums[l] >= s){
                    sum -= nums[l++];
                }
                res = Math.min(res, i - l + 1);
            }
        }
        return res > nums.length ? 0 : res;
    }

    //259
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return 0;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; ++i){
            int l = i + 1, r = nums.length - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum < target) {
                    res += r - l; // -2 0 1 2 3. t = 4, (-2, 0, 3) < 4 then all 1,2 between 0 and 3 + 1 < 4. so before cut 1, must add r - l
                    ++l;
                }
                else {
                    --r;
                }
            }
        }
        return res;
    }

    //277
    boolean knows(int a, int b){return true;}

    public int findCelebrity(int n) {
        if (n <= 1)
            return 0;
        int l = 0, r = n - 1;
        while (l < r){
            if (knows(l, r))
                ++l;
            else
                --r;
        }
        for (int i = 0; i < n; ++i){
            if (i != l && (!knows(i, l) || knows(l, i)))
                return -1;
        }
        return l;
    }
}
