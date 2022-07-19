package crudmoodstatus

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

/*ClassHandler : Handler class MoodEntry*/

const val DATABASE_NAME = "Database"
const val TABLE_NAME = "MoodEntry"
const val COL_ENTRY_ID = "entryID"
const val COL_ENTRY_TIME = "entryTime"
const val COL_ENTRY_DATE = "entryDate"
const val COL_ENTRY_MOOD = "entryMood"
const val COL_ENTRY_ACTIVITY_STATUS = "entryActivityStatus"

class MoodHandler(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        //create MoodEntry class
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ENTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_ENTRY_TIME + " VARCHAR(256)," +
                COL_ENTRY_DATE + " VARCHAR(256)," +
                COL_ENTRY_MOOD + " INTEGER," +
                COL_ENTRY_ACTIVITY_STATUS + " VARCHAR(256))";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    //insert a row with attribute values : EntryID(automated increment), EntryTime, EntryDate, EntryMood, EntryActivityStatus
    fun insertData(moodEntry: MoodEntry) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ENTRY_TIME, moodEntry.entryTime)
        cv.put(COL_ENTRY_DATE, moodEntry.entryDate)
        cv.put(COL_ENTRY_MOOD, moodEntry.entryMood)
        cv.put(COL_ENTRY_ACTIVITY_STATUS, moodEntry.entryActivityStatus)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong())
            Toast.makeText(context, "Entry save error", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Entry recorded.", Toast.LENGTH_SHORT).show()
    }

    //show all rows by placing into a list
    fun readData(): MutableList<MoodEntry> {
        val list: MutableList<MoodEntry> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val entry = MoodEntry()
                entry.entryID = result.getString(result.getColumnIndex(COL_ENTRY_ID)).toInt()
                entry.entryTime = result.getString(result.getColumnIndex(COL_ENTRY_TIME))
                entry.entryDate = result.getString(result.getColumnIndex(COL_ENTRY_DATE))
                entry.entryMood = result.getString(result.getColumnIndex(COL_ENTRY_MOOD)).toInt()
                entry.entryActivityStatus =
                    result.getString(result.getColumnIndex(COL_ENTRY_ACTIVITY_STATUS))
                list.add(entry)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

}