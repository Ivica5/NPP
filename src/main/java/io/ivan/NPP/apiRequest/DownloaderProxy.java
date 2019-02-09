package io.ivan.NPP.apiRequest;

import java.util.HashMap;
import java.util.Map;

public class DownloaderProxy implements IDownloader {

    private static Map<String, String> cacheData = new HashMap<String, String>();
    private Downloader downloader = new Downloader();
    
    public String downloadData(String url) {
        if (cacheData.containsKey(url)) {
            return cacheData.get(url);
        } else {
            cacheData.put(url, downloader.downloadData(url));
            return cacheData.get(url);
        }
    }

}
