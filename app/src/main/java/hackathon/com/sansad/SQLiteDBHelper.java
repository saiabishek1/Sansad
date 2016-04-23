package hackathon.com.sansad;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import hackathon.com.sansad.models.api.Data;
import hackathon.com.sansad.models.api.Session;
import hackathon.com.sansad.models.chat.Chat;
import hackathon.com.sansad.models.chat.Messages;
import hackathon.com.sansad.models.home.Notificationn;
import hackathon.com.sansad.models.home.Offer;
import hackathon.com.sansad.models.places.Places;
import hackathon.com.sansad.models.user.User;

import java.util.ArrayList;


import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_ADDRESS;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_ADMIN;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_DESCRIPTION;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_FEATURED;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_ID;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_IMAGE_LINK;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_LATITUDE;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_LOCATION;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_LONGITUDE;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_MIN_COST;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_NAME;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_NO_RATINGS;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_PHONE;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_POPULAR;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_RATING;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_RECOMMENDED;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_STATUS;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_TYPE;
import static hackathon.com.sansad.resources.Constants.COLUMN_PLACE_VIEWS;
import static hackathon.com.sansad.resources.Constants.COLUMN_USER_EMAIL;
import static hackathon.com.sansad.resources.Constants.COLUMN_USER_GENDER;
import static hackathon.com.sansad.resources.Constants.COLUMN_USER_IMAGE;

import static hackathon.com.sansad.resources.Constants.COLUMN_USER_NAME;
import static hackathon.com.sansad.resources.Constants.COLUMN_USER_SESSION;
import static hackathon.com.sansad.resources.Constants.COLUMN_USER_TOKEN;
import static hackathon.com.sansad.resources.Constants.COLUMN_USER_USERNAME;

