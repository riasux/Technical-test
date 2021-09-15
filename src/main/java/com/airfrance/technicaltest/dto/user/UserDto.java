package com.airfrance.technicaltest.dto.user;

import com.airfrance.technicaltest.constraints.BirthDate;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
    @NotNull(message = "{date.birth.required}")
    @BirthDate
    @Past(message = "{date.user.past}")
    private LocalDate birthDate;
    @NotBlank(message = "{country.not.blank}")
    @Size(max = 100)
    private String country;
    private String phoneNumber;
    private Gender gender;
}
