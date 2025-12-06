function countPartitions(nums: number[], k: number): number {
    const n = nums.length;
    const MOD = 1_000_000_007;

    // dp[i] = number of ways to partition nums[0..i-1]
    const dp = Array(n + 1).fill(0);
    const prefix = Array(n + 1).fill(0);

    dp[0] = 1;
    prefix[0] = 1;

    // Monotonic deques for maintaining max and min in window
    const maxQ: number[] = [];
    const minQ: number[] = [];

    let left = 0;

    for (let right = 0; right < n; right++) {

        // Maintain monotonic decreasing max deque
        while (maxQ.length && nums[maxQ[maxQ.length - 1]] <= nums[right]) {
            maxQ.pop();
        }
        maxQ.push(right);

        // Maintain monotonic increasing min deque
        while (minQ.length && nums[minQ[minQ.length - 1]] >= nums[right]) {
            minQ.pop();
        }
        minQ.push(right);

        // Shrink left pointer until window is valid: max - min ≤ k
        while (nums[maxQ[0]] - nums[minQ[0]] > k) {
            left++;

            // Remove indices that fall out of window
            if (maxQ[0] < left) maxQ.shift();
            if (minQ[0] < left) minQ.shift();
        }

        // dp[right+1] = sum(dp[left .. right])
        dp[right + 1] = (prefix[right] - (left > 0 ? prefix[left - 1] : 0) + MOD) % MOD;

        prefix[right + 1] = (prefix[right] + dp[right + 1]) % MOD;
    }

    return dp[n];
}
