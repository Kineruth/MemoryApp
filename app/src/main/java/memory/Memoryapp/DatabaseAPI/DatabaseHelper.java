<<<<<<< HEAD
//package memory.Memoryapp.DatabaseAPI;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import java.util.ArrayList;
//
//import memory.Memoryapp.Object.Memory;
//
//public class DatabaseHelper extends SQLiteOpenHelper {
//    //database
//    private static final int DATABASE_VER = 1;
//    private static final String DATABASE_NAME = "MemoryAppDB";
//
//    //table
//    private static final String TABLE_NAME = "PersonalMemories";
//    private static final String MEMORY_ID_COLNAME = "memoryID";
//    private static final String USER_ID_COLNAME = "userName";
//    private static final String MEMORY_NAME_COLNAME = "memoryName";
//    private static final String DESCRIPTION_COLNAME = "description";
//    private static final String CREATION_DATE_COLNAME = "date";
//    private static final int MEMORY_ID_COL = 0;
//    private static final int USER_ID_COL = 1;
//    private static final int MEMORY_NAME_COL = 2;
//    private static final int DESCRIPTION_COL = 3;
//    private static final int CREATION_DATE_COL = 4;
//
//    public DatabaseHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VER);
//    }
//
//    /**
//     * Called when the database is created for the first time. This is where the
//     * creation of tables and the initial population of the tables should happen.
//     *
//     * @param db The database.
//     */
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
//                + MEMORY_ID_COLNAME + " INTEGER PRIMARY KEY,"
//                + USER_ID_COLNAME + " TEXT, "
//                + MEMORY_NAME_COLNAME + " TEXT, "
//                + DESCRIPTION_COLNAME + " TEXT, "
//                + CREATION_DATE_COLNAME + " INTEGER, "
//                + "image_1 TEXT, "
//                + "image_2 TEXT, "
//                + "image_3 TEXT, "
//                + "image_4 TEXT, "
//                + "image_5 TEXT, "
//                + "image_6 TEXT, "
//                + "image_7 TEXT, "
//                + "image_8 TEXT, "
//                + "image_9 TEXT, "
//                + "image_10 TEXT);";
//        db.execSQL(CREATE_TABLE);
//    }
//
//    /**

//     * Called when the database needs to be upgraded. The implementation
//     * should use this method to drop tables, add tables, or do anything else it
//     * needs to upgrade to the new schema version.
//     *
//     * <p>
//     * The SQLite ALTER TABLE documentation can be found
//     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
//     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
//     * you can use ALTER TABLE to rename the old table, then create the new table and then
//     * populate the new table with the contents of the old table.
//     * </p><p>
//     * This method executes within a transaction.  If an exception is thrown, all changes
//     * will automatically be rolled back.
//     * </p>
//     *
//     * @param db         The database.
//     * @param oldVersion The old database version.
//     * @param newVersion The new database version.
//     */
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(db);
//    }
//    /**
//     * This function is query that inserts new line of memory into the database.
//     * @param memory The new memory
//     * @return true in case the query is good, and false otherwise.
//     */
//    public boolean setNewMemory(Memory memory) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(MEMORY_ID_COLNAME, memory.getMemoryID());
//        contentValues.put(USER_ID_COLNAME, memory.getUserID());
//        contentValues.put(MEMORY_NAME_COLNAME, memory.getMemoryName());
//        contentValues.put(DESCRIPTION_COLNAME, memory.getDescription());
//        contentValues.put(CREATION_DATE_COLNAME, memory.getCreationTime().getTime());
//        contentValues.put("image_1", memory.getImages().get(0));
//        contentValues.put("image_2", memory.getImages().get(1));
//        contentValues.put("image_3", memory.getImages().get(2));
//        contentValues.put("image_4", memory.getImages().get(3));
//        contentValues.put("image_5", memory.getImages().get(4));
//        contentValues.put("image_6", memory.getImages().get(5));
//        contentValues.put("image_7", memory.getImages().get(6));
//        contentValues.put("image_8", memory.getImages().get(7));
//        contentValues.put("image_9", memory.getImages().get(8));
//        contentValues.put("image_10", memory.getImages().get(9));
//
//        if(db.insert(TABLE_NAME, null, contentValues) < 0){
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * This function is an database query that goes
//     * to the database and retrieves memories object from it.
//     * @return ArrayList<Memory> object in case the query is good, and null otherwise.
//     */
//    public ArrayList<Memory> getAllMemoriesQuery() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<Memory> result = null;
//        Cursor res = db.rawQuery("SELECT * FROM TABLE_NAME", null);
//        while(res.moveToNext()){
//
//            ArrayList<String> images = new ArrayList<String>();
//            images.add(res.getString(5));
//            images.add(res.getString(6));
//            images.add(res.getString(7));
//            images.add(res.getString(8));
//            images.add(res.getString(9));
//            images.add(res.getString(10));
//            images.add(res.getString(11));
//            images.add(res.getString(12));
//            images.add(res.getString(13));
//            images.add(res.getString(14));
//            result.add(new Memory(res.getInt(MEMORY_ID_COL),
//                    res.getString(USER_ID_COL),
//                    res.getString(MEMORY_NAME_COL),
//                    res.getString(DESCRIPTION_COL),
//                    res.getLong(CREATION_DATE_COL),
//                    images));
//        }
//        res.close();
//        return result;
//    }
//}
=======
package memory.Memoryapp.DatabaseAPI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import memory.Memoryapp.Object.Memory;

