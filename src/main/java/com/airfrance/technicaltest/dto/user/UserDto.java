package com.airfrance.technicaltest.dto.user;

import com.airfrance.technicaltest.constraints.BirthDate;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
public class UserDto {

    @NotBlank(message = "{username.not.blank}")
    @Size(max = 100)
    private String username;

    @BirthDate
    @NotNull(message = "{date.birth.required}")
    @DateTimeFormat
    private LocalDate birthDate;

    @NotBlank(message = "{country.not.blank}")
    @Size(max = 100)
    private String country;

    private String phoneNumber;

    private Gender gender;
}
