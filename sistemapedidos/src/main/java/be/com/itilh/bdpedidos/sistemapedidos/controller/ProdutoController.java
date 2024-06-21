package be.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import be.com.itilh.bdpedidos.sistemapedidos.model.Produto;
import be.com.itilh.bdpedidos.sistemapedidos.repository.ProdutoRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ProdutoController {

    private final ProdutoRepository repositorio;

    public ProdutoController(ProdutoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/produtos")
    public List<Produto> getProdutos() {
        return (List<Produto>) repositorio.findAll();
    }

    @GetMapping("/produto/{id}")
    public Produto getProdutoid(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("ID Inválido"));
    }

    @PostMapping("/produto")
    public Produto postProduto(@RequestBody Produto entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Erro ao Salvar o Produto!");
        }
    }

    @PutMapping("produto/{id}")
    public Produto alterarProduto(@RequestBody Produto entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception ex) {
            throw new Exception("Não foi possível alterar o produto." + ex.getMessage());
        }
    }

    @DeleteMapping("/produto/{id}")
    public String deleteProdutoPorId(@PathVariable BigInteger id) throws Exception {

        Optional<Produto> produtoArmazenado = repositorio.findById(id);
        if (produtoArmazenado.isPresent()) {
            repositorio.delete(produtoArmazenado.get());
            return "Excluido";
        }

        throw new Exception("ID não encontrado para Exclusão!");
    }

}
