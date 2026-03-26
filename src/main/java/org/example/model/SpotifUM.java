package org.example.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Main Facade class for the SpotifUM application business layer.
 */
public class SpotifUM implements Serializable {

    private Map<String, User> users;
    private Map<Integer, Playlist> playlists;
    private Map<Integer, Album> albums;
    private Map<Integer, Music> musics;
    private Map<String, Artist> artists;

    //------------------------------------------------------------------------------------------------------------
    /**
     * Default constructor for SpotifUM.
     */
    public SpotifUM() {
        this.users = new LinkedHashMap<>();
        this.playlists = new LinkedHashMap<>();
        this.albums = new LinkedHashMap<>();
        this.musics = new LinkedHashMap<>();
        this.artists = new LinkedHashMap<>();
    }

    /**
     * Constructor for SpotifUM with parameters.
     *
     * @param users Map of users
     * @param playlists Map of playlists
     * @param albums Map of albums
     * @param musics Map of musics
     * @param artists Map of artists
     */
    public SpotifUM(Map<String, User> users, Map<Integer, Playlist> playlists, Map<Integer, Album> albums, Map<Integer, Music> musics, Map<String, Artist> artists) {
        this.setUsers(users);
        this.setPlaylists(playlists);
        this.setAlbums(albums);
        this.setMusics(musics);
        this.setArtists(artists);

    }

