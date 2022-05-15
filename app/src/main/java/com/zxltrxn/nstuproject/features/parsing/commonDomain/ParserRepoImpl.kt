package com.zxltrxn.nstuproject.features.parsing.commonDomain

import android.util.Log
import com.zxltrxn.nstuproject.R
import com.zxltrxn.nstuproject.features.Page
import com.zxltrxn.nstuproject.features.parsing.commonData.Parser
import com.zxltrxn.nstuproject.features.parsing.commonData.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.domain.model.PointsData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class ParserRepoImpl @Inject constructor(
    private val pointsParser: Parser<PointsData>
) : ParserRepo {
    override suspend fun getPointsData(): Resource<PointsData> =
        tryExecute {
            pointsParser.execute(Page.MINIMUM_POINTS.url)
        }

    private suspend fun <T> tryExecute(execute: suspend () -> T): Resource<T> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                Resource.Success(execute())
            } catch (e: IOException) {
                Resource.Error(LocalizeString.Resource(R.string.network_error), 1)
            } catch (e: Exception) {
                Log.e(javaClass.simpleName, "tryExecute: ${e.message}")
                Resource.Error(LocalizeString.Resource(R.string.source_error), 0)
            }
        }
}