package com.example.rustorecourse.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rustorecourse.data.repository.AppRepositoryImpl
import com.example.rustorecourse.domain.model.App
import com.example.rustorecourse.domain.repository.AppRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppListViewModel(
    private val repository: AppRepository = AppRepositoryImpl()
) : ViewModel() {

    private val _apps = MutableStateFlow<List<App>>(emptyList())
    val apps = _apps.asStateFlow()

    private val _snackbarEvent = MutableSharedFlow<Unit>()
    val snackbarEvent = _snackbarEvent.asSharedFlow()

    init {
        _apps.value = repository.getApps()
    }

    fun onToolbarClicked() {
        viewModelScope.launch {
            _snackbarEvent.emit(Unit)
        }
    }
}