public class SQLiteDBHelper extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    //last increased  on 10th march 2016. Don't(preferably) increment unless a release was pushed before current day
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_LEUK = "sansad";

    // Login table name
    private static final String TABLE_USER = "users";
    private static final String TABLE_PLACES = "places";
    ;

    public SQLiteDBHelper(Context context) {
        super(context, DATABASE_LEUK, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER
                + "("
                + COLUMN_USER_NAME + " TEXT NOT NULL, "
                + COLUMN_USER_USERNAME + " TEXT PRIMARY KEY, "
                + COLUMN_USER_EMAIL + " TEXT NOT NULL, "
                + COLUMN_USER_GENDER + " TEXT NOT NULL, "

                + COLUMN_USER_IMAGE + " TEXT, "
                + COLUMN_USER_SESSION + " TEXT NOT NULL, "
                + COLUMN_USER_TOKEN + " TEXT NOT NULL "
                + ")";
        String CREATE_PLACES_TABLE = "CREATE TABLE " + TABLE_PLACES
                + "("
                + COLUMN_PLACE_ID + " TEXT PRIMARY KEY NOT NULL, "
                + COLUMN_PLACE_NAME + " TEXT , "
                + COLUMN_PLACE_TYPE + " TEXT NOT NULL, "
                + COLUMN_PLACE_MIN_COST + " TEXT, "
                + COLUMN_PLACE_RATING + " TEXT, "
                + COLUMN_PLACE_DESCRIPTION + " TEXT NOT NULL, "
                + COLUMN_PLACE_ADDRESS + " TEXT, "
                + COLUMN_PLACE_LOCATION + " TEXT, "
                + COLUMN_PLACE_IMAGE_LINK + " TEXT NOT NULL, "
                + COLUMN_PLACE_LATITUDE + " TEXT, "
                + COLUMN_PLACE_LONGITUDE + " TEXT, "
                + COLUMN_PLACE_PHONE + " TEXT, "
                + COLUMN_PLACE_FEATURED + " TEXT, "
                + COLUMN_PLACE_POPULAR + " TEXT, "
                + COLUMN_PLACE_RECOMMENDED + " TEXT, "
                + COLUMN_PLACE_NO_RATINGS + " TEXT, "
                + COLUMN_PLACE_ADMIN + " TEXT, "
                + COLUMN_PLACE_VIEWS + " TEXT, "
                + COLUMN_PLACE_STATUS + " TEXT "
                + ")";


        db.execSQL(CREATE_LOGIN_TABLE);
        Log.d("database", "user created");
        db.execSQL(CREATE_PLACES_TABLE);
        Log.d("database", "places created");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLACES);

        // Create tables again
        onCreate(db);
    }

    /**
     * Storing user details in database
     */
    public void addUser(String name, String username, String email, String gender, String phone,
                        String location, String image, String level, String totalCredits,
                        String remainingCredits, int maxCredits, String session, String token) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, name); // Name
        values.put(COLUMN_USER_USERNAME, username); // Username
        values.put(COLUMN_USER_EMAIL, email); // Email
        values.put(COLUMN_USER_GENDER, gender); // Gender

        values.put(COLUMN_USER_IMAGE, image); // Image
        values.put(COLUMN_USER_SESSION, session); // Session ID
        values.put(COLUMN_USER_TOKEN, token); // Token
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close(); // Closing database connection
    }

    /**
     * Getting user data from database
     */
    public Data getUserDetails() {
        String selectQuery = "SELECT * FROM " + TABLE_USER;
        Data data = new Data();
        User user = new User();
        Session session = new Session();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // Move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.setName(cursor.getString(0));
            user.setUsername(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setGender(cursor.getString(3));
            user.setProfileImg(cursor.getString(4));
            session.setSessionid(cursor.getString(5));
            session.setToken(cursor.getString(6));
            data.setSession(session);
            data.setUser(user);
        }
        cursor.close();
        db.close();
        return data;
    }

    /**
     * Update user data in database
     */
    public void updateUserDetails(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName()); // Name
        values.put(COLUMN_USER_GENDER, user.getGender()); // Gender

        db.update(TABLE_USER, values, COLUMN_USER_USERNAME + "=\'" + user.getUsername() + "\'", null);
    }

    /**
     * Getting user login status return true if rows are there in table
     */
    public int getRowCount() {
        String countQuery = "SELECT  * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int rowCount = cursor.getCount();
        db.close();
        cursor.close();

        // return row count
        return rowCount;
    }

    /**
     * Storing places in the database
     */
    public void addPlace(Places place) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLACE_ID, place.getId());
        values.put(COLUMN_PLACE_NAME, place.getName()); // Place Name
        values.put(COLUMN_PLACE_TYPE, place.getType()); // Place Type
        values.put(COLUMN_PLACE_MIN_COST, place.getMinimumSpending()); // Place minimum spending
        values.put(COLUMN_PLACE_RATING, place.getRating()); // Place Rating
        values.put(COLUMN_PLACE_DESCRIPTION, place.getDescription()); // Place Description
        values.put(COLUMN_PLACE_ADDRESS, place.getAddress()); // Place Address
        values.put(COLUMN_PLACE_LOCATION, place.getLocation()); // Place Location
        values.put(COLUMN_PLACE_IMAGE_LINK, place.getPhotoLink()); // Place image link
        values.put(COLUMN_PLACE_LATITUDE, place.getLatitude()); // Place Location
        values.put(COLUMN_PLACE_LONGITUDE, place.getLongitude()); // Place Location
        values.put(COLUMN_PLACE_PHONE, place.getPhone()); // Place phone
        values.put(COLUMN_PLACE_FEATURED, place.getFeatured()); // Place phone
        values.put(COLUMN_PLACE_POPULAR, place.getPopular()); // Place phone
        values.put(COLUMN_PLACE_RECOMMENDED, place.getRecommended()); // Place phone
        values.put(COLUMN_PLACE_NO_RATINGS, place.getNoOfRatings()); // Place phone
        values.put(COLUMN_PLACE_ADMIN, place.getLeukAdmin()); // Place phone
        values.put(COLUMN_PLACE_VIEWS, place.getViews());//Place views
        values.put(COLUMN_PLACE_STATUS, place.getStatus()); // Place phone
        // Inserting Row
        db.insert(TABLE_PLACES, null, values);
        Log.d("database", "added place" + place.getName());
        db.close(); // Closing database connection
    }

    public void addPlaces(ArrayList<Places> placesArrayList) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        Log.d("bulk insert start", System.currentTimeMillis() + "");
        for (Places place : placesArrayList) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_PLACE_ID, place.getId());
            values.put(COLUMN_PLACE_NAME, place.getName()); // Place Name
            values.put(COLUMN_PLACE_TYPE, place.getType()); // Place Type
            values.put(COLUMN_PLACE_MIN_COST, place.getMinimumSpending()); // Place minimum spending
            values.put(COLUMN_PLACE_RATING, place.getRating()); // Place Rating
            values.put(COLUMN_PLACE_DESCRIPTION, place.getDescription()); // Place Description
            values.put(COLUMN_PLACE_ADDRESS, place.getAddress()); // Place Address
            values.put(COLUMN_PLACE_LOCATION, place.getLocation()); // Place Location
            values.put(COLUMN_PLACE_IMAGE_LINK, place.getPhotoLink()); // Place image link
            values.put(COLUMN_PLACE_LATITUDE, place.getLatitude()); // Place Location
            values.put(COLUMN_PLACE_LONGITUDE, place.getLongitude()); // Place Location
            values.put(COLUMN_PLACE_PHONE, place.getPhone()); // Place phone
            values.put(COLUMN_PLACE_FEATURED, place.getFeatured()); // Place phone
            values.put(COLUMN_PLACE_POPULAR, place.getPopular()); // Place phone
            values.put(COLUMN_PLACE_RECOMMENDED, place.getRecommended()); // Place phone
            values.put(COLUMN_PLACE_NO_RATINGS, place.getNoOfRatings()); // Place phone
            values.put(COLUMN_PLACE_ADMIN, place.getLeukAdmin()); // Place phone
            values.put(COLUMN_PLACE_VIEWS, place.getViews());//Place views
            values.put(COLUMN_PLACE_STATUS, place.getStatus()); // Place phone
            // Inserting Row
            db.insert(TABLE_PLACES, null, values);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        Log.d("bulk insert end", System.currentTimeMillis() + "");
    }

    public ArrayList<Places> getAllPlaces() {
        String placeTypeQuery = "SELECT * FROM " + TABLE_PLACES + " ORDER BY " + COLUMN_PLACE_FEATURED + " DESC," + COLUMN_PLACE_POPULAR + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(placeTypeQuery, null);
        ArrayList<Places> places = new ArrayList<>();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Places place = new Places();
            place.setId(cursor.getString(0));
            place.setName(cursor.getString(1));
            place.setType(cursor.getString(2));
            place.setMinimumSpending(cursor.getString(3));
            place.setRating(cursor.getString(4));
            place.setDescription(cursor.getString(5));
            place.setAddress(cursor.getString(6));
            place.setLocation(cursor.getString(7));
            place.setPhotoLink(cursor.getString(8));
            place.setLatitude(cursor.getString(9));
            place.setLongitude(cursor.getString(10));
            place.setPhone(cursor.getString(11));
            place.setFeatured(cursor.getString(12));
            place.setPopular(cursor.getString(13));
            place.setRecommended(cursor.getString(14));
            place.setNoOfRatings(cursor.getString(15));
            place.setLeukAdmin(cursor.getString(16));
            place.setViews(cursor.getString(17));
            place.setStatus(cursor.getString(18));
            places.add(place);
        }
        return places;
    }



    /**
     * Re crate database Delete all tables and create them again
     */
    public void resetTables() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(TABLE_USER, null, null);
        db.close();
    }

    public void resetPlacesTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        try {
            db.delete(TABLE_PLACES, null, null);
        } catch (Exception e) {
        }
        db.close();
    }

    public void deletePlacesFromType(String category) {
        String deleteFromType = "DELETE FROM " + TABLE_PLACES + " WHERE " + COLUMN_PLACE_TYPE + " ='" + category + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteFromType);
        db.close();
    }

}