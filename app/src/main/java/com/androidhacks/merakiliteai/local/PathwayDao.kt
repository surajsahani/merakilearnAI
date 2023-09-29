package com.androidhacks.merakiliteai.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PathwayDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPathway(pathway: List<PathwayEntity>)

    @Query("SELECT * FROM pathways")
    suspend fun getAllPathways(): List<PathwayEntity>
}