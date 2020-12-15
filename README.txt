The factor.java class runs the factoring algorithms. Syntax: java factor <1-7> <2-50,60,70,80,90,100>.

generatePrime() can be used to generate biprimes.
primes() is a containet that has a precomputed list of primes of varied size.
primeSieve() generates a sive of primes up to 2^32-1
primalityTest() is an implementation of the Rabin-Miller primality test

Factoring algorithms included are: Trial division A1 A2, Pollard Rho method B1 B2, and Pollard p-1 method C1 C2 C3.

Each algorithm has a factor() method that is the main execution code. Each algortihm is designed to factor
a biprime. If the intent is to use the trial division algorithm to find the exact prime decomposition of
a number n, then an implementation will have to be made around the factor() method.
