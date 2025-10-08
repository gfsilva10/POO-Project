package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.model.Music;
import org.example.model.Plan;
import org.example.model.PlanFree;
import org.example.model.PlanPremiumBase;
import org.example.model.PlanPremiumTop;
import org.example.model.Playlist;
import org.example.model.PlaylistFavs;
import org.example.model.PlaylistRandom;
import org.example.model.PlaylistUserBuilt;
import org.example.model.User;

public class App {
private static final String USERS_FILE = "data/users.ser";
private static final String MUSIC_FILE = "data/musics.ser";
private static final String PLAYLIST_FILE = "data/playlists.ser";

    public static void main(String[] args) {
        // Inicializar listas
        List<User> users = User.loadUsers(USERS_FILE);
        List<Music> musics = Music.loadMusics(MUSIC_FILE);
        List<Playlist> playlists = Playlist.loadPlaylists(PLAYLIST_FILE);

        if (users == null) users = new ArrayList<>();
        if (musics == null) musics = new ArrayList<>();
        if (playlists == null) playlists = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- SpotifUM ---");
            System.out.println("1. Criar Utilizador");
            System.out.println("2. Adicionar Música");
            System.out.println("3. Criar Playlist");
            System.out.println("4. Reproduzir Conteúdo");
            System.out.println("5. Carregar Dados");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha
            switch (option) {
                case 1 -> {
                    System.out.print("Nome: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String address = scanner.nextLine();
                    System.out.print("Plano (Free, PremiumBase, PremiumTop): ");
                    String planType = scanner.nextLine();

                    Plan plan = switch (planType) {
                        case "Free" -> new PlanFree();
                        case "PremiumBase" -> new PlanPremiumBase();
                        case "PremiumTop" -> new PlanPremiumTop();
                        default -> {
                            System.out.println("Plano inválido! Usando plano Free.");
                            yield new PlanFree();
                        }
                    };

                    users.add(new User(name, email, address, plan));
                    User.saveUsers(users, USERS_FILE);
                    System.out.println("Utilizador criado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Nome da música: ");
                    String name = scanner.nextLine();
                    System.out.print("Artista: ");
                    String artist = scanner.nextLine();
                    System.out.print("Gênero: ");
                    String genre = scanner.nextLine();
                    System.out.print("Duração (em segundos): ");
                    int duration = scanner.nextInt();

                    musics.add(new Music(name, artist, "", "", new ArrayList<>(), genre, duration));
                    Music.saveMusics(musics, MUSIC_FILE);
                    System.out.println("Música adicionada com sucesso!");
                }
                case 3 -> {
                    System.out.println("Criando uma playlist...");
                    System.out.println("1. Playlist Aleatória");
                    System.out.println("2. Playlist Personalizada");
                    System.out.println("3. Playlist de Favoritos");
                    int playlistOption = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha

                    switch (playlistOption) {
                        case 1 -> {
                            Playlist randomPlaylist = new PlaylistRandom(musics);
                            playlists.add(randomPlaylist);
                            Playlist.savePlaylists(playlists, PLAYLIST_FILE);
                            System.out.println("Playlist aleatória criada!");
                        }
                        case 2 -> {
                            PlaylistUserBuilt userPlaylist = new PlaylistUserBuilt(musics);
                            playlists.add(userPlaylist);
                            Playlist.savePlaylists(playlists, PLAYLIST_FILE);
                            System.out.println("Playlist personalizada criada!");
                        }
                        case 3 -> {
                            System.out.print("Digite o nome do utilizador para gerar a playlist de favoritos: ");
                            String userName = scanner.nextLine();
                            User user = users.stream()
                                    .filter(u -> u.getName().equals(userName))
                                    .findFirst()
                                    .orElse(null);

                            if (user != null) {
                                PlaylistFavs favPlaylist = new PlaylistFavs(user, musics);
                                playlists.add(favPlaylist);
                                Playlist.savePlaylists(playlists, PLAYLIST_FILE);
                                System.out.println("Playlist de favoritos criada!");
                            } else {
                                System.out.println("Utilizador não encontrado!");
                            }
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                }
                case 4 -> {
                    System.out.println("Reproduzindo playlists...");
                    for (Playlist playlist : playlists) {
                        System.out.println("Reproduzindo: " + playlist);
                        playlist.getMusics().forEach(music -> System.out.println("Tocando: " + music));
                    }
                }
                case 5 -> {
                    users = User.loadUsers(USERS_FILE);
                    musics = Music.loadMusics(MUSIC_FILE);
                    playlists = Playlist.loadPlaylists(PLAYLIST_FILE);
                    System.out.println("Dados carregados com sucesso!");
                
                    // Exibir utilizadores carregados
                    System.out.println("\nUtilizadores carregados:");
                    for (User user : users) {
                        System.out.println("Nome: " + user.getName());
                        System.out.println("Email: " + user.getEmail());
                        System.out.println("Endereço: " + user.getAddress());
                        System.out.println("Plano: " + user.getSubscriptionPlan().getName());
                        System.out.println("--------------------");
                    }
                
                    // Exibir músicas carregadas
                    System.out.println("\nMúsicas carregadas:");
                    for (Music music : musics) {
                        System.out.println("Nome: " + music.getName());
                        System.out.println("Artista: " + music.getArtist());
                        System.out.println("Gênero: " + music.getGenre());
                        System.out.println("Duração: " + music.getDuration() + " segundos");
                        System.out.println("--------------------");
                    }
                
                    // Exibir playlists carregadas
                    System.out.println("\nPlaylists carregadas:");
                    for (Playlist playlist : playlists) {
                        System.out.println("Playlist: " + playlist);
                        System.out.println("Músicas:");
                        for (Music music : playlist.getMusics()) {
                            System.out.println("  - " + music.getName() + " por " + music.getArtist());
                        }
                        System.out.println("--------------------");
                    }
                }
                case 6 -> {
                    running = false;
                    System.out.println("Saindo do SpotifUM...");
                }
                default -> System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
