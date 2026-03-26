package org.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class that represents a user in the Spotifum system.
 */
public class User implements Serializable {

    private String username;
    private Plan plan;
    private String name;
    private int age;
    private String email;
    private String address;
    private int points;
    private String password;
    private List<Reproduction> reproductions;
    private Library library;
    private List<String> top3Artists;
    private List<Integer> top3ArtistReproductions;
    private String mostListenedMusic;
    private int mostListenedMusicCount;

    //------------------------------------------------------------------------------------------------------------
    /**
     * Default constructor for User.
     */
    public User() {
        this.username = "";
        this.plan = new PlanFree();
        this.name = "";
        this.age = 0;
        this.email = "";
        this.address = "";
        this.points = 0;
        this.password = "";
        this.reproductions = new ArrayList<>();
        this.library = new Library();
        this.top3Artists = new ArrayList<>();
        this.top3ArtistReproductions = new ArrayList<>();
        this.mostListenedMusic = "";
        this.mostListenedMusicCount = 0;
    }

    /**
     * Constructor for User with parameters.
     *
     * @param id Username
     * @param plan Subscription plan
     * @param name User's name
     * @param email User's email
     * @param address User's address
     * @param points User's points
     * @param password User's password
     * @param reproductions List of reproductions
     */
    public User(String id, Plan plan, String name, int age, String email, String address, int points, String password, List<Reproduction> reproductions) {
        this.username = id;
        this.plan = plan;
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
        this.points = points;
        this.password = password;
        this.reproductions = reproductions;
        this.library = new Library();
        this.top3Artists = new ArrayList<>();
        this.top3ArtistReproductions = new ArrayList<>();
        this.mostListenedMusic = "";
        this.mostListenedMusicCount = 0;
    }

