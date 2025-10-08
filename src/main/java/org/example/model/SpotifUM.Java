package org.example.model;

public class SpotifUM{
    private map<String, Playlist> playlistMap;
    private map<String, User> userMap;
    private map<String, Album> albumMap;

    public SpotifUM() {
        this.playlistMap = new HashMap<>();
        this.userMap = new HashMap<>();
        this.albumMap = new HashMap<>();
    }

    public SpotifUM(Map<String, Playlist> playlistMap, Map<String, User> userMap, Map<String, Album> albumMap) {
        this.playlistMap = playlistMap != null ? new HashMap<>(playlistMap) : new HashMap<>();
        this.userMap = userMap != null ? new HashMap<>(userMap) : new HashMap<>();
        this.albumMap = albumMap != null ? new HashMap<>(albumMap) : new HashMap<>();
    }

    public(SpotifUM spotifUM) {
        this.playlistMap = new HashMap<>(spotifUM.playlistMap);
        this.userMap = new HashMap<>(spotifUM.userMap);
        this.albumMap = new HashMap<>(spotifUM.albumMap);
    }

    @Override
    public String toString() {
        return "SpotifUM{" +
                "playlistMap=" + playlistMap +
                ", userMap=" + userMap +
                ", albumMap=" + albumMap +
                '}';
    }
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SpotifUM)) return false;
        SpotifUM spotifUM = (SpotifUM) o;
        return Objects.equals(playlistMap, spotifUM.playlistMap) &&
                Objects.equals(userMap, spotifUM.userMap) &&
                Objects.equals(albumMap, spotifUM.albumMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playlistMap, userMap, albumMap);
    }

    @Override
    public clone() {
        return new SpotifUM(this);
    }

}