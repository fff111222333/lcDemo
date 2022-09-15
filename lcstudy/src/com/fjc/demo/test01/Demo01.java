package com.fjc.demo.test01;

/**
 * author 凡
 * @author Administrator
 */
public class Demo01 {

    public static void main(String[] args) {
        int[] nums1 = {11,23,6,15,28,24};
        // 冒泡排序
        int[] ints = bubbleSort(nums1);
        for (int anInt : ints) {
            System.out.print(anInt + " ");
        }
        // 选择排序
        selectSort(nums1);

    }
    /**
     * 冒泡排序
     * @param nums 数组
     * @return int[]
     */
    public static int[] bubbleSort(int[] nums){
        int length = nums.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length ; j++) {
                if(nums[j] < nums[i]){
                    swap(nums,i,j);
                }
            }
        }
        return nums;
    }
    public static void swap(int[] nums, int i, int j){
        if (i != j){
            nums[i] ^= nums[j];
            nums[j] ^= nums[i];
            nums[i] ^= nums[j];
        }
    }

    /**
     * 排序选择
     * @param nums 数组
     */
    public static void selectSort(int[] nums){
        for (int i = 0; i < nums.length; i++) {
            int index = i;
            for (int j = i+1; j < nums.length; j++) {
                if(nums[index] > nums[j]){
                    index = j;
                }
            }
            if (i != index){
                swap(nums,i,index);
            }
        }
    }


}
