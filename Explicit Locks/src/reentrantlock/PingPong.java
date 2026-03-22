package reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition pingTurn = lock.newCondition();
    private final Condition pongTurn = lock.newCondition();
    private boolean isPingTurn = true;

    public void ping() throws InterruptedException {
        for (int i=0;i<5;i++){
            lock.lock();

            try{
                while (!isPingTurn){
                    pingTurn.await();
                }
                System.out.println("Ping");
                isPingTurn = false;
                pongTurn.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public void pong() throws InterruptedException {
        for (int i=0;i<5;i++){
            lock.lock();

            try{
                while (isPingTurn){
                    pongTurn.await();
                }
                System.out.println("Pong");
                isPingTurn = true;
                pingTurn.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
