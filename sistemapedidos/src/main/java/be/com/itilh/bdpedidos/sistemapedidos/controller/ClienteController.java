package be.com.itilh.bdpedidos.sistemapedidos.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import be.com.itilh.bdpedidos.sistemapedidos.model.Cliente;
import be.com.itilh.bdpedidos.sistemapedidos.repository.ClienteRepository;

import java.math.BigInteger;
import java.util.List;

@RestController
public class ClienteController {

    private final ClienteRepository repositorio;

    public ClienteController(ClienteRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping("/clientes")
    public List<Cliente> getClientes() {
        return (List<Cliente>) repositorio.findAll();
    }

    @GetMapping("/cliente/{id}")
    public Cliente getClientePorId(@PathVariable BigInteger id) throws Exception {
        return repositorio.findById(id).orElseThrow(() -> new Exception("Id não Encontrado"));
    }

    @GetMapping("/clientes/municipio/{id}")
    public List<Cliente> getClientesPorMunicipioId(@PathVariable BigInteger id) {
        return (List<Cliente>) repositorio.findByMunicipioId(id);
    }    

    @PostMapping("/cliente")
    public Cliente postCliente(@RequestBody Cliente entity) throws Exception {    
        try{ 
            return repositorio.save(entity);
        }catch (Exception ex){
            throw new Exception("Não foi possível criar o cliente." + ex.getMessage());
        }
    }    

    @PutMapping("cliente/{id}")
    public Cliente putCliente(@RequestBody Cliente entity) throws Exception {
        try{ 
            return repositorio.save(entity);
        }catch (Exception ex){
            throw new Exception("Não foi possível alterar o cliente." + ex.getMessage());
        }
    }
    
    @DeleteMapping("/cliente/{id}")
    public String deleteCliente(@PathVariable BigInteger id) throws Exception{
        try{ 
             repositorio.deleteById(id);
             return "Excluído";
        }catch (Exception ex){
            throw new Exception("Não foi possível excluir o cliente." + ex.getMessage());
        }
    }    

}
