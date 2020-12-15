import java.math.BigInteger;
import java.util.Random;

// The Rabin-Miller primality test algorithm. Uses a default value of 50 for security parameter. Can also be overwritten, Syntax: 'java primalityTest <number n> <optional t value>'.

public class primalityTest {

  private static final BigInteger ZRO = new BigInteger("0");
  private static final BigInteger ONE = new BigInteger("1");
  private static final BigInteger TWO = new BigInteger("2");

  private static BigInteger n;
  private static int k;

  public static boolean MillerRabin(BigInteger n, int t) { // Slightly modified version from pseudocode found in the Handbook of Applied Cryptography by Menezes. Fairly standard adaptation of the algortihm.
    if (n.mod(TWO).equals(ZRO)) {
      System.out.println(n + " is composite. Input was an even number.");
      System.exit(0);
    }
    System.out.println("Testing primality for " + n + " with t value " + k + ".");
    int s = n.subtract(ONE).getLowestSetBit();
    BigInteger r = n.subtract(ONE).shiftRight(s);
    for (int i = 1; i <= k; i++) {
      Random rand = new Random();
      BigInteger a;
      do {
        a = new BigInteger(n.subtract(BigInteger.ONE).bitLength(), rand);
      } while ((a.compareTo(n.subtract(TWO)) > 0) || (a.compareTo(TWO) < 0));
      BigInteger y = a.modPow(r, n);
      if (!y.equals(ONE) && !y.equals(n.subtract(ONE))) {
        int j = 1;
        while ((j <= s-1) && !y.equals(n.subtract(ONE))) {
          y = y.modPow(TWO, n);
          if (y.equals(ONE)) return false;
          j++;
        }
        if (!y.equals(n.subtract(ONE))) return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    if (args.length == 1) {
      try {
        n = new BigInteger(args[0]);
        k = 50;
      }
      catch(Exception e) {
        System.out.println(e);
        System.exit(0);
      }
    }
    if (args.length == 2) {
      try {
        n = new BigInteger(args[0]);
        k = Integer.parseInt(args[1]);
      }
      catch(Exception e) {
        System.out.println(e);
        System.exit(0);
      }

    }
    long startTime = System.currentTimeMillis();
    if (MillerRabin(n, k))
      System.out.print(n + " is probable prime. ");
    else
      System.out.print(n + " is composite. ");
    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    if (elapsedTime < 1000)
      System.out.println("Elapsed time: " + elapsedTime + " millisecond(s). ");
    else {
      elapsedTime = Math.round((elapsedTime / 1000.0) + 0.5);
      if (elapsedTime < 60)
        System.out.println("Elapsed time: " + elapsedTime + " second(s). ");
      else {
        elapsedTime = Math.round((elapsedTime / 60.0) + 0.5);
        System.out.println("Elapsed time: " + elapsedTime + " minute(s). ");
      }
    }
  }

}
