package be.com.itilh.bdpedidos.sistemapedidos.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.com.itilh.bdpedidos.sistemapedidos.model.Estado;
import java.util.List;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, BigInteger> {

    // select * from tb_estados where tx_nome = 'nome'
    List<Estado> findByNome(String nome);

    // select * from tb_estados where tx_nome like 'nome%'
    List<Estado> findByNomeStartsWithIgnoreCase(String nome);

    // Select * from tb_estados where UPPER(tx_nome) like UPPER('%nome')//
    List<Estado> findByNomeEndingWithIgnoreCase(String nome);   

    // select * from tb_estados where tx_nome like '%nome%'
    List<Estado> findByNomeContainingIgnoreCase(String nome);

    @Query("from Estado e WHERE UPPER(e.nome) like %?1")
    List<Estado> findByMinhaQuery(String nome);

    @Query(value = "select * from tb_estados" , nativeQuery = true)
    List<Estado> findByNativeQuery();

}
