package com.example.ascolian.data.NoticeRepository

import com.example.ascolian.domain.Notice
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class NoticeRepositoryImpl @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase
) : NoticeRepository {
    override suspend fun getNotices(): List<Notice> {
        val snapshot = firebaseDatabase.getReference("Notices").get().await()
        return snapshot.children.mapNotNull { it.getValue(Notice::class.java) }
    }
}