/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utn.frsf.ofa.cursojava.tp.integrador.logica.RecetaLogica;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Ingrediente;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;

/**
 *
 * @author mdominguez
 */
@Stateless
public class RecetaService {
    @PersistenceContext(unitName = "RECETAS_PU")
    private EntityManager em;
    
    @Inject
    private RecetaLogica logica;
    
    public Receta guardar(Receta r){        
        if(!logica.autorPuedeCrearReceta(r)) throw new RuntimeException("La receta no puede ser creada. El autor creo el maximo de recetas. Verifique los datos"); 
        if(!logica.puedeAgregarIngredientes(r)) throw new RuntimeException("La receta no puede ser creada. Excede la cantidad maxima de ingredientes"); 
        if(!logica.costoIngredientesValido(r)) throw new RuntimeException("La receta no puede ser creada. El monto de los ingredientes supera el maximo"); 
        if(r.getId()!=null && r.getId()>0) {
                return em.merge(r);
        }
        em.persist(r);
        em.flush();
        em.refresh(r);
        return r;
    }  
    
    public List<Receta> listar(){
        return em.createQuery("SELECT r FROM Receta r").getResultList();
    }
    
    public List<Ingrediente> ingredientesPorIdReceta(Integer id){
        return em.createQuery("SELECT i FROM Receta r JOIN r.ingredientes i WHERE r.id = :P_ID_RECETA")
                .setParameter("P_ID_RECETA", id)
                .getResultList();
    }
    
    public List<Receta> busquedaAvanzada(AutorService a, Ingrediente i, Double precioMin, Double precioMax,Date fMin,Date fMax){        
                String queryStr = "SELECT r FROM Receta r WHERE r.id > 0";
        if (precioMin != null) {
            queryStr += " AND r.precio >= :PRECIO_MIN";
        }
        if (precioMax != null) {
            queryStr += " AND r.precio <= :PRECIO_MAX";
        }
        if (fMin != null) {
            queryStr += " AND r.fechaCreacion >= :FECHA_MIN";
        }
        if (fMax != null) {
            queryStr += " AND r.fechaCreacion <= :FECHA_MAX";
        }
        if (a != null) {
            queryStr += " AND r.autor.id = :ID_AUTOR";
        }

        List<Receta> retorno = new ArrayList<Receta>();
        Query query = em.createQuery(queryStr);
        if (precioMin != null) {
            query.setParameter("PRECIO_MIN", precioMin);
        }
        if (precioMax != null) {
            query.setParameter("PRECIO_MAX", precioMax);
        }
        if (fMin != null) {
            query.setParameter("FECHA_MIN", fMin);
        }
        if (fMax != null) {
            query.setParameter("FECHA_MAX", fMax);
        }
        if (a != null) {
            query.setParameter("ID_AUTOR", a);
        }
        retorno = query.getResultList();
        return retorno;
    }

}
