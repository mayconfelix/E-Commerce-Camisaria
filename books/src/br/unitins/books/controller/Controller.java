package br.unitins.books.controller;

import java.io.Serializable;

import br.unitins.books.application.Util;
import br.unitins.books.dao.DAO;
import br.unitins.books.model.Entity;

public abstract class Controller <T extends Entity<T>> implements Serializable {

	private static final long serialVersionUID = -2022582742025295921L;
	
	protected T entity = null;
	protected DAO<T> dao = null;
	
	public Controller(DAO<T> dao) {
		super();
		this.dao = dao;
	}
	
	public abstract T getEntity();
	
	public void setEntity(T entity) {
		this.entity = entity;
	}

	public String incluir() {
		if (validarDados()) {
			if (dao.create(getEntity())) {
				limpar();
				Util.addInfoMessage("Inclus�o realizada com sucesso.");
				return "consultacamisa.xhtml?faces-redirect=true";

			} else {
				Util.addInfoMessage("Erro ao incluir no banco de dados.");
			}
		}
		return null;
		
	}

	public void alterar() {
		if (validarDados()) {		
			if (dao.update(getEntity())) {
				limpar();
				Util.addInfoMessage("Alteração realizada com sucesso.");
			} else {
				Util.addInfoMessage("Erro ao alterar no banco de dados.");
			}
		}
	}

	public void remover() {
		if (dao.delete(getEntity().getId())) {
			limpar();
			Util.addInfoMessage("Remoção realizada com sucesso.");
		} else {
			Util.addInfoMessage("Erro ao remover no banco de dados.");
		}
	}

	public void editar(T entity) {
		entity = dao.findById(entity.getId());
		setEntity(entity);
	}
	
	public boolean validarDados() {
		return true;
	}
	
	public void limpar() {
		entity = null;
	}
	

}