package br.unitins.books.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.unitins.books.application.Util;
import br.unitins.books.dao.UsuarioDAO;
import br.unitins.books.model.Usuario;

@Named
@RequestScoped
public class LoginController {

	private Usuario usuario;
	
	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.verificarLoginSenha(getUsuario().getLogin(),
				Util.hashSHA256(getUsuario().getSenha()));
		
		if (usuario != null)
			return "usuario.xhtml?faces-redirect=true";
		Util.addErrorMessage("Login ou Senha inválido.");
		return "";
	}
	
	public void limpar() {
		usuario = null;
	}
	
	public String cadastrar() {
		return "usuario.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
