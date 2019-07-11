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

import com.ogbrown.courses.model.dto.NoClassDateDto;
import com.ogbrown.courses.model.entity.NoClassDateEntity;

import java.time.LocalDateTime;

public class NoClassDateModelConverter {
    /**
     * Converts a NoClassDateEntity object to a NoClassDateDto object. Assumes callers have set all fields as needed.
     * @param entity
     * @return
     */
    public static NoClassDateDto toDto(NoClassDateEntity entity) {
        return NoClassDateDto.builder()
                .date(entity.getDateTime().toLocalDate())
                .time(entity.getDateTime().toLocalTime())
                .duration(entity.getDuration())
                .description(entity.getDescription())
                .build();
    }

    /**
     * Converts a NoClassDateDto object to a NoClassDateEntity. Assumes callers have set all fields as needed.
     * @param dto
     * @return
     */
    public static NoClassDateEntity toEntity(NoClassDateDto dto) {
        return NoClassDateEntity.builder()
                 .dateTime(LocalDateTime.of(dto.getDate(), dto.getTime()))
                 .duration(dto.getDuration())
                 .description(dto.getDescription())
                 .build();
    }
}
