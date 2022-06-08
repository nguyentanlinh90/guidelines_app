package com.ntl.guidelinesapp.modules.room_db.database

import androidx.room.*
import com.ntl.guidelinesapp.modules.room_db.User

@Dao
interface UserDAO {
    @Insert
    fun insertUser(user: User?)

    //coroutine
    @Insert
    suspend fun addUser(user: User?)

    @Query("SELECT * FROM user LIMIT 20")
    suspend fun getLast20User(): List<User>

    @get:Query("SELECT * FROM user")
    val listUser: List<User?>?

    @Query("SELECT * FROM user where username= :username")
    fun checkUser(username: String?): List<User?>?

    @Update
    fun updateUser(user: User?)

    @Delete
    fun deleteUser(user: User?)

    @Query("DELETE FROM user")
    fun dellAllUser()

    @Query("select * from user where username like '%' || :username || '%'")
    fun searchUsername(username: String?): List<User?>?
}