package com.example.ascolian.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ascolian.domain.Notice
import com.example.ascolian.domain.usecases.NoticeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetNoticesViewModel @Inject constructor(
    private val noticeUseCases: NoticeUseCases
) : ViewModel() {

    private val _notices = MutableStateFlow<List<Notice>>(emptyList())
    val notices = _notices.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        fetchNotices()
    }

    private fun fetchNotices() {
        viewModelScope.launch {
            _isLoading.value = true
            _notices.value = noticeUseCases.fetchNotices().sortedByDescending { it.date }
            _isLoading.value = false
        }
    }
}
