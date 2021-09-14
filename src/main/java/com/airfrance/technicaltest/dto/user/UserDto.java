package com.airfrance.technicaltest.dto.user;

import com.airfrance.technicaltest.constraints.BirthDate;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    @NotBlank(message = "{username.not.blank}")
    @Size(max = 100)
    private String username;
    @NotNull(message = "The date of birth is required.")
    @BirthDate(message = "The birth date must be greater or equal than 18")
    @Past(message = "The date of birth must be in past.")
    private LocalDate birthDate;
    @NotBlank(message = "Country can't be blank")
    @Size(max = 100)
    private String country;
    private String phoneNumber;
    private Gender gender;
}
