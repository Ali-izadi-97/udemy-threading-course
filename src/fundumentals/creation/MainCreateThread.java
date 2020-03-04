package fundumentals.creation;

public class MainCreateThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("we are in thread " + Thread.currentThread().getName());
            }
        });
        thread.setName("IN-THREAD");

        System.out.println("before thread " + Thread.currentThread().getName());
        thread.start();
        System.out.println("after thread " + Thread.currentThread().getName());

    }
}