    /**
     * Copy constructor for SpotifUM.
     *
     * @param spotifUM SpotifUM object to copy
     */
    public SpotifUM(SpotifUM spotifUM) {
        this.users = spotifUM.getUsers();
        this.playlists = spotifUM.getPlaylists();
        this.albums = spotifUM.getAlbums();
        this.musics = spotifUM.getMusics();
        this.artists = spotifUM.getArtists();

    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Get the users map.
     */
    public Map<String, User> getUsers() {
        Map<String, User> copy = new HashMap<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    /**
     * Set the users map.
     *
     * @param users Map of users
     */
    public void setUsers(Map<String, User> users) {
        this.users = new LinkedHashMap<>();
        for (Map.Entry<String, User> entry : users.entrySet()) {
            this.users.put(entry.getKey(), entry.getValue().clone());
        }
    }

    /**
     * Get the playlists map.
     */
    public Map<Integer, Playlist> getPlaylists() {
        Map<Integer, Playlist> copy = new HashMap<>();
        for (Map.Entry<Integer, Playlist> entry : playlists.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    /**
     * Set the playlists map.
     *
     * @param playlists Map of playlists
     */
    public void setPlaylists(Map<Integer, Playlist> playlists) {
        this.playlists = new LinkedHashMap<>();
        for (Map.Entry<Integer, Playlist> entry : playlists.entrySet()) {
            this.playlists.put(entry.getKey(), entry.getValue().clone());
        }
    }

    /**
     * Get the albums map.
     */
    public Map<Integer, Album> getAlbums() {
        Map<Integer, Album> copy = new LinkedHashMap<>();
        for (Map.Entry<Integer, Album> entry : albums.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    /**
     * Set the albums map.
     *
     * @param albums Map of albums
     */
    public void setAlbums(Map<Integer, Album> albums) {
        this.albums = new LinkedHashMap<>();
        for (Map.Entry<Integer, Album> entry : albums.entrySet()) {
            this.albums.put(entry.getKey(), entry.getValue().clone());
        }
    }

    /**
     * Get the musics map.
     */
    public Map<Integer, Music> getMusics() {
        Map<Integer, Music> copy = new LinkedHashMap<>();
        for (Map.Entry<Integer, Music> entry : musics.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    /**
     * Set the musics map.
     *
     * @param musics Map of musics
     */
    public void setMusics(Map<Integer, Music> musics) {
        this.musics = new LinkedHashMap<>();
        for (Map.Entry<Integer, Music> entry : musics.entrySet()) {
            this.musics.put(entry.getKey(), entry.getValue().clone());
        }
    }

    /**
     * Get the artists map.
     */
    public Map<String, Artist> getArtists() {
        Map<String, Artist> copy = new LinkedHashMap<>();
        for (Map.Entry<String, Artist> entry : artists.entrySet()) {
            copy.put(entry.getKey(), entry.getValue().clone());
        }
        return copy;
    }

    /**
     * Set the artists map.
     *
     * @param artists Map of artists
     */
    public void setArtists(Map<String, Artist> artists) {
        this.artists = new LinkedHashMap<>();
        for (Map.Entry<String, Artist> entry : artists.entrySet()) {
            this.artists.put(entry.getKey(), entry.getValue().clone());
        }
    }

    /**
     * Get an artist by ID.
     *
     * @param id Artist ID
     * @return Cloned artist object
     * @throws SpotifumExecption if the artist is not found
     */
    public Artist getArtist(String id) throws SpotifumExecption {
        Artist artist = artists.get(id);
        if (artist == null) {
            throw new SpotifumExecption("Artist not found");
        }
        return artist.clone();
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Login method to authenticate a user.
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        User user = this.users.get(username); // Verifica se o usuário existe
        if (user == null) {
            return false; // Usuário não encontrado
        }
        return user.validaPassword(password); // Verifica se a senha está correta
    }

    /**
     * Check if the user is an admin.
     *
     * @param username
     * @return
     */
    public boolean isAdmin(String username) {
        return ("admin".equals(username));
    }

    /**
     * Get the plan type of a user.
     *
     * @param userId
     * @return
     * @throws SpotifumExecption
     */
    public String getTipoPlan(String userId) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }
        return user.getTipoPlan();
    }

    /**
     * Get the most listened song across all albums.
     *
     * @return Cloned most listened song
     * @throws SpotifumExecption if no albums are found or unable to determine the most listened song
     */
    public Music getMostListenedSong() throws SpotifumExecption {
        if (albums.isEmpty()) {
            throw new SpotifumExecption("No albums found");
        }

        Music mostListened = null;
        int maxPlayCount = 0;

        for (Album album : this.albums.values()) {
            try {
                Music albumMostListened = album.mostListenedSong();
                if (albumMostListened.getPlayCount() > maxPlayCount) {
                    maxPlayCount = albumMostListened.getPlayCount();
                    mostListened = albumMostListened;
                }
            } catch (SpotifumExecption e) {
                // Ignora álbuns sem músicas
            }
        }

        if (mostListened == null) {
            throw new SpotifumExecption("Unable to determine the most listened song");
        }

        return mostListened.clone(); // Retorna uma cópia da música
    }

    /**
     * Get the most listened artist across all albums.
     *
     * @return String representation of the most listened artist
     * @throws SpotifumExecption if no artists are found or unable to determine the most listened artist
     */
    public String getMostListenedArtist() throws SpotifumExecption {
        if (artists.isEmpty()) {
            throw new SpotifumExecption("No artists found");
        }

        Artist mostListened = null;
        int maxReproductions = 0;
        for (Artist artist : this.artists.values()) {

            if (artist.getReproductions() > maxReproductions) {
                maxReproductions = artist.getReproductions();
                mostListened = artist;
            }
        }

        
        if (mostListened == null) {
            throw new SpotifumExecption("Unable to determine the most listened artist");
        }

        return "Artist: " + mostListened.getName() + ", Reproductions: " + mostListened.getReproductions();
    }

    /**
     * Get the user with the most reproductions in a specified period.
     * @param startStr
     * @param endStr
     * @return
     * @throws SpotifumExecption
     */
    public User userMostReproductionsInPeriod(String startStr, String endStr) throws SpotifumExecption {
        LocalDateTime start = null;
        LocalDateTime end = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Se receber datas, converte-as
        if (startStr != null && !startStr.isEmpty()) {
            start = LocalDate.parse(startStr, formatter).atStartOfDay();
        }
        if (endStr != null && !endStr.isEmpty()) {
            end = LocalDate.parse(endStr, formatter).atTime(23, 59, 59);
        }

        String topUserId = null;
        int max = 0;

        for (User user : users.values()) {
            int count = 0;
            List<Reproduction> reps = user.getReproductions();
            if (reps != null) {
                for (Reproduction r : reps) {
                    LocalDateTime time = r.getDate();
                    boolean inPeriod = (start == null || !time.isBefore(start)) && (end == null || !time.isAfter(end));
                    if (inPeriod) {
                        count++;
                    }
                }
            }
            if (count >= max) {
                max = count;
                topUserId = user.getIdUser();
            }
        }

        if (topUserId == null) {
            throw new SpotifumExecption("Unable to determine the user with most reproductions");
        }

        return users.get(topUserId).clone();
    }

    /**
     * Get the user with the most points.
     * @return
     * @throws SpotifumExecption
     */
    public User getUserMostPoints() throws SpotifumExecption {
        if (users.isEmpty()) {
            throw new SpotifumExecption("No users found");
        }

        User most_points = null;
        int maxPoints = 0;

        for (User user : this.users.values()) {
            if (user.getPoints() > maxPoints) {
                maxPoints = user.getPoints();
                most_points = user;
            }
        }

        if (most_points == null) {
            throw new SpotifumExecption("Unable to determine the most points");
        }

        return most_points.clone();
    }

    /**
     * Get the user with the most reproductions.
     * @return
     * @throws SpotifumExecption
     */
    private void updateGenrePlayCounts(int[] genrePlayCounts) {
        for (Music music : musics.values()) {
            if (music.getGenre() != null) {
                genrePlayCounts[music.getGenre().ordinal()] += music.getPlayCount();
            }
        }
    }

    /**
     * Get the most played genre across all musics.
     *
     * @return Genre object representing the most played genre
     * @throws SpotifumExecption if no musics are found or unable to determine the most played genre
     */
    public Genre mostPlayedGenre() throws SpotifumExecption {
        if (musics.isEmpty()) {
            throw new SpotifumExecption("No musics found");
        }

        int[] genrePlayCounts = new int[Genre.values().length];
        updateGenrePlayCounts(genrePlayCounts);

        int maxCount = 0;
        int mostPlayedIndex = -1;

        for (int i = 0; i < genrePlayCounts.length; i++) {
            if (genrePlayCounts[i] > maxCount) {
                maxCount = genrePlayCounts[i];
                mostPlayedIndex = i;
            }
        }

        if (mostPlayedIndex == -1) {
            throw new SpotifumExecption("Unable to determine the most played genre");
        }

        return Genre.values()[mostPlayedIndex];
    }

    /**
     * Get the number of public playlists.
     *
     * @return Number of public playlists
     * @throws SpotifumExecption if no playlists are found
     */
    public int getNumPlaylistsPublic() throws SpotifumExecption {
        if (playlists.isEmpty()) {
            throw new SpotifumExecption("No playlists found");
        }

        int num_playlists_public = 0;
        for (Playlist playlist : getPlaylists().values()) {
            if (playlist.getIsPublic()) {
                num_playlists_public++;
            }
        }

        return num_playlists_public;
    }

    /**
     * Get the user with the most user-built playlists.
     *
     * @return Cloned user object
     * @throws SpotifumExecption if no users are found or unable to determine the user with most user-built playlists
     */
    public User userMostPlaylistsUserBuilt() throws SpotifumExecption {
        if (users.isEmpty()) {
            throw new SpotifumExecption("No users found");
        }

        String topUserId = null;
        int max = 0;

        for (User user : users.values()) {
            int count = 0;
            Library lib = user.getLibrary();
            if (lib != null) {
                for (Playlist p : lib.getPlaylists()) {
                    if (p instanceof PlaylistUserBuilt) {
                        count++;
                    }
                }
            }
            if (count > max) {
                max = count;
                topUserId = user.getIdUser();
            }
        }

        if (topUserId == null) {
            throw new SpotifumExecption("Unable to determine the user with most UserBuilt playlists");
        }

        return users.get(topUserId).clone();
    }

    /**
     * Get the user with the most public playlists.
     *
     * @return Cloned user object
     * @throws SpotifumExecption if no users are found or unable to determine the user with most public playlists
     */
    public void save(String nomef) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomef));

        oos.writeObject(this);
        oos.writeObject(Music.getIdCounter());
        oos.writeObject(Album.getIdCounter());
        oos.writeObject(Playlist.getIdCounter());

        oos.close();
    }

    /**
     * Load the SpotifUM object from a file.
     *
     * @param nomef File name
     * @return Loaded SpotifUM object
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class is not found
     */
    public static SpotifUM readObj(String nomef) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomef));
        SpotifUM s = (SpotifUM) ois.readObject();

        // Atualizar os contadores de ID para Music, Album e Playlist
        int idCounterMusic = (int) ois.readObject();
        Music.setIdCounter(idCounterMusic);

        int idCounterAlbum = (int) ois.readObject();
        Album.setIdCounter(idCounterAlbum);

        int idCounterPlaylist = (int) ois.readObject();
        Playlist.setIdCounter(idCounterPlaylist);

        ois.close();

        return s;
    }
    //------------------------------------------------------------------------------------------------------------

