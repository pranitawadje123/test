package com.example.test_service;

import java.util.stream.Collectors;

public class SubString {

    public static void main(String[] args) {
        printSubstring();
        checkSubstring();
        countOccurrences();
    }

    //Java program to extract a substring from a given string between two indices

    public static void printSubstring(){
        String input = "Pranita";
        int startIndex = 2;
        int endIndex = 4;

        String substring = input.substring(startIndex,endIndex);
        System.out.println(substring);
    }

    //check whether a given string contains a specific substring or not.
    public static void checkSubstring(){
        String input = "Pranita";
        String substring = "Pr";

        if(input.contains(substring)){
            System.out.println(true);
        }
        else{
            System.out.println(false);
        }
    }

    //counts how many times a specific substring appears in a given string.

    public static void countSubstring(){
        String input = "PranitaPrPr";
        String substring = "Pr";

        if(input.contains(substring)){
            System.out.println(true);
        }
        else{
            System.out.println(false);
        }
    }

        public static int countOccurrences(String str, String subStr) {
            int count = 0;
            int index = 0;
            System.out.println(str.indexOf(subStr, index));
            while ((index = str.indexOf(subStr, index)) != -1) {
                count++;
                index++;
            }
            return count;
        }

        public static void countOccurrences() {
            String str = "PranitaPrani";
            String subStr = "Prani";
            int count = countOccurrences(str, subStr);
            System.out.println("Occurrences of '" + subStr + "': " + count);
        }

    public static void countOccurrencesStreamAPI() {
        String str = "PranitaPrani";
        str.chars().mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c , Collectors.counting()));
    }

}
