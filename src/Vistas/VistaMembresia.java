/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vistas;

import AccesoADatos.MembresiaData;
import AccesoADatos.SocioData;
import Entidades.Membresia;
import Entidades.Socio;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class VistaMembresia extends javax.swing.JInternalFrame {
    SocioData sociodata;
    MembresiaData memdata;
    List<Socio> socio;
    
    public VistaMembresia() {
        initComponents();
        sociodata=new SocioData();
        socio= sociodata.listarSocios();
        memdata=new MembresiaData();
        marcarFecha();
        Cargar();
    }
    
    private void Cargar(){
    CargarComboSocio();
    }
    
    
    private void CargarComboSocio(){
    jcbSocio.removeAllItems();
        for(Socio s:socio){
            if (s.isEstado()) {
                String socio=s.toString();
                jcbSocio.addItem(socio);
            }
        }
    }
    private void LimpiarCampos(){
    jtPases.setText("");
    jdcFechaFin.setDate(new Date());
    jtCosto.setText("");
    }
    
    private void marcarFecha(){
        LocalDate hoy=LocalDate.now();
        jl_FechaHoy.setText(hoy.toString());
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbSocio = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtPases = new javax.swing.JTextField();
        jdcFechaFin = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jbGuardar = new javax.swing.JButton();
        jbLimpiar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jtCosto = new javax.swing.JTextField();
        jl_FechaHoy = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        jLabel1.setText("Registro de Membresia");

        jLabel2.setText("Socio:");

        jLabel3.setText("Cantidad de Pases:");

        jLabel4.setText("Fecha de Inicio:");

        jLabel5.setText("Fecha de Finalizacion:");

        jbGuardar.setText("Guardar");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
            }
        });

        jbLimpiar.setText("Limpiar");
        jbLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLimpiarActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jLabel6.setText("Costo:");

        jl_FechaHoy.setText("hoy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbLimpiar)
                                .addGap(110, 110, 110)
                                .addComponent(jbGuardar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbSalir)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jtPases, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jcbSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29))
                                    .addComponent(jl_FechaHoy, javax.swing.GroupLayout.Alignment.LEADING)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jcbSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtPases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jl_FechaHoy))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdcFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbLimpiar)))
                    .addComponent(jbGuardar))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        double costo;
        int pases;
        Membresia mem=new Membresia();
        for(Socio s:socio){
            if (jcbSocio.getSelectedItem().toString().equalsIgnoreCase(s.toString())) {
                mem.setSocio(s);
            }
        }
        if (!jtPases.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido en pases");
            return;
            }
        try{
        pases=Integer.parseInt(jtPases.getText());
        mem.setCantidadPases(pases);
        }catch(NullPointerException e){
        JOptionPane.showMessageDialog(null, "Los pases deben ser enteros");
        }
       
        

        LocalDate fechaI=LocalDate.now();
        mem.setFechaInicio(fechaI);
        
        Date fecha2=jdcFechaFin.getDate();
        LocalDate fechaF=fecha2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(ChronoUnit.DAYS.between(fechaI, fechaF)<0){
            JOptionPane.showMessageDialog(null, "Fecha Vencimiento debe ser mayor a "+fechaI.toString());
            return;
        }
        
        if (!jtCosto.getText().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido en costo");
            return;
            }
        
        mem.setFechaFin(fechaF);
        try{
        costo=Double.parseDouble(jtCosto.getText());
        mem.setCosto(costo);
        }catch(NullPointerException e){
        JOptionPane.showMessageDialog(null, "Los costos deben ser decimales");
        return;
        }
        
        mem.setEstado(true);
        
        memdata.guardarMembresia(mem);

    }//GEN-LAST:event_jbGuardarActionPerformed

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
     dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLimpiarActionPerformed
        LimpiarCampos();
    }//GEN-LAST:event_jbLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbLimpiar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<String> jcbSocio;
    private com.toedter.calendar.JDateChooser jdcFechaFin;
    private javax.swing.JLabel jl_FechaHoy;
    private javax.swing.JTextField jtCosto;
    private javax.swing.JTextField jtPases;
    // End of variables declaration//GEN-END:variables
}
