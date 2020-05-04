
package socket_udp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;


public class Servidor_udp extends javax.swing.JFrame {
    public static Servidor_udp servidor;

    DatagramSocket socket;
    DatagramPacket paquete, enviar_paquete;
    boolean fin_chat = false;
    BufferedReader input;
    byte[] tamaño_msj, tamaño_msj2;
    String mensaje = "", mensaje_comp = "";
    int puerto;
    InetAddress direccion, direccion2;

    public Servidor_udp() {
        initComponents();
        recibir_mensaje();
    }

    @SuppressWarnings("unchecked")
    private void recibir_mensaje() {
        try {
            socket = new DatagramSocket(6000);
            this.setVisible(true);
            this.setTitle("Servidor"); 
            this.setBounds(100, 100, 470, 360);
            
            txt_mensaje.grabFocus();
            tamaño_msj = new byte[256];
            paquete = new DatagramPacket(tamaño_msj, 256);
            enviar_paquete = new DatagramPacket(tamaño_msj, 256);
            while (true) {
                socket.receive(paquete);
                mensaje = new String(tamaño_msj).trim();
                puerto = paquete.getPort();
                direccion = paquete.getAddress();
                area_mensaje.append("Cliente: "+mensaje + "\n");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        area_mensaje = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        txt_mensaje = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Small", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Servidor");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 390, 30));

        area_mensaje.setColumns(20);
        area_mensaje.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        area_mensaje.setRows(5);
        jScrollPane1.setViewportView(area_mensaje);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 350, 140));

        jButton1.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N
        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(263, 250, 130, -1));

        txt_mensaje.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        getContentPane().add(txt_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 190, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txt_mensaje.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite su Mensaje");
        } else {
            try {
                tamaño_msj2 = new byte[256];
                direccion2 = InetAddress.getByName("localhost");
                input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(txt_mensaje.getText().getBytes())));
                mensaje_comp = input.readLine();
                tamaño_msj2 = mensaje_comp.getBytes();
                System.out.println("direccion:  " + direccion2);
                enviar_paquete = new DatagramPacket(tamaño_msj2, mensaje_comp.length(), direccion2, puerto);
                socket.send(enviar_paquete);
                txt_mensaje.setText("");

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Servidor_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        servidor = new Servidor_udp();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_mensaje;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_mensaje;
    // End of variables declaration//GEN-END:variables
}
