package medium;

import com.check24.model.Film;
import com.check24.model.Rating;
import com.check24.model.User;
import com.check24.services.FilmService;
import com.check24.services.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class FilmControllerTest extends AbstractMediumTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private FilmService filmService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUser_whenRecommendations_thenSuccedd() throws Exception {
        // given
        User user = new User.Builder().setName("user1").setGenre(List.of("genre1")).setDirectors(List.of("director1")).build();
        Rating r1 = new Rating.Builder().setRating(1).setRatingCount(1).setFilmName("film1").build();
        Rating r2 = new Rating.Builder().setRating(2).setRatingCount(2).setFilmName("film2").build();

        List<Rating> ratings = Arrays.asList(r1, r2);

        List<Film> films = Arrays.asList(
                new Film.Builder().setFilmName("film1").setGenre("genre1").setDirector("director1").build(),
                new Film.Builder().setFilmName("film2").setGenre("genre2").setDirector("director2").build(),
                new Film.Builder().setFilmName("film3").setGenre("genre2").setDirector("director2").build()
        );

        //when
        when(userService.findUser("user1")).thenReturn(user);

        when(filmService.getAllFilms()).thenReturn(films);

        MvcResult result = mockMvc.perform(get("/api/user/recommendations/user1").header(HEADER_NAME, HEADER_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        List<Film> recommendations = objectMapper.readValue(result.getResponse().getContentAsString(),
                new TypeReference<List<Film>>() {
                });

        //then
        assertEquals(1, recommendations.size());
        assertEquals("film1", recommendations.get(0).getFilmName());
        assertEquals("genre1", recommendations.get(0).getGenre());
        assertEquals("director1", recommendations.get(0).getDirector());

        verify(userService).findUser("user1");

        verify(filmService).getAllFilms();
    }
}