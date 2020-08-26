package br.com.crudconsulta.dao;

import br.com.crudconsulta.model.*;
import java.sql.*;

public class DAOEmpresas {
	StringBuilder sb;
	
	public void salvar(EmpresasModel empresa) {
		sb = new StringBuilder();
		sb.append("INSERT INTO T_EMPRESAS ");
		sb.append("(CNPJ, NOME, ENDERECO, NUMERO, ");
		sb.append("COMPLEMENTO, MUNICIPIO, UF, CEP, ");
		sb.append("TELEFONE, EMAIL, STATUS) VALUES (");
		sb.append("'" + empresa.getCnpj() + "', ");
		sb.append("'" + empresa.getNome() + "', ");
		sb.append("'" + empresa.getEndereco() + "', ");
		sb.append("'" + empresa.getNumero() + "', ");
		sb.append("'" + empresa.getComplemento() + "', ");
		sb.append("'" + empresa.getMunicipio() + "', ");
		sb.append("'" + empresa.getUf() + "', ");
		sb.append("'" + empresa.getCep() + "', ");
		sb.append("'" + empresa.getTelefone() + "', ");
		sb.append("'" + empresa.getEmail() + "', ");
		sb.append("'1')");
		new DAOAccessManager().executaQuery(String.valueOf(sb));
	}
	
	public ResultSet verificarSeExiste(String cnpj) {
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_EMPRESAS ");
		sb.append("WHERE CNPJ = '" + cnpj.replaceAll("\\D", "") + "'");
		return new DAOAccessManager().retornaQuery(String.valueOf(sb));
	}
}