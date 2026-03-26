package org.example.model;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that represents an explicit music track.
 */
public class MusicExplicit extends Music {

    /**
     * Constructor for explicit music with parameters.
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
    public MusicExplicit(String name, Artist artist, String labelName, int albumId, String lyrics, List<String> music, Genre genre,
                        int duration, int playCount) {
        super(name, artist, labelName, albumId, lyrics, music, genre, duration, playCount);
    }

    /**
     * Copy constructor for explicit music.
     * @param m Explicit music to be copied
     */
    public MusicExplicit(MusicExplicit m) {
        super(m); // calls the copy constructor of the superclass, which copies the ID correctly
    }

    /**
     * Returns a string representation of the explicit music.
     * @return String with music information and explicit content indication
     */
    @Override
    public String toString() {
        return super.toString() + "[Explicit Content]\n";
    }

    /**
     * Creates a copy of the explicit music.
     * @return New MusicExplicit object (clone)
     */
    @Override
    public MusicExplicit clone() {
        return new MusicExplicit(this);
    }

    /**
     * Filters explicit or non-explicit musics from a list.
     * @param musicList List of musics
     * @param isExplicit true to filter explicit, false for non-explicit
     * @return Filtered list of MusicExplicit
     */
    public static List<MusicExplicit> filterExplicit(List<Music> musicList, boolean isExplicit) {
        if (isExplicit) {
            return musicList.stream()
                .filter(music -> music instanceof MusicExplicit)
                .map(music -> (MusicExplicit) music)
                .collect(Collectors.toList());
        }
        return musicList.stream()
                .filter(music -> !(music instanceof MusicExplicit))
                .map(music -> (MusicExplicit) music)
                .collect(Collectors.toList());
    }

    @Override
    public String play(User user) throws SpotifumExecption {
        if(user.getAge() < 18) {
           return "This music is explicit and cannot be played for users under 18.";
        }
        return super.play(user);
    }

}
