package br.com.alura.spring.data.orm;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cargo_id", nullable = false)
	private Cargo cargo;
	
	@Fetch(FetchMode.SELECT)
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "funcionarios_unidades", joinColumns = {
			@JoinColumn(name = "fk_funcionario") }, 
	inverseJoinColumns = { @JoinColumn(name = "fk_unidade") })
	private List<UnidadeTrabalho> unidadeTrabalhos;
	
	private String nome;
	private String cpf;
	private double salario;
	private LocalDate dataContratacao;
	
	@Override
	public String toString() {
		return "Funcionario{" +
				"id=" + id +
				", cargo=" + cargo +
				", nome='" + nome + '\'' +
				", cpf='" + cpf + '\'' +
				", salario=" + salario +
				", dataContratacao=" + dataContratacao +
				'}';
	}
	
	
	
}
























