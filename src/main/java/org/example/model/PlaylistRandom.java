package org.example.model;

import java.util.List;

/**
 * Class that represents a Random playlist.
 */
public class PlaylistRandom extends Playlist {

    /**
     * Default constructor for Random playlist.
     */
    public PlaylistRandom() {
        super();
    }

    /**
     * Constructor for Random playlist with name, musics, and public flag.
     * @param name Playlist name
     * @param musics List of musics
     * @param isPublic Whether the playlist is public
     */
    public PlaylistRandom(String name, List<Music> musics, boolean isPublic) {
        super(name, musics, isPublic);
    } // only for testing

    /**
     * Copy constructor for Random playlist.
     * @param playlist Playlist to be copied
     */
    public PlaylistRandom(Playlist playlist) {
        super(playlist);
    }

    /**
     * Gets the playlist type.
     * @return Playlist type ("Random")
     */
    @Override
    public String getPlaylistType() {
        return "Random";
    }

    /**
     * Returns a string representation of the Random playlist.
     * @return String with playlist information
     */
    @Override
    public String toString() {
        return super.toString() + "Playlist Type: Random\n---------------------------------\n";
    }

    /**
     * Checks if two Random playlists are equal.
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
     * Creates a copy of the Random playlist.
     * @return New PlaylistRandom object (clone)
     */
    @Override
    public Playlist clone() {
        return new PlaylistRandom(this);
    }
}