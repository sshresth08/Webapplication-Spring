package de.thk.rwoerzbe.toolbox.palindrome.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.Transient;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.thk.rwoerzbe.toolbox.palindrome.services.PalindromeService;

@SpringBootTest
class PalindromeServiceTest {

    @Autowired
    private PalindromeService palindromeService;

    @Test
    void testIsPalindromeWithPalindrome(){
        assertTrue(palindromeService.isPalindrome("racecar"));
    }

    @Test
    void testIsPalindromeWithoutPalindrome(){
        assertFalse(palindromeService.isPalindrome("12345"));
    }

    @Test
    void testIsPalindromeWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            palindromeService.isPalindrome(null);
        });
    }

    @Test
    void testIsPalindromeWithEmptyString() {
        assertTrue(palindromeService.isPalindrome(""));
    }

    @Test
    void testIsPalindromeWithSingleCharacter() {
        assertTrue(palindromeService.isPalindrome("a"));
    }

    @Test
    void testIsPalindromeWithSpaces() {
        assertTrue(palindromeService.isPalindrome("amanaplanacanalPanama"));
    }

}
