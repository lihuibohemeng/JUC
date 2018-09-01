package com.llh.test;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/1
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(){
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            public void run(){
                try {
                    System.out.println("子线程" + Thread.currentThread().getName()+"正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try{
            System.out.println("等待两个子线程执行");
            countDownLatch.await();
            System.out.println("2个执行完毕");
            System.out.println("继续执行主线程");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
