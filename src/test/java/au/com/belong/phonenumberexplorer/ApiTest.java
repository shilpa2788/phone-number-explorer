package au.com.belong.phonenumberexplorer;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PhoneNumberExplorerApplication.class)
@AutoConfigureMockMvc
public class ApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    ObjectMapper mapper;


    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void test_existence_of_vertices_no_payload() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/getAllPhoneNumbers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void test_existence_of_customer_phone_number_no_payload() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/getPhoneNumbers")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void test_existence_of_activate_phone_number_no_payload() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/activatePhoneNumber")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is(404));
    }
}