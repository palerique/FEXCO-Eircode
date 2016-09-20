package br.com.sitedoph.fexco.postcode.web.rest;

import br.com.sitedoph.fexco.postcode.FexcoPostcodeApp;
import br.com.sitedoph.fexco.postcode.domain.Address;
import br.com.sitedoph.fexco.postcode.repository.AddressRepository;
import br.com.sitedoph.fexco.postcode.service.PostcodeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by ph on 9/18/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FexcoPostcodeApp.class)
public class PostcodeResourceTest {

    private static final String DEFAULT_POSTCODE = "AAAAA";

    private static RedisServer redisServer;

    @Inject
    private AddressRepository addressRepository;

    @Inject
    private PostcodeService postcodeService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
    private MockMvc                               restAddressMockMvc;
    private Address                               address;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createEntity() {
        Address address = new Address()
            .postcode(DEFAULT_POSTCODE);
        return address;
    }

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PostcodeResource postcodeResource = new PostcodeResource();
        ReflectionTestUtils.setField(postcodeResource, "postcodeService", postcodeService);
        this.restAddressMockMvc = MockMvcBuilders.standaloneSetup(postcodeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        addressRepository.deleteAll();
        address = createEntity();
    }

    private void assertresultAction(ResultActions resultActions) throws Exception {
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*]").value(hasItem(DEFAULT_POSTCODE.toString())));
    }

    @Test
    public void getPostcodesByPostcodePart() throws Exception {
        addressRepository.save(address);
        ResultActions resultActions = restAddressMockMvc.perform(get("/api/postcodes/A"));
        assertresultAction(resultActions);
    }

}
