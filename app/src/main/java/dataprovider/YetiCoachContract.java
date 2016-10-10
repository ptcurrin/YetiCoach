package dataprovider;

import android.content.ContentResolver;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.BaseColumns;
import static dataprovider.DBHelper.*;
import static dataprovider.DBSchema.*;


public final class YetiCoachContract {

    /**
     * Created by Patrick on 9/23/2016.
     */

    public static interface CommonColumns extends BaseColumns {
    }

    public static final String AUTHORITY =
            "com.paddysoft.yeticoach";

    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY);

    public static final UriMatcher URI_MATCHER;
    public static final int USER_LOGINS_LIST = 1;
    public static final int USER_LOGINS_ID = 2;
    public static final int USER_LIST = 3;
    public static final int USER_ID = 4;
    public static final int LEAGUE_LIST = 5;
    public static final int LEAGUE_ID = 6;
    public static final int PLAYER_LIST = 7;
    public static final int PLAYER_ID = 8;
    public static final int PARENT_LIST = 9;
    public static final int PARENT_ID = 10;
    public static final int ENROLLMENT_LIST = 11;
    public static final int ENROLLMENT_ID = 12;
    public static final int SPORT_LIST = 13;
    public static final int SPORT_ID = 14;
    public static final int TRANSACTION_LIST = 15;
    public static final int TRANSACTION_ID = 16;
    public static final int TEAM_LIST = 17;
    public static final int TEAM_ID = 18;


    public static final String VENDOR = "/vnd.com.paddysoft.yeticoach";
    public static final String FWS_HASH = "/#";
    public static final String ASC = " ASC";

    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_USER_LOGINS, USER_LOGINS_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_USER_LOGINS + FWS_HASH, USER_LOGINS_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_USERS, USER_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_USERS + FWS_HASH, USER_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_LEAGUES, LEAGUE_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_LEAGUES + FWS_HASH, LEAGUE_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_PLAYERS, PLAYER_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_PLAYERS + FWS_HASH, PLAYER_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_PARENTS, PARENT_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_PARENTS + FWS_HASH, PARENT_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_ENROLLMENTS, ENROLLMENT_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_ENROLLMENTS + FWS_HASH, ENROLLMENT_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_SPORTS, SPORT_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_SPORTS + FWS_HASH, SPORT_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_TRANSACTIONS, TRANSACTION_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_TRANSACTIONS + FWS_HASH, TRANSACTION_ID);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_TEAMS + FWS_HASH, TEAM_LIST);
        URI_MATCHER.addURI(YetiCoachContract.AUTHORITY, TBL_TEAMS + FWS_HASH, TEAM_ID);
    }

    public static final class UserLogins implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_USER_LOGINS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_USER_LOGINS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_USER_LOGINS;

        public static final String[] PROJECTION_ALL =
                {_ID, USER_LOGINS_CN_EMAIL, USER_LOGINS_CN_PASSWORD};

        public static final String SORT_ORDER_DEFAULT =
                USER_LOGINS_CN_EMAIL + ASC;
    }

    public static final class Users implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_USERS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_USERS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_USERS;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        USERS_COL_USER_ID,
                        USERS_COL_FIRSTNAME,
                        USERS_COL_LASTNAME,
                        USERS_COL_EMAIL,
                        USERS_COL_PHONE,
                        USERS_COL_STREET,
                        USERS_COL_CITY,
                        USERS_COL_STATE,
                        USERS_COL_ZIP
                };

        public static final String SORT_ORDER_DEFAULT =
                USERS_COL_LASTNAME + ASC;
    }

    public static final class Leagues implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_LEAGUES);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_LEAGUES;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_LEAGUES;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        LEAGUES_COL_LEAGUE_ID,
                        LEAGUES_COL_NAME,
                        LEAGUES_COL_USER_ID,
                        LEAGUES_COL_SPORT_ID,
                        LEAGUES_COL_MIN_AGE,
                        LEAGUES_COL_MAX_AGE,
                        LEAGUES_COL_START_DATE,
                        LEAGUES_COL_END_DATE
                };

        public static final String SORT_ORDER_DEFAULT =
                LEAGUES_COL_SPORT_ID + ASC;
    }

    public static final class Players implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_PLAYERS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_PLAYERS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_PLAYERS;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        PLAYERS_COL_PLAYER_ID,
                        PLAYERS_COL_FIRST_NAME,
                        PLAYERS_COL_LAST_NAME,
                        PLAYERS_COL_DATE_OF_BIRTH
                };

        public static final String SORT_ORDER_DEFAULT =
                PLAYERS_COL_LAST_NAME + ASC;


    }

    public static final class Parents implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_PARENTS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_PARENTS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_PARENTS;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        PARENTS_COL_PARENT_ID,
                        PARENTS_COL_PLAYER_ID,
                        PARENTS_COL_USER_ID
                };

        public static final String SORT_ORDER_DEFAULT =
                PARENTS_COL_USER_ID + ASC;


    }

    public static final class Enrollments implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_ENROLLMENTS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_ENROLLMENTS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_ENROLLMENTS;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        ENROLLMENTS_COL_ENROLLMENT_ID,
                        ENROLLMENTS_COL_TRANS_ID,
                        ENROLLMENTS_COL_PARENT_ID,
                        ENROLLMENTS_COL_TEAM_ID
                };

        public static final String SORT_ORDER_DEFAULT =
                ENROLLMENTS_COL_PARENT_ID + ASC;
    }

    public static final class Sports implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_SPORTS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_SPORTS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_SPORTS;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        SPORTS_COL_SPORT_ID,
                        SPORTS_COL_NAME,
                        SPORTS_COL_DESCRIPTION,
                        SPORTS_COL_IMG_PTH
                };

        public static final String SORT_ORDER_DEFAULT =
                SPORTS_COL_NAME + ASC;


    }

    public static final class Transactions implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_TRANSACTIONS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_TRANSACTIONS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_TRANSACTIONS;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        TRANSACTIONS_COL_TRANS_ID,
                        TRANSACTIONS_COL_PURPOSE,
                        TRANSACTIONS_COL_AMOUNT
                };

        public static final String SORT_ORDER_DEFAULT =
                TRANSACTIONS_COL_PURPOSE + ASC;

    }

    public static final class Teams implements CommonColumns {

        public static final Uri CONTENT_URI =
                Uri.withAppendedPath(YetiCoachContract.CONTENT_URI, TBL_TEAMS);

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + VENDOR + "_" + TBL_TEAMS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + VENDOR + "_" + TBL_TEAMS;

        public static final String[] PROJECTION_ALL =
                {
                        _ID,
                        TEAMS_COL_TEAM_ID,
                        TEAMS_COL_NAME,
                        TEAMS_COL_LEAGUE_ID,
                        TEAMS_COL_USER_ID
                };

        public static final String SORT_ORDER_DEFAULT =
                TEAMS_COL_LEAGUE_ID + ASC;

    }
}
