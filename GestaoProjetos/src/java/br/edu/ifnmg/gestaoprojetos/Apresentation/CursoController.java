/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.gestaoprojetos.Apresentation;

import br.edu.ifnmg.gestaoprojetos.DomainModel.*;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Isla Guedes
 */
@Named(value = "cursoController")
@SessionScoped
public class CursoController
   extends ControllerGenerico<Curso> implements Serializable {

    /**
     * Creates a new instance of CursoController
     */
    public CursoController() {
        filtro = new Curso();
        entidade = new Curso();
    }
    
    @EJB
    CursoRepositorio dao;
    
    @Override
    public void salvar() {
        if(dao.Salvar(entidade)){
            
        } else {
            //mensagem de erro
        }
    }

    @Override
    public String novo(){
        entidade = new Curso();
        return "admin/editarCurso.xhtml";
    }
    
    @Override
    public String abrir() {
        return "admin/editarCurso.xhtml";
    }

    @Override
    public String cancelar() {
        return "admin/listagemCurso.xhtml";
    }

    
    @Override
    public String excluir() {
        if(dao.Apagar(entidade)){
            return "admin/listagemCurso.xhtml";
        } else {
            return "";
        }
    }

    @Override
    public void filtrar() {
        listagem = dao.Buscar(filtro);
    }

    public CursoRepositorio getDao() {
        return dao;
    }

    public void setDao(CursoRepositorio dao) {
        this.dao = dao;
    }
    
    

}