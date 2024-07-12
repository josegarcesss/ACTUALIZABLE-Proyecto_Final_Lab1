/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;

import AccesoADatos.EntrenadorData;
import AccesoADatos.SocioData;
import Entidades.Entrenador;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Alakyan
 */
public class VistaListaEntrenadores extends javax.swing.JInternalFrame {
    private EntrenadorData entrenadorData;
    private List<Entrenador> entrenadores;
    private DefaultTableModel tabla= new DefaultTableModel(){ 
    public boolean isCellEditable(int f,int c){
        return c!=0;
        }
    };
    /**
     * Creates new form VistaListaEntrenadores
     */
    public VistaListaEntrenadores() {
        initComponents();
        entrenadorData=new EntrenadorData();
        entrenadores=new ArrayList<>();
        armarCabecera();
        cargarCombo();
    }
    private void armarCabecera(){
    ArrayList<Object>filacabecera=new ArrayList<>();
    filacabecera.add("ID");
    filacabecera.add("DNI");
    filacabecera.add("Nombre");
    filacabecera.add("Apellido");
    filacabecera.add("Especialidad");
    filacabecera.add("Activo");
    for(Object it:filacabecera){
    tabla.addColumn(it);
    }
    jt_TablaEntrenadores.setModel(tabla);
    }
    
    private void borrarFilas(){
        int filas=tabla.getRowCount()-1;        
        for (int f = filas; f >= 0; f--) {
            tabla.removeRow(f);
        }
    }
    
    private void cargarCombo(){
        String item1="Todos";
        String item2="Nombre y/o Apellido";
        String item3="Especialidad";
        jcb_ComboTipo.addItem(item1);
        jcb_ComboTipo.addItem(item2);
        jcb_ComboTipo.addItem(item3);
    }
    
