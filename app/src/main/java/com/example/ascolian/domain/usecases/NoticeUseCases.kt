package com.example.ascolian.domain.usecases

import com.example.ascolian.data.NoticeRepository.NoticeRepository
import com.example.ascolian.domain.Notice
import javax.inject.Inject

// NoticeUseCases.kt
class NoticeUseCases @Inject constructor(
    private val repository: NoticeRepository
) {
    suspend fun fetchNotices(): List<Notice> = repository.getNotices()
}