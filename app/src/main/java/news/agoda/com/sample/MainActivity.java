package news.agoda.com.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.facebook.drawee.backends.pipeline.Fresco;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import news.agoda.com.sample.base.BaseListActivity;
import news.agoda.com.sample.injection.component.ApplicationComponent;
import news.agoda.com.sample.model.domain.ApiManager;
import news.agoda.com.sample.model.domain.entity.NewsEntity;
import news.agoda.com.sample.util.Loge;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseListActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<NewsEntity> newsItemList;
    private Handler handler = new Handler(Looper.getMainLooper());
    @Inject ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);

        newsItemList = new ArrayList<>();

        loadResource();
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadResource() {
        apiManager.getNews()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap(news -> {
                List<NewsEntity> list = new ArrayList<>();
                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(news);
                    JSONArray resultArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject newsObject = resultArray.getJSONObject(i);
                        NewsEntity newsEntity = new NewsEntity(newsObject);
                        Loge.d("entity: " + newsEntity.getTitle());
                        list.add(newsEntity);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "fail to parse json string");
                }
                return io.reactivex.Observable.just(list);
            })
            .subscribe(newsList -> {
                newsItemList.addAll(newsList);
                NewsListAdapter adapter = new NewsListAdapter(MainActivity.this,
                                                              R.layout.list_item_news,
                                                              newsItemList);
                setListAdapter(adapter);

                ListView listView = getListView();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        NewsEntity newsEntity = newsItemList.get(position);
                        String title = newsEntity.getTitle();
                        Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                        intent.putExtra("title", title);
                        startActivity(intent);
                    }
                });
            });
    }

    public void onResult(final String data) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject;

                try {
                    jsonObject = new JSONObject(data);
                    JSONArray resultArray = jsonObject.getJSONArray("results");
                    for (int i = 0; i < resultArray.length(); i++) {
                        JSONObject newsObject = resultArray.getJSONObject(i);
                        NewsEntity newsEntity = new NewsEntity(newsObject);
                        newsItemList.add(newsEntity);
                    }
                } catch (JSONException e) {
                    Log.e(TAG, "fail to parse json string");
                }

                NewsListAdapter adapter = new NewsListAdapter(MainActivity.this,
                                                              R.layout.list_item_news,
                                                              newsItemList);
                setListAdapter(adapter);

                ListView listView = getListView();
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position,
                                            long id) {
                        NewsEntity newsEntity = newsItemList.get(position);
                        String title = newsEntity.getTitle();
                        Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                        intent.putExtra("title", title);
                        startActivity(intent);
                    }
                });
            }
        }, 0);
    }
}
