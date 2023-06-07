package br.com.alura.spring.data.orm;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "unidade_trabalho")
public class UnidadeTrabalho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;
	
	private String descricao;
	private String endereco;
	
	@Override
	public String toString() {
		return "UnidadeTrabalho{" +
				"id=" + id +
				", descricao='" + descricao + '\'' +
				", endereco='" + endereco + '\'' +
				'}';
	}
		
}























