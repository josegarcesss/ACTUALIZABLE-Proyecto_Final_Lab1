/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;
import AccesoADatos.MembresiaData;
import AccesoADatos.SocioData;
import Entidades.Membresia;
import Entidades.Socio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Alakyan
 */
    public class VistaHistorialMembresias extends javax.swing.JInternalFrame {
    private final SocioData socioData;
    private final MembresiaData membresiaData;
    private List<Socio> socios;
    private DefaultTableModel tabla= new DefaultTableModel(){ 
    public boolean isCellEditable(int f,int c){
        return false;
        }
    };

    public VistaHistorialMembresias() {
        initComponents();
        socioData = new SocioData();
        membresiaData = new MembresiaData();
          actualizarMembresias();
          armarCabecera();
          cargarCombo();
    }

    





    private void armarCabecera(){
    ArrayList<Object>filacabecera=new ArrayList<>();
    filacabecera.add("N° Membresia");
    filacabecera.add("Pases Restantes");
    filacabecera.add("Fecha Inicio");
    filacabecera.add("Fecha Vencimiento");
    filacabecera.add("Costo");
    filacabecera.add("Estado");
    for(Object it:filacabecera){
    tabla.addColumn(it);
    }
    jt_TablaMembresia.setModel(tabla);
    }
    
    private void cargarCombo(){
        String item1="Todos";
        socios = socioData.listarSociosActivos();
        jcb_Socios.addItem(item1);
        for (Socio socio : socios) {
            jcb_Socios.addItem(socio.toString());
        }
    }
    
    private void borrarFilas(){
        int filas=tabla.getRowCount()-1;        
        for (int f = filas; f >= 0; f--) {
            tabla.removeRow(f);
        }
    }
    
    private void mostrarMembresiasEnTabla() {
        int idSocio=-1;
        String elegido=(String) jcb_Socios.getSelectedItem();
        if(elegido.equals("Todos")){
            List<Membresia> membresias=membresiaData.listarMembresias();
        for (Membresia membresia : membresias) {
            tabla.addRow(new Object[]{
                    membresia.getIdMembresia(),
                    membresia.getCantidadPases(),
                    membresia.getFechaInicio(),
                    membresia.getFechaFin(),
                    membresia.getCosto(),
                    membresia.isEstado() ? "Activa" : "Inactiva"
            });
        }
        }else{
        for (Socio socio : socios) {
            if(socio.toString().equals(elegido)){
                idSocio=socio.getIdSocio();
            }
        }
        
        List<Membresia> membresias=membresiaData.listarMembresiasPorSocio(idSocio);
        for (Membresia membresia : membresias) {
            tabla.addRow(new Object[]{
                    membresia.getIdMembresia(),
                    membresia.getCantidadPases(),
                    membresia.getFechaInicio(),
                    membresia.getFechaFin(),
                    membresia.getCosto(),
                    membresia.isEstado() ? "Activa" : "Inactiva"
            });
        }
        }
    }
    
    private void actualizarMembresias(){
        List<Socio> socios=socioData.listarSocios();
        for (Socio socio : socios) {
          socioData.membresiaActiva(socio.getIdSocio());  
        }
        
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcb_Socios = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_TablaMembresia = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Historial de Membresias");

        jcb_Socios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcb_SociosItemStateChanged(evt);
            }
        });

        jLabel2.setText("Seleccione Socio:");

        jt_TablaMembresia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jt_TablaMembresia);

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(309, 309, 309)
                        .addComponent(jLabel2)
                        .addGap(0, 382, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jcb_Socios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jcb_Socios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcb_SociosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_SociosItemStateChanged
        borrarFilas();
        mostrarMembresiasEnTabla();
    }//GEN-LAST:event_jcb_SociosItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcb_Socios;
    private javax.swing.JTable jt_TablaMembresia;
    // End of variables declaration//GEN-END:variables
}
