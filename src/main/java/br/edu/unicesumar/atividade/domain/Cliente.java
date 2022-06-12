package br.edu.unicesumar.atividade.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	
	private String cpf;
	
	private Integer idade;
	
	private String telefone;
	
	private String email;
	
	private String senha;
		
	private String cep;
	
	private String logradouro;
	
	private Integer numero;
	
	private String bairro;
	
	private String cidade;
	
	private String estado;
	
	
}
