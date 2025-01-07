package ge.tbc.testautomation.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuestCountParser {

    /**
     parses the productName to extract all possible guest counts (it handles plus, dash, slash,
     "or" words, commas, etc, on ka and en. Then checks if every extracted number is within [minRange to maxRange].
     */
    public static boolean parseGuestCount(String productName, int minRange, int maxRange) {
        // Normalize the string
        String normalized = productName.replaceAll("[–—]", "-");

        normalized = normalized
                .replaceAll("(?i)\\s*და\\s*", ";")
                .replaceAll("(?i)\\s*ან\\s*", ";")
                .replaceAll("(?i)\\s*or\\s*", ";")
                .replaceAll("(?i)\\s*and\\s*", ";")
                .replaceAll("\\s*,\\s*", ";")
                .replaceAll("\\.", ";");

        String[] chunks = normalized.split(";");
        List<Integer> allNumbers = new ArrayList<>();

        // Sometime i detected empty ones, so lets skip them
        for (String chunk : chunks) {
            chunk = chunk.trim();
            if (chunk.isEmpty()) {
                continue;
            }
            // and let it pass test number 2 too
            allNumbers.addAll(parseChunkTwoPass(chunk));
        }

        // If no numbers found => false
        if (allNumbers.isEmpty()) {
            return false;
        }

        // Check that all numbers are in [minRange..maxRange]
        for (int n : allNumbers) {
            if (n < minRange || n > maxRange) {
                return false;
            }
        }
        return true;
    }

    /**
     *      <<<<<<warning>>>>>>
     *      i have tested on all product of swoop and it worked, because products'
     *      names are in same style/format, but if totally different thing will detect,
     *      there can be occurred some errors, will need to rewrite some parts.
     *      <<<<<<///////>>>>>>

       in parseChunkTwoPass we use two-pass approach:
       we are looking for compound patterns (dash/plus/slash). If found, capture them,
       and we ignore any single integers in the same chunk.
       but, if no compound patterns are found, then parse single integers.

       Example:
       "67 ნაჭერი - სეტი 4-5 სტუმარზე"
       we see a dash pattern "4-5", so it sees this as [4, 5] and ignores "67".
       "10+2 სეტი" goes to sum, so its 12
       "4/6 სეთი" goes to [4,6]
        "15-17" goes to [15,16,17]
        "25" goes to [25] (only if no dash/plus/slash pattern is found)
     */
    private static List<Integer> parseChunkTwoPass(String chunk) {
        List<Integer> results = new ArrayList<>();

        // First pass: find any dash, plus, slash pattern
        Pattern multiPattern = Pattern.compile(
                "(\\d+\\s*\\+\\s*\\d+)|" +  // "10+2" or "10 + 2"
                        "(\\d+\\s*-\\s*\\d+)|" +  // "15-17"
                        "(\\d+\\s*/\\s*\\d+)"  // "4/6"
        );

        Matcher m1 = multiPattern.matcher(chunk);
        boolean foundMulti = false;

        while (m1.find()) {
            foundMulti = true;
            String found = m1.group();
            String cleaned = found.replaceAll("\\s+", "");

            if (cleaned.contains("+")) {
                // "5+2" goes to sum, so 7
                String[] parts = cleaned.split("\\+");
                int sum = 0;
                for (String p : parts) {
                    sum += Integer.parseInt(p);
                }
                results.add(sum);

            } else if (cleaned.contains("-")) {
                // "15-17" goes to 15,16,17
                String[] parts = cleaned.split("-");
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);
                int from = Math.min(start, end);
                int to = Math.max(start, end);
                for (int i = from; i <= to; i++) {
                    results.add(i);
                }

            } else if (cleaned.contains("/")) {
                // "4/6" goes to 4,6
                String[] parts = cleaned.split("/");
                int first = Integer.parseInt(parts[0]);
                int second = Integer.parseInt(parts[1]);
                results.add(first);
                results.add(second);
            }
        }

        // second pass: only if we did not find any dash/plus/slash pattern, lets parse single integers in that chunk.
        if (!foundMulti) {
            Matcher m2 = Pattern.compile("\\d+").matcher(chunk);
            while (m2.find()) {
                String single = m2.group();
                int val = Integer.parseInt(single);
                results.add(val);
            }
        }

        return results;
    }

//    public static void main(String[] args) {
//        // lets try when range is 2 to 15:
//        int minRange = 2;
//        int maxRange = 15;
//
//        String[] testNames = {
//                "მენიუ 25 ადამიანზე", // has 25 -> out of range
//                "მენიუ 15-17 ადამიანზე", // 15,16,17 -> out of range
//                "მენიუ 6 ადამიანზე", // single 6 -> in range
//                "სუშის მენიუ 5-6 ადამიანზე", // 5,6 -> in range
//                "მენიუ 10+2 სტუმარზე", // sum is 12 -> in range
//                "მენიუ 2, 4 ან 6 სტუმარზე", // 2,4,6 -> in range
//                "მენიუ 10 ან 15 სტუმარზე", // 10,15 -> in range
//                "XXL ნეკნების დაფა 6 სტუმარზე", // 6 -> in range
//                "ქორწილის მენიუ 120 სტუმარზე", // 120 -> out of range
//                "ატრიის და სუშის მენიუ", // we dont see digits -> false
//                "67 ნაჭერი - სეტი 4-5 სტუმარზე." // chunk has "4-5" so it is [4,5], we ignore 67 => in range
//        };
//
//        for (String name : testNames) {
//            boolean fits = parseGuestCount(name, minRange, maxRange);
//            System.out.printf("[%s] => %s%n", name, fits);
//        }
//    }
}
