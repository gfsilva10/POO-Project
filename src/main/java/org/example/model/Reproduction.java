package org.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class that represents a music reproduction (play event).
 */
public class Reproduction implements Serializable {
    private Music music;
    private LocalDateTime date;

    /**
     * Default constructor for reproduction.
     * Initializes with a new Music and the current date/time.
     */
    public Reproduction() {
        this.music = new Music();
        this.date = LocalDateTime.now();
    }

    /**
     * Constructor for reproduction with music and date.
     * @param music Music played
     * @param date Date and time of reproduction
     */
    public Reproduction(Music music, LocalDateTime date) {
        this.music = music;
        this.date = date;
    }

    /**
     * Copy constructor for reproduction.
     * @param reproduction Reproduction to be copied
     */
    public Reproduction(Reproduction reproduction) {
        this.music = reproduction.music == null ? null : reproduction.music.clone();
        this.date = reproduction.date;
    }

    /**
     * Gets the music of the reproduction.
     * @return Music played
     */
    public Music getMusic() {
        return music == null ? null : music.clone();
    }

    /**
     * Sets the music of the reproduction.
     * @param music Music to set
     */
    public void setMusic(Music music) {
        this.music = music;
    }

    /**
     * Gets the date and time of the reproduction.
     * @return Date and time
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date and time of the reproduction.
     * @param date Date and time to set
     */
    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns a string representation of the reproduction.
     * @return String with reproduction information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Music: ").append(this.music);
        sb.append("\nDate: ").append(this.date);
        return sb.toString();
    }

    /**
     * Checks if two reproductions are equal.
     * @param o Object to compare
     * @return true if equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        Reproduction reproduction = (Reproduction) o;
        return this.music.equals(reproduction.getMusic()) && this.date.equals(reproduction.getDate());
    }

    /**
     * Creates a copy of the reproduction.
     * @return New Reproduction object (clone)
     */
    public Reproduction clone() {
        return new Reproduction(this);
    }
}
