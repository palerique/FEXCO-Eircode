package br.com.sitedoph.fexco.postcode.web.rest;

import br.com.sitedoph.fexco.postcode.FexcoPostcodeApp;
import br.com.sitedoph.fexco.postcode.domain.Address;
import br.com.sitedoph.fexco.postcode.repository.AddressRepository;
import br.com.sitedoph.fexco.postcode.service.AddressService;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AddressResource REST controller.
 *
 * @see AddressResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FexcoPostcodeApp.class)
public class AddressResourceIntTest {


    private static final String DEFAULT_ADDRESSLINE_1           = "AAAAA";
    private static final String UPDATED_ADDRESSLINE_1           = "BBBBB";
    private static final String DEFAULT_ADDRESSLINE_2           = "AAAAA";
    private static final String UPDATED_ADDRESSLINE_2           = "BBBBB";
    private static final String DEFAULT_ADDRESSLINE_3           = "AAAAA";
    private static final String UPDATED_ADDRESSLINE_3           = "BBBBB";
    private static final String DEFAULT_SUMMARYLINE             = "AAAAA";
    private static final String UPDATED_SUMMARYLINE             = "BBBBB";
    private static final String DEFAULT_ORGANISATION            = "AAAAA";
    private static final String UPDATED_ORGANISATION            = "BBBBB";
    private static final String DEFAULT_BUILDINGNAME            = "AAAAA";
    private static final String UPDATED_BUILDINGNAME            = "BBBBB";
    private static final String DEFAULT_PREMISE                 = "AAAAA";
    private static final String UPDATED_PREMISE                 = "BBBBB";
    private static final String DEFAULT_STREET                  = "AAAAA";
    private static final String UPDATED_STREET                  = "BBBBB";
    private static final String DEFAULT_DEPENDENTLOCALITY       = "AAAAA";
    private static final String UPDATED_DEPENDENTLOCALITY       = "BBBBB";
    private static final String DEFAULT_POSTTOWN                = "AAAAA";
    private static final String UPDATED_POSTTOWN                = "BBBBB";
    private static final String DEFAULT_COUNTY                  = "AAAAA";
    private static final String UPDATED_COUNTY                  = "BBBBB";
    private static final String DEFAULT_POSTCODE                = "AAAAA";
    private static final String UPDATED_POSTCODE                = "BBBBB";
    private static final String DEFAULT_NUMBER                  = "AAAAA";
    private static final String UPDATED_NUMBER                  = "BBBBB";
    private static final String DEFAULT_POBOX                   = "AAAAA";
    private static final String UPDATED_POBOX                   = "BBBBB";
    private static final String DEFAULT_DEPARTMENTNAME          = "AAAAA";
    private static final String UPDATED_DEPARTMENTNAME          = "BBBBB";
    private static final String DEFAULT_SUBBUILDINGNAME         = "AAAAA";
    private static final String UPDATED_SUBBUILDINGNAME         = "BBBBB";
    private static final String DEFAULT_DEPENDENTSTREET         = "AAAAA";
    private static final String UPDATED_DEPENDENTSTREET         = "BBBBB";
    private static final String DEFAULT_DOUBLEDEPENDENTLOCALITY = "AAAAA";
    private static final String UPDATED_DOUBLEDEPENDENTLOCALITY = "BBBBB";
    private static final String DEFAULT_RECODES                 = "AAAAA";
    private static final String UPDATED_RECODES                 = "BBBBB";

    private static final Boolean DEFAULT_MOREVALUES = false;
    private static final Boolean UPDATED_MOREVALUES = true;

    private static final Integer DEFAULT_NEXTPAGE = 1;
    private static final Integer UPDATED_NEXTPAGE = 2;

    private static final Integer DEFAULT_TOTALRESULTS = 1;
    private static final Integer UPDATED_TOTALRESULTS = 2;
    private static final String  DEFAULT_LATITUDE     = "AAAAA";
    private static final String  UPDATED_LATITUDE     = "BBBBB";
    private static final String  DEFAULT_LONGITUDE    = "AAAAA";
    private static final String  UPDATED_LONGITUDE    = "BBBBB";
    private static RedisServer                           redisServer;
    @Inject
    private        AddressRepository                     addressRepository;
    @Inject
    private        AddressService                        addressService;
    @Inject
    private        MappingJackson2HttpMessageConverter   jacksonMessageConverter;
    @Inject
    private        PageableHandlerMethodArgumentResolver pageableArgumentResolver;
    private        MockMvc                               restAddressMockMvc;
    private        Address                               address;

