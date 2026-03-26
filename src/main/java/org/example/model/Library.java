package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a library of playlists and albums.
 */
public class Library implements Serializable {

    private List<Playlist> playlists;
    private List<Album> albums;

    /**
     * Default constructor for the library.
     */
    public Library() {
        playlists = new ArrayList<Playlist>();
        albums = new ArrayList<Album>();
    }

    /**
     * Library constructor with lists of playlists and albums.
     * @param playlists List of playlists
     * @param albums List of albums
     */
    public Library(List<Playlist> playlists, List<Album> albums) {
        this.playlists = new ArrayList<>(playlists);
        this.albums = new ArrayList<>(albums);
    }

    /**
     * Copy constructor for the library.
     * @param library Library to be copied
     */
    public Library(Library library) {
        this.playlists = library.getPlaylists();
        this.albums = library.getAlbums();
    }

    /**
     * Gets a copy of the playlist list.
     * @return List of playlists
     */
    public List<Playlist> getPlaylists() {
        return new ArrayList<>(this.playlists);
    }

    /**
     * Sets the playlist list.
     * @param playlists New playlist list
     */
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = new ArrayList<>(playlists);
    }

    /**
     * Gets a copy of the album list.
     * @return List of albums
     */
    public List<Album> getAlbums() {
        return new ArrayList<>(this.albums);
    }

    /**
     * Sets the album list.
     * @param albums New album list
     */
    public void setAlbums(List<Album> albums) {
        this.albums = new ArrayList<>(albums);
    }

    /**
     * Returns a string representation of the library.
     * @return String with library information
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Playlist playlist : playlists) {
            sb.append(playlist.toString()).append("\n");
        }
        for (Album album : albums) {
            sb.append(album.toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Checks if two libraries are equal.
     * @param o Object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Library library = (Library) o;
        return this.playlists.equals(library.getPlaylists()) && this.albums.equals(library.getAlbums());
    }

    /**
     * Creates a shallow copy of the library.
     * @return New Library object (clone)
     */
    @Override
    public Library clone() {
        return new Library(this);
    }

    /**
     * Creates a deep copy of the library.
     * @return New Library object (deep clone)
     */
    public Library deepClone() {
        List<Playlist> clonedPlaylists = new ArrayList<>();
        for (Playlist playlist : this.playlists) {
            clonedPlaylists.add(playlist.clone());
        }

        List<Album> clonedAlbums = new ArrayList<>();
        for (Album album : this.albums) {
            clonedAlbums.add(album.deepClone());
        }

        return new Library(clonedPlaylists, clonedAlbums);
    }

    /**
     * Adds a playlist to the library.
     * @param playlist Playlist to add
     */
    public void addPlaylist(Playlist playlist) {
        this.playlists.add(playlist);
    }

    /**
     * Adds an album to the library.
     * @param album Album to add
     */
    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    /**
     * Gets a playlist by its id.
     * @param playlistId Playlist id
     * @return Corresponding playlist or null if it does not exist
     */
    Playlist getPlaylistById(int playlistId) {
        for (Playlist p : this.playlists) {
            if (p.getIdPlaylist() == playlistId) return p;
        }
        return null;
    }

}