    private void listar(){
        borrarFilas();
        String estado=null;
        entrenadores = entrenadorData.listarEntrenadores();
        //SI EL COMBOBOX ESTA EN TODOS MUESTRA TODOS LOS ENTRENADORES
        if (jcb_ComboTipo.getSelectedItem().toString().equalsIgnoreCase("Todos")) {
            jt_PrimerValor.setText("");
            jt_SegundoValor.setText("");
            jt_PrimerValor.disable();
            jt_SegundoValor.disable();
            for (Entrenador entrenador : entrenadores) {
                estado=siOno(entrenador.isEstado());
                tabla.addRow(new Object[]{entrenador.getIdEntrenador(), entrenador.getDni(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getEspecialidad(), estado});
            }
        //SI EL COMBOBOX ESTA EN "Nombre y/o Apellido" FILTA POR NOMBRE Y/O APELLIDO
        } else if (jcb_ComboTipo.getSelectedItem().toString().equalsIgnoreCase("Nombre y/o Apellido")) {
            jt_PrimerValor.enable();
            jt_SegundoValor.enable();
            jl_PrimerValor.setText("Nombre:");
            jl_SegundoValor.setText("Apellido");
            //FILTRADO SI ES POR NOMBRE, POR APELLIDO, AMBOS O MOSTRAR TODOS
            //FILTRO TODOS
            if (jt_PrimerValor.getText().equals("") && jt_SegundoValor.getText().equals("")) {
                for (Entrenador entrenador : entrenadores) {
                    estado=siOno(entrenador.isEstado());
                    tabla.addRow(new Object[]{entrenador.getIdEntrenador(), entrenador.getDni(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getEspecialidad(), estado});
                }
            //FILTRO POR NOMBRE
            } else if (!jt_PrimerValor.getText().equals("") && jt_SegundoValor.getText().equals("")){
                for (Entrenador entrenador : entrenadores) {
                    if (entrenador.getNombre().toLowerCase().contains(jt_PrimerValor.getText().toLowerCase())) {
                        estado=siOno(entrenador.isEstado());
                        tabla.addRow(new Object[]{entrenador.getIdEntrenador(), entrenador.getDni(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getEspecialidad(), estado});
                    }
                }
            //FILTRO POR APELLIDO    
            } else if(jt_PrimerValor.getText().equals("") && !jt_SegundoValor.getText().equals("")){
                for (Entrenador entrenador : entrenadores) {
                    if (entrenador.getApellido().toLowerCase().contains(jt_SegundoValor.getText().toLowerCase())) {
                        estado=siOno(entrenador.isEstado());
                        tabla.addRow(new Object[]{entrenador.getIdEntrenador(), entrenador.getDni(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getEspecialidad(), estado});
                    }
                }
            //FILTRO POR NOMBRE Y APELLIDO    
            } else{
                for (Entrenador entrenador : entrenadores) {
                    if (entrenador.getNombre().toLowerCase().contains(jt_PrimerValor.getText().toLowerCase()) && entrenador.getApellido().toLowerCase().contains(jt_SegundoValor.getText().toLowerCase())) {
                        estado=siOno(entrenador.isEstado());
                        tabla.addRow(new Object[]{entrenador.getIdEntrenador(), entrenador.getDni(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getEspecialidad(), estado});
                    }
                }
            }
            //SI EL COMBOBOX ESTA EN "Especialidad" FILTRA POR ESPECIALIDAD
        }else{
            jl_PrimerValor.setText("Especialidad:");
            jl_SegundoValor.setText("-");
            jt_PrimerValor.enable();
            jt_SegundoValor.disable();
            for (Entrenador entrenador : entrenadores) {
                    if (entrenador.getEspecialidad().toLowerCase().contains(jt_PrimerValor.getText().toLowerCase())) {
                        estado=siOno(entrenador.isEstado());
                        tabla.addRow(new Object[]{entrenador.getIdEntrenador(), entrenador.getDni(), entrenador.getNombre(), entrenador.getApellido(), entrenador.getEspecialidad(), estado});
                    }
                }
        }
    }
    
    
    private String siOno(boolean valor){
        if(valor){
            return "si";
        }else{
            return "no";
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jb_Salir = new javax.swing.JButton();
        jcb_ComboTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jt_PrimerValor = new javax.swing.JTextField();
        jt_SegundoValor = new javax.swing.JTextField();
        jl_PrimerValor = new javax.swing.JLabel();
        jl_SegundoValor = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_TablaEntrenadores = new javax.swing.JTable();
        jb_Guardar = new javax.swing.JButton();
        jb_Limpiar = new javax.swing.JButton();

        setTitle("Listado de Entrenadores");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Entrenadores");

        jb_Salir.setText("Salir");
        jb_Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_SalirActionPerformed(evt);
            }
        });

        jcb_ComboTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcb_ComboTipoItemStateChanged(evt);
            }
        });

        jLabel2.setText("Buscar por:");

        jt_PrimerValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_PrimerValorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_PrimerValorKeyTyped(evt);
            }
        });

        jt_SegundoValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_SegundoValorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jt_SegundoValorKeyTyped(evt);
            }
        });

        jl_PrimerValor.setText("Nombre:");

        jl_SegundoValor.setText("Apellido:");

        jt_TablaEntrenadores.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jt_TablaEntrenadores);

        jb_Guardar.setText("Guardar");
        jb_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_GuardarActionPerformed(evt);
            }
        });

        jb_Limpiar.setText("Limpiar");
        jb_Limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_LimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jcb_ComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jt_PrimerValor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jl_PrimerValor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jl_SegundoValor)
                                    .addComponent(jt_SegundoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jb_Guardar)
                        .addGap(29, 29, 29)
                        .addComponent(jb_Limpiar)
                        .addGap(18, 18, 18)
                        .addComponent(jb_Salir)))
                .addGap(0, 24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jb_Salir)
                    .addComponent(jb_Limpiar)
                    .addComponent(jb_Guardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jl_PrimerValor)
                    .addComponent(jl_SegundoValor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt_SegundoValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jt_PrimerValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcb_ComboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jcb_ComboTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcb_ComboTipoItemStateChanged
        borrarFilas();
        jt_PrimerValor.setText("");
        jt_SegundoValor.setText("");
        listar();
    }//GEN-LAST:event_jcb_ComboTipoItemStateChanged

    private void jb_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_SalirActionPerformed
        dispose(); 
    }//GEN-LAST:event_jb_SalirActionPerformed

    private void jb_LimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_LimpiarActionPerformed
        jt_PrimerValor.setText("");
        jt_SegundoValor.setText("");
        listar();
    }//GEN-LAST:event_jb_LimpiarActionPerformed

    private void jt_PrimerValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_PrimerValorKeyReleased
        listar();
    }//GEN-LAST:event_jt_PrimerValorKeyReleased

    private void jt_PrimerValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_PrimerValorKeyTyped
        listar();
    }//GEN-LAST:event_jt_PrimerValorKeyTyped

    private void jt_SegundoValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_SegundoValorKeyTyped
        listar();
    }//GEN-LAST:event_jt_SegundoValorKeyTyped

    private void jt_SegundoValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_SegundoValorKeyReleased
        listar();
    }//GEN-LAST:event_jt_SegundoValorKeyReleased

    private void jb_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_GuardarActionPerformed
        boolean estado=true;
        for (int row = 0; row < tabla.getRowCount(); row++) {
        int idEntrenador = (int) tabla.getValueAt(row, 0);
        int dni = (int) tabla.getValueAt(row, 1);
        String nombre = (String) tabla.getValueAt(row, 2);
        String apellido = (String) tabla.getValueAt(row, 3);
        String especialidad = (String) tabla.getValueAt(row, 4);
        String estadoString = (String) tabla.getValueAt(row, 5);
        if(!(estadoString.equalsIgnoreCase("si") || estadoString.equalsIgnoreCase("no"))){
            JOptionPane.showMessageDialog(this, "El valor de Activo tiene ser \"SI\" o \"NO\"", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            if(estadoString.equalsIgnoreCase("si")){
                estado=true;
            }else if(estadoString.equalsIgnoreCase("no")){
                estado=false;
            }
        }
      
        Entrenador entrenador = new Entrenador(idEntrenador, dni, nombre, apellido, especialidad, estado);
       
        if (dniYaExiste(dni, idEntrenador)) {
            JOptionPane.showMessageDialog(this, "El DNI ingresado ya está en uso por otro entrenador.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        entrenadorData.modificarEntrenador(entrenador);
    }
    
    JOptionPane.showMessageDialog(this, "Cambios guardados correctamente");
}

private boolean dniYaExiste(int dni, int idEntrenadorActual) {
   
    List<Entrenador> entrenadores = entrenadorData.listarEntrenadores();
    
   
    for (Entrenador e : entrenadores) {
        if (e.getDni() == dni && e.getIdEntrenador() != idEntrenadorActual) {
            return true;
        }
    }
    
    return false; 
    }//GEN-LAST:event_jb_GuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jb_Guardar;
    private javax.swing.JButton jb_Limpiar;
    private javax.swing.JButton jb_Salir;
    private javax.swing.JComboBox<String> jcb_ComboTipo;
    private javax.swing.JLabel jl_PrimerValor;
    private javax.swing.JLabel jl_SegundoValor;
    private javax.swing.JTextField jt_PrimerValor;
    private javax.swing.JTextField jt_SegundoValor;
    private javax.swing.JTable jt_TablaEntrenadores;
    // End of variables declaration//GEN-END:variables
}
