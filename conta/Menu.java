package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {


	public static void main(String[] args) {
		
		
		
		ContaController contas = new ContaController();
		
		Scanner leia = new Scanner (System.in);
		
		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		System.out.println("\nCriar contas\n");
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(),123, 1, "João da Silva", 1000f,100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(),124, 1, "Maria da Silva", 2000f,100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca (contas.gerarNumero(),125, 2, "Mariana Santos", 4000f,12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca (contas.gerarNumero(),126, 2, "Julia Ramos", 8000f,15);
		contas.cadastrar(cp2);
		
		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND);
					
			System.out.println(" __________________________________________________ ");
			System.out.println("|                                                  |");
			System.out.println("|               BANCO DO BRAZIL                    |");
			System.out.println("|                                                  |");
			System.out.println("|__________________________________________________|");
			System.out.println("|                                                  |");
			System.out.println("|              1 - Criar conta                     |");
			System.out.println("|              2 - Listar todas as contas          |");
			System.out.println("|              3 - Buscar conta por número         |");
			System.out.println("|              4 - Atualizar dados da conta        |");
			System.out.println("|              5 - Deletar conta                   |");
			System.out.println("|              6 - Sacar                           |");
			System.out.println("|              7 - Depositar                       |");
			System.out.println("|              8 - Transferir valores entre contas |");
			System.out.println("|              9 - Sair                            |");
			System.out.println("|                                                  |");
			System.out.println("|__________________________________________________|");
			System.out.println("|            Entre com a opção desejada            |");
			System.out.println("|__________________________________________________|" + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE + "\n Criar Conta");
				System.out.println("Digite o numero da agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
			do {
				System.out.println("Digite o tipo da conta (1-CC ou 2-CP): ");
				tipo = leia.nextInt();
			}while(tipo < 1 && tipo > 2);
			
			System.out.println("Digite o Saldo da Conta (R$): ");
			saldo = leia.nextFloat();
			
		switch(tipo) {
		case 1 -> {
			System.out.println("Digite o limite de crédito (R$): ");
			limite = leia.nextFloat();
			contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));		
		}
		case 2 -> {
			System.out.println("Digite o dia do aniversario da conta: ");
			aniversario = leia.nextInt();
			contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
		}
		}
				keyPress();
				break;
			case 2:
				System.out.println("\n Listar todas as Contas");
				contas.listaTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE + "Buscar Conta por número");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				contas.procurarPorNumero(numero);
				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE + "Atualizar dados da Conta");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				if(contas.buscarNaCollection(numero) != null) {
					
					System.out.println("Digite o numero da agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o saldo da conta (R$): ");
					saldo = leia.nextFloat();
					
					tipo = contas.retornarTipo(numero);
					
					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o limite de crédito (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do aniversario da conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca (numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de conta inválido!");
						
					}
					}
				}else
					System.out.println("\nConta não encontrada!");

				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE +"Apagar a Conta\n\n");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				contas.deletar(numero);
				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE + "Saque\n\n");
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do saque (R$): ");
					valor = leia.nextFloat();
				}while (valor <= 0);
				contas.sacar(numero, valor);
				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE + "Depósitar\n\n");
				System.out.println("Digite o numero da conta: ");
				numero = leia.nextInt();
				
				do{
					System.out.println("Digite o valor do depósito (R$): ");
					valor = leia.nextFloat();
				}while (valor <= 0);
				contas.depositar(numero, valor);

				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE +"\n Transferência entre contas\n\n");
				System.out.println("Digite o número da conta de origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o número da conta de destino: ");
				numeroDestino = leia.nextInt();
				do {
					System.out.println("Digite o valor da transferência (R$): ");
					valor = leia.nextFloat();
				}while (valor <= 0);
				contas.transferir(numero,numeroDestino, valor);
				keyPress();
				break;
			default:
				System.out.println("\nOpção Inválida" + Cores.TEXT_RESET);
				
				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	}
}