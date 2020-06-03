package br.unitins.books.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.dao.CamisaDAO;
import br.unitins.books.model.Camisa;

@Named
@ViewScoped
public class ConsultaCamisaController implements Serializable{
	
	private static final long serialVersionUID = 5971844866316069324L;
	
	private int tipoFiltro = 1;
	private String filtro;
	private List<Camisa> listaCamisa;
	
	public void pesquisar() {
		CamisaDAO dao = new CamisaDAO();
		if (tipoFiltro == 1)
			listaCamisa = dao.findByModelo(getFiltro());
		else 
			listaCamisa = dao.findByMarca(getFiltro());
	}
	
	public String novaCamisa() {
		return "camisa.xhtml?faces-redirect=true";
	}
	
	public String editar(Camisa camisa) {
		CamisaDAO dao = new CamisaDAO();
		camisa = dao.findById(camisa.getId());
		
		Flash flash = FacesContext.getCurrentInstance().
						getExternalContext().getFlash();
		
		flash.put("flashCamisa", camisa);
		return "camisa.xhtml?faces-redirect=true";
	}

	public List<Camisa> getListaCamisa() {
		if (listaCamisa == null) {
			listaCamisa = new ArrayList<Camisa>();
		}
		return listaCamisa;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

}
