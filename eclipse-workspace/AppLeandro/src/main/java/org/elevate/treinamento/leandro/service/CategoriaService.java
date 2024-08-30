package org.elevate.treinamento.leandro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.elevate.treinamento.leandro.domain.entity.CategoriaEntity;
import org.elevate.treinamento.leandro.domain.to.CategoriaTO;
import org.elevate.treinamento.leandro.exception.EntityNotFoundException;
import org.elevate.treinamento.leandro.repository.CategoriaRepository;
import org.elevate.treinamento.leandro.util.ErrorEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CategoriaTO> getAllCategoria() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Page<CategoriaTO> getAllCategoria(Pageable pageable) {
        Page<CategoriaEntity> categoriaPage = categoriaRepository.findAll(pageable);
        return categoriaPage.map(this::convertToDTO);
    }

    public CategoriaTO getCategoriaById(Long id) {
    	CategoriaEntity categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorEnum.NOT_FOUND, id));
        return convertToDTO(categoria);
    }

    public CategoriaTO createCategoria(CategoriaTO categoriaTO) {
    	CategoriaEntity categoria = convertToEntity(categoriaTO);
    	CategoriaEntity savedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(savedCategoria);
    }

    public CategoriaTO updateCategoria(Long id, CategoriaTO categoriaTO) {
    	CategoriaEntity categoria = categoriaRepository.findById(id)
        		.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        modelMapper.map(categoriaTO, categoria);
        CategoriaEntity updatedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(updatedCategoria);
    }

    public void deleteCategoria(Long id) {
    	categoriaRepository.deleteById(id);
    }

    private CategoriaTO convertToDTO(CategoriaEntity categoria) {
    	CategoriaTO categoriaTO = modelMapper.map(categoria, CategoriaTO.class);
		return categoriaTO;
    }

    private CategoriaEntity convertToEntity(CategoriaTO categoriaTO) {
    	CategoriaEntity categoriaEntity = modelMapper.map(categoriaTO,CategoriaEntity.class);
		return categoriaEntity;
    }
}