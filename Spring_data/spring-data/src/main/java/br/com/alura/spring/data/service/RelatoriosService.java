package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {
	
	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	private Boolean system = true;
	
	public void inicial(Scanner scanner) {
		while (this.system) {
			System.out.println("Qual ação de busca deseja executar:");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionário por nome");
			System.out.println("2 - Buscar funcionário por nome, salário e data de entrada");
			System.out.println("3 - Buscar funcionário por data de entrada");
			System.out.println("4 - Buscar funcionário por salário");

			int action = scanner.nextInt();

			switch (action) {
			case 1: 
				buscarPorNome(scanner);
				break;
			case 2: 
				buscarPorNomeSalarioData(scanner);
				break;
			case 3: 
				buscarPorData(scanner);
				break;
			case 4: 
				buscarTodos();
				break;
			default:
				system = false;
				break;
			}
		}
	}

	private void buscarTodos() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(funcionario -> System.out.println("Funcionário id: "+funcionario.getId()
				+" - nome: "+funcionario.getNome()+" - salario: "+funcionario.getSalario()));
		
	}

	private void buscarPorNome(Scanner scanner) {
		System.out.print("Digite o nome do funcionário: ");
		String nomeFuncionario = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nomeFuncionario);
		list.forEach(System.out::println);
	}
	
	private void buscarPorNomeSalarioData(Scanner scanner) {
		System.out.print("Digite o nome do funcionário: ");
		String nomeFuncionario = scanner.next();
		
		System.out.print("Digite o salário do funcionário: ");
		Double salarioFuncionario = scanner.nextDouble();
		
		System.out.print("Digite a data de entrada do funcionário: ");
		String dataEntrada = scanner.next();
		LocalDate localDate = LocalDate.parse(dataEntrada, formatter);
		
		List<Funcionario> list = funcionarioRepository.
				findNomeSalariMaiorDataContratacao(nomeFuncionario, salarioFuncionario, localDate);
		list.forEach(System.out::println);
		
	}
	
	private void buscarPorData(Scanner scanner) {
		System.out.print("Digite a data de entrada do funcionário: ");
		String dataEntrada = scanner.next();
		LocalDate localDate = LocalDate.parse(dataEntrada, formatter);
		List<Funcionario> list = funcionarioRepository.
				findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
		
	}

}
