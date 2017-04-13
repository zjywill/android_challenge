package news.agoda.com.sample;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.List;
import news.agoda.com.sample.model.domain.entity.MediaEntity;
import news.agoda.com.sample.model.domain.entity.NewsEntity;

public class NewsListAdapter extends BaseAdapter {
    private static class ViewHolder {
        TextView newsTitle;
        DraweeView imageView;
    }

    private List<NewsEntity> newsItemList;
    private Context context;

    public NewsListAdapter(Context context, int resource, List<NewsEntity> objects) {
        super();
        this.context = context;
        this.newsItemList = objects;
    }

    @Override
    public int getCount() {
        return newsItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return newsItemList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity newsEntity = (NewsEntity) getItem(position);
        //List<MediaEntity> mediaEntityList = newsEntity.getMediaEntity();
        //String thumbnailURL = "";
        //MediaEntity mediaEntity = mediaEntityList.get(0);
        //thumbnailURL = mediaEntity.getUrl();

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.list_item_news, parent, false);
            viewHolder.newsTitle = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.imageView = (DraweeView) convertView.findViewById(R.id.news_item_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.newsTitle.setText(newsEntity.getTitle());
        //DraweeController draweeController = Fresco.newDraweeControllerBuilder()
        //    .setImageRequest(ImageRequest.fromUri(Uri.parse(thumbnailURL)))
        //    .setOldController(viewHolder.imageView.getController())
        //    .build();
        //viewHolder.imageView.setController(draweeController);
        return convertView;
    }
}
