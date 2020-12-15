import java.math.BigInteger;

// Trial division algorithm using consecutive odd numbers to generate set of values to divide n with.

public class A1 implements Algorithm {

  private static final BigInteger ZRO = new BigInteger("0");
  private static final BigInteger TWO = new BigInteger("2");

  private static BigInteger counter, n, sqrt;

  public BigInteger[] factor() {
    if (n.mod(TWO).equals(ZRO)) return new BigInteger[] {TWO, ZRO};
    for (counter = new BigInteger("3"); counter.compareTo(sqrt) < 0; counter = counter.add(TWO))
      if (n.mod(counter).equals(ZRO)) return new BigInteger[] {counter, counter.divide(TWO)};
    return new BigInteger[] {n, counter.divide(TWO)};
  }

  public A1(BigInteger n) {
    this.n = n;
    this.sqrt = n.sqrt();
  }

}
