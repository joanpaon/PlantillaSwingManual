/*
 * Copyright 2020 José A. Pacheco Ondoño - joanpaon@gmail.com.
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
package org.japo.java.entities;

import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class DAM {

    // Referencias
    private Connection conn;
    private Statement stmt;

    // Constructor Parametrizado - Connection
    public DAM(Connection conn) {
        this.conn = conn;
    }

    // Constructor Parametrizado - Connection + Statement
    public DAM(Connection conn, Statement stmt) {
        this.conn = conn;
        this.stmt = stmt;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
}
