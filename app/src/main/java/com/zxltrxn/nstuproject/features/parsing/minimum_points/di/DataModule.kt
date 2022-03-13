package com.zxltrxn.nstuproject.features.parsing.minimum_points.di

import com.zxltrxn.nstuproject.features.parsing.Parser
import com.zxltrxn.nstuproject.features.parsing.minimum_points.data.PointsParser
import com.zxltrxn.nstuproject.features.parsing.minimum_points.data.PointsRepoImpl
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.PointsRepo
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.model.PointsData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

//    @Provides
//    @Singleton
//    fun provideStorage(@ApplicationContext context:Context): Storage{
//        return SharedPrefsStorage(context = context)
//    }

    @Provides
    @Singleton
    fun providePointsParser(): Parser<PointsData> {
        return PointsParser()
    }

    @Provides
    @Singleton
    fun providePointsRepo(parser: Parser<PointsData>): PointsRepo {
        return PointsRepoImpl(parser)
    }
}