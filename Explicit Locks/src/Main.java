import reentrantlock.PingPong;

public class Main {
    public static void main(String[] args)  {

        PingPong pp = new PingPong();
        Thread t1 = new Thread(
                () -> {
                    try {
                        pp.ping();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Thread t2 = new Thread( () -> {
            try {
                pp.pong();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();
    }
}