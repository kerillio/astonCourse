package Step1.module4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Livelock_ex {

    private Lock lock1 = new ReentrantLock(true);
    private Lock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {
        Livelock_ex livelock = new Livelock_ex();
        new Thread(livelock::Thread1).start();
        new Thread(livelock::Thread2).start();
    }

    private void Thread1(){
        while (true) {
            try {
                lock1.tryLock(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("lock1 acquired, trying to acquire lock2");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            if (lock2.tryLock()) {
                System.out.println("lock2 acquired");
            } else {
                System.out.println("cannot acquire lock2, releasing lock1");
                lock1.unlock();
                continue;
            }

            System.out.println("Thread1 is done, interrupting");
            break;
        }
    }

    private void Thread2(){
        while (true) {
            try {
                lock2.tryLock(1000, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("lock2 acquired, trying to acquire lock1");
            if (lock1.tryLock()) {
                System.out.println("lock1 acquired");
            } else {
                System.out.println("cannot acquire lock1, releasing lock2");
                lock2.unlock();
                continue;
            }

            System.out.println("Thread2 is done, interrupting");
            break;
        }
    }

}
