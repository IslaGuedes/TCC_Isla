/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AgenciaFinanciadora;
import br.edu.ifnmg.gestaoprojetos.DomainModel.Modalidade;
import br.edu.ifnmg.gestaoprojetos.DomainModel.ModalidadeRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author HOME
 */
@Stateless(name= "ModalidadeRepositorio")
public class ModalidadeDAO

    extends DAOGenerico<Modalidade>
    implements ModalidadeRepositorio {
    
    public ModalidadeDAO() {
        super(Modalidade.class);
    }
    
    @Override
    public List<Modalidade> Buscar(Modalidade obj) {
        String sql = "select m from Modalidade m";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += " m.id = " + obj.getId();
            }
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "m.nome like '%" + obj.getNome() + "%'"; 
            }
            if(obj.getSigla() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "m.sigla like '%" + obj.getSigla() + "%'"; 
            }
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
    
   public Modalidade Abrir(String sigla) {
        String sql = "select m from Modalidade m where m.sigla = :s";
        
        Query consulta = manager.createQuery(sql);
        
        consulta.setParameter("s", sigla);
        try {
            return (Modalidade)consulta.getSingleResult();
        } catch(Exception ex){
            return null;
        }
    }
    
}
