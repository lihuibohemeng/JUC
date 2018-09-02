package com.llh.lock;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/2
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class SynchronizedTest {
    public static void main(String[] args) {
        final InsertData insertData = new InsertData();
        new Thread(){
            @Override
            public void run() {
                insertData.insert(Thread.currentThread());
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                insertData.insert(Thread.currentThread());
            }
        }.start();
    }

}
class InsertData{
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
//   public synchronized void insert(Thread thread){
    public  void insert(Thread thread){
        synchronized (InsertData.class) {

            for (int i = 0; i < 5; i++) {
                System.out.println(thread.getName() + "在插入数据" + i);
                arrayList.add(i);
            }
        }
    }
}
