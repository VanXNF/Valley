package com.vanxnf.photovalley.features.Preview.Entity;

public class FilterPreviewItem {

    private Integer thumbnailId;
    private Integer filterNameId;
    private String filterUri;

    public FilterPreviewItem(Integer thumbnailId, Integer filterNameId) {
        this.thumbnailId = thumbnailId;
        this.filterNameId = filterNameId;
    }

    public FilterPreviewItem(Integer thumbnailId, Integer filterNameId, String filterUri) {
        this.thumbnailId = thumbnailId;
        this.filterNameId = filterNameId;
        this.filterUri = filterUri;
    }

    public Integer getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(Integer thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public Integer getFilterNameId() {
        return filterNameId;
    }

    public void setFilterNameId(Integer filterNameId) {
        this.filterNameId = filterNameId;
    }

    public String getFilterUri() {
        return filterUri;
    }

    public void setFilterUri(String filterUri) {
        this.filterUri = filterUri;
    }
}
