package org.elevate.treinamento.diogo.rest;

import org.elevate.treinamento.diogo.domain.to.ProdutoTO;
import org.elevate.treinamento.diogo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v2/produtos")
public class ProdutoControllerV2 {

	@Autowired
	private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<ProdutoTO>> getAllProdutos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "nome") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        
        Sort.Direction sortDirection = Sort.Direction.fromString(direction.toLowerCase());
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        
        Page<ProdutoTO> produtos = produtoService.getAllProdutos(pageable);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoTO> getProduto(@PathVariable String id) {
        ProdutoTO produtoTO = produtoService.getProdutoById(id);
        return ResponseEntity.ok(produtoTO);
    }
    
    @PostMapping
    public ResponseEntity<ProdutoTO> createProduto(@Valid @RequestBody ProdutoTO produtoTO) {
        ProdutoTO createdProduto = produtoService.createProduto(produtoTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduto);
    }


	@PutMapping("/{id}")
    public ResponseEntity<ProdutoTO> updateProduto(@PathVariable String id, @Valid @RequestBody ProdutoTO produtoTO) {
        ProdutoTO updatedProduto = produtoService.updateProduto(id, produtoTO);
        return ResponseEntity.ok(updatedProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable String id) {
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}