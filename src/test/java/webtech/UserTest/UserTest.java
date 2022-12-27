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
        User user = new User(111L, "John", 80.0, 180.0, 35, 75, "MALE", null);

        // when
        int result = user.getBmr();

        // then
        assertEquals(1330, result);
    }

    @Test
    @DisplayName("should calculate bmr when goal weight is more than actual weight")
    void should_calculate_bmr_when_goalW_more(){
        // given
        User user = new User(222L, "Maria", 60.0, 170.0, 30, 65, "FEMALE", null);

        // when
        int result = user.getBmr();

        // then
        assertEquals(2039, result);
    }

    @Test
    @DisplayName("should calculate category when bmi is on the border of two categories")
    void should_calculate_category_when_bmi_on_border(){
        // given
        User user = new User(111L, "John", 80.0, 180.0, 35, 75, "MALE", null);

        // when
        String result = user.getCategory();

        // then
        assertEquals("Normal", result);
    }

    @Test
    @DisplayName("should calculate category when bmi is in the borders of category")
    void should_calculate_category_when_bmi_in_borders(){
        // given
        User user = new User(222L, "Maria",  53.0, 170.0, 30, 65, "FEMALE", null);

        // when
        String result = user.getCategory();

        // then
        assertEquals("Mild Thinness", result);
    }

    @Test
    @DisplayName("should calculate bmi when the result should be rounded down")
    void should_calc_bmi_when_rounded_down(){
        // given
        User user = new User(222L, "Maria",  53.0, 170.0, 30, 65, "FEMALE", null);

        // when
        double result = user.getBmi();

        // then
        assertEquals(18.0, result);
    }

    @Test
    @DisplayName("should calculate bmi when the result should be rounded up")
    void should_calc_bmi_when_rounded_up(){
        // given
        User user = new User(111L, "John", 80.0, 180.0, 35, 75, "MALE", null);

        // when
        double result = user.getBmi();

        // then
        assertEquals(25.0, result);
    }
}
