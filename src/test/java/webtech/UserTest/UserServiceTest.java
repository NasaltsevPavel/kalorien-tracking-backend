package webtech.UserTest;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import webtech.User.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class UserServiceTest implements WithAssertions {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService underTest;

    @Test
    @DisplayName("should return true if delete was successful")
    void should_return_true_if_delete_was_successful() {
        // given
        Long givenId = 111L;
        doReturn(true).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verify(repository).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("should return false if person to delete does not exist")
    void should_return_false_if_person_to_delete_does_not_exist() {
        // given
        Long givenId = 111L;
        doReturn(false).when(repository).existsById(givenId);

        // when
        boolean result = underTest.deleteById(givenId);

        // then
        verifyNoMoreInteractions(repository);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("should transform UserEntity to User")
    void should_transform_user_entity_to_user() {
        // given
        var userEntity = Mockito.mock(UserEntity.class);
        doReturn(111L).when(userEntity).getId();
        doReturn("John").when(userEntity).getUsername();
        doReturn("pass123").when(userEntity).getPasswort();
        doReturn(80.0).when(userEntity).getWeight();
        doReturn(180.0).when(userEntity).getHeight();
        doReturn(35).when(userEntity).getAge();
        doReturn(75).when(userEntity).getGoalW();
        doReturn(Gender.MALE).when(userEntity).getGender();

        // when
        var result = underTest.transformEntity(userEntity);

        // then
        assertThat(result.getId()).isEqualTo(111);
        assertThat(result.getUsername()).isEqualTo("John");
        assertThat(result.getPasswort()).isEqualTo("pass123");
        assertThat(result.getWeight()).isEqualTo(80.0);
        assertThat(result.getHeight()).isEqualTo(180.0);
        assertThat(result.getAge()).isEqualTo(35);
        assertThat(result.getBmi()).isEqualTo(25.0);
        assertThat(result.getCategory()).isEqualTo("Normal");
        assertThat(result.getGoalW()).isEqualTo(75);
        assertThat(result.getBmr()).isEqualTo(1330);
        assertThat(result.getGender()).isEqualTo("MALE");
    }

    @Test
    @DisplayName("should create user if all variables are correct")
    void should_create_user() {
        // given
        UserCreateOrUpdateRequest request = new UserCreateOrUpdateRequest("John", "pass123", 80,
                180, 35, 25.0, "Normal", 75, 1330, "MALE");
        User expected = new User(111L, "John", "pass123", 80.0, 180.0, 35, 75, "MALE");

        // when
        User result = underTest.create(request);

        // then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("should create user with unknown gender if it is null")
    void should_create_user_with_unknown_gender() {
        // given
        UserCreateOrUpdateRequest request = new UserCreateOrUpdateRequest("John", "pass123", 80,
                180, 35, 25.0, "Normal", 75, 1330, null);
        User expected = new User(111L, "John", "pass123", 80.0, 180.0, 35, 75, null);

        // when
        User result = underTest.create(request);

        // then
        assertEquals(expected, result);
    }
}