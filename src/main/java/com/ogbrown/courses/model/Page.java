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

import com.ogbrown.courses.model.dto.CourseSessionDto;
import com.ogbrown.courses.model.dto.PageDto;

import java.util.List;
import java.util.Set;

public interface Page {
    void publish();
    void draft();

    long getId();

    void setId(long id);

    String getTitle();

    void setTitle(String title);

    String getUrlSlug();

    void setUrlSlug(String urlSlug);

    String getFolderUrlSlug();

    void setFolderUrlSlug(String folderUrlSlug);

    String getMetaDescription();

    void setMetaDescription(String metaDescription);

    String getContentHeader();

    void setContentHeader(String contentHeader);

    String getMetaKeywordsCsv();

    void setMetaKeywordsCsv(String metaKeywordsCsv);

    String getContent();

    void setContent(String content);

    boolean isPublished();

    void setPublished(boolean published);

    boolean isProtectedPage();

    void setProtectedPage(boolean protectedPage);

    short getPageOrd();

    void setPageOrd(short pageOrd);

    List<PageDto> getParentPages();

    void setParentPages(List<PageDto> parentPages);

    List<PageDto> getChildPages();

    void setChildPages(List<PageDto> childPages);

    String getPrevPageLinkOverride();

    void setPrevPageLinkOverride(String prevPageLinkOverride);

    PageDto getPreviousPage();

    void setPreviousPage(PageDto previousPage);

    String getNextPageLinkOverride();

    void setNextPageLinkOverride(String nextPageLinkOverride);

    PageDto getNextPage();

    void setNextPage(PageDto nextPage);

    Set<CourseSessionDto> getCourseSessions();

    void setCourseSessions(Set<CourseSessionDto> courseSessions);

    String getNotes();

    void setNotes(String notes);
}
