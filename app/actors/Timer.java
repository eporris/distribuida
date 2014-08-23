package actors;

import java.util.concurrent.TimeUnit;

public class Timer {

    private long start;
    private long end;

    public void start() {
        this.start = System.nanoTime();
    }

    public void end() {
        this.end = System.nanoTime();
    }

    public long elapsedTimeMilliseconds() {
        return TimeUnit.MILLISECONDS.convert((end - start), TimeUnit.NANOSECONDS);
    }
}
