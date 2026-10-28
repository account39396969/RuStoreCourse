package com.example.rustorecourse.data.mapper

import com.example.rustorecourse.data.dto.AppDto
import com.example.rustorecourse.domain.model.App

class AppMapper {
    fun toDomain(dto: AppDto): App {
        return App(
            id = dto.id,
            name = dto.title,
            description = dto.shortDescription,
            category = dto.categoryName
        )
    }
}