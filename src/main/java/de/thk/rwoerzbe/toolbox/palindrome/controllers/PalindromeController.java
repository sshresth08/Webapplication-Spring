package de.thk.rwoerzbe.toolbox.palindrome.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import de.thk.rwoerzbe.toolbox.palindrome.services.PalindromeService;

/**
 * Controller class that serves requests to the palindrome checker
 */
@Controller
public class PalindromeController {

    @Autowired
    private PalindromeService palindromeService;

    /**
     * Serves the web page of the palindrome checker. Is triggered by a
     * <code>GET /palindrome</code> request
     * 
     * @return "palindrome" that hints to the palindrome.html
     */
    @GetMapping(value = "/palindrome")
    public String getPalindromePage() {
        return "palindrome";
    }

    /**
     * Serves a post request for a new palindrom check. Is triggered
     * by a <code>POST /palindrome</code> request with a body containing
     * the form data (<code>palindromecandidate</code>)
     * 
     * @param model Receives value for placehoder <code>result</code>
     * that reflects the check result
     * @param palindromecandidate The palindrom candidate to check
     * @return <code>"redirect:/palindrome"</code> such that the web page is 
     * rerendered with the check result
     */
    @PostMapping(value = "/palindrome", consumes = "application/x-www-form-urlencoded")
    public String checkCandidate(Model model, String palindromecandidate) {
        String result;
        try {
            result = Boolean.toString(palindromeService.isPalindrome(palindromecandidate));
        } catch (Exception e) {
            result = "undefined";
        }
        model.addAttribute("result", result);
        return "palindrome";
    }

}