package news.agoda.com.sample.util;

/**
 * Created by junyi on 5/4/16.
 */
public class Constants {

    public static final int NETWORK_CONNECTION_TIMEOUT = 30; // 30 sec
    public static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    public static final int CACHE_MAX_AGE = 2; // 2 min
    public static final int CACHE_MAX_STALE = 7; // 7 day
    public static final int NETWORK_RETRY_COUNT = 1;

    private Constants() {}
}
