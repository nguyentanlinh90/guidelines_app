package com.ntl.guidelinesapp.modules.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(
    // if want change name of collum on table use @ColumnInfo
    // @ColumnInfo(name = "user_name")
    var username: String,
    var address: String,
    var year: String,
    var phone: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", year='" + year + '\'' +
                ", phone='" + phone + '\'' +
                '}'
    }
}