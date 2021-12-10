package com.dev.iccaka;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        int p, q, n, phi, d = 0, e, i;
        double c;
        BigInteger message_back;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first prime number - p:");
        p = sc.nextInt();

        if (!isPrime(p)) {
            System.out.println("The number you've entered ( " + p + " ) is not prime!");
            return;
        }

        System.out.println("Enter second prime number - q:");
        q = sc.nextInt();

        if (!isPrime(q)) {
            System.out.println("The number you've entered ( " + q + " ) is not prime!");
            return;
        }

        n = p * q;
        System.out.println("The value of n: " + n);

        System.out.println("Enter the message to be encrypted ( must be smaller than 'n' --> " + n + " )");
        int message = sc.nextInt();

        if (message >= n) {
            return;
        }

        phi = (p - 1) * (q - 1);
        System.out.println("The value of f(n): " + phi);

        for (e = 2; e < phi; e++) {
            if (getGCD(e, phi) == 1) {
                break;
            }
        }
        System.out.println("The value of e: " + e);

        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * phi);

            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("The value of d: " + d);

        c = (Math.pow(message, e)) % n;
        System.out.println("The encrypted message is: " + c);

        BigInteger N = BigInteger.valueOf(n);
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        message_back = (C.pow(d)).mod(N);
        System.out.println("The decrypted message is: " + message_back);
    }

    private static boolean isPrime(int num) {
        if (num == 0 || num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        Random rand = new Random();

        for (int i = 0; i < 3; i++) {
            long r = Math.abs(rand.nextLong());
            long a = r % (num - 1) + 1;

            if (modPow(a, num - 1, num) != 1) {
                return false;
            }
        }

        return true;
    }

    private static long modPow(long a, long b, long c) {

        long result = 1;

        for (int i = 0; i < b; i++) {
            result *= a;
            result %= c;
        }

        return result % c;
    }

    private static int getGCD(int e, int phi) {
        if (e == 0) {
            return phi;
        } else {
            return getGCD(phi % e, e);
        }
    }
}