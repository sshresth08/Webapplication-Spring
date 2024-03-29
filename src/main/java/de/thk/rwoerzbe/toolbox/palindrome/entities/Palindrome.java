package de.thk.rwoerzbe.toolbox.palindrome.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Represents a palindrome (candidate) as it will be stored in 
 * the database
 */
@Entity
public class Palindrome {

    /**
     * Contructs a Palindrome entity
     * @param palindromeCandidate The string that is checked for the being a palindrome
     * @param palindromeProperty Result of the palindrom checker test
     */
    public Palindrome(String palindromeCandidate, boolean palindromeProperty) {
        this.palindromeCandidate = palindromeCandidate;
        this.palindromeProperty = palindromeProperty;
    }

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    /**
     * @return the id of the entity
     */
    public UUID getId() {
        return id;
    }

    /** 
     * @param id the id of the entity
     */
    public void setId(UUID id) {
        this.id = id;
    }

    private String palindromeCandidate;

    /**
     * @return the candidate string
     */
    public String getPalindromeCandidate() {
        return palindromeCandidate;
    }

    /**
     * @param palindromeCandidate the candidate string
     */
    public void setPalindromeCandidate(String palindromeCandidate) {
        this.palindromeCandidate = palindromeCandidate;
    }

    private boolean palindromeProperty;

    /**
     * @return the palindrome property
     */
    public boolean isPalindromeProperty() {
        return palindromeProperty;
    }

    /**
     * @param palindromeProperty the palindrome property
     */
    public void setPalindromeProperty(boolean palindromeProperty) {
        this.palindromeProperty = palindromeProperty;
    }

}