    /**
     * Copy constructor for User.
     *
     * @param user User to be copied
     */
    public User(User user) {
        this.username = user.getIdUser();
        this.plan = user.getPlan().clone();
        this.name = user.getNameUser();
        this.age = user.getAge();
        this.email = user.getEmail();
        this.address = user.getAdress();
        this.points = user.getPoints();
        this.password = user.getPassword();
        this.reproductions = new ArrayList<>();
        user.getReproductions().forEach(m -> this.reproductions.add(m.clone()));
        this.library = (user.getLibrary() == null) ? null : user.getLibrary().clone();
        this.top3Artists = new ArrayList<>(user.top3Artists);
        this.top3ArtistReproductions = new ArrayList<>(user.top3ArtistReproductions);
        this.mostListenedMusic = user.mostListenedMusic;
        this.mostListenedMusicCount = user.mostListenedMusicCount;
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Gets the user's username.
     *
     * @return Username
     */
    public String getIdUser() {
        return this.username;
    }

    /**
     * Sets the user's username.
     *
     * @param id New username
     */
    public void setIdUser(String id) {
        this.username = id;
    }

    /**
     * Gets the user's subscription plan.
     *
     * @return Plan
     */
    public Plan getPlan() {
        return this.plan;
    }

    /**
     * Sets the user's subscription plan.
     *
     * @param plan New plan
     */
    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * Gets the user's name.
     *
     * @return Name
     */
    public String getNameUser() {
        return this.name;
    }

    /**
     * Sets the user's name.
     *
     * @param name New name
     */
    public void setNameUser(String name) {
        this.name = name;
    }

    /**
     * Gets the user's age.
     *
     * @return Age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the user's age.
     *
     * @param age New age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the user's email.
     *
     * @return Email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Sets the user's email.
     *
     * @param email New email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's address.
     *
     * @return Address
     */
    public String getAdress() {
        return this.address;
    }

    /**
     * Sets the user's address.
     *
     * @param address New address
     */
    public void setAdress(String address) {
        this.address = address;
    }

    /**
     * Gets the user's points.
     *
     * @return Points
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Sets the user's points.
     *
     * @param points New points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Gets the user's password.
     *
     * @return Password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Gets a copy of the user's reproductions.
     *
     * @return List of reproductions
     */
    public List<Reproduction> getReproductions() {
        List<Reproduction> copy = new ArrayList<>();
        for (Reproduction r : this.reproductions) {
            copy.add(r.clone());
        }
        return copy;
    }

    /**
     * Sets the user's reproductions.
     *
     * @param reproductions New list of reproductions
     */
    public void setReproductions(List<Reproduction> reproductions) {
        this.reproductions = reproductions;
    }

    /**
     * Gets the user's library.
     *
     * @return Library
     */
    public Library getLibrary() {
        return this.library == null ? null : this.library.clone();
    }

    /**
     * Sets the user's library.
     *
     * @param library New library
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    /**
     * Sets the user's password.
     *
     * @param password New password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Validates the user's password.
     *
     * @param password Password to validate
     * @return true if valid, false otherwise
     */
    public boolean validaPassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Gets the top 3 artists listened to by the user.
     *
     * @return List of top 3 artists
     */
    public List<String> getTop3Artists() {
        return new ArrayList<>(this.top3Artists);
    }

    /**
     * Sets the top 3 artists listened to by the user.
     *
     * @param top3Artists List of top 3 artists
     */
    public void setTop3Artists(List<String> top3Artists) {
        this.top3Artists = new ArrayList<>(top3Artists);
    }

    /**
     * Gets the number of reproductions for the top 3 artists.
     *
     * @return List of reproduction counts
     */
    public List<Integer> getTop3ArtistReproductions() {
        return new ArrayList<>(this.top3ArtistReproductions);
    }

    /**
     * Sets the number of reproductions for the top 3 artists.
     *
     * @param top3ArtistReproductions List of reproduction counts
     */
    public void setTop3ArtistReproductions(List<Integer> top3ArtistReproductions) {
        this.top3ArtistReproductions = new ArrayList<>(top3ArtistReproductions);
    }

    /**
     * Gets the most listened music.
     *
     * @return Most listened music
     */
    public String getMostListenedMusic() {
        return this.mostListenedMusic;
    }

    /**
     * Sets the most listened music.
     *
     * @param mostListenedMusic Most listened music
     */
    public void setMostListenedMusic(String mostListenedMusic) {
        this.mostListenedMusic = mostListenedMusic;
    }

    /**
     * Gets the number of times the most listened music was played.
     *
     * @return Play count
     */
    public int getMostListenedMusicCount() {
        return this.mostListenedMusicCount;
    }

    /**
     * Sets the number of times the most listened music was played.
     *
     * @param mostListenedMusicCount Play count
     */
    public void setMostListenedMusicCount(int mostListenedMusicCount) {
        this.mostListenedMusicCount = mostListenedMusicCount;
    }

    /**
     * Registers a listen for a music.
     *
     * @param m Music listened to
     */
    public void registerListen(Music m) {
        this.points = this.plan.getUpdatedPoints(this.points);
        Reproduction reproduction = new Reproduction(m, LocalDateTime.now());
        this.reproductions.add(reproduction);
        updateListeningStats();
    }

    /**
     * Updates the user's listening statistics (top artists and most listened
     * music).
     */
    private void updateListeningStats() {
        // Count reproductions per artist
        Map<String, Integer> artistCounts = new HashMap<>();
        Map<String, Integer> musicCounts = new HashMap<>();

        for (Reproduction r : this.reproductions) {
            String artist = r.getMusic().getArtist().getName();
            String music = r.getMusic().getName();
            artistCounts.put(artist, artistCounts.getOrDefault(artist, 0) + 1);
            musicCounts.put(music, musicCounts.getOrDefault(music, 0) + 1);
        }

        // Top 3 artists
        List<Map.Entry<String, Integer>> sortedArtists = new ArrayList<>(artistCounts.entrySet());
        sortedArtists.sort((a, b) -> b.getValue() - a.getValue());

        this.top3Artists = new ArrayList<>();
        this.top3ArtistReproductions = new ArrayList<>();
        for (int i = 0; i < Math.min(3, sortedArtists.size()); i++) {
            this.top3Artists.add(sortedArtists.get(i).getKey());
            this.top3ArtistReproductions.add(sortedArtists.get(i).getValue());
        }

        // Most listened music
        String topMusic = "";
        int topCount = 0;
        for (Map.Entry<String, Integer> entry : musicCounts.entrySet()) {
            if (entry.getValue() > topCount) {
                topMusic = entry.getKey();
                topCount = entry.getValue();
            }
        }
        this.mostListenedMusic = topMusic;
        this.mostListenedMusicCount = topCount;
    }

    /**
     * Changes the user's subscription plan.
     *
     * @param newPlanType New plan type ("PlanFree", "PlanPremiumBase",
     * "PlanPremiumTop")
     * @throws SpotifumExecption If the plan type is invalid or already active
     */
    public void changeSubscription(String newPlanType) throws SpotifumExecption {
        Plan newPlan;
        switch (newPlanType) {
            case "PlanFree" ->
                newPlan = new PlanFree();
            case "PlanPremiumBase" ->
                newPlan = new PlanPremiumBase();
            case "PlanPremiumTop" -> {
                newPlan = new PlanPremiumTop();
                if (!(this.plan instanceof PlanPremiumTop)) {
                    this.points += 100; // Add 100 points for upgrading to PremiumTop
                }
            }
            default ->
                throw new SpotifumExecption("Invalid plan type");
        }
        if (this.plan.getPlanType().equals(newPlan.getPlanType())) {
            throw new SpotifumExecption("You are already on this plan");
        }
        this.plan = newPlan; // Updates the user's plan
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Returns a string representation of the user.
     *
     * @return String with user information
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User username: ").append(this.username)
                .append("\nUser Name: ").append(this.name)
                .append("\nUser Age: ").append(this.age)
                .append("\nUser Email: ").append(this.email)
                .append("\nUser Address: ").append(this.address)
                .append("\nUser Points: ").append(this.points)
                .append("\nUser Password: ").append(this.password)
                .append("\nUser Plan: ").append(this.plan.toString())
                .append("\nReproductions: ").append(this.reproductions.toString())
                .append("\nLibrary: ").append(this.library != null ? this.library.toString() : "null")
                .append("\nTop 3 Artists: ").append(this.top3Artists)
                .append("\nTop 3 Artist Reproductions: ").append(this.top3ArtistReproductions)
                .append("\nMost Listened Music: ").append(this.mostListenedMusic)
                .append("\nMost Listened Music Count: ").append(this.mostListenedMusicCount)
                .append("\n\n");
        return sb.toString();
    }

    /**
     * Checks if two users are equal.
     *
     * @param o Object to compare
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        User user = (User) o;
        return this.username.equals(user.getIdUser())
                && this.plan.equals(user.getPlan())
                && this.name.equals(user.getNameUser())
                && this.email.equals(user.getEmail())
                && this.address.equals(user.getAdress())
                && this.points == user.getPoints()
                && this.password.equals(user.getPassword())
                && this.reproductions.equals(user.getReproductions())
                && this.library.equals(user.getLibrary())
                && this.top3Artists.equals(user.getTop3Artists())
                && this.top3ArtistReproductions.equals(user.getTop3ArtistReproductions())
                && this.mostListenedMusic.equals(user.getMostListenedMusic())
                && this.mostListenedMusicCount == user.getMostListenedMusicCount();
    }

    /**
     * Creates a copy of the user.
     *
     * @return New User object (clone)
     */
    public User clone() {
        return new User(this);
    }

    //------------------------------------------------------------------------------------------------------------
    /**
     * Gets the type of the user's plan.
     *
     * @return Plan type
     */
    public String getTipoPlan() {
        return this.plan.getClass().getSimpleName();
    }

    /**
     * Checks if the user has a library.
     *
     * @return true if has library, false otherwise
     */
    public boolean hasLibrary() {
        return this.library != null;
    }

    /**
     * Adds a playlist to the user's library.
     *
     * @param playlist Playlist to add
     */
    public void addPlaylistToLibrary(Playlist playlist) {
        this.library.addPlaylist(playlist);
    }

    /**
     * Adds an album to the user's library.
     *
     * @param album Album to add
     */
    public void addAlbumToLibrary(Album album) {
        this.library.addAlbum(album);
    }

    /**
     * Adds a reproduction to the user's list.
     *
     * @param reproduction Reproduction to add
     */
    public void addReproduction(Reproduction reproduction) {
        this.reproductions.add(reproduction);
    }

}
