// Generates a sieve of primes up to maxint 2^32-1.

public class primeSieve {

  private static int bound;
  private static boolean[] isPrime;

  public static int[] sieve() {
    int sqrtBound = (int) Math.floor(Math.sqrt(bound));
    int counter = 1;

    long startTimer = System.currentTimeMillis();
    for (int i = 3; i <= sqrtBound; i+=2) {
      if (!isPrime[i])
        for (int j = i * i; j < bound; j += i) {
          isPrime[j] = true;
        }
    }
    for(int i = 3; i < bound; i+=2) {
      if (!isPrime[i])
        counter++;
    }
    int[] primes = new int[counter+1];
    primes[0] = 2;
    int j = 1;
    for(int i = 3; i < bound; i+=2) {
      if(!isPrime[i]) {
        primes[j] = i;
        j++;
      }
    }
    long stopTimer = System.currentTimeMillis();
    long elapsedTime = stopTimer - startTimer;
    System.out.print("Sieve computed in ");
    if (elapsedTime < 1000)
      System.out.println(elapsedTime + " millisecond(s). Size is " + counter + ".");
    else {
      elapsedTime = Math.round((elapsedTime / 1000.0) + 0.5);
      if (elapsedTime < 60)
        System.out.println(elapsedTime + " second(s). Size is " + counter + ".");
      else {
        elapsedTime = Math.round((elapsedTime / 60.0) + 0.5);
        System.out.println(elapsedTime + " minute(s). Size is " + counter + ".");
      }
    }
    return primes;
  }

  public primeSieve(int bound) {
    this.bound = bound;
    this.isPrime = new boolean[bound + 1];
  }

}
