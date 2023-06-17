package com.ashehata.brightskies_task.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ashehata.brightskies_task.modules.recipes.data.local.dao.RecipesDao
import com.ashehata.brightskies_task.modules.recipes.data.model.RecipeDataModel

@Database(entities = [RecipeDataModel::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}
