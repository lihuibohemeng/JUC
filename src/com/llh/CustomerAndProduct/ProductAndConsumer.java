package com.llh.CustomerAndProduct;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/2
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public class ProductAndConsumer {
    LinkedBlockingQueue queue = new LinkedBlockingQueue(10);
    public static void main(String[] args) {
      ProductAndConsumer productAndConsumer = new ProductAndConsumer();
      Product product = productAndConsumer.new Product();
      Consumer consumer = productAndConsumer.new Consumer();
      product.start();
      consumer.start();
    }

    class Product extends Thread{
        @Override
        public void run() {
            product();
        }
        private void product(){
            while(true){
                try{
                  queue.put(1);
                    System.out.println("向队列中插入一个数据,队列size"+queue.size());
                }catch(Exception e){
               e.printStackTrace();
                }
            }
        }
    }
    class Consumer extends Thread{
        @Override
        public void run() {
            consumer();
        }
        private void consumer(){
            while(true){
                try{
                    queue.take();
                    System.out.println("向队列中拿出一个数据,队列size"+queue.size());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
