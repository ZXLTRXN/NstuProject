package com.zxltrxn.nstuproject.features.parsing.minimum_points.di

import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.PointsRepo
import com.zxltrxn.nstuproject.features.parsing.minimum_points.domain.usecase.GetPointsData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetPointsData(repo: PointsRepo): GetPointsData {
        return GetPointsData(repo = repo)
    }
}