package com.nominalista.expenses.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.nominalista.expenses.data.room.entities.KeywordEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface KeywordDao {

    @Query("SELECT * FROM keywords")
    fun observeAll(): Observable<List<KeywordEntity>>

    @Query("SELECT * FROM keywords")
    fun getAll(): Single<List<KeywordEntity>>

    @Query("SELECT * from keywords WHERE id = :id")
    fun observeById(id: Long): Observable<KeywordEntity>

    @Query("SELECT * from keywords WHERE id = :id")
    fun getById(id: Long): Single<KeywordEntity>

    @Update
    fun update(tag: KeywordEntity): Completable

    @Insert
    fun insert(tag: KeywordEntity): Single<Long>

    @Query("DELETE FROM keywords WHERE id = :id")
    fun deleteById(id: Long): Completable

}