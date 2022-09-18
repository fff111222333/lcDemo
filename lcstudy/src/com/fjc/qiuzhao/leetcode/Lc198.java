package com.fjc.qiuzhao.leetcode;

/**
 * author 凡
 * @author Administrator
 */
public class Lc198 {

    /**
     * leetcode 198 打家劫舍
     * @param args
     */
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,1};
        int[] nums2 = {2,7,9,3,1};
        System.out.println(rob(nums1));
        System.out.println(rob(nums2));

    }
    /**
     * 使用动态规划
     */
    public static int rob(int[] nums){
        if(nums==null || nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        if(nums.length==2){
            return Math.max(nums[0],nums[1]);
        }
        int[] res = new int[nums.length];
        res[0] = nums[0];
        res[1] = Math.max(nums[0],nums[1]);
        for(int i=2;i<nums.length;i++){
            res[i] = Math.max(res[i-2]+nums[i],res[i-1]);
        }
        return res[nums.length-1];
    }
    // 这个方法考虑不对 比如{2,1,1,2}
//    public static int rob(int[] nums) {
//        if (nums == null){
//            return 0;
//        }
//        int res1=0;
//        int res2=0;
//        for(int i = 0;i<nums.length;i++){
//            if(i % 2 == 0){
//                res1 += nums[i];
//            }else{
//                res2 += nums[i];
//            }
//        }
//        return Math.max(res1, res2);
//    }
}
