package com.spotifystream.sptofyapp.models;

/**
 * Created by bri6ko on 3/6/14.
 */
public class AlbumTracks {
    private String name;

    public AlbumTracks(String name) {
        this.name = name;
    }

    public AlbumTracks() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AlbumTracks{" +
                "name='" + name + '\'' +
                '}';
    }

}
