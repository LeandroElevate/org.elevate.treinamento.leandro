package org.elevate.treinamento.leandro.rest;

import java.util.List;

import org.elevate.treinamento.leandro.domain.to.LivroTO;
import org.elevate.treinamento.leandro.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/livro")

public class LivroController {

		@Autowired
		private LivroService livroService;

	    @GetMapping
	    public List<LivroTO> getAllLivro() {
	        return livroService.getAllLivro();
	    }


	    @GetMapping("/{id}")
	    public ResponseEntity<LivroTO> getLivro(@PathVariable String id) {
	    	LivroTO livroTO = livroService.getLivroById(id);
	        return ResponseEntity.ok(livroTO);
	    }
	    
	    @PostMapping
	    public ResponseEntity<LivroTO> createLivro(@Valid @RequestBody LivroTO livroTO) {
	    	LivroTO createdLivro = livroService.createLivro(livroTO);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdLivro);
	    }


		@PutMapping("/{id}")
	    public ResponseEntity<LivroTO> updateLivro(@PathVariable String id, @Valid @RequestBody LivroTO livroTO) {
			LivroTO updatedLivro = livroService.updateLivro(id, livroTO);
	        return ResponseEntity.ok(updatedLivro);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteLivro(@PathVariable String id) {
	    	livroService.deleteLivro(id);
	        return ResponseEntity.noContent().build();
	    }
	}