    @BeforeClass
    public static void beforeClass() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterClass
    public static void afterClass() throws IOException {
        redisServer.stop();
    }

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Address createEntity() {
        Address address = new Address()
            .addressline1(DEFAULT_ADDRESSLINE_1)
            .addressline2(DEFAULT_ADDRESSLINE_2)
            .addressline3(DEFAULT_ADDRESSLINE_3)
            .summaryline(DEFAULT_SUMMARYLINE)
            .organisation(DEFAULT_ORGANISATION)
            .buildingname(DEFAULT_BUILDINGNAME)
            .premise(DEFAULT_PREMISE)
            .street(DEFAULT_STREET)
            .dependentlocality(DEFAULT_DEPENDENTLOCALITY)
            .posttown(DEFAULT_POSTTOWN)
            .county(DEFAULT_COUNTY)
            .postcode(DEFAULT_POSTCODE)
            .number(DEFAULT_NUMBER)
            .pobox(DEFAULT_POBOX)
            .departmentname(DEFAULT_DEPARTMENTNAME)
            .subbuildingname(DEFAULT_SUBBUILDINGNAME)
            .dependentstreet(DEFAULT_DEPENDENTSTREET)
            .doubledependentlocality(DEFAULT_DOUBLEDEPENDENTLOCALITY)
            .recodes(DEFAULT_RECODES)
            .morevalues(DEFAULT_MOREVALUES)
            .nextpage(DEFAULT_NEXTPAGE)
            .totalresults(DEFAULT_TOTALRESULTS)
            .latitude(DEFAULT_LATITUDE)
            .longitude(DEFAULT_LONGITUDE);
        return address;
    }

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AddressResource addressResource = new AddressResource();
        ReflectionTestUtils.setField(addressResource, "addressService", addressService);
        this.restAddressMockMvc = MockMvcBuilders.standaloneSetup(addressResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        addressRepository.deleteAll();
        address = createEntity();
    }

