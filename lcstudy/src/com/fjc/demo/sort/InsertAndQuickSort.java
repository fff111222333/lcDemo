package com.fjc.demo.sort;

/**
 * author 凡
 * @author Administrator
 */
public class InsertAndQuickSort {
    public static void main(String[] args) {
        //验证插入排序
        int[] num1 = {4,16,20,18,8};
        int[] ints = insertSort(num1);
        for (int anInt : ints) {
            System.out.print(anInt + "　");
        }
        //验证快速排序



    }

    /**
     * 插入排序 插入排序的原理是默认前面的元素都是已经排序好的，然后从后面逐个读取插入到前面 排序好的合适的位置
     * @param array
     */
    public static int[] insertSort(int[] array){
        int length = array.length;
        for (int i=0;i<length;i++){
            int j = i;
            int key = array[i];
            while(j>0 && array[j-1] > key){
                array[j] = array[j-1];
                j--;
            }
            if(i != j){
               array[j] = key;
            }
        }
        return array;
    }
    /**
     * 快速排序
     */
    public static int[] quickSort(int[] array, int left, int right){
        if(left < right){
            //用待排序的数组第一个作为中枢
            int key =array[left];
            int i = left;
            for(int j = left+1;j<right;j++){
                if(key > array[j]){
                    swap(array,j,++i);
                }
            }
            // 把中枢方法哦指定位置
            array[left] = array[i];
            array[i] = key;
            quickSort(array,left,i-1);
            quickSort(array,i+1,right);
        }
        return array;
    }
    public static void swap(int[] array,int i,int j){
        if(i!=j){
            array[i] ^= array[j];
            array[j] ^= array[i];
            array[i] ^= array[j];
        }
    }


}
