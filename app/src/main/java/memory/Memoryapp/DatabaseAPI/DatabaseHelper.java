package memory.Memoryapp.DatabaseAPI;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import memory.Memoryapp.Memory;

public class DatabaseHelper extends SQLiteOpenHelper {
    //database
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "MemoryAppDB";

    //table
    private static final String TABLE_NAME = "PersonalMemories";
    private static final String MEMORY_ID_COLNAME = "memoryID";
    private static final String MEMORY_NAME_COLNAME = "memoryName";
    private static final String DESCRIPTION_COLNAME = "description";
    private static final String CREATION_DATE_COLNAME = "date";
    private static final String IMAGES_PATH_COLNAME = "imagesPath";
    private static final int MEMORY_ID_COL = 0;
    private static final int MEMORY_NAME_COL = 1;
    private static final int DESCRIPTION_COL = 2;
    private static final int CREATION_DATE_COL = 3;
    private static final int IMAGES_PATH_COL = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + MEMORY_ID_COLNAME + " INTEGER PRIMARY KEY,"
                + MEMORY_NAME_COLNAME + " TEXT, "
                + DESCRIPTION_COLNAME + " TEXT, "
                + CREATION_DATE_COLNAME + " INTEGER, "
                + IMAGES_PATH_COLNAME + " TEXT);";
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
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    /**
     * This function is query that inserts new line of memory into the database.
     * @param memory The new memory
     * @return true in case the query is good, and false otherwise.
     */
    public boolean setNewMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEMORY_ID_COLNAME, memory.getMemoryID());
        contentValues.put(MEMORY_NAME_COLNAME, memory.getMemoryName());
        contentValues.put(DESCRIPTION_COLNAME, memory.getDescription());
        contentValues.put(CREATION_DATE_COLNAME, memory.getCreationTime().getTime());
        contentValues.put(IMAGES_PATH_COLNAME, memory.getImagesPath());

        if(db.insert(TABLE_NAME, null, contentValues) < 0){
            return false;
        }
        return true;
    }

    /**
     * This function is an database query that goes
     * to the database and retrieves memories object from it.
     * @return ArrayList<Memory> object in case the query is good, and null otherwise.
     */
    public ArrayList<Memory> getAllMemoriesQuery() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Memory> result = null;
        Cursor res = db.rawQuery("SELECT * FROM TABLE_NAME", null);
        while(res.moveToNext()){
            result.add(new Memory(res.getInt(MEMORY_ID_COL),
                    res.getString(MEMORY_NAME_COL),
                    res.getString(DESCRIPTION_COL),
                    res.getLong(CREATION_DATE_COL),
                    res.getString(IMAGES_PATH_COL)));
        }
        res.close();
        return result;
    }
}