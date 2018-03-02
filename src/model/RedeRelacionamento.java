package model;

import java.util.ArrayList;

public class RedeRelacionamento {
	private ArrayList<Usuario> listUsuarios;
	private ArrayList<Comunidade> listComunidades;
	private ArrayList<Usuario> listUsuariosRemovidos;

	public RedeRelacionamento() {
		this.listUsuarios = new ArrayList<Usuario>();
		this.listComunidades = new ArrayList<Comunidade>();
		this.listUsuariosRemovidos = new ArrayList<Usuario>();
	}

	public Usuario getUsuario(String email, String senha){
		for(Usuario usuario : this.listUsuarios){
			if(usuario.getEmail().equals(email)){
				if(usuario.getSenha().equals(senha))
					return usuario;
				return null;
			}
		}
		
		return null;
	}
	
	public Usuario getUsuario(String email){
		for(Usuario usuario : this.listUsuarios){
			if(usuario.getEmail().equals(email)){
					return usuario;
			}
		}
		
		return null;
	}
	
	public ArrayList<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(ArrayList<Usuario> listUsuarios) {
		this.listUsuarios = listUsuarios;
	}	
	
	public ArrayList<Comunidade> getListComunidades() {
		return listComunidades;
	}

	public void setListComunidades(ArrayList<Comunidade> listComunidades) {
		this.listComunidades = listComunidades;
	}

	public ArrayList<Usuario> getListUsuariosRemovidos() {
		return listUsuariosRemovidos;
	}

	public void setListUsuariosRemovidos(ArrayList<Usuario> listUsuariosRemovidos) {
		this.listUsuariosRemovidos = listUsuariosRemovidos;
	}

	public boolean adicionarUsuario(Usuario novoUsuario){
		for(Usuario usuario : this.listUsuarios)
			if(usuario.getEmail().equals(novoUsuario.getEmail()))
				return false;
		
		
		this.listUsuarios.add(novoUsuario);
		return true;
	}
	
	public boolean removerUsuario(Usuario usuario){
		this.listUsuarios.remove(usuario);
		this.listUsuariosRemovidos.add(usuario);
		
		for(Usuario iterator : this.listUsuarios){
			if(iterator.getListAmigos().contains(usuario)){
				iterator.getListAmigos().remove(usuario);
				for(Mensagem mensagem : iterator.getListMensagens()){
					if(mensagem.getUsuario().equals(usuario))
						iterator.getListMensagens().remove(mensagem);
				}
			}
		}
		
		for(Comunidade comunidade : this.listComunidades){
			if(comunidade.getListParticipantes().contains(usuario))
				comunidade.getListParticipantes().remove(usuario);
		}
		
		return true;
	}
	
	public boolean reativarUsuario(Usuario usuario){
		if(this.listUsuariosRemovidos.contains(usuario)){
			this.listUsuariosRemovidos.remove(usuario);
			this.listUsuarios.add(usuario);
			
			for(Usuario iterator : this.listUsuarios){
				if(usuario.getListAmigos().contains(iterator))
					iterator.getListAmigos().add(usuario);
			}
			
			for(Comunidade iterator : this.listComunidades){
				if(usuario.getListComunidades().contains(iterator))
					iterator.getListParticipantes().add(usuario);
			}
		}
		
		return false;
	}
	
	public void adicionarComunidade(Comunidade comunidade){
		this.listComunidades.add(comunidade);
	}
	
	public void enviarMensagem(Usuario usuario, String mensagem, Usuario destinatario){
		for(Usuario iterator : this.listUsuarios) {
			if(iterator.equals(destinatario)){
				Mensagem novaMensagem = new Mensagem(usuario, mensagem);
				iterator.getListMensagens().add(novaMensagem);
				break;
			}
		}
	}
	
	public void enviarMensagem(Usuario usuario, String mensagem, Comunidade destinatario){
		for(Comunidade iterator : this.listComunidades) {
			if(iterator.equals(destinatario)){
				Mensagem novaMensagem = new Mensagem(usuario, mensagem);
				iterator.getListMensagens().add(novaMensagem);
				break;
			}
		}
	}
	
	public boolean adicionarAmigo(Usuario usuarioAtivo, String email){
		for(Usuario iterator : listUsuarios){
			if(iterator.getEmail().equals(email)){
				iterator.getListSolicitacaoAmizade().add(usuarioAtivo);
				return true;
			}
		}
		
		return false;
	}
	
}
