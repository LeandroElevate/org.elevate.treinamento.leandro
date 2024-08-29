package org.elevate.treinamento.diogo.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import org.elevate.treinamento.diogo.AbstractControllerTest;
import org.elevate.treinamento.diogo.domain.to.CategoriaTO;
import org.elevate.treinamento.diogo.domain.to.ProdutoTO;
import org.elevate.treinamento.diogo.rest.ProdutoController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest(ProdutoController.class)
public class ProdutoTest extends AbstractControllerTest<ProdutoTO, ProdutoController> {

	// definição de parametros de teste
	private static String PATH = "/api/produtos";
	private static String nome = "Teste Produto";
	private static double preco = 10.0;
	private static long idCategoria = 1L;
	private static String descricaoCategoria = "Teste1";

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
	protected List<ProdutoTO> convertMultiple(String json) throws Exception {
		return Arrays.asList(mapper.readValue(json, ProdutoTO[].class));
	}

	@Override
	protected ProdutoTO convertOne(String json) throws Exception {
		return mapper.readValue(json, ProdutoTO.class);
	}

	@Override
	protected ProdutoTO getObjectTest() {

		if (objectTest == null) {
			final CategoriaTO categoria = new CategoriaTO();
			categoria.setId("1");
			categoria.setDescricao("Descrição Categoria");
			final ProdutoTO produtoTO = new ProdutoTO();
			produtoTO.setNome(nome);
			produtoTO.setPreco(preco);
			produtoTO.setCategoria(categoria);
			
			objectTest = produtoTO;
		}

		return objectTest;
	}

	@Override
	protected String getPath() {

		return PATH;
	}

}