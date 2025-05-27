package com.example.test_service;

public class StringClass {

    public static void main(String[] args) {
        reverseString();
        checkIsPalindrom();
        countVowelsAndConsonants();
    }

    //✅ 1. Reverse a String
    public static void reverseString() {

        String input = "PRANITA";

        String reversedString = "";

        char[] charString = input.toCharArray();

        for (int i = charString.length - 1; i >= 0; i--) {
            reversedString = reversedString + charString[i];
        }
        System.out.println("reversedString : " + reversedString);

    }

    //✅ 2. Check if a String is a Palindrome
    public static void checkIsPalindrom() {

        String input = "MADAM";

        String reversedString = "";

        char[] charString = input.toCharArray();

        for (int i = charString.length - 1; i >= 0; i--) {
            reversedString = reversedString + charString[i];
        }

        if (input.equals(reversedString)) {
            System.out.println("String is palindrom");
        } else {
            System.out.println("String is not palindrom");
        }

    }
//✅ 3. Count Vowels and Consonants

    public static void countVowelsAndConsonants() {

        String input = "madam";

        char[] charString = input.toCharArray();
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i <= charString.length - 1; i++) {
            if (charString[i] == 'a' || charString[i] == 'e' || charString[i] == 'i' || charString[i] == 'o' || charString[i] == 'u') {
                vowelCount = vowelCount + 1;
            } else {
                consonantCount = consonantCount + 1;
            }
        }
        System.out.println("vowelCount : "+ vowelCount + ", consonantCount : "+ consonantCount);

    }


//✅ 4. Remove Duplicate Characters

    public static void removeDuplicates() {

        String input = "madam";
        String result ="";

        char[] charString = input.toCharArray();
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i <= charString.length - 1; i++) {
            if (charString[i] == 'a' || charString[i] == 'e' || charString[i] == 'i' || charString[i] == 'o' || charString[i] == 'u') {
                vowelCount = vowelCount + 1;
            } else {
                consonantCount = consonantCount + 1;
            }
        }
    }


//✅ 5. Find the Most Frequent Character
//✅ 6. Find the Longest Repeated Character Sequence
}



