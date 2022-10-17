package com.petushkov.webappcollections;


import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//public class Test implements Runnable {
//
//    int x = 0, y = 0;
//
//    int addX() {
//        x++;
//        return x;
//    }
//
//    int addY() {
//        y++;
//        return y;
//    }
//
//    @Override
//    public void run() {
//        for(int i = 0; i < 10; i++) {
//            System.out.println(Thread.currentThread().getName() + ": " + addX() + ": " + addY());
//        }
//    }
//
//    public static void main(String[] args) {
////        Test test1 = new Test();
////        Test test2 = new Test();
////        Thread thread1 = new Thread(test1);
////        Thread thread2 = new Thread(test2);
////        thread1.start();
////        thread2.start();
//
//        List<? super Number> test1 = new ArrayList<>();
//        Number ad = 1;
//        test1.add(ad);
//    }
//}

public class Test extends Thread {



    public void run() {
        for(int i = 1; i < 3; ++i) {
            System.out.println(i + ".. ");
        }
    }

    public static void main(String[] args) {
        System.out.println(new Date());
        System.out.println(Instant.now());
        Test test = new Test();
        test.run();


        List<? super Number> test1 = new ArrayList<>();
        Number ad = 1;
        test1.add(ad);
    }
}