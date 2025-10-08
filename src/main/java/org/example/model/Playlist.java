package org.example.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
public abstract class Playlist {
    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
    protected List <Music> musics;
=======
public abstract class Playlist implements Serializable {
    private static final long serialVersionUID = 1L; 

    protected List<Music> musics;
>>>>>>> 3c282de (data and main)

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------
    public Playlist() {
        this.musics = new ArrayList<>();
    }

    public Playlist(List<Music> musics) {
        this.musics = musics != null ? new ArrayList<>(musics) : new ArrayList<>();
    }

    public Playlist(Playlist playlist) {
        this.musics = new ArrayList<>(playlist.musics);
    }

    //------------------------------------------------------------------------------------------------
    // Gets e Sets
    //------------------------------------------------------------------------------------------------
    public List<Music> getMusics() {
        return new ArrayList<>(musics); // Retorna uma cópia para evitar modificações externas
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics != null ? new ArrayList<>(musics) : new ArrayList<>();
    }
    
<<<<<<< HEAD
    //------------------------------------------------------------------------------------------------
    //  toString/equals/clone
    //------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Playlist{" +
                "musics=" + musics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Playlist)) return false;
        Playlist playlist = (Playlist) o;
        return musics.equals(playlist.musics);
    }

    @Override
    public abstract Playlist clone();

    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------
=======
    public static void savePlaylists(List<Playlist> playlists, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(playlists);
            System.out.println("Playlists salvas no arquivo: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao salvar playlists: " + e.getMessage());
        }
    }

    public static List<Playlist> loadPlaylists(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Playlist>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar playlists: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
>>>>>>> 3c282de (data and main)
}


