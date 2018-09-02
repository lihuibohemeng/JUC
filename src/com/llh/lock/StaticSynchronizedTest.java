package com.llh.lock;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/2
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
public class StaticSynchronizedTest {
    public static void main(String[] args)  {
        final InsertData1 insertData = new InsertData1();
        new Thread(){
            @Override
            public void run() {
                insertData.insert();
            }
        }.start();
        new Thread(){
            @Override
            public void run() {
                insertData.insert1();
            }
        }.start();
    }
}

class InsertData1 {
    public synchronized void insert(){
        System.out.println("执行insert");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert完毕");
    }

    public synchronized static void insert1() {
        System.out.println("执行insert1");
        System.out.println("执行insert1完毕");
    }
}
