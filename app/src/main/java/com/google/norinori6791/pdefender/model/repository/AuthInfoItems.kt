package com.google.norinori6791.pdefender.model.repository

import com.google.norinori6791.pdefender.MainApplication
import com.google.norinori6791.pdefender.model.entity.AuthInfo
import java.text.SimpleDateFormat

class AuthInfoItems {
    val db = MainApplication.helper.writableDatabase

    fun getItems(category: Int = 0): MutableList<AuthInfo> {
        val mutableList: MutableList<AuthInfo> = mutableListOf()

        val sqlSelect = "SELECT * FROM AuthInfo"
        var cousor = db.rawQuery(sqlSelect, null)

        while (cousor.moveToNext()) {
            val item = AuthInfo(
                cousor.getInt(cousor.getColumnIndex("_id")),
                cousor.getInt(cousor.getColumnIndex("category")),
                cousor.getString(cousor.getColumnIndex("title")),
                cousor.getString(cousor.getColumnIndex("url")),
                cousor.getString(cousor.getColumnIndex("uid")),
                cousor.getString(cousor.getColumnIndex("password")),
                cousor.getString(cousor.getColumnIndex("memo"))
            )
            mutableList.add(item)
        }
        return mutableList
    }
}