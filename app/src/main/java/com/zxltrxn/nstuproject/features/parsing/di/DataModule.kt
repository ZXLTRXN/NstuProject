package com.zxltrxn.nstuproject.features.parsing.di

import com.zxltrxn.nstuproject.features.parsing.commonData.Parser
import com.zxltrxn.nstuproject.features.parsing.commonDomain.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.commonData.AssetManager
import com.zxltrxn.nstuproject.features.parsing.commonData.ParserRepoImpl
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.PointsParser
import com.zxltrxn.nstuproject.features.parsing.minimumPoints.data.model.PointsData
import com.zxltrxn.nstuproject.features.parsing.plan.data.PlanParser
import com.zxltrxn.nstuproject.features.parsing.plan.data.model.PlanData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun providePointsParser(): Parser<PointsData> {
        return PointsParser()
    }

    @Provides
    @Singleton
    fun providePlanParser(): Parser<PlanData> {
        return PlanParser()
    }

    @Provides
    @Singleton
    fun provideParserRepo(
        assetManager: AssetManager,
        pointsParser: Parser<PointsData>,
        planParser: Parser<PlanData>
    ): ParserRepo {
        return ParserRepoImpl(assetManager, pointsParser, planParser)
    }

}