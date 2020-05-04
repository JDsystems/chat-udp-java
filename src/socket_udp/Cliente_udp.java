/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socket_udp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class Cliente_udp extends javax.swing.JFrame {

    public static Cliente_udp cliente;
    DatagramSocket socket;
    DatagramPacket paquete, serv_paquete;

    BufferedReader input;
    InetAddress direccion;
    byte[] tamaño_msj, tomar_flujo;
    String mensaje = "", cadena_msj = "";

    public Cliente_udp() {
        initComponents();
        recibir_mensaje();

    }
    @SuppressWarnings("unchecked")
    public void recibir_mensaje() {
        try {
            socket = new DatagramSocket();
            this.setVisible(true);
            this.setTitle("Cliente");
            this.setBounds(700, 100, 410, 340);
            txt_mensaje.grabFocus();
            direccion = InetAddress.getByName("localhost");
            while (true) {
                tomar_flujo = new byte[256];
                serv_paquete = new DatagramPacket(tomar_flujo, 256);
                socket.receive(serv_paquete);
                cadena_msj = new String(tomar_flujo).trim();
                area_mensaje.append("servidor: " + cadena_msj + "\n");
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
        txt_mensaje = new javax.swing.JTextField();
        btn_enviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Sitka Small", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 310, 30));

        area_mensaje.setColumns(20);
        area_mensaje.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        area_mensaje.setRows(5);
        jScrollPane1.setViewportView(area_mensaje);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 310, 120));

        txt_mensaje.setFont(new java.awt.Font("Sitka Small", 0, 12)); // NOI18N
        getContentPane().add(txt_mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 170, -1));

        btn_enviar.setFont(new java.awt.Font("Sitka Small", 0, 14)); // NOI18N
        btn_enviar.setText("Enviar");
        btn_enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_enviarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 110, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_enviarActionPerformed
        if (txt_mensaje.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite su Mensaje");
        } else {
            try {
                input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(txt_mensaje.getText().getBytes())));
                mensaje = input.readLine();
                tamaño_msj = mensaje.getBytes();
                paquete = new DatagramPacket(tamaño_msj, mensaje.length(), direccion, 6000);
                socket.send(paquete);
                txt_mensaje.setText("");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btn_enviarActionPerformed

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
            java.util.logging.Logger.getLogger(Cliente_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente_udp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        cliente = new Cliente_udp();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_mensaje;
    private javax.swing.JButton btn_enviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_mensaje;
    // End of variables declaration//GEN-END:variables
}
