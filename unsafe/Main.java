import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        System.load("/path/to/repository/unsafe/libtriple.so");

        long iterations = 100_000_000L;
        long start = System.currentTimeMillis();
        long sum = 0;

        for (long i = 0; i < iterations; i++) {
            NativeTriple triple = NativeTriple.create();
            sum += triple.subject().getId() + triple.predicate().getId() + triple.object().getId();
            triple.close();
        }

        long end = System.currentTimeMillis();
        double timeTaken = ((double) (end - start) * 1_000_000L)/iterations;

        System.out.println("Final sum is: " + sum + ", time taken per iteration in nano seconds: " + timeTaken);

    }
}