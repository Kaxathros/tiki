import java.math.BigInteger;

// Generic class to run factoring algorithms. Syntax: 'java factor <1-7> <2-50,60,70,80,90,100,200,300>'.

class factor {

  private static final BigInteger TWO = new BigInteger("2");

  private static Algorithm f;
  private static String fAlgorithm;
  private static BigInteger n;
  private static primes pr = new primes();

  public static void main(String args[]) {
    try {
      fAlgorithm = args[0];
      n = pr.valueN[Integer.parseInt(args[1])];
    }
    catch(Exception e) {
      System.out.println(e);
    }
    switch (fAlgorithm) {
      case "1": {
        System.out.println("Trial division (algorithm A1)");
        f = new A1(n); break;
      }
      case "2": {
        System.out.println("Trial division (algorithm A2)");
        f = new A2(n); break;
      }
      case "3": {
        System.out.println("Pollard rho (algorithm B1)");
        f = new B1(n); break;
      }
      case "4": {
        System.out.println("Pollard rho (algorithm B2)");
        f = new B2(n); break;
      }
      case "5": {
        System.out.println("Pollard p-1 (algorithm C1)");
        f = new C1(n); break;
      }
      case "6": {
        System.out.println("Pollard p-1 (algorithm C2)");
        f = new C2(n); break;
      }
      case "7": {
        System.out.println("Pollard p-1 (algorithm C3)");
        f = new C3(n); break;
      }
      default: throw new IllegalArgumentException("[Invalid arguments. Valid input is <#algorithm: 1,2,3> <#digits in biprime: 3-40,50,60,70,80,90,100,200,300>]");
    }
    long startTime = System.currentTimeMillis();
    BigInteger[] result = f.factor();
    long stopTime = System.currentTimeMillis();
    long elapsedTime = stopTime - startTime;
    BigInteger c = result[1];
    if (elapsedTime < 1000)
      System.out.print(c + " cycles computed in " + elapsedTime + " millisecond(s). ");
    else {
      elapsedTime = Math.round((elapsedTime / 1000.0) + 0.5);
      if (elapsedTime < 60)
        System.out.print(c + " cycles computed in " + elapsedTime + " second(s). ");
      else {
        System.out.print(c + " cycles computed in " + (float) elapsedTime/60F + " minute(s). ");
      }
    }
    BigInteger p = result[0];
    if (!p.equals(n) && !p.equals(TWO))
      System.out.println("The divisors are: " + p + " and " + n.divide(p) + ".");
    else
      System.out.println("Failed to find factors for " + n + ".");
  }

}
