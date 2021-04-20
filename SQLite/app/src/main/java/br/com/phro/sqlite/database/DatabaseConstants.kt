package br.com.phro.sqlite.database

object DatabaseConstants {

    const val DATABASE_NAME = "Bootcamp"
    const val DATABASE_VERSION = 1

    private const val TABLE_NAME = "user"
    const val COLUMN_ID = "id"
    const val COLUMN_USERNAME = "username"
    const val COLUMN_PASSWORD = "password"

    const val SQL_CREATE_ENTRIES = """
    CREATE TABLE $TABLE_NAME
    ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
     $COLUMN_USERNAME TEXT NOT NULL,
     $COLUMN_PASSWORD TEXT NOT NULL DEFAULT '') """
}