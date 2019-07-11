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

import com.ogbrown.courses.model.dto.CourseOfferingStatusDto;
import com.ogbrown.courses.model.dto.InstructorDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CourseOffering {
    Long getId();
	void setId(Long id);
	int getCourseNumber();
	void setCourseNumber(int courseNumber);
	int getTermNumber();
	void setTermNumber(int termNumber);
	String getTerm();
	void setTerm(String term);
	String getLocation();
	void setLocation(String location);
	String getRoom();
	void setRoom(String room);
	LocalDate getStart();
	void setStart(LocalDate start);
	LocalTime getStartTime();
	void setStartTime(LocalTime startTime);
	LocalTime getEndTime();
	void setEndTime(LocalTime endTime);
	LocalDate getEnd();
	void setEnd(LocalDate end);
	int getSessionCount();
	void setSessionCount(int sessionCount);
	float getContEdHours();
	void setContEdHours(float contEdHours);
	String getDaysOfWeek();
	void setDaysOfWeek(String daysOfWeek);
	DayOfWeek[] getDaysOfWeekArray();
	void setDaysOfWeekArray(DayOfWeek[] daysOfWeekArray);
	String getDaysOfWeekCsv();
	List<InstructorDto> getInstructors();
	void setInstructors(List<InstructorDto> instructors);
	Integer getSeats();
	void setSeats(Integer seats);
	CourseOfferingStatusDto getCourseOfferingStatus();
	void setCourseOfferingStatus(CourseOfferingStatusDto courseOfferingStatus);
}
