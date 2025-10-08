package org.example.model;

import java.util.List;

public class Album{
   
    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
    private List <Music> musics;

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------

    public Album (){
        this.musics = null;
    }

    public Album(List<Music> musics) {
        this.musics = musics;
    }

    public Album(Album album) {
        this.musics = album.musics;
    }

    //------------------------------------------------------------------------------------------------
    // Gets e Sets
    //------------------------------------------------------------------------------------------------
    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    //------------------------------------------------------------------------------------------------
    //  toString/equals/clone
    //------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Album{" +
                "musics=" + musics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Album)) return false;
        Album album = (Album) o;
        return musics.equals(album.musics);
    }

    @Override
    public Album clone() {
        return new Album(this.musics);
    }

    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------
}
