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

import com.ogbrown.courses.model.dto.BasicCourseOfferingInfoDto;
import com.ogbrown.courses.model.dto.CourseDto;

import java.util.ArrayList;
import java.util.List;

public class BasicCourseOfferingInfoModelConverter {
    public static List<BasicCourseOfferingInfoDto> toDto(CourseDto course) {
        List<BasicCourseOfferingInfoDto> basicCourseInfo = new ArrayList<>();
        course.getCourseOfferings().forEach(offering -> {
            basicCourseInfo.add(BasicCourseOfferingInfoDto.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .shortTitle(course.getShortTitle())
                    .courseLabel(course.getCurrentCourseLabel())
                    .courseNumber(offering.getCourseNumber())
                    .termNumber(offering.getTerm())
                    .term(offering.getTerm())
                    .courseOfferingStatus(offering.getCourseOfferingStatus().name())
                    .daysOfWeek(offering.getDaysOfWeekCsv())
                    .start(offering.getStart())
                    .startTime(offering.getStartTime())
                    .end(offering.getEnd())
                    .endTime(offering.getEndTime())
                    .prerequisitesShortNames(course.getPrerequisitesShortNames())
                    .urlSlug(course.getUrlSlug())
                    .build()
            );
        });
        return basicCourseInfo;
    }
}
