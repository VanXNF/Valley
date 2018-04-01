package com.vanxnf.photovalley.features.Preview.Gson;

import com.google.gson.annotations.SerializedName;

public class Download {

    @SerializedName("download_path")
    public String downloadUri;

    public String getDownloadUri() {
        return downloadUri;
    }

    public void setDownloadUri(String downloadUri) {
        this.downloadUri = downloadUri;
    }
}
