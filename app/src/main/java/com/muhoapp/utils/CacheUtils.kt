package com.muhoapp.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.math.BigDecimal

object CacheUtils {
    enum class CacheType {
        PREFERENCES
    }

    /**
     * 获取格式化后的缓存大小
     */
    fun getCacheSize(mContext: Context?): String {
        var folderSize = getFolderSize(mContext?.cacheDir)
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            folderSize += getFolderSize(mContext?.externalCacheDir)
        }
        return getFormatSize(folderSize.toDouble())
    }

    /**
     * 获取未格式化缓存大小
     */
    private fun getFolderSize(file: File?): Long {
        var size: Long = 0
        try {
            val listFiles = file?.listFiles()
            for (i in listFiles!!.indices) {
                if (listFiles[i].isDirectory) {
                    size += getFolderSize(listFiles[i])
                } else {
                    size += listFiles[i].length()
                }
            }
        } catch (a: Exception) {
            a.printStackTrace()
        }
        return size
    }

    /**
     * 格式化缓存大小
     */
    private fun getFormatSize(size: Double): String {
        val kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        val megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            val result1 = BigDecimal(kiloByte);
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "K";
        }

        val gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            val result2 = BigDecimal(megaByte)
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "M";
        }

        val teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            val result3 = BigDecimal(gigaByte);
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "GB";
        }
        val result4 = BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    /**
     * 删除缓存文件
     */
    fun deleteFileByDir(type: CacheType, mContext: Context?) {
        var dir: File? = null
        when (type) {
            CacheType.PREFERENCES -> {
                val dirPath = "${mContext?.filesDir?.parentFile?.path}/shared_prefs"
                dir = File(dirPath)
            }
        }
        if (dir.exists() && dir.isDirectory) {
            val listFiles = dir.listFiles()
            listFiles?.forEach {
                it.delete()
            }
        }
    }

}
