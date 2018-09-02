package com.llh;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: 刘莉慧
 * Date: 2018/9/2
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
       Task1 task = new Task1();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            System.out.println("task运行结果"+result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }
    }

class Task1 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程在进行计算");
        Thread.sleep(3000);
        int sum = 0;
        for(int i = 0 ; i < 100 ;i++){
            sum += i;
        }
        return sum;
    }
}
