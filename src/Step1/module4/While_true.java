package Step1.module4;

public class While_true {
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();


    static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        t2.start();

    }

    private static class Thread1 extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (Lock1) {
                    System.out.println(1);
                }
                synchronized (Lock2) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }

    private static class Thread2 extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (Lock1) {
                    System.out.println(2);
                }
                synchronized (Lock2) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
    }
}
