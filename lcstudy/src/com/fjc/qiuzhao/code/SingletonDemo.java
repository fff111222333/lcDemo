package com.fjc.qiuzhao.code;

/**
 * author 凡
 * 双检查单例模式
 * @author Administrator
 */
public class SingletonDemo {

    private static volatile SingletonDemo instance = null;
    private SingletonDemo(){

    }
    public static synchronized SingletonDemo getInstance(){
        if(instance == null){
            //外层的if判断：如果实例被创建直接return，不让线程再继续竞争锁
            //在没有创建实例时，多个线程已经进入if判断了
            //一个线程竞争到锁，其他线程阻塞等待
            synchronized (SingletonDemo.class) {
                //内层的if判断，目的是让竞争失败的锁如果再次竞争成功的话判断实例是否被创建，创建释放锁return，没有则创建
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }
}
