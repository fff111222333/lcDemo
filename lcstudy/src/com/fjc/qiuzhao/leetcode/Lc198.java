package com.fjc.qiuzhao.leetcode;
//
///**
// * author 凡
// * @author Administrator
// */
//public class Lc198 {
//
//    /**
//     * leetcode 198 打家劫舍
//     * @param args
//     */
//    public static void main(String[] args) {
//        int[] nums1 = {1,2,3,1};
//        int[] nums2 = {2,7,9,3,1};
//        System.out.println(rob(nums1));
//        System.out.println(rob(nums2));
//
//    }
//    /**
//     * 使用动态规划
//     */
//    public static int rob(int[] nums){
//        if(nums==null || nums.length==0){
//            return 0;
//        }
//        if(nums.length==1){
//            return nums[0];
//        }
//        if(nums.length==2){
//            return Math.max(nums[0],nums[1]);
//        }
//        int[] res = new int[nums.length];
//        res[0] = nums[0];
//        res[1] = Math.max(nums[0],nums[1]);
//        for(int i=2;i<nums.length;i++){
//            res[i] = Math.max(res[i-2]+nums[i],res[i-1]);
//        }
//        return res[nums.length-1];
//    }
//    // 这个方法考虑不对 比如{2,1,1,2}
////    public static int rob(int[] nums) {
////        if (nums == null){
////            return 0;
////        }
////        int res1=0;
////        int res2=0;
////        for(int i = 0;i<nums.length;i++){
////            if(i % 2 == 0){
////                res1 += nums[i];
////            }else{
////                res2 += nums[i];
////            }
////        }
////        return Math.max(res1, res2);
////    }
//}
// 本题为考试多行输入输出规范示例，无需提交，不计分。
import java.util.Scanner;
import java.util.*;

public class Lc198 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int[][] index = new int[k][2];
        int count=0;
        while(sc.hasNext()){
            index[count][0] = sc.nextInt();
            index[count][1] = sc.nextInt();
            if(count++ >= 2){
                break;
            }
        }
        System.out.println(getNum(n,m,k,index));

    }
     public static int getNum(int n,int m,int k,int[][] nums){
         int[][] num = new int[n][m];
         for(int i=0;i<n;i++){
             for(int j=0;j<m;j++){
                 num[i][j] =0;
             }
         }
         for(int i = 0;i < k; i++){
             num[nums[i][0]-1][nums[i][1]-1] = 1;
         }
         int res = 0;
         int[] row = new int[n];
         for(int e=0;e<n;e++){
             row[e] = 0;
         }
         int[] col = new int[m];
         for(int f=0;f<n;f++){
             row[f] = 0;
         }
         for(int h=0;h<n;h++){
             for(int j=0;j<m;j++){
                 if(num[h][j]==1){
                     row[h]++;
                 }
             }
         }
         for(int x=0;x<m;x++){
             for(int y=0;y<n;y++){
                 if(num[x][y]==1){
                     col[x]++;
                 }
             }
         }

         int[][] result = new int[n][m];
         for(int a=0;a<n;a++){
             for(int b=0;b<m;b++){
                 result[a][b] = row[a] + col[b] - 1;
             }
         }
         for(int c=0;c<n;c++){
             for(int d=0;d<m;d++){
                 if(res <= result[c][d]){
                     res = result[c][d];
                 }
             }
         }
         return getMax(col)+getMax(row)-1;
      }
     public static int getMax(int[] num){
         int max = num[0];
         for(int i = 1;i<num.length;i++){
             if(max <= num[i]){
                 max = num[i];
             }
         }
         return max;
     }
}
