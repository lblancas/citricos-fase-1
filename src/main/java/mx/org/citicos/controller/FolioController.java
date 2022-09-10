package mx.org.citicos.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.org.citricos.dao.Conexion;
import mx.org.citricos.entity.Corrida;
import mx.org.citricos.entity.Folios;

public class FolioController extends Conexion {
	String fecha;
	String querySelect;
	String queryMax;
	String querySelectAll;
	String queryUpdateNumber;
	String querySelectOne;
	String querySelectOneByFolio;
	String queryUpdate1;
	String queryUpdateMvl1;
	String queryUpdateMvl1_neu;
	String queryUpdate2;
	String queryUpdateMvl2;
	String queryUpdateMvl3;
	String queryUpdate2_1;
	String queryUpdate3;
	String queryUpdate4;
	String queryUpdateMov4;
	String queryrechazarActivo;
	String queryrechazar;
	String queryDelete;
	String queryCreate;
	String queryCreateMvl;
	String queryCreateMv2;
	String updateProductor;
	String queryUpdateMvl2_neutro;
	String queryUpdateMvl_tck1;

	public FolioController() {
		fecha = "  select DATE_FORMAT(NOW(),'%a %d de %M del %Y %h:%m.%s') FECHA ";
		querySelect = "  SELECT TICKET, FECHA_BASC,FECHA_RECE,FECHA_CONF,"
				+ "F.CALIDAD_EMPAQUE,F.ID,F.FOLIO,F.FECHA,F.ID_PRODUCTOR,F.PESO_BRUTO,"
				+ "F.PESO_TARA,F.PESO_NETO, F.NO_REJAS,F.ID_REJAS,F.ID_TLIMON,F.ID_AGRONOMO,"
				+ "F.DEJO,F.OBSERVACIONES, F.SEGUNDAS,F.TERCERAS,F.TORREON,F.COLEADA,F.JAPON, "
				+ "(SELECT A.DESCRIPCION FROM CC_AGRONOMO A WHERE F.ID_AGRONOMO  = A.ID)AGRONOMO,"
				+ " concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) as PRODUCTOR, (SELECT R.DESCRIPCION FROM CC_CAT_REJA R WHERE F.ID_REJAS = R.ID)TIPO_REJAS,"
				+ "(SELECT L.DESCRIPCION FROM CC_CAT_TAMANO_LIMON L WHERE F.ID_TLIMON = L.ID)TIPO_LIMON,"
				+ " F.COMPRADOR,F.FACTURAR"
				+ " FROM CC_FOLIOS F, CC_PRODUCTOR P WHERE F.ID_PRODUCTOR = P.ID  AND F.ID_ACTIVO =1 AND STATUS =";

		querySelectAll = "  SELECT FECHA_BASC,FECHA_RECE,FECHA_CONF,"
				+ "F.CALIDAD_EMPAQUE,F.ID,F.FOLIO,F.FECHA,F.ID_PRODUCTOR,F.PESO_BRUTO,F.PESO_TARA,"
				+ "F.PESO_NETO, F.NO_REJAS,F.ID_REJAS,F.ID_TLIMON,F.ID_AGRONOMO,F.DEJO,F.OBSERVACIONES,  "
				+ "F.SEGUNDAS,F.TERCERAS,F.TORREON,F.COLEADA,F.JAPON,  (SELECT  A.DESCRIPCION "
				+ "FROM CC_AGRONOMO A WHERE F.ID_AGRONOMO  = A.ID)AGRONOMO, concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) as PRODUCTOR, "
				+ "(SELECT R.DESCRIPCION FROM CC_CAT_REJA R WHERE F.ID_REJAS     = R.ID)TIPO_REJAS, "
				+ "(SELECT L.DESCRIPCION FROM CC_CAT_TAMANO_LIMON L WHERE F.ID_TLIMON    = L.ID)TIPO_LIMON "
				+ "FROM CC_FOLIOS F, CC_PRODUCTOR P  WHERE F.ID_PRODUCTOR = P.ID  AND F.ID_ACTIVO =1  ";
		queryMax = "SELECT MAX(ID) ID FROM CC_FOLIOS";
		queryUpdateNumber = "UPDATE CC_FOLIOS SET  FOLIO =DATE_FORMAT(now(), '%Y-%m-%d-%h-%i-%s') WHERE ID = ? ";
		querySelectOne = "SELECT TICKET,FECHA_BASC,FECHA_RECE,FECHA_CONF,F.COMPRADOR,F.FACTURAR,"
				+ "F.CALIDAD_EMPAQUE,F.STATUS, F.ID,F.FOLIO,F.FECHA,F.ID_PRODUCTOR,F.PESO_BRUTO,F.PESO_TARA,F.PESO_NETO,"
				+ "F.NO_REJAS,F.ID_REJAS,F.ID_TLIMON,F.ID_AGRONOMO,F.DEJO,F.OBSERVACIONES,   F.SEGUNDAS,F.TERCERAS,"
				+ "F.TORREON,F.COLEADA,F.JAPON,(SELECT A.DESCRIPCION FROM CC_AGRONOMO A WHERE F.ID_AGRONOMO  = A.ID)AGRONOMO,"
				+ " concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) as PRODUCTOR ,  (SELECT R.DESCRIPCION FROM CC_CAT_REJA R WHERE F.ID_REJAS  = R.ID)TIPO_REJAS,  "
				+ "(SELECT L.DESCRIPCION FROM CC_CAT_TAMANO_LIMON L "
				+ " WHERE F.ID_TLIMON    = L.ID)TIPO_LIMON  FROM CC_FOLIOS F, CC_PRODUCTOR P WHERE F.ID_PRODUCTOR = P.ID and F.ID = ";
		querySelectOneByFolio = "SELECT TICKET,FECHA_BASC,FECHA_RECE,FECHA_CONF,F.COMPRADOR,F.FACTURAR,"
				+ "F.CALIDAD_EMPAQUE,F.STATUS, F.ID,F.FOLIO,F.FECHA,F.ID_PRODUCTOR,F.PESO_BRUTO,F.PESO_TARA,F.PESO_NETO,"
				+ "F.NO_REJAS,F.ID_REJAS,F.ID_TLIMON,F.ID_AGRONOMO,F.DEJO,F.OBSERVACIONES,   F.SEGUNDAS,F.TERCERAS,"
				+ "F.TORREON,F.COLEADA,F.JAPON,(SELECT A.DESCRIPCION FROM CC_AGRONOMO A WHERE F.ID_AGRONOMO  = A.ID)AGRONOMO,"
				+ " concat(concat(concat(p.nombre,' '),concat(p.apellido_p,' ')),concat(p.apellido_m,' ')) as PRODUCTOR ,  (SELECT R.DESCRIPCION FROM CC_CAT_REJA R WHERE F.ID_REJAS  = R.ID)TIPO_REJAS,  "
				+ "(SELECT L.DESCRIPCION FROM CC_CAT_TAMANO_LIMON L " + " WHERE F.ID_TLIMON    = L.ID)TIPO_LIMON  "
				+ " FROM CC_FOLIOS F, CC_PRODUCTOR P " + " WHERE F.ID_PRODUCTOR = P.ID and F.FOLIO  = ";
		queryUpdate1 = "UPDATE CC_FOLIOS SET STATUS=?, ID_PRODUCTOR=?,PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=? WHERE ID = ?";
		queryUpdateMvl1 = "UPDATE CC_FOLIOS SET STATUS=?, ID_PRODUCTOR=?,PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=?, MODIFICADOPOR=? WHERE ID = ?";
		queryUpdateMvl_tck1 = "UPDATE CC_FOLIOS SET STATUS=?, ID_PRODUCTOR=?,PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=?, MODIFICADOPOR=?, TICKET = ? WHERE ID = ?";
		queryUpdateMvl1_neu = "UPDATE CC_FOLIOS SET  ID_PRODUCTOR=?,PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=?, MODIFICADOPOR=? WHERE ID = ?";
		queryUpdate2 = "UPDATE CC_FOLIOS SET STATUS=?, NO_REJAS=?,ID_REJAS=?,ID_TLIMON=?,ID_AGRONOMO=?,DEJO=?,OBSERVACIONES=? ,CALIDAD_EMPAQUE=?WHERE ID = ?";
		queryUpdateMvl2 = "UPDATE CC_FOLIOS SET STATUS=?, NO_REJAS=?,ID_REJAS=?,ID_TLIMON=?,ID_AGRONOMO=?,"
				+ "DEJO=?,OBSERVACIONES=?,MODIFICADOPOR=?,CALIDAD_EMPAQUE=? ,PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=? WHERE ID = ?";
		queryUpdateMvl3 = "UPDATE CC_FOLIOS SET STATUS=?, NO_REJAS=?,ID_REJAS=?,ID_TLIMON=?,ID_AGRONOMO=?,DEJO=?,OBSERVACIONES=?,"
				+ "MODIFICADOPOR=?,CALIDAD_EMPAQUE=? ,PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=? , COMPRADOR= ? ,FACTURAR= ?,TICKET=?  WHERE ID = ?";

		queryUpdate2_1 = "UPDATE CC_FOLIOS SET STATUS=?, NO_REJAS=?,ID_REJAS=?,ID_TLIMON=?,DEJO=?,OBSERVACIONES=? WHERE ID = ?";
		queryUpdate3 = "UPDATE CC_FOLIOS SET SEGUNDAS=?,TERCERAS=?,TORREON=?,COLEADA=?,JAPON=? WHERE ID = ?";

		queryUpdate4 = "UPDATE CC_FOLIOS SET PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=?,JAPON=?,SEGUNDAS=?,TERCERAS=?,TORREON=?,COLEADA=?,STATUS=? WHERE ID = ?";
		queryUpdateMov4 = "UPDATE CC_FOLIOS SET PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=?,JAPON=?,SEGUNDAS=?,TERCERAS=?,TORREON=?,COLEADA=?,STATUS=?,MODIFICADOPOR=? WHERE ID = ?";
		updateProductor = "UPDATE CC_FOLIOS SET ID_PRODUCTOR=?  WHERE ID = ?";
		queryrechazarActivo = "UPDATE CC_FOLIOS SET STATUS=0  WHERE ID = ?";
		queryrechazar = "UPDATE CC_FOLIOS SET STATUS=?  WHERE ID = ?";
		queryDelete = "DELETE FROM CC_FOLIOS WHERE ID = ? ";
		queryCreate = "INSERT INTO CC_FOLIOS (ID_PRODUCTOR,PESO_BRUTO,PESO_TARA,PESO_NETO)VALUES (?,?,?,?)";
		queryCreateMvl = "INSERT INTO CC_FOLIOS (ID_PRODUCTOR,PESO_BRUTO,PESO_TARA,PESO_NETO,CREADOPOR,MODIFICADOPOR,folio)VALUES (?,?,?,?,?,?,DATE_FORMAT(now(), '%Y-%m-%d-%h-%i-%s'))";
		queryCreateMv2 = "INSERT INTO CC_FOLIOS (ID_PRODUCTOR,PESO_BRUTO,PESO_TARA,PESO_NETO,CREADOPOR,MODIFICADOPOR,folio,ticket)VALUES (?,?,?,?,?,?,DATE_FORMAT(now(), '%Y-%m-%d-%h-%i-%s'),?)";
		queryUpdateMvl2_neutro = "UPDATE CC_FOLIOS SET   NO_REJAS=?,ID_REJAS=?,ID_TLIMON=?,ID_AGRONOMO=?,"
				+ "DEJO=?,OBSERVACIONES=?,MODIFICADOPOR=?,CALIDAD_EMPAQUE=? ,PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=?,TICKET=? WHERE ID = ?";
	}

