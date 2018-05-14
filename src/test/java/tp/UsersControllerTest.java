package tp;

import static org.hamcrest.CoreMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)));
    }
    
    @Test
    public void getUser() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.get("/users/1").accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk())
    		.andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));
    }
    
    @Test
    public void creditUser() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.post("/users/1/credit")
    			.param("montant", "10")
    			.accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk())
    		.andExpect(content().string(equalTo("Ok")));
    	
    }
    
    @Test
    public void debitUser() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.post("/users/1/debit")
    			.param("montant", "10")
    			.accept(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk())
    		.andExpect(content().string(equalTo("Ok")));
    	
    }
}
