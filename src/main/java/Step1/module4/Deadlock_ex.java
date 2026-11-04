package Step1.module4;

public class Deadlock_ex {

    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    static void main(String[] args) {

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        thread2.start();

    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
//            System.out.println("Thread 1 initializing");
            synchronized (Lock1) {
                System.out.println("Thread 1 is running");
                synchronized (Lock2) {
                    System.out.println("Thread 1 waiting Lock2");
                }
            }
        }
    }


    private static class Thread2 extends Thread {
        @Override
        public void run() {
//            System.out.println("Thread 2 initializing");
            synchronized (Lock2) {
                System.out.println("Thread 2 is running");
                synchronized (Lock1) {
                    System.out.println("Thread 2 waiting Lock1");
                }
            }
        }
    }

}
