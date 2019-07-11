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

package com.ogbrown.courses.model;

import com.ogbrown.courses.model.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;

public interface Course {
    Long getId();

    void setId(Long id);

    String getTitle();

    void setTitle(String title);

    String getShortTitle();

    void setShortTitle(String shortTitle);

    List<CourseOfferingDto> getCourseOfferings();

    void setCourseOfferings(List<CourseOfferingDto> courseOfferings);

    String getUrlSlug();

    void setUrlSlug(String urlSlug);

    short getNumberOfSessions();

    void setNumberOfSessions(short numberOfSessions);

    Set<CourseDto> getPrerequisites();

    void setPrerequisites(Set<CourseDto> prerequisites);

    Set<TextBookDto> getTextBooks();

    void setTextBooks(Set<TextBookDto> textBooks);

    List<ObjectiveDto> getCourseObjectives();

    void setCourseObjectives(List<ObjectiveDto> courseObjectives);

    void setCurrentInstructor(InstructorDto currentInstructor);

    void setCurrentCourseOffering(CourseOfferingDto currentCourseOffering);

    List<LocalDate> getCurrentSchedule();

    void setCurrentSchedule(List<LocalDate> currentSchedule);

    SortedMap<Short, List<LessonPlanDto>> getLessonPlanMap();

    void setLessonPlanMap(SortedMap<Short, List<LessonPlanDto>> lessonPlanMap);

    String getPrerequisitesShortNames();

    void setPrerequisitesShortNames(String prerequisitesShortNames);

    boolean isActive();

    void setActive(boolean active);

    String getCurrentCourseLabel();

    CourseOfferingDto getCurrentCourseOffering();

    InstructorDto getCurrentInstructor();


}
