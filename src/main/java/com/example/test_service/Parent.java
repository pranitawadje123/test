package com.example.test_service;

import java.util.HashMap;
import java.util.Map;

public class Parent {


    final String str1;
    static final String str2 = "STR+2";

    public int show(){
        System.out.println("Parent show method...");
        return 1;
    }

    Parent(String str1) {
        System.out.println("Parent class contructor called..");
        this.str1 = "Initialized value in parent constructor";
    }

    Parent() {
        this.str1 = "Initialized value in parent constructor";
        System.out.println("Parent class default contructor called..");
    }

    public void parentInstanceMethod() {
        System.out.println("Parent instance Method" + str1);
    }

    public static void parentStatic() {
        System.out.println("Parent static Method");
    }

    //Fibonacci Sequence
    public static void fibonacciSeries(int index) {
        //output = [1,2,3,5,8]
        int a = 1;
        int b = 2;
        int arr[] = new int[index];
        arr[0] = a;
        arr[1] = b;
        for (int i = 2; i <= index - 1; i++) {
            arr[i] = a + b;
            a = arr[i];
            b = arr[i - 1];
        }
        for (int j : arr) {
            System.out.println(j);
        }
    }

    //Factorial
    public static int factorial(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        return number * factorial(number - 1);
//        Parent p = new Child();
//        p.parentInstanceMethod();
//        Child c = new Parent();

//        c.childInstanceMethod();

    }


//    1. Find the Largest Element in an Array

    public static void largerElement(int arr[]) {

        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Largest number - >" + max);
    }

    //    2. Reverse an Array

    public static void reverseArray(int arr[]) {

    }


//    3. Find the Second Largest Element in an Array
//    4. Check if an Array is Sorted
//    5. Find the Missing Number in an Array
//    6. Merge Two Arrays
//    7. Rotate an Array
//    8. Count Occurrences of an Element in an Array
//    9. Find the Intersection of Two Arrays
//    10. Find the Union of Two Arrays

    public static void findLargestElement(int arr[]) {

        int max = arr[0];
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        System.out.println("Max elemt :" + max);

    }

    public static void findLongestSubstring(String str) {
        String longestSubstring = null;
        char[] strChar = str.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < strChar.length; i++) {
            if (!map.containsKey(strChar[i])) {
                map.put(strChar[i], i);
            } else {
                i = map.get(strChar[i]);
                map.clear();
            }
            if (map.size() > 0) {
                longestSubstring = map.keySet().toString();
            }
        }
        System.out.println("Longest substring : " + longestSubstring);
    }

    public static void checkAnagram() {

    }
    //count the repeating charecter with occurane

    public static String countStringChar(String input) {

        //aaabbbcc

        char maxChar = input.charAt(0);
        char currentChar = input.charAt(0);

        char maxCount = 1;
        char currentCount = 1;

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == currentChar) {
                currentCount++;
            }
            else{
                if(currentCount>maxCount){
                    maxChar = currentChar;
                    maxCount = currentCount;
                }

                currentCount =1;
                currentChar = input.charAt(i);
            }

        }
        System.out.println("maxChar + \" -> \" + maxCount;" + maxChar + " -> " + maxCount);
        return maxChar + " -> " + maxCount;

    }


    public static void findNumberOfOccurence(String input){

     String [] strArray =   input.split(" ");
//        char [] strArray = input.toCharArray();
//
        HashMap<String , Integer> map = new HashMap<>();

        for (int i =0 ; i < strArray.length; i ++){
            if(!map.containsKey(strArray[i])){
                map.put(strArray[i],1);
            }
            else{
                int count = map.get(strArray[i]) + 1;
                map.put(strArray[i] , count);
            }
        }

        for(Map.Entry<String, Integer> eachMap : map.entrySet() ){
            System.out.println("Word " + eachMap.getKey() + " occured " + eachMap.getValue() + "times");
        }

    }

    public static void main(String[] args) {
//        fibonacciSeries(10);
//        System.out.println(factorial(10));
//        int[] arr = new int[]{10, 5,20,40};
//        largerElement(arr);
//        findLargestElement(arr);
//        findLongestSubstring("abcdefabc");
//        countStringChar("abbac");
        findNumberOfOccurence("java java kiran kiran");
    }
}
