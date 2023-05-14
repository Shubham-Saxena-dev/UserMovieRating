package medium;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class SimpleHeaderFilterTest extends AbstractMediumTests{

    @Autowired
    private MockMvc mockMvc;
    private static final String PATH = "/api/tasks/**";

    @Test
    public void givenFilter_whenInvalidHeader_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(get(PATH)
                        .header("header", "headervalue"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Not authorized"));
    }

    @Test
    public void givenFilter_whenValidHeader_thenReturnSuccess() throws Exception {
        mockMvc.perform(get(PATH)
                        .header(HEADER_NAME, HEADER_VALUE)).andReturn();
    }

    @Test
    public void givenFilter_whenOptionMethod_theReturnSuccess() throws Exception {
        mockMvc.perform(options(PATH))
                .andExpect(status().isOk());
    }
}
