package de.thk.rwoerzbe.toolbox.palindrome.repositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import de.thk.rwoerzbe.toolbox.palindrome.entities.Palindrome;

/**
 * Standard interface for storing and receiving 
 * {@link de.thk.rwoerzbe.toolbox.palindrome.entities.Palindrome} entities
 */
public interface PalindromeRepository extends CrudRepository<Palindrome, UUID> {

}