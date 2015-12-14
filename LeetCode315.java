public class Solution {
    
    public List<Integer> countSmaller(int[] nums) {
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        int[] pos = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            pos[i]= Arrays.binarySearch(sortedNums, nums[i]);
        }
        
        int [] bit = new int[nums.length+1];
        Arrays.fill(bit,0);
        Integer [] ans = new Integer[nums.length];
        Arrays.fill(ans, 0);
        
        for(int i = pos.length-1; i >= 0; i--){
            ans[i] = query(bit, pos[i]);
            add(bit, pos[i]+1, 1);
        }
        
        return Arrays.asList(ans);
    }
    
    private static int query(int [] bit, int i){
        int ans = 0;
        for(; i > 0; i -= (i & -i)){
            ans += bit[i];
        }
        return ans;
    }
    
    private void add(int [] bit, int i, int val){
        for(; i < bit.length; i += (i & -i)){
            bit[i] += val;
        }
    }
}