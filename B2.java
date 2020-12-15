import java.math.BigInteger;

// Pollard's Rho algorithm using Brent's cycle detection method.

class B2 implements Algorithm {

  private final static BigInteger ZRO = new BigInteger("0");
  private final static BigInteger ONE = new BigInteger("1");
  private final static BigInteger TWO = new BigInteger("2");
  private final static BigInteger b = ONE, x_0 = TWO;

  private static BigInteger checkLoops, counter, n;

  public BigInteger[] factor() { // Slightly modified version of Brent's algorithm from his published article.
    BigInteger c1 = ZRO, d = ONE, product = ONE, range = ONE, steps = ZRO;
    BigInteger x = x_0.pow(2).add(b).mod(n);
    BigInteger y = x.pow(2).add(b).mod(n), x2 = x;
    while (d.equals(ONE)) {
      for (BigInteger i = BigInteger.ONE; i.compareTo(range) <= 0; i = i.add(ONE)) {
        y = y.pow(2).add(b).mod(n);
        counter = counter.add(ONE);
        product = y.subtract(x).abs().multiply(product).mod(n);
        c1 = c1.add(ONE);
        if (c1.mod(checkLoops).equals(ZRO)) {
          steps = steps.add(ONE);
          d = product.gcd(n);
          if (!d.equals(ONE)) break;
          x2 = x;
        }
      }
      if (!d.equals(ONE)) break;
      range = range.multiply(TWO);
      x = y;
      for (BigInteger i = BigInteger.ONE; i.compareTo(range) <= 0; i = i.add(ONE))
        y = y.pow(2).add(b).mod(n);
    }
    x = x2;
    y = x.pow(2).add(b).mod(n);
    if (d.equals(n) && !checkLoops.equals(ONE)) {
      while(true) {
        for (BigInteger i = BigInteger.ONE; i.compareTo(range) <= 0; i = i.add(ONE)) {
          y = y.pow(2).add(b).mod(n);
          d = y.subtract(x).abs().gcd(n);
          counter = counter.add(ONE);
          steps = steps.add(ONE);
          if (!d.equals(ONE)) break;
        }
        if (!d.equals(ONE)) break;
        range = range.multiply(TWO);
        x = y;
        for (BigInteger i = BigInteger.ONE; i.compareTo(range) <= 0; i = i.add(ONE))
          counter = counter.add(ONE);
          y = y.pow(2).add(b).mod(n);
      }
    }
    if (d.equals(n)) System.out.print("Try different generator value. ");
    System.out.println("GCD eval: " + steps);
    return new BigInteger[] {d, counter};
  }

  public B2(BigInteger n) {
    this.n = n;
    this.checkLoops = new BigInteger("10000"); // Frequency with which to check gcd computation. Every 10^5 iterations of combined product.
    this.counter = ZRO;
  }

}
