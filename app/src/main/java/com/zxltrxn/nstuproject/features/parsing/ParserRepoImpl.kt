package com.zxltrxn.nstuproject.features.parsing

import android.util.Log
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
import com.zxltrxn.nstuproject.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ParserRepoImpl @Inject constructor(
    private val pointsParser: Parser<PointsData>
    ) : ParserRepo {
    override suspend fun getPointsData(): Resource<PointsData> =
        tryExecute{
            pointsParser.execute(Page.MINIMUM_POINTS.url)
        }

    private suspend fun <T>tryExecute(execute: suspend () -> T)
    : Resource<T> = withContext(Dispatchers.IO){
        return@withContext try{
            Resource.Success(execute())
        }
        catch (e: IOException){
            Resource.Error(1)
        }
        catch (e: Exception){
            Log.e(Constants.TAG, "getPointsData: ${e.message}")
            Resource.Error(0)
        }
    }
}