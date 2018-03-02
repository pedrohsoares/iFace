import model.RedeRelacionamento;
import model.Usuario;

public class iFace {
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		Usuario usuarioAtivo = null;
		RedeRelacionamento redeRelacionamento = new RedeRelacionamento();
		int opcao;
		
		do {
			opcao = menu.principal(usuarioAtivo);
			
			switch(opcao){
				case 1:
					if(usuarioAtivo == null){
						usuarioAtivo = menu.entrar(redeRelacionamento);
					}else{
						
					}
					break;
				case 2:
					if(usuarioAtivo == null){
						Usuario novoUsuario = menu.cadastrarUsuario();
						redeRelacionamento.adicionarUsuario(novoUsuario);
						usuarioAtivo = novoUsuario;
					}else{
						menu.adicionarAmigo(usuarioAtivo,redeRelacionamento);
					}
					
					break;
				case 3:
					if(usuarioAtivo == null){
						menu.reativarConta(redeRelacionamento);
					}else{
						menu.visualizarPedidosAmizade(usuarioAtivo,redeRelacionamento);
					}
					
					break;
				case 4:
					menu.enviarMensagem(usuarioAtivo, redeRelacionamento);
					break;
				case 5:
					menu.visualizarMensagens(usuarioAtivo);
			}
			
		}while(opcao != 0);
		
	}
	
}
