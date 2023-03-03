package tn.esprit.loldatastorage.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val PICTURE = "PICTURE"
const val NAME = "NAME"
const val ROLE = "ROLE"

//TODO 6 "Change this class to an Entity"
@Entity(tableName = "champions")
data class Champion(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "champ_pic")
    val champPic: Int,
    @ColumnInfo(name = "champ_name")
    val champName: String,
    @ColumnInfo(name = "champ_role")
    val champRole: String

)