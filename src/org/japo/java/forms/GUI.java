/*
 * Copyright 2017 José A. Pacheco Ondoño - joanpaon@gmail.com.
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
package org.japo.java.forms;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.net.URL;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.japo.java.components.BackgroundPanel;
import org.japo.java.libraries.UtilesSwing;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Propiedades App
    public static final String PRP_LOOK_AND_FEEL_PROFILE = "look_and_feel_profile";
    public static final String PRP_FAVICON_RESOURCE = "favicon_resource";
    public static final String PRP_BACKGROUND_RESOURCE = "background_resource";
    public static final String PRP_FONT_RESOURCE = "font_resource";

    // Valores por Defecto
    public static final String DEF_LOOK_AND_FEEL_PROFILE = UtilesSwing.LNF_WINDOWS_PROFILE;
    public static final String DEF_FAVICON_RESOURCE = "images/favicon.png";
    public static final String DEF_BACKGROUND_RESOURCE = "images/background.png";
    public static final String DEF_FONT_RESOURCE = "fonts/default_font.ttf";

    // Referencias
    private Properties prp;

    // Constructor
    public GUI(Properties prp) {
        // Inicialización Anterior
        initBefore(prp);

        // Creación Interfaz
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción - GUI
    private void initComponents() {
        // Etiqueta Muestra
        JLabel lblSample = new JLabel("Connect the dots!!!");
        lblSample.setFont(UtilesSwing.importarFuenteRecurso(
                prp.getProperty(PRP_FONT_RESOURCE, DEF_FONT_RESOURCE)).
                deriveFont(Font.BOLD, 60f));
        lblSample.setHorizontalAlignment(JLabel.RIGHT);

        // Imagen de fondo
        String bckPpal = prp.getProperty(PRP_BACKGROUND_RESOURCE, DEF_BACKGROUND_RESOURCE);
        URL urlPpal = ClassLoader.getSystemResource(bckPpal);
        Image imgPpal = new ImageIcon(urlPpal).getImage();

        // Panel Principal
        JPanel pnlPpal = new BackgroundPanel(imgPpal);
        pnlPpal.setLayout(new GridBagLayout());
        pnlPpal.add(lblSample);

        // Ventana Principal
        setContentPane(pnlPpal);
        setTitle("Swing Manual #00");
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicialización Anterior    
    private void initBefore(Properties prp) {
        // Memorizar Referencia
        this.prp = prp;

        // Establecer LnF
        UtilesSwing.establecerLnFProfile(prp.getProperty(
                PRP_LOOK_AND_FEEL_PROFILE, DEF_LOOK_AND_FEEL_PROFILE));
    }

    // Inicialización Posterior
    private void initAfter() {
        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(
                PRP_FAVICON_RESOURCE, DEF_FAVICON_RESOURCE));
    }
}
