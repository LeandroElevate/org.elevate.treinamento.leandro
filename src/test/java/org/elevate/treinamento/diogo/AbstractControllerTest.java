package org.elevate.treinamento.diogo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.assertj.core.util.Strings;
import org.elevate.treinamento.diogo.domain.to.BaseTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { DiogoRest1ApplicationTest.class })
@ExtendWith(SpringExtension.class)
@SpringJUnitWebConfig
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles(profiles = { "test" })
@TestInstance(Lifecycle.PER_CLASS)
public abstract class AbstractControllerTest<TO extends BaseTO, CONTROLLER> {

	protected static MockMvc mockMvc;
	protected static ObjectMapper mapper;

	@Autowired
	protected CONTROLLER controller;
	protected TO objectTest;

	protected abstract List<TO> convertMultiple(String json) throws Exception;

	protected abstract TO convertOne(String json) throws Exception;

	protected void doDelete() throws UnsupportedEncodingException, Exception {
		if (Strings.isNullOrEmpty(getObjectTest().getId())) {
			doSave();
		}

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.delete(getPath() + "/" + getObjectTest().getId());

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.NO_CONTENT.value()));

	}

	protected void doFindAll() throws UnsupportedEncodingException, Exception {
		if (Strings.isNullOrEmpty(getObjectTest().getId())) {
			doSave();
		}

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(getPath());

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotEmpty();

		final List<TO> objResult = convertMultiple(result);

		assertThat(objResult).isNotNull();
		assertThat(objResult).isNotEmpty();

	}

	protected void doFindById() throws UnsupportedEncodingException, Exception {
		if (Strings.isNullOrEmpty(getObjectTest().getId())) {
			doSave();
		}

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(getPath() + "/" + getObjectTest().getId());

		final String result = mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.OK.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotEmpty();

		final TO objResult = convertOne(result);

		assertThat(objResult).isNotNull();
		assertThat(objResult.getId()).hasToString(getObjectTest().getId());

	}

	protected void doFindByIdFail() throws UnsupportedEncodingException, Exception {
		if (Strings.isNullOrEmpty(getObjectTest().getId())) {
			doSave();
		}

		final MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.get(getPath() + "/" + getObjectTest().getId());

		mockMvc.perform(request).andDo(print()).andExpect(status().is(HttpStatus.NOT_FOUND.value())).andReturn();
	}

	protected void doSave() throws Exception {

		final byte[] objectTestAsByteArray = mapper.writeValueAsBytes(getObjectTest());
		final MockHttpServletRequestBuilder requestSave = MockMvcRequestBuilders.post(getPath())
				.characterEncoding(StandardCharsets.UTF_8.name()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(objectTestAsByteArray);

		final String result = mockMvc.perform(requestSave).andDo(print()).andExpect(status().is(HttpStatus.CREATED.value()))
				.andReturn().getResponse().getContentAsString();

		assertThat(result).isNotNull();

		final TO objResult = convertOne(result);
		assertThat(objResult.getId()).isNotNull();
		getObjectTest().setId(objResult.getId());
	}

	protected CONTROLLER getController() {
		return controller;
	};

	protected abstract TO getObjectTest();

	protected abstract String getPath();

	@BeforeAll
	public void setUpMocks() {
		mockMvc = MockMvcBuilders.standaloneSetup(getController()).build();
		mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
}