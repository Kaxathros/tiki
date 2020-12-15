import java.math.BigInteger;

// Pollard p-1 algorithm using primes from sieve as a generator for m where gcd(a^m -1 mod n, n) returns factor p. Uses bound B.

class C2 implements Algorithm {

  private final static BigInteger ZRO = new BigInteger("0");
  private final static BigInteger ONE = new BigInteger("1");
  private final static BigInteger TWO = new BigInteger("2");

  private static BigInteger d = ONE, n, sqrt, a = TWO;
  private static int bound, i = 0;

  public static double log(BigInteger n) {
    int shift = n.bitLength() - 64;
    if (shift < 200) return Math.log(n.doubleValue());
    return Math.log10(n.shiftRight(shift).doubleValue()) / Math.log10(2) + shift;
  }

  public BigInteger[] factor() {

    primeSieve s = new primeSieve(bound);
    int[] r = s.sieve();
    BigInteger counter = ZRO, d = ONE;
    while (r[i] <= bound) {
        counter = counter.add(ONE);
        int l = (int) Math.floor(log(n) / Math.log(r[i]));
        a = a.modPow(BigInteger.valueOf(r[i]).pow(l), n);
        d = a.subtract(ONE).gcd(n);
        if (d.equals(n)) {
          System.out.print("Try larger bound. ");
          return new BigInteger[] {d, counter};
        }
        if (!d.equals(ONE)) {
          System.out.println("B = " + r[i]);
          return new BigInteger[] {d, counter};
        }
      if (i < (r.length - 1)) i++;
      else break;
    }
    System.out.println("Sieve exhausted. Largest prime checked: " + r[i-1]);

    return new BigInteger[] {n, counter};
  }

  public C2(BigInteger n) {
    this.n = n;
    this.sqrt = n.sqrt();
    this.bound = 2147000000; // maximum capacity of the sieve
    if (n.compareTo(BigInteger.valueOf(bound)) < 1)
      bound = n.intValue();
  }

}
