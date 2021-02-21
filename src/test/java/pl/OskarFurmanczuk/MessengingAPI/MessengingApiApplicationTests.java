package pl.OskarFurmanczuk.MessengingAPI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.OskarFurmanczuk.MessengingAPI.model.EmailByMagicNumber;
import pl.OskarFurmanczuk.MessengingAPI.model.MagicNumber;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessengingApiApplicationTests {

    @Autowired
    private MockMvc mvc;
    
    @Autowired
    ObjectMapper objectMapper;

    final private String emailValue = "test@test.com";
    final private int magicNumber = 123;
    
    @Test
    public void shouldCreateGivenEmail() throws Exception{
    	
    	EmailByMagicNumber email = new EmailByMagicNumber();
    	
    	email.setEmail(emailValue);
    	email.setTitle("This is a test title");
    	email.setContent("This is s test content.");
    	email.setNumber(magicNumber);
    	
    	mvc
    		.perform(post("/api/message/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(email)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.email").value(emailValue));
    }
    
    @Test
    public void shouldGetEmailsWithGivenEmailValue() throws Exception{

    	mvc
    		.perform(get("/api/messages/" + emailValue))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));  
    }
    
    @Test
    public void shouldSendEmailsWithGivenMagicNumber() throws Exception{

    	MagicNumber magNumber = new MagicNumber();
    	magNumber.setNumber(magicNumber);
    	
    	mvc
    		.perform(post("/api/send/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(magNumber)))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

}