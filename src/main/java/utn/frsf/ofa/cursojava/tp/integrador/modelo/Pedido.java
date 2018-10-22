/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nstessens
 */

@Entity
public class Pedido implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)      
    Integer idPedido;
    @Temporal(TemporalType.DATE)
    Date fechaPedido;
    String direccionEnvio;
    
    @OneToMany(mappedBy = "pedido")
    List<Receta> recetasPedidas;

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public void setRecetasPedidas(List<Receta> recetasPedidas) {
        this.recetasPedidas = recetasPedidas;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public List<Receta> getRecetasPedidas() {
        return recetasPedidas;
    }

}
