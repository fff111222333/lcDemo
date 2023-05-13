package com.study.exp;

/**
 * author 凡
 */
public class DemoSiSuo {
    /*
    死锁产生的条件：
    1. 互斥条件
    2. 请求与保持条件
    3. 不可剥夺条件
    4. 循环等待条件
     */
    //资源1
    public static Object res1 = new Object();
    //资源2
    public static Object res2 = new Object();
    public static void main(String[] args){
        new Thread(()->{
            synchronized (res1){
                System.out.println(Thread.currentThread() + "get res1");
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread()+"waiting get res2");
                synchronized (res2){
                    System.out.println(Thread.currentThread() + "get res2");
                }
            }
        },"线程1").start();

        new Thread(() -> {
            synchronized (res2) {
                System.out.println(Thread.currentThread() + "get res2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get res1");
                synchronized (res1) {
                    System.out.println(Thread.currentThread() + "get res1");
                }
            }
        }, "线程 2").start();

    }
}
