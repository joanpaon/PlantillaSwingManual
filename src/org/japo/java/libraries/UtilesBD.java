/*
 * Copyright 2019 José A. Pacheco Ondoño - joanpaon@gmail.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.japo.java.libraries;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class UtilesBD {

    // Valores Predeterminados Conexión BBDD
    // private static final String DEF_PROT = "jdbc:mysql";
    private static final String DEF_PROT = "jdbc:mariadb";
    private static final String DEF_HOST = "localhost";
    private static final String DEF_PORT = "3306";
    private static final String DEF_DBAM = "agenda";
    private static final String DEF_USER = "usuario";
    private static final String DEF_PASS = "usuario";

    // Formato Conexión
    private static final String FORMATO_CON = "%s://%s:%s/%s?user=%s&password=%s";

    // Properties > Conexión con BD
    public static final Connection conectar(Properties prp) throws SQLException {
        // Definir cadena de conexión
        String cadenaConexion = obtenerCadenaConexion(prp);

        // Realizar la conexión
        return DriverManager.getConnection(cadenaConexion);
    }

    // Conexión con BD - Predeterminada
    public static final Connection conectar(String cadena) throws SQLException {
        return DriverManager.getConnection(cadena);
    }

    // Parámetros - Conexión con BD
    public static final Connection conectar(
            String prot, String host, String port, String db,
            String user, String pass) throws SQLException {
        // Definir cadena de conexión
        String cadenaConexion = obtenerCadenaConexion(prot, host, port, db, user, pass);

        // Realizar la conexión
        return UtilesBD.conectar(cadenaConexion);
    }

    // Conexión > Statement
    public static final Statement vincular(Connection conn) throws SQLException {
        // ---- TIPOS DE ACCESO ----
        // ResultSet.TYPE_FORWARD_ONLY (*) - Indica que el objeto ResultSet se
        //      puede recorrer unicamente hacia adelante.
        // ResultSet.TYPE_SCROLL_INSENSITIVE - Indica que el objeto ResultSet se
        //      puede recorrer, pero en general no es sensible a los cambios en
        //      los datos que subyacen en él.
        // ResultSet.TYPE_SCROLL_SENSITIVE - Indica que el objeto ResultSet se
        //      puede  recorrer, y además, los cambios en él repercuten
        //      en la base de datos subyacente.
        // ---- MODOS DE CONCURRENCIA ----
        // ResultSet.CONCUR_READ_ONLY (*) - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste no puede ser actualizado.
        // ResultSet.CONCUR_UPDATABLE - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste podria ser actualizado.
        //
        // Retorno > Statement
        return conn.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
    }

    // Conexión + Access + Concurrency > Statement
    public static final Statement vincular(Connection conn, int acceso, int concurrencia) throws SQLException {
        // ---- TIPOS DE ACCESO ----
        // ResultSet.TYPE_FORWARD_ONLY (*) - Indica que el objeto ResultSet se
        //      puede recorrer unicamente hacia adelante.
        // ResultSet.TYPE_SCROLL_INSENSITIVE - Indica que el objeto ResultSet se
        //      puede recorrer, pero en general no es sensible a los cambios en
        //      los datos que subyacen en él.
        // ResultSet.TYPE_SCROLL_SENSITIVE - Indica que el objeto ResultSet se
        //      puede  recorrer, y además, los cambios en él repercuten
        //      en la base de datos subyacente.
        // ---- MODOS DE CONCURRENCIA ----
        // ResultSet.CONCUR_READ_ONLY (*) - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste no puede ser actualizado.
        // ResultSet.CONCUR_UPDATABLE - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste podria ser actualizado.
        //
        // Retorno > Statement
        return conn.createStatement(acceso, concurrencia);
    }

    // Conexión + Access + Concurrency > Statement
    public static final Statement vincular(Connection conn, Properties prp) throws SQLException {
        // ---- TIPOS DE ACCESO ----
        // ResultSet.TYPE_FORWARD_ONLY (*) - Indica que el objeto ResultSet se
        //      puede recorrer unicamente hacia adelante.
        // ResultSet.TYPE_SCROLL_INSENSITIVE - Indica que el objeto ResultSet se
        //      puede recorrer, pero en general no es sensible a los cambios en
        //      los datos que subyacen en él.
        // ResultSet.TYPE_SCROLL_SENSITIVE - Indica que el objeto ResultSet se
        //      puede  recorrer, y además, los cambios en él repercuten
        //      en la base de datos subyacente.
        // ---- MODOS DE CONCURRENCIA ----
        // ResultSet.CONCUR_READ_ONLY (*) - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste no puede ser actualizado.
        // ResultSet.CONCUR_UPDATABLE - Indica que en el modo de concurrencia
        //      para el objeto ResultSet éste podria ser actualizado.
        //
        // Tipo de Acceso
        int tipoAcceso = ResultSet.TYPE_FORWARD_ONLY;
        if (prp.getProperty(UtilesConfig.DB_STMT_TYPE)
                .equals("TYPE_SCROLL_INSENSITIVE")) {
            tipoAcceso = ResultSet.TYPE_SCROLL_INSENSITIVE;
        } else if (prp.getProperty(UtilesConfig.DB_STMT_TYPE)
                .equals("TYPE_SCROLL_SENSITIVE")) {
            tipoAcceso = ResultSet.TYPE_SCROLL_SENSITIVE;
        }

        // Concurrencia
        int concurrencia = ResultSet.CONCUR_READ_ONLY;
        if (prp.getProperty(UtilesConfig.DB_STMT_CONCUR)
                .equals("CONCUR_UPDATABLE")) {
            concurrencia = ResultSet.CONCUR_UPDATABLE;
        }

        // Retorno > Statement
        return conn.createStatement(tipoAcceso, concurrencia);
    }

    // Statement + SQL > ResultSet
    public static final ResultSet obtener(Statement stmt, String sql) throws SQLException {
        // Retorno - SQL > Statement
        return stmt.executeQuery(sql);
    }

    // Statement + Properties > ResultSet
    public static final ResultSet obtener(Statement stmt, Properties prp) throws SQLException {
        // Retorno - SQL > Statement
        return stmt.executeQuery(prp.getProperty("default_query"));
    }

    // SQL Date > String (dd/MM/yyyy)
    public static final String convertirSQLDate2String(java.sql.Date sqlDate) {
        return new SimpleDateFormat("dd/MM/yyyy").format(sqlDate);
    }

    // SQL Date + Pattern > String
    public static final String convertirSQLDate2String(java.sql.Date sqlDate, String pattern) {
        return new SimpleDateFormat(pattern).format(sqlDate);
    }

    // ResultSet > Número Registros
    public static final int obtenerNumeroRegistros(ResultSet rs) {
        // Almacén resultado
        int numFilas;

        try {
            // Comprobar ResultSet
            if (rs != null && !rs.isClosed()) {
                // Número de la fila a la que apunta el cursor
                int filaAct = rs.getRow();

                // Va al final del ResultSet
                rs.last();

                // Obtiene el número de filas
                numFilas = rs.getRow();

                if (filaAct != 0) {
                    // Coloca el cursor en la posición previa
                    rs.absolute(filaAct);
                } else {
                    // Coloca el cursor al principio del ResultSet
                    rs.first();
                }
            } else {
                numFilas = 0;
            }
        } catch (SQLException ex) {
            numFilas = 0;
        }

        // Número de filas calculadas
        return numFilas;
    }

    // ResultSet > Número Registro Actual
    public static final int obtenerPosicionActual(ResultSet rs) {
        // Almacén resultado
        int filaActual;

        try {
            // Comprobar ResultSet
            if (rs != null && !rs.isClosed()) {
                filaActual = rs.getRow();
            } else {
                filaActual = 0;
            }
        } catch (SQLException ex) {
            filaActual = 0;
        }

        // Número Registro Actual
        return filaActual;
    }

    // Cierre JDBC Connection
    public static final void cerrar(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Cierre JDBC Statement
    public static final void cerrar(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    // Cierre JDBC ResultSet
    public static final void cerrar(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException | NullPointerException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    // Properties > Cadena de Conexión
    public static final String obtenerCadenaConexion(Properties prp) {
        return String.format(
                FORMATO_CON,
                prp.getProperty(UtilesConfig.DB_PROT, DEF_PROT),
                prp.getProperty(UtilesConfig.DB_HOST, DEF_HOST),
                prp.getProperty(UtilesConfig.DB_PORT, DEF_PORT),
                prp.getProperty(UtilesConfig.DB_NAME, DEF_DBAM),
                prp.getProperty(UtilesConfig.DB_USER, DEF_USER),
                prp.getProperty(UtilesConfig.DB_PASS, DEF_PASS));
    }
    
    // Parámetros > Cadena de Conexión
    public static final String obtenerCadenaConexion(
            String prot, String host, String port, String db,
            String user, String pass) {
        return String.format(FORMATO_CON, 
                prot, host, port, db, user, pass);

    }
}
