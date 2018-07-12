package com.company;

public class FFibonacci {


    public static long fibonacci(int n){
        if(n<2) {
            return 1;
        }else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    public static long fast_fibonacci(int n,Cache cache){
        if(cache.contains(n)){
            return cache.getResult(n);
        }else{
            if(n<2) {
                return 1;
            }else{
                long fib_result = fast_fibonacci(n-1,cache) + fast_fibonacci(n-2,cache);
                cache.add(fib_result,n);
                return fib_result;
            }
        }
    }

    private class Cache {

        long[] results;

        public Cache(int fibonacci_depth){
            results = new long[fibonacci_depth+1];
        }

        public boolean contains(int n){
            return results[n] != 0;
        }

        public long getResult(int n){
            return results[n];
        }

        public void add(long result,int cmpt){
            results[cmpt] = result;
        }


    }
}
