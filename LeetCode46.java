public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        return help(nums);
    }
    
    private List<List<Integer>> help(int[] nums)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0) return result;
        
        dfs(nums, 0, result);
        return result;
    }
    
    private void dfs(int[] nums, int pos, List<List<Integer>> result)
    {
        if(pos == nums.length){
            List<Integer> oneSol = new ArrayList<Integer>();
            for(int num : nums){
                oneSol.add(num);
            }
            result.add(oneSol);
            return ;
        }
        
        for(int i = pos; i < nums.length; i++){
            swap(nums,i, pos);
            dfs(nums, pos+1, result);
            swap(nums,i, pos);
        }
    }
    
    private void swap(int [] nums, int i, int j)
    {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}