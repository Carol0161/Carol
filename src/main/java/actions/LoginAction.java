package src.main.java.actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String nombre;
	private String usuario;
	private String password;
	

	Connection conn = null; // conexion a BD
	
	// metodo execute
	public String execute() {
		setNombre(acceder (getUsuario(), getPassword())); 

		// poenmos el usuario en la sesion
		ServletActionContext.getActionContext().getSession().put("usuario", getUsuario());
		
		// actualizo el dato de que ha ido bien en la sesion
		ServletActionContext.getActionContext().getSession().put("datosAnyadidos", "NO");
		
		if (getNombre()!=null)
			return SUCCESS;
		else return ERROR;
		
	}
	
	private Connection getConexion() {


			try {
				// comprobar que no existe una conexi�n anterior abierta
				if (conn == null || conn.isClosed()) {
					
					Class.forName("com.mysql.jdbc.Driver"); // es necesario en los Action
					String connectionUrl = "jdbc:mysql://localhost:3306/empresa?&serverTimezone=UTC";
					String user = "root";
					String pass = "Carol%01";
		
					conn = DriverManager.getConnection(connectionUrl, user, pass);
				}
				return conn;

			} catch (Exception ex) {
				System.out.println("Error." + ex.getMessage());
				ex.printStackTrace();
				return null;
			} finally {

			}
		}
	
	private String acceder (String usuario, String password) {


		Statement st = null;
		ResultSet rs = null;
		String nombre= null;

		try {//si no hay conexi�n disponible, pido crearla.
		if (conn == null)
			conn = getConexion();

		// Ejecutamos una sentencia SELECT
		st = conn.createStatement();

		rs = st.executeQuery("select * from Usuarios where usuario = '" + usuario + "'" );


		while (rs.next()) {
			if ( rs.getString(2).trim().equals(password.trim()))
					nombre=  rs.getString(3);

		}
		} catch (Exception e) {
			// Mostrar error
			System.out.println("Error." + e.getMessage());
		} finally {
			try {
				// Cerrar conexi�n y resto de recursos
				conn.close();
				st.close();
				rs.close();
				// devolver resultado
				return nombre;
			} catch (Exception e2) {
				// Si hay excepci�n 
				return nombre;
			}
		}
	}

	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
