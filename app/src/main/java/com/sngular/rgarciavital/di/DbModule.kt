package com.sngular.rgarciavital.di

import android.app.Application
import androidx.room.Room
import com.sngular.rgarciavital.data.local.TmdbDao
import com.sngular.rgarciavital.data.local.TmdbDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application, callback: TmdbDatabase.Callback): TmdbDatabase{
        return Room.databaseBuilder(application, TmdbDatabase::class.java, "jrgv_database")
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    @Provides
    fun provideMovieAppDao(db: TmdbDatabase): TmdbDao{
        return db.getMovieAppDao()
    }
}