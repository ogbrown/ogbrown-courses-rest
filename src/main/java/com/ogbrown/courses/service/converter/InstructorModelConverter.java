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

import com.ogbrown.courses.model.dto.InstructorDto;
import com.ogbrown.courses.model.entity.InstructorEntity;

public class InstructorModelConverter {
    public static InstructorDto toDto(InstructorEntity entity) {
        return InstructorDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .emailInstructions(entity.getEmailInstructions())
                .firstName(entity.getFirstName())
                .nickname(entity.getNickname())
                .middleInitial(entity.getMiddleInitial())
                .lastName(entity.getLastName())
                .suffix(entity.getSuffix())
                .phone(entity.getPhone())
                .phoneInstructions(entity.getPhoneInstructions())
                .website(entity.getWebsite())
                .build();
    }
}
