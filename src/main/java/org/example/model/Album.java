package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Class that represents a music album.
 */
public class Album implements Serializable, Playable {

    private int id;
    private String nome;
    private List<Music> musics;
    private Artist artist;
    private static int idCounter = 0;

    /**
     * Private constructor for default initialization.
     */
    private Album() {
        this.id = -1; // Invalid ID
        this.nome = "";
        this.musics = new ArrayList<>();
        this.artist = new Artist(); // Initializes the artist
    }

    /**
     * Album constructor with name, musics, artist and id.
     * @param nome Album name
     * @param musics List of musics
     * @param artist Album artist
     * @param id Album identifier
     */
    public Album(String nome,List<Music> musics, Artist artist, int id) {
        this.id = id;
        this.nome = nome;
        this.musics = new ArrayList<>(musics);
        this.artist = artist;
    }

    /**
     * Album constructor with name, musics and artist.
     * @param nome Album name
     * @param musics List of musics
     * @param artist Album artist
     */
    public Album(String nome, List<Music> musics, Artist artist) {
        this.id = idCounter++;
        this.nome = nome;
        this.musics = new ArrayList<>(musics);
        this.artist = artist;
    }

    /**
     * Copy constructor for album.
     * @param album Album to be copied
     */
    public Album(Album album) {
        this.id = album.getId();
        this.nome = album.getNome();
        this.musics = album.getMusics();
        this.artist = album.getArtist();
    }

    //------------------------------------------------------------------------------------------------------------

    /**
     * Gets the album id.
     * @return album id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the album id.
     * @param id New album id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the album name.
     * @return album name
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Sets the album name.
     * @param nome New album name
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets a copy of the album's music list.
     * @return List of musics
     */
    public List<Music> getMusics() {
        List<Music> copy = new ArrayList<>();
        for (Music m : this.musics) {
            copy.add(m.clone());
        }
        return copy;
    }

    /**
     * Sets the album's music list.
     * @param musics New music list
     */
    public void setMusics(List<Music> musics) {
        this.musics = new ArrayList<>(musics);
    }

    /**
     * Gets the current id counter value.
     * @return idCounter value
     */
    public static int getIdCounter() {
        return Album.idCounter;
    }

    /**
     * Sets the id counter value.
     * @param idCounter New value for idCounter
     */
    public static void setIdCounter(int idCounter) {
        Album.idCounter = idCounter;
    }

    /**
     * Gets the album artist.
     * @return Album artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Sets the album artist.
     * @param artist New artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist.clone(); // Stores a copy of the artist
    }

    //------------------------------------------------------------------------------------------------------------

    /**
     * Returns the most listened song in the album.
     * @return Most listened music
     * @throws SpotifumExecption If there are no musics in the album
     */
    public Music mostListenedSong() throws SpotifumExecption {
        if (this.musics.isEmpty()) {
            throw new SpotifumExecption("No musics found in this album");
        }

        Music mostListened = null;
        int maxPlayCount = 0;

        for (Music music : this.musics) {
            if (music.getPlayCount() > maxPlayCount) {
                maxPlayCount = music.getPlayCount();
                mostListened = music;
            }
        }

        if (mostListened == null) {
            throw new SpotifumExecption("Unable to determine the most listened song in this album");
        }

        return mostListened; // Returns the music
    }

    //------------------------------------------------------------------------------------------------------------

    /**
     * Returns a String representation of the album.
     * @return String with album information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Album ID: ").append(this.id).append("\n");
        sb.append("Album Name: ").append(this.nome).append("\n");
        sb.append("Musics: \n");
        if (this.musics.isEmpty()) {
            sb.append(" - No musics available\n");
        } else {
            for (Music music : this.musics) {
                sb.append(" - ").append(music.getName()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Checks if two albums are equal.
     * @param o Object to compare
     * @return true if equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Album)) {
            return false;
        }
        Album album = (Album) o;
        return this.id == album.getId() && this.nome.equals(album.getNome()) && this.musics.equals(album.getMusics());
    }

    /**
     * Performs a shallow clone of the album (aggregation).
     * @return New Album object (shallow clone)
     */
    public Album clone() {
        return new Album(this);
    }

    /**
     * Performs a deep clone of the album (composition).
     * @return New Album object (deep clone)
     */
    public Album deepClone() {
        Album clonedAlbum = new Album(this.nome, new ArrayList<>(), this.artist.clone(), this.id);
        for (Music music : this.musics) {
            clonedAlbum.addMusic(music.clone());
        }
        return clonedAlbum;
    }

    /**
     * Adds a music to the album.
     * @param music Music to add
     */
    public void addMusic(Music music) { //only used in spotifum
        if (music != null) {
            this.musics.add(music);
        }
    }

    /**
     * Plays all musics in the album for a user.
     * @param user User listening
     * @return String with the playback result
     * @throws SpotifumExecption If an error occurs while playing music
     */
    @Override
    public String play(User user) throws SpotifumExecption {
        StringBuilder sb = new StringBuilder();
        for (Music music : this.musics) {
            sb.append(music.play(user)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns an unmodifiable iterator for the album's musics.
     * @return ListIterator of Playable
     */
    public ListIterator<Playable> playablesIterator() {
        List<Playable> playables = new ArrayList<>(this.musics);
        return Collections.unmodifiableList(playables).listIterator();
    }

}
