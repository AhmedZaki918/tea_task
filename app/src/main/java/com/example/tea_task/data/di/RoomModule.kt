package com.example.tea_task.data.di

import android.app.Application
import androidx.room.Room
import com.example.tea_task.data.local.CompetitionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) =
        Room.databaseBuilder(app, CompetitionDatabase::class.java, "competition_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(db: CompetitionDatabase) = db.getCompetitionDao()
}