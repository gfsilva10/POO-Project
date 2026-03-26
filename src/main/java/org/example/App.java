package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.example.model.Album;
import org.example.model.Artist;
import org.example.model.Genre;
import org.example.model.Music;
import org.example.model.Playable;
import org.example.model.Playlist;
import org.example.model.SpotifUM;
import org.example.model.SpotifumExecption;
import org.example.model.User;

public class App {

    /**
     * Main class for the SpotifUM application. This class handles user
     * interactions and manages the application flow.
     *
     * @author Simão, Gonçalo, José
     */
    private SpotifUM model;
    private Scanner scanner;

    /**
     * Constructor for the App class. Initializes the SpotifUM model and loads
     * data from a file.
     *
     * @throws IOException if there is an error reading the file
     */
    public App() {
        try {
            model = SpotifUM.readObj("SpotifUM.obj");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Oops! Não consegui ler! " + e.getMessage());
            model = new SpotifUM(); // Cria uma nova instância de SpotifUM
            model.initializeDefaultData(); // Inicializa os dados padrão
            try {
                model.save("SpotifUM.obj"); // Save the newly created object
            } catch (IOException saveException) {
                System.out.println("Oops! Não consegui gravar! " + saveException.getMessage());
            }
        }
        scanner = new Scanner(System.in);
    }

    /**
     * Main method for the SpotifUM application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        App app = new App();
        app.run();
    }

    /**
     * Runs the main application loop. Displays the main menu and handles user
     * input.
     */
    private void run() {
        NewMenu menu = new NewMenu(new String[]{
            "Login 🔐",
            "Register 🖋️",});
        menu.setHandler(1, this::doLogin);
        menu.setHandler(2, this::createUser);

        //System.out.println(model.getMusics());
        menu.run();
        try {
            model.save("SpotifUM.obj");
        } catch (IOException e) {
            System.out.println("Oops! Não consegui gravar! " + e.getMessage());
        } finally {
            scanner.close();
        }
    }



