public class MultithreadedIntegralCalculator extends Thread implements IntegralCalculator {
    private final int threadCount;
    private final int threadRemainder;
    private final int n;
    private final double start;
    private final Function f;
    private final double h;
    private double sum = 0;


    public MultithreadedIntegralCalculator(int threadCount, int threadRemainder, double start, double end, int n, Function f) {
        this.threadCount = threadCount;
        this.threadRemainder = threadRemainder;
        this.start = start;
        this.f = f;
        this.n = n;
        this.h = (end - this.start) / this.n;
    }

    @Override
    public void run() {
        for (int i = threadRemainder; i <= n - 1; i += threadCount) {
            double xi = this.start + i * h;

            this.sum += this.f.calculate(xi + h/2) * h;
        }
    }

    @Override
    public double getSum() {
        return sum;
    }
}
