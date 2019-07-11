/*
 * Copyright (c) 2017 - 2019 Oswald G. Brown, III
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ogbrown.courses.service.converter;

import com.ogbrown.courses.model.dto.CourseDto;
import com.ogbrown.courses.model.entity.CourseEntity;

import java.util.stream.Collectors;


public class CourseModelConverter {
    public static CourseDto toDto(CourseEntity entity) {
        return CourseDto.builder()
                .id(entity.getId())
                .numberOfSessions(entity.getNumberOfSessions())
                .courseLabels(entity.getCourseLabels().stream().map(CourseLabelModelConverter::toDto).collect(Collectors.toList()))
                .courseObjectives(entity.getCourseObjectives().stream().map(ObjectiveModelConverter::toDto).collect(Collectors.toList()))
                .courseOfferings(entity.getCourseOfferings().stream().map(CourseOfferingModelConverter::toDto).collect(Collectors.toList()))
                .prerequisites(entity.getPrerequisites().stream().map(CourseModelConverter::toDto).collect(Collectors.toSet()))
                .textBooks(entity.getTextBooks().stream().map(TextBookModelConverter::toDto).collect(Collectors.toSet()))
                .shortTitle(entity.getShortTitle())
                .title(entity.getTitle())
                .urlSlug(entity.getUrlSlug())
                .build();
    }

    public static CourseEntity toEntity(CourseDto dto) {
        return CourseEntity.builder()
                .id(dto.getId())
                .build();
    }
}
