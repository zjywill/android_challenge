package news.agoda.com.sample.model.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This represents a news item
 */
public class NewsEntity implements Parcelable {
    public static final Parcelable.Creator<NewsEntity> CREATOR
        = new Parcelable.Creator<NewsEntity>() {
        @Override
        public NewsEntity createFromParcel(Parcel source) {return new NewsEntity(source);}

        @Override
        public NewsEntity[] newArray(int size) {return new NewsEntity[size];}
    };
    private static final String TAG = NewsEntity.class.getSimpleName();
    private String title;
    private String summary;
    private String articleUrl;
    private String byline;
    private String publishedDate;
    private List<MediaEntity> mediaEntityList;

    public NewsEntity() {

    }

    public NewsEntity(JSONObject jsonObject) {
        try {
            title = jsonObject.optString("title");
            summary = jsonObject.optString("abstract");
            articleUrl = jsonObject.optString("url");
            byline = jsonObject.optString("byline");
            publishedDate = jsonObject.optString("published_date");
            JSONArray mediaArray = jsonObject.optJSONArray("multimedia");
            if (mediaArray != null) {
                mediaEntityList = new ArrayList<>();
                for (int i = 0; i < mediaArray.length(); i++) {
                    JSONObject mediaObject = mediaArray.getJSONObject(i);
                    MediaEntity mediaEntity = new MediaEntity(mediaObject);
                    mediaEntityList.add(mediaEntity);
                }
            }
        } catch (JSONException exception) {
            exception.printStackTrace();
            Log.e(TAG, exception.getMessage());
        }
    }

    protected NewsEntity(Parcel in) {
        this.title = in.readString();
        this.summary = in.readString();
        this.articleUrl = in.readString();
        this.byline = in.readString();
        this.publishedDate = in.readString();
        this.mediaEntityList = in.createTypedArrayList(MediaEntity.CREATOR);
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<MediaEntity> getMediaEntity() {
        return mediaEntityList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setMediaEntityList(List<MediaEntity> mediaEntityList) {
        this.mediaEntityList = mediaEntityList;
    }

    public String getStandardImage() {
        if (mediaEntityList != null) {
            for (MediaEntity mediaEntity : mediaEntityList) {
                if (mediaEntity.getFormat().equals(MediaEntity.STANDARD_IMAGE_KEY)) {
                    return mediaEntity.getUrl();
                }
            }
        }
        return null;
    }

    public String getLargeImage() {
        if (mediaEntityList != null) {
            for (MediaEntity mediaEntity : mediaEntityList) {
                if (mediaEntity.getFormat().equals(MediaEntity.LARGE_IMAGE_KEY)) {
                    return mediaEntity.getUrl();
                }
            }
        }
        return null;
    }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.summary);
        dest.writeString(this.articleUrl);
        dest.writeString(this.byline);
        dest.writeString(this.publishedDate);
        dest.writeTypedList(this.mediaEntityList);
    }
}
