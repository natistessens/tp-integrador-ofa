/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Pedido;

/**
 *
 * @author nstessens
 */
@Stateless
public class PedidoService {

    @PersistenceContext(unitName = "RECETAS_PU")
    private EntityManager em;
    
    
    public Pedido guardar(Pedido p) {
        if(p.getIdPedido()!=null && p.getIdPedido()>0) {
             return em.merge(p);        }
        em.persist(p);
        em.flush();
        em.refresh(p);
        return p;

    }
}
