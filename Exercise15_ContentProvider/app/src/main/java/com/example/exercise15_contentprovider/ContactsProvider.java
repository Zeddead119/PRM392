package com.example.exercise15_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ContactsProvider extends ContentProvider {

    private static final String AUTHORITY = "com.example.contentproviderexample.provider";
    private static final String BASE_PATH = "contacts";

    private static final int CONTACTS = 1;
    private static final int CONTACT_ID = 2;

    private SQLiteDatabase db;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH, CONTACTS);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/#", CONTACT_ID);
    }

    public ContactsProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count;

        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                count = db.delete(DBHelper.TABLE_NAME, selection, selectionArgs);
                break;
            case CONTACT_ID:
                count = db.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID + " = " + uri.getLastPathSegment() + (!selection.isEmpty() ? "AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                return "vnd.android.cursor.dir/vnd.com.example.contacts";
            case CONTACT_ID:
                return "vnd.android.cursor.item/vnd.com.example.contacts";
            default:
                throw new IllegalArgumentException("Unsupported URI : " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = db.insert(DBHelper.TABLE_NAME, null, values);
        if (id > 0) {
            Uri newUri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return db != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DBHelper.TABLE_NAME);
        switch (uriMatcher.match(uri)) {
            case CONTACTS:
                break;
            case CONTACT_ID:
                queryBuilder.appendWhere(DBHelper.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }

        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count;
        switch (uriMatcher.match(uri)){
            case CONTACTS:
                count = db.update(DBHelper.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CONTACT_ID:
                count = db.update(DBHelper.TABLE_NAME, values, DBHelper.COLUMN_ID + "=" + uri.getLastPathSegment() + (!selection.isEmpty() ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI : " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}