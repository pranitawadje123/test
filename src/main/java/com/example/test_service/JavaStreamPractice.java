package com.example.test_service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JavaStreamPractice {
    public static void main(String[] args) {
//        countNumberOfLetters();
//        countNumberOfWords();
//        findDuplicateLettersWithCount();
//        findMissingNumber();
//        reverseEachWordInSentence();
        filterEvenNumbers();
//        int[] arr = {1, 3, 30, 203, 200};
//        filterNumber(arr);
//        findMax(arr);
//        List l = new ArrayList<>();
//        l.add(1);
//        l.add(10);
//        sum(l);
//        countSubstring("PRANITA");
//        isAnagram();
//        String originalPassword = "Original password";
//        originalPassword = "Hacked Password";
//        testString(originalPassword);
//        System.out.println(originalPassword);

//        String password = "SafePass123";
//        System.out.println("Before: " + password);
//
//        // Hacker tries to modify the password
//        password = "HackedPass456"; // Only changes reference, original value remains unchanged
//
//        System.out.println("After: " + password);
    }

    //Count the Number of letter in a String
    public static void countNumberOfLetters() {
        String input = "Pranita";
        Map<Character, Long> map = input.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("count Number Of Letters" + map);
    }

    //Count the Number of word in a String
    public static void countNumberOfWords() {
        String sentence = "Java is very nice";
        Long count = Arrays.stream(sentence.split(" ")).count();
        System.out.println("count Number Of Words " + count);
    }

    //find duplicate letter with their occurence
    public static void findDuplicateLettersWithCount() {
        String word = "Pranita";
        Map<Character, Long> map = word.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<Character, Long> duplicateLetter = map.entrySet().stream().filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("duplicate letter count " + duplicateLetter);
    }

    //find duplicate elements from an array
    public static void findDuplicateElement() {
        int[] arr = {1, 4, 4, 2, 6, 6};


    }

    //find the missing number from an array
    public static void findMissingNumber() {
        int[] arr = {1, 4, 3, 2, 6};

        List<Integer> list = IntStream.rangeClosed(1, 10).filter(w -> Arrays.stream(arr).noneMatch(x -> x == w)).boxed().collect(Collectors.toList());
        System.out.println(" find Missing Numbers " + list);
    }

    //Reverse Each Word in a Sentence
    public static void reverseEachWordInSentence() {
        String sentence = "Java is nice";
        String reversed = Arrays.stream(sentence.split(" ")).map(word -> new StringBuilder(word).reverse().toString()).collect(Collectors.joining(" "));
        System.out.println("reverse Each Word In Sentence " + reversed);
    }

    //Reverse whole Sentence
    public static void reverseSentence() {
        String sentence = "Java is nice";
        String reversed = Arrays.stream(sentence.split(" ")).map(word -> new StringBuilder(word).reverse().toString()).collect(Collectors.joining(" "));
        System.out.println("reverse Sentence " + reversed);
    }

    public static void testString(String originalPassword) {
        originalPassword = "Password Hacked";
        System.out.println(originalPassword);
    }

    //Given a list of integers, use the Stream API to filter out all numbers greater than 10 and print them.
    public static void filterNumber(int[] arr) {

        List<Integer> list = Arrays.stream(arr)
                .filter(element -> element > 10)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("NUmbers greated than 10 ->" + list);

    }

    //Given a list of integers, use the Stream API to find the maximum value.
    public static void findMax(int[] arr) {

        Optional<Integer> list = Arrays.stream(arr)
                .boxed()
                .max(Integer::compareTo);
        System.out.println("NUmbers greated than 10 ->" + list);

    }
//Given a list of integers, calculate the sum of all elements.

    public static void sum(List<Integer> list) {
        Integer sum = list.stream().mapToInt(Integer::intValue).sum();
        System.out.println("SUm ->" + sum);

    }

    //filter missing number
    public static void filtermissingNum(List<Integer> list) {
        int start = 0;
        int end = 10;
        List<Integer> missingNumbers = IntStream.rangeClosed(start, end)
                .filter(num -> !list.contains(num))
                .boxed()
                .collect(Collectors.toList());
    }

    public static void countSubstring(String input) {

        List<String> output = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j <= input.length(); j++) {
                String subString = input.substring(i, j);
                output.add(subString);
            }
        }
        System.out.println(output);

    }

    //isAnagram
    //input PRANITA
    //OUTPUT - false

    public static boolean isAnagram() {
        String input = "PRANITA";
        int left = 0;
        int right = input.length();

        while (left < right) {
            if (input.charAt(left) != input.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //Filter Even Numbers
    public static void filterEvenNumbers() {

        int start = 0;
        int end = 10;

        List<Integer> list = IntStream.rangeClosed(start, end).filter(element -> element % 2 == 0).boxed().toList();
        System.out.println("Even numbers are " + list);
    }

    //Uppercase Conversion

    public static void uppercaseConversion() {

       String input = "Pranita";
        input.chars().mapToObj(c -> Character.toUpperCase((char) c))
                .forEach(System.out::print); // Output: PRANITA
    }

    //Count Strings Starting with 'A'

    public static void countString() {

        String input = "Pranita";
        Map<Character, Long> charCount = input.chars().mapToObj(c -> Character.toUpperCase((char) c))
                .collect(Collectors.groupingBy(c->c, Collectors.counting()));

        System.out.println(charCount); // Optional: to see the result

    }
}

