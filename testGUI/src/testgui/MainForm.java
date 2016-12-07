/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testgui;

import java.awt.Toolkit;
import jGetFreeProxyList.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author ilya.gulevskiy
 */
public class MainForm extends javax.swing.JFrame {

	/**
	 * Creates new form MainForm
	 */
	public MainForm() {
		initComponents();
		
		init();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelMain = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAmountThreads = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldAwaitGetProxy = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldAwaitTestProxy = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldCapacityProxiesQueue = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldURLConnectionTimeOut = new javax.swing.JTextField();
        jButtonStart = new javax.swing.JButton();
        jProgressBarGet = new javax.swing.JProgressBar();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jProgressBarTest = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaTestByUrls = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaGetProxyUrls = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaDone = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Test of jGetFreeProxyList");
        setLocationByPlatform(true);

        jLabel1.setText("<html>List of public urls to try to<br> connect with proxies:</html>");

        jLabel2.setText("<html>List of public urls, where possible<br> to find proxies:</html>");

        jLabel3.setText("<html>How much threads have to be<br> for test proxies:</html>");

        jTextFieldAmountThreads.setEnabled(false);

        jLabel4.setText("<html>How long await end of work <br>GetProxy threads, in seconds:</html>");

        jTextFieldAwaitGetProxy.setEnabled(false);

        jLabel5.setText("<html>How long await end of work <br>TestProxy threads, in seconds:</html>");

        jTextFieldAwaitTestProxy.setEnabled(false);

        jLabel6.setText("<html>Capacity of Queue, must be <br>greater than threads:</html>");

        jTextFieldCapacityProxiesQueue.setEnabled(false);

        jLabel7.setText("<html>How long await when connect <br>to URL, in seconds:</html>");

        jTextFieldURLConnectionTimeOut.setEnabled(false);

        jButtonStart.setText("Start get and test free proxy");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jLabel8.setText("Get proxies:");

        jLabel9.setText("Test proxies:");

        jLabel10.setText("Tested proxies:");

        jTextAreaTestByUrls.setColumns(20);
        jTextAreaTestByUrls.setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        jTextAreaTestByUrls.setRows(5);
        jTextAreaTestByUrls.setEnabled(false);
        jScrollPane1.setViewportView(jTextAreaTestByUrls);

        jTextAreaGetProxyUrls.setColumns(20);
        jTextAreaGetProxyUrls.setFont(new java.awt.Font("Monospaced", 0, 10)); // NOI18N
        jTextAreaGetProxyUrls.setRows(5);
        jTextAreaGetProxyUrls.setEnabled(false);
        jScrollPane2.setViewportView(jTextAreaGetProxyUrls);

        jTextAreaDone.setColumns(20);
        jTextAreaDone.setRows(5);
        jScrollPane3.setViewportView(jTextAreaDone);

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jProgressBarGet, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(14, 14, 14)
                        .addComponent(jProgressBarTest, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jScrollPane2)
                            .addComponent(jTextFieldAmountThreads)))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jTextFieldAwaitGetProxy))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(jTextFieldAwaitTestProxy))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jTextFieldCapacityProxiesQueue))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jTextFieldURLConnectionTimeOut))
                    .addGroup(jPanelMainLayout.createSequentialGroup()
                        .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(jPanelMainLayout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(jButtonStart)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAmountThreads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAwaitGetProxy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAwaitTestProxy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCapacityProxiesQueue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldURLConnectionTimeOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jButtonStart)
                .addGap(18, 18, 18)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBarGet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jProgressBarTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
		
		// Block Main button
		this.jButtonStart.setEnabled(false);
		
		final jGetFreeProxyList jGetFreeProxyList = new jGetFreeProxyList(
				
			// Listener for consumer's communications
			new jGetFreeProxyListListener(){
				
				/** Method will cal every second and give a consumer percentage of work, 100 - is 100%.
				 * To give opportunity to make a process bar or other information things.
				 * Be aware, testProxyPerc will be zero until getProxyPerc became 100.
				 * 
				 * @param getProxyPerc - percentage of ask urls with proxies
				 * @param testProxyPerc - percentage of test proxies
				 */
				@Override
				public void process(int getProxyPerc, int testProxyPerc){
                    jProgressBarGet.setValue(getProxyPerc);
					jProgressBarTest.setValue(testProxyPerc);
				}
				
				/**
				 * Will call when all work is done. Give ArrayList of tested proxies.
				 * 
				 * @param testedProxies - list of tested proxies
				 * @param errors - structure of errors. <code>null</code> if it was no errors.
				 */
				@Override
				public void done(ArrayList<ProxyItem> testedProxies, WorkErrors errors){
					
					// Print tested proxies
					if(testedProxies.size() > 0){
						StringBuffer results = new StringBuffer();
						for(ProxyItem s: testedProxies) results.append(s.toString() + "\n");
						jTextAreaDone.setText( results.substring(0, results.length() - 1) );
					}
					
					// Print errors if they happend
                    if (null != errors && !errors.WithoutProxies.isEmpty()){
						StringBuffer results2 = new StringBuffer();
						results2.append(jTextAreaDone.getText());
						for(InfoUrl s: errors.WithoutProxies) results2.append("\n" + s.toString());
                        jTextAreaDone.setText( results2.toString() );
                    }
                   
					// Unblock Main button
					jButtonStart.setEnabled(true);
					
				}
			}
		);
		
		// Do it asynchronously
		SwingWorker worker = new SwingWorker<Integer, Integer>() {
			@Override
			protected Integer doInBackground() throws Exception {
				
				try {
					jGetFreeProxyList.run();
				}
				catch(InterruptedException e) {
					jTextAreaDone.setText( e.getMessage() );
				}
		
				return 42;
			}

		};
		worker.execute();
		
		
    }//GEN-LAST:event_jButtonStartActionPerformed

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
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainForm().setVisible(true);
			}
		});
	}
	
	public void init(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/logo.32x32.png")));
		
		// First invokation to initialize static constructor
		Settings.getVersion();
		
		// Init form fields
		StringBuffer testByUrls = new StringBuffer();
		for(java.net.URL url:Settings.TestByUrls) testByUrls.append(url.toString() + "\n");
		this.jTextAreaTestByUrls.setText(testByUrls.substring(0, testByUrls.length() - 1));
		
		StringBuffer getProxyUrls = new StringBuffer();
		for(InfoUrl iu:Settings.GetProxyUrls) getProxyUrls.append(iu.Url.toString() + "\n");
		this.jTextAreaGetProxyUrls.setText(getProxyUrls.substring(0, getProxyUrls.length() - 1));
		
		this.jTextFieldAmountThreads.setText(Integer.toString(Settings.AmountThreads));
		this.jTextFieldAwaitGetProxy.setText(Integer.toString(Settings.AwaitGetProxy));
		this.jTextFieldAwaitTestProxy.setText(Integer.toString(Settings.AwaitTestProxy));
		this.jTextFieldCapacityProxiesQueue.setText(Integer.toString(Settings.CapacityProxiesQueue));
		this.jTextFieldURLConnectionTimeOut.setText(Integer.toString(Settings.URLConnectionTimeOut));
		
		// Be aware: minimum is 0, maximum is 100
		this.jProgressBarGet.setMinimum(0);
		this.jProgressBarGet.setMaximum(100);
		this.jProgressBarTest.setMinimum(0);
		this.jProgressBarTest.setMaximum(100);
		
		jTextAreaDone.setText("");
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanelMain;
    public javax.swing.JProgressBar jProgressBarGet;
    public javax.swing.JProgressBar jProgressBarTest;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextAreaDone;
    private javax.swing.JTextArea jTextAreaGetProxyUrls;
    private javax.swing.JTextArea jTextAreaTestByUrls;
    private javax.swing.JTextField jTextFieldAmountThreads;
    private javax.swing.JTextField jTextFieldAwaitGetProxy;
    private javax.swing.JTextField jTextFieldAwaitTestProxy;
    private javax.swing.JTextField jTextFieldCapacityProxiesQueue;
    private javax.swing.JTextField jTextFieldURLConnectionTimeOut;
    // End of variables declaration//GEN-END:variables
}
