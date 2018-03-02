

import java.util.Scanner;

import model.RedeRelacionamento;
import model.Usuario;

public class Menu {
	
	public Usuario entrar(RedeRelacionamento redeRelacionamento){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe seu email: ");
		String email = input.nextLine();
		
		System.out.println("Informe sua senha: ");
		String senha = input.nextLine();
		
		return redeRelacionamento.getUsuario(email, senha);
	}
	
	public Usuario cadastrarUsuario(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe o seu email: ");
		String email = input.nextLine();
		
		System.out.println("Informe sua senha: ");
		String senha = input.nextLine();
		
		System.out.println("Informe seu nome: ");
		String nome = input.nextLine();
		
		Usuario novoUsuario = new Usuario(email,senha,nome);
		return novoUsuario;
	}
	
	public void adicionarAmigo(Usuario usuarioAtivo, RedeRelacionamento redeRelacionamento){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe o email do amigo que deseja adicionar: ");
		String email = input.nextLine();
		
		boolean flag = redeRelacionamento.adicionarAmigo(usuarioAtivo, email);
		
		if(flag)
			System.out.println("Solicitacao de amizade enviada com sucesso!");
		else
			System.out.println("Nao foi possivel enviar o pedido de amizade!");
	}
	
	public void reativarConta(RedeRelacionamento redeRelacionamento){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Informe o email da sua conta desativada:");
		String email = input.nextLine();
		
		System.out.println("Informe a senha da sua conta desativada:");
		String senha = input.nextLine();
		
		redeRelacionamento.reativarUsuario(redeRelacionamento.getUsuario(email, senha));
	}
	
	public void visualizarPedidosAmizade(Usuario usuarioAtivo, RedeRelacionamento redeRelacionamento){
		Scanner input = new Scanner(System.in);
		boolean flag = true;
		
		System.out.println("Pedidos de Amizade: ");
		for(Usuario usuario : usuarioAtivo.getListSolicitacaoAmizade()){
			System.out.println("Nome: " + usuario.getNome() + "\tEmail: " + usuario.getEmail());
		}
		
		while(flag){
			System.out.println("Deseja aceitar algum pedido de amizade? Se sim, digite o email da pessoa deseja: (Caso não deseje digite '0')");
			String email = input.nextLine();
			
			if(email.equals('0'))
				flag = false;
			else{
				usuarioAtivo.aceitarSolicitacaoAmizade(redeRelacionamento.getUsuario(email));
			}
			
		}
	}
	
	public void enviarMensagem(Usuario usuarioAtivo, RedeRelacionamento redeRelacionamento){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Digite a mensagem que você deseja enviar:");
		String mensagem = input.nextLine();
		
		System.out.println("Voce deseja enviar para: \n\t1. Amigo\n\t2. Comunidade");
		System.out.println("Digite a opcao desejada:");
		int opcao = input.nextInt();
		
		while(opcao < 1 || opcao > 2){
			System.out.println("Opcao invalida, digite novamente: ");
			opcao = input.nextInt();
		}
		
		if(opcao == 1){
			System.out.println("Digite o email do usuario que deseja enviar a mensagem: ");
			String emailDestinatario = input.nextLine();
			
			redeRelacionamento.enviarMensagem(usuarioAtivo, mensagem, redeRelacionamento.getUsuario(emailDestinatario));
		}else{
			System.out.println("Digite o nome da comunidade que deseja enviar a mensagem: ");
			String nomeComunidade = input.nextLine();
			
			redeRelacionamento.enviarMensagem(usuarioAtivo, mensagem, usuarioAtivo.getComunidadeByName(nomeComunidade));
		}
	}
	
	public void visualizarMensagens(Usuario usuarioAtivo){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Voce deseja visualizar as mensagens de:");
		System.out.println("\t1. Usuario\n\t2.Comunidade");
		System.out.println("Digite a opcao desejada:");
		int opcao = input.nextInt();
		
		while(opcao < 1 || opcao > 2){
			System.out.println("Opcao invalida, digite novamente: ");
			opcao = input.nextInt();
		}
		
		if(opcao == 1){
			System.out.println("Digite o email do usuario:");
			String email = input.nextLine();
		}else{
			System.out.println("Digite o nome da comunidade:");
			String nome = input.nextLine();
		}
	}
	
	public int principal(Usuario usuario){
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		int opcao = 0;
		
		System.out.println("\t\tiFace");
		System.out.println("\n------------ MENU PRINCIPAL --------------- \n");
		
		if(usuario == null){
			System.out.println("\t1. Entrar com sua conta");
			System.out.println("\t2. Criar uma nova conta");
			System.out.println("\t3. Reativar sua conta");
		}else{
			System.out.println("Bem vindo, " + usuario.getNome());
			
			System.out.println("\t1. Criar/Editar Perfil");
			System.out.println("\t2. Adicionar Amigo");
			System.out.println("\t3. Visualizar Pedidos de Amizade");
			System.out.println("\t4. Enviar Mensagem");
			System.out.println("\t5. Visualizar Mensagens");
			System.out.println("\t6. Criar comunidade");
			System.out.println("\t7. Entrar em uma comunidade");
			System.out.println("\t8. Apagar sua conta");
		}
		
		System.out.println("\t0. Sair");
		
		while(!flag){
			System.out.println("Informe a opcao desejada: ");
			opcao = input.nextInt();
			
			if((opcao >= 0 && opcao <= 6 && usuario != null) || (opcao >= 0 && opcao <= 3))
				flag = true;
			else
				System.out.println("Opcao invalida!");
		}
		
		return opcao;
		
	}
	
}
