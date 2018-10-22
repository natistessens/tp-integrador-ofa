/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frsf.ofa.cursojava.tp.integrador.controladores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Pedido;
import utn.frsf.ofa.cursojava.tp.integrador.modelo.Receta;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.PedidoService;
import utn.frsf.ofa.cursojava.tp.integrador.servicio.RecetaService;

/**
 *
 * @author nstessens
 */
@SessionScoped
@Named("pedidoController")
public class PedidoController implements Serializable {

    @Inject
    PedidoService pedidoSrv;
    @Inject
    RecetaService recetaService;

    private Pedido pedidoSeleccionado;
    List<Receta> listaRecetas;

    @PostConstruct
    public void init() {
        this.pedidoSeleccionado = null;
        this.listaRecetas = recetaService.listar();
    }

    public void setListaRecetas(List<Receta> listaRecetas) {
        this.listaRecetas = listaRecetas;
    }

    public List<Receta> getListaRecetas() {
        return listaRecetas;
    }

    public Pedido getPedidoSeleccionado() {
        return pedidoSeleccionado;
    }

    public void setPedidoSeleccionado(Pedido pedidoSeleccionado) {
        this.pedidoSeleccionado = pedidoSeleccionado;
    }

    public PedidoService getPedidoSrv() {
        return pedidoSrv;
    }

    public void setPedidoSrv(PedidoService pedidoSrv) {
        this.pedidoSrv = pedidoSrv;
    }

    public RecetaService getRecetaService() {
        return recetaService;
    }

    public void setRecetaService(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    public String guardar() {
        Pedido tmp = this.pedidoSrv.guardar(pedidoSeleccionado);
        this.pedidoSeleccionado = null;
        return null;
    }

    public String nuevo() {
        this.pedidoSeleccionado = new Pedido();
        return null;
    }
}
