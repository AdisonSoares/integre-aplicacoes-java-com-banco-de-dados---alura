package br.com.alura.loja.modelo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CategoriaId id;

	public Categoria(String nome) {
		this.id = new CategoriaId(nome, "tipo");
	}
	
	public String getNome() {
		return this.id.getNome();
	}

}
