package com.ntl.guidelinesapp.modules.room_db.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ntl.guidelinesapp.modules.room_db.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDatabaseTest : TestCase() {

    private lateinit var db: UserDatabase
    private lateinit var dao: UserDAO

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDatabase::class.java).build()
        dao = db.getUserDAO()!!
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun writeAndReadUser() = runBlocking {
        val user = User("linhnt", "165/5", "90", "495")
        dao.addUser(user)
        val users = dao.getLast20User()
        assertThat(users.contains(user)).isTrue()
    }
}