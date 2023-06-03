package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livro")
public class Livro extends Produto{
	private String autor;
	private Integer numeroDePaginas;
}
