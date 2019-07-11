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


import com.ogbrown.courses.model.dto.CourseOfferingDto;
import com.ogbrown.courses.model.entity.CourseOfferingEntity;

import java.util.stream.Collectors;

public class CourseOfferingModelConverter {

    public static CourseOfferingDto toDto(CourseOfferingEntity entity) {
        return CourseOfferingDto.builder()
                .id(entity.getId())
                .contEdHours(entity.getContEdHours())
                .courseNumber(entity.getCourseNumber())
                .term(entity.getTerm())
                .termNumber(entity.getTermNumber())
                .sessionCount(entity.getSessionCount())
                .courseOfferingStatus(entity.getCourseOfferingStatus())
                .daysOfWeek(entity.getDaysOfWeek())
                .daysOfWeekArray(entity.getDaysOfWeekArray())
                .instructors(entity.getInstructors().stream().map(InstructorModelConverter::toDto).collect(Collectors.toList()))
                .location(entity.getLocation())
                .room(entity.getRoom())
                .start(entity.getStart())
                .startTime(entity.getStartTime())
                .end(entity.getEnd())
                .endTime(entity.getEndTime())
                .seats(entity.getSeats())
                .build();

    }
}
