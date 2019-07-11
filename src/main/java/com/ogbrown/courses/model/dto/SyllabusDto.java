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

package com.ogbrown.courses.model.dto;

public class SyllabusDto {
	private CourseDto course;
	private ScheduleDto schedule;
	private String description;
	private String objectives;
	private String lessonPlan;
    public SyllabusDto() {
        // TODO Auto-generated constructor stub
    }
    public CourseDto getCourse() {
        return course;
    }
    public void setCourse(CourseDto course) {
        this.course = course;
    }
    public ScheduleDto getSchedule() {
        return schedule;
    }
    public void setSchedule(ScheduleDto schedule) {
        this.schedule = schedule;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getObjectives() {
        return objectives;
    }
    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }
    public String getLessonPlan() {
        return lessonPlan;
    }
    public void setLessonPlan(String lessonPlan) {
        this.lessonPlan = lessonPlan;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((lessonPlan == null) ? 0 : lessonPlan.hashCode());
        result = prime * result + ((objectives == null) ? 0 : objectives.hashCode());
        result = prime * result + ((schedule == null) ? 0 : schedule.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SyllabusDto other = (SyllabusDto) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (lessonPlan == null) {
            if (other.lessonPlan != null)
                return false;
        } else if (!lessonPlan.equals(other.lessonPlan))
            return false;
        if (objectives == null) {
            if (other.objectives != null)
                return false;
        } else if (!objectives.equals(other.objectives))
            return false;
        if (schedule == null) {
            if (other.schedule != null)
                return false;
        } else if (!schedule.equals(other.schedule))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "SyllabusDto [course=" + course + ", schedule=" + schedule + ", description=" + description
                + ", objectives=" + objectives + ", lessonPlan=" + lessonPlan + "]";
    }

}
