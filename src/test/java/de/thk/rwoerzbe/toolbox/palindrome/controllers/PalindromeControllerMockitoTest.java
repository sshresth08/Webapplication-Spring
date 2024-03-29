package de.thk.rwoerzbe.toolbox.palindrome.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import de.thk.rwoerzbe.toolbox.palindrome.services.PalindromeService;

@WebMvcTest(PalindromeController.class)
class PalindromeControllerMockitoTest {

    @MockBean
    PalindromeService palindromeService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void submitForm_ValidPalindrome_PageContainsResultTrue() throws Exception {
        // TODO implement test. Use comments as a guideline

        // 1 fixture setup: mock isPalindrome.("racecar") == true
        when(palindromeService.isPalindrome("racecar")).thenReturn(true);

        // 2 exercise subject under test (SUT)
        ResultActions resultActions= mockMvc.perform(post("/palindrome").contentType(MediaType.APPLICATION_FORM_URLENCODED).param( "palindromecandidate","racecar"));

        // 3 result verification
        // 3.1 Is HTTP status code equal to 200 (OK)?
        resultActions.andExpect(status().isOk());
        // 3.2 Check if "true" is being contained at the right place in the HTML
        resultActions.andExpect(xpath("/html/body/div/span").string("true"));

        verify(palindromeService).isPalindrome("racecar");
    }

    @Test
    void submitForm_Nonpalindrome_PageContainsResultFalse() throws Exception {
        // TODO implement test
        // 1 fixture setup: mock isPalindrome.("1234") == false
        when(palindromeService.isPalindrome("1234")).thenReturn(false);

        // 2 exercise subject under test (SUT)
        ResultActions resultActions= mockMvc.perform(post("/palindrome").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("palindromecandidate","1234"));

        // 3 result verification
        // 3.1 Is HTTP status code equal to 200 (OK)?
        resultActions.andExpect(status().isOk());
        // 3.2 Check if "true" is being contained at the right place in the HTML
        resultActions.andExpect(xpath("/html/body/div/span").string("false"));

        verify(palindromeService).isPalindrome("car");

    }

}
