import java.math.BigInteger;

// Pollard's Rho algorithm using Floyd's cycle detection method.

class B1 implements Algorithm {

  private final static BigInteger ZRO = new BigInteger("0");
  private final static BigInteger ONE = new BigInteger("1");
  private final static BigInteger TWO = new BigInteger("2");
  private final static BigInteger b = ONE, x_0 = TWO;

  private static BigInteger checkLoops, counter, n;

  public BigInteger[] factor() { // Fairly standard implementation of the Pollard rho algorithm (Floyd).
    BigInteger d = ONE, product = ONE, x = x_0, y = x_0, x2 = x_0, y2 = x_0;
    while (d.equals(ONE)) {
      counter = counter.add(ONE);
      x = x.pow(2).add(b).mod(n);
      y = y.pow(2).add(b).mod(n);
      y = y.pow(2).add(b).mod(n);
      product = x.subtract(y).abs().multiply(product).mod(n);
      if (counter.mod(checkLoops).equals(ZRO)) {
        d = product.gcd(n);
        if (d.equals(n)) break;
        if (!d.equals(ONE)) {
          System.out.print("GCD eval. ");
          return new BigInteger[] {d, counter};
        }
        x2 = x; y2 = y;
      }
    }
    x = x2; y = y2;
    if (d.equals(n) && !checkLoops.equals(ONE)) {
      while (true) {
        counter = counter.add(ONE);
        x = x.pow(2).add(b).mod(n);
        y = y.pow(2).add(b).mod(n);
        y = y.pow(2).add(b).mod(n);
        d = x.subtract(y).abs().gcd(n);
        if (!d.equals(ONE)) break;
      }
    }
    if (d.equals(n)) System.out.print("Try different generator values. ");
    System.out.print("GCD eval. ");
    return new BigInteger[] {d, counter};
  }

  public B1(BigInteger n) {
    this.n = n;
    this.checkLoops = ONE; // Frequency with which to check gcd computation.
    this.counter = ZRO;
  }

}
