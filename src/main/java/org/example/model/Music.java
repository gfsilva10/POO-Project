package org.example.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

<<<<<<< HEAD
public class Music {
    
    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
=======
public class Music implements Serializable {
    private static final long serialVersionUID = 1L;
>>>>>>> 3c282de (data and main)
    private String name;
    private String artist;
    private String labelName;
    private String lyricPoem;
    private List<String> music;
    private String genre;
    private int duration;

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------
    public Music() {
        this.name = "";
        this.artist = "";
        this.labelName = "";
        this.lyricPoem = "";
        this.music = new ArrayList<>(); // Inicializar como lista vazia
        this.genre = "";
        this.duration = 0;
    }

    public Music(String name, String artist, String labelName, String lyricPoem, List<String> music, String genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.labelName = labelName;
        this.lyricPoem = lyricPoem;
        this.music = music != null ? new ArrayList<>(music) : new ArrayList<>();
        this.genre = genre;
        this.duration = duration;
    }

    public Music(Music music) {
        this.name = music.name;
        this.artist = music.artist;
        this.labelName = music.labelName;
        this.lyricPoem = music.lyricPoem;
        this.music = new ArrayList<>(music.music);
        this.genre = music.genre;
        this.duration = music.duration;
    }

    //------------------------------------------------------------------------------------------------
    // Gets e Sets
    //------------------------------------------------------------------------------------------------
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLyricPoem() {
        return lyricPoem;
    }

    public void setLyricPoem(String lyricPoem) {
        this.lyricPoem = lyricPoem;
    }

    public List<String> getMusic() {
        return new ArrayList<>(music); // Retornar uma cópia para evitar modificações externas
    }

    public void setMusic(List<String> music) {
        this.music = music != null ? new ArrayList<>(music) : new ArrayList<>();
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    //------------------------------------------------------------------------------------------------
    //  toString/equals/clone
    //------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Music{" +
                "name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", labelName='" + labelName + '\'' +
                ", lyricPoem='" + lyricPoem + '\'' +
                ", music=" + music +
                ", genre='" + genre + '\'' +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Music)) return false;
        Music music1 = (Music) o;
        return duration == music1.duration &&
                Objects.equals(name, music1.name) &&
                Objects.equals(artist, music1.artist) &&
                Objects.equals(labelName, music1.labelName) &&
                Objects.equals(lyricPoem, music1.lyricPoem) &&
                Objects.equals(music, music1.music) &&
                Objects.equals(genre, music1.genre);
    }

    @Override
    public Music clone() {
        return new Music(this);
    }

<<<<<<< HEAD
    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------
}
=======

    public static void saveMusics(List<Music> musics, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(musics);
            System.out.println("Músicas salvas no arquivo: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao salvar músicas: " + e.getMessage());
        }
    }

    public static List<Music> loadMusics(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Music>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar músicas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}

>>>>>>> 3c282de (data and main)
