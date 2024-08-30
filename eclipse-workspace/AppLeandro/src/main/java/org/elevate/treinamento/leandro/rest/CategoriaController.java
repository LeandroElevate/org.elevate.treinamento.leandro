package org.elevate.treinamento.leandro.rest;
import java.util.List;

import org.elevate.treinamento.leandro.domain.to.CategoriaTO;
import org.elevate.treinamento.leandro.service.CategoriaService;
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
@RequestMapping("/api/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaTO> getAllCategoria() {
        return categoriaService.getAllCategoria();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaTO> getCategoria(@PathVariable Long id) {
    	CategoriaTO categoriaTO = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(categoriaTO);
    }
    
    @PostMapping
    public ResponseEntity<CategoriaTO> createCategoria(@Valid @RequestBody CategoriaTO categoriaTO) {
    	CategoriaTO createdCategoria = categoriaService.createCategoria(categoriaTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }


	@PutMapping("/{id}")
    public ResponseEntity<CategoriaTO> updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaTO categoriaTO) {
		CategoriaTO updatedCategoria = categoriaService.updateCategoria(id, categoriaTO);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
    	categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}