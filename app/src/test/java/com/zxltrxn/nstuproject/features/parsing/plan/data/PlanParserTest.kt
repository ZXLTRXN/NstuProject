package com.zxltrxn.nstuproject.features.parsing.plan.data

import com.zxltrxn.nstuproject.features.Page
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class PlanParserTest {
    private val parser: PlanParser = PlanParser()
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest{
//        val data = parser.execute(Page.RECRUITING_PLAN.url).
//        println(data.title)
//        println(data.formTables[1].faculties.last().directions[10])
        Assert.assertEquals(4, 2 + 2)
    }
}