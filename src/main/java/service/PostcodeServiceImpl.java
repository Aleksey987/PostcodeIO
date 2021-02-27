package service;

import com.google.gson.*;
import dto.PostcodeInfo;
import exception.ApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class PostcodeServiceImpl implements PostcodeService {

    public static String LOOKUP_URL = "http://api.postcodes.io/postcodes/";
    public static String ERROR = "error";
    public static String RESULT = "result";

    @Override
    public boolean validatePostcode(String postcode) {
        JsonObject obj;
        try {
            URL url = new URL(LOOKUP_URL + postcode + "/validate");
            obj = getJson(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj.get(RESULT).getAsBoolean();
    }

    @Override
    public PostcodeInfo getPostcodeInfo(String postcode) {
        JsonObject obj;
        try {
            URL url = new URL(LOOKUP_URL + postcode);
            obj = getJson(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonObject resultObj = obj.getAsJsonObject(RESULT);
        return new Gson().fromJson(resultObj, PostcodeInfo.class);
    }

    @Override
    public List<PostcodeInfo> getNearestPostcodes(String postcode) {
        JsonObject obj;
        try {
            URL url = new URL(LOOKUP_URL + postcode + "/nearest");
            obj = getJson(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        JsonArray jsonArray = obj.getAsJsonArray(RESULT);
        return Arrays.asList(new Gson().fromJson(jsonArray, PostcodeInfo[].class));
    }

    public JsonObject getJson(URL url) throws Exception {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        con.connect();

        int responseCode = con.getResponseCode();

        if (responseCode != 200) {
            JsonObject jsonObject = parseResponse(con.getErrorStream());
            throw new ApiException(jsonObject.get(ERROR).getAsString());
        }
        return parseResponse(con.getInputStream());
    }

    private JsonObject parseResponse(InputStream inputStream) throws IOException {
        StringBuilder content;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }
        JsonParser parser = new JsonParser();
        return parser.parse(content.toString()).getAsJsonObject();
    }
}
