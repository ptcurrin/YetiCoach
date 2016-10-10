package dataprovider;

        import android.content.AsyncTaskLoader;
        import android.content.Context;
        import android.content.Loader;
        import android.database.Cursor;
        import android.os.Handler;
        import android.util.Log;

/**
 * Created by Patrick on 9/27/2016.
 */

public class YUserLoader extends AsyncTaskLoader<Cursor>
{

    String yUserId = "";
    String email = "";
    int y_id = -1;
    Cursor yCursor;
    YContentObserver yContentObserver;
    private boolean checkingByEmail = false;
    private boolean checkingBy_Id = false;

    public YUserLoader(Context context, String email){
        super(context);

        this.email = email;
        checkingByEmail = true;
    }

    public YUserLoader(Context context, int lid){
        super(context);

        this.y_id = lid;
        checkingBy_Id = true;
    }


    @Override
    public Cursor loadInBackground() {

        if(checkingByEmail) {
            Cursor useridCursor = getContext().getContentResolver().query(
                    YetiCoachContract.Users.CONTENT_URI,
                    new String[]{"userid"},
                    "email=?",
                    new String[]{email},
                    null);
            if (useridCursor != null && useridCursor.getCount() == 1) {

                useridCursor.moveToFirst();
                yUserId = useridCursor.getString(useridCursor.getColumnIndex("userid"));

                yCursor = getContext().getContentResolver().query(YetiCoachContract.Leagues.CONTENT_URI,
                        YetiCoachContract.Leagues.PROJECTION_ALL,
                        "userid=?",
                        new String[]{yUserId},
                        null);

                checkingByEmail = false;
                if (yCursor != null && yCursor.getCount() > 0) {
                    return yCursor;
                }
            }
            checkingByEmail = false;
            return null;
        }else if(checkingBy_Id){

            yCursor = getContext().getContentResolver().query(YetiCoachContract.Leagues.CONTENT_URI,
                    YetiCoachContract.Leagues.PROJECTION_ALL,
                    "_id=?",
                    new String[]{String.format(y_id + "")},
                    null);
            checkingBy_Id = false;
            if (yCursor != null && yCursor.getCount() == 1) {
                return yCursor;
            }
        }
        checkingBy_Id = false;
        return null;
    }

    @Override
    public void deliverResult(Cursor cursor){
        if(isReset()){
            releaseResources(cursor);
            return;
        }

        Cursor oldCursor = yCursor;
        yCursor = cursor;

        if(isStarted()){
            super.deliverResult(cursor);
        }

        if(oldCursor != null && oldCursor != cursor){
            releaseResources(oldCursor);
        }
    }

    public void releaseResources(Cursor cursor){
        cursor.close();
    }

    protected void onStartLoading(){
        if(yCursor != null){
            deliverResult(yCursor);
        }

        // TODO: Register a ContentObserver
        if(yContentObserver == null){
            yContentObserver = new YContentObserver(new Handler());
        }

        if(takeContentChanged() || yCursor == null){
            // When the observer detects a change, it should call onContentChanged()
            // on the Loader, which will cause the next call to takeContentChanged()
            // to return true. If this is ever the case (or if the current data is
            // null), we force a new load.
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading(){
        // The Loader is in a stopped state, so we should attempt to cancel the
        // current load (if there is one).
        cancelLoad();

        // Note that we leave the observer as is. Loaders in a stopped state
        // should still monitor the data source for changes so that the Loader
        // will know to force a new load if it is ever started again.
    }

    protected void onReset(){
        onStopLoading();

        if(yCursor != null){
            releaseResources(yCursor);
            yCursor = null;
        }

//        if(yObserver != null){
//            yObserver = null;
//        }
    }

    public void onCancelled(Cursor cursor){
        super.onCanceled(cursor);

        releaseResources(cursor);
    }
}

