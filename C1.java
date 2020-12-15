import java.math.BigInteger;

// Pollard p-1 algorithm using k! sequence as a generator for m where gcd(a^m -1 mod n, n) returns factor p.

class C1 implements Algorithm {

  private final static BigInteger ZRO = new BigInteger("0");
  private final static BigInteger ONE = new BigInteger("1");
  private final static BigInteger TWO = new BigInteger("2");

  private static BigInteger counter, bound, checkLoops, n;
  private static BigInteger a = TWO, m = TWO;

  public BigInteger[] factor() {
    BigInteger a_2 = a, m_2 = m, d = ONE;
    while (m.compareTo(bound) <= 0) {
      counter = counter.add(ONE);
      a = a.modPow(m, n);
      if (m.mod(checkLoops).equals(ZRO)) {
        d = a.subtract(ONE).gcd(n);
        if (d.equals(n)) {
          counter = m;
          break;
        }
        if (!d.equals(ONE)) {
          System.out.println("M = " + m);
          return new BigInteger[] {d, counter};
        }
        a_2 = a; m_2 = m;
      }
      m = m.add(ONE);
    }
    if (d.equals(ONE)) d = n;
    a = a_2; m = m_2;
    if ((d.equals(n) && !checkLoops.equals(ONE)) || (m.equals(TWO) && d.equals(ONE))) {
      while(true) {
        counter = counter.add(ONE);
        a = a.modPow(m, n);
        d = a.subtract(ONE).gcd(n);
        m = m.add(ONE);
        if (!d.equals(ONE)) break;
      }
    }
    if (d.equals(n)) System.out.print("Try different value for 'a'. ");
    return new BigInteger[] {d, counter};
  }

  public C1(BigInteger n) {
    this.n = n;
    this.bound = n.sqrt();
    this.checkLoops = ONE; // Frequency with which to check gcd.
    this.counter = ZRO;
  }

}
