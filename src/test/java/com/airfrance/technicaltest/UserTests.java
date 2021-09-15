package com.airfrance.technicaltest;

import com.airfrance.technicaltest.dao.UserRepository;
import com.airfrance.technicaltest.dto.user.Gender;
import com.airfrance.technicaltest.dto.user.UserDto;
import com.airfrance.technicaltest.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * user operation test class
 *
 * @author r-fonkoue
 */
@ExtendWith(SpringExtension.class)
class UserTests extends TechnicalTestApplicationTests {


    @BeforeEach
    @Transactional
    public void setUpUsers(@Autowired UserRepository userRepository) {

        userRepository.deleteAll();

        userRepository.saveAll(buildUserEntities());

        System.out.println(userRepository.count());

    }

    @Test
    public void findUserById(@Autowired UserRepository userRepository) {
        //GIVEN
        UserEntity userToFind = new UserEntity();
        userToFind.setUsername("UserTest");
        userToFind.setBirthDate(LocalDate.of(1982, 2, 16));
        userToFind.setCountry("france");
        userToFind.setPhoneNumber("+33598247169");
        userToFind.setGender(Gender.FEMALE);

        userRepository.save(userToFind);
        long id = userToFind.getId();

        //WHEN
        String uri = UriComponentsBuilder
                .fromUriString("/user/" + id)
                .build(false)
                .toUriString();

        ResponseEntity<UserDto> getUserResponse = restTemplate.exchange(uri, HttpMethod.GET, emptyPayloadWithoutAuth(), UserDto.class);

        //THEN
        assertEquals(HttpStatus.OK, getUserResponse.getStatusCode());
        assertEquals(userToFind.getUsername(), getUserResponse.getBody().getUsername());
    }

    @Test
    public void findUserById_NOT_FOUND() {
        //GIVEN
        long id = 5;

        //WHEN

        ResponseEntity<UserDto> getUserResponse = restTemplate.exchange(String.format("/user/%d", id), HttpMethod.GET, emptyPayloadWithoutAuth(), UserDto.class);

        //THEN
        assertEquals(HttpStatus.NOT_FOUND, getUserResponse.getStatusCode());
    }

    @Test
    @Modifying
    public void saveUser(@Autowired UserRepository userRepository) {
        //GIVEN
        UserDto userDto = new UserDto();
        userDto.setUsername("benjamin");
        userDto.setBirthDate(LocalDate.of(2001, 10, 14));
        userDto.setCountry("france");
        userDto.setPhoneNumber("+33782019857");
        userDto.setGender(Gender.MALE);

        long countUserBefore = userRepository.count();
        //WHEN

        ResponseEntity<UserDto> saveUserResponse = restTemplate.exchange(String.format("/user/"), HttpMethod.POST, payloadWithoutAuth(userDto), UserDto.class);

        //THEN
        assertEquals(HttpStatus.CREATED, saveUserResponse.getStatusCode());
        assertEquals(countUserBefore + 1, userRepository.count());
    }

    @Test
    public void saveUser_Without_Username() {
        //GIVEN
        UserDto userDto = new UserDto();
        userDto.setBirthDate(LocalDate.of(2001, 10, 14));
        userDto.setCountry("france");
        userDto.setPhoneNumber("+33782019857");
        userDto.setGender(Gender.MALE);
        //WHEN
        String uri = UriComponentsBuilder
                .fromUriString("/user/")
                .build(false)
                .toUriString();

        ResponseEntity<UserDto> saveUserResponse = restTemplate.exchange(uri, HttpMethod.POST, payloadWithoutAuth(userDto), UserDto.class);

        //THEN
        assertEquals(HttpStatus.BAD_REQUEST, saveUserResponse.getStatusCode());
    }

    public List<UserEntity> buildUserEntities() {
        UserEntity user1 = new UserEntity();
        user1.setUsername("Vincent");
        user1.setBirthDate(LocalDate.of(1982, 2, 16));
        user1.setCountry("france");
        user1.setPhoneNumber("+33598247169");
        user1.setGender(Gender.MALE);

        UserEntity user2 = new UserEntity();
        user2.setId(2);
        user2.setUsername("Christophe");
        user2.setBirthDate(LocalDate.of(1972, 10, 3));
        user2.setCountry("france");
        user2.setPhoneNumber("+33688247169");
        user2.setGender(Gender.MALE);

        UserEntity user3 = new UserEntity();
        user3.setUsername("Roux");
        user3.setBirthDate(LocalDate.of(1992, 11, 11));
        user3.setCountry("france");
        user3.setPhoneNumber("+33728257110");
        user3.setGender(Gender.MALE);

        UserEntity user4 = new UserEntity();
        user4.setUsername("Frederic");
        user4.setBirthDate(LocalDate.of(1984, 6, 14));
        user4.setCountry("france");
        user4.setPhoneNumber("+33788247135");
        user4.setGender(Gender.MALE);

        return List.of(user1, user2, user3, user4);
    }
}
