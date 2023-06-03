package br.com.alura.loja.modelo;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class CategoriaId implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nome;
	private String tipo;
}
