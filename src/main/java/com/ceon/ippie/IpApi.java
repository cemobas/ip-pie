package com.ceon.ippie;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class IpApi {

    public String getIpData(String ip) {
        try {
            URL url = new URL("https://ip-api.io/json/"+ip);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output, response = "";
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                response = output;
            }
            conn.disconnect();
            return response;

        } catch (Exception e) {
            System.out.println("Exception in ip-api.io:- " + e);
        }
        return "{}";
    }
}
