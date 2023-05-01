package com.easyprog.peil.di.modules

import com.easyprog.peil.data.FirebaseSource
import com.easyprog.peil.data.FirebaseSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FirebaseSourceModule {

    @Binds
    @Singleton
    abstract fun provideFirebaseSource(firebaseSource: FirebaseSourceImpl): FirebaseSource
}