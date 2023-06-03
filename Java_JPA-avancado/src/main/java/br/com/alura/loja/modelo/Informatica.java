package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "informatica")
public class Informatica extends Produto{
	private String marca;
	private Integer modelo;
}
