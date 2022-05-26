package com.zxltrxn.nstuproject.features.parsing.commonDomain

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import javax.inject.Inject


class AssetManager @Inject constructor(@ApplicationContext private val context: Context) {
    private val TAG = javaClass.simpleName

    fun read(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            Log.e(TAG, "readFromAssets: $e")
            null
        }
    }
}
