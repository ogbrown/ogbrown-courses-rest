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

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Builder
@Table(name = "course")
public class CourseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private String shortTitle;
	
	@OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="COURSE_ID")
	private List<CourseLabelEntity> courseLabels;
	@OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="COURSE_ID")
	private List<CourseOfferingEntity> courseOfferings;
//	private String instructor;
	@Column(unique=true)
	private String urlSlug;
	@Column(columnDefinition="SMALLINT")
	private short numberOfSessions;
	@ManyToMany(targetEntity = CourseEntity.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "course_prereqs", joinColumns = @JoinColumn( name="id"),
            inverseJoinColumns = @JoinColumn( name="prereq_id"))
	private Set<CourseEntity> prerequisites = new HashSet<CourseEntity>();
	@ManyToMany(targetEntity = TextBookEntity.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "course_textbooks", joinColumns = @JoinColumn( name="id"),
            inverseJoinColumns = @JoinColumn( name="textbook_id"))
    private Set<TextBookEntity> textBooks = new HashSet<TextBookEntity>();
	@ManyToMany(targetEntity = ObjectiveEntity.class, cascade = { CascadeType.REFRESH })
    @JoinTable(name = "course_objectives", joinColumns = @JoinColumn( name="course_id"),
            inverseJoinColumns = @JoinColumn( name="objective_id"))
	private List<ObjectiveEntity> courseObjectives;


	public CourseEntity() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}


	public String getCourseLabel() {
		return courseLabels.get(courseLabels.size()-1).getLabel();
	}
	public void setCourseLabel(CourseLabelEntity courseLabel) {
		this.courseLabels.add(courseLabel);
	}


	public List<CourseLabelEntity> getCourseLabels() {
        return courseLabels;
    }


    public void setCourseLabels(List<CourseLabelEntity> courseLabels) {
        this.courseLabels = courseLabels;
    }


    public List<CourseOfferingEntity> getCourseOfferings() {
		return courseOfferings;
	}
	public void setCourseOfferings(List<CourseOfferingEntity> courseOfferings) {
		this.courseOfferings = courseOfferings;
	}


	public String getUrlSlug() {
		return urlSlug;
	}
	public void setUrlSlug(String urlSlug) {
		this.urlSlug = urlSlug;
	}


	
	public short getNumberOfSessions() {
		return numberOfSessions;
	}
	public void setNumberOfSessions(short numberOfSessions) {
		this.numberOfSessions = numberOfSessions;
	}


	public Set<CourseEntity> getPrerequisites() {
        return prerequisites;
    }


    public void setPrerequisites(Set<CourseEntity> prerequisites) {
        this.prerequisites = prerequisites;
    }
    
    public Set<TextBookEntity> getTextBooks() {
        return textBooks;
    }


    public void setTextBooks(Set<TextBookEntity> textBooks) {
        this.textBooks = textBooks;
    }


    public List<ObjectiveEntity> getCourseObjectives() {
        return courseObjectives;
    }


    public void setCourseObjectives(List<ObjectiveEntity> courseObjectives) {
        this.courseObjectives = courseObjectives;
    }


    public String getCurrentLabel() {
        return this.courseLabels.get(courseLabels.size() - 1).getLabel();
    }
    public CourseOfferingEntity getCurrentCourseOffering() {
        return this.courseOfferings.get(0);
    }
    public InstructorEntity getCurrentInstructor() {
        return getCurrentCourseOffering().getInstructors().get(getCurrentCourseOffering().getInstructors().size() - 1);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseLabels == null) ? 0 : courseLabels.hashCode());
        result = prime * result + ((courseObjectives == null) ? 0 : courseObjectives.hashCode());
        result = prime * result + ((courseOfferings == null) ? 0 : courseOfferings.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + numberOfSessions;
        result = prime * result + ((prerequisites == null) ? 0 : prerequisites.hashCode());
        result = prime * result + ((shortTitle == null) ? 0 : shortTitle.hashCode());
        result = prime * result + ((textBooks == null) ? 0 : textBooks.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((urlSlug == null) ? 0 : urlSlug.hashCode());
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
        CourseEntity other = (CourseEntity) obj;
        if (courseLabels == null) {
            if (other.courseLabels != null)
                return false;
        } else if (!courseLabels.equals(other.courseLabels))
            return false;
        if (courseObjectives == null) {
            if (other.courseObjectives != null)
                return false;
        } else if (!courseObjectives.equals(other.courseObjectives))
            return false;
        if (courseOfferings == null) {
            if (other.courseOfferings != null)
                return false;
        } else if (!courseOfferings.equals(other.courseOfferings))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (numberOfSessions != other.numberOfSessions)
            return false;
        if (prerequisites == null) {
            if (other.prerequisites != null)
                return false;
        } else if (!prerequisites.equals(other.prerequisites))
            return false;
        if (shortTitle == null) {
            if (other.shortTitle != null)
                return false;
        } else if (!shortTitle.equals(other.shortTitle))
            return false;
        if (textBooks == null) {
            if (other.textBooks != null)
                return false;
        } else if (!textBooks.equals(other.textBooks))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (urlSlug == null) {
            if (other.urlSlug != null)
                return false;
        } else if (!urlSlug.equals(other.urlSlug))
            return false;
        return true;
    }


    @Override
	public String toString() {
		return "CourseDto [id=" + id + ", title=" + title + ", shortTitle=" + shortTitle + ", courseLabels=" + courseLabels
				+ ", courseOfferings=" + courseOfferings + ", urlSlug=" + urlSlug
				+ ", numberOfSessions=" + numberOfSessions + ", numberOfPreReqs=" + prerequisites.size() + "]";
	}

}
