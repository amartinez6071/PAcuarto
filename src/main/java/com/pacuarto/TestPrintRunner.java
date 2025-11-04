package com.pacuarto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestPrintRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("\n==============================");
        System.out.println("🔥  RUNNER FUNCIONANDO!");
        System.out.println("==============================\n");
    }
}
