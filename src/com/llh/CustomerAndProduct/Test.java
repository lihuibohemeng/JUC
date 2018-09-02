package com.llh.CustomerAndProduct;

import java.util.PriorityQueue;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/2
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
//  利用非阻塞队列实现 生产者消费者模式
public class Test {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>();

    public static void main(String[] args) {
       Test test = new Test();
        Consumer consumer = test.new Consumer();
        Producer producer = test.new Producer();
        producer.start();
        consumer.start();
    }
    class Consumer extends Thread {
        public void run() {
            consumer();
        }

        private void consumer() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列为空");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll();
                    queue.notify();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                }
            }
        }
    }
        class Producer extends Thread{

            @Override
            public void run() {
                produce();
            }

            private void produce() {
                while(true){
                    synchronized (queue) {
                        while(queue.size() == queueSize){
                            try {
                                System.out.println("队列满，等待有空余空间");
                                queue.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                queue.notify();
                            }
                        }
                        queue.offer(1);        //每次插入一个元素
                        queue.notify();
                        System.out.println("向队列取中插入一个元素，队列剩余空间："+(queueSize-queue.size()));
                    }
                }
            }
    }
}

