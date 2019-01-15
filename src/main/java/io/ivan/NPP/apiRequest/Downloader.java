package io.ivan.NPP.apiRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader implements IDownloader {

    HttpURLConnection c = null;

    public String downloadData(String url) {
        try {
            URL u = new URL(url);
            c = (HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length", "0");
            c.setRequestProperty("X-Auth-Token", "e52b3984242149d79468a190e67652cb");
            c.setConnectTimeout(15000);
            c.setReadTimeout(15000);
            c.connect();

            int status = c.getResponseCode();
            switch (status) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));

                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                return sb.toString();
            }
        } catch (Exception e) {
            System.out.println("Something went wrong.Please try again");
        } finally {
            if (c != null) {
                try {
                    c.disconnect();
                } catch (Exception e) {
                	System.out.println("Something went wrong.Please try again");
                }
            }
        }
        return null;
    }

}
