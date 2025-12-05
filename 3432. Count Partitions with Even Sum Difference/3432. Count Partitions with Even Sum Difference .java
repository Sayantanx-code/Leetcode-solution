class Solution {
    public int countPartitions(int[] nums) {
        int total = 0;
        for (int x : nums) {
            total += x;
        }

        // If total is odd -> no valid partitions
        if (total % 2 != 0) {
            return 0;
        }

        // If total even -> all (n - 1) partitions are valid
        return nums.length - 1;
    }
}
