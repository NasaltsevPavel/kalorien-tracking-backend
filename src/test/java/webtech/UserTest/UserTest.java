package webtech.UserTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import webtech.User.User;
import webtech.User.UserRepository;
import webtech.User.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService underTest;

    @Test
    @DisplayName("should calculate bmr when goal weight is less than actual weight")
    void should_calculate_bmr_when_goalW_less(){
        // given
        User user = new User(111L, "John", "pass123", 80.0, 180.0, 35, 75, "MALE");

        // when
        int result = user.getBmr();

        // then
        assertEquals(1330, result);
    }

    @Test
    @DisplayName("should calculate bmr when goal weight is more than actual weight")
    void should_calculate_bmr_when_goalW_more(){
        // given
        User user = new User(222L, "Maria", "456pass", 60.0, 170.0, 30, 65, "FEMALE");

        // when
        int result = user.getBmr();

        // then
        assertEquals(2039, result);
    }
}
