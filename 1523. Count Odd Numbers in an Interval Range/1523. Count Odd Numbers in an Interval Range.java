class Solution {
    public int countOdds(int low, int high) {
        // If both low and high are even, number of odds = (high - low) / 2
        // Else, add +1
        return (high - low) / 2 + ((low % 2 != 0 || high % 2 != 0) ? 1 : 0);
    }
}
