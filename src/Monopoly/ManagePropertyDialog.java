package Monopoly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import javax.swing.*;

/**
 *
 * @authore Frederik
 */
public class ManagePropertyDialog extends javax.swing.JDialog 
{
    private int numHouses;
    private Property p;
    private Player pl;
    
    private class HotelListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(pl.getBalance() >= p.getOneHousePrice())
                {
                    //Can afford the Hotel
                    logTextArea.setText(logTextArea.getText() + pl.buyHotel(p));
                    
                }
                else
                {
                    logTextArea.setText(logTextArea.getText() + "\nDu kannst dir das Hotel nicht leisten"+ 
                            (p.getOneHousePrice() - pl.getBalance()));
                }
        }
        
    }
    private class MortgageListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if(p.getOwner().equalsIgnoreCase(pl.getName()))
            {
                //Player owns the property
                p.mortgage();
                pl.acceptPayment(p.getMortgagedPrice());
                
                logTextArea.setText(logTextArea.getText() + "\n"+pl.getName() + " hat erfolgreich eine Hyptothek bei " +p.getName()+ " über $" + p.getMortgagedPrice());
            }
            else
            {
                throw new UnknownError(pl.getName() + " gehört nicht" + p.getName());
            }
        }
        
    }
    private class BuyHousesListener implements ActionListener
    {
        
        @Override
        public void actionPerformed(ActionEvent e) 
        {
           boolean isNum = true;
            if(!houseNumField.getText().equalsIgnoreCase(""))
            {
                try
                {
                    numHouses = Integer.parseInt(houseNumField.getText());
                    
                    if(numHouses > 4)
                    {
                        logTextArea.setText(logTextArea.getText() + "\nDie Anzahl der Häuser darf nicht größer als 4 sein");
                    }
                    else if(numHouses > 4 - p.getNumHouses() || numHouses <= 0)
                    {
                        logTextArea.setText(logTextArea.getText() + "\nDie Zahl "+numHouses+" ist kein zulässiger Parameter. "
                                + "\nBitte zwischen 1 und " +(4 - p.getNumHouses())+ "eingeben");
                    }
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(null, "Die Eingabe"+ houseNumField.getText() + " ist keine reale Nummmer. Versuch es erneut");
                    isNum = false;
                }
            }
            
            if(isNum)
            {
                if(pl.getBalance() >= numHouses * p.getOneHousePrice())
                {
                    //Can afford the indicated num of houses
                    logTextArea.setText(logTextArea.getText() + pl.buyHouses(p, numHouses));
                }
                else
                {
                    logTextArea.setText(logTextArea.getText() + "\n"+numHouses + " Kann nicht gewährt werden für $"+ 
                            ((numHouses * p.getOneHousePrice()) - pl.getBalance()));
                }
                
            }
        }
        
    }
    

    /**
     * Creates new form ManagePropertyDialog
     */
    public ManagePropertyDialog(java.awt.Frame parent, Property pr, Player pl) 
    {
        super(parent, true);
        setTitle(pr.getName());
        p = pr;
        this.pl = pl;
        initComponents();
        hotelButton.setEnabled(pl.hasAllPropertiesInGroup(p.getGroup()));
        
        if(!(p instanceof Property))
        {
            logTextArea.setText(p.getName() + " kann nicht ausgebaut werden, du kannst nur eine Hypothek aufnehmen.");
            houseNumField.setEditable(false);
            buyHousesButton.setEnabled(false);
        }
        else
        {
            if(!pl.hasAllPropertiesInGroup(pr.getGroup()))
        {
            buyHousesButton.setEnabled(false);
            logTextArea.setText(logTextArea.getText() + "\nDu beseitzt nicht alle Karten der Gruppe: "+p.getGroup()+" \n"
                    + "Desewegen kann es nicht ausgebaut werden."+p.getName());
        }
        }
        
        buyHousesButton.addActionListener(new BuyHousesListener());
        mortgageButton.addActionListener(new MortgageListener());
        hotelButton.addActionListener(new HotelListener());
        
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imagePanel = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel(p.getPropertyImage());
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        houseNumField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        buyHousesButton = new javax.swing.JButton();
        mortgageButton = new javax.swing.JButton();
        hotelButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        imagePanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        logTextArea.setColumns(20);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        houseNumField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                houseNumFieldActionPerformed(evt);
            }
        });

        jLabel1.setText("Anzahl der Häuser: ");

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        buyHousesButton.setText("Haus kaufen");

        mortgageButton.setText("Hypothek");

        hotelButton.setText("Hotel kaufen");
        hotelButton.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buyHousesButton)
                .addGap(18, 18, 18)
                .addComponent(mortgageButton)
                .addGap(18, 18, 18)
                .addComponent(hotelButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyHousesButton)
                    .addComponent(mortgageButton)
                    .addComponent(hotelButton))
                .addContainerGap())
        );

        jLabel2.setText("Gesamte Kosten : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(houseNumField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(houseNumField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void houseNumFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_houseNumFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_houseNumFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagePropertyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePropertyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePropertyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePropertyDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() 
            {
                Player j = new Player("Jordan", null);
                j.getProperties().add(new Property(
                "Mediterranean Avenue", 60, 2, 10, 30, 90, 160, 250, null, 50, "h", null, "Purple"));
                j.getProperties().add(new Property(
                "Baltic Avenue", 60, 4, 20, 60, 180, 320, 450, null, 50, "h", null, "Purple"));
                ManagePropertyDialog dialog = new ManagePropertyDialog(new javax.swing.JFrame(), new Property(
                "Mediterranean Avenue", 60, 2, 10, 30, 90, 160, 250, null, 50, "h", null, "Purple"), j);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buyHousesButton;
    private javax.swing.JButton hotelButton;
    private javax.swing.JTextField houseNumField;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JButton mortgageButton;
    // End of variables declaration//GEN-END:variables
}
