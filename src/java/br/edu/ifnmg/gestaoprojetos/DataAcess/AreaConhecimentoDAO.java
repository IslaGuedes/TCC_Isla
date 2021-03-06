/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.DataAcess;

import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimento;
import br.edu.ifnmg.gestaoprojetos.DomainModel.AreaConhecimentoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Isla Guedes
 */
@Stateless(name= "AreaConhecimentoRepositorio")
public class AreaConhecimentoDAO 

    extends DAOGenerico<AreaConhecimento>
    implements AreaConhecimentoRepositorio{

    public AreaConhecimentoDAO() {
        super(AreaConhecimento.class);
    }
    
    @Override
    public List<AreaConhecimento> Buscar(AreaConhecimento obj) {
        String sql = "select a from AreaConhecimento a";
        
        String filtros = "";
        
        if(obj != null){
            if(obj.getId() != null){
                filtros += "a.id = " + obj.getId();
            }
            if(obj.getNome() != null){
                if(filtros.length() > 0)
                    filtros += " and ";
                filtros += "a.nome like '%" + obj.getNome() + "%'"; 
            }
           
        }
        
        if(filtros.length() > 0){
            sql += " where " + filtros;
        }
        
        Query consulta = manager.createQuery(sql);
        
        return consulta.getResultList();
    }
    
   public AreaConhecimento Abrir(String nome) {
        String sql = "select a from AreaConhecimento a where a.nome = :s";
        
        Query consulta = manager.createQuery(sql);
        
        consulta.setParameter("s", nome);
        try {
            return (AreaConhecimento)consulta.getSingleResult();
        } catch(Exception ex){
            return null;
        }
    }  
    
    
    
}

