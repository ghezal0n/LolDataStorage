package tn.esprit.loldatastorage.dao

import androidx.room.*
import tn.esprit.loldatastorage.data.Champion


//TODO 7 "Create the dao for the entity"
@Dao
interface ChampionDao {
    @Insert
    fun insert(champ: Champion)
    @Update
    fun update(champ: Champion)
    @Delete
    fun delete(champ: Champion)
    @Query(value="SELECT * FROM champions")
    fun getAllChamps(): List<Champion>

}