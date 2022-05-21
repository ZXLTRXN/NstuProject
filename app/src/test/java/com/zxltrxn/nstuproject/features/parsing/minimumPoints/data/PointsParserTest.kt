package com.zxltrxn.nstuproject.features.parsing.minimumPoints.data

import com.zxltrxn.nstuproject.features.Page
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test


class PointsParserTest {
    private val parser: PointsParser = PointsParser()
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest{
        Assert.assertEquals(4, 2 + 2)
    }
}