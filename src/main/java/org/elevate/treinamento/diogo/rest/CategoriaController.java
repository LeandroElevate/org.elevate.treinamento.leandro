package org.elevate.treinamento.diogo.rest;

import java.util.List;

import org.elevate.treinamento.diogo.domain.to.CategoriaTO;
import org.elevate.treinamento.diogo.service.CategoriaService;
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
@RequestMapping("/api/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaTO> getAllCategorias() {
        return categoriaService.getAllCategorias();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoriaTO> getCategoria(@PathVariable Long id) {
        CategoriaTO produtoTO = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(produtoTO);
    }
    
    @PostMapping
    public ResponseEntity<CategoriaTO> createCategoria(@Valid @RequestBody CategoriaTO produtoTO) {
        CategoriaTO createdCategoria = categoriaService.createCategoria(produtoTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategoria);
    }


	@PutMapping("/{id}")
    public ResponseEntity<CategoriaTO> updateCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaTO produtoTO) {
        CategoriaTO updatedCategoria = categoriaService.updateCategoria(id, produtoTO);
        return ResponseEntity.ok(updatedCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }
}