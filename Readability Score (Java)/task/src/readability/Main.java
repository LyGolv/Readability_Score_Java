package readability;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    static List<Integer> list = List.of(6,7,8,9,10,11,12,13,14,15,16,17,18,22,23);

    public static void main(String[] args) throws IOException {
        Context c = getContext(args[0]);
        System.out.printf(c.toString());
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
        String choice = new Scanner(System.in).nextLine().toUpperCase();
        calcReadability(c, choice);

    }

    static Context getContext(String filepath) throws IOException {
        Context c = new Context();
        try(Stream<String> stream= Files.lines(Paths.get(filepath))) {
            String text = stream.collect(Collectors.joining()).toLowerCase();
            for (String s : text.split("[.?!]")) {
                c.characters += s.replaceAll("\\s", "").length() + 1;
                for (String word : s.trim().split("\\s")) {
                    String newWord = word.replaceAll("e,?$", "").replaceAll("[aeiouy]+", "a");
                    int nbSyllable = newWord.replaceAll("[^a]", "").length();
                    c.syllables += nbSyllable == 0 ? 1 : nbSyllable;
                    c.polysyllables += nbSyllable > 2 ? 1 : 0;
                }
                c.words += s.trim().split(" ").length;
                c.sentences += 1;
            }
        }
        return c;
    }

    static void calcReadability(Context c, String choice) {
        List<Integer> result = new ArrayList<>();
        if (choice.equals("ARI") || choice.equals("ALL")) {
            double ari = ReadabilityTest.ARI.calc(c);
            result.add(list.get((int)Math.ceil(ari) - 1));
            System.out.println("Automated Readability Index: " + ari + " (about " + result.get(0) + "-year-olds).");
        }
        if (choice.equals("FK") || choice.equals("ALL")) {
            double fk = ReadabilityTest.FK.calc(c);
            result.add(list.get((int)Math.ceil(fk) - 1));
            System.out.println("Flesch–Kincaid readability tests: " + fk + " (about " + result.get(1) + "-year-olds).");
        }
        if (choice.equals("SMOG") || choice.equals("ALL")) {
            double smog = ReadabilityTest.SMOG.calc(c);
            result.add(list.get((int)Math.ceil(smog) - 1));
            System.out.println("Simple Measure of Gobbledygook: " + smog + " (about " + result.get(2) + "-year-olds).");
        }
        if (choice.equals("CL") || choice.equals("ALL")) {
            double cl = ReadabilityTest.CL.calc(c);
            result.add(list.get((int)Math.ceil(cl) - 1));
            System.out.println("Coleman–Liau index: " + cl + " (about " + result.get(3) + "-year-olds).");
        }
        System.out.println("This text should be understood by " + result.stream().mapToDouble(i -> i).summaryStatistics().getAverage() + " year-olds.");
    }
}
