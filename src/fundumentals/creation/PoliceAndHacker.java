package fundumentals.creation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PoliceAndHacker {
    public static final int MAX_PASS=99999999;
    public static void main(String[] args) {
        Random random = new Random();
        Vault vault = new Vault(random.nextInt(MAX_PASS));

        List<Thread> threads = new ArrayList<>();
        threads.add(new AscendingHackerThread(vault));
        threads.add(new DescendingHackerThread(vault));
        threads.add(new PoliceThread());

        threads.forEach(Thread::start);
    }

    private static class Vault {
        private int password;

        public Vault(int password) {
            this.password = password;
        }
        public boolean isCorrectPassword(int guessPassword) {
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.password == guessPassword;
        }
    }

    //Generic Hacker Thread
    private static abstract class HackerThread extends Thread {
        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public synchronized void start() {
            System.out.println("Starting Thread " + this.getName());
            super.start();
        }
    }

    private static class AscendingHackerThread extends HackerThread {
        public AscendingHackerThread(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 0; guess < MAX_PASS/2; guess++) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guess the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class DescendingHackerThread extends HackerThread {
        public DescendingHackerThread(Vault vault) {
            super(vault);
        }
        @Override
        public void run() {
            for (int guess = MAX_PASS; guess > MAX_PASS/2; guess--) {
                if (vault.isCorrectPassword(guess)) {
                    System.out.println(this.getName() + " guess the password " + guess);
                    System.exit(0);
                }
            }
        }
    }

    private static class PoliceThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
            System.out.println("Game Over For Hackers");
            System.exit(0);
        }
    }
}


