package br.com.crudconsulta.dao;

import com.thoughtworks.xstream.io.xml.*;
import br.com.crudconsulta.model.*;
import com.thoughtworks.xstream.*;
import br.com.crudconsulta.util.*;
import java.sql.*;
import java.io.*;

public class DAOAccessManager {
	public Connection openConnection() {
		Connection con = null;
		XStream xstream = new XStream(new DomDriver());
		InputStream inputstream = Utils.getConfigFile("configDao.xml");
		xstream.autodetectAnnotations(true);
		xstream.alias("access", ConfigDaoModel.class);
		ConfigDaoModel infos = (ConfigDaoModel) xstream.fromXML(inputstream);
		try {
			Class.forName(infos.getDriver());
			con = (Connection) DriverManager.getConnection(infos.getAcesso() + infos.getLogin() + infos.getSenha());
		} catch(ClassNotFoundException e) {
			e.getMessage();
		} catch(SQLException e) {
			e.getMessage();
		}
		return con;
	}
	
	public ResultSet retornaQuery(String query) {
		ResultSet rs = null;
		try {
			Connection con = openConnection();
			PreparedStatement st = con.prepareStatement(query);
			rs = st.executeQuery();
		} catch(SQLException e) {
			e.getMessage();
		}
		return rs;
	}
	
	public void executaQuery(String query) {
		Connection con = openConnection();
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.execute();
		} catch(SQLException e) {
			e.getMessage();
		}
	}
}