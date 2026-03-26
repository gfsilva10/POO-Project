package org.example.model;

import java.util.List;

/**
 * Class that represents a Favorites playlist.
 */
public class PlaylistFavs extends Playlist {

    /**
     * Default constructor for Favorites playlist.
     */
    public PlaylistFavs() {
        super();
    }

    /**
     * Constructor for Favorites playlist with name, musics, and public flag.
     * @param name Playlist name
     * @param musics List of musics
     * @param isPublic Whether the playlist is public
     */
    public PlaylistFavs(String name, List<Music> musics, boolean isPublic) {
        super(name, musics, isPublic);
    }

    /**
     * Copy constructor for Favorites playlist.
     * @param playlist Playlist to be copied
     */
    public PlaylistFavs(Playlist playlist) {
        super(playlist);
    }

    /**
     * Gets the playlist type.
     * @return Playlist type ("Favorites")
     */
    @Override
    public String getPlaylistType() {
        return "Favorites";
    }

    /**
     * Returns a string representation of the Favorites playlist.
     * @return String with playlist information
     */
    @Override
    public String toString() {
        return super.toString() + "Playlist Type: Favorites";
    }

    /**
     * Checks if two Favorites playlists are equal.
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
     * Creates a copy of the Favorites playlist.
     * @return New PlaylistFavs object (clone)
     */
    @Override
    public Playlist clone() {
        return new PlaylistFavs(this);
    }

}