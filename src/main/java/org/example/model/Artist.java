package org.example.model;

import java.io.Serializable;

/**
 * Class that represents a music artist.
 */
public class Artist implements Serializable {
    private String name;
    private int reproductions;

    /**
     * Default constructor for artist.
     */
    public Artist() {
        this.name = "";
        this.reproductions = 0;
    }

    /**
     * Artist constructor with name.
     * @param name Artist name
     */
    public Artist(String name) { 
        this.name = name;
        this.reproductions = 0;
    }

    /**
     * Artist constructor with name and number of reproductions.
     * @param name Artist name
     * @param reproductions Number of reproductions
     */
    public Artist(String name, int reproductions) {
        this.name = name;
        this.reproductions = reproductions;
    }

    /**
     * Copy constructor for artist.
     * @param artist Artist to be copied
     */
    public Artist(Artist artist) {
        this.name = artist.name;
        this.reproductions = artist.reproductions;
    }

    /**
     * Gets the artist's name.
     * @return Artist name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the artist's name.
     * @param name New artist name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the number of reproductions.
     * @return Number of reproductions
     */
    public int getReproductions() {
        return reproductions;
    }

    /**
     * Sets the number of reproductions.
     * @param reproductions New number of reproductions
     */
    public void setReproductions(int reproductions) {
        this.reproductions = reproductions;
    }

    /**
     * Increments the number of reproductions by 1.
     */
    public void incrementReproductions() {
        this.reproductions++;
    }

    /**
     * Returns a string representation of the artist.
     * @return String with artist information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nName : ").append(this.name);
        sb.append("\nReproductions : ").append(this.reproductions);
        sb.append("\n");   
        return sb.toString();
    }

    /**
     * Checks if two artists are equal.
     * @param o Object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return this.name.equals(artist.name) && this.reproductions == artist.reproductions;
    }

    /**
     * Creates a copy of the artist.
     * @return New Artist object (clone)
     */
    public Artist clone() {
        return new Artist(this);
    }

}
