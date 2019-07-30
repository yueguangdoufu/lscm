package com.sanzu.test;

import java.util.UUID;

public class Uuid {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
