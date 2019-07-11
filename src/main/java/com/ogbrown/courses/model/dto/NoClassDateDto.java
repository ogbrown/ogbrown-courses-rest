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

import com.ogbrown.courses.model.NoClassDate;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NoClassDateDto implements NoClassDate, Comparable<NoClassDateDto>, Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    @NotNull(message = "Date is compulsory")
    @NotBlank(message = "Date is compulsory")
    @Pattern(regexp = "\\d{4}-\\d{1,2}-\\d{1,2}", message = "Date must be of format yyyy-mm-dd")
    private LocalDate date;
    private LocalTime time;
    private Duration duration;
    @NonNull
    @NotNull(message = "Description is compulsory")
    @NotBlank(message = "Description is compulsory")
    @Size(min=3, max=255, message = "A description must be provided")
    private String description;

    public NoClassDateDto(NoClassDate model) {
        this.date = model.getDate();
        this.time = model.getTime();
        this.duration = model.getDuration();
        this.description = model.getDescription();
    }

    @Override
    public int compareTo(NoClassDateDto o) {
        return this.getDate().compareTo(o.getDate());
    }
}
