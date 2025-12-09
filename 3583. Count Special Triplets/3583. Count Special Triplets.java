import java.util.*;

class Solution {
    public int specialTriplets(int[] nums) {
        final int MOD = 1_000_000_007;
        int n = nums.length;

        Map<Integer, Integer> rightFreq = new HashMap<>();
        for (int num : nums) {
            rightFreq.put(num, rightFreq.getOrDefault(num, 0) + 1);
        }

        Map<Integer, Integer> leftFreq = new HashMap<>();
        long ans = 0;

        for (int j = 0; j < n; j++) {
            int x = nums[j];

            // remove nums[j] from right side before using
            rightFreq.put(x, rightFreq.get(x) - 1);

            int target = x * 2;

            long left = leftFreq.getOrDefault(target, 0);
            long right = rightFreq.getOrDefault(target, 0);

            ans = (ans + (left * right) % MOD) % MOD;

            // add nums[j] to the left side
            leftFreq.put(x, leftFreq.getOrDefault(x, 0) + 1);
        }

        return (int) ans;
    }
}
