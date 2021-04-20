package br.com.phro.sqlite.database.dao

import android.content.ContentValues
import android.content.Context
import br.com.phro.sqlite.App.Companion.database
import br.com.phro.sqlite.database.DatabaseConstants.COLUMN_ID
import br.com.phro.sqlite.database.DatabaseConstants.COLUMN_PASSWORD
import br.com.phro.sqlite.database.DatabaseConstants.COLUMN_USERNAME
import br.com.phro.sqlite.database.entity.User

class UserDAO(private val context: Context) {

    private val contentValues = ContentValues()
    private val writableDatabase = database.writableDatabase
    private val readableDatabase = database.readableDatabase

    fun insertUser(user: User) {

        contentValues.apply {
            put("USERNAME", user.username)
            put("PASSWORD", user.password)
        }

        writableDatabase.insert(USER_TABLE, null, contentValues)
    }

    fun getUsers(): MutableList<User> {
        val userList = mutableListOf<User>()
        val cursor = readableDatabase.rawQuery(SELECT_ALL, null)

        while (cursor.moveToNext()) {
            userList
                .add(
                    generateUser(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
        }

        cursor.close()

        return userList
    }

    fun getUser(id: Int?): User? {
        val cursor = readableDatabase.rawQuery(SELECT_ONE, arrayOf(id.toString()))

        if (cursor.count > 0) {
            cursor.moveToFirst()

            return generateUser(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2)
            )
        }

        cursor.close()
        return null
    }

    fun updateUser(user: User) {
        val newValues = ContentValues().apply {
            put(COLUMN_USERNAME, user.username)
            put(COLUMN_PASSWORD, user.password)
        }

        val selectionArguments = arrayOf(user.id.toString())

        database.writableDatabase.update(
            USER_TABLE,
            newValues,
            WHERE_CLAUSE_UPDATE,
            selectionArguments
        )
    }

    fun deleteUsers() {
        database.writableDatabase.delete(USER_TABLE, null, null)
    }

    fun deleteUserByName(name: String) {
        val selectionArguments = arrayOf(name)

        database.writableDatabase.delete(USER_TABLE, WHERE_CLAUSE_DELETE, selectionArguments)
    }

    private fun generateUser(id: Int, userName: String, password: String): User {
        return User(id, userName, password)
    }

    companion object {
        const val USER_TABLE = "user"
        const val SELECT_ALL = "SELECT * FROM $USER_TABLE"
        const val SELECT_ONE = "SELECT * FROM $USER_TABLE WHERE $COLUMN_ID LIKE ?"
        const val WHERE_CLAUSE_DELETE = "$COLUMN_USERNAME LIKE ?"
        const val WHERE_CLAUSE_UPDATE = "$COLUMN_ID LIKE ?"
    }
}