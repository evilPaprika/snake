package com.bigz.app.util;

/**
 * Created by User on 11.12.2017.
 */
public class ImageFromWeb {

    private String preview;
    private String origin;

    public ImageFromWeb(String preview, String origin) {
        this.preview = preview;
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "ImageFromWeb{" +
                "preview='" + preview + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }

    public String getPreview() {
        return preview;
    }

    public String getOrigin() {
        return origin;
    }
}
