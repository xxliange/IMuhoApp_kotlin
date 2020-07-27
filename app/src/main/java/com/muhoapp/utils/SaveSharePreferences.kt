package com.muhoapp.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson

class SaveSharePreferences {
    companion object{
        private var mPreferences : SharedPreferences? = null
        private var mEdit : SharedPreferences.Editor? = null
        private var mGs : Gson? = null
        /**
         * 初始化sharePreferences
         */
        fun initSP(mContext : Context?, sPName : String){
            mPreferences = mContext?.getSharedPreferences(sPName, MODE_PRIVATE)
            mGs = Gson()
        }

        /**
         * 保存列表数据
         */
        fun <T>saveListData(tag : String , dataList : List<T>){
            if (dataList.isEmpty()) return
            val jsonData = mGs?.toJson(dataList)
            mEdit = mPreferences?.edit()
            mEdit?.putString(tag,jsonData)
            mEdit?.apply()
        }

        /**
         * 读取列表数据
         */
        fun getListData(tag: String ): String? {
            return mPreferences?.getString(tag,null)
        }
    }

}