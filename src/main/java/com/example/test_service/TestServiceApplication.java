package com.example.test_service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@SpringBootApplication
public class TestServiceApplication {

    public static void main(String[] args) {
//        SpringApplication.run(TestServiceApplication.class, args);

//        int arr[] = {5,9,4,2,1};
//        printBublleSort(arr);
        printLetterOccurence();
        printNumberOfWords("Java is very easy");
        printWordCount();
        printMissingNumber();
        printSentanceLetterCount();
    }


    //bubble sort

    public static void printBublleSort(int[] arr) {

        //outter loop
        System.out.println(arr.length);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    //insertion sort

    public static void printMap() {
        List<String> words = List.of("Java", "Streams", "API");

        List<Integer> lengths = words.stream()
                .map(String::length)
                .toList();

        List<Character> characters = words.stream()
                .flatMap(word -> word.chars().mapToObj(c -> (char) c))
                .toList();

        System.out.println(lengths);
    }

    //🔹 Java Program to Group Letters by Occurrence Count

    public static void printLetterOcurrenceCount(String input) {

        char[] strChar = input.toCharArray();

        HashMap<Character, Integer> map = new HashMap<>();


        for (int i = 0; i < strChar.length; i++) {
            if (!map.containsKey(strChar[i])) {
                map.put(strChar[i], 1);
            } else {
                int count = map.get(strChar[i]);
                map.put(strChar[i], count + 1);
            }
        }

        for (Map.Entry<Character, Integer> each : map.entrySet()) {
            System.out.println(each.getKey() + " " + each.getValue());
        }


        Map<Character, Long> charCount = input.chars()



                .mapToObj(c -> (char) c) // Convert IntStream to Stream<Character>
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(charCount);
    }

    public static void printLetterOccurence() {
        String letter = "PRANITA";
        Map<Character, Long> charCount = letter.chars().mapToObj(w -> (char) w).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Letter count : " + charCount);
    }

    //1️⃣ Count the Number of Words in a String

    public static void printNumberOfWords(String input) {
        long wordCount = Arrays.stream(input.split(" ")).count();
        System.out.println("Number of word count : " + wordCount);
    }


    public static void printWordCount() {
        String sentence = "Java is very easy if we study well";
        long count = Arrays.stream(sentence.split(" ")).count();
        System.out.println("Practice word occurence : " + count);
    }

    public static void printMissingNumber() {

        int arr[] = {1, 4, 3};
        int start = 1;
        int end = 10;

        Set<Integer> intSet = IntStream.rangeClosed(start, end).filter(num -> Arrays.stream(arr).noneMatch(x -> x == num)).boxed().collect(Collectors.toSet());
        System.out.println(intSet);
    }

    public static void printSentanceLetterCount(){
        String sentence = "Java is very nice";
       long count = sentence.chars()
               .filter(ch -> ch != ' ') // Exclude spaces
               .count();
        System.out.println("printSentanceLetterCount " + count);
    }




}
