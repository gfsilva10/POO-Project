package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistRandom extends Playlist implements Serializable {
    private static final long serialVersionUID = 1L;

    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------

    
    //-------------------------------------------------------------------------------------------------
    // Construtores
    //-------------------------------------------------------------------------------------------------
    public PlaylistRandom() {
        super();
    }

    // Construtor com lista de músicas
    public PlaylistRandom(List<Music> musics) {
        super(musics);
        shufflePlaylist(); // Embaralha a ordem das músicas
    }
    
    // Sobrescreve o método setMusics para impedir modificações externas
    @Override
    public void setMusics(List<Music> musics) {
        throw new UnsupportedOperationException("A playlist aleatória não pode ser modificada.");
    }

    // Sobrescreve o método getMusics para retornar a lista na ordem atual
    @Override
    public List<Music> getMusics() {
        return new ArrayList<>(this.musics); // Retorna uma cópia da lista embaralhada
    }


    @Override
    public boolean equals(Object o) {
    if (this == o) return true; // Verifica se é o mesmo objeto
    if (!(o instanceof PlaylistRandom)) return false; // Verifica se o objeto é do tipo PlaylistRandom
    PlaylistRandom that = (PlaylistRandom) o;

    // Compara as listas de músicas, ignorando a ordem
    return this.musics.size() == that.musics.size() &&
           this.musics.containsAll(that.musics) &&
           that.musics.containsAll(this.musics);
}

    @Override
    public String toString() {
        return "PlaylistRandom{" +
                "musics=" + musics +
                '}';
    }

    @Override
    public PlaylistRandom clone() {
        // Cria uma nova instância de PlaylistRandom com uma cópia da lista de músicas
        return new PlaylistRandom(new ArrayList<>(this.musics));
    }

        // Método para embaralhar a ordem das músicas
    private void shufflePlaylist() {
            Collections.shuffle(this.musics); // Embaralha a lista de músicas
        }
}
