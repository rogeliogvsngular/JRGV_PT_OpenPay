package com.sngular.rgarciavital.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sngular.rgarciavital.data.model.Pelicula
import com.sngular.rgarciavital.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [Pelicula::class], version = 1)
abstract class TmdbDatabase : RoomDatabase() {

    abstract fun getMovieAppDao(): TmdbDao

    class Callback @Inject constructor(
        private val database: Provider<TmdbDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback()
}