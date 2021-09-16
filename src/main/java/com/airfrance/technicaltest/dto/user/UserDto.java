package com.airfrance.technicaltest.dto.user;


import com.airfrance.technicaltest.constraints.groupsequence.First;
import com.airfrance.technicaltest.constraints.groupsequence.Second;
import com.airfrance.technicaltest.constraints.validator.birthdate.BirthDate;
import com.airfrance.technicaltest.constraints.validator.country.Country;
import com.airfrance.technicaltest.constraints.validator.phone.Phone;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * User definition
 *
 * @author r-fonkoue
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@GroupSequence({First.class, Second.class, UserDto.class})
public class UserDto {

    @NotBlank(message = "{username.not.blank}", groups = {First.class})
    @Size(max = 100, message = "{size.limit100.username}")
    private String username;

    @BirthDate(groups = {Second.class})
    @NotNull(message = "{date.birth.required}", groups = {First.class})
    @DateTimeFormat
    private LocalDate birthDate;

    @Country(groups = {Second.class})
    @NotBlank(message = "{country.not.blank}", groups = {First.class})
    private String country;

    @Phone
    private String phoneNumber;

    private Gender gender;
}
