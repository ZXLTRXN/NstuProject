package com.zxltrxn.nstuproject.features.parsing.di

import com.zxltrxn.nstuproject.features.parsing.Parser
import com.zxltrxn.nstuproject.features.parsing.ParserRepo
import com.zxltrxn.nstuproject.features.parsing.ParserRepoImpl
import com.zxltrxn.nstuproject.features.parsing.minimum_points.data.PointsParser
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
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
    fun provideParserRepo(pointsParser: Parser<PointsData>): ParserRepo {
        return ParserRepoImpl(pointsParser)
    }

}