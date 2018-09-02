package com.llh;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/2
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
public class ThreadPool {
    public static ThreadPoolExecutor threadPoolExecutor =(ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        Task task = new Task();
        for(int i = 0 ;i < 10 ;i++){
            threadPoolExecutor.execute(task);
            System.out.println("线程池中线程数目："+threadPoolExecutor.getPoolSize()+"，队列中等待执行的任务数目："+ threadPoolExecutor.getQueue().size()+"，已执行玩别的任务数目："+threadPoolExecutor.getCompletedTaskCount());
        }
    }
}
 class Task extends Thread{
     public void run(){
        task();
     }

     public void task(){
         System.out.println("执行该任务");
         try{
            Thread.sleep(4000);
         }catch(Exception e){
             e.printStackTrace();
         }
         System.out.println("执行完成");
     }
        }