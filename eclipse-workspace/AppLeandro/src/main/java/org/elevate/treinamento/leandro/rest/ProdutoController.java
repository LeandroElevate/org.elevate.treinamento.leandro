package org.elevate.treinamento.leandro.rest;

import java.util.List;

import org.elevate.treinamento.leandro.domain.to.ProdutoTO;
import org.elevate.treinamento.leandro.service.ProdutoService;
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
@RequestMapping("/api/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

    @GetMapping
    public List<ProdutoTO> getAllProdutos() {
        return produtoService.getAllProdutos();
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