package br.com.springmvc.models;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
 
public class Tarefa {
	private Long id;
	
	@NotBlank
	private String tarefa;
	
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date data;
	
	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	private Date hora;
	
	public Tarefa() {
	
	}
	
	public Tarefa(String tarefa, Date data) {
		this.tarefa = tarefa;
		this.data = data;
	}
	
	public Tarefa(Long id, String tarefa, Date data, Date hora) {
		this.id = id;
		this.tarefa = tarefa;
		this.data = data;
		this.hora = hora;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTarefa() {
		return tarefa;
	}

	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

}
