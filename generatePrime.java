import java.math.BigInteger;
import java.util.Random;

// Generates two primes p and q. With (p ~ 0.5 q) p being about half the size of q. Uses BigInteger.probablePrime method.

class generatePrime {

  private static final BigInteger TWO = new BigInteger("2");

  private static BigInteger n, p, q;
  private static int digitsN;

  private static int digitsN(BigInteger n) {
    double x = Math.log(2) / Math.log(10);
    int digits = (int) (x * n.bitLength() + 1);
    if (BigInteger.TEN.pow(digits - 1).compareTo(n) > 0) {
      return digits - 1;
    }
    return digits;
  }

  private static double log(BigInteger n) {
    int shift = n.bitLength() - 64;
    if (shift < 200) return Math.log(n.doubleValue());
    return Math.log(n.shiftRight(shift).doubleValue()) + shift * Math.log(2.0);
  }

  public static void main(String[] args) {
    try {
      digitsN = Integer.parseInt(args[0]);
    }
    catch(Exception e) {
      System.out.println(e);
    }
    long startTime = System.currentTimeMillis();
    while(true) {
      Random rnd = new Random();
      BigInteger boundP = new BigInteger("10").pow(digitsN - 1).sqrt();
      int bitsP = (int) (log(boundP) / Math.log(2) + 1);
      p = BigInteger.probablePrime(bitsP, rnd);
      BigInteger boundQ = p.multiply(TWO);
      int bitsQ = (int) (log(boundQ) / Math.log(2) + 1);
      q = BigInteger.probablePrime(bitsQ, rnd);
      n = p.multiply(q);
      if (digitsN == digitsN(n))
        break;
    }
    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    if (elapsedTime < 1000)
      System.out.print("Biprime computed in " + elapsedTime + " millisecond(s). ");
    else {
      elapsedTime = Math.round((elapsedTime / 1000.0) + 0.5);
      if (elapsedTime < 60)
        System.out.print("Biprime computed in " + elapsedTime + " second(s). ");
      else {
        elapsedTime = Math.round((elapsedTime / 60.0) + 0.5);
        System.out.print("Biprime computed in " + elapsedTime + " minute(s). ");
      }
    }
    System.out.println("\np = " + p + "\nq = " + q + "\nN = " + n);
  }

}
