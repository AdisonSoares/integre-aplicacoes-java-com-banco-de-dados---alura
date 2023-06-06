package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.spring.data.service.CrudCargoService;
import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.CrudUnidadeTrabalhoService;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	private final CrudCargoService cargoService;
	private final CrudFuncionarioService funcionarioService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;

	public SpringDataApplication(CrudCargoService cargoService, CrudFuncionarioService funcionarioService,
			CrudUnidadeTrabalhoService unidadeTrabalhoService) {

		this.cargoService = cargoService;
		this.funcionarioService = funcionarioService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (this.system) {
			System.err.println("Qual ação deseja executar:");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionario");

			Integer action = scanner.nextInt();

			switch (action) {
			case 1:
				cargoService.inicial(scanner);
				break;
			case 2:
				unidadeTrabalhoService.inicial(scanner);
				break;
			case 3:
				funcionarioService.inicial(scanner);
				break;
			default:
				System.out.println("Finalizando");
				system = false;
				break;
			}

		}

	}
}