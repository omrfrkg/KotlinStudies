package com.omrfrkg.kotlinmaps.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.omrfrkg.kotlinmaps.model.Place
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface PlaceDAO {

    /*@Query("Select * From PlaceTbl Where id = :id")
    fun getAll(id : String) : List<Place>*/

    @Query("Select * From PlaceTbl")
    fun getAll() : Flowable<List<Place>>

    @Delete
    fun delete(place: Place) : Completable

    @Insert
    fun insert (place : Place) : Completable
}