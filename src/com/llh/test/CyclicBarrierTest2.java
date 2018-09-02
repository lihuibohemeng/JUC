package com.llh.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/2
 * Time: 9:06
 * To change this template use File | Settings | File Templates.
 */

//  这段代码是用来验证CyclicBarrier的可重用性
public class CyclicBarrierTest2 {
    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        for (int i = 0; i < 4; i++) {
            new Writer(cyclicBarrier).start();
        }

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 4; i++) {
            new Writer(cyclicBarrier).start();
        }
    }
    static class Writer extends Thread{
        CyclicBarrier cyclicBarrier ;
        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier =  cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("Thread 正在执行");
            try{
                Thread.sleep(2500);// 模拟线程执行任务过程
                System.out.println("线程执行完毕，等待其他线程执行到cyclicBarrier状态");
                cyclicBarrier.await();
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("线程执行完成");
        }
    }
}
