package actions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class GuardarDatosAction extends ActionSupport {

	String  apellido;
	int genero, ciclo, curso, modulo;
	String observaciones;
	String datosAnyadidos = "";
	


	Connection conn = null; // conexion a BD
	
	public String execute() {
		
		if ( anyadirDatos() ) {
			setDatosAnyadidos("OK");
			// poenmos el dato de que ha ido bien en la sesion
			ServletActionContext.getActionContext().getSession().put("datosAnyadidos", "OK");
			return SUCCESS;
		}else return ERROR;
	}
	
	private Connection getConexion() {


		try {
			// comprobar que no existe una conexión anterior abierta
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
	
	private boolean anyadirDatos() {

		PreparedStatement st = null;
		String nombre= null;
		int numFilas = 0;

		try {//si no hay conexión disponible, pido crearla.
		if (conn == null)
			conn = getConexion();

		// Ejecutamos una sentencia SELECT
		st = conn.prepareStatement("update Usuarios set apellido = ? , genero = ?, ciclo = ?, curso =?, modulo =?, observaciones = ? "
									+ " where usuario = ?"  );
		
		st.setString(1, getApellido());
		st.setInt(2, getGenero());
		st.setInt(3, getCiclo());
		st.setInt(4, getCurso());
		st.setInt(5, getModulo());
		st.setString(6, getObservaciones());
		
		
		// sacamos el usuario de la sesion.
		String usuario = (String) ServletActionContext.getActionContext().getSession().get("usuario");

		st.setString(7, usuario);

		
		numFilas = st.executeUpdate();

		} catch (Exception e) {
			// Mostrar error
			System.out.println("Error." + e.getMessage());
		} finally {
			try {
				// Cerrar conexión y resto de recursos
				conn.close();
				st.close();
				// devolver resultado
				return ( numFilas > 0);
			} catch (Exception e2) {
				// Si hay excepción 
				return false;
			}
		}
	}


	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}

	public int getCurso() {
		return curso;
	}

	public void setCurso(int curso) {
		this.curso = curso;
	}

	public int getModulo() {
		return modulo;
	}

	public void setModulo(int modulo) {
		this.modulo = modulo;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String obsevaciones) {
		this.observaciones = obsevaciones;
	}


	public String getDatosAnyadidos() {
		return datosAnyadidos;
	}

	public void setDatosAnyadidos(String datosAnyadidos) {
		this.datosAnyadidos = datosAnyadidos;
	}
	
}
