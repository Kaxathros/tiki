import java.math.BigInteger;

// Trial division algorithm that uses sieve of primes to generate set of values to try divide n with.

public class A2 implements Algorithm {

  private static final BigInteger ZRO = new BigInteger("0");
  private static final BigInteger TWO = new BigInteger("2");

  private static BigInteger counter, n, sqrt;
  private static int bound;

  public BigInteger[] factor() {
    if (n.mod(TWO).equals(ZRO)) return new BigInteger[] {TWO, ZRO};
    primeSieve ps = new primeSieve(bound);
    int[] r = ps.sieve();
    int counter = 0;
    while (counter < (r.length-1)) {
      if (n.mod(BigInteger.valueOf(r[counter])).equals(ZRO)) return new BigInteger[] {BigInteger.valueOf(r[counter]), BigInteger.valueOf(counter)};
      counter++;
    }
    System.out.println("Sieve exhausted. Largest prime checked: " + r[(r.length-2)]);
    return new BigInteger[] {n, BigInteger.valueOf(counter)};
  }

  public A2(BigInteger n) {
    this.n = n;
    this.sqrt = n.sqrt();
    this.bound = 2147000000; // max value for sieve
    if (n.compareTo(BigInteger.valueOf(bound)) < 1)
      this.bound = n.intValue();
  }

}
