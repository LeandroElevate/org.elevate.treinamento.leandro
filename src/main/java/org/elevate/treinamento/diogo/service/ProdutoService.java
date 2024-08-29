package org.elevate.treinamento.diogo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.elevate.treinamento.diogo.domain.entity.ProdutoEntity;
import org.elevate.treinamento.diogo.domain.to.ProdutoTO;
import org.elevate.treinamento.diogo.exception.EntityNotFoundException;
import org.elevate.treinamento.diogo.repository.ProdutoRepository;
import org.elevate.treinamento.diogo.util.ErrorEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProdutoTO> getAllProdutos() {
        return produtoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Page<ProdutoTO> getAllProdutos(Pageable pageable) {
        Page<ProdutoEntity> produtoPage = produtoRepository.findAll(pageable);
        return produtoPage.map(this::convertToDTO);
    }

    public ProdutoTO getProdutoById(String id) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorEnum.NOT_FOUND, id));
        return convertToDTO(produto);
    }

    public ProdutoTO createProduto(ProdutoTO produtoTO) {
        ProdutoEntity produto = convertToEntity(produtoTO);
        ProdutoEntity savedProduto = produtoRepository.save(produto);
        return convertToDTO(savedProduto);
    }

    public ProdutoTO updateProduto(String id, ProdutoTO produtoTO) {
        ProdutoEntity produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        modelMapper.map(produtoTO, produto);
        ProdutoEntity updatedProduto = produtoRepository.save(produto);
        return convertToDTO(updatedProduto);
    }

    public void deleteProduto(String id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoTO convertToDTO(ProdutoEntity produto) {
        ProdutoTO produtoTO = modelMapper.map(produto, ProdutoTO.class);
		return produtoTO;
    }

    private ProdutoEntity convertToEntity(ProdutoTO produtoTO) {
        ProdutoEntity produtoEntity = modelMapper.map(produtoTO, ProdutoEntity.class);
		return produtoEntity;
    }
}