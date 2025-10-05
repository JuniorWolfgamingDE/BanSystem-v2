package net.coalcube.bansystem.core.util;

import net.coalcube.bansystem.core.BanSystem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UpdateChecker {
    private String owner;
    private String repo;
    private String newVersion;

    public UpdateChecker(String owner, String repo) {
        this.owner = owner;
        this.repo = repo;
        this.newVersion = BanSystem.getInstance().getVersion();
    }

    public String getResourceUrl() {
        return "https://github.com/" + owner + "/" + repo + "/releases/latest";
    }

    public boolean checkForUpdates() throws Exception {
        URL url = new URL("https://api.github.com/repos/" + owner + "/" + repo + "/releases/latest");
        URLConnection con = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
        JsonObject json = JsonParser.parseReader(reader).getAsJsonObject();
        reader.close();
        newVersion = json.get("tag_name").getAsString();
        return !BanSystem.getInstance().getVersion().equals(newVersion);
    }
}