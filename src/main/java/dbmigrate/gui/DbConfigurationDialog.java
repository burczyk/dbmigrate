/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DbConfigurationDialog.java
 *
 * Created on 2011-12-01, 17:44:14
 */
package dbmigrate.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbmigrate.model.db.DbConnector;

// CHECKSTYLE:OFF

/**
 * 
 * @author zyxist
 */
public class DbConfigurationDialog extends javax.swing.JDialog {
	public DbConnector getConnector() {
		return connector;
	}

	public void setConnector(DbConnector connector) {
		this.connector = connector;
	}

	private DbConnector connector;
	private JTextField hostnameText;
	private JTextField usernameText;
	private JPasswordField passwordText;
	private JTextField databaseText;

	/** Creates new form DbConfigurationDialog */
	public DbConfigurationDialog(java.awt.Frame parent, boolean modal) {
		super(parent, true);
		this.setResizable(false);
		this.setPreferredSize(new Dimension(350, 160));
		this.initComponents();

		// create and load default properties
		Properties defaultProps = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream("dbmigrate.properties");
			defaultProps.load(in);
			in.close();
			this.hostnameText.setText(defaultProps.getProperty("hostname", ""));
			this.usernameText.setText(defaultProps.getProperty("username", ""));
			this.databaseText.setText(defaultProps.getProperty("database", ""));
			this.passwordText.setText(defaultProps.getProperty("password", ""));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
	private void initComponents() {
		Font fontStyle = new Font("Verdana", Font.PLAIN, 11);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		JLabel lblHostname = new JLabel("Hostname");
		lblHostname.setBounds(54, 12, 72, 15);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(54, 42, 72, 15);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(54, 72, 70, 15);

		JLabel lblDatabase = new JLabel("Database");
		lblDatabase.setBounds(57, 102, 69, 15);

		this.hostnameText = new JTextField();
		this.hostnameText.setBounds(157, 12, 114, 22);
		this.hostnameText.setColumns(10);

		this.usernameText = new JTextField();
		this.usernameText.setBounds(157, 42, 114, 22);
		this.usernameText.setColumns(10);

		this.passwordText = new JPasswordField();
		this.passwordText.setBounds(157, 72, 114, 22);
		this.passwordText.setColumns(10);

		this.databaseText = new JTextField();
		this.databaseText.setBounds(157, 102, 114, 22);
		this.databaseText.setColumns(10);

		this.usernameText.setFont(fontStyle);
		this.hostnameText.setFont(fontStyle);
		this.passwordText.setFont(fontStyle);
		this.databaseText.setFont(fontStyle);

		this.getContentPane().setLayout(null);
		this.getContentPane().add(lblHostname);
		this.getContentPane().add(this.hostnameText);
		this.getContentPane().add(lblPassword);
		this.getContentPane().add(lblUsername);
		this.getContentPane().add(lblDatabase);
		this.getContentPane().add(this.databaseText);
		this.getContentPane().add(this.passwordText);
		this.getContentPane().add(this.usernameText);

		JButton okButton = new JButton("Ok");
		okButton.setBounds(54, 129, 117, 25);
		this.getContentPane().add(okButton);

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DbConfigurationDialog.this.connector.getConnection(DbConnector.DB_TYPE,
						DbConfigurationDialog.this.hostnameText.getText(), DbConfigurationDialog.this.databaseText.getText(),
						DbConfigurationDialog.this.usernameText.getText(), DbConfigurationDialog.this.passwordText.getText());
				DbConfigurationDialog.this.saveProperties();
				DbConfigurationDialog.this.setVisible(false);
			}
		});

		JButton cancelButton = new JButton("Cancel");

		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveProperties();
				DbConfigurationDialog.this.setVisible(false);
			}
		});
		cancelButton.setBounds(183, 129, 117, 25);
		this.getContentPane().add(cancelButton);

		this.pack();
	}// </editor-fold>                        

	private void saveProperties() {
		FileOutputStream out;
		try {
			out = new FileOutputStream("dbmigrate.properties");
			Properties defaultProps = new Properties();
			defaultProps.setProperty("hostname", this.hostnameText.getText());
			defaultProps.setProperty("username", this.usernameText.getText());
			defaultProps.setProperty("password", this.passwordText.getText());
			defaultProps.setProperty("database", this.databaseText.getText());
			defaultProps.store(out, "---No Comment---");
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					DbConfigurationDialog.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					DbConfigurationDialog.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					DbConfigurationDialog.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					DbConfigurationDialog.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {

			public void run() {
				DbConfigurationDialog dialog = new DbConfigurationDialog(
						new javax.swing.JFrame(), true);
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
}
