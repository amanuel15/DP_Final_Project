/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logindesign.views;

import logindesign.singelton.JDBCSingelton;
import logindesign.composite.CartComposite;
import logindesign.composite.Product;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import logindesign.state.*;
import logindesign.strategy.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author aman
 */
public class CustomerJFrame extends javax.swing.JFrame {

    PreparedStatement pst = null;
    ResultSet res = null;
    CartComposite chartComposite = new CartComposite();
    Product product1;
    StateContext stateContext = new StateContext();
    public CustomerJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        showTable();
    }
    
    public void showTable(){
       
        try{
            String sql = "SELECT * FROM products";
            pst = JDBCSingelton.getConnection().prepareStatement(sql);
            res = pst.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(res));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
    }
    
    public void addToCart(){
        // call the composite pattern in use
        TableModel tableModel = jTable1.getModel();
        int column = jTable1.convertRowIndexToModel(jTable1.getSelectedRow());
        String name = tableModel.getValueAt(column,1).toString();
        Double price = Double.parseDouble(tableModel.getValueAt(column,3).toString());
        int amount = (Integer)jSpinner1.getValue();
        
        product1 = new Product(name,price,amount);
        chartComposite.addProduct(product1);
        
        // view the added cart items on the left "cart table"
        if(column >= 0){
            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            model.addRow(new Object[]{tableModel.getValueAt(column,0),tableModel.getValueAt(column,1),
                tableModel.getValueAt(column,2),jSpinner1.getValue(),(amount*price)
            });
        }else{
            JOptionPane.showMessageDialog(null, "Please select a product to add first!");
        }
        calculatePrice();
        System.out.println(chartComposite.productsList);
    }
    
    public void removeFromCart(){
        TableModel tableModel = jTable2.getModel();
        int column = jTable2.convertRowIndexToModel(jTable2.getSelectedRow());
        String name = tableModel.getValueAt(column,1).toString();
        int amount = (Integer)tableModel.getValueAt(column,3);
        Long price = Long.parseLong(tableModel.getValueAt(column,4).toString())/amount;
        
        //product1 = new Product(name,price,amount);
        chartComposite.removeProduct(column);
        
        if(column >= 0){
            DefaultTableModel model = (DefaultTableModel)jTable2.getModel();
            model.removeRow(column);
        }else{
            JOptionPane.showMessageDialog(null, "Please select a product to remove first!");
        }
        calculatePrice();System.out.println(chartComposite.productsList);
    }
    
    public void calculatePrice(){
        //chartComposite.getPrice();
        totalLabel.setText(chartComposite.getPrice()+"$");
    }
    
    public void updateInventory(){
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchTextField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cartAddButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buyButton = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        totalLabel = new javax.swing.JLabel();
        discountTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1040, 555));
        setResizable(false);

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        jTable1.setDefaultEditor(Object.class, null);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Name", "Category", "Price", "Left in stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Cart items");

        cartAddButton.setText("Select");
        cartAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartAddButtonActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Name", "Category", "Item amount", "Price x amount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        buyButton.setText("Buy!");
        buyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyButtonActionPerformed(evt);
            }
        });

        jSpinner1.setValue(1);

        discountTextField.setForeground(new java.awt.Color(153, 153, 153));
        discountTextField.setText("I have discount code");
        discountTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                discountTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                discountTextFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cartAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(discountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(searchTextField))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(cartAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(discountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(totalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void discountTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_discountTextFieldFocusLost
        if(discountTextField.getText().trim().equals("") ||
            discountTextField.getText().trim().toLowerCase().equals("i have discount code")){
            discountTextField.setText("I have discount code");
            discountTextField.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_discountTextFieldFocusLost

    private void discountTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_discountTextFieldFocusGained
        if(discountTextField.getText().trim().toLowerCase().equals("i have discount code")){
            discountTextField.setText("");
            discountTextField.setForeground(Color.black);
        }
    }//GEN-LAST:event_discountTextFieldFocusGained

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jTable1.clearSelection();
        cartAddButton.setText("Remove");
        RemoveState removeState = new RemoveState();
        removeState.updateCart(stateContext);
    }//GEN-LAST:event_jTable2MouseClicked

    private void cartAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartAddButtonActionPerformed
        // Give the state to either add or remove from cart
        try{
            if(stateContext.getState().stateName().equals("add")){
                addToCart();
            } else if (stateContext.getState().stateName().equals("remove")){
                removeFromCart();
            }
        } catch(NullPointerException ex){
            System.out.println("Make sure to select an item!");
        }
        jSpinner1.setValue(1);
        cartAddButton.setText("Select");
    }//GEN-LAST:event_cartAddButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jTable2.clearSelection();
        cartAddButton.setText("Add");
        AddState addState = new AddState();
        addState.updateCart(stateContext);
    }//GEN-LAST:event_jTable1MouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        try{
            if(searchTextField.getText().equals("all")){
                showTable();
            } else{
                String sql = "SELECT * FROM products WHERE name LIKE ? OR type LIKE ?";//empname like '%"+pat+"%'"
                pst = JDBCSingelton.getConnection().prepareStatement(sql);
                pst.setString(1, "%"+searchTextField.getText()+"%");
                pst.setString(2, "%"+searchTextField.getText()+"%");
                res = pst.executeQuery();
                jTable1.setModel(DbUtils.resultSetToTableModel(res));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }//GEN-LAST:event_searchButtonActionPerformed

    private void buyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyButtonActionPerformed
        if(jTable2.getRowCount() < 1){System.out.println("no items");return;}
        String sql = "SELECT amount FROM discounts WHERE code = ?";
        try {
            pst = JDBCSingelton.getConnection().prepareStatement(sql);
            pst.setString(1,discountTextField.getText());
            res = pst.executeQuery();
            if(res.next()){
                Double discount = res.getDouble(1);
                System.out.println("discount: "+discount);
                if(discount < 1){
                    BillingContext context = new BillingContext(new PercentoffStrategy());
                    conformPurchase((context.executeStrategy(chartComposite.getPrice(), discount)));
                }
                else {
                    BillingContext context = new BillingContext(new DeductStrategy());
                    conformPurchase((context.executeStrategy(chartComposite.getPrice(), discount)));
                }
            } else{
                JOptionPane.showMessageDialog(null, "The discount code you typed doesnt exist!/n"
                        + "Price will be calculated without discount");
                conformPurchase(chartComposite.getPrice());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }  
    }//GEN-LAST:event_buyButtonActionPerformed
    
    public void conformPurchase(Double total){
        JOptionPane.showMessageDialog(null, "Purchase successfull\nYou spent: $"+total);
        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
        dtm.setRowCount(0);
    }
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
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buyButton;
    private javax.swing.JButton cartAddButton;
    private javax.swing.JTextField discountTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchTextField;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
