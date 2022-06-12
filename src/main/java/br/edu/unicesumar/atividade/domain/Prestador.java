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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Prestador {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String razaoSocial;
	
	private String nomeFantasia;
	
	private String cnpj;
	
	private String inscricaoEstadual;
	
	private String telefone;
	
	private String email;
	
	private String tipoServico;
	
	@ManyToOne
    @JoinColumn(name = "endereco_id")
	private Prestador prestador;

}
