package io.ivan.NPP.apiRequest;

public class CollectData {

    static private CollectData instance;

    private CollectData() {

    }
    
    public static CollectData getInstance() {
        if (instance == null) {
            instance = new CollectData();
        }
        return instance;
    }
    
    public String getJSON(String url) {
        DownloaderProxy proxy = new DownloaderProxy();
        return proxy.downloadData(url);
    }
}
