package fundumentals.cordination;

public class Interrupt {
    public static void main(String[] args) {
        Thread thread = new Thread(new BlockThread());
        Thread counter = new Thread(new Counter());
        counter.start();
        thread.start();
        counter.interrupt();
    }

    private static class BlockThread implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("thread " +this.getClass().getSimpleName() + " started");
                Thread.sleep(2000);
                System.exit(0);
            } catch (InterruptedException e) {
                System.out.println("Exiting Thread");
            }
        }
    }

    private static class Counter implements Runnable{
        @Override
        public void run() {
            System.out.println("thread " +this.getClass().getSimpleName() + " started");
            for (int i = 1; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println(i + " second");
                } catch (InterruptedException e) {
                    System.out.println("Exiting counter");
                }
            }
        }
    }
}
