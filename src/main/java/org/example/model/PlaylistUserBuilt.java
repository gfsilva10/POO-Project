package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistUserBuilt extends Playlist implements Serializable {
    private static final long serialVersionUID = 1L;
    private int currentIndex; // Índice da música atualmente sendo reproduzida

    // Construtor padrão
    public PlaylistUserBuilt() {
        super();
        this.currentIndex = 0;
    }

    // Construtor com lista de músicas
    public PlaylistUserBuilt(List<Music> musics) {
        super(musics);
        this.currentIndex = 0;
    }

    // Avança para a próxima música
    public void next() {
        if (!musics.isEmpty()) {
            currentIndex = (currentIndex + 1) % musics.size(); // Avança circularmente
        }
    }

    // Retrocede para a música anterior
    public void previous() {
        if (!musics.isEmpty()) {
            currentIndex = (currentIndex - 1 + musics.size()) % musics.size(); // Retrocede circularmente
        }
    }

    // Obtém a música atual
    public Music getCurrentMusic() {
        if (musics.isEmpty()) {
            return null; // Retorna null se a playlist estiver vazia
        }
        return musics.get(currentIndex);
    }

    // Embaralha a ordem das músicas
    public void shuffle() {
        Collections.shuffle(musics);
        currentIndex = 0; // Reinicia o índice após embaralhar
    }

    // Define uma nova ordem para as músicas
    public void setOrder(List<Music> newOrder) {
        if (newOrder != null && newOrder.size() == musics.size() && musics.containsAll(newOrder)) {
            this.musics = new ArrayList<>(newOrder); // Define a nova ordem
            currentIndex = 0; // Reinicia o índice
        } else {
            throw new IllegalArgumentException("A nova ordem deve conter exatamente as mesmas músicas.");
        }
    }

    @Override
    public String toString() {
        return "PlaylistUserBuilt{" +
                "musics=" + musics +
                ", currentIndex=" + currentIndex +
                ", currentMusic=" + (getCurrentMusic() != null ? getCurrentMusic().getName() : "Nenhuma") +
                '}';
    }

    @Override
    public PlaylistUserBuilt clone() {
    // Cria uma nova instância de PlaylistUserBuilt com uma cópia da lista de músicas
    PlaylistUserBuilt cloned = new PlaylistUserBuilt(new ArrayList<>(this.musics));
    cloned.currentIndex = this.currentIndex; // Copia o índice atual
    return cloned;
}

}

   