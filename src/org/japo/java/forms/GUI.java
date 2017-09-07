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

import java.awt.Image;
import java.net.URL;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.japo.java.libraries.UtilesSwing;
import org.japo.java.main.Main;

/**
 *
 * @author José A. Pacheco Ondoño - joanpaon@gmail.com
 */
public class GUI extends JFrame {

    // Referencias
    private Main main;
    private Properties prp;

    // Constructor
    public GUI(Main main, Properties prp) {
        // Inicializacion Anterior
        initBefore(main, prp);

        // Creación Vista
        initComponents();

        // Inicializacion Posterior
        initAfter();
    }

    // Construcción - GUI
    private void initComponents() {
        // Panel Principal
        JPanel pnlPpal = new JPanel();

        // Icono Ventana - Recurso
        URL urlICN = getClass().getResource("/img/favicon.png");
        Image imgICN = new ImageIcon(urlICN).getImage();

        // Ventana principal
        setTitle("Here goes the title");
        setContentPane(pnlPpal);
        setResizable(false);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setIconImage(imgICN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Inicializar GUI - PREVIA
    private void initBefore(Main main, Properties prp) {
        // Memorizar Referencias
        this.main = main;
        this.prp = prp;

        // Establece Lnf
        UtilesSwing.establecerLnF(prp.getProperty(
                Main.PRP_LOOK_AND_FEEL, Main.DEF_LOOK_AND_FEEL));

        // Establecer Favicon
        UtilesSwing.establecerFavicon(this, prp.getProperty(
                Main.PRP_RUTA_FAVICON, Main.DEF_RUTA_FAVICON));
    }

    // Inicializar GUI - POSTERIOR
    private void initAfter() {

    }
}
