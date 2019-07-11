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

public interface Instructor {
    Long getId();

    void setId(Long id);

    String getFirstName();

    void setFirstName(String firstName);

    String getMiddleInitial();

    void setMiddleInitial(String middleInitial);

    String getLastName();

    void setLastName(String lastName);

    String getSuffix();

    void setSuffix(String suffix);

    String getNickname();

    void setNickname(String nickname);

    String getEmail();

    void setEmail(String email);

    String getEmailInstructions();

    void setEmailInstructions(String emailInstructions);

    String getPhone();

    void setPhone(String phone);

    String getPhoneInstructions();

    void setPhoneInstructions(String phoneInstructions);

    String getWebsite();

    void setWebsite(String website);
}
