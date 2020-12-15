import java.math.BigInteger;

// Pollard p-1 algorithm using primes from sieve as a generator for m where gcd(a^m -1 mod n, n) returns factor p. Uses bounds B1 and B2.

class C3 implements Algorithm {

  private final static BigInteger ZRO = new BigInteger("0");
  private final static BigInteger ONE = new BigInteger("1");
  private final static BigInteger TWO = new BigInteger("2");

  private static BigInteger d = ONE, n, sqrt, a = TWO;
  private static int i = 0;
  private static int bound;

  public static double log(BigInteger n) {
    int shift = n.bitLength() - 64;
    if (shift < 200) return Math.log(n.doubleValue());
    return Math.log10(n.shiftRight(shift).doubleValue()) / Math.log10(2) + shift;
  }

  public BigInteger[] factor() {
    primeSieve s = new primeSieve(bound);
    int[] r = s.sieve();
    BigInteger counter = ZRO, d = ONE;
    System.out.println("B1 = " + r[10000000]);
    while (r[i] <= r[10000000]) {
      counter = counter.add(ONE);
      int l = (int) Math.floor(log(n) / Math.log(r[i]));
      a = a.modPow(BigInteger.valueOf(r[i]).pow(l), n);
      if (i < (r.length - 1)) i++;
      else break;
    }
    d = a.subtract(ONE).gcd(n);
    if (!d.equals(ONE)) {
      System.out.println("B = " + r[i]);
      return new BigInteger[] {d, counter};
    }
    if (d.equals(n)) {
      System.out.print("Try larger bound. ");
      return new BigInteger[] {d, counter};
    }
    System.out.println("B2 = " + bound);
    while (r[i] <= r[105075113]) {
        counter = counter.add(ONE);
        a = a.modPow(BigInteger.valueOf(r[i]), n);
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
    System.out.println("Bound B2 exhausted. Largest prime checked: " + r[i-1]);
    return new BigInteger[] {n, counter};
  }

  public C3(BigInteger n) {
    this.n = n;
    this.sqrt = n.sqrt();
    this.bound = 2147000000; // maximum value of sieve
  }

}
