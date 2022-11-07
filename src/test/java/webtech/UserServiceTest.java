package webtech;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import webtech.User.*;

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
    @DisplayName("should transform PersonEntity to Person")
    void should_transform_person_entity_to_person() {
        // given
        var userEntity = Mockito.mock(UserEntity.class);
        doReturn(111L).when(userEntity).getId();
        doReturn("John").when(userEntity).getUsername();
        doReturn("pass123").when(userEntity).getPasswort();
        doReturn(80).when(userEntity).getWeight();
        doReturn(180).when(userEntity).getHeight();
        doReturn(35).when(userEntity).getAge();
        doReturn(25).when(userEntity).getBmi();
        doReturn("Normal").when(userEntity).getCategory();
        doReturn(75).when(userEntity).getGoalW();
        doReturn(2330).when(userEntity).getBmr();
        doReturn(Gender.MALE).when(userEntity).getGender();

        // when
        var result = underTest.transformEntity(userEntity);

        // then
        assertThat(result.getId()).isEqualTo(111);
        assertThat(result.getUsername()).isEqualTo("John");
        assertThat(result.getPasswort()).isEqualTo("pass123");
        assertThat(result.getWeight()).isEqualTo(80);
        assertThat(result.getHeight()).isEqualTo(180);
        assertThat(result.getAge()).isEqualTo(35);
        assertThat(result.getBmi()).isEqualTo(25);
        assertThat(result.getCategory()).isEqualTo("Normal");
        assertThat(result.getGoalW()).isEqualTo(75);
        assertThat(result.getBmr()).isEqualTo(2330);
        assertThat(result.getGender()).isEqualTo("MALE");
    }
}