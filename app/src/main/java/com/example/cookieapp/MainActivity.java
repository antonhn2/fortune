package com.example.cookieapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import org.json.JSONObject;
import org.json.JSONException;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStreamReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import android.os.StrictMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button lookup = (Button) findViewById(R.id.button1);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        lookup.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                TextView name = (TextView) findViewById(R.id.text);
                try{
                    JSONObject result = getJSONObjectFromURL("https://api.adviceslip.com/advice");
                    //JsonObject advice = parser.parse(json).getAsJsonObject();
                    JSONObject fortune = result.getJSONObject("slip");
                    name.setText(fortune.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                TextView name2 = (TextView) findViewById(R.id.text2);
                name2.setText(luckyNumber() + " " + luckyNumber() + " " + luckyNumber() +
                        " " + luckyNumber() + " " + luckyNumber() + " " + luckyNumber());
            }
        });
    }

    private int luckyNumber() {
        Random random = new Random();
        return random.nextInt(61);
    }
    public static JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {
        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000 /* milliseconds */);
        urlConnection.setConnectTimeout(15000 /* milliseconds */);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println("JSON: " + jsonString);

        return new JSONObject(jsonString);
    }
}
