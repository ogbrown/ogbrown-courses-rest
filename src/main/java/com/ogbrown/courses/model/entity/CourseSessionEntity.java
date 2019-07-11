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

package com.ogbrown.courses.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@IdClass(CourseSessionPk.class)
@Table(name = "course_session")
public class CourseSessionEntity {
    public final static short LAST_SESSION=(short)9999;
	@Id
	@ManyToOne
	@JoinColumn(name = "courseid", referencedColumnName = "id")
	private CourseEntity course;
	@Id
	@Column(name = "sessionnumber", columnDefinition="SMALLINT")
	private Short sessionNumber;
	@OneToMany(targetEntity = PageEntity.class, cascade = { CascadeType.ALL })
	  @JoinTable(name = "coursesessions_pages", joinColumns = { @JoinColumn(name = "courseid"), @JoinColumn(name = "sessionnumber") }, inverseJoinColumns = { @JoinColumn(name = "pageid") })
	private List<PageEntity> pages;
	@ManyToMany(targetEntity = LessonPlanEntity.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "coursesession_lessonplan",joinColumns = { @JoinColumn(name = "courseid"), @JoinColumn(name = "sessionnumber") }, inverseJoinColumns = { @JoinColumn(name = "lessonplanid") })
    private List<LessonPlanEntity> lessonPlan;

	public CourseSessionEntity() {

	}


	public CourseSessionEntity(CourseEntity course, Short sessionNumber, List<PageEntity> pages) {
		this.course = course;
		this.sessionNumber = sessionNumber;
		this.pages = pages;
	}


	public CourseSessionEntity(CourseEntity course, Short sessionNumber) {
		this.course = course;
		this.sessionNumber = sessionNumber;
	}


	public CourseSessionEntity(CourseEntity course, short sessionNumber, short pageOrder) {
		this.course = course;
		this.sessionNumber = sessionNumber;
	}


	public CourseEntity getCourse() {
		return course;
	}
	public void setCourse(CourseEntity course) {
		this.course = course;
	}
	public short getSessionNumber() {
		return sessionNumber;
	}
	public void setSessionNumber(short sessionNumber) {
		this.sessionNumber = sessionNumber;
	}
	
	public List<PageEntity> getPages() {
		return pages;
	}

	public void setPages(List<PageEntity> pages) {
		this.pages = pages;
	}

	public List<LessonPlanEntity> getLessonPlan() {
        return lessonPlan;
    }


    public void setLessonPlan(List<LessonPlanEntity> lessonPlan) {
        this.lessonPlan = lessonPlan;
    }


  
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((course == null) ? 0 : course.hashCode());
        result = prime * result + ((lessonPlan == null) ? 0 : lessonPlan.hashCode());
        result = prime * result + ((pages == null) ? 0 : pages.hashCode());
        result = prime * result + ((sessionNumber == null) ? 0 : sessionNumber.hashCode());
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
		CourseSessionEntity other = (CourseSessionEntity) obj;
        if (course == null) {
            if (other.course != null)
                return false;
        } else if (!course.equals(other.course))
            return false;
        if (lessonPlan == null) {
            if (other.lessonPlan != null)
                return false;
        } else if (!lessonPlan.equals(other.lessonPlan))
            return false;
        if (pages == null) {
            if (other.pages != null)
                return false;
        } else if (!pages.equals(other.pages))
            return false;
        if (sessionNumber == null) {
            if (other.sessionNumber != null)
                return false;
        } else if (!sessionNumber.equals(other.sessionNumber))
            return false;
        return true;
    }


    @Override
	public String toString() {
		try {
			return "CourseSession [course=" + (null==course?"Null":course) + ", sessionNumber=" + sessionNumber + ", pages=" + (pages!=null?pages.size():"none") + ", lessonPlan=" + (lessonPlan!=null?lessonPlan.size():"none") + "]";
		} catch(NullPointerException npe) {
			return "CourseSession [course=" + (null==course?"Null":course) + ", sessionNumber=" + sessionNumber + " pages=n/a]";
		}
	}

}
