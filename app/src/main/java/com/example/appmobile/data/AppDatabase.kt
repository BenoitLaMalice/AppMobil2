package com.example.appmobile.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.appmobile.MobileApplication
import com.example.appmobile.data.models.MangaModel
import com.example.appmobile.data.sources.MangaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.internal.aggregatedroot.codegen._com_example_appmobile_MobileApplication
import javax.inject.Singleton

@Database(entities = arrayOf(MangaModel::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun mangaDao():MangaDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "appmobile_database").build()
                    INSTANCE=instance
                instance
            }

        }
    }
}

@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule{
    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getDatabase(MobileApplication.getContext()!!)
    }
}
