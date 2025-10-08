package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

<<<<<<< HEAD:src/main/java/org/example/model/PlaylistFavs.Java
public class PlaylistFavs extends Playlist {
    //------------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------------
=======
public class PlaylistFavs extends Playlist implements Serializable {
    private static final long serialVersionUID = 1L;
>>>>>>> 3c282de (data and main):src/main/java/org/example/model/PlaylistFavs.java
    private User user;

    //------------------------------------------------------------------------------------------------
    // Construtores
    //------------------------------------------------------------------------------------------------
    public PlaylistFavs() {
        super();
        this.user = null; // Inicializa o utilizador como nulo
    }
    public PlaylistFavs(User user) {
        super(); // Chama o construtor da superclasse Playlist
        this.user = user; // Inicializa o utilizador
    }
    public PlaylistFavs(User user, List<Music> musics) {
        super(musics); // Chama o construtor da superclasse Playlist com a lista de músicas
        this.user = user; // Inicializa o utilizador
    }
    public PlaylistFavs(PlaylistFavs playlist) {
        super(playlist); // Chama o construtor da superclasse Playlist com a lista de músicas
        this.user = playlist.user != null ? playlist.user.clone() : null; // Clona o utilizador, se não for nulo
    }
    
    //------------------------------------------------------------------------------------------------
    // Gets e Sets
    //------------------------------------------------------------------------------------------------
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //------------------------------------------------------------------------------------------------
    //  toString/equals/clone
    //------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
    return "PlaylistFavs{" +
            "user=" + user.getName() + // Exibe o nome do utilizador
            ", musics=" + musics +
            '}';
    }
    
    @Override
    public boolean equals(Object o) {
    if (this == o) return true; // Verifica se é o mesmo objeto
    if (!(o instanceof PlaylistFavs)) return false; // Verifica se o objeto é do tipo PlaylistFavs
    PlaylistFavs that = (PlaylistFavs) o;
    return user.equals(that.user) && musics.equals(that.musics);
}

    @Override
    public Playlist clone() {
        // Cria uma nova instância de PlaylistFavs com uma cópia do utilizador e da lista de músicas
        return new PlaylistFavs(
            this.user != null ? this.user.clone() : null, // Clona o utilizador, se não for nulo
            new ArrayList<>(this.musics) // Cria uma cópia da lista de músicas
        );
    }
    /* 
    public boolean isGeneratedForPremiumUser() {
        return user != null && user.isPremium(); // Verifica se o utilizador é premium
    } */

    //------------------------------------------------------------------------------------------------
    // Outros métodos
    //------------------------------------------------------------------------------------------------
}