package com.baek.prime;

public class PrintPrimes {
    static final int numberOfPrimes = 1000;
    static final int linesPerPage = 50;
    static final int columns = 4;

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        int[] primes = primeGenerator.generatePrimes(numberOfPrimes);
        NumberPrinter numberPrinter = new NumberPrinter(linesPerPage, columns);
        numberPrinter.print(primes, numberOfPrimes);
    }
}

