package com.google.norinori6791.pdefender.model.repository

import android.database.sqlite.SQLiteDatabase
import com.google.norinori6791.pdefender.MainApplication
import com.google.norinori6791.pdefender.model.entity.AuthInfo

class AuthInfoItem(val authInfo: AuthInfo){
    val db: SQLiteDatabase = MainApplication.helper.writableDatabase

    fun addAuthInfo(){
        val sqlInsert = "INSERT INTO AuthInfo (category, title, url, uid, password, memo) VALUES (?, ?, ?, ?, ?, ?)"
        val stmt = db.compileStatement(sqlInsert)

        stmt.bindLong(1, authInfo.category!!.toLong())
        stmt.bindString(2, authInfo.title)
        stmt.bindString(3,authInfo.url)
        stmt.bindString(4,authInfo.uid)
        stmt.bindString(5,authInfo.password)
        stmt.bindString(6,authInfo.memo)

        stmt.executeInsert()
    }

    fun removeAuthInfo(){
        val sqlDelete = "DELETE FROM AuthInfo WHERE _id = ?"
        val stmt = db.compileStatement(sqlDelete)
        stmt.bindLong(1, authInfo._id!!.toLong())
        stmt.executeUpdateDelete()
    }
    fun updateAuthInfo(){
        val sqlUpdate = "UPDATE AuthInfo SET" +
                "CATEGORY = ?," +
                "TITLE = ?," +
                "URL = ?," +
                "UID = ?," +
                "PASSWORD = ?," +
                "MEMO = ?" +
                "WHERE _id = ?"
        val stmt = db.compileStatement((sqlUpdate))
        stmt.bindLong(1, authInfo.category!!.toLong())
        stmt.bindString(2, authInfo.title)
        stmt.bindString(3,authInfo.url)
        stmt.bindString(4,authInfo.uid)
        stmt.bindString(5,authInfo.password)
        stmt.bindString(6,authInfo.memo)
        stmt.bindLong(7, authInfo._id!!.toLong())
    }
}