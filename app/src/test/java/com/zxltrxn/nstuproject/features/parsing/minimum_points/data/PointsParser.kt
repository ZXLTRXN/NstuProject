package com.zxltrxn.nstuproject.features.parsing.minimum_points.data

import org.junit.Assert
import org.junit.Test


class PointsParserTest {
    private val parser:PointsParser = PointsParser()
    @Test
    fun addition_isCorrect() {
        parser.execute()
        Assert.assertEquals(4, 2 + 2)
    }
}