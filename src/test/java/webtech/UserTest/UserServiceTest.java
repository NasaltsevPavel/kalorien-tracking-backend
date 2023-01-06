package webtech.UserTest;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import webtech.User.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
    @DisplayName("should return false if user to delete does not exist")
    void should_return_false_if_user_to_delete_does_not_exist() {
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
        UserCreateOrUpdateRequest request = new UserCreateOrUpdateRequest("John", 80,
                180, 35, 25.0, "Normal", 75, 1330, "MALE");
        UserEntity expected = new UserEntity( "John", 80.0, 180.0, 35, 75, "normal", 75, 25, Gender.MALE);
        doReturn(expected).when(repository).save(any());


        // when
        User actual = underTest.create(request);

        // then
        assertEquals(expected.getAge(), actual.getAge());
    }

    @Test
    @DisplayName("should create user if all variables are correct")
    void should_create_user2() {
        // given
        UserCreateOrUpdateRequest request = new UserCreateOrUpdateRequest("John", 80,
                180, 35, 25.0, "Normal", 75, 1330, "MALE");
        UserEntity expected = new UserEntity( "John", 80.0, 180.0, 35, 75, "normal", 75, 25, Gender.MALE);
        doReturn(expected).when(repository).save(any());


        // when
        User actual = underTest.create(request);


        // then
        ArgumentCaptor<UserEntity> captor = ArgumentCaptor.forClass(UserEntity.class);
        verify(repository).save(captor.capture());
        assertEquals(request.getAge(), captor.getValue().getAge());
    }
}
