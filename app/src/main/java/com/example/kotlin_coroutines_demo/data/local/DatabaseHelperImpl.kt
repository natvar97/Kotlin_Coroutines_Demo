package com.example.kotlin_coroutines_demo.data.local

class DatabaseHelperImpl(private val database: AppDatabase) : DatabaseHelper {
    override suspend fun getUsers():
            List<UserEntity> = database.userDao().getAll()

    override suspend fun insertAll(
        users: List<UserEntity>
    ) = database.userDao().insertAll(users)

}