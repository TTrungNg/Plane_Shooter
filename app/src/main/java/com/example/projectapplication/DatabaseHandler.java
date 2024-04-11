package com.example.projectapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static DatabaseHandler sInstance;
    private static final String DATABASE_NAME = "GalaxyDefenderManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "leaderboard";


    private static String KEY_ID = "id";
    private static String KEY_NAME = "name";
    private static String KEY_SCORE = "score";

    public static synchronized DatabaseHandler getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new DatabaseHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = String.format("CREATE TABLE %s(%s LONG PRIMARY KEY, %s TEXT, %s INTEGER)", TABLE_NAME, KEY_ID, KEY_NAME, KEY_SCORE);
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_table);

        onCreate(db);
    }

    public void addPlayRecord(PlayRecord playRecord) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, playRecord.getId());
        values.put(KEY_NAME, playRecord.getName());
        values.put(KEY_SCORE, playRecord.getScore());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<PlayRecord> getTopScores() {
        List<PlayRecord> topScores = new ArrayList<>();

        // Lấy instance của SQLiteDatabase từ DatabaseHelper
        SQLiteDatabase db = this.getReadableDatabase();

        // Câu lệnh truy vấn
        String query = "SELECT * FROM TABLE_NAME ORDER BY score DESC LIMIT 1";

        // Thực hiện truy vấn
        Cursor cursor = db.rawQuery(query, null);

        // Duyệt qua các dòng kết quả
        if (cursor.moveToFirst()) {
            // Lấy dữ liệu từ Cursor và tạo đối tượng PlayRecord
            @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex("KEY_ID"));
            @SuppressLint("Range") String playerName = cursor.getString(cursor.getColumnIndex("KEY_NAME"));
            @SuppressLint("Range") int score = cursor.getInt(cursor.getColumnIndex("KEY_SCORE"));
            // Khởi tạo đối tượng PlayRecord từ dữ liệu
            PlayRecord playRecord = new PlayRecord(id, playerName, score);
            // Thêm vào danh sách topScores
            topScores.add(playRecord);
        }


        // Trả về danh sách topScores
        return topScores;
    }

}
