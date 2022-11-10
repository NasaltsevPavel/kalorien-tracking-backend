package webtech.UserTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import webtech.User.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    @DisplayName("should return found users from user service")
    void should_return_found_users_from_user_service() throws Exception {
        // given
        var users = List.of(
                new User(111L, "John", "pass123", 80.0, 180.0, 35, 75, "MALE"),
                new User(222L, "Maria", "456pass", 60.0, 170.0, 30, 65, "FEMALE")
        );
        doReturn(users).when(userService).findAll();

        // when
        mockMvc.perform(get("/v1/users"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(111L))
                .andExpect(jsonPath("$[0].username").value("John"))
                .andExpect(jsonPath("$[0].passwort").value("pass123"))
                .andExpect(jsonPath("$[0].weight").value(80.0))
                .andExpect(jsonPath("$[0].height").value(180.0))
                .andExpect(jsonPath("$[0].age").value(35))
                .andExpect(jsonPath("$[0].goalW").value(75))
                .andExpect(jsonPath("$[0].gender").value("MALE"))
                .andExpect(jsonPath("$[1].id").value(222L))
                .andExpect(jsonPath("$[1].username").value("Maria"))
                .andExpect(jsonPath("$[1].passwort").value("456pass"))
                .andExpect(jsonPath("$[1].weight").value(60.0))
                .andExpect(jsonPath("$[1].height").value(170.0))
                .andExpect(jsonPath("$[1].age").value(30))
                .andExpect(jsonPath("$[1].goalW").value(65))
                .andExpect(jsonPath("$[1].gender").value("FEMALE"));
    }

    @Test
    @DisplayName("should return 404 if user is not found")
    void should_return_404_if_user_is_not_found() throws Exception {
        // given
        doReturn(null).when(userService).findById(anyLong());

        // when
        mockMvc.perform(get("/v1/users/123"))
                // then
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should return 201 http status and Location header when creating a user")
    @Disabled
    void should_return_201_http_status_and_location_header_when_creating_a_user() throws Exception {

        // given
        String userToCreateAsJson = "{\"username\": \"John\", \"passwort\":\"pass123\", \"weight\":\"80.0\", \"height\":\"180.0\", \"age\":\"35\", \"goalW\":\"75\", \"gender\":\"MALE\"}";
        var user = new User(123, null, null, 0.0, 0.0, 0, 0, null);
        doReturn(user).when(userService).create(any());

        // when
        mockMvc.perform(
                        post("/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/v1/users/" + user.getId()))));
//            .andExpect(header().string("Location", Matchers.containsString(Long.toString(user.getId()))));

    }

    @Test
    @DisplayName("should validate create user request")
    void should_validate_create_user_request() throws Exception {
        // given
        String userToCreateAsJson = "{\"username\": \"John\", \"passwort\":\"pass123\", \"weight\":\"80.0\", \"height\":\"180.0\"," +
                " \"age\":\"35\", \"goalW\":\"75\", \"gender\":\"MALE\"}";

        // when
        mockMvc.perform(
                        post("/v1/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(userToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }



}


