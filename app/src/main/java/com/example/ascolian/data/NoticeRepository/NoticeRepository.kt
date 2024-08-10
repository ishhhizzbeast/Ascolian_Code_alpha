package com.example.ascolian.data.NoticeRepository

import com.example.ascolian.domain.Notice


// NoticeRepository.kt
interface NoticeRepository {
    suspend fun getNotices(): List<Notice>
}