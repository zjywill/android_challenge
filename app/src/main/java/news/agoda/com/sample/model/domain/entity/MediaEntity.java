package news.agoda.com.sample.model.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class represents a media item
 */
public class MediaEntity implements Parcelable {
    public static final String STANDARD_IMAGE_KEY = "Standard Thumbnail";
    public static final String LARGE_IMAGE_KEY = "mediumThreeByTwo210";
    public static final Parcelable.Creator<MediaEntity> CREATOR
        = new Parcelable.Creator<MediaEntity>() {
        @Override
        public MediaEntity createFromParcel(Parcel source) {return new MediaEntity(source);}

        @Override
        public MediaEntity[] newArray(int size) {return new MediaEntity[size];}
    };
    private String url;
    private String format;
    private int height;
    private int width;
    private String type;
    private String subType;
    private String caption;
    private String copyright;

    public MediaEntity() {}

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

    protected MediaEntity(Parcel in) {
        this.url = in.readString();
        this.format = in.readString();
        this.height = in.readInt();
        this.width = in.readInt();
        this.type = in.readString();
        this.subType = in.readString();
        this.caption = in.readString();
        this.copyright = in.readString();
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.format);
        dest.writeInt(this.height);
        dest.writeInt(this.width);
        dest.writeString(this.type);
        dest.writeString(this.subType);
        dest.writeString(this.caption);
        dest.writeString(this.copyright);
    }
}
