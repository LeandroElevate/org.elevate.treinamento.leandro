package org.elevate.treinamento.leandro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.elevate.treinamento.leandro.domain.entity.LivroEntity;
import org.elevate.treinamento.leandro.domain.to.LivroTO;
import org.elevate.treinamento.leandro.exception.EntityNotFoundException;
import org.elevate.treinamento.leandro.repository.LivroRepository;
import org.elevate.treinamento.leandro.util.ErrorEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class LivroService {

	    @Autowired
	    private LivroRepository livroRepository;

	    @Autowired
	    private ModelMapper modelMapper;

	    public List<LivroTO> getAllLivro() {
	        return livroRepository.findAll().stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    public Page<LivroTO> getAllLivro(Pageable pageable) {
	        Page<LivroEntity> livroPage = livroRepository.findAll(pageable);
	        return livroPage.map(this::convertToDTO);
	    }

	    public LivroTO getLivroById(String id) {
	    	LivroEntity livro = livroRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException(ErrorEnum.NOT_FOUND, id));
	        return convertToDTO(livro);
	    }

	    public LivroTO updateLivro(String id, LivroTO livroTO) {
	    	LivroEntity livro = livroRepository.findById(id)
	        		.orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
	        
	        modelMapper.map(livroTO, livro);
	        LivroEntity updatedCategoria = livroRepository.save(livro);
	        return convertToDTO(updatedCategoria);
	    }


	    private LivroTO convertToDTO(LivroEntity livro) {
	    	LivroTO livroTO = modelMapper.map(livro, LivroTO.class);
			return livroTO;
	    }

	    private LivroEntity convertToEntity(LivroTO livroTO) {
	    	LivroEntity LivroEntity = modelMapper.map(livroTO,LivroEntity.class);
			return LivroEntity;
	    }

		public LivroTO createLivro(@Valid LivroTO livroTO) {
			LivroEntity livro = convertToEntity(livroTO);
	    	LivroEntity savedLivro = livroRepository.save(livro);
	        return convertToDTO(savedLivro);
		}

		public void deleteLivro(String id) {
			livroRepository.deleteById(id);

		}

	}