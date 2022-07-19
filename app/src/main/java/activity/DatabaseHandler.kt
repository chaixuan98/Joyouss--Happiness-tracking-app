package activity

//SQLite database handler for storing activities

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseHandler(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Db"
        private val TABLE_ACT = "ActivityTable"
        private val KEY_ANAME = "ActivityName"
        private val KEY_ATYPE = "ActivityType"
        private val KEY_AID = "ActivityID"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields to store activity info
        val CREATE_ACT_TABLE = ("CREATE TABLE $TABLE_ACT ("
                + "$KEY_AID INTEGER PRIMARY KEY AUTOINCREMENT, $KEY_ANAME TEXT,"// $KEY_ATYPE INTEGER, "
                + "$KEY_ATYPE INTEGER)"
                )
        db?.execSQL(CREATE_ACT_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //drop table if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACT)
        onCreate(db)
    }

    //write activity to database
    fun addActivity(context: Context, activity: Activity) {
        val cv = ContentValues()
        cv.put(KEY_ANAME, activity.aName)
        cv.put(KEY_ATYPE, activity.aType)
        val db = this.writableDatabase
        try {
            db.insert(TABLE_ACT, null, cv)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    //retrieve activity from database
    fun getActivities(context: Context, type: Int): ArrayList<Activity> {

        val query = "Select * From $TABLE_ACT WHERE $KEY_ATYPE = $type"

        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        val activities = ArrayList<Activity>()

        if (cursor.count == 0) {
            Toast.makeText(context, "No activities found", Toast.LENGTH_SHORT).show()
        } else {
            cursor.moveToFirst()
            while (!cursor.isAfterLast()) {
                val activity = Activity()
                activity.aName = cursor.getString(cursor.getColumnIndex(KEY_ANAME))
                activities.add(activity)
                cursor.moveToNext()
            }
            Toast.makeText(context, "${cursor.count.toString()} activities available", Toast.LENGTH_SHORT).show()
        }
        cursor.close()
        return activities
    }
}