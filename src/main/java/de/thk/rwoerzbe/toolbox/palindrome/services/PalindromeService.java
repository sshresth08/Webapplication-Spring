package de.thk.rwoerzbe.toolbox.palindrome.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.thk.rwoerzbe.toolbox.palindrome.entities.Palindrome;
import de.thk.rwoerzbe.toolbox.palindrome.repositories.PalindromeRepository;

/**
 * Service class that implements the palindrome checker
 */
@Service
public class PalindromeService {

    @Autowired
    private PalindromeRepository palindromeRepository;

    /**
     * Takes a non-null <code>candidate</code> string of length n and returns true
     * iff on the position k and n-k is the same character in the string. For
     * example,
     * 'noon' and 'racecar' are a palindromes.
     * Additionally, the palindrome candidate is stored along with the computed
     * palindrome property (true or false) for later analysis.
     * 
     * @param candidate A non-null candidate string to check.
     * @return true iff the <code>candidate</code> is a palindrome.
     * @throws IllegalArgumentException thrown iff candidate == null. No storage
     *                                  takes place then.
     */
    public boolean isPalindrome(String candidate) throws IllegalArgumentException {
        //TODO replace next line by proper implementation
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate string cannot be null.");
        }

        // Remove whitespace and convert the string to lowercase for a case-insensitive comparison.
        String cleanCandidate = candidate.replaceAll("\\s+", "").toLowerCase();

        int length = cleanCandidate.length();
        for (int i = 0; i < length / 2; i++) {
            if (cleanCandidate.charAt(i) != cleanCandidate.charAt(length - i - 1)) {
                return false;
            }
        }

         // Store the palindrome candidate along with the computed palindrome property.
         Palindrome palindrome = new Palindrome(candidate, true);
         palindromeRepository.save(palindrome);
 
         return true;

        //throw new UnsupportedOperationException();
    }

}