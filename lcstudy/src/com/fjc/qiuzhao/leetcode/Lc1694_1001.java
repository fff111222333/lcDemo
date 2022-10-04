package com.fjc.qiuzhao.leetcode;

/**
 * author 凡
 */
public class Lc1694_1001 {
    public static void main(String[] args) {
        String s = "1-23-45 6";
        System.out.println(reformatNumber(s));

    }
    public static String reformatNumber(String number) {
        number = number.replaceAll(" ","");
        number = number.replaceAll("-","");
        int n = number.length();
        int m = 0;
        StringBuilder sb= new StringBuilder();
        StringBuilder sb1= new StringBuilder();
        if(n % 3 ==0){
            int count1 = 0;
            for(int i=0;i<n;i++){
                sb1.append(number.charAt(i));
                count1++;
                if( count1 % 3==0 && count1 < n){
                    sb1.append("-");
                }
            }
            return sb1.toString();
        } else {
            for(int i = 0;i < n;i++){
                m = n-i;
                if(m==2 || m==3){
                    for(int j=i;j<n;j++){
                        sb.append(number.charAt(j));
                    }
                    break;
                }
                if(m==4){
                    int count = 0;
                    for(int h=i;h<n;h++){
                        sb.append(number.charAt(h));
                        count ++;
                        if(count % 2 == 0 && count+i != n){
                            sb.append("-");
                        }
                    }
                    break;
                }
                sb.append(number.charAt(i));
                if((i+1) % 3 == 0){
                    sb.append("-");
                }
            }
            return sb.toString();
        }
    }
}
