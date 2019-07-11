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

package com.ogbrown.courses.service;

import com.ogbrown.courses.model.dto.NoClassDateDto;
import com.ogbrown.courses.repository.NoClassDateRepository;
import com.ogbrown.courses.service.converter.NoClassDateModelConverter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NoClassDateServiceImpl implements NoClassDateService {
    private NoClassDateRepository repository;
    private static List<NoClassDateDto> noClassDates;
    private static List<LocalDate> dates;

    public NoClassDateServiceImpl(NoClassDateRepository repository) {
        this.repository = repository;
        noClassDates = new ArrayList<>();
        dates = new ArrayList<>();

    }

    @PostConstruct
    public void loadNoClassDates() {
        noClassDates.addAll(list());
        for (NoClassDateDto noClassDate: noClassDates) {
            dates.add(noClassDate.getDate());
        }
    }
    public List<NoClassDateDto> list() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(NoClassDateModelConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public void add(NoClassDateDto noClassDate) {
        repository.save(NoClassDateModelConverter.toEntity(noClassDate));
    }

    public static boolean containsDate(LocalDate date) {
        return dates.contains(date);
    }
}
