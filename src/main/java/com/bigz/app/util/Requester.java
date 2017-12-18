package com.bigz.app.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Requester {


    public List<ImageFromWeb> getImageFromWebList() {
        return imageFromWebList;
    }

    List<ImageFromWeb> imageFromWebList = new ArrayList<>();

    public List<ImageFromWeb> sendPost(String name) throws Exception {

        String url = "https://pixabay.com/api/?key=6669031-d6bc2b04f21aaa19ee97f3690&q="+name.replaceAll(" ","+")
                +"&min_width=700&min_height=700&image_type=photo&pretty=true";

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        JsonParser parser = new JsonParser();

        for (JsonElement hit : parser.parse(response.toString()).getAsJsonObject().get("hits").getAsJsonArray()
                ) {
            String pr = hit.getAsJsonObject().get("previewURL").getAsString();
            String full = hit.getAsJsonObject().get("webformatURL").getAsString();
            imageFromWebList.add(new ImageFromWeb(pr,full));
        }
        
        return imageFromWebList;
    }
}
