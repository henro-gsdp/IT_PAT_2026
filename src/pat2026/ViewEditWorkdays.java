package pat2026;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ViewEditWorkdays extends javax.swing.JDialog {

    private String workerCode;
    private Workday[] workdays;
    private int workdayCount;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public ViewEditWorkdays(java.awt.Frame parent, boolean modal, String workerCode) {
        super(parent, modal);
        this.workerCode = workerCode;
        initComponents();
        setupBackground();
        tblViewWork.setDefaultEditor(Object.class, null); // read-only cells
        displayTableDetails();
    }

    private void setupBackground() {
        this.getContentPane().setBackground(new java.awt.Color(0, 0, 0, 0));
        javax.swing.JLabel background = new javax.swing.JLabel(new javax.swing.ImageIcon(getClass().getResource("/pat2026/public/image_470e50a4.jpg")));
        this.getLayeredPane().add(background, javax.swing.JLayeredPane.FRAME_CONTENT_LAYER);
        background.setBounds(0, 0, this.getWidth(), this.getHeight());

    }

    private void displayTableDetails() {
        DefaultTableModel model = (DefaultTableModel) tblViewWork.getModel();
        model.setRowCount(0); // clears the empty placeholder row from the designer

        try {
            GetWorkerWorkdays workdayFetcher = new GetWorkerWorkdays();

            if (workdayFetcher.getWorkdayData(workerCode)) {
                workdays = workdayFetcher.getWorkdays();
                workdayCount = workdayFetcher.getCount();

                for (int i = 0; i < workdayCount; i++) {
                    Object[] row = {
                        workdays[i].getJobID(),
                        workdays[i].getDateWorked().format(dateFormat),
                        workdays[i].getStartTime().format(timeFormat),
                        workdays[i].getLunchStart().format(timeFormat),
                        workdays[i].getLunchEnd().format(timeFormat),
                        workdays[i].getEndTime().format(timeFormat),
                        formatDuration(workdays[i].getHoursWorked()),
                        workdays[i].getTypeOfWork(),
                        workdays[i].getRowsCompleted(),
                        "Edit"
                    };
                    model.addRow(row);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No workdays have been logged yet.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Could not load workday records: " + e.getMessage());
        }
    }

// Use the formatDuration you already wrote in ViewWork. This is a simple version:
    private String formatDuration(Duration d) {
        return d.toHours() + "h " + d.toMinutesPart() + "m";
    }

// Designer: right-click tblViewWork > Events > Mouse > mouseClicked
    private void tblViewWorkMouseClicked(java.awt.event.MouseEvent evt) {
        int row = tblViewWork.rowAtPoint(evt.getPoint());
        int col = tblViewWork.columnAtPoint(evt.getPoint());

        if (row < 0 || col != 9) {
            return; // empty space, or not the Edit column
        }

        String jobID = tblViewWork.getValueAt(row, 0).toString();

        // PLACEHOLDER: open the screen for this workday
        // EditWorkday dialog = new EditWorkday((java.awt.Frame) this.getParent(), true, jobID);
        // dialog.setVisible(true);
        // displayTableDetails(); // refresh after edits
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrlViewWork = new javax.swing.JScrollPane();
        tblViewWork = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblViewWork.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblViewWork.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "JobID", "Date", "Time Start", "Lunch Start", "Lunch End", "Time End", "Time worked", "Type of Work", "Rows Complete", "Type Of Work"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrlViewWork.setViewportView(tblViewWork);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 51));
        jLabel1.setText("Workdays Information:");

        jButton1.setText("Return to Home");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrlViewWork)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 197, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(scrlViewWork, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane scrlViewWork;
    private javax.swing.JTable tblViewWork;
    // End of variables declaration//GEN-END:variables
}
