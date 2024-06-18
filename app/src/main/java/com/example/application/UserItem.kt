package com.example.application

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "User_Item_Table")
class UserItem(
    @ColumnInfo(name = "Name") var name: String,
    @ColumnInfo(name = "Email") var email: String,
    @ColumnInfo(name = "Phone") var phone: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0) {

}