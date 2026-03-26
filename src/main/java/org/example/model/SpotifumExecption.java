package org.example.model;

/**
 * Exception class for Spotifum-specific errors.
 */
public class SpotifumExecption extends Exception {

    /**
     * Default constructor for SpotifumExecption.
     */
    public SpotifumExecption() {
        super();
    }

    /**
     * Constructor for SpotifumExecption with a message.
     * @param message Error message
     */
    public SpotifumExecption(String message) {
        super(message);
    }  
}