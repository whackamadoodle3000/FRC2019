package frc.team972.robot.loops;

public class Scheduler {

    CrashTrackingRunnable runnable;
    boolean threadRunning = false;
    Thread thread;
    long period;

    public Scheduler(CrashTrackingRunnable runnable_) {
        runnable = runnable_;
    }

    public synchronized void startPeriodic(double _period) {
        threadRunning = true;
        period = (long)(_period * 1000);
        thread = new Thread() {
            public void run() {
                while(true) {
                    if(threadRunning) {
                        runnable.run();
                        try {
                            Thread.sleep(period);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }

    //Pseudo "Thread Safe"
    public synchronized void stop() {
        System.out.println("Stopping Thread");
        threadRunning = false;
        thread.stop();
        thread = null;
    }
}