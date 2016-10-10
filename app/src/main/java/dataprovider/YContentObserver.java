package dataprovider;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;

/**
 * Created by Patrick on 9/26/2016.
 */

public class YContentObserver extends ContentObserver {

    /**
     * Creates a content observer.
     *
     * @param handler The handler to run {@link #onChange} on, or null if none.
     */
    public YContentObserver(Handler handler) {
        super(handler);
    }

    @Override
    public void onChange(boolean selfChange){
        this.onChange(selfChange, null);
    }

    public void onChange(boolean selfChange, Uri uri){

    }
}
