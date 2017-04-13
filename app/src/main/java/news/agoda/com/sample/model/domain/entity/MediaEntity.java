package news.agoda.com.sample.model.domain.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents a media item
 */
public class MediaEntity {
    private String url;
    private String format;
    private int height;
    private int width;
    private String type;
    private String subType;
    private String caption;
    private String copyright;

    public MediaEntity(JSONObject jsonObject) throws JSONException {
        url = jsonObject.optString("url");
        format = jsonObject.optString("format");
        height = jsonObject.optInt("height");
        width = jsonObject.optInt("width");
        type = jsonObject.optString("type");
        subType = jsonObject.optString("subtype");
        caption = jsonObject.optString("capton");
        copyright = jsonObject.optString("copyright");
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }
}
