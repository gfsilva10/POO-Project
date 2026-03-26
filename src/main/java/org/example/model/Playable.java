package org.example.model;

/**
 * Interface that represents a playable entity (music, album, etc).
 */
public interface Playable {
    /**
     * Plays the entity for a user.
     * @param user User who is listening
     * @return String with the playback result
     * @throws SpotifumExecption If an error occurs while playing
     */
    String play(User user) throws SpotifumExecption;
}