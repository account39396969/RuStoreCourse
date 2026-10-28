package com.example.rustorecourse.domain.repository

import com.example.rustorecourse.domain.model.App

interface AppRepository {
    fun getApps(): List<App>
}