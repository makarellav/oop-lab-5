public class SinglethreadedIntegralCalculator implements IntegralCalculator {
    private final int n;
    private final double start;
    private final Function f;
    private final double h;


    public SinglethreadedIntegralCalculator(double start, double end, int n, Function f) {
        this.start = start;
        this.f = f;
        this.n = n;
        this.h = (end - this.start) / this.n;
    }

    private double calculate() {
        var sum = 0.0;

        for (int i = 0; i <= n - 1; i++) {
            double xi = this.start + i * h;

            sum += this.f.calculate(xi + h/2) * h;
        }

        return sum;
    }

    @Override
    public double getSum() {
        return calculate();
    }
}
