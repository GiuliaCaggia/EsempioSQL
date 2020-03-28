package it.polito.tdp.esempioSQL.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import it.polito.tdp.esempioSQL.db.BabsDAO;

public class LeggiBabs {

	public void run() {

		String jdbcURL = "jdbc:mysql://localhost/babs?user=root&password=root"; // passo 1

		Connection conn;
		try {
			conn = DriverManager.getConnection(jdbcURL);

			//Statement st = conn.createStatement();
			//ResultSet res = st.executeQuery(sql);

			String sql = "SELECT NAME FROM station  ";

			String sql2= "SELECT NAME FROM station WHERE landmark = ? ";  //non basta: devo definire il parametro
			
			//PREPAREDSTATEMENT
			
			PreparedStatement st = conn.prepareStatement(sql);
			
			//ResultSet res = st.executeQuery();

			PreparedStatement st2 = conn.prepareStatement(sql2);
			
			st2.setString(1, "Palo Alto");
			
			ResultSet res2 = st2.executeQuery();



			/*while (res.next()) {
				String nomeStazione = res.getString("name");
				System.out.println(nomeStazione);

			}*/
			
			while (res2.next()) {
				String nomeStazione = res2.getString("name");
				System.out.println(nomeStazione);

			}
			st.close();
			st2.close();
			
		//	Statement st2 = conn.createStatement();
			
			
			conn.close();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		// pattern di programmazione di tipo FACTORY: creazione di una classe senza
		// sapere il tipo della classe
		// (non potevo usare new)
		// uso un metodo fornito da un'altra classe che internamente farà la new e
		// conoscerà il tipo
		// di classe effettivo.

		/*
		 * BabsDAO dao = new BabsDAO() ;
		 * 
		 * List<Station> tutte = dao.listStation() ;
		 * 
		 * for(Station s: tutte) { System.out.println(s.getName()) ; }
		 * 
		 * System.out.println("----") ; List<Station> paloAlto =
		 * dao.listStationByLandmark("Palo Alto") ; for(Station s: paloAlto) {
		 * System.out.println(s.getName()) ; }
		 */

	}

	public static void main(String args[]) {
		LeggiBabs babs = new LeggiBabs();
		babs.run();
	}

}
