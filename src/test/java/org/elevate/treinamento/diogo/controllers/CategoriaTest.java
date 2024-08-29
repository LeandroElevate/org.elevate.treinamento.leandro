package org.elevate.treinamento.diogo.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.elevate.treinamento.diogo.AbstractControllerTest;
import org.elevate.treinamento.diogo.domain.to.CategoriaTO;
import org.elevate.treinamento.diogo.rest.CategoriaController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(CategoriaController.class)
public class CategoriaTest extends AbstractControllerTest<CategoriaTO, CategoriaController> {

	// definição de parametros de teste
	private static String PATH = "/api/categorias";
	private static String nome = "Teste Categoria";

	@Test
	public void aa_save() throws UnsupportedEncodingException, Exception {
		doSave();
	}

	@Test
	public void ab_findSavedAgreements() throws UnsupportedEncodingException, Exception {
		doFindById();
	}

	@Test
	public void ac_findAllAgreements() throws UnsupportedEncodingException, Exception {
		doFindAll();
	}

	@Test
	public void ad_delete() throws UnsupportedEncodingException, Exception {
		doDelete();
		doFindByIdFail();
	}

	@Override
	protected List<CategoriaTO> convertMultiple(String json) throws Exception {
		return Arrays.asList(mapper.readValue(json, CategoriaTO[].class));
	}

	@Override
	protected CategoriaTO convertOne(String json) throws Exception {
		return mapper.readValue(json, CategoriaTO.class);
	}

	@Override
	protected CategoriaTO getObjectTest() {

		if (objectTest == null) {
			final CategoriaTO categoriaTO = new CategoriaTO();
			categoriaTO.setDescricao("Descricao Categoria");
			categoriaTO.setId("1");
			
			objectTest = categoriaTO;
		}

		return objectTest;
	}

	@Override
	protected String getPath() {

		return PATH;
	}

}