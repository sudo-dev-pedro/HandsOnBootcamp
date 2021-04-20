package br.com.phro.sqlite.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.phro.sqlite.database.DatabaseConstants.DATABASE_NAME
import br.com.phro.sqlite.database.DatabaseConstants.DATABASE_VERSION
import br.com.phro.sqlite.database.DatabaseConstants.SQL_CREATE_ENTRIES

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }
}