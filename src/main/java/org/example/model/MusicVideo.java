package org.example.model;

import java.util.List;

/**
 * Class that represents a music track with a music video.
 */
public class MusicVideo extends Music {
    private String videoUrl;

    /**
     * Constructor for music with video.
     * @param name Music name
     * @param artist Music artist
     * @param labelName Label name
     * @param albumId Album id
     * @param lyrics Music lyrics
     * @param music List of notes or chords
     * @param genre Music genre
     * @param duration Music duration
     * @param playCount Number of plays
     * @param videoUrl Music video URL
     */
    public MusicVideo(String name, Artist artist, String labelName, int albumId, String lyrics, List<String> music, Genre genre,
                      int duration, int playCount, String videoUrl) {
        super(name, artist, labelName, albumId, lyrics, music, genre, duration, playCount);
        this.videoUrl = videoUrl;
    }

    /**
     * Copy constructor for music with video.
     * @param m MusicVideo to be copied
     */
    public MusicVideo(MusicVideo m) {
        super(m); // calls the copy constructor of the superclass, which copies the ID correctly
    }

    /**
     * Gets the music video URL.
     * @return Music video URL
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * Sets the music video URL.
     * @param videoUrl New music video URL
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * Returns a string representation of the music with video.
     * @return String with music information and video URL
     */
    @Override
    public String toString() {
        return super.toString() + "[Music Video: " + videoUrl + "]\n";
    }

    /**
     * Creates a copy of the music with video.
     * @return New MusicVideo object (clone)
     */
    @Override
    public MusicVideo clone() {
        return new MusicVideo(this);
    }

    @Override
    public String play(User user) throws SpotifumExecption {
        return super.play(user) + "\nVideo URL: " + this.getVideoUrl();
    }

}
