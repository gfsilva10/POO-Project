package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Abstract class that represents a playlist.
 */
public abstract class Playlist implements Serializable, Playable {

    private static int idCounter = 0;
    private int id;
    private String nome;
    private List<Music> musics;
    private boolean isPublic;

    /**
     * Default constructor for playlist.
     */
    public Playlist() {
        this.id = idCounter++; // Generates a unique ID
        this.nome = "";
        this.musics = new ArrayList<>();
        this.isPublic = false;
    }

    /**
     * Playlist constructor with name, musics and public flag.
     * @param name Playlist name
     * @param musics List of musics
     * @param isPublic Whether the playlist is public
     */
    public Playlist(String name, List<Music> musics, boolean isPublic) {
        this.id = idCounter++; // Generates a unique ID
        this.nome = name;
        this.musics = musics;
        this.isPublic = isPublic;
    }

    /**
     * Copy constructor for playlist.
     * @param p Playlist to be copied
     */
    public Playlist(Playlist p) {
        this.id = p.getIdPlaylist();
        this.nome = p.getNomePlaylist();
        this.musics = p.getMusicsPlaylist();
        this.isPublic = p.getIsPublic();
    }

    //------------------------------------------------------------------------------------------------------------

    /**
     * Gets the playlist id.
     * @return Playlist id
     */
    public int getIdPlaylist() {
        return this.id;
    }

    /**
     * Sets the playlist id.
     * @param id New playlist id
     */
    public void setIdPlaylist(int id) {
        this.id = id;
    }

    /**
     * Gets the playlist name.
     * @return Playlist name
     */
    public String getNomePlaylist() {
        return this.nome;
    }

    /**
     * Sets the playlist name.
     * @param nome New playlist name
     */
    public void setNomePlaylist(String nome) {
        this.nome = nome;
    }

    /**
     * Gets a copy of the playlist's music list.
     * @return List of musics
     */
    public List<Music> getMusicsPlaylist() {
        List<Music> copy = new ArrayList<>();
        for (Music m : this.musics) {
            copy.add(m.clone());
        }
        return copy;
    }

    /**
     * Sets the playlist's music list.
     * @param musics New music list
     */
    public void setMusicsPlaylist(List<Music> musics) {
        this.musics = new ArrayList<>(musics);
    }

    /**
     * Gets whether the playlist is public.
     * @return true if public, false otherwise
     */
    public boolean getIsPublic() {
        return this.isPublic;
    }

    /**
     * Sets whether the playlist is public.
     * @param isPublic true if public, false otherwise
     */
    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * Gets the current id counter value.
     * @return idCounter value
     */
    public static int getIdCounter() {
        return Playlist.idCounter;
    }

    /**
     * Sets the id counter value.
     * @param idCounter New value for idCounter
     */
    public static void setIdCounter(int idCounter) {
        Playlist.idCounter = idCounter;
    }

    /**
     * Adds a music to the playlist.
     * @param music Music to add
     */
    public void addMusic(Music music) {
        this.musics.add(music);
    }

    //------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the playlist.
     * @return String with playlist information
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist ID: ").append(this.id)
                .append("\nPlaylist Name: ").append(this.nome)
                .append("\nPublic: ").append(this.isPublic)
                .append("\nMusics: \n");
        for (Music music : this.musics) {
            sb.append(music.getName()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Checks if two playlists are equal.
     * @param o Object to compare
     * @return true if equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        Playlist p = (Playlist) o;
        return this.id == p.getIdPlaylist()
                && this.nome.equals(p.getNomePlaylist())
                && this.musics.equals(p.getMusicsPlaylist())
                && this.isPublic == p.getIsPublic();
    }

    /**
     * Creates a deep copy of the playlist.
     * @return New Playlist object (deep clone)
     */
    public abstract Playlist clone();

    /**
     * Gets the playlist type.
     * @return Playlist type
     */
    public abstract String getPlaylistType();

    /**
     * Plays all musics in the playlist for a user.
     * @param user User who is listening
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
     * Returns an unmodifiable iterator for the playlist's musics.
     * @return ListIterator of Playable
     */
    public ListIterator<Playable> playablesIterator() {
        List<Playable> playables = new ArrayList<>(this.musics);
        return Collections.unmodifiableList(playables).listIterator();
    }
    
}