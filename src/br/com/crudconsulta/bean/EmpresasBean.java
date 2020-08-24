package br.com.crudconsulta.bean;

import br.com.crudconsulta.model.*;
import br.com.crudconsulta.dao.*;
import br.com.crudconsulta.dto.*;
import javax.faces.bean.*;
import org.primefaces.*;

@ManagedBean
@SessionScoped
public class EmpresasBean {
	public EmpresasDTO empresasDTO = new EmpresasDTO();
	
	public void salvarEmpresa() {
		EmpresasModel empresa = new EmpresasModel();
		empresa.setNome(empresasDTO.getNome());
		empresa.setEndereco(empresasDTO.getEndereco());
		empresa.setNumero(empresasDTO.getNumero());
		empresa.setComplemento(empresasDTO.getComplemento());
		empresa.setMunicipio(empresasDTO.getMunicipio());
		empresa.setUf(empresasDTO.getUf());
		empresa.setCep(empresasDTO.getCep());
		empresa.setTelefone(empresasDTO.getTelefone());
		empresa.setEmail(empresasDTO.getEmail());
		new DAOEmpresas().salvar(empresa);
		PrimeFaces.current().executeScript("alertEmpresaSalva()");
		limparDTO();
	}
	
	private void limparDTO() {
		empresasDTO.setNome("");
		empresasDTO.setEndereco("");
		empresasDTO.setNumero("");
		empresasDTO.setComplemento("");
		empresasDTO.setMunicipio("");
		empresasDTO.setUf("");
		empresasDTO.setCep("");
		empresasDTO.setTelefone("");
		empresasDTO.setEmail("");
	}
	
	public EmpresasDTO getEmpresasDTO() {
		return empresasDTO;
	}
	
	public void setEmpresasDTO(EmpresasDTO empresasDTO) {
		this.empresasDTO = empresasDTO;
	}
}