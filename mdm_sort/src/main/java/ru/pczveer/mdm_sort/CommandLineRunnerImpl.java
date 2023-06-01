package ru.pczveer.mdm_sort;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    public static final String FILE_INPUT_NAME = "input.txt";
    public static final String FILE_OUTPUT_NAME = "output.txt";

    @Override
    public void run(String... args) {
        System.out.println("In CommandLineRunnerImpl ");
        Arrays.stream(args).forEach(System.out::println);

        Map<String, String> map = null;
        if (args.length == 0) {
            map = readMdmUnicodeClassic(FILE_INPUT_NAME);
        } else {
            map = readMdmUnicodeClassic(args[0]);
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        writeMdmUnicodeClassic(map);

    }

    public static void writeMdmUnicodeClassic(Map<String, String> map) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_OUTPUT_NAME))) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                writer.write(entry.getKey() + "-" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, String> readMdmUnicodeClassic(String fileName) {

        Map<String, String> map = new TreeMap<>();

        File file = new File(fileName);

        try (FileInputStream fis = new FileInputStream(file);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)
        ) {

            String str;
            while ((str = reader.readLine()) != null) {
                List<String> stringList = List.of(str.split("-", 2));
                if (stringList.size() != 2) {
                    System.out.println("Error:");
                    stringList.forEach(System.out::println);
                    System.out.println();
                } else {
                    map.put(stringList.get(0), stringList.get(1));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;

    }
}