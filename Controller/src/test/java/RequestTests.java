import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import ru.Onshin.Cats.CatDto;
import ru.Onshin.Cats.Colors;
import ru.Onshin.Main;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class RequestTests {
    public static final
    MediaType APPLICATION_JSON_UTF8 = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);

    @Autowired
    private MockMvc mockMvc;

    private final ObjectWriter ow;

    public RequestTests() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        this.ow = mapper.writer().withDefaultPrettyPrinter();
    }

    @Test
    public void testLogin() throws Exception {
        this.mockMvc.perform(post("/login")
                        .with(user("user").password("password")))
                .andExpect(redirectedUrl("/login?error"));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void testGettingAllOwners() throws Exception {
        this.mockMvc
                .perform(get("/owners/"))
                .andExpect(status().isOk());
    }

    @Test
    public void createCats() throws Exception {
        CatDto cat1 = new CatDto();
        cat1.setName("jsl");
        cat1.setBirthday(new Date());
        cat1.setColor(Colors.BROWN);
        cat1.setBreed("someBreed");
        cat1.setId(1);
        cat1.setOwnerId(1);
        cat1.setCatFriendsId(new ArrayList<>());

        this.mockMvc.perform(
                        post("/cats/create")
                                .contentType(APPLICATION_JSON_UTF8)
                                .content(ow.writeValueAsString(cat1)))
                .andDo(print())
                .andExpect(redirectedUrl("http://localhost/login"));
    }
}