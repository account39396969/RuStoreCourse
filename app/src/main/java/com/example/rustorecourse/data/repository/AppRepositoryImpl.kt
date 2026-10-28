package com.example.rustorecourse.data.repository

import com.example.rustorecourse.data.dto.AppDto
import com.example.rustorecourse.data.mapper.AppMapper
import com.example.rustorecourse.domain.model.App
import com.example.rustorecourse.domain.repository.AppRepository

class AppRepositoryImpl : AppRepository {
    private val mapper = AppMapper()

    private val mockDtoApps = listOf(
        AppDto("1", "RuStore", "Магазин приложений", "Инструменты"),
        AppDto("2", "Гильдия Героев", "Легендарный рейд героев", "Игры"),
        AppDto("3", "VK", "Социальная сеть", "Социальные")
    )

    override fun getApps(): List<App> {
        return mockDtoApps.map { dto -> mapper.toDomain(dto) }
    }
}