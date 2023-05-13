package com.fjc.demo.example;

import sun.management.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTable;

import java.util.ArrayList;

/**
 * author 凡
 * @author Administrator
 */
public class Month3 {
    public static void main(String[] args) {
//        ArrayList<Integer> obj = new ArrayList<>();
//        obj.add(1);
        Month3 mon = new Month3();
        mon.showAll();



    }
    public void showAll() {
        int num,x,y,z;
        for(num=100;num<=999;num++){
            x=num/100;
            y=num%100/10;
            z=num%10;
            if(num==x*x*x+y*y*y+z*z*z){
                System.out.println(num);
            }
        }
    }
}