    /**
     * Add a user with details.
     *
     * @param id User ID
     * @param name User name
     * @param age User age
     * @param email User email
     * @param address User address
     * @param password User password
     * @param planType Plan type (e.g., "PlanFree", "PlanPremiumBase", "PlanPremiumTop")
     * @throws SpotifumExecption if the plan type is invalid
     */
    public void addUserWithDetails(String id, String name, int age, String email, String address, String password, String planType) throws SpotifumExecption {
        Plan plan;
        int initialPoints = 0;
        switch (planType) {
            case "PlanFree" ->
                plan = new PlanFree();
            case "PlanPremiumBase" ->
                plan = new PlanPremiumBase();
            case "PlanPremiumTop" -> {
                plan = new PlanPremiumTop();
                initialPoints = 100;
            }
            default -> {
                throw new SpotifumExecption("Invalid plan type: " + planType);
            }
        }

        User newUser = new User(id, plan, name, age, email, address, initialPoints, password, new ArrayList<>());
        this.users.put(newUser.getIdUser(), newUser);

        System.out.println("User added successfully: " + newUser);
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Add a music with details.
     *
     * @param name Music name
     * @param artistID Artist ID
     * @param labelName Label name
     * @param lyrics Lyrics of the music
     * @param musicLines List of music lines
     * @param genre Genre of the music
     * @param duration Duration of the music in seconds
     * @param albumId Album ID
     * @return ID of the added music
     * @throws SpotifumExecption if the artist is not found
     */
    public int addMusicWithDetails(String name, String artistID, String labelName, String lyrics, List<String> musicLines,
            Genre genre, int duration, int albumId) throws SpotifumExecption {

        Artist artist = this.getArtist(artistID); // Obtém o artista pelo ID
        if (artist == null) {
            throw new SpotifumExecption("Artist not found: " + artistID);
        }

        // Cria uma nova música com os detalhes fornecidos
        Music newMusic = new Music(name, artist, labelName, albumId, lyrics, musicLines, genre, duration, 0);

        // Adiciona a música ao mapa de músicas
        this.musics.put(newMusic.getId(), newMusic);

        // Retorna o ID da nova música (agora do tipo int)
        return newMusic.getId();
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Add an artist with details.
     *
     * @param name Artist name
     * @throws SpotifumExecption if the artist already exists
     */    
    public void addArtistWithDetails(String name) throws SpotifumExecption {
        if (this.artists.containsKey(name)) {
            throw new SpotifumExecption("Artist already exists: " + name);
        }
        Artist newArtist = new Artist(name);
        this.artists.put(newArtist.getName(), newArtist); // Adiciona o artista ao sistema
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Create an empty album.
     *
     * @param artistName Artist name
     * @param albumName Album name
     * @return ID of the created album
     * @throws SpotifumExecption if the artist is not found
     */
    public int createEmptyAlbum(String artistName, String albumName) throws SpotifumExecption {
        // Verifica se o artista existe
        Artist artist = this.artists.get(artistName);
        if (artist == null) {
            throw new SpotifumExecption("Artist not found: " + artistName);
        }

        // Cria o álbum e associa ao artista
        Album album = new Album(albumName, new ArrayList<>(), artist);
        this.albums.put(album.getId(), album);

        // Retorna o ID do novo álbum
        return album.getId();
    }

    /**
     * Add a music with details to an album.
     *
     * @param albumId Album ID
     * @param name Music name
     * @param albumArtist Artist name
     * @param labelName Label name
     * @param lyrics Lyrics of the music
     * @param musicLines List of music lines
     * @param genre Genre of the music
     * @param duration Duration of the music in seconds
     * @throws SpotifumExecption if the album is not found
     */
    public void addMusicWithDetailsToAlbum(int albumId, String name, String albumArtist, String labelName, String lyrics,
            List<String> musicLines, Genre genre, int duration) throws SpotifumExecption {
        // Verifica se o álbum existe
        Album album = this.albums.get(albumId);
        if (album == null) {
            throw new SpotifumExecption("Album not found: " + albumId);
        }

        // Adiciona a música ao sistema e ao álbum
        int musicId = addMusicWithDetails(name, albumArtist, labelName, lyrics, musicLines, genre, duration, albumId);
        Music music = this.musics.get(musicId);
        if (music == null) {
            throw new SpotifumExecption("Music not found: " + musicId);
        }
        album.addMusic(music);
    }

    /**
     * Create a random playlist.
     *
     * @param name Playlist name
     * @param numMusics Number of musics in the playlist
     * @param isPublic Is the playlist public?
     * @throws SpotifumExecption if the number of musics is invalid or not enough musics are available
     */
    public void createRandomPlaylist(String name, int numMusics, boolean isPublic) throws SpotifumExecption {
        if (numMusics <= 0) {
            throw new SpotifumExecption("Number of musics must be greater than 0");
        }

        List<Music> randomMusics = new ArrayList<>();
        List<Music> allMusics = new ArrayList<>(this.musics.values());
        if (allMusics.size() < numMusics) {
            throw new SpotifumExecption("Not enough musics available");
        }

        for (int i = 0; i < numMusics; i++) {
            int randomIndex = (int) (Math.random() * allMusics.size());
            Music randomMusic = allMusics.get(randomIndex);
            randomMusics.add(randomMusic);
            allMusics.remove(randomIndex); // Remove para evitar duplicatas
        }

        PlaylistRandom newPlaylist = new PlaylistRandom(name, randomMusics, isPublic);
        this.playlists.put(newPlaylist.getIdPlaylist(), newPlaylist); // Adiciona a nova playlist ao sistema
    }

    /**
     * Create a playlist with a maximum duration.
     *
     * @param userId User ID
     * @param name Playlist name
     * @param maxDuration Maximum duration in seconds
     * @param isPublic Is the playlist public?
     * @throws SpotifumExecption if the maximum duration is invalid or no musics fit within the specified duration
     */
    public void createGenMaxPlaylist(String userId, String name, int maxDuration, boolean isPublic) throws SpotifumExecption {
        if (maxDuration <= 0) {
            throw new SpotifumExecption("Maximum duration must be greater than 0");
        }

        List<Music> allMusics = new ArrayList<>(this.musics.values());
        List<Music> selectedMusics = new ArrayList<>();
        int currentDuration = 0;

        // Embaralha as músicas para garantir aleatoriedade
        Collections.shuffle(allMusics);

        for (Music music : allMusics) {
            if (currentDuration + music.getDuration() <= maxDuration) {
                selectedMusics.add(music);
                currentDuration += music.getDuration();
            }
            // Para o loop se atingir a duração máxima
            if (currentDuration >= maxDuration) {
                break;
            }
        }

        if (selectedMusics.isEmpty()) {
            throw new SpotifumExecption("No musics fit within the specified maximum duration");
        }

        // Cria a playlist com as músicas selecionadas
        PlaylistGenMax newPlaylist = new PlaylistGenMax(name, selectedMusics, isPublic);

        if (isPublic) {
            this.playlists.put(newPlaylist.getIdPlaylist(), newPlaylist);
        }// Adiciona a playlist ao sistema

        User user = this.users.get(userId);
        if (user != null) {
            user.addPlaylistToLibrary(newPlaylist);
        } else {
            throw new SpotifumExecption("User not found");
        }
    }

    /**
     * Create a user-built playlist.
     *
     * @param userId User ID
     * @param name Playlist name
     * @param musicsChoosen List of chosen musics
     * @param isPublic Is the playlist public?
     * @throws SpotifumExecption if the playlist is empty or the user is not found
     */
    public void createUserBuiltPlaylist(String userId, String name, List<Music> musicsChoosen, boolean isPublic) throws SpotifumExecption {
        if (musicsChoosen.isEmpty()) {
            throw new SpotifumExecption("Playlist must contain at least one music");
        }

        // Cria a nova playlist
        PlaylistUserBuilt newPlaylist = new PlaylistUserBuilt(name, new ArrayList<>(), isPublic);

        // Itera pelas músicas escolhidas
        for (Music music : musicsChoosen) {
            int musicId = music.getId();
            Music musicFromMap = this.musics.get(musicId);

            if (musicFromMap != null) {
                newPlaylist.addMusic(musicFromMap);
            } else {
                throw new SpotifumExecption("Music with ID " + musicId + " not found in the system.");
            }
        }

        // Adiciona a playlist ao sistema
        if (isPublic) {
            this.playlists.put(newPlaylist.getIdPlaylist(), newPlaylist);
        }

        // Adiciona a playlist à biblioteca do usuário
        User user = this.users.get(userId);
        if (user != null) {
            user.addPlaylistToLibrary(newPlaylist);
        } else {
            throw new SpotifumExecption("User not found");
        }
    }

    /**
     * Listen to a random music interactively.
     *
     * @param userId User ID
     * @return String representation of the played music
     * @throws SpotifumExecption if the user is not found or no musics are available
     */
    public String listenToRandomMusicInteractive(String userId) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }

        List<Music> allMusics = new ArrayList<>(musics.values());
        if (allMusics.isEmpty()) {
            throw new SpotifumExecption("No musics available.");
        }

        int idx = (int) (Math.random() * allMusics.size());
        Playable randomMusic = allMusics.get(idx);

        return randomMusic.play(user);
    }

    /**
     * Play a music for a user.
     *
     * @param playable Playable object (e.g., Music, Playlist)
     * @param userId User ID
     * @return String representation of the played music
     * @throws SpotifumExecption if the user is not found
     */
    public String playMusicForUser(Playable playable, String userId) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }
        return playable.play(user);
    }

    /**
     * Get the playables in a playlist for a user.
     *
     * @param userId User ID
     * @param playlistId Playlist ID
     * @return List of Playable objects in the playlist
     * @throws SpotifumExecption if the user or playlist is not found
     */
    public List<Playable> getPlaylistPlayables(String userId, int playlistId) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }
        Library library = user.getLibrary();
        if (library == null) {
            throw new SpotifumExecption("Library not found for user");
        }

        // Procura a playlist na library do user (são clones, mas serve para leitura e play)
        Playlist playlist = library.getPlaylists().stream()
                .filter(p -> p.getIdPlaylist() == playlistId)
                .findFirst()
                .orElseThrow(() -> new SpotifumExecption("Playlist not found in user's library"));

        List<Playable> playables = new ArrayList<>();
        playlist.playablesIterator().forEachRemaining(playables::add);
        return playables;
    }

    /**
     * Get the playables in an album.
     *
     * @param albumId Album ID
     * @return List of Playable objects in the album
     * @throws SpotifumExecption if the album is not found
     */
    public List<Playable> getAlbumPlayables(int albumId) throws SpotifumExecption {
        Album album = albums.get(albumId);
        if (album == null) {
            throw new SpotifumExecption("Album not found");
        }
        List<Playable> playables = new ArrayList<>();
        album.playablesIterator().forEachRemaining(playables::add);
        return playables;
    }

    /**
     * Change the subscription plan for a user.
     *
     * @param userId User ID
     * @param newPlanType New plan type (e.g., "PlanFree", "PlanPremiumBase", "PlanPremiumTop")
     * @throws SpotifumExecption if the user is not found or the plan type is invalid
     */
    public void changeSubscription(String userId, String newPlanType) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }

        // Delegar a lógica para a classe User
        user.changeSubscription(newPlanType);
    }

    /**
     * Create a library for a user.
     *
     * @param userId User ID
     * @throws SpotifumExecption if the user is not found or the library already exists
     */
    public void createLibraryForUser(String userId) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }

        if (user.getLibrary() == null) {
            user.setLibrary(new Library());
        } else {
            throw new SpotifumExecption("Library already exists for this user");
        }
    }

    /**
     * Get the available albums.
     *
     * @return List of cloned Album objects
     */
    public List<Album> getAvailableAlbums() {
        return albums.values().stream().map(Album::clone).toList();
    }

    /**
     * Get the available musics.
     *
     * @return List of cloned Music objects
     */
    public List<Playlist> getPublicPlaylists() {
        return playlists.values()
                .stream()
                .filter(Playlist::getIsPublic)
                .map(Playlist::clone)
                .toList();
    }

    /**
     * Get the available musics.
     *
     * @return List of cloned Music objects
     */
    public void addAlbumToUserLibrary(String userId, Album album) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }
        if (user.getLibrary() == null) {
            throw new SpotifumExecption("Library not created for this user");
        }
        user.addAlbumToLibrary(album); // Agora sim, mexe na library real!
    }

    /**
     * Add a playlist to a user's library.
     *
     * @param userId User ID
     * @param playlist Playlist object
     * @throws SpotifumExecption if the user or library is not found
     */
    public void addPlaylistToUserLibrary(String userId, Playlist playlist) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }
        if (user.getLibrary() == null) {
            throw new SpotifumExecption("Library not created for this user");
        }
        user.addPlaylistToLibrary(playlist); // Usa o método do User, que mexe na library real
    }

    /**
     * Generate a "Made For You" playlist based on the user's listening history.
     *
     * @param userId User ID
     * @param isExplicit Is the playlist explicit?
     * @return Generated Playlist object
     * @throws SpotifumExecption if the user is not found or unable to generate the playlist
     */
    public Playlist generateMadeForYouPlaylist(String userId, boolean isExplicit) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }

        // Array to count genres based on their ordinal
        int[] genreCounts = new int[Genre.values().length];
        for (Reproduction reproduction : user.getReproductions()) {
            Genre genre = reproduction.getMusic().getGenre();
            genreCounts[genre.ordinal()]++;
        }

        // Get the top 3 genres
        List<Genre> topGenres = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int maxIndex = -1;
            int maxCount = -1;
            for (int j = 0; j < genreCounts.length; j++) {
                if (genreCounts[j] > maxCount) {
                    maxCount = genreCounts[j];
                    maxIndex = j;
                }
            }
            if (maxIndex != -1) {
                topGenres.add(Genre.values()[maxIndex]);
                genreCounts[maxIndex] = -1; // Mark as processed
            }
        }

        // Collect songs from the top genres
        List<Music> allPlaylistMusics = musics.values().stream()
                .filter(music -> topGenres.contains(music.getGenre()))
                .toList();

        // Filtrar músicas baseado no parâmetro isExplicit
        List<Music> playlistMusics;
        if (isExplicit) {
            // Se isExplicit é true, pegar apenas músicas explícitas
            playlistMusics = allPlaylistMusics.stream()
                    .filter(music -> music instanceof MusicExplicit)
                    .collect(Collectors.toList());
        } else {
            // Se isExplicit é false, usar todas as músicas
            playlistMusics = new ArrayList<>(allPlaylistMusics);
        }

        // Criar a playlist com as músicas filtradas
        Playlist madeForYou = new PlaylistFavs("Made For You", playlistMusics, false);
        playlists.put(madeForYou.getIdPlaylist(), madeForYou);
        return madeForYou;
    }

    /**
     * Change the publicity of a playlist to public.
     *
     * @param userId User ID
     * @param playlistId Playlist ID
     * @throws SpotifumExecption if the user or playlist is not found
     */
    public void changePlaylistPublicity(String userId, int playlistId) throws SpotifumExecption {
        User user = users.get(userId);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }
        Library library = user.getLibrary();
        if (library == null) {
            throw new SpotifumExecption("Library not found for user");
        }

        // Usa o método seguro para obter a playlist real
        Playlist realPlaylist = library.getPlaylistById(playlistId);

        if (realPlaylist == null) {
            throw new SpotifumExecption("Playlist not found in user's library");
        }
        if (realPlaylist.getIsPublic()) {
            throw new SpotifumExecption("Playlist is already public.");
        }

        realPlaylist.setIsPublic(true);

        // Adiciona ao mapa global se ainda não existir
        if (!playlists.containsKey(realPlaylist.getIdPlaylist())) {
            playlists.put(realPlaylist.getIdPlaylist(), realPlaylist);
        }
    }

    /**
     * Initialize default data for testing.
     */
    public void initializeDefaultData() {
        // === ARTISTAS ===
        Artist lon3r = new Artist("Lon3r Johny", 1);
        this.artists.put(lon3r.getName(), lon3r);

        Artist vanZee = new Artist("Van Zee", 0);
        this.artists.put(vanZee.getName(), vanZee);

        Artist slowJ = new Artist("Slow J", 0);
        this.artists.put(slowJ.getName(), slowJ);

        // === ÁLBUNS ===
        Album antisocial = new Album("ANTI$$OCIAL", new ArrayList<>(), lon3r);
        this.albums.put(antisocial.getId(), antisocial);

        Album doMar = new Album("DO MAR", new ArrayList<>(), vanZee);
        this.albums.put(doMar.getId(), doMar);

        Album altaCostura = new Album("Alta Costura", new ArrayList<>(), vanZee);
        this.albums.put(altaCostura.getId(), altaCostura);

        Album afroFado = new Album("Afro Fado", new ArrayList<>(), slowJ);
        this.albums.put(afroFado.getId(), afroFado);
        int afroFadoId = afroFado.getId(); // Variável para armazenar o ID do álbum

        // === MÚSICAS: ANTI$$OCIAL ===
        Music m1 = new Music("ANTI$$OCIAL", lon3r, "Sony", antisocial.getId(),
                "Nada me prende, eu tô fora do normal\nDinheiro na mente, no bolso ilegal\nNo corre sozinho, sem medo do final\nANTI$$OCIAL, o meu ritual",
                new ArrayList<>(), Genre.HIPHOP, 180, 0);

        Music m2 = new Music("AO TEU LADO", lon3r, "Sony", antisocial.getId(),
                "Ao teu lado eu sinto paz\nMesmo se o mundo girar pra trás\nTuas mãos seguram meu caos\nE tudo volta ao normal",
                new ArrayList<>(), Genre.RAP, 185, 0);

        Music m3 = new Music("NÃO OS VEJO", lon3r, "Sony", antisocial.getId(),
                "Falam demais, mas eu não os vejo\nVivem no palco, eu vivo no enredo\nOlhos fechados, foco no meu desejo\nPasso por cima, sem dar um beijo",
                new ArrayList<>(), Genre.RAP, 175, 0);

        Music m4 = new Music("DIAMANTE", lon3r, "Sony", antisocial.getId(),
                "Brilho intenso no escuro da alma\nLapidado na dor, na palma\nCaminho firme, sempre avante\nPorque nasci pra ser diamante",
                new ArrayList<>(), Genre.RAP, 170, 0);

        Music m5 = new Music("UH LA LA LA", lon3r, "Sony", antisocial.getId(),
                "Uh la la la, baby, vem dançar\nNo beat pesado, vamos flutuar\nTeu corpo chama, me deixa levar\nNo som do bairro, vamos estourar",
                new ArrayList<>(), Genre.RAP, 160, 0);

        Music m6 = new Music("PIÑA COLADA", lon3r, "Sony", antisocial.getId(),
                "Piña colada no copo gelado\nNo paraíso, sem passado\nTeu beijo doce, pecado jurado\nNo beat do verão, tudo é sagrado",
                new ArrayList<>(), Genre.RAP, 165, 0);

        for (Music m : List.of(m1, m2, m3, m4, m5, m6)) {
            this.musics.put(m.getId(), m);
            antisocial.addMusic(m);
        }

        // === MÚSICAS: DO MAR ===
        Music d1 = new Music("Sol", vanZee, "Independente", doMar.getId(),
                "O sol tocou meu rosto leve\nBrisa do mar que nunca se perde\nNos teus olhos vejo um lar\nCalmo como o céu sobre o mar",
                new ArrayList<>(), Genre.ALTERNATIVE, 221, 0);

        Music d2 = new Music("Sober", vanZee, "Independente", doMar.getId(),
                "Sóbrio entre copos vazios\nRostos que já não são meus\nTeu nome ecoa no frio\nMas o tempo se perdeu",
                new ArrayList<>(), Genre.ALTERNATIVE, 163, 0);

        Music d3 = new Music("Amar De Cor", vanZee, "Independente", doMar.getId(),
                "Sei amar de cor, sem roteiro\nCada toque, cada suspiro verdadeiro\nNos detalhes encontro calor\nE no teu olhar, amor inteiro",
                new ArrayList<>(), Genre.ALTERNATIVE, 166, 0);

        Music d4 = new Music("Perto", vanZee, "Independente", doMar.getId(),
                "Mesmo longe, sinto-te perto\nNo silêncio, o teu afeto\nNos sonhos onde desperto\nÉ teu nome que repito discreto",
                new ArrayList<>(), Genre.ALTERNATIVE, 210, 0);

        Music d5 = new Music("International Bizz", vanZee, "Independente", doMar.getId(),
                "Voo alto, voo livre, internacional\nNegócio feito sem tocar jornal\nNo estúdio ou na rua marginal\nFaço arte, real, original",
                new ArrayList<>(), Genre.ALTERNATIVE, 194, 0);

        Music d6 = new Music("Senti Tanto", vanZee, "Independente", doMar.getId(),
                "Senti tanto que calei\nAs palavras queimam demais\nTeu nome guardei\nMas já não volto atrás",
                new ArrayList<>(), Genre.ALTERNATIVE, 163, 0);

        Music d7 = new Music("À Tua Porta", vanZee, "Independente", doMar.getId(),
                "À tua porta deixei meu orgulho\nEsperando só um sinal teu\nNa chuva, no escuro, eu mergulho\nPorque o que sinto é mais que meu",
                new ArrayList<>(), Genre.ALTERNATIVE, 212, 0);

        Music d8 = new Music("Rota Nova", vanZee, "Independente", doMar.getId(),
                "Mudando o rumo, seguindo a maré\nNavego sozinho sem saber onde é\nA rota é nova, mas o vento é fé\nA bússola aponta pra quem quiser",
                new ArrayList<>(), Genre.ALTERNATIVE, 132, 0);

        Music d9 = new MusicExplicit("Zeenterlude", vanZee, "Independente", doMar.getId(),
                "Entre beats e silêncio profundo\nOuço minha essência nesse mundo\nNem sempre é música que acalma\nÀs vezes é só a minha alma",
                new ArrayList<>(), Genre.ALTERNATIVE, 114, 0);

        Music d10 = new Music("Saudade", vanZee, "Independente", doMar.getId(),
                "Saudade mora em mim sem pedir\nTua ausência custa a sair\nCada lembrança é um ferir\nMas no fundo, é só por ti",
                new ArrayList<>(), Genre.ALTERNATIVE, 190, 0);

        Music d11 = new Music("Nossa", vanZee, "Independente", doMar.getId(),
                "Nossa história escrita no vento\nTeus olhos, meu maior argumento\nSei que o tempo apaga lento\nMas o amor ficou, sem lamento",
                new ArrayList<>(), Genre.ALTERNATIVE, 124, 0);

        Music d12 = new MusicExplicit("Seaside", vanZee, "Independente", doMar.getId(),
                "Walking by the seaside again\nWaves singing our refrain\nHolding hands in ocean rain\nYour smile takes away the pain",
                new ArrayList<>(), Genre.ALTERNATIVE, 180, 0);

        Music d13 = new Music("Para Casa", vanZee, "Independente", doMar.getId(),
                "Volto pra casa com passos lentos\nLevo comigo todos os momentos\nMesmo longe, eu me lembro\nQue em ti encontrei meu centro",
                new ArrayList<>(), Genre.ALTERNATIVE, 249, 0);

        Music d14 = new Music("Alma Nua", vanZee, "Independente", doMar.getId(),
                "Despida de medo, eu me revelo\nNa luz da lua, eu me modelo\nMinha alma nua, sem anel\nTe mostra tudo que é fiel",
                new ArrayList<>(), Genre.ALTERNATIVE, 206, 0);

        for (Music d : List.of(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14)) {
            this.musics.put(d.getId(), d);
            doMar.addMusic(d);
        }

        // === MÚSICAS: ALTA COSTURA ===
        Music ac1 = new MusicExplicit("Bravos", vanZee, "Sente Isto", altaCostura.getId(),
                "Bravos na linha da frente\nNão correm, não mentem\nHonra no sangue ardente\nVivem livres, vivem quente",
                new ArrayList<>(), Genre.RAP, 200, 0);

        Music ac2 = new MusicExplicit("Nessa Chama", vanZee, "Sente Isto", altaCostura.getId(),
                "Nessa chama eu queimo lento\nCada beijo, um novo intento\nNo teu corpo, meu talento\nNo teu olhar, meu firmamento",
                new ArrayList<>(), Genre.RAP, 180, 0);

        Music ac3 = new MusicExplicit("Quem És Tu?", vanZee, "Sente Isto", altaCostura.getId(),
                "Quem és tu por trás do espelho?\nVerdade ou só um conselho?\nTantas máscaras sem pele\nTanta pose que repele",
                new ArrayList<>(), Genre.RAP, 185, 0);

        Music ac4 = new MusicExplicit("Atitude", vanZee, "Sente Isto", altaCostura.getId(),
                "Minha atitude vale mais que fala\nNo silêncio é que a alma se embala\nAndo firme, passo de gala\nMinha presença nunca falha",
                new ArrayList<>(), Genre.RAP, 190, 0);

        Music ac5 = new Music("O Amor É mm Assim, Mas...", vanZee, "Sente Isto", altaCostura.getId(),
                "O amor é mm assim, mas...\nÀs vezes dói demais\nÀs vezes grita paz\nE noutras, nem volta atrás",
                new ArrayList<>(), Genre.RAP, 210, 0);

        Music ac6 = new Music("Ainda Prendes o Cabelo", vanZee, "Sente Isto", altaCostura.getId(),
                "Ainda prendes o cabelo como antes\nE eu perco-me nos teus instantes\nMesmo longe, lembro dos teus encantos\nNa memória, tu és constante",
                new ArrayList<>(), Genre.RAP, 195, 0);

        Music ac7 = new Music("Insular", vanZee, "Sente Isto", altaCostura.getId(),
                "Sou ilha, sou mar e sou brisa\nSozinho no meio da briga\nInsular, mas não perdido\nSó sigo meu próprio abrigo",
                new ArrayList<>(), Genre.RAP, 200, 0);

        Music ac8 = new Music("Trágico", vanZee, "Sente Isto", altaCostura.getId(),
                "Trágico é querer-te assim\nSabendo que não há fim\nTeu toque é fogo sem fim\nE eu volto, mesmo ruim",
                new ArrayList<>(), Genre.RAP, 185, 0);

        Music ac9 = new Music("Como Seria? / Amor Sóbrio", vanZee, "Sente Isto", altaCostura.getId(),
                "Como seria se fosse só nós dois?\nSem medo, sem depois?\nUm amor sóbrio, limpo e claro\nSem ciúmes, sem disparo",
                new ArrayList<>(), Genre.RAP, 220, 0);

        Music ac10 = new Music("Fica Só", vanZee, "Sente Isto", altaCostura.getId(),
                "Se não sabe cuidar, então fica só\nMelhor do que viver um nó\nCoração preso em dó\nÉ hora de soltar, cortar o pó",
                new ArrayList<>(), Genre.RAP, 190, 0);

        for (Music m : List.of(ac1, ac2, ac3, ac4, ac5, ac6, ac7, ac8, ac9, ac10)) {
            this.musics.put(m.getId(), m);
            altaCostura.addMusic(m);
        }

        // === MÚSICAS: AFRO FADO ===
        Music af1 = new Music("Tata", slowJ, "Independente", afroFadoId,
                "Tata dizia pra não parar\nMesmo quando o mundo só quer julgar\nNas raízes encontrei lugar\nE no batuque voltei a sonhar",
                new ArrayList<>(), Genre.RAP, 200, 0);

        Music af2 = new Music("Pirâmide", slowJ, "Independente", afroFadoId,
                "Subo degrau nessa pirâmide\nCom o peso de quem sobrevive\nOlhos no topo, sem alarde\nMeu silêncio também é arte",
                new ArrayList<>(), Genre.RAP, 180, 0);

        Music af3 = new Music("Where U @", slowJ, "Independente", afroFadoId,
                "Where u at when I needed love?\nWhere u at when the skies fell above?\nNow I'm drifting like smoke in air\nNo calls, no texts, just cold stare",
                new ArrayList<>(), Genre.RAP, 185, 0);

        Music af4 = new Music("Nascidos & Criados", slowJ, "Independente", afroFadoId,
                "Nascidos e criados na luta\nCom fé que nunca se oculta\nNa pele, a história escrita\nNo som, a alma bendita",
                new ArrayList<>(), Genre.RAP, 190, 0);

        Music af5 = new Music("Fogo", slowJ, "Independente", afroFadoId,
                "No peito levo o fogo\nQue não se apaga no jogo\nÉ chama de quem não recua\nMesmo quando a vida flutua",
                new ArrayList<>(), Genre.RAP, 210, 0);

        Music af6 = new Music("CorDaPele", slowJ, "Independente", afroFadoId,
                "Cor da pele não define valor\nMas o mundo ainda sente temor\nCada passo é revolução\nCada verso é redenção",
                new ArrayList<>(), Genre.RAP, 195, 0);

        Music af7 = new Music("Ultimamente", slowJ, "Independente", afroFadoId,
                "Ultimamente tenho visto demais\nTraição com sorrisos normais\nGente fria, gestos banais\nMas sigo firme, com meus ideais",
                new ArrayList<>(), Genre.RAP, 200, 0);

        Music af8 = new Music("Terra", slowJ, "Independente", afroFadoId,
                "Minha terra tem cheiro de mãe\nTem dor que o tempo não desfaz\nMas é nela que me encontro\nMesmo quando o mundo jaz",
                new ArrayList<>(), Genre.RAP, 185, 0);

        Music af9 = new Music("Sem Ti", slowJ, "Independente", afroFadoId,
                "Sem ti sou só metade\nNo espelho, só saudade\nTeu perfume ainda invade\nMas tua ausência já é realidade",
                new ArrayList<>(), Genre.RAP, 220, 0);

        Music af10 = new Music("Reza", slowJ, "Independente", afroFadoId,
                "Cada linha é uma reza minha\nPra que a dor não vire rotina\nFalo com Deus em silêncio\nE com o beat no meu templo",
                new ArrayList<>(), Genre.RAP, 190, 0);

        Music af11 = new Music("Seda", slowJ, "Independente", afroFadoId,
                "Tua voz desliza como seda\nEntre meus dedos, cada queda\nÉ leve, mas deixa marca\nTão suave que até desarma",
                new ArrayList<>(), Genre.RAP, 210, 0);

        Music af12 = new Music("Sereia", slowJ, "Independente", afroFadoId,
                "Vi uma sereia na quebrada\nCanto doce, alma encantada\nMe perdi no teu olhar\nE já nem sei onde é meu lar",
                new ArrayList<>(), Genre.RAP, 195, 0);

        Music af13 = new Music("Cabeça", slowJ, "Independente", afroFadoId,
                "Minha cabeça nunca para\nPensamentos como sara\nRimas brotam sem ensaio\nCoração em desabafo diário",
                new ArrayList<>(), Genre.RAP, 200, 0);

        Music af14 = new MusicVideo("Origami", slowJ, "Independente", afroFadoId,
                "Dobras e curvas da vida\nCada linha escondida\nMeu coração em origami\nFaz arte até da ferida",
                new ArrayList<>(), Genre.RAP, 185, 1, "https://www.youtube.com/watch?v=example");

        for (Music af : List.of(af1, af2, af3, af4, af5, af6, af7, af8, af9, af10, af11, af12, af13, af14)) {
            this.musics.put(af.getId(), af);
            afroFado.addMusic(af);
        }

        // === UTILIZADORES ===
        List<Reproduction> reproductions = new ArrayList<>();
        reproductions.add(new Reproduction(af14, LocalDateTime.now()));
        User u1 = new User("u1", new PlanFree(), "Ema", 20, "ema@mail.com", "Rua do Mar", 0, "123", reproductions);
        User u2 = new User("u2", new PlanPremiumBase(), "Simão", 20, "simao@mail.com", "Rua do Rap", 10, "abc", new ArrayList<>());
        User u3 = new User("u3", new PlanPremiumTop(), "Araújo", 16, "araujo@mail.com", "Rua das Letras", 20, "senha", new ArrayList<>());
        User admin = new User("admin", new PlanFree(), "admin", 30, "admin@admin.com", "admin", 0, "admin", new ArrayList<>());

        for (User u : List.of(u1, u2, u3, admin)) {
            this.users.put(u.getIdUser(), u);
        }

        // === PLAYLIST ===
        List<Music> mix = new ArrayList<>();
        mix.add(m1);     // do Lon3r
        mix.add(d1);    // do Van Zee
        mix.add(ac1);    // do Slow J

        PlaylistUserBuilt p1 = new PlaylistUserBuilt("Mix Lusófono", mix, true);
        this.playlists.put(p1.getIdPlaylist(), p1);
    }

    /**
     * Check if a user has a library.
     *
     * @param userID User ID
     * @return true if the user has a library, false otherwise
     */
    public boolean hasLibrary(String userID) {
        User user = this.users.get(userID);
        if (user == null) {
            return false;
        }
        return this.users.get(userID).hasLibrary();
    }

    /**
     * Get the library of a user.
     *
     * @param userID User ID
     * @return Library object
     * @throws SpotifumExecption if the user is not found or the library does not exist
     */
    public Library getLibrary(String userID) throws SpotifumExecption {
        User user = this.users.get(userID);
        if (user == null) {
            throw new SpotifumExecption("User not found");
        }
        return user.getLibrary().deepClone();
    }

    /**
     * Get a specific album.
     *
     * @param albumID Album ID
     * @return Album object
     * @throws SpotifumExecption if the album is not found
     */
    public Album getAlbum(int albumID) {
        return this.albums.get(albumID).deepClone();
    }

    /**
     * Get all private playlists from all users.
     *
     * @return List of cloned Playlist objects
     */
    public List<Playlist> getPrivatePlaylistsFromLibraries() {
        List<Playlist> privatePlaylists = new ArrayList<>();

        for (User user : this.users.values()) {
            Library library = user.getLibrary();
            if (library != null) {
                for (Playlist playlist : library.getPlaylists()) {
                    if (!playlist.getIsPublic()) { // Filtra apenas as playlists privadas
                        privatePlaylists.add(playlist.clone()); // Clona para evitar modificações externas
                    }
                }
            }
        }

        return privatePlaylists;
    }

}
