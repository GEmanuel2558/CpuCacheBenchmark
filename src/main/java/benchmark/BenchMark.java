package benchmark;

public class BenchMark {

    public final int NUM_THREADS_MAX = 4;
    public final static long ITERATIONS = 50_000_000L;

    private VolatileLongPadded[] paddedLongs;
    private VolatileLongUnPadded[] unPaddedLongs;

    public BenchMark() {
        paddedLongs = new VolatileLongPadded[NUM_THREADS_MAX];
        for (int i = 0; i < paddedLongs.length; i++) {
            paddedLongs[i] = new VolatileLongPadded();
        }
        unPaddedLongs = new VolatileLongUnPadded[NUM_THREADS_MAX];
        for (int i = 0; i < unPaddedLongs.length; i++) {
            unPaddedLongs[i] = new VolatileLongUnPadded();
        }
    }

    public void runBenchmark() throws InterruptedException {
        long beginBenchMark;
        Thread[] threads = new Thread[NUM_THREADS_MAX];
        for (int n = 1; n <= NUM_THREADS_MAX; n++) {
            beginBenchMark = System.currentTimeMillis();
            testPaddedObject(n, threads);
            testUnPaddedObject(n, threads);
            System.out.printf("\tTotal time # threads %d - T = %dms\n\n", n, System.currentTimeMillis() - beginBenchMark);
        }
    }

    private void testPaddedObject(int n, Thread[] threads) throws InterruptedException {
        for (int j = 0; j < threads.length; j++) {
            threads[j] = new Thread(createPaddedRunnable(j));
        }

        long beginThreadExecution = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long endThreadExecution = System.currentTimeMillis();
        System.out.printf("\tPadded # threads %d - T = %dms\n", n, endThreadExecution - beginThreadExecution);
    }

    private void testUnPaddedObject(int n, Thread[] threads) throws InterruptedException {
        for (int j = 0; j < threads.length; j++) {
            threads[j] = new Thread(createUnpaddedRunnable(j));
        }
        long beginThreadExecution = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }
        long endThreadExecution = System.currentTimeMillis();
        System.out.printf("\tUnPadded # threads %d - T = %dms\n", n, endThreadExecution - beginThreadExecution);
    }

    private Runnable createUnpaddedRunnable(final int k) {
        return () -> {
            long i = ITERATIONS + 1;
            while (0 != --i) {
                unPaddedLongs[k].value = i;
            }
        };
    }

    private Runnable createPaddedRunnable(final int k) {
        return () -> {
            long i = ITERATIONS + 1;
            while (0 != --i) {
                paddedLongs[k].value = i;
            }
        };
    }

}
