package fr.lernejo.fileinjector;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Game {
    public final Number id;
    public final String title;
    public final String thumbnail;
    public final String short_description;
    public final String genre;
    public final String platform;
    public final String publisher;
    public final String developer;
    public final String release_date;
    public final String game_url;
    public final String freetogame_profile_url;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Game(@JsonProperty("id") Number id, @JsonProperty("title") String title, @JsonProperty("thumbnail") String thumbnail,
        @JsonProperty("short_description")String short_description, @JsonProperty("genre")String genre, @JsonProperty("platform")String platform,
        @JsonProperty("publisher")String publisher, @JsonProperty("developer")String developer, @JsonProperty("release_date")String release_date,
        @JsonProperty("game_url")String game_url, @JsonProperty("freetogame_profile_url")String freetogame_profile_url) {
        this.id = id; this.title = title;
        this.thumbnail = thumbnail; this.short_description = short_description;
        this.genre = genre; this.platform = platform;
        this.publisher = publisher; this.developer = developer;
        this.release_date = release_date; this.game_url = game_url;
        this.freetogame_profile_url = freetogame_profile_url;
    }
}
