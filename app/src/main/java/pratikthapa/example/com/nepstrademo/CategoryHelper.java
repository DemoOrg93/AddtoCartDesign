package pratikthapa.example.com.nepstrademo;

/**
 * Created by Prakriti on 9/23/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CategoryHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "category.db";
    private static final String TABLE_NAME = "tbcategory";
    public static final String CATEGORY_COLUMN_ID = "_id";
    public static final String CATEGORY_COLUMN_NAME = "name";
    Category openHelper;
    private SQLiteDatabase database;

    public CategoryHelper(Context context){
        openHelper = new Category(context);
        database = openHelper.getWritableDatabase();
    }
    public void saveCategoryRecord(String id, String name) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CATEGORY_COLUMN_ID, id);
        contentValues.put(CATEGORY_COLUMN_NAME, name);
        database.insert(TABLE_NAME, null, contentValues);
    }
    public Cursor getTimeRecordList() {
        return database.rawQuery("select * from " + TABLE_NAME, null);
    }
    private class Category extends SQLiteOpenHelper {

        public Category(Context context) {
            // TODO Auto-generated constructor stub
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + TABLE_NAME + "( "
                    + CATEGORY_COLUMN_ID + " INTEGER PRIMARY KEY, "
                    + CATEGORY_COLUMN_NAME + " TEXT )" );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS"+ TABLE_NAME);
            onCreate(db);
        }

    }
}