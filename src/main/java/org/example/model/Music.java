package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a music track.
 */
public class Music implements Serializable, Cloneable, Playable {

    private String name;
    private Artist artist;
    private String labelName;
    private int albumId;
    private String lyrics;
    private List<String> music;
    private Genre genre;
    private int duration;
    private int playCount;
    private int id;
    private static int idCounter = 0;

    /**
     * Default constructor for music.
     */
    public Music() {
        this.name = "";
        this.artist = new Artist();
        this.labelName = "";
        this.albumId = -1;
        this.lyrics = "";
        this.music = new ArrayList<>();
        this.genre = Genre.ROCK;
        this.duration = 0;
        this.playCount = 0;
        this.id = idCounter++;
    }

    /**
     * Music constructor with parameters.
     * @param name Music name
     * @param artist Music artist
     * @param labelName Label name
     * @param albumId Album id
     * @param lyrics Music lyrics
     * @param music List of notes or chords
     * @param genre Music genre
     * @param duration Music duration
     * @param playCount Number of plays
     */
    public Music(String name, Artist artist, String labelName, int albumId, String lyrics, List<String> music, Genre genre,
                 int duration, int playCount) {
        this.name = name;
        this.artist = artist;
        this.labelName = labelName;
        this.albumId = albumId;
        this.lyrics = lyrics;
        this.music = new ArrayList<>(music);
        this.genre = genre;
        this.duration = duration;
        this.playCount = playCount;
        this.id = idCounter++;
    }

    /**
     * Copy constructor for music.
     * @param music Music to be copied
     */
    public Music(Music music) {
        this.name = music.getName();
        this.artist = music.getArtist();
        this.labelName = music.getLabelName();
        this.albumId = music.getAlbumId();
        this.lyrics = music.getLyrics();
        this.music = music.getMusic();
        this.genre = music.getGenre();
        this.duration = music.getDuration();
        this.playCount = music.getPlayCount();
        this.id = music.getId();
    }

    /**
     * Gets the music name.
     * @return Music name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the music name.
     * @param name New music name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the music artist.
     * @return Music artist
     */
    public Artist getArtist() {
        return this.artist.clone();
    }

    /**
     * Sets the music artist.
     * @param artist New artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Gets the label name.
     * @return Label name
     */
    public String getLabelName() {
        return this.labelName;
    }

    /**
     * Sets the label name.
     * @param labelName New label name
     */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /**
     * Gets the album id.
     * @return Album id
     */
    public int getAlbumId() {
        return albumId;
    }

    /**
     * Sets the album id.
     * @param albumId New album id
     */
    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    /**
     * Gets the music lyrics.
     * @return Music lyrics
     */
    public String getLyrics() {
        return this.lyrics;
    }

    /**
     * Sets the music lyrics.
     * @param lyrics New lyrics
     */
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    /**
     * Gets the list of notes or chords of the music.
     * @return List of notes/chords
     */
    public List<String> getMusic() {
        return new ArrayList<>(this.music);
    }

    /**
     * Sets the list of notes or chords of the music.
     * @param music New list of notes/chords
     */
    public void setMusic(List<String> music) {
        this.music = new ArrayList<>(music);
    }

    /**
     * Gets the music genre.
     * @return Music genre
     */
    public Genre getGenre() {
        return this.genre;
    }

    /**
     * Sets the music genre.
     * @param genre New music genre
     */
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /**
     * Gets the music duration.
     * @return Music duration
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Sets the music duration.
     * @param duration New music duration
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Gets the number of plays.
     * @return Number of plays
     */
    public int getPlayCount() {
        return this.playCount;
    }

    /**
     * Sets the number of plays.
     * @param playCount New number of plays
     */
    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    /**
     * Gets the music id.
     * @return Music id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the music id.
     * @param id New music id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the current value of the id counter.
     * @return idCounter value
     */
    public static int getIdCounter() {
        return Music.idCounter;
    }

    /**
     * Sets the value of the id counter.
     * @param idCounter New value for idCounter
     */
    public static void setIdCounter(int idCounter) {
        Music.idCounter = idCounter;
    }

    /**
     * Updates the play count of the music and the artist.
     */
    public void updatePlayCount() {
        this.playCount++;
        if (this.artist != null) {
            this.artist.incrementReproductions();
        }
    }

    /**
     * Returns a string representation of the music.
     * @return String with music information
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMusic ID: ").append(this.id)
                .append("\nMusic Name: ").append(this.name)
                .append("\nArtist Name: ").append(this.artist.getName())
                .append("\nLabel Name: ").append(this.labelName)
                .append("\nAlbum Id: ").append(this.albumId)
                .append("\nLyrics: ").append(this.lyrics)
                .append("\nMusic: ").append(this.music)
                .append("\nGenre: ").append(this.genre)
                .append("\nDuration: ").append(this.duration)
                .append("\nPlay Count: ").append(this.playCount)
                .append("\n---------------------------\n");

        return sb.toString();
    }

    /**
     * Checks if two musics are equal.
     * @param o Object to compare
     * @return true if equal, false otherwise
     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Music m = (Music) o;
        return this.name.equals(m.getName())
                && this.artist.equals(m.getArtist())
                && this.labelName.equals(m.getLabelName())
                && this.albumId == m.getAlbumId()
                && this.lyrics.equals(m.getLyrics())
                && this.music.equals(m.getMusic())
                && this.genre.equals(m.getGenre())
                && this.duration == m.getDuration()
                && this.playCount == m.getPlayCount()
                && this.id == m.getId();
    }

    /**
     * Creates a shallow copy of the music (clone).
     * @return New Music object (clone)
     */
    public Music clone() {
        return new Music(this);
    }

    /**
     * Plays the music for a user.
     * @param user User who is listening
     * @return String with the playback result
     * @throws SpotifumExecption If an error occurs while playing music
     */
    @Override
    public String play(User user) throws SpotifumExecption {
        user.registerListen(this);
        this.updatePlayCount();
        return "Now playing: " + this.getName() + " by " + this.getArtist().getName()
                + "\nYour current points: " + user.getPoints() + "\nLyrics:\n" + this.getLyrics();
    }

}