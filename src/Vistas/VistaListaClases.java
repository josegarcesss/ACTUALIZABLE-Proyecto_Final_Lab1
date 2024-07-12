/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;

import AccesoADatos.ClasesData;
import Entidades.Clase;
import Entidades.Entrenador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alakyan
 */
public class VistaListaClases extends javax.swing.JInternalFrame {
    ClasesData claseData;
    ArrayList<Clase> clases;
    private DefaultTableModel modelo= new DefaultTableModel(){ 
    public boolean isCellEditable(int f,int c){
       
        return false;
        }
    };
    /**
     * Creates new form VistaListaClases
     */
    public VistaListaClases() {
        initComponents();
        claseData=new ClasesData();
        cargarLista();
        armarCabeceras();
        listaSocios();
    }
    
    private void armarCabeceras(){
        ArrayList<Object> filaCabecera = new ArrayList<>();
        modelo.addColumn("Nombre");
        modelo.addColumn("Entrenador");
        modelo.addColumn("Hora de Clase");
        modelo.addColumn("Capacidad");
        for(Object it: filaCabecera){
            modelo.addColumn(it);
        }
        jt_TablaClases.setModel(modelo);
    }
    
    private void listaSocios(){
        for(Clase clase: clases){
        modelo.addRow(new Object[]{clase.getNombre(),clase.getEntrenador().getNombre()+" "+clase.getEntrenador().getApellido(),clase.getHorario().toString(),clase.getCapacidad()});
            }
        
    }
    
    
    private void cargarLista(){
    clases=(ArrayList<Clase>) claseData.listarClases();
        for (Clase clase : clases) {
            if(!clase.isEstado()){
                clases.remove(clase);
            }
        }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_TablaClases = new javax.swing.JTable();
        jbSalir = new javax.swing.JButton();

        setTitle("Lista de Clases");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Lista de Clases");

        jt_TablaClases.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jt_TablaClases);

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(256, 256, 256)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jbSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbSalir)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        dispose();
    }//GEN-LAST:event_jbSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSalir;
    private javax.swing.JTable jt_TablaClases;
    // End of variables declaration//GEN-END:variables
}
