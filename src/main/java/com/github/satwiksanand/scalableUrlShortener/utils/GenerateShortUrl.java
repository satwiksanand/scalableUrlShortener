package com.github.satwiksanand.scalableUrlShortener.utils;

import java.security.SecureRandom;

public class GenerateShortUrl {
    //here i am going to try implementing all the various ways i can think of implementing the short url.
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();
    public static String generateRandomShortUrl(String seed, int num){
        StringBuilder sb = new StringBuilder(num);
        random.setSeed((seed + System.nanoTime()).hashCode());
        for(int i = 0; i < num; ++i){
            int ind = random.nextInt(BASE62.length());
            sb.append(BASE62.charAt(ind));
        }
        return sb.toString();
    }
}
