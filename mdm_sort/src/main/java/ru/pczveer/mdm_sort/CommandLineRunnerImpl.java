package ru.pczveer.mdm_sort;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("In CommandLineRunnerImpl ");
        Arrays.stream(args).forEach(System.out::println);
    }
}