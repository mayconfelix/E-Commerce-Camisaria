package br.unitins.books.controller;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.books.dao.CamisaDAO;
import br.unitins.books.model.Camisa;

@Named
@ViewScoped
public class CamisaController extends Controller<Camisa> {

	private static final long serialVersionUID = 1651642114811762868L;
	
	public CamisaController() {
		super(new CamisaDAO());
		Flash flash = FacesContext.getCurrentInstance().
				getExternalContext().getFlash();
		flash.keep("flashCamisa");
		entity = (Camisa) flash.get("flashCamisa");
	}
	
	public String voltar() {
		return "consultacamisa.xhtml?faces-redirect=true";
		
	}
	
	@Override
	public Camisa getEntity() {
		if (entity == null)
			entity = new Camisa();
		return entity;
	}
	
}
