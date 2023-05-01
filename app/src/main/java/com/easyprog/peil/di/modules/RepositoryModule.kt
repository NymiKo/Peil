package com.easyprog.peil.di.modules

import com.easyprog.peil.fragments.lessons_list.LessonsListRepository
import com.easyprog.peil.fragments.lessons_list.LessonsListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideLessonsListRepository(lessonsListRepositoryImpl: LessonsListRepositoryImpl): LessonsListRepository
}