    /**
     * Handles user login. Prompts the user for their username and password, and
     * validates them.
     */
    private void doLogin() {
        System.out.println("\nEnter your Username: 👤");
        String userId = scanner.nextLine();
        System.out.println("Enter your Password: 🔒");
        String password = scanner.nextLine();

        if (model.login(userId, password)) {
            if (model.isAdmin(userId)) {
                this.runAdminMenu();
            } else {
                this.runMainMenu(userId);
            }
            closeApp();
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    /**
     * Handles Queries menu.
     */
    private void doQueries() {
        this.runQueryMenu();
        closeApp();
    }

    /**
     * Runs the Queries menu.
     */
    private void runQueryMenu() {
        NewMenu menuQuery = new NewMenu(new String[]{
            "Most listened song 🎵",
            "Most listened artist 🎤",
            "Which users listened to more songs over a period of time or all time ⏱️",
            "User with the most points 🏆",
            "Most listened music genre 🎧",
            "How many public playlists exist 📚",
            "Which user has the most playlists 👥",
            "Exit ❌"
        });
        menuQuery.setHandler(1, this::doQuery1);
        menuQuery.setHandler(2, this::doQuery2);
        menuQuery.setHandler(3, this::doQuery3);
        menuQuery.setHandler(4, this::doQuery4);
        menuQuery.setHandler(5, this::doQuery5);
        menuQuery.setHandler(6, this::doQuery6);
        menuQuery.setHandler(7, this::doQuery7);
        menuQuery.setHandler(8, this::exitApplication);
        menuQuery.run();
    }

    /**
     * Executes Query1
     */
    private void doQuery1() {
        try {
            Music music = model.getMostListenedSong();
            System.out.println("Most listened song: " + music.getName() + " (Plays: " + music.getPlayCount() + ")");
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Executes Query2
     */
    private void doQuery2() {
        try {
            String artistInfo = model.getMostListenedArtist();
            System.out.println("\nMost listened artist: " + artistInfo);
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Executes Query3
     */
    private void doQuery3() {
        try {
            System.out.println("\nDo you want to filter by a specific period? (yes/no) ⏱️");
            String choice = scanner.nextLine();

            String startStr = null;
            String endStr = null;

            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter the start date (yyyy-MM-dd) or leave blank for no start date: 🚩");
                startStr = scanner.nextLine();

                System.out.print("Enter the end date (yyyy-MM-dd) or leave blank for no end date: 🏁");
                endStr = scanner.nextLine();
            }

            User user = model.userMostReproductionsInPeriod(startStr, endStr);

            System.out.println("\nUser who listened to the most songs: 🏅" + user.getNameUser()
                    + " (" + user.getReproductions().size() + " songs)");
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Executes Query4
     */
    private void doQuery4() {
        try {
            User user = model.getUserMostPoints();
            System.out.println("User with most points: " + user.getNameUser() + " (" + user.getPoints() + " points 🏆)");
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Executes Query5
     */
    private void doQuery5() {
        try {
            Genre mostPlayed = model.mostPlayedGenre();
            System.out.println("\nMost listened genre: " + mostPlayed + " 🎧");
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Executes Query6
     */
    private void doQuery6() {
        try {
            int public_playlists = model.getNumPlaylistsPublic();
            System.out.println("Number of public playlists: " + public_playlists + " 📚");

        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Executes Query7
     */
    private void doQuery7() {
        try {
            User userMostPlaylists = model.userMostPlaylistsUserBuilt();
            System.out.println("User with most UserBuilt playlists: " + userMostPlaylists.getNameUser() + " 🎼");
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Runs the admin menu.
     */
    private void runAdminMenu() {
        NewMenu menuAdmin = new NewMenu(new String[]{
            "Handle User 👤",
            "Handle Artist 🎤",
            "Handle Music 🎵",
            "Handle Album 💿",
            "Handle Playlist 📀",
            "Queries ❓",
            "Exit 🚪"
        });

        menuAdmin.setHandler(1, this::handleUser);
        menuAdmin.setHandler(2, this::handleArtist);
        menuAdmin.setHandler(3, this::handleMusic);
        menuAdmin.setHandler(4, this::handleAlbum);
        menuAdmin.setHandler(5, this::handlePlaylist);
        menuAdmin.setHandler(6, this::doQueries);
        menuAdmin.setHandler(7, this::exitApplication);

        menuAdmin.run();
    }

    /**
     * Handles user menu.
     */
    private void handleUser() {
        NewMenu handleUserMenu = new NewMenu(new String[]{
            "Add User ➕👤",
            "View 1 User 🔍👤",
            "View Users 👥",
            "Remove User ❌👤"

        });

        handleUserMenu.setHandler(1, this::createUser);
        handleUserMenu.setHandler(2, () -> viewUser());
        handleUserMenu.setHandler(3, () -> viewUsers());
        //handleUserMenu.setHandler(4, () -> removeUser());

        handleUserMenu.run();
    }

    /**
     * Handles artist menu.
     */
    private void handleArtist() {
        NewMenu handleArtistMenu = new NewMenu(new String[]{
            "Add Artist ➕🎤",
            "View 1 Artist 🔍🎤",
            "View Artists 👥🎤",
            "Remove Artist ❌🎤"
        });

        handleArtistMenu.setHandler(1, this::createArtist);
        handleArtistMenu.setHandler(2, () -> viewArtist());
        handleArtistMenu.setHandler(3, () -> viewArtists());
        //handleArtistMenu.setHandler(4, () -> removeArtist());

        handleArtistMenu.run();
    }

    /**
     * Handles music menu.
     */
    private void handleMusic() {
        NewMenu handleMusicMenu = new NewMenu(new String[]{
            "Add Music ➕🎵",
            "View 1 Music 🔍🎵",
            "View Musics 📄🎵",
            "Remove Music ❌🎵"
        });

        handleMusicMenu.setHandler(1, this::createMusic);
        handleMusicMenu.setHandler(2, () -> viewMusic());
        handleMusicMenu.setHandler(3, () -> viewMusics());
        //handleMusicMenu.setHandler(4, () -> removeMusic());

        handleMusicMenu.run();
    }

    /**
     * Handles album menu.
     */
    private void handleAlbum() {
        NewMenu handleAlbumMenu = new NewMenu(new String[]{
            "Add Album ➕💿",
            "View 1 Album 🔍💿",
            "View Albums 📚💿",
            "Remove Album ❌💿"
        });

        handleAlbumMenu.setHandler(1, this::createAlbum);
        handleAlbumMenu.setHandler(2, () -> viewAlbum());
        handleAlbumMenu.setHandler(3, () -> viewAlbums());
        //handleAlbumMenu.setHandler(4, () -> removeAlbum());

        handleAlbumMenu.run();
    }

    /**
     * Handles playlist menu.
     */
    private void handlePlaylist() {
        NewMenu handlePlaylistMenu = new NewMenu(new String[]{
            "Add Playlist ➕📀",
            "View 1 Playlist 🔍📀",
            "View Playlists 📚📀",
            "Remove Playlist ❌📀"

        });

        handlePlaylistMenu.setHandler(1, this::createPlaylist);
        handlePlaylistMenu.setHandler(2, () -> viewPlaylist());
        handlePlaylistMenu.setHandler(3, () -> viewPlaylists());
        //handlePlaylistMenu.setHandler(4, () -> removePlaylist());

        handlePlaylistMenu.run();
    }

    /**
     * Exits the application.
     */
    private void closeApp() {
        System.out.println("\nSee You Next Time! 👋");

        try {
            model.save("SpotifUM.obj");
        } catch (IOException e) {
            System.out.println("Oops! Não consegui gravar! " + e.getMessage());
        }
    }

    /**
     * Runs Main Menu
     */
    private void runMainMenu(String userID) {
        try {
            String userPlan = model.getTipoPlan(userID); // Obtém o tipo de plano do usuário

            if (userPlan.equals("PlanFree")) {
                runFreeMenu(userID);
            } else if (userPlan.equals("PlanPremiumBase")) {
                runPremiumBaseMenu(userID);
            } else if (userPlan.equals("PlanPremiumTop")) {
                runPremiumTopMenu(userID);
            } else {
                System.out.println("Invalid plan. Returning to main menu.");
                run(); // Volta ao menu principal
            }
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage());
            run(); // Volta ao menu principal em caso de erro
        }
    }

    /**
     * Runs the Free Menu
     */
    private void runFreeMenu(String userID) {
        NewMenu freeMenu = new NewMenu(new String[]{
            "Listen to Music 🎧",
            "Change Subscription 🔄",
            "View My Data 👤",
            "Exit 🚪"
        });

        freeMenu.setHandler(1, () -> listenToMusic(userID));
        freeMenu.setHandler(2, () -> changeSubscription(userID));
        freeMenu.setHandler(3, () -> viewMyData(userID));
        freeMenu.setHandler(4, this::exitApplication);

        freeMenu.run();
    }

    /**
     * Runs the Premium Base Menu
     */
    private void runPremiumBaseMenu(String userID) {
        NewMenu premiumBaseMenu = new NewMenu(new String[]{
            "Listen to Music 🎧",
            "Library 📚",
            "Listen to Public Playlists 📀",
            "Listen to All Albums 💿",
            "View My Data 👤",
            "Change Subscription 🔄"
        });

        premiumBaseMenu.setHandler(1, () -> listenToMusic(userID)); // Use a lambda to pass userID falta fazer
        premiumBaseMenu.setHandler(2, () -> manageLibrary(userID));
        premiumBaseMenu.setHandler(3, () -> listenToPlaylist(userID));
        premiumBaseMenu.setHandler(4, () -> listenToAlbum(userID));
        premiumBaseMenu.setHandler(5, () -> viewMyData(userID));
        premiumBaseMenu.setHandler(6, () -> changeSubscription(userID));

        premiumBaseMenu.run();
    }

    /**
     * Runs the Premium Top Menu
     */
    private void runPremiumTopMenu(String userID) {
        NewMenu premiumTopMenu = new NewMenu(new String[]{
            "Listen to Music 🎧",
            "Library 📚",
            "Listen to Public Playlists 📀",
            "Listen to All Albums 💿",
            "Change Subscription 🔄",
            "View My Data 👤"
        });

        premiumTopMenu.setHandler(1, () -> listenToMusic(userID)); // Use a lambda to pass userID falta fazer
        premiumTopMenu.setHandler(2, () -> manageLibrary(userID));
        premiumTopMenu.setHandler(3, () -> listenToPlaylist(userID));
        premiumTopMenu.setHandler(4, () -> listenToAlbum(userID));
        premiumTopMenu.setHandler(5, () -> changeSubscription(userID));
        premiumTopMenu.setHandler(6, () -> viewMyData(userID));

        premiumTopMenu.run();
    }

    //-------------------------------------------------------------
    // MÉTODOS DO ADMIN
    //-------------------------------------------------------------
    /**
     * Handles the creation of a new user. Prompts the user for their details
     * and adds them to the model.
     */
    private void createUser() {
        System.out.println("\n *** Creating a new user ***");

        System.out.print("👤 Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("🔞 Enter your age:");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.print("🆔 Enter your username: ");
        String id = scanner.nextLine();

        System.out.print("📧 Enter user email: ");
        String email = scanner.nextLine();

        System.out.print("🏠 Enter user address: ");
        String address = scanner.nextLine();

        System.out.print("🔒 Enter user password: ");
        String password = scanner.nextLine();

        String planType = choosePlan();

        // Delegar a criação e adição do usuário para o SpotifUM
        try {
            model.addUserWithDetails(id, name, age, email, address, password, planType);
            System.out.println("User created successfully. ✅");
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage() + " ⚠️");
            System.out.println("Invalid plan type. ❌");
        }
    }

    /**
     * Handles the creation of a new artist. Prompts the user for the artist's
     * name and adds them to the model.
     */
    private void createArtist() {
        System.out.println("\n🎤 Creating a new artist...");

        System.out.print("🎶 Enter artist name: ");
        String name = scanner.nextLine();
        try {
            // Delegar a criação e adição do artista para o SpotifUM
            model.addArtistWithDetails(name);
            System.out.println("Artist created successfully. ✅");
        } catch (SpotifumExecption e) {
            System.out.println("Error: " + e.getMessage() + " ⚠️");
        }
    }

    /**
     * Handles the creation of a new music. Prompts the user for the music's
     * details and adds it to the model.
     */
    private void createMusic() {
        System.out.println("\n🎵 Creating a new music...");

        int albumID = chooseAlbum();

        Album selectedAlbum = model.getAlbum(albumID);
        if (selectedAlbum == null) {
            System.out.println("❌ Album not found. Returning to menu...");
            return;
        }

        String artistID = selectedAlbum.getArtist().getName();

        System.out.print("🎼 Enter music name: ");
        String name = scanner.nextLine();

        System.out.print("🏷️ Enter label name: ");
        String labelName = scanner.nextLine();

        System.out.print("📝 Enter lyrics: ");
        String lyrics = scanner.nextLine();

        List<String> musicLines = new ArrayList<>();
        boolean isDone;
        do {
            System.out.print("✏️ Enter music line (or 'done' to finish): ");
            String line = scanner.nextLine();
            isDone = line.equalsIgnoreCase("done");
            if (!isDone) {
                musicLines.add(line);
            }
        } while (!isDone);

        Genre genre = this.chooseGenre();

        System.out.print("⏱️ Enter duration (in seconds): ");
        int duration = Integer.parseInt(scanner.nextLine());

        try {
            // Delegar a criação e adição da música para o SpotifUM
            model.addMusicWithDetails(name, artistID, labelName, lyrics, musicLines, genre, duration, albumID);

            System.out.println("✅ Music added successfully.");

        } catch (SpotifumExecption e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }

    /**
     * Handles the creation of a new album. Prompts the user for the album's
     * details and adds it to the model.
     */
    private void createAlbum() {
        System.out.println("\n🎶 Creating a new album...");

        //System.out.print("Enter album artist: ");
        String albumArtist = chooseArtist();

        System.out.print("📀 Enter album name: ");
        String albumName = scanner.nextLine();

        int albumId = -1; // Alterado para int
        try {
            albumId = model.createEmptyAlbum(albumArtist, albumName); // Retorna um int
            System.out.println("✅ Album created successfully: " + albumName + " for artist: " + albumArtist);
        } catch (SpotifumExecption e) {
            System.out.println("⚠️ Error: " + e.getMessage());
            return; // Sai do método em caso de erro
        }

        System.out.print("🏷️ Enter label name: ");
        String labelName = scanner.nextLine();

        System.out.print("🎵 Enter the number of musics on the album: ");
        int numMusics = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numMusics; i++) {
            System.out.println("🎼 Music " + (i + 1) + " of " + numMusics);
            this.createMusicOnAlbum(String.valueOf(albumId), albumArtist, labelName); // Converte albumId para String
        }
    }

    /**
     * Handles the creation of a new music on an existing album. Prompts the
     * user for the music's details and adds it to the specified album.
     *
     * @param albumId the ID of the album
     * @param albumArtist the artist of the album
     * @param labelName the label name
     */
    private void createMusicOnAlbum(String albumId, String albumArtist, String labelName) {
        System.out.println("\n🎵 Creating a new music...");

        System.out.print("🎶 Enter music name: ");
        String name = scanner.nextLine();

        System.out.print("📝 Enter lyrics: ");
        String lyrics = scanner.nextLine();

        List<String> musicLines = new ArrayList<>();
        boolean isDone;
        do {
            System.out.print("✍️ Enter music line (or 'done' to finish): ");
            String line = scanner.nextLine();
            isDone = line.equalsIgnoreCase("done");
            if (!isDone) {
                musicLines.add(line);
            }
        } while (!isDone);

        Genre genre = this.chooseGenre();

        System.out.print("⏱️ Enter duration (in seconds): ");
        int duration = Integer.parseInt(scanner.nextLine());

        try {
            // Converte albumId de String para int
            int albumIdInt = Integer.parseInt(albumId);

            // Chama o método com o ID convertido
            model.addMusicWithDetailsToAlbum(albumIdInt, name, albumArtist, labelName, lyrics, musicLines, genre, duration);
            System.out.println("✅ Music added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid album ID format. Please enter a valid number.");
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Handles the creation of a new playlist. Prompts the user for the
     * playlist's details and adds it to the model.
     */
    private void createPlaylist() {
        System.out.println("\n🎶 Creating a new playlist...");

        System.out.print("📛 Enter playlist name: ");
        String name = scanner.nextLine();

        NewMenu playlistMenu = new NewMenu(new String[]{
            "🎲 Create a Random playlist"
        });
        playlistMenu.setHandler(1, () -> createRandomPlaylist(name));

        playlistMenu.runOnce();
    }

    /**
     * Handles the creation of a random playlist. Prompts the user for the
     * number of musics and creates the playlist.
     *
     * @param name the name of the playlist
     */
    private void createRandomPlaylist(String name) {
        System.out.println("\n🎲 Creating a random playlist...");

        System.out.print("Enter the number of musics: ");
        int numMusics = Integer.parseInt(scanner.nextLine());

        try {
            model.createRandomPlaylist(name, numMusics, true);
            System.out.println("✅ Playlist created successfully");
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Handles the creation of a new GenMax, UserBuilt or MadeForYou playlist.
     * Prompts the user for the playlist's details and adds it to the model.
     *
     * @param userId the ID of the user
     */
    private void createUBorGM(String userId) {
        NewMenu menu = new NewMenu(new String[]{
            "🎵 Create a GenMax playlist",
            "👤 Create a UserBuiltPlaylist",
            "👤 Create a MadeForYou playlist"
        });
        menu.setHandler(1, () -> createGenMaxPlaylist(userId));
        menu.setHandler(2, () -> createUserBuiltPlaylist(userId));
        menu.setHandler(3, () -> createFavsPlaylist(userId));
        menu.runOnce();
    }

    /**
     * Handles the creation of a new GenMax playlist. Prompts the user for the
     * playlist's details and adds it to the model.
     *
     * @param userId the ID of the user
     */
    private void createGenMaxPlaylist(String userId) {
        System.out.println("\n🎶 Creating a GenMax playlist...");

        System.out.print("🎵 Enter playlist name: ");
        String name = scanner.nextLine();

        System.out.print("⏳ Enter the max duration of the playlist (in seconds): ");
        int maxduration = Integer.parseInt(scanner.nextLine());

        boolean isPublic = false;
        System.out.println("\n🌐 Do you want to make your playlist public? (yes/NO)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("🔓 Your playlist will be public.");
            isPublic = true;
        } else {
            System.out.println("🔒 Your playlist will be private.");
            isPublic = false;
        }

        try {
            model.createGenMaxPlaylist(userId, name, maxduration, isPublic);
            System.out.println("✅ Playlist created successfully");
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Handles the creation of a new UserBuilt playlist. Prompts the user for
     * the playlist's details and adds it to the model.
     *
     * @param userId the ID of the user
     */
    private void createUserBuiltPlaylist(String userId) {
        System.out.println("\n🎶 Creating a UserBuilt playlist...");

        System.out.print("🎵 Enter playlist name: ");
        String name = scanner.nextLine();

        System.out.print("🎧 Enter the number of musics: ");
        int numMusics = Integer.parseInt(scanner.nextLine());

        boolean isPublic = false;
        System.out.println("\n🌐 Do you want to make your playlist public? (yes/NO)");
        String choice = scanner.nextLine();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.println("🔓 Your playlist will be public.");
            isPublic = true;
        } else {
            System.out.println("🔒 Your playlist will be private.");
            isPublic = false;
        }

        List<Music> selectedMusics = new ArrayList<>();
        for (int i = 0; i < numMusics; i++) {
            System.out.println("\n🎵 Choose music " + (i + 1) + " of " + numMusics + ":");
            Music music = chooseMusic(); // Usa o método existente para selecionar uma música
            if (music != null) {
                selectedMusics.add(music); // Armazena o objeto Music
            } else {
                System.out.println("⚠️ Invalid music selection. Please try again.");
                i--; // Permite que o usuário tente novamente
            }
        }

        try {
            model.createUserBuiltPlaylist(userId, name, selectedMusics, isPublic);
            System.out.println("✅ Playlist created successfully");
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Makes a private playlist public
     *
     * @param userId the ID of the user
     */
    private void makePlaylistPublic(String userId) {
        Playlist playlist = choosePlaylistForUser(userId);
        if (playlist == null) {
            System.out.println("⚠️ No playlist selected.");
            return;
        }
        try {
            model.changePlaylistPublicity(userId, playlist.getIdPlaylist());
            System.out.println("🌟 Playlist is now public and available to all users! 🌍");
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Choooses a genre from the available genres.
     */
    private Genre chooseGenre() {
        System.out.println("\n🎵 Choose a genre:");
        Genre[] genres = Genre.values();
        for (int i = 0; i < genres.length; i++) {
            System.out.println("  " + (i + 1) + " - " + genres[i]);
        }

        int choice = -1;
        while (choice < 1 || choice > genres.length) {
            System.out.print("👉 Enter your choice (1-" + genres.length + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and " + genres.length + ".");
            }
        }

        System.out.println("✅ You selected: " + genres[choice - 1]);
        return genres[choice - 1]; // Retorna o gênero escolhido
    }

    /**
     * Choooses a plan from the available plans.
     */
    private String choosePlan() {
        System.out.println("\nChoose a plan: 📋");  // Emoji na mensagem
        String[] plans = {"PlanFree", "PlanPremiumBase", "PlanPremiumTop"};
        for (int i = 0; i < plans.length; i++) {
            System.out.println((i + 1) + " - " + plans[i]);
        }

        int choice = -1;
        while (choice < 1 || choice > plans.length) {
            System.out.print("Enter your choice (1-" + plans.length + "): "); // sem emoji aqui para não poluir input
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and " + plans.length + ".");
            }
        }

        System.out.println("You chose: " + plans[choice - 1] + " ✅");  // Confirmar com emoji
        return plans[choice - 1]; // Retorna o plano escolhido como string
    }

    /**
     * Choooses an album from the available albums.
     */
    private int chooseAlbum() {
        System.out.println("\n *** Choose an album *** 🎵\n");
        List<Album> albums = new ArrayList<>(model.getAlbums().values());
        if (albums.isEmpty()) {
            System.out.println("🚫 No albums available.");
            return 0; // Retorna 0 se não houver álbuns
        }

        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + " - " + albums.get(i).getNome() + " (Artist: " + albums.get(i).getArtist().getName() + ")");
        }

        int choice = -1;
        while (choice < 1 || choice > albums.size()) {
            System.out.print("\nEnter your choice (1-" + albums.size() + "): ");  // sem emoji no input
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > albums.size()) {
                    System.out.println("❌ Invalid choice. Please enter a number between 1 and " + albums.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        System.out.println("You chose album: " + albums.get(choice - 1).getNome() + " ✅");
        return albums.get(choice - 1).getId(); // Retorna o ID do álbum escolhido
    }

    /**
     * Choooses an album e from the available albums on the user library.
     *
     * @param userId the ID of the user
     */
    private int chooseAlbumFromLibrary(String userId) {
        System.out.println("\n *** Choose an album from your library *** 📚🎵\n");
        List<Album> albums;
        try {
            albums = model.getLibrary(userId).getAlbums();
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
            return 0;
        }
        if (albums.isEmpty()) {
            System.out.println("🚫 No albums available in your library.");
            return 0;
        }

        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + " - " + albums.get(i).getNome() + " (Artist: " + albums.get(i).getArtist().getName() + ")");
        }

        int choice = -1;
        while (choice < 1 || choice > albums.size()) {
            System.out.print("\nEnter your choice (1-" + albums.size() + "): "); // sem emoji na entrada
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > albums.size()) {
                    System.out.println("❌ Invalid choice. Please enter a number between 1 and " + albums.size() + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a valid number.");
            }
        }

        System.out.println("You chose album: " + albums.get(choice - 1).getNome() + " ✅");
        return albums.get(choice - 1).getId(); // Retorna o ID do álbum escolhido da library do user
    }

    /**
     * Choooses an artist from the available artists.
     */
    private String chooseArtist() {
        System.out.println("\n*** Choose an artist *** 🎤🎶\n");
        List<Artist> artists = new ArrayList<>(model.getArtists().values());
        for (int i = 0; i < artists.size(); i++) {
            System.out.println((i + 1) + " - " + artists.get(i).getName());
        }

        int choice = -1;
        while (choice < 1 || choice > artists.size()) {
            System.out.print("Enter your choice (1-" + artists.size() + "): "); // entrada sem emoji
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and " + artists.size() + ".");
            }
        }

        System.out.println("You chose artist: " + artists.get(choice - 1).getName() + " ✅");
        return artists.get(choice - 1).getName(); // Retorna o nome do artista escolhido
    }

    /**
     * Choooses a music from the available musics.
     */
    private Music chooseMusic() {
        System.out.println("\n *** Choose a Music 🎵 ***\n");

        // Lista todas as músicas com ID, nome e artista
        model.getMusics().values().forEach(music -> {
            System.out.println("ID: " + music.getId() + " | Name: " + music.getName() + " | Artist: " + music.getArtist().getName());
        });

        System.out.print("\nEnter the ID of the music: "); // entrada sem emoji para clareza
        try {
            int musicId = Integer.parseInt(scanner.nextLine());
            Music selectedMusic = model.getMusics().get(musicId);

            if (selectedMusic == null) {
                System.out.println("❌ Music not found with ID: " + musicId);
                return null;
            }

            System.out.println("✅ Selected music: " + selectedMusic.getName() + " by " + selectedMusic.getArtist().getName());
            return selectedMusic; // Retorna a música selecionada
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a valid music ID.");
            return null;
        }
    }

    /**
     * Choooses a playlist from the available playlists.
     */
    private Playlist choosePlaylist() {
        System.out.println("\n *** Choose a Playlist 🎶 ***\n");

        // Lista todas as playlists disponíveis
        List<Playlist> playlists = new ArrayList<>(model.getPlaylists().values());
        if (playlists.isEmpty()) {
            System.out.println("⚠️ No playlists available.");
            return null; // Retorna null se não houver playlists
        }

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + " - " + playlists.get(i).getNomePlaylist());
        }

        int choice = -1;
        while (choice < 1 || choice > playlists.size()) {
            System.out.print("\nEnter your choice (1-" + playlists.size() + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number between 1 and " + playlists.size() + ".");
            }
        }

        System.out.println("✅ Playlist selected: " + playlists.get(choice - 1).getNomePlaylist());
        return playlists.get(choice - 1); // Retorna a playlist escolhida
    }

    /**
     * Choooses a playlist from the available playlists in the user library.
     *
     * @param userId the ID of the user
     */
    private Playlist choosePlaylistForUser(String userId) {
        System.out.println("\n🎵 *** Choose a Playlist from Your Library *** 🎵\n");

        List<Playlist> playlists;
        try {
            playlists = model.getLibrary(userId).getPlaylists();
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
            return null;
        }

        if (playlists.isEmpty()) {
            System.out.println("📭 No playlists available in your library.");
            return null;
        }

        for (int i = 0; i < playlists.size(); i++) {
            System.out.println((i + 1) + " - " + playlists.get(i).getNomePlaylist());
        }

        int choice = -1;
        while (choice < 1 || choice > playlists.size()) {
            System.out.print("\n👉 Enter your choice (1-" + playlists.size() + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input. Please enter a number between 1 and " + playlists.size() + ".");
            }
        }

        return playlists.get(choice - 1); // Retorna a playlist escolhida da library do user
    }

    /**
     * Choooses a user from the available users.
     */
    private User chooseUser() {
        System.out.println("\n👥 *** Choose a User *** 👥\n");

        // Lista todos os usuários disponíveis
        List<User> users = new ArrayList<>(model.getUsers().values());
        if (users.isEmpty()) {
            System.out.println("📭 No users available.");
            return null; // Retorna null se não houver usuários
        }

        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + " - " + users.get(i).getNameUser() + " (Username: " + users.get(i).getIdUser() + ")");
        }

        int choice = -1;
        while (choice < 1 || choice > users.size()) {
            System.out.print("\n👉 Enter your choice (1-" + users.size() + "): ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Invalid input. Please enter a number between 1 and " + users.size() + ".");
            }
        }

        return users.get(choice - 1); // Retorna o usuário escolhido
    }

    /**
     * listens to music
     *
     * @param userId the ID of the user
     */
    private void listenToMusic(String userId) {
        Scanner sc = new Scanner(System.in);
        boolean keepListening = true;

        while (keepListening) {
            try {
                String message = model.listenToRandomMusicInteractive(userId);

                System.out.println(message);
            } catch (SpotifumExecption e) {
                System.out.println("❌ Error: " + e.getMessage());
                break;
            }

            System.out.println("\n🎵 Options: [n]ext ▶️, [q]uit ❌");
            String cmd = sc.nextLine();
            if (cmd.equalsIgnoreCase("n")) {
                keepListening = true;
            } else if (cmd.equalsIgnoreCase("q")) {
                keepListening = false;
            } else {
                System.out.println("⚠️ Invalid option. Type 'n' for next or 'q' to quit.");
            }
        }
    }

    /**
     * plays a playlist
     *
     * @param userId the ID of the user
     * @param playlistId the ID of the playlist
     */
    private void playPlaylist(int playlistId, String userId) {
        try {
            List<Playable> playables = model.getPlaylistPlayables(userId, playlistId);

            System.out.println("🎧 Do you want to listen in shuffle mode? (yes/no)");
            String shuffleChoice = scanner.nextLine();
            boolean shuffleMode = shuffleChoice.equalsIgnoreCase("yes");

            if (shuffleMode) {
                playPlaylistShuffle(playables, userId);
            } else {
                playPlaylistNormal(playables, userId);
            }
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * plays a playlist in normal mode
     *
     * @param playables the list of playables
     * @param userId the ID of the user
     */
    private void playPlaylistNormal(List<Playable> playables, String userId) {
        ListIterator<Playable> it = playables.listIterator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions: [n]ext ▶️, [p]revious ◀️, [q]uit ❌");
            String cmd = sc.nextLine();

            if (cmd.equalsIgnoreCase("n")) {
                if (it.hasNext()) {
                    Playable p = it.next();
                    try {
                        System.out.println(model.playMusicForUser(p, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("🚫 No next music.");
                }
            } else if (cmd.equalsIgnoreCase("p")) {
                if (it.hasPrevious()) {
                    Playable p = it.previous();
                    try {
                        System.out.println(model.playMusicForUser(p, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("🚫 No previous music.");
                }
            } else if (cmd.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("⚠️ Invalid option.");
            }
        }
    }

    /**
     * plays a playlist in shuffle mode
     *
     * @param playables the list of playables
     * @param userId the ID of the user
     */
    private void playPlaylistShuffle(List<Playable> playables, String userId) {
        List<Playable> remaining = new ArrayList<>(playables);
        List<Playable> played = new ArrayList<>();
        Playable current = null;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions: [n]ext (random) 🎲, [p]revious ◀️, [q]uit ❌");
            String cmd = sc.nextLine();

            if (cmd.equalsIgnoreCase("n")) {
                if (remaining.isEmpty()) {
                    System.out.println("🚫 No more musics to play.");
                } else {
                    int idx = (int) (Math.random() * remaining.size());
                    current = remaining.remove(idx);
                    played.add(current);
                    try {
                        System.out.println(model.playMusicForUser(current, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
            } else if (cmd.equalsIgnoreCase("p")) {
                if (played.size() <= 1) {
                    System.out.println("🚫 No previous music.");
                } else {
                    played.remove(played.size() - 1);
                    current = played.get(played.size() - 1);
                    remaining.add(current); // Opcional
                    try {
                        System.out.println(model.playMusicForUser(current, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
            } else if (cmd.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("⚠️ Invalid option.");
            }
        }
    }

    /**
     * listens to a playlist
     *
     * @param userId the ID of the user
     */
    private void listenToPlaylist(String userId) {
        Playlist playlist = choosePlaylist();
        if (playlist == null) {
            System.out.println("⚠️ No playlist selected.");
            return;
        }
        playPlaylist(playlist.getIdPlaylist(), userId);
    }

    /**
     * listens to a playlist from the user library
     *
     * @param userId the ID of the user
     */
    private void listenToLibraryPlaylist(String userId) {
        Playlist playlist = choosePlaylistForUser(userId);
        if (playlist == null) {
            System.out.println("⚠️ No playlist selected.");
            return;
        }
        playPlaylist(playlist.getIdPlaylist(), userId);
    }

    /**
     * plays an album
     *
     * @param albumId the ID of the album
     * @param userId the ID of the user
     */
    private void playAlbum(int albumId, String userId) {
        try {
            List<Playable> playables = model.getAlbumPlayables(albumId);

            System.out.println("🎧 Do you want to listen in shuffle mode? (yes/no)");
            String shuffleChoice = scanner.nextLine();
            boolean shuffleMode = shuffleChoice.equalsIgnoreCase("yes");

            if (shuffleMode) {
                playAlbumShuffle(playables, userId);
            } else {
                playAlbumNormal(playables, userId);
            }
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * plays an album in normal mode
     *
     * @param playables the list of playables
     * @param userId the ID of the user
     */
    private void playAlbumNormal(List<Playable> playables, String userId) {
        ListIterator<Playable> it = playables.listIterator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions: [n]ext ▶️, [p]revious ◀️, [q]uit ❌");
            String cmd = sc.nextLine();

            if (cmd.equalsIgnoreCase("n")) {
                if (it.hasNext()) {
                    Playable p = it.next();
                    try {
                        System.out.println(model.playMusicForUser(p, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("🚫 No next music.");
                }
            } else if (cmd.equalsIgnoreCase("p")) {
                if (it.hasPrevious()) {
                    Playable p = it.previous();
                    try {
                        System.out.println(model.playMusicForUser(p, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("🚫 No previous music.");
                }
            } else if (cmd.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("⚠️ Invalid option.");
            }
        }
    }

    /**
     * plays an album in shuffle mode
     *
     * @param playables the list of playables
     * @param userId the ID of the user
     */
    private void playAlbumShuffle(List<Playable> playables, String userId) {
        List<Playable> remaining = new ArrayList<>(playables);
        List<Playable> played = new ArrayList<>();
        Playable current = null;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nOptions: [n]ext (random) 🎲, [p]revious ⏮️, [q]uit ❌");
            String cmd = sc.nextLine();

            if (cmd.equalsIgnoreCase("n")) {
                if (remaining.isEmpty()) {
                    System.out.println("🚫 No more musics to play.");
                } else {
                    int idx = (int) (Math.random() * remaining.size());
                    current = remaining.remove(idx);
                    played.add(current);
                    try {
                        System.out.println(model.playMusicForUser(current, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
            } else if (cmd.equalsIgnoreCase("p")) {
                if (played.size() <= 1) {
                    System.out.println("🚫 No previous music.");
                } else {
                    played.remove(played.size() - 1);
                    current = played.get(played.size() - 1);
                    remaining.add(current); // Opcional
                    try {
                        System.out.println(model.playMusicForUser(current, userId));
                    } catch (SpotifumExecption e) {
                        System.out.println("❌ Error: " + e.getMessage());
                    }
                }
            } else if (cmd.equalsIgnoreCase("q")) {
                break;
            } else {
                System.out.println("⚠️ Invalid option.");
            }
        }
    }

    /**
     * listens to an album
     *
     * @param userId the ID of the user
     */
    private void listenToAlbum(String userId) {
        int albumId = chooseAlbum();
        if (albumId < 0) {
            System.out.println("No album selected.");
            return;
        }
        playAlbum(albumId, userId);
    }

    /**
     * listens to an album from the user library
     *
     * @param userId the ID of the user
     */
    private void listenToLibraryAlbum(String userId) {
        int albumId = chooseAlbumFromLibrary(userId);
        if (albumId < 0) {
            System.out.println("No album selected.");
            return;
        }
        playAlbum(albumId, userId);
    }

    /**
     * Manages the user's library. Displays the library contents and provides
     * options to manage it.
     *
     * @param userID the ID of the user
     */
    private void manageLibrary(String userID) {
        try {
            System.out.println("\n📚 *** Library contents *** \n");
            System.out.println(model.getLibrary(userID));

            // Criação do menu para gerenciar a biblioteca
            NewMenu libraryMenu = new NewMenu(new String[]{
                "Create UserBuilt|GenMax|ForYou Playlist 👤",
                "Add Album 💿",
                "Add Public Playlist 💿",
                "Listen to Playlists ▶️",
                "Listen to Albums 🎧",
                "Make Your Playlists public 🔓",
                "Return to Menu ↩️"
            });

            // Registra os handlers para cada opção
            libraryMenu.setHandler(1, () -> createUBorGM(userID));
            libraryMenu.setHandler(2, () -> addAlbumToLibrary(userID));
            libraryMenu.setHandler(3, () -> addPublicPlaylistToLibrary(userID));
            libraryMenu.setHandler(4, () -> listenToLibraryPlaylist(userID));
            libraryMenu.setHandler(5, () -> listenToLibraryAlbum(userID));
            libraryMenu.setHandler(6, () -> makePlaylistPublic(userID));
            libraryMenu.setHandler(7, () -> System.out.println("🔙 Returning to menu..."));

            // Executa o menu
            libraryMenu.runOnce();
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Adds an album to the user's library. Prompts the user for the album
     * details and adds it to the library.
     *
     * @param userId the ID of the user
     */
    private void addAlbumToLibrary(String userId) {
        System.out.println("\n📀 Available Albums:");
        List<Album> albums = model.getAvailableAlbums();
        for (int i = 0; i < albums.size(); i++) {
            System.out.println((i + 1) + " - " + albums.get(i).getNome());
        }

        System.out.print("Enter the number of the album to add: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > albums.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Album selectedAlbum = albums.get(choice - 1);
        try {
            model.addAlbumToUserLibrary(userId, selectedAlbum);
            System.out.println("✅ Album added to library: " + selectedAlbum.getNome());
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    /**
     * Adds a public playlist to the user's library. Prompts the user for the
     * playlist details and adds it to the library.
     *
     * @param userId the ID of the user
     */
    private void addPublicPlaylistToLibrary(String userId) {
        System.out.println("\n🎵 Available Public Playlists:");
        List<Playlist> publicPlaylists = model.getPublicPlaylists();
        for (int i = 0; i < publicPlaylists.size(); i++) {
            System.out.println((i + 1) + " - " + publicPlaylists.get(i).getNomePlaylist());
        }

        System.out.print("Enter the number of the playlist to add: ");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice < 1 || choice > publicPlaylists.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Playlist selectedPlaylist = publicPlaylists.get(choice - 1);
        try {
            model.addPlaylistToUserLibrary(userId, selectedPlaylist);
            System.out.println("✅ Playlist added to library: " + selectedPlaylist.getNomePlaylist());
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }


    /**
     * Changes the user's subscription plan. Prompts the user for the new plan
     * and updates it in the model.
     *
     * @param userId the ID of the user
     */
    private void changeSubscription(String userId) {
        try {
            System.out.println("\n🔄 Current Plan: " + model.getUsers().get(userId).clone().getTipoPlan());
            String newPlan = choosePlan();
            model.changeSubscription(userId, newPlan);
            System.out.println("✅ Subscription changed successfully to: " + newPlan);
            System.out.println("⭐ Your current points: " + model.getUsers().get(userId).clone().getPoints());
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    //-------------------------------------------------------------
    // MÉTODOS DO USUÁRIO PREMIUM TOP
    //-------------------------------------------------------------
    /**
     * Creates a 'Made for You' playlist. Prompts the user for the playlist's
     * details and adds it to the model.
     *
     * @param userId the ID of the user
     */
    private void createFavsPlaylist(String userId) {
        System.out.println("\n🎵 Creating a 'Made for You' playlist...");
        System.out.print("Do you want a explicit playlist? (yes/no): ");
        String explicitChoice = scanner.nextLine();
        boolean isExplicit = explicitChoice.equalsIgnoreCase("yes");

        try {
            Playlist madeForYou = model.generateMadeForYouPlaylist(userId, isExplicit);
            System.out.println("✅ Playlist created successfully: " + madeForYou.getNomePlaylist());
            System.out.println(madeForYou);
            System.out.println("Do you want to add it to your library? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                model.addPlaylistToUserLibrary(userId, madeForYou);
                System.out.println("📚 Playlist added to library: " + madeForYou.getNomePlaylist());
            } else {
                System.out.println("↩️ Returning to menu...");
            }
        } catch (SpotifumExecption e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    //-------------------------------------------------------------
    // Métodos auxiliares para os handlers
    private void exitApplication() {
        System.out.println("\n👋 Exiting application. Goodbye!");
        closeApp(); // Salva o estado e finaliza
        System.exit(0); // Encerra o programa
    }

    //-------------------------------------------------------------
    // Métodos View Interface
    //-------------------------------------------------------------
    /**
     * Displays the list of users in the system. If no users are found, prompts
     * the user to create one.
     */
    private void viewUsers() {
        System.out.println("\n👥 *** List of Users *** 👥\n");
        model.getUsers().values().forEach(user -> {
            System.out.println("👤 Name: " + user.getNameUser());
            System.out.println("🔞 Age " + user.getAge());
            System.out.println("🆔 Username: " + user.getIdUser());
            System.out.println("🔑 Password: " + user.getPassword());
            System.out.println("📄 Plan: " + user.getTipoPlan());
            System.out.println("📧 Email: " + user.getEmail());
            System.out.println("🏠 Address: " + user.getAdress());
            System.out.println("⭐ Points: " + user.getPoints());
            System.out.println("🎵 Number of reproductions: " + user.getReproductions().size());
            System.out.println();
        });
    }


    /**
     * Displays detailed information about a specific user. If no user is
     * selected, prompts the user to create one.
     */
    private void viewUser() {
        User selectedUser = chooseUser(); // Usa o método auxiliar para escolher o usuário
        if (selectedUser == null) {
            System.out.println("❌ No user selected. Do you want to create one? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                createUser(); // Cria um novo usuário
            } else {
                System.out.println("🔙 Returning to menu...");
            }
            return;
        }

        // Exibe as informações detalhadas do usuário selecionado
        System.out.println("\n👤 *** User Information *** 👤\n");
        System.out.println("👤 Name: " + selectedUser.getNameUser());
        System.out.println("🔞 Age " + selectedUser.getAge());
        System.out.println("🆔 Username: " + selectedUser.getIdUser());
        System.out.println("🔑 Password: " + selectedUser.getPassword());
        System.out.println("📄 Plan: " + selectedUser.getTipoPlan());
        System.out.println("📧 Email: " + selectedUser.getEmail());
        System.out.println("🏠 Address: " + selectedUser.getAdress());
        System.out.println("⭐ Points: " + selectedUser.getPoints());
        System.out.println("🎵 Reproductions: " + selectedUser.getReproductions().size());
    }


    /**
     * Displays the list of artists in the system. If no artists are found,
     * prompts the user to create one.
     */
    private void viewArtists() {
        System.out.println("\n🎤 *** List of Artists *** 🎤\n");
        model.getArtists().values().forEach(artist -> {
            System.out.println("🎨 Name: " + artist.getName());
            System.out.println("🎧 Reproductions: " + artist.getReproductions());
            System.out.println("");
        });
    }


    /**
     * Displays detailed information about a specific artist. If no artist is
     * selected, prompts the user to create one.
     */
    private void viewArtist() {
        String artistName = chooseArtist(); // Usa o método auxiliar para escolher o artista
        if (artistName == null) {
            System.out.println("⚠️ No artist selected. Returning to menu...");
            return;
        }

        try {
            Artist artist = model.getArtists().get(artistName);
            if (artist == null) {
                System.out.println("❌ Artist not found: " + artistName);
                System.out.println("Do you want to add this artist? (yes/no)");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {
                    model.addArtistWithDetails(artistName); // Cria o artista com o nome fornecido
                    System.out.println("✅ Artist created successfully.");
                } else {
                    System.out.println("🔙 Returning to menu...");
                }
            } else {
                System.out.println("\n🎤 *** Artist Information *** 🎤\n");
                System.out.println("🎨 Name: " + artist.getName());
                System.out.println("🎧 Reproductions: " + artist.getReproductions());
            }
        } catch (SpotifumExecption e) {
            System.out.println("⚠️ An error occurred: " + e.getMessage());
        }
    }

    /**
     * Displays the list of musics in the system. If no musics are found,
     * prompts the user to create one.
     */
    private void viewMusics() {

        System.out.println("\n🎵 *** List of Musics ***\n");
        model.getMusics().values().forEach(music -> {
            System.out.println("🎼 Name: " + music.getName());
            System.out.println("🎤 Artist: " + music.getArtist().getName());
            Album album = model.getAlbums().get(music.getAlbumId());
            System.out.println("💿 Album: " + (album != null ? album.getNome() : "Unknown"));
            System.out.println("⏱ Duration: " + music.getDuration() + " seconds");
            System.out.println("");
        });
    }

    /**
     * Displays detailed information about a specific music. If no music is
     * selected, prompts the user to create one.
     */
    private void viewMusic() {
        Music selectedMusic = chooseMusic(); // Usa o método auxiliar para escolher a música
        if (selectedMusic == null) {
            System.out.println("⚠️ No music selected. Returning to menu...");
            return;
        }

        // Exibe as informações detalhadas da música selecionada
        System.out.println("\n🎶 *** Music Information *** 🎶\n");
        System.out.println("🎼 Name: " + selectedMusic.getName());
        System.out.println("🎤 Artist: " + selectedMusic.getArtist().getName());
        Album album = model.getAlbums().get(selectedMusic.getAlbumId());
        System.out.println("💿 Album: " + (album != null ? album.getNome() : "Unknown"));
        System.out.println("⏱ Duration: " + selectedMusic.getDuration() + " seconds");
    }


    /**
     * Displays the list of albums in the system. If no albums are found,
     * prompts the user to create one.
     */
    private void viewAlbums() {
        System.out.println("\n💿 *** List of Albums *** 💿\n");
        model.getAlbums().values().forEach(album -> {
            System.out.println("📀 Name: " + album.getNome());
            System.out.println("🎤 Artist: " + album.getArtist().getName());
            System.out.println("🎵 Musics: " + album.getMusics().size());
            if (album.getMusics().isEmpty()) {
                System.out.println("  ⚠️ No musics available");
            } else {
                album.getMusics().forEach(music -> System.out.println("  - 🎶 " + music.getName()));
            }
            System.out.println("");
        });
    }


    /**
     * Displays detailed information about a specific album. If no album is
     * selected, prompts the user to create one.
     */
    private void viewAlbum() {
        int albumId = chooseAlbum(); // Usa a função chooseAlbum para selecionar o álbum
        if (albumId < 0) { // Caso nenhum álbum esteja disponível ou selecionado
            System.out.println("No album selected. Returning to menu...");
            return;
        }

        try {
            Album album = model.getAlbums().get(albumId);
            if (album == null) {
                System.out.println("❌ Album not found with ID: " + albumId);
            } else {
                System.out.println("\n💿 *** Album Information *** 💿\n");
                System.out.println("📀 Name: " + album.getNome());
                System.out.println("🎤 Artist: " + album.getArtist().getName());
                System.out.println("🎵 Number of Musics: " + album.getMusics().size());
                System.out.println("🎶 Musics:");
                if (album.getMusics().isEmpty()) {
                    System.out.println("  ⚠️ No musics available");
                } else {
                    album.getMusics().forEach(music -> System.out.println("  - 🎵 " + music.getName()));
                }
            }
        } catch (Exception e) {
            System.out.println("⚠️ An error occurred: " + e.getMessage());
        }
    }

    /* public void viewPlaylists() {
        System.out.println("\n *** List of Playlists ***\n");
        model.getPlaylists().values().forEach(playlist -> {
            System.out.println("Name: " + playlist.getNomePlaylist());
            System.out.println("Musics: " + playlist.getMusicsPlaylist().size());
            if (playlist.getMusicsPlaylist().isEmpty()) {
                System.out.println(" - No musics available");
            } else {
                playlist.getMusicsPlaylist().forEach(music -> System.out.println(" - " + music.getName()));
            }
            System.out.println("");
        });
    } */
    private void viewPlaylists() {
        System.out.println("\n *** Playlists *** \n");

        // Obtém playlists públicas do modelo
        List<Playlist> publicPlaylists = model.getPublicPlaylists();

        // Obtém playlists privadas da biblioteca do usuário
        List<Playlist> privatePlaylists = model.getPrivatePlaylistsFromLibraries();

        // Exibe playlists públicas
        System.out.println("🌍 Public Playlists:");
        for (int i = 0; i < publicPlaylists.size(); i++) {
            System.out.println("  " + (i + 1) + " - 🎶 " + publicPlaylists.get(i).getNomePlaylist());
        }

        // Exibe playlists privadas
        System.out.println("\n🔒 Private Playlists:");
        for (int i = 0; i < privatePlaylists.size(); i++) {
            System.out.println("  " + (i + 1 + publicPlaylists.size()) + " - 🎵 " + privatePlaylists.get(i).getNomePlaylist());
        }
    }

    /**
     * Displays detailed information about a specific playlist. If no playlist
     * is selected, prompts the user to create one.
     */
    public void viewPlaylist() {
        Playlist selectedPlaylist = choosePlaylist(); // Usa o método auxiliar para escolher a playlist
        if (selectedPlaylist == null) {
            System.out.println("❌ No playlist selected. Do you want to create one? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                createPlaylist(); // Cria uma nova playlist
            } else {
                System.out.println("🔙 Returning to menu...");
            }
            return;
        }

        // Exibe as informações detalhadas da playlist selecionada
        System.out.println("\n🎵 *** Playlist Information *** 🎵\n");
        System.out.println("📛 Name: " + selectedPlaylist.getNomePlaylist());
        System.out.println("🎶 Musics: " + selectedPlaylist.getMusicsPlaylist().size());
        if (selectedPlaylist.getMusicsPlaylist().isEmpty()) {
            System.out.println(" - 🚫 No musics available");
        } else {
            selectedPlaylist.getMusicsPlaylist().forEach(music -> System.out.println(" - 🎼 " + music.getName()));
        }
    }

    /**
     * Displays the user's data. If no user is selected, prompts the user to
     * create one.
     *
     * @param userId the ID of the user
     */
    private void viewMyData(String userId) {
        User user = model.getUsers().get(userId); // clone para os outros dados
        if (user == null) {
            System.out.println("❌ User not found.");
            return;
        }
        System.out.println("\n👤 *** My Data *** 👤\n");
        System.out.println("🧑 Name: " + user.getNameUser());
        System.out.println("🔞 Age: " + user.getAge());
        System.out.println("🆔 Username: " + user.getIdUser());
        System.out.println("🔒 Password: " + user.getPassword());
        try {
            System.out.println("📄 Plan: " + model.getTipoPlan(userId));
        } catch (SpotifumExecption e) {
            System.out.println("📄 Plan: (error)");
        }
        System.out.println("📧 Email: " + user.getEmail());
        System.out.println("🏠 Address: " + user.getAdress());
        System.out.println("⭐ Points: " + user.getPoints());
        System.out.println("🎧 Reproductions: " + user.getReproductions().size());

        // Top 3 Artists formatado
        System.out.println("\n🔥 Top 3 Artists:");
        List<String> artists = user.getTop3Artists();
        List<Integer> reps = user.getTop3ArtistReproductions();
        for (int i = 0; i < artists.size(); i++) {
            System.out.println("  🎤 " + artists.get(i) + " - " + reps.get(i) + " reproductions");
        }

        // Música mais ouvida formatada
        System.out.println("\n🎵 Most Listened Music: " + user.getMostListenedMusic() + " (" + user.getMostListenedMusicCount() + " reproduções)");
    }

}
