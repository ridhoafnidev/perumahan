package com.ridhoafnidev.project.core_data.data.local.room

import androidx.room.*
import com.ridhoafnidev.project.core_data.data.local.entity.AuthEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AuthDao {

    @Query("SELECT * FROM AuthEntity")
    abstract fun selectAuthAsFlow(): Flow<AuthEntity?>

    @Query("DELETE FROM AuthEntity")
    abstract suspend fun truncate(): Int

    @Insert(entity = AuthEntity::class, onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(
        authEntity: AuthEntity
    )

    @Transaction
    open suspend fun replace(authEntity: AuthEntity) {
        truncate()
        insert(authEntity)
    }
}