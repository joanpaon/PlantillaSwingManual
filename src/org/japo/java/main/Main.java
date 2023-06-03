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
package org.japo.java.main;

import java.util.Properties;
import javax.swing.SwingUtilities;
import org.japo.java.forms.GUI;
import org.japo.java.libraries.UtilesApp;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public final class Main {

    // Clave de Acceso
    private static final String ACCESS_UID = "JAPO-Omicron-0";

    // Constructor Oculto
    private Main() {
    }

    // Entrada a la aplicación
    public static void main(String[] args) {
        if (validarAcceso(args)) {
            // Lanzar GUI
            SwingUtilities.invokeLater(() -> {
                // Obtener Propiedades
                Properties prp = obtenerPropiedades();

                // Instanciar Vistas
                GUI gui = new GUI(prp);

                // Mostrar Vista
                gui.setVisible(true);
            });
        } else {
            System.out.println("Acceso Denegado");
            System.out.println("---");
            System.out.println("Contacte con el servicio Técnico");
        }
    }

    // Validar Acceso
    private static boolean validarAcceso(String[] args) {
        return true
                && args != null
                && args.length == 1
                && args[0].equals(ACCESS_UID);
    }

    // Cargar Propiedades
    private static Properties obtenerPropiedades() {
        // Contenedor de propiedades
        Properties prp = new Properties();

        // Obtener Propiedades Externas
        prp.putAll(UtilesApp.importarPropiedades());

        // Obtener Propiedades Internas
        prp.putAll(UtilesApp.importarPropiedadesRecurso());

        // Retorno
        return prp;
    }
}
