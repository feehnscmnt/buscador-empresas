package br.com.crudconsulta.bean;

import br.com.crudconsulta.model.*;
import br.com.crudconsulta.dao.*;
import br.com.crudconsulta.dto.*;
import javax.faces.bean.*;
import org.primefaces.*;
import java.sql.*;

@ManagedBean
@SessionScoped
public class EmpresasBean {
	public EmpresasDTO empresasDTO = new EmpresasDTO();
	
	public void salvarEmpresa() {
		DAOEmpresas empresasDAO = new DAOEmpresas();
		try {
			ResultSet rs = empresasDAO.verificarSeExiste(empresasDTO.getCnpj());
			if (rs.next()) {
				PrimeFaces.current().executeScript("alertEmpresaExiste()");
				limparDTO();
				return;
			} else {
				EmpresasModel empresa = new EmpresasModel();
				empresa.setCnpj(empresasDTO.getCnpj());
				empresa.setNome(empresasDTO.getNome());
				empresa.setEndereco(empresasDTO.getEndereco());
				empresa.setNumero(empresasDTO.getNumero());
				empresa.setComplemento(empresasDTO.getComplemento());
				empresa.setMunicipio(empresasDTO.getMunicipio());
				empresa.setUf(empresasDTO.getUf());
				empresa.setCep(empresasDTO.getCep());
				empresa.setTelefone(empresasDTO.getTelefone());
				empresa.setEmail(empresasDTO.getEmail());
				empresasDAO.salvar(empresa);
				PrimeFaces.current().executeScript("alertEmpresaSalva()");
				limparDTO();
			}
		} catch(SQLException e) {
			e.getMessage();
		}
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