public class DatabaseHelper extends SQLiteOpenHelper {
    //database
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "MemoryAppDB";

    //table
    private static final String TABLE_NAME = "PersonalMemories";
    private static final String MEMORY_ID_COLNAME = "memoryID";
    private static final String USER_ID_COLNAME = "userName";
    private static final String MEMORY_NAME_COLNAME = "memoryName";
    private static final String DESCRIPTION_COLNAME = "description";
    private static final String CREATION_DATE_COLNAME = "date";
    private static final int MEMORY_ID_COL = 0;
    private static final int USER_ID_COL = 1;
    private static final int MEMORY_NAME_COL = 2;
    private static final int DESCRIPTION_COL = 3;
    private static final int CREATION_DATE_COL = 4;

    /**
     * Parameterized Constructor
     * @param context the context.
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    /**
     * Called when the database is created for the first time.
     * This is where the creation of tables and the initial population of the tables should happen.
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + MEMORY_ID_COLNAME + " INTEGER PRIMARY KEY,"
                + USER_ID_COLNAME + " TEXT, "
                + MEMORY_NAME_COLNAME + " TEXT, "
                + DESCRIPTION_COLNAME + " TEXT, "
                + CREATION_DATE_COLNAME + " INTEGER, "
                + "image_1 TEXT, "
                + "image_2 TEXT, "
                + "image_3 TEXT, "
                + "image_4 TEXT, "
                + "image_5 TEXT, "
                + "image_6 TEXT, "
                + "image_7 TEXT, "
                + "image_8 TEXT, "
                + "image_9 TEXT, "
                + "image_10 TEXT);";
        db.execSQL(CREATE_TABLE);
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    /**
     * This function is a query that inserts new line of memory into the database.
     * @param memory The new memory.
     * @return true in case the query is good, and false otherwise.
     */
    public boolean setNewMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEMORY_ID_COLNAME, memory.getMemoryID());
        contentValues.put(USER_ID_COLNAME, memory.getUserID());
        contentValues.put(MEMORY_NAME_COLNAME, memory.getMemoryName());
        contentValues.put(DESCRIPTION_COLNAME, memory.getDescription());
        contentValues.put(CREATION_DATE_COLNAME, memory.getCreationTime().getTime());
        contentValues.put("image_1", memory.getImages().get(0));
        contentValues.put("image_2", memory.getImages().get(1));
        contentValues.put("image_3", memory.getImages().get(2));
        contentValues.put("image_4", memory.getImages().get(3));
        contentValues.put("image_5", memory.getImages().get(4));
        contentValues.put("image_6", memory.getImages().get(5));
        contentValues.put("image_7", memory.getImages().get(6));
        contentValues.put("image_8", memory.getImages().get(7));
        contentValues.put("image_9", memory.getImages().get(8));
        contentValues.put("image_10", memory.getImages().get(9));

        if(db.insert(TABLE_NAME, null, contentValues) < 0){
            return false;
        }
        return true;
    }

    /**
     * This function is a database query that goes
     * to the database and retrieves memories object from it.
     * @return ArrayList<Memory> object in case the query is good, and null otherwise.
     */
    public ArrayList<Memory> getAllMemoriesQuery() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Memory> result = null;
        Cursor res = db.rawQuery("SELECT * FROM TABLE_NAME", null);
        while(res.moveToNext()){

            ArrayList<String> images = new ArrayList<String>();
            images.add(res.getString(5));
            images.add(res.getString(6));
            images.add(res.getString(7));
            images.add(res.getString(8));
            images.add(res.getString(9));
            images.add(res.getString(10));
            images.add(res.getString(11));
            images.add(res.getString(12));
            images.add(res.getString(13));
            images.add(res.getString(14));
            result.add(new Memory(res.getInt(MEMORY_ID_COL),
                    res.getString(USER_ID_COL),
                    res.getString(MEMORY_NAME_COL),
                    res.getString(DESCRIPTION_COL),
                    res.getLong(CREATION_DATE_COL),
                    images));
        }
        res.close();
        return result;
    }
}
>>>>>>> 39ff2e2e6da08db2bd24667381d2dd99992d369b
