package be.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.RestController;

import be.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import be.com.itilh.bdpedidos.sistemapedidos.repository.EstadoRepository;
import be.com.itilh.bdpedidos.sistemapedidos.util.ModoBusca;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class EstadoController {

    private final EstadoRepository repositorio;

    public EstadoController(EstadoRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/estados")
    public List<Estado> getTodos() {
        // pedir para o repositoy trazer a lista de estados
        return (List<Estado>) repositorio.findAll();
    }

    @GetMapping("/estado/{id}")
    public Estado getPorid(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(
                () -> new Exception("ID Inválido"));
    }

    // @GetMapping("/estados/nome/{nome}")
    // public List<Estado> getEstadosPorNome(@PathVariable String nome) {
    // return repositorio.findByNomeContainingIgnoreCase(nome);
    // return repositorio.findByMinhaQuery(nome);
    // }

    @GetMapping("/estados/nome/{nome}")
    public List<Estado> getEstadosPorNome(@PathVariable String nome, @RequestParam(required=true) ModoBusca modoBusca) {
        if (modoBusca.equals(ModoBusca.EXATO)) {
            return repositorio.findByNome(nome);
        } else if (modoBusca.equals(ModoBusca.INICIADO)) { 
            return repositorio.findByNomeStartsWithIgnoreCase(nome);
        } else if (modoBusca.equals(ModoBusca.FINALIZADO)) {
            return repositorio.findByNomeEndingWithIgnoreCase(nome);
        } else {
            return repositorio.findByNomeContainingIgnoreCase(nome);
        }

    }

    @PostMapping("/estado")
    public Estado postEstado(@RequestBody Estado entity) throws Exception {
        try {
            return repositorio.save(entity);
        } catch (Exception e) {
            throw new Exception("Erro ao Salvar o Estado!");
        }
    }

    @PutMapping("/estado/{id}")
    public Estado alterarEstado(@PathVariable BigInteger id, @RequestBody Estado novosDados) throws Exception {
        Optional<Estado> estadoArmazenado = repositorio.findById(id);
        if (estadoArmazenado.isPresent()) {
            estadoArmazenado.get().setNome(novosDados.getNome());
            return repositorio.save(estadoArmazenado.get());
        }

        throw new Exception("Alteração não Realizada!");
    }

    @DeleteMapping("/estado/{id}")
    public String deletePorId(@PathVariable BigInteger id) throws Exception {

        Optional<Estado> estadoArmazenado = repositorio.findById(id);
        if (estadoArmazenado.isPresent()) {
            repositorio.delete(estadoArmazenado.get());
            return "Excluido";
        }

        throw new Exception("ID não encontrado para Exclusão!");
    }

}
