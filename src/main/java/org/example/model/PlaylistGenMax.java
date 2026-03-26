package org.example.model;

import java.util.List;

/**
 * Class that represents a General Max playlist.
 */
public class PlaylistGenMax extends Playlist {

    /**
     * Default constructor for General Max playlist.
     */
    public PlaylistGenMax() {
        super();
    }

    /**
     * Constructor for General Max playlist with name, musics, and public flag.
     * @param name Playlist name
     * @param musics List of musics
     * @param isPublic Whether the playlist is public
     */
    public PlaylistGenMax(String name, List<Music> musics, boolean isPublic) {
        super(name, musics, isPublic);
    } // only for testing

    /**
     * Copy constructor for General Max playlist.
     * @param playlist Playlist to be copied
     */
    public PlaylistGenMax(Playlist playlist) {
        super(playlist);
    }

    /**
     * Gets the playlist type.
     * @return Playlist type ("General Max")
     */
    @Override
    public String getPlaylistType() {
        return "General Max";
    }

    /**
     * Returns a string representation of the General Max playlist.
     * @return String with playlist information
     */
    @Override
    public String toString() {
        return super.toString() + "Playlist Type: General Max";
    }

    /**
     * Checks if two General Max playlists are equal.
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
     * Creates a copy of the General Max playlist.
     * @return New PlaylistGenMax object (clone)
     */
    @Override
    public Playlist clone() {
        return new PlaylistGenMax(this);
    }
}