	public void updateRecord(String fecha, int opc, int id) {
		String update_basc = "UPDATE CC_FOLIOS SET fecha_basc=? WHERE ID = ?";
		String update_rece = "UPDATE CC_FOLIOS SET fecha_rece=? WHERE ID = ?";
		String update_conf = "UPDATE CC_FOLIOS SET fecha_conf=? WHERE ID = ?";
		String update = "";
		Connection connection = null;
		switch (opc) {
		case 1:
			update = update_basc;
			break;
		case 2:
			update = update_rece;
			break;
		case 3:
			update = update_conf;
			break;
		default:
			update = update_basc;
			break;
		}
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(update);
			if (opc <= 4) {
				ps.setString(1, fecha);
				ps.setInt(2, id);
			}
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	public String getImpresora(int user) {
		String imprimir = "";
		Connection connection = null;
		try {
			connection = get_connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select impresora from cc_usuario where id =" + user);
			if (rs.next()) {
				imprimir = rs.getString("impresora");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return imprimir;
	}

	public boolean impresionXusuario(int user) {
		boolean imprimir = false;
		Connection connection = null;
		try {
			connection = get_connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select imprimir from cc_usuario where id =" + user);
			if (rs.next()) {
				imprimir = rs.getBoolean("imprimir");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return imprimir;
	}

	public String getFecha() {
		String fechaCadena = "";
		Connection connection = null;
		try {
			connection = get_connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(fecha);
			if (rs.next()) {
				fechaCadena = rs.getString("FECHA");
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return fechaCadena;
	}

	public Folios getMax() {
		Folios bean = new Folios();
		Connection connection = null;
		try {
			connection = get_connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(queryMax);
			if (rs.next()) {
				bean = getOne(rs.getInt("ID"));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return bean;
	}

	public Folios getOne(String folio) {
		Folios bean = new Folios();
		Connection connection = null;
		try {
			connection = get_connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySelectOneByFolio + " '" + folio + "'");
			if (rs.next()) {
				bean.setId(Integer.valueOf(rs.getInt("ID")));
				bean.setFolio(rs.getString("FOLIO"));
				bean.setTicket(rs.getString("TICKET"));
				bean.setEstatus(Integer.valueOf(rs.getInt("STATUS")));
				bean.setCalidad_empaque(Integer.valueOf(rs.getInt("calidad_empaque")));
				if (bean.getFolio() != null && bean.getFolio().length() > 19) {
					bean.setCodigo(getToken(bean.getFolio()));
				}
				bean.setFecha(rs.getString("FECHA"));
				bean.setId_productor((rs.getInt("ID_PRODUCTOR")));
				bean.setPeso_bruto((rs.getDouble("PESO_BRUTO")));
				bean.setPeso_tara((rs.getDouble("PESO_TARA")));
				bean.setPeso_neto((rs.getDouble("PESO_NETO")));
				bean.setNo_rejas((rs.getDouble("NO_REJAS")));
				bean.setTipo_rejas((rs.getInt("ID_REJAS")));
				bean.setTipo_limon((rs.getInt("ID_TLIMON")));
				bean.setCalidad_empaque((rs.getInt("CALIDAD_EMPAQUE")));
				bean.setId_agronomo((rs.getInt("ID_AGRONOMO")));
				bean.setDejo((rs.getInt("DEJO")));
				bean.setIsdejo(false);
				if (bean.getDejo() == 1)
					bean.setIsdejo(true);
				bean.setObservaciones(rs.getString("OBSERVACIONES"));
				bean.setSegundas((rs.getDouble("SEGUNDAS")));
				bean.setTerceras((rs.getDouble("TERCERAS")));
				bean.setTorreon((rs.getDouble("TORREON")));
				bean.setColeada((rs.getDouble("COLEADA")));
				bean.setJapon((rs.getDouble("JAPON")));
				bean.setProductor(rs.getString("PRODUCTOR"));
				bean.setAgronomo(rs.getString("AGRONOMO"));

				bean.setComprador(rs.getString("COMPRADOR"));
				bean.setFacturar(rs.getString("FACTURAR"));

				bean.setTipos_rejas(rs.getString("TIPO_REJAS"));
				bean.setTipos_limones(rs.getString("TIPO_LIMON"));
				bean.setFechabas(null);
				bean.setFecharec(null);
				bean.setFechacon(null);
				try {
					bean.setFechabas(rs.getString("FECHA_BASC"));
				} catch (Exception ed) {
					System.out.println("Problemas con FECHA Bascula");
				}
				try {
					bean.setFecharec(rs.getString("FECHA_RECE"));
				} catch (Exception ed) {
					System.out.println("Problemas con FECHA Recepcion");
				}
				try {
					bean.setFechacon(rs.getString("FECHA_CONF"));
				} catch (Exception ed) {
					System.out.println("Problemas con FECHA Confirmacion");
				}
				if (bean.getEstatus() == 0 && bean.getFechabas() == null && bean.getFecharec() == null)
					bean.setProceso(0);
				if (bean.getEstatus() == 0 && bean.getFechabas() != null && bean.getFecharec() == null)
					bean.setProceso(1);
				if (bean.getEstatus() == 1 && bean.getFechabas() != null && bean.getFecharec() == null)
					bean.setProceso(2);
				if (bean.getEstatus() == 0 && bean.getFechabas() == null && bean.getFecharec() == null) {
					bean.setProceso(0);
					bean.setBotonBascula("Cambio de estatus a recepción");
					bean.setStatusSiguiente(1);
				}
				if (bean.getEstatus() == 0 && bean.getFechabas() != null && bean.getFecharec() != null) {
					bean.setProceso(1);
					bean.setBotonBascula("Cambio de estatus a confirmación");
					bean.setStatusSiguiente(2);
				}
				if (bean.getEstatus() == 1 && bean.getFechabas() != null && bean.getFecharec() == null) {
					bean.setProceso(2);
				}
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return bean;
	}

	public Folios getOne(int i) {
		Folios bean = new Folios();
		Connection connection = null;
		try {
			connection = get_connection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(querySelectOne + i);
			if (rs.next()) {
				bean.setId(Integer.valueOf(rs.getInt("ID")));
				bean.setFolio(rs.getString("FOLIO"));
				bean.setTicket(rs.getString("TICKET"));
				bean.setEstatus(Integer.valueOf(rs.getInt("STATUS")));
				bean.setCalidad_empaque(Integer.valueOf(rs.getInt("calidad_empaque")));
				if (bean.getFolio() != null && bean.getFolio().length() > 19) {
					bean.setCodigo(getToken(bean.getFolio()));
				}
				bean.setFecha(rs.getString("FECHA"));
				bean.setId_productor((rs.getInt("ID_PRODUCTOR")));
				bean.setPeso_bruto((rs.getDouble("PESO_BRUTO")));
				bean.setPeso_tara((rs.getDouble("PESO_TARA")));
				bean.setPeso_neto((rs.getDouble("PESO_NETO")));
				bean.setNo_rejas((rs.getDouble("NO_REJAS")));
				bean.setTipo_rejas((rs.getInt("ID_REJAS")));
				bean.setTipo_limon((rs.getInt("ID_TLIMON")));
				bean.setCalidad_empaque((rs.getInt("CALIDAD_EMPAQUE")));
				bean.setId_agronomo((rs.getInt("ID_AGRONOMO")));
				bean.setDejo((rs.getInt("DEJO")));
				bean.setIsdejo(false);
				bean.setIdRejas(rs.getInt("id_rejas"));
				if (bean.getDejo() == 1)
					bean.setIsdejo(true);
				bean.setObservaciones(rs.getString("OBSERVACIONES"));
				bean.setSegundas((rs.getDouble("SEGUNDAS")));
				bean.setTerceras((rs.getDouble("TERCERAS")));
				bean.setTorreon((rs.getDouble("TORREON")));
				bean.setColeada((rs.getDouble("COLEADA")));
				bean.setJapon((rs.getDouble("JAPON")));
				bean.setProductor(rs.getString("PRODUCTOR"));
				bean.setAgronomo(rs.getString("AGRONOMO"));

				bean.setComprador(rs.getString("COMPRADOR"));
				bean.setFacturar(rs.getString("FACTURAR"));

				bean.setTipos_rejas(rs.getString("TIPO_REJAS"));
				bean.setTipos_limones(rs.getString("TIPO_LIMON"));
				bean.setFechabas(null);
				bean.setFecharec(null);
				bean.setFechacon(null);
				try {
					bean.setFechabas(rs.getString("FECHA_BASC"));
				} catch (Exception ed) {
					System.out.println("Problemas con FECHA Bascula");
				}
				try {
					bean.setFecharec(rs.getString("FECHA_RECE"));
				} catch (Exception ed) {
					System.out.println("Problemas con FECHA Recepcion");
				}
				try {
					bean.setFechacon(rs.getString("FECHA_CONF"));
				} catch (Exception ed) {
					System.out.println("Problemas con FECHA Confirmacion");
				}
				if (bean.getEstatus() == 0 && bean.getFechabas() == null && bean.getFecharec() == null)
					bean.setProceso(0);
				if (bean.getEstatus() == 0 && bean.getFechabas() != null && bean.getFecharec() == null)
					bean.setProceso(1);
				if (bean.getEstatus() == 1 && bean.getFechabas() != null && bean.getFecharec() == null)
					bean.setProceso(2);
				if (bean.getEstatus() == 0 && bean.getFechabas() == null && bean.getFecharec() == null) {
					bean.setProceso(0);
					bean.setBotonBascula("Cambio de estatus a recepción");
					bean.setStatusSiguiente(1);
				}
				if (bean.getEstatus() == 0 && bean.getFechabas() != null && bean.getFecharec() != null) {
					bean.setProceso(1);
					bean.setBotonBascula("Cambio de estatus a confirmación");
					bean.setStatusSiguiente(2);
				}
				if (bean.getEstatus() == 1 && bean.getFechabas() != null && bean.getFecharec() == null) {
					bean.setProceso(2);
				}
			}
			rs.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return bean;
	}

	public ArrayList<Folios> getAll(int i) {
		ArrayList<Folios> l = new ArrayList<>();
		Connection connection = null;
		try {
			connection = get_connection();
			Statement st = connection.createStatement();
			try (ResultSet rs = st.executeQuery(querySelect + i + " order by F.FOLIO desc ")) {
				while (rs.next()) {
					Folios bean = new Folios();
					bean.setEstatus(i);
					bean.setId(rs.getInt("ID"));
					bean.setFolio(rs.getString("FOLIO"));
					bean.setCodigo(getToken(bean.getFolio()));
					bean.setFecha(rs.getString("FECHA"));
					bean.setComprador(rs.getString("COMPRADOR"));
					bean.setTicket(rs.getString("TICKET"));
					bean.setFacturar(rs.getString("FACTURAR"));
					bean.setId_productor(rs.getInt("ID_PRODUCTOR"));
					bean.setPeso_bruto((rs.getDouble("PESO_BRUTO")));
					bean.setPeso_tara((rs.getDouble("PESO_TARA")));
					bean.setPeso_neto((rs.getDouble("PESO_NETO")));
					bean.setNo_rejas((rs.getDouble("NO_REJAS")));
					bean.setTipo_rejas(rs.getInt("ID_REJAS"));
					bean.setTipo_limon(rs.getInt("ID_TLIMON"));
					bean.setId_agronomo(rs.getInt("ID_AGRONOMO"));
					bean.setDejo(rs.getInt("DEJO"));
					if (bean.getDejo() == 1)
						bean.setIsdejo(true);
					else
						bean.setIsdejo(false);
					bean.setObservaciones(rs.getString("OBSERVACIONES"));
					bean.setSegundas((rs.getDouble("SEGUNDAS")));
					bean.setTerceras((rs.getDouble("TERCERAS")));
					bean.setTerceras((rs.getDouble("TERCERAS")));
					bean.setTorreon((rs.getDouble("TORREON")));
					bean.setColeada((rs.getDouble("COLEADA")));
					bean.setJapon((rs.getDouble("JAPON")));
					bean.setProductor(rs.getString("PRODUCTOR"));
					bean.setAgronomo(rs.getString("AGRONOMO"));
					bean.setTipos_rejas(rs.getString("TIPO_REJAS"));
					bean.setTipos_limones(rs.getString("TIPO_LIMON"));
					bean.setMensaje1(
							(bean.isIsdejo()
									? "Nota:  Dejarón un total de " + bean.getNo_rejas() + " rejas ["
											+ bean.getTipos_rejas() + "]"
									: "Nota:  No dejarón rejas "));

					bean.setFechabas(null);
					bean.setFecharec(null);
					bean.setFechacon(null);
					try {
						bean.setFechabas(rs.getString("FECHA_BASC"));
					} catch (Exception ed) {
						System.out.println("Problemas con FECHA Bascula");
					}
					try {
						bean.setFecharec(rs.getString("FECHA_RECE"));
					} catch (Exception ed) {
						System.out.println("Problemas con FECHA Recepcion");
					}
					try {
						bean.setFechacon(rs.getString("FECHA_CONF"));
					} catch (Exception ed) {
						System.out.println("Problemas con FECHA Confirmacion");
					}
					if (bean.getEstatus() == 0 && bean.getFechabas() == null && bean.getFecharec() == null) {
						bean.setProceso(0);
						bean.setBotonBascula("Cambio de estatus a recepción");
						bean.setStatusSiguiente(1);
					}
					if (bean.getEstatus() == 0 && bean.getFechabas() != null && bean.getFecharec() != null) {
						bean.setProceso(1);
						bean.setBotonBascula("Cambio de estatus a confirmación");
						bean.setStatusSiguiente(3);
					}
					if (bean.getEstatus() == 1 && bean.getFechabas() != null && bean.getFecharec() == null) {
						bean.setProceso(2);
					}
					l.add(bean);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return l;
	}

	public int modificaProductor(int id, int prod) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(updateProductor);
			ps.setInt(1, prod);
			ps.setInt(2, id);
			updeteCont = ps.executeUpdate();
			ps.close();
			updeteCont = 1;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;

	}

	/**
	 * Opciono para rechazar el registro
	 * 
	 * @param id
	 * @param opcion
	 * @return
	 */
	public int rechazar(int id, int opcion) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryrechazar);
			ps.setInt(1, opcion);
			ps.setInt(2, id);
			updeteCont = ps.executeUpdate();
			ps.close();
			updeteCont = 1;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modificar registro
	 * 
	 * @param id_productor
	 * @param peso_bruto
	 * @param peso_tara
	 * @param peso_neto
	 * @param id
	 * @param opc
	 * @return
	 */
	public int updateRecordFase1(int id_productor, double peso_bruto, double peso_tara, double peso_neto, int id,
			int opc) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdate1);
			ps.setInt(1, opc);
			ps.setInt(2, id_productor);
			ps.setDouble(3, peso_bruto);
			ps.setDouble(4, peso_tara);
			ps.setDouble(5, peso_neto);
			ps.setInt(6, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modificar registro
	 * 
	 * @param id_productor
	 * @param peso_bruto
	 * @param peso_tara
	 * @param peso_neto
	 * @param id
	 * @param opc
	 * @return
	 */
	public int updateRecordFase1(int id_productor, double peso_bruto, double peso_tara, double peso_neto, int id,
			int opc, int usuario, String tck) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMvl_tck1);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
			System.out.println(queryUpdateMvl_tck1);
			System.out.println(opc);
			System.out.println(id_productor);
			System.out.println(peso_bruto);
			System.out.println(peso_tara);
			System.out.println(peso_neto);
			System.out.println(usuario);
			System.out.println(id);
			ps.setInt(1, opc);
			ps.setInt(2, id_productor);
			ps.setDouble(3, peso_bruto);
			ps.setDouble(4, peso_tara);
			ps.setDouble(5, peso_neto);
			ps.setInt(6, usuario);
			ps.setString(7, tck);
			ps.setInt(8, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modificar registro
	 * 
	 * @param id_productor
	 * @param peso_bruto
	 * @param peso_tara
	 * @param peso_neto
	 * @param id
	 * @param opc
	 * @return
	 */
	public int updateRecordFase1(int id_productor, double peso_bruto, double peso_tara, double peso_neto, int id,
			int opc, int usuario) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMvl1);
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------");
			System.out.println(queryUpdateMvl1);
			System.out.println(opc);
			System.out.println(id_productor);
			System.out.println(peso_bruto);
			System.out.println(peso_tara);
			System.out.println(peso_neto);
			System.out.println(usuario);
			System.out.println(id);
			ps.setInt(1, opc);
			ps.setInt(2, id_productor);
			ps.setDouble(3, peso_bruto);
			ps.setDouble(4, peso_tara);
			ps.setDouble(5, peso_neto);
			ps.setInt(6, usuario);
			ps.setInt(7, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	public int updateRecordFase1_Neutro(int id_productor, double peso_bruto, double peso_tara, double peso_neto, int id,
			int usuario) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMvl1_neu);
			ps.setInt(1, id_productor);
			ps.setDouble(2, peso_bruto);
			ps.setDouble(3, peso_tara);
			ps.setDouble(4, peso_neto);
			ps.setInt(5, usuario);
			ps.setInt(6, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modifcar Registro
	 * 
	 * @param no_rejas
	 * @param id_rejas
	 * @param id_tlimon
	 * @param id_agronomo
	 * @param dejo
	 * @param observaciones
	 * @param id
	 * @return
	 */
	public int updateRecord(double no_rejas, int id_rejas, int id_tlimon, int id_agronomo, int dejo,
			String observaciones, int id, int opc) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdate2);
			ps.setInt(1, opc);
			ps.setDouble(2, no_rejas);
			ps.setInt(3, id_rejas);
			ps.setInt(4, id_tlimon);
			ps.setInt(5, id_agronomo);
			ps.setInt(6, dejo);
			ps.setString(7, observaciones);
			ps.setInt(8, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modifcar Registro
	 * 
	 * @param no_rejas
	 * @param id_rejas
	 * @param id_tlimon
	 * @param id_agronomo
	 * @param dejo
	 * @param observaciones
	 * @param id
	 * @return
	 */
	public int updateRecord(double no_rejas, int id_rejas, int id_tlimon, int id_agronomo, int dejo,
			String observaciones, int id, int opc, int usuario) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMvl2);
			ps.setInt(1, opc);
			ps.setDouble(2, no_rejas);
			ps.setInt(3, id_rejas);
			ps.setInt(4, id_tlimon);
			ps.setInt(5, id_agronomo);
			ps.setInt(6, dejo);
			ps.setString(7, observaciones);
			ps.setInt(8, usuario);
			ps.setInt(9, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modifcar Registro
	 * 
	 * @param no_rejas
	 * @param id_rejas
	 * @param id_tlimon
	 * @param dejo
	 * @param observaciones
	 * @param id
	 * @return
	 */
	public int updateRecordSinAgr(double no_rejas, int id_rejas, int id_tlimon, int dejo, String observaciones, int id,
			int opc) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdate2_1);
			ps.setInt(1, opc);
			ps.setDouble(2, no_rejas);
			ps.setInt(3, id_rejas);
			ps.setInt(4, id_tlimon);
			ps.setInt(5, dejo);
			ps.setString(6, observaciones);
			ps.setInt(7, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modificar Registro
	 * 
	 * @param segundas
	 * @param terceras
	 * @param torreon
	 * @param coleada
	 * @param japon
	 * @param id
	 * @return
	 */
	public int updateRecord(double segundas, double terceras, double torreon, double coleada, double japon, int id) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdate3);
			ps.setDouble(1, segundas);
			ps.setDouble(2, terceras);
			ps.setDouble(3, torreon);
			ps.setDouble(4, coleada);
			ps.setDouble(5, japon);
			ps.setInt(6, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modificar Registro
	 * 
	 * @param bruto
	 * @param tara
	 * @param neto
	 * @param japon
	 * @param segundas
	 * @param terceras
	 * @param torreon
	 * @param coleada
	 * @param id
	 * @param opc
	 * @return
	 */
	public int updateRecord(double bruto, double tara, double neto, double japon, double segundas, double terceras,
			double torreon, double coleada, int id, int opc) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdate4);
			ps.setDouble(1, bruto);
			ps.setDouble(2, tara);
			ps.setDouble(3, neto);
			ps.setDouble(4, japon);
			ps.setDouble(5, segundas);
			ps.setDouble(6, terceras);
			ps.setDouble(7, torreon);
			ps.setDouble(8, coleada);
			ps.setInt(9, opc);
			ps.setInt(10, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Modificar Registro
	 * 
	 * @param bruto
	 * @param tara
	 * @param neto
	 * @param japon
	 * @param segundas
	 * @param terceras
	 * @param torreon
	 * @param coleada
	 * @param id
	 * @param opc
	 * @param usuario
	 * @return
	 */
	public int updateRecord(double bruto, double tara, double neto, double japon, double segundas, double terceras,
			double torreon, double coleada, int id, int opc, int usuario) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMov4);
			ps.setDouble(1, bruto);
			ps.setDouble(2, tara);
			ps.setDouble(3, neto);
			ps.setDouble(4, japon);
			ps.setDouble(5, segundas);
			ps.setDouble(6, terceras);
			ps.setDouble(7, torreon);
			ps.setDouble(8, coleada);
			ps.setInt(9, opc);
			ps.setInt(10, usuario);
			ps.setInt(11, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	public int updateRecord(double bruto, double tara, double neto, double japon, double segundas, double terceras,
			double torreon, double coleada, int id, int opc, int usuario, String comprador, String facturar,
			String id_folio) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			// UPDATE CC_FOLIOS SET
			// PESO_BRUTO=?,PESO_TARA=?,PESO_NETO=?,JAPON=?,SEGUNDAS=?,TERCERAS=?,TORREON=?,COLEADA=?,STATUS=?,MODIFICADOPOR=?
			// WHERE ID
			PreparedStatement ps = connection.prepareStatement(queryUpdateMov4);
			ps.setDouble(1, bruto);
			ps.setDouble(2, tara);
			ps.setDouble(3, neto);
			ps.setDouble(4, japon);
			ps.setDouble(5, segundas);
			ps.setDouble(6, terceras);
			ps.setDouble(7, torreon);
			ps.setDouble(8, coleada);
			ps.setInt(9, opc);
			ps.setInt(10, usuario);
			ps.setInt(11, id);
			updeteCont = ps.executeUpdate();
			ps.close();
			if (opc == 3) {
				CorridasController ctr = new CorridasController();
				Corrida cdr = ctr.insertaDatoVacio(id, comprador, facturar);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Proceso para el borrado de registro.
	 * 
	 * @param id
	 * @return
	 */
	public int rechazarActivo(int id) {
		int deleteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryrechazarActivo);
			ps.setInt(1, id);
			deleteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return deleteCont;
	}

	/**
	 * Proceso para el borrado de registro.
	 * 
	 * @param id
	 * @return
	 */
	public int deleteRecord(int id) {
		int deleteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryDelete);
			ps.setInt(1, id);
			deleteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return deleteCont;
	}

	/**
	 * Proceso para la modificación de registro.
	 * 
	 * @param id
	 * @return numero de registros dados de alta.
	 */
	private int updateRecord(int id) {
		int updeteCont = 0;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateNumber);
			ps.setInt(1, id);
			updeteCont = ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransporteController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return updeteCont;
	}

	/**
	 * Insertar Registro
	 * 
	 * @param id_productor
	 * @param peso_bruto
	 * @param peso_tara
	 * @param peso_neto
	 * @return
	 */
	public Folios insertRecord(int id_productor, double peso_bruto, double peso_tara, double peso_neto) {
		int insertCont = 0;
		Folios bean = null;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryCreate);
			ps.setInt(1, id_productor);
			ps.setDouble(2, peso_bruto);
			ps.setDouble(3, peso_tara);
			ps.setDouble(4, peso_neto);
			insertCont = ps.executeUpdate();
			if (insertCont == 1) {
				bean = getMax();
				updateRecord(bean.getId());
			}
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return bean;
	}

	/**
	 * Insertar Registro
	 * 
	 * @param id_productor
	 * @param peso_bruto
	 * @param peso_tara
	 * @param peso_neto
	 * @return
	 */
	public Folios insertRecord(int id_productor, double peso_bruto, double peso_tara, double peso_neto, int usuario) {
		int insertCont = 0;
		Folios bean = null;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryCreateMvl);
			ps.setInt(1, id_productor);
			ps.setDouble(2, peso_bruto);
			ps.setDouble(3, peso_tara);
			ps.setDouble(4, peso_neto);
			ps.setInt(5, usuario);
			ps.setInt(6, usuario);
			insertCont = ps.executeUpdate();
			if (insertCont == 1) {
				bean = getMax();
				updateRecord(bean.getId());
				bean = getOne(bean.getId());
			}
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return bean;
	}

	/**
	 * Insertar Registro
	 * 
	 * @param id_productor
	 * @param peso_bruto
	 * @param peso_tara
	 * @param peso_neto
	 * @return
	 */
	public Folios insertRecordcTCK(int id_productor, double peso_bruto, double peso_tara, double peso_neto, int usuario,
			String tck) {
		int insertCont = 0;
		Folios bean = null;
		Connection connection = null;
		try {
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryCreateMv2);
			ps.setInt(1, id_productor);
			ps.setDouble(2, peso_bruto);
			ps.setDouble(3, peso_tara);
			ps.setDouble(4, peso_neto);
			ps.setInt(5, usuario);
			ps.setInt(6, usuario);
			ps.setString(7, tck);
			insertCont = ps.executeUpdate();
			if (insertCont == 1) {
				bean = getMax();
				updateRecord(bean.getId());
				bean = getOne(bean.getId());
			}
			ps.close();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return bean;
	}

	private String getToken(String cadena) {
		if (cadena == null)
			return cadena;
		if (cadena.length() > 16)
			return cadena;
		cadena = cadena.substring(3, 15);
		String pww = "";
		if (cadena.length() % 2 == 0) {
			for (int i = 0; i < (cadena.length() / 2); i++) {
				String tok = cadena.substring((2 * i), (2 * (i + 1)));
				pww = pww + (sexty(tok));
			}
		}
		return pww;
	}

	private String sexty(String to) {
		int cod = (new Integer(to));
		switch (cod) {
		case 0:
			return "0";
		case 1:
			return "1";
		case 2:
			return "2";
		case 3:
			return "3";
		case 4:
			return "4";
		case 5:
			return "5";
		case 6:
			return "6";
		case 7:
			return "7";
		case 8:
			return "8";
		case 9:
			return "9";

		case 10:
			return "A";
		case 11:
			return "B";
		case 12:
			return "C";
		case 13:
			return "D";
		case 14:
			return "E";
		case 15:
			return "F";
		case 16:
			return "G";
		case 17:
			return "H";
		case 18:
			return "I";
		case 19:
			return "J";

		case 20:
			return "K";
		case 21:
			return "L";
		case 22:
			return "M";
		case 23:
			return "N";
		case 24:
			return "O";
		case 25:
			return "P";
		case 26:
			return "Q";
		case 27:
			return "R";
		case 28:
			return "S";
		case 29:
			return "T";

		case 30:
			return "U";
		case 31:
			return "V";
		case 32:
			return "W";
		case 33:
			return "X";
		case 34:
			return "Y";
		case 35:
			return "Z";
		case 36:
			return "*";
		case 37:
			return "#";
		case 38:
			return "$";
		case 39:
			return "%";

		case 40:
			return "&";
		case 41:
			return "/";
		case 42:
			return "(";
		case 43:
			return ")";
		case 44:
			return "=";
		case 45:
			return "!";
		case 46:
			return "+";
		case 47:
			return "{";
		case 48:
			return "}";
		case 49:
			return "[";

		case 50:
			return "]";
		case 51:
			return "-";
		case 52:
			return ".";
		case 53:
			return ",";
		case 54:
			return ";";
		case 55:
			return ":";
		case 56:
			return "<";
		case 57:
			return ">";
		case 58:
			return "@";
		case 59:
			return "?";
		}
		return " ";
	}

	public int updateRecord_neutro(double no_rejas, int id_rejas, int id_tlimon, int id_agronomo, int dejo,
			String observaciones, int id, int usuario, Integer calidad_empaque, double bruto, double tara, double neto,
			String comprador, String facturar, String ticket) {
		int updeteCont = 0;
		try {
			Connection connection;
			updeteCont = 0;
			connection = null;
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMvl2_neutro);
			ps.setDouble(1, no_rejas);
			ps.setInt(2, id_rejas);
			ps.setInt(3, id_tlimon);
			ps.setInt(4, id_agronomo);
			ps.setInt(5, dejo);
			ps.setString(6, observaciones);
			ps.setInt(7, usuario);
			ps.setInt(8, calidad_empaque);
			ps.setDouble(9, bruto);
			ps.setDouble(10, tara);
			ps.setDouble(11, neto);
			ps.setString(12, ticket);
			ps.setInt(13, id);
			updeteCont = ps.executeUpdate();
			ps.close();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			Logger.getLogger(FolioController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return updeteCont;
	}

	public int updateRecord(double no_rejas, int id_rejas, int id_tlimon, int id_agronomo, int dejo,
			String observaciones, int id, int opc, int usuario, Integer calidad_empaque, double bruto, double tara,
			double neto, String comprador, String facturar, String ticket) {
		int updeteCont = 0;
		try {
			Connection connection;
			updeteCont = 0;
			connection = null;
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMvl3);
			ps.setInt(1, opc);
			ps.setDouble(2, no_rejas);
			ps.setInt(3, id_rejas);
			ps.setInt(4, id_tlimon);
			ps.setInt(5, id_agronomo);
			ps.setInt(6, dejo);
			ps.setString(7, observaciones);
			ps.setInt(8, usuario);
			ps.setInt(9, calidad_empaque.intValue());
			ps.setDouble(10, bruto);
			ps.setDouble(11, tara);
			ps.setDouble(12, neto);
			ps.setString(13, comprador);
			ps.setString(14, facturar);
			ps.setString(15, ticket);
			ps.setInt(16, id);
			updeteCont = ps.executeUpdate();
			ps.close();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			Logger.getLogger(FolioController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return updeteCont;
	}

	public int updateRecord(double no_rejas, int id_rejas, int id_tlimon, int id_agronomo, int dejo,
			String observaciones, int id, int opc, int usuario, Integer calidad_empaque, double bruto, double tara,
			double neto) {
		int updeteCont = 0;
		try {
			Connection connection;
			updeteCont = 0;
			connection = null;
			connection = get_connection();
			PreparedStatement ps = connection.prepareStatement(queryUpdateMvl2);
			ps.setInt(1, opc);
			ps.setDouble(2, no_rejas);
			ps.setInt(3, id_rejas);
			ps.setInt(4, id_tlimon);
			ps.setInt(5, id_agronomo);
			ps.setInt(6, dejo);
			ps.setString(7, observaciones);
			ps.setInt(8, usuario);
			ps.setInt(9, calidad_empaque.intValue());
			ps.setDouble(10, bruto);
			ps.setDouble(11, tara);
			ps.setDouble(12, neto);
			ps.setInt(13, id);
			updeteCont = ps.executeUpdate();
			ps.close();
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception ex) {
			Logger.getLogger(FolioController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return updeteCont;
	}

	public ArrayList getAll() {

		ArrayList l;
		Connection connection;
		l = new ArrayList();
		try {
			connection = null;
			ResultSet rs;
			connection = get_connection();
			Statement st = connection.createStatement();
			rs = st.executeQuery(
					(new StringBuilder()).append(querySelectAll).append(" order by F.FOLIO desc ").toString());
			try {
				Folios bean;
				for (; rs.next(); l.add(bean)) {
					bean = new Folios();
					bean.setId((rs.getInt("ID")));
					bean.setFolio(rs.getString("FOLIO"));
					bean.setCodigo(getToken(bean.getFolio()));
					bean.setFecha(rs.getString("FECHA"));
					bean.setId_productor((rs.getInt("ID_PRODUCTOR")));
					bean.setPeso_bruto((rs.getDouble("PESO_BRUTO")));
					bean.setPeso_tara((rs.getDouble("PESO_TARA")));
					bean.setPeso_neto((rs.getDouble("PESO_NETO")));
					bean.setNo_rejas((rs.getDouble("NO_REJAS")));
					bean.setTipo_rejas((rs.getInt("ID_REJAS")));
					bean.setTipo_limon((rs.getInt("ID_TLIMON")));
					bean.setId_agronomo((rs.getInt("ID_AGRONOMO")));
					bean.setNo_rejas((rs.getDouble("NO_REJAS")));
					bean.setDejo((rs.getInt("DEJO")));
					if (bean.getDejo().intValue() == 1) {
						bean.setIsdejo(true);
					} else {
						bean.setIsdejo(false);
					}
					bean.setObservaciones(rs.getString("OBSERVACIONES"));
					bean.setSegundas((rs.getDouble("SEGUNDAS")));
					bean.setTerceras((rs.getDouble("TERCERAS")));
					bean.setTorreon((rs.getDouble("TORREON")));
					bean.setColeada((rs.getDouble("COLEADA")));
					bean.setJapon((rs.getDouble("JAPON")));
					bean.setProductor(rs.getString("PRODUCTOR"));
					bean.setAgronomo(rs.getString("AGRONOMO"));
					bean.setTipos_rejas(rs.getString("TIPO_REJAS"));
					bean.setTipos_limones(rs.getString("TIPO_LIMON"));
					bean.setFechabas(null);
					bean.setFecharec(null);
					bean.setFechacon(null);
					try {
						bean.setFechabas(rs.getString("FECHA_BASC"));
					} catch (Exception ed1) {
						System.out.println("Problemas con FECHA Bascula");
					}
					try {
						bean.setFecharec(rs.getString("FECHA_RECE"));
					} catch (Exception ed2) {
						System.out.println("Problemas con FECHA Recepcion");
					}
					try {
						bean.setFechacon(rs.getString("FECHA_CONF"));
					} catch (Exception ed3) {
						System.out.println("Problemas con FECHA Confirmacion");
					}
				}

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				rs.close();
				st.close();
				if (connection != null) {
					try {
						connection.close();
					} catch (SQLException ex) {
						Logger.getLogger(TransportistaController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}

		} catch (Exception ex) {
			Logger.getLogger(FolioController.class.getName()).log(Level.SEVERE, null, ex);
		}
		return l;
	}

}
