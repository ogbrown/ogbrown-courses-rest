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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ogbrown.courses.model.NoClassDate;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class NoClassDateDtoTest {
    private NoClassDate noClassDate;

    @Before
    public void setUp() {
        noClassDate = new NoClassDateDto();
    }

    @Test
    public void test2ArgConstructorSetsNoClassDateProperly() {
        noClassDate = new NoClassDateDto(LocalDate.of(2019,4,20), LocalTime.MIDNIGHT, Duration.ofHours(24),"Happy 20th of April");
        assertEquals("Date should be \"2019-04-20\"", LocalDate.of(2019,4,20), noClassDate.getDate());
        assertEquals("Description should be the same","Happy 20th of April", noClassDate.getDescription());
    }

    @Test
    public void testCompareToShouldSortByDate() {
        List<NoClassDateDto> noClassDateDtos = new ArrayList<>();
        noClassDate = new NoClassDateDto(LocalDate.of(2019,4,20), LocalTime.MIDNIGHT, Duration.ofHours(24), "Happy 20th of April");
        noClassDateDtos.add((NoClassDateDto)noClassDate);
        noClassDate = new NoClassDateDto(LocalDate.of(2019,1,1), LocalTime.MIDNIGHT, Duration.ofHours(24), "Happy New Year 2019");
        noClassDateDtos.add((NoClassDateDto)noClassDate);

        assertEquals("New Year should be in second index!", new NoClassDateDto(LocalDate.of(2019,1,1), LocalTime.MIDNIGHT, Duration.ofHours(24), "Happy New Year 2019") , noClassDateDtos.get(1));

        Collections.sort(noClassDateDtos);
        assertEquals("New Year should be in first index after sort!", new NoClassDateDto(LocalDate.of(2019,1,1), LocalTime.MIDNIGHT, Duration.ofHours(24), "Happy New Year 2019") , noClassDateDtos.get(0));
    }

    @Test
    public void testSettersAndBuilderAndEqualsAndHashcodeShouldReturnEqualityOnDateAndDescription() {
        noClassDate.setDate(LocalDate.of(2019,1,1));
        noClassDate.setDescription("Happy New Year 2019");

        NoClassDate anotherNoClassDate = new NoClassDateDto(LocalDate.of(2019,4,20), LocalTime.MIDNIGHT, Duration.ofHours(24), "Happy 20th of April");
        assertNotEquals("Should not be equal", noClassDate, anotherNoClassDate);

        anotherNoClassDate = NoClassDateDto.builder()
                .date(LocalDate.of(2019,1,1))
                .description("Happy New Year 2019")
                .build();
        assertEquals("Should be equal", noClassDate, anotherNoClassDate);
    }

    @Test
    public void testObjectMapper() {
        noClassDate.setDate(LocalDate.of(2019,1,1));
        noClassDate.setDescription("Happy New Year 2019");
        String s = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            s = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(noClassDate);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertTrue(s.contains("2019"));
    }
}
