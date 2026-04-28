package vistas;

import java.awt.Dimension;
import javax.swing.*;

public class UIUtil {

  

    private static final Dimension FIELD_SIZE = new Dimension(200, 25);
    private static final Dimension COMBO_SIZE = new Dimension(200, 25);
    private static final Dimension TEXTAREA_SIZE = new Dimension(400, 200);


    public static void estilizarCampo(JTextField tf) {
        tf.setMaximumSize(FIELD_SIZE);
        tf.setPreferredSize(FIELD_SIZE);
        tf.setMinimumSize(FIELD_SIZE);
    }

  
    public static void estilizarCombo(JComboBox<?> combo) {
        combo.setMaximumSize(COMBO_SIZE);
        combo.setPreferredSize(COMBO_SIZE);
        combo.setMinimumSize(COMBO_SIZE);
    }


    public static void estilizarArea(JTextArea area) {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

        area.setMaximumSize(TEXTAREA_SIZE);
        area.setPreferredSize(TEXTAREA_SIZE);
        area.setMinimumSize(TEXTAREA_SIZE);
    }

    public static void estilizarScroll(JScrollPane scroll) {
        scroll.setMaximumSize(TEXTAREA_SIZE);
        scroll.setPreferredSize(TEXTAREA_SIZE);
        scroll.setMinimumSize(TEXTAREA_SIZE);
    }
}
