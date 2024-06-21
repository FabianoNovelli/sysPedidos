package be.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import be.com.itilh.bdpedidos.sistemapedidos.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, BigInteger >{

        

}
