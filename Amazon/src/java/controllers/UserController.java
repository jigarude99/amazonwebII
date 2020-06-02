package controllers;

import models.*;
import helpers.PropertiesReader;
import helpers.PoolManager;

import java.sql.*;
import java.io.IOException;

/**
 * @author Ptthappy
 */

@SuppressWarnings("Duplicates")
public class UserController {
	private static PoolManager poolManager = PoolManager.getPoolManager();
	private static PropertiesReader prop = PropertiesReader.getInstance();

	public static Response<User> login(User user) {
		Connection con = poolManager.getConn();
		Response<User> response = new Response<>();
		String query = prop.getValue("login");
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				getUserData(rs, user);
				response.setStatus(200);
				response.setMessage("Acceso concedido");
				response.setData(user);
			}
			else {
				response.setStatus(401);
				response.setMessage("Credenciales no validas, intente de nuevo");
			}
		} catch (SQLException e) {
			response.setStatus(500);
			response.setMessage("Error en DB");
			e.printStackTrace();
		}
		poolManager.returnConn(con);
		return response;
	}

	public static Response<User> register(User user) throws IOException {
		Connection con = poolManager.getConn();
		Response<User> response = new Response<>();
		String query = prop.getValue("registerUser");
		if(checkUsername(user.getUsername())) {
			response.setStatus(409);
			response.setMessage("Nombre de usuario en uso");
			poolManager.returnConn(con);
			return response;
		}
		try {
			PreparedStatement pstmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getCedula());
			pstmt.setString(5, user.getPhone());
			pstmt.execute();
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));
			response.setStatus(200);
			response.setMessage("Usuario registrado de manera exitosa");
			user.setPassword(null);
			response.setData(user);
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setMessage("Error en DB");
		}
		poolManager.returnConn(con);
		return response;
	}

	private static void getUserData(ResultSet rs, User user) throws SQLException {
	    user.setId(rs.getInt(1));
		user.setUsername(rs.getString("username"));
		user.setName(rs.getString("name"));
		user.setCedula(rs.getString("cedula"));
		user.setPhone(rs.getString("phone"));
		user.setPassword(null);
	}

	public static boolean checkUsername(String username) {
		Connection con = poolManager.getConn();
		String query = prop.getValue("checkUsername");
		try {
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				poolManager.returnConn(con);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			poolManager.returnConn(con);
		}
		return false;
	}

	public static Response<User> modifyUser(User user) {
		Connection con = poolManager.getConn();
		Response<User> response = new Response<>();
		String query = prop.getValue("updateUser");
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, user.getName());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getCedula());
			ps.setString(4, user.getPhone());
			ps.setInt(5, user.getId());
			ps.setString(6, user.getPassword());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 1) {
				response.setStatus(200);
				response.setMessage("Usuario actualizado de manera exitosa");
				response.setData(user);
			} else {
				response.setStatus(401);
				response.setMessage("Credenciales invalidas");
				response.setData(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setMessage("Error DB");
			response.setData(user);
		}
		poolManager.returnConn(con);
		return response;
	}
            public static Response<User> deleteUser(User user) {
		Connection con = poolManager.getConn();
		Response<User> response = new Response<>();
		String query = prop.getValue("deleteUser");
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getPassword());
			int affectedRows = ps.executeUpdate();
			if(affectedRows == 1) {
				response.setStatus(200);
				response.setMessage("Usuario eliminado de manera exitosa");
				response.setData(user);
			} else {
				response.setStatus(401);
				response.setMessage("Credenciales invalidas");
				response.setData(null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(500);
			response.setMessage("Error DB");
			response.setData(user);
		}
		poolManager.returnConn(con);
		return response;
	}
}
