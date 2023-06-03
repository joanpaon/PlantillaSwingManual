/*
 * Copyright 2023 JAPO Labs - japolabs@gmail.com.
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

/**
 *
 * @author JAPO Labs - japolabs@gmail.com
 */
public final class UtilesConfig {

    // DB - JDBC Protocol
    public static final String DB_PROT = "db_prot";

    // DB - Server Name/IP 
    public static final String DB_HOST = "db_host";

    // DB - Server Port
    public static final String DB_PORT = "db_port";

    // DB - Schema Name
    public static final String DB_NAME = "db_name";

    // DB - Access Credentials
    public static final String DB_USER = "db_user";
    public static final String DB_PASS = "db_pass";

    // DB - JDBC - Statement - Organización de Datos
    public static final String DB_STMT_TYPE = "db_stmt_type";

    // DB - JDBC - Statement - Actualización de Datos
    public static final String DB_STMT_CONCUR = "db_stmt_concur";

    // Look and Feel
    public static final String LNF_PROFILE_NAME = "look_and_feel_profile";

    // Main Form - Título
    public static final String FRM_TITLE = "form_title";

    // Main Form - Tamaño
    public static final String FRM_WIDTH = "form_width";
    public static final String FRM_HEIGHT = "form_height";

    // Main Form - Favicon - Resource
    public static final String FRM_FAVICON = "img_favicon_resource";

    // Main Form - Viewport Background Texture - Resource
    public static final String FRM_BACK_IMG = "img_back_resource";

    // Custom Font #01 - Resource
    public static final String FNT_CUSTOM01 = "font_resource";

    // Constructor Privado
    private UtilesConfig() {
    }
}
