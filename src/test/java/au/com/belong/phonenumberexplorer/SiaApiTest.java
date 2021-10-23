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
public class SiaApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    ObjectMapper mapper;

/*
    @MockBean
    GremlinHttpHandlerService mockClient;

    @MockBean
    GetVerticesGivenIdentifiers existenceQuery;
*/

    @Before
    public void setUp() {
        mapper = new ObjectMapper();
    }

   /* @Test
    public void test_sia_query_with_no_input() throws Exception {
        //when(this.mockClient.request(Mockito.anyString())).thenReturn(CompletableFuture.completedFuture(""));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/sia")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenSiaQueryWithNoValidInput_shouldReturnBadRequest() throws Exception {
        when(this.mockClient.request(Mockito.anyString())).thenReturn(CompletableFuture.completedFuture(""));

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/sia")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }*/

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
//    @Test
//    public void test_existence_of_vertices_all_exist() throws Exception {
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("vertexLookupResponse.json").getFile());
//        when(mockClient.request(Mockito.anyString())).thenReturn(CompletableFuture.completedFuture(FileUtils.readFileToString(file)));
//
//        final Map<String, List<String>> payload = new HashMap<String, List<String>>(){{
//                put("assetNames", Arrays.asList("OTCRM-5TWO-0001.00-1-012.5", "OTCRM-5TWO-0001.00-1-012"));
//        }};
//
//
//        this.mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/vertices")
//                        .content(mapper.writeValueAsString(payload))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk());
//    }

//    @Test
//    public void test_existence_of_vertices_missing() throws Exception {
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("vertexLookupResponse.json").getFile());
//        when(mockClient.request(Mockito.anyString())).thenReturn(CompletableFuture.completedFuture(FileUtils.readFileToString(file)));
//
//        final Map<String, List<String>> payload = new HashMap<String, List<String>>(){{
//            put("assetNames", Arrays.asList("OTCRM-5TWO-0001.00-1-012.5", "OTCRM-5TWO-0001.00-1-013"));
//        }};
//
//
//        this.mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/vertices")
//                        .content(mapper.writeValueAsString(payload))
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isNotFound());
//    }
}
