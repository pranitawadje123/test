package NICE_Practice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringPractice {

    public static void main(String[] args) {
//        findFirstNonRepeatingChar("swiss");
//        countEachCharacter("swiss");
//        countHighestRepeatedChar("swiss");
//        int[] arr = {13,11,10,9,15,15};
//        bubbleSort(arr);
//        countDuplicateUsingStream(arr);
//        findLongestSubstring();
        printLongestNonRepeativeSubstring();
    }
    //11. Find First Non-Repeated Character in a String
//
//Input:
//swiss
//Output:
//First non-repeated character: w

    public static void findFirstNonRepeatingChar(String input) {
        for (int i = 0; i < input.length(); i++) {
            boolean isRepeated = false;
            for (int j = 1; j < input.length(); j++) {
                if (i != j && input.charAt(i) == input.charAt(j)) {
                    isRepeated = true;
                    break;
                }
            }
            if (!isRepeated) {
                System.out.println("First non-repeated character: " + input.charAt(i));
                return;
            }
        }
    }


    //write a program to store the character with their count using hashmap

    public static void countEachCharacter(String input){

        Map<Character , Integer> map = new HashMap<>();
        for(int i =0 ; i<input.length();i++){
            map.put(input.charAt(i),map.getOrDefault(input.charAt(i),0)+1);
        }
        for(Map.Entry<Character,Integer>entryMap:map.entrySet()){
            System.out.println("Occurance of " + entryMap.getKey() + " is" + entryMap.getValue());
        }
    }

    //counts the total number of occurrences

    public static void countHighestRepeatedChar(String input){
        Map<Character, Integer> map = new HashMap<>();
        for(char c : input.toCharArray()){
            map.put(c, map.getOrDefault(c,0)+1);
        }
        int max = 0;
        char maxCountChar = '-';
        for(Map.Entry<Character,Integer> entryMap : map.entrySet()){
            if(max<entryMap.getValue()){
                max= entryMap.getValue();
                maxCountChar= entryMap.getKey();
            }
        }
        System.out.println("Max count of char : " + maxCountChar + " with count : " + max);

    }

    //Bubble sort program

    //input: 13,11,10,9,15
    //output: 9,10,11,13,15

    public static void bubbleSort(int[] arr){

        for(int i=0; i<arr.length; i++){
            for(int j=0 ; j<arr.length;j++){
                if(arr[i]<arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j]= temp;
                }
            }

        }
        for(int eachArr : arr){
            System.out.println(eachArr);
        }
    }

    //find second highest element using stream

    public static void findSecondHighestElement(int[] arr){

       int secondHighestElement= Arrays.stream(arr).sorted().skip(1).findFirst().orElseThrow(()->new IllegalArgumentException("Stream is empty"));

        System.out.println("secondHighestElement : "+ secondHighestElement);
    }

    //find missing number in an array

    public static void findMissingNumber(int[] arr){
        int n = arr.length -1;
        int totalSum = n*n+1/2;
        int currentSum =0;
        for (int num : arr) {
            currentSum += num;
        }
        int missingNumber = totalSum - currentSum;
        System.out.println("Missing number is: " + missingNumber);
    }

    public static void findMissingNumberUsingStream(int[] arr){

       int totalSum = IntStream.rangeClosed(1,10)
                .sum();

        int currentSum = Arrays.stream(arr).sum();
        int missingNumber = totalSum - currentSum;
        System.out.println("Missing number is: " + missingNumber);

    }

    public static void countDuplicateUsingStream(int[] arr) {

        Arrays.stream(arr).boxed().collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entryMap : map.entrySet()) {
            if (entryMap.getValue() > 1) {
                System.out.println("Print duplicates: " + entryMap.getKey() + "for " + entryMap.getValue());
            }
        }

    }
    public static void findLongestSubstring(){

        String input = "aaahhhhhhhbbbbbbbbbbbbbbbcccccccccccccccccccccccccccccccccc";
        int maxLenght = 1;
        int currentLenght = 1;
        char longestSubString = '-';

        for(int i=1;i<input.length();i++){
            if(input.charAt(i) == input.charAt(i-1)){
               currentLenght++;
            }
            else{
                currentLenght = 1;
            }

            if(currentLenght>maxLenght){
                maxLenght=currentLenght;
                longestSubString = input.charAt(i);
            }
        }
        System.out.println("longestSubString :" + longestSubString);
    }

    //find longest consucetive non repeated substring

    public static void printLongestNonRepeativeSubstring(){
        String input = "abcdabcdef";

        int start =0;
        int end =0;
        int maxlength =1;
        String longestSubstring = "";
        Set<Character> seen = new HashSet<>();
        while(end < input.length()){
            char c = input.charAt(end);
            if(!seen.contains(c)){
                seen.add(c);
                end++;
                if(end -start >maxlength){
                    maxlength = end-start;
                    longestSubstring = input.substring(start , end);
                }
            }
            else{
                seen.remove(c);
                start++;
            }

        }
        System.out.println("longestSubstring : "+ longestSubstring);

    }
    }