    @Test
    public void createAddress() throws Exception {
        int databaseSizeBeforeCreate = addressRepository.findAll().size();

        // Create the Address

        restAddressMockMvc.perform(post("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(address)))
            .andExpect(status().isCreated());

        // Validate the Address in the database
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).hasSize(databaseSizeBeforeCreate + 1);
        Address testAddress = addresses.get(addresses.size() - 1);
        assertThat(testAddress.getAddressline1()).isEqualTo(DEFAULT_ADDRESSLINE_1);
        assertThat(testAddress.getAddressline2()).isEqualTo(DEFAULT_ADDRESSLINE_2);
        assertThat(testAddress.getAddressline3()).isEqualTo(DEFAULT_ADDRESSLINE_3);
        assertThat(testAddress.getSummaryline()).isEqualTo(DEFAULT_SUMMARYLINE);
        assertThat(testAddress.getOrganisation()).isEqualTo(DEFAULT_ORGANISATION);
        assertThat(testAddress.getBuildingname()).isEqualTo(DEFAULT_BUILDINGNAME);
        assertThat(testAddress.getPremise()).isEqualTo(DEFAULT_PREMISE);
        assertThat(testAddress.getStreet()).isEqualTo(DEFAULT_STREET);
        assertThat(testAddress.getDependentlocality()).isEqualTo(DEFAULT_DEPENDENTLOCALITY);
        assertThat(testAddress.getPosttown()).isEqualTo(DEFAULT_POSTTOWN);
        assertThat(testAddress.getCounty()).isEqualTo(DEFAULT_COUNTY);
        assertThat(testAddress.getPostcode()).isEqualTo(DEFAULT_POSTCODE);
        assertThat(testAddress.getNumber()).isEqualTo(DEFAULT_NUMBER);
        assertThat(testAddress.getPobox()).isEqualTo(DEFAULT_POBOX);
        assertThat(testAddress.getDepartmentname()).isEqualTo(DEFAULT_DEPARTMENTNAME);
        assertThat(testAddress.getSubbuildingname()).isEqualTo(DEFAULT_SUBBUILDINGNAME);
        assertThat(testAddress.getDependentstreet()).isEqualTo(DEFAULT_DEPENDENTSTREET);
        assertThat(testAddress.getDoubledependentlocality()).isEqualTo(DEFAULT_DOUBLEDEPENDENTLOCALITY);
        assertThat(testAddress.getRecodes()).isEqualTo(DEFAULT_RECODES);
        assertThat(testAddress.isMorevalues()).isEqualTo(DEFAULT_MOREVALUES);
        assertThat(testAddress.getNextpage()).isEqualTo(DEFAULT_NEXTPAGE);
        assertThat(testAddress.getTotalresults()).isEqualTo(DEFAULT_TOTALRESULTS);
        assertThat(testAddress.getLatitude()).isEqualTo(DEFAULT_LATITUDE);
        assertThat(testAddress.getLongitude()).isEqualTo(DEFAULT_LONGITUDE);
    }

    @Test
    public void getAllAddresses() throws Exception {
        // Initialize the database
        addressRepository.save(address);

        // Get all the addresses
        ResultActions resultActions = restAddressMockMvc.perform(get("/api/addresses?sort=id,desc"));

        assertresultAction(resultActions);
    }

    @Test
    public void getAddressesByPostcode() throws Exception {
        addressRepository.save(address);
        ResultActions resultActions = restAddressMockMvc.perform(get("/api/addresses/postcode/A?sort=id,desc"));

        assertresultAction(resultActions);
    }

    private void assertresultAction(ResultActions resultActions) throws Exception {
        resultActions
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(address.getId())))
            .andExpect(jsonPath("$.[*].addressline1").value(hasItem(DEFAULT_ADDRESSLINE_1.toString())))
            .andExpect(jsonPath("$.[*].addressline2").value(hasItem(DEFAULT_ADDRESSLINE_2.toString())))
            .andExpect(jsonPath("$.[*].addressline3").value(hasItem(DEFAULT_ADDRESSLINE_3.toString())))
            .andExpect(jsonPath("$.[*].summaryline").value(hasItem(DEFAULT_SUMMARYLINE.toString())))
            .andExpect(jsonPath("$.[*].organisation").value(hasItem(DEFAULT_ORGANISATION.toString())))
            .andExpect(jsonPath("$.[*].buildingname").value(hasItem(DEFAULT_BUILDINGNAME.toString())))
            .andExpect(jsonPath("$.[*].premise").value(hasItem(DEFAULT_PREMISE.toString())))
            .andExpect(jsonPath("$.[*].street").value(hasItem(DEFAULT_STREET.toString())))
            .andExpect(jsonPath("$.[*].dependentlocality").value(hasItem(DEFAULT_DEPENDENTLOCALITY.toString())))
            .andExpect(jsonPath("$.[*].posttown").value(hasItem(DEFAULT_POSTTOWN.toString())))
            .andExpect(jsonPath("$.[*].county").value(hasItem(DEFAULT_COUNTY.toString())))
            .andExpect(jsonPath("$.[*].postcode").value(hasItem(DEFAULT_POSTCODE.toString())))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER.toString())))
            .andExpect(jsonPath("$.[*].pobox").value(hasItem(DEFAULT_POBOX.toString())))
            .andExpect(jsonPath("$.[*].departmentname").value(hasItem(DEFAULT_DEPARTMENTNAME.toString())))
            .andExpect(jsonPath("$.[*].subbuildingname").value(hasItem(DEFAULT_SUBBUILDINGNAME.toString())))
            .andExpect(jsonPath("$.[*].dependentstreet").value(hasItem(DEFAULT_DEPENDENTSTREET.toString())))
            .andExpect(jsonPath("$.[*].doubledependentlocality").value(hasItem(DEFAULT_DOUBLEDEPENDENTLOCALITY.toString())))
            .andExpect(jsonPath("$.[*].recodes").value(hasItem(DEFAULT_RECODES.toString())))
            .andExpect(jsonPath("$.[*].morevalues").value(hasItem(DEFAULT_MOREVALUES.booleanValue())))
            .andExpect(jsonPath("$.[*].nextpage").value(hasItem(DEFAULT_NEXTPAGE)))
            .andExpect(jsonPath("$.[*].totalresults").value(hasItem(DEFAULT_TOTALRESULTS)))
            .andExpect(jsonPath("$.[*].latitude").value(hasItem(DEFAULT_LATITUDE.toString())))
            .andExpect(jsonPath("$.[*].longitude").value(hasItem(DEFAULT_LONGITUDE.toString())));
    }

    @Test
    public void getAddress() throws Exception {
        // Initialize the database
        addressRepository.save(address);

        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", address.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(address.getId()))
            .andExpect(jsonPath("$.addressline1").value(DEFAULT_ADDRESSLINE_1.toString()))
            .andExpect(jsonPath("$.addressline2").value(DEFAULT_ADDRESSLINE_2.toString()))
            .andExpect(jsonPath("$.addressline3").value(DEFAULT_ADDRESSLINE_3.toString()))
            .andExpect(jsonPath("$.summaryline").value(DEFAULT_SUMMARYLINE.toString()))
            .andExpect(jsonPath("$.organisation").value(DEFAULT_ORGANISATION.toString()))
            .andExpect(jsonPath("$.buildingname").value(DEFAULT_BUILDINGNAME.toString()))
            .andExpect(jsonPath("$.premise").value(DEFAULT_PREMISE.toString()))
            .andExpect(jsonPath("$.street").value(DEFAULT_STREET.toString()))
            .andExpect(jsonPath("$.dependentlocality").value(DEFAULT_DEPENDENTLOCALITY.toString()))
            .andExpect(jsonPath("$.posttown").value(DEFAULT_POSTTOWN.toString()))
            .andExpect(jsonPath("$.county").value(DEFAULT_COUNTY.toString()))
            .andExpect(jsonPath("$.postcode").value(DEFAULT_POSTCODE.toString()))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER.toString()))
            .andExpect(jsonPath("$.pobox").value(DEFAULT_POBOX.toString()))
            .andExpect(jsonPath("$.departmentname").value(DEFAULT_DEPARTMENTNAME.toString()))
            .andExpect(jsonPath("$.subbuildingname").value(DEFAULT_SUBBUILDINGNAME.toString()))
            .andExpect(jsonPath("$.dependentstreet").value(DEFAULT_DEPENDENTSTREET.toString()))
            .andExpect(jsonPath("$.doubledependentlocality").value(DEFAULT_DOUBLEDEPENDENTLOCALITY.toString()))
            .andExpect(jsonPath("$.recodes").value(DEFAULT_RECODES.toString()))
            .andExpect(jsonPath("$.morevalues").value(DEFAULT_MOREVALUES.booleanValue()))
            .andExpect(jsonPath("$.nextpage").value(DEFAULT_NEXTPAGE))
            .andExpect(jsonPath("$.totalresults").value(DEFAULT_TOTALRESULTS))
            .andExpect(jsonPath("$.latitude").value(DEFAULT_LATITUDE.toString()))
            .andExpect(jsonPath("$.longitude").value(DEFAULT_LONGITUDE.toString()));
    }

    @Test
    public void getNonExistingAddress() throws Exception {
        // Get the address
        restAddressMockMvc.perform(get("/api/addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateAddress() throws Exception {
        // Initialize the database
        addressService.save(address);

        int databaseSizeBeforeUpdate = addressRepository.findAll().size();

        // Update the address
        Address updatedAddress = addressRepository.findOne(address.getId());
        updatedAddress
            .addressline1(UPDATED_ADDRESSLINE_1)
            .addressline2(UPDATED_ADDRESSLINE_2)
            .addressline3(UPDATED_ADDRESSLINE_3)
            .summaryline(UPDATED_SUMMARYLINE)
            .organisation(UPDATED_ORGANISATION)
            .buildingname(UPDATED_BUILDINGNAME)
            .premise(UPDATED_PREMISE)
            .street(UPDATED_STREET)
            .dependentlocality(UPDATED_DEPENDENTLOCALITY)
            .posttown(UPDATED_POSTTOWN)
            .county(UPDATED_COUNTY)
            .postcode(UPDATED_POSTCODE)
            .number(UPDATED_NUMBER)
            .pobox(UPDATED_POBOX)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .subbuildingname(UPDATED_SUBBUILDINGNAME)
            .dependentstreet(UPDATED_DEPENDENTSTREET)
            .doubledependentlocality(UPDATED_DOUBLEDEPENDENTLOCALITY)
            .recodes(UPDATED_RECODES)
            .morevalues(UPDATED_MOREVALUES)
            .nextpage(UPDATED_NEXTPAGE)
            .totalresults(UPDATED_TOTALRESULTS)
            .latitude(UPDATED_LATITUDE)
            .longitude(UPDATED_LONGITUDE);

        restAddressMockMvc.perform(put("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAddress)))
            .andExpect(status().isOk());

        // Validate the Address in the database
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).hasSize(databaseSizeBeforeUpdate);
        Address testAddress = addresses.get(addresses.size() - 1);
        assertThat(testAddress.getAddressline1()).isEqualTo(UPDATED_ADDRESSLINE_1);
        assertThat(testAddress.getAddressline2()).isEqualTo(UPDATED_ADDRESSLINE_2);
        assertThat(testAddress.getAddressline3()).isEqualTo(UPDATED_ADDRESSLINE_3);
        assertThat(testAddress.getSummaryline()).isEqualTo(UPDATED_SUMMARYLINE);
        assertThat(testAddress.getOrganisation()).isEqualTo(UPDATED_ORGANISATION);
        assertThat(testAddress.getBuildingname()).isEqualTo(UPDATED_BUILDINGNAME);
        assertThat(testAddress.getPremise()).isEqualTo(UPDATED_PREMISE);
        assertThat(testAddress.getStreet()).isEqualTo(UPDATED_STREET);
        assertThat(testAddress.getDependentlocality()).isEqualTo(UPDATED_DEPENDENTLOCALITY);
        assertThat(testAddress.getPosttown()).isEqualTo(UPDATED_POSTTOWN);
        assertThat(testAddress.getCounty()).isEqualTo(UPDATED_COUNTY);
        assertThat(testAddress.getPostcode()).isEqualTo(UPDATED_POSTCODE);
        assertThat(testAddress.getNumber()).isEqualTo(UPDATED_NUMBER);
        assertThat(testAddress.getPobox()).isEqualTo(UPDATED_POBOX);
        assertThat(testAddress.getDepartmentname()).isEqualTo(UPDATED_DEPARTMENTNAME);
        assertThat(testAddress.getSubbuildingname()).isEqualTo(UPDATED_SUBBUILDINGNAME);
        assertThat(testAddress.getDependentstreet()).isEqualTo(UPDATED_DEPENDENTSTREET);
        assertThat(testAddress.getDoubledependentlocality()).isEqualTo(UPDATED_DOUBLEDEPENDENTLOCALITY);
        assertThat(testAddress.getRecodes()).isEqualTo(UPDATED_RECODES);
        assertThat(testAddress.isMorevalues()).isEqualTo(UPDATED_MOREVALUES);
        assertThat(testAddress.getNextpage()).isEqualTo(UPDATED_NEXTPAGE);
        assertThat(testAddress.getTotalresults()).isEqualTo(UPDATED_TOTALRESULTS);
        assertThat(testAddress.getLatitude()).isEqualTo(UPDATED_LATITUDE);
        assertThat(testAddress.getLongitude()).isEqualTo(UPDATED_LONGITUDE);
    }

    @Test
    public void deleteAddress() throws Exception {
        // Initialize the database
        addressService.save(address);

        int databaseSizeBeforeDelete = addressRepository.findAll().size();

        // Get the address
        restAddressMockMvc.perform(delete("/api/addresses/{id}", address.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).hasSize(databaseSizeBeforeDelete - 1);
    }
}
