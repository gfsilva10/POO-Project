package org.example.model;

import java.io.Serializable;
import java.util.List;

/**
 * Class that represents a User Built playlist.
 */
public class PlaylistUserBuilt extends Playlist implements Serializable {

    /**
     * Default constructor for User Built playlist.
     */
    public PlaylistUserBuilt() {
        super();
    }

    /**
     * Copy constructor for User Built playlist.
     * @param playlist Playlist to be copied
     */
    public PlaylistUserBuilt(Playlist playlist) {
        super(playlist);
    }

    /**
     * Constructor for User Built playlist with name, musics, and public flag.
     * @param name Playlist name
     * @param musics List of musics
     * @param isPublic Whether the playlist is public
     */
    public PlaylistUserBuilt(String name, List<Music> musics, boolean isPublic) {
        super(name, musics, isPublic);
    } // only for testing

    /**
     * Gets the playlist type.
     * @return Playlist type ("User Built")
     */
    @Override
    public String getPlaylistType() {
        return "User Built";
    }

    /**
     * Returns a string representation of the User Built playlist.
     * @return String with playlist information
     */
    @Override
    public String toString() {
        return super.toString() + "Playlist Type: User Built";
    }

    /**
     * Checks if two User Built playlists are equal.
     * @param obj Object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return true;
    }

    /**
     * Creates a copy of the User Built playlist.
     * @return New PlaylistUserBuilt object (clone)
     */
    @Override
    public Playlist clone() {
        return new PlaylistUserBuilt(this);
    }
}