package org.example.model;

<<<<<<< HEAD
public class PlaylistGenMax extends Playlist {
    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
=======
import java.io.Serializable;

public class PlaylistGenMax extends Playlist implements Serializable {
    private static final long serialVersionUID = 1L;
>>>>>>> 3c282de (data and main)
    private String genre; // Gênero musical da playlist
    private int maxDuration; // Duração máxima em minutos

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------
    public PlaylistGenMax() {
        super();
        this.genre = "";
        this.maxDuration = 0;
    }

    public PlaylistGenMax(String genre, int maxDuration) {
        super();
        this.genre = genre;
        this.maxDuration = maxDuration;
        //generatePlaylist(); // Gera a playlist com base no gênero e duração
    }

    public PlaylistGenMax(PlaylistGenMax playlist) {
        super(playlist); // Chama o construtor de cópia da superclasse Playlist
        this.genre = playlist.genre; // Copia o gênero
        this.maxDuration = playlist.maxDuration; // Copia a duração máxima
    }

    //------------------------------------------------------------------------------------------------
    // Gets e Sets
    //------------------------------------------------------------------------------------------------
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        //generatePlaylist(); // Atualiza a playlist ao alterar o gênero
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
        //generatePlaylist(); // Atualiza a playlist ao alterar a duração máxima
    }

    //------------------------------------------------------------------------------------------------
    //  toString/equals/clone
    //------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return "PlaylistGenMax{" +
                "genre='" + genre + '\'' +
                ", maxDuration=" + maxDuration +
                ", musics=" + musics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaylistGenMax)) return false;
        PlaylistGenMax that = (PlaylistGenMax) o;
        return maxDuration == that.maxDuration && genre.equals(that.genre) && musics.equals(that.musics);
    }

    @Override
    public Playlist clone() {
        return new PlaylistGenMax(this); // Retorna uma nova instância clonada
    }

    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------

        // Gera a playlist com base no gênero e duração máxima
/*     private void generatePlaylist() {
        this.musics.clear(); // Limpa a lista atual de músicas
        int currentDuration = 0;

        // Obtém músicas do banco de dados fictício com base no gênero
        List<Music> availableMusics = MusicDatabase.getMusicsByGenre(genre);

        for (Music music : availableMusics) {
            if (currentDuration + music.getDuration() <= maxDuration) {
                this.musics.add(music);
                currentDuration += music.getDuration();
            } else {
                break; // Para de adicionar músicas quando o limite é atingido
            }
        }
    } */

<<<<<<< HEAD
=======
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        //generatePlaylist(); // Atualiza a playlist ao alterar o gênero
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(int maxDuration) {
        this.maxDuration = maxDuration;
        //generatePlaylist(); // Atualiza a playlist ao alterar a duração máxima
    }

    @Override
    public String toString() {
        return "PlaylistGenMax{" +
                "genre='" + genre + '\'' +
                ", maxDuration=" + maxDuration +
                ", musics=" + musics +
                '}';
    }
>>>>>>> 3c282de (data and main)
}