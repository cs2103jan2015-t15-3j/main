import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.JTabbedPane;
import javax.swing.DropMode;
import javax.swing.border.LineBorder;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Choice;
import java.awt.TextArea;
import java.awt.List;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.ImageIcon;



public class SampleFrame {

	private JFrame frame;
	private JTable table;
	private JTable table1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SampleFrame window = new SampleFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SampleFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(){
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(124, 252, 0));
		frame.setBounds(100, 100, 735, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null); 
				
		JTextArea txtrHello = new JTextArea();
		txtrHello.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtrHello.setFont(new Font("Cambria", Font.PLAIN, 13));
		txtrHello.setForeground(new Color(0, 0, 0));
		txtrHello.setBackground(new Color(124, 252, 0));
		txtrHello.setBounds(69, 76, 423, 23);
		txtrHello.setText("search EE2024");
		frame.getContentPane().add(txtrHello);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.setForeground(Color.WHITE);
		btnEnter.setBackground(Color.DARK_GRAY);
		btnEnter.setBounds(495, 76, 75, 23);
		frame.getContentPane().add(btnEnter);
		
		JLabel lblourProjectName = new JLabel("ProTask");
		lblourProjectName.setForeground(Color.DARK_GRAY);
		lblourProjectName.setFont(new Font("Agency FB", Font.BOLD, 32));
		lblourProjectName.setBounds(110, 13, 108, 50);
		frame.getContentPane().add(lblourProjectName);
		
		JTextArea txtrEeTutorialHas = new JTextArea();
		txtrEeTutorialHas.setText("1 result found.");
		txtrEeTutorialHas.setFont(new Font("Cambria", Font.PLAIN, 13));
		txtrEeTutorialHas.setForeground(Color.WHITE);
		txtrEeTutorialHas.setBackground(Color.DARK_GRAY);
		txtrEeTutorialHas.setBounds(69, 320, 501, 40);
		frame.getContentPane().add(txtrEeTutorialHas);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(69, 109, 501, 198);
		frame.getContentPane().add(tabbedPane);
		
		ImageIcon icon = new ImageIcon("src/Purple-Pear-400px.png");
		table = new JTable();
		table.setCellSelectionEnabled(true);
		tabbedPane.addTab("To-do", icon, table, null);
		table.setFont(new Font("Cambria", Font.PLAIN, 13));
		table.setForeground(Color.BLACK);
		table.setBorder(new EmptyBorder(1, 0, 1, 0));
		table.setBackground(new Color(245, 255, 250));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "Task Name", "Start", "End", "Comments"},
				{"3", "CS2103 Homework", "09/02/15", "09/02/15", "User stories"},
				{"4", "CG2271 Assignment", "15/02/15, 12:00AM", "16/02/15  11:59PM", "Lab 1"},
				{"5", "EE2024 Tutorial", "20/02/15", "20/02/15", ""},
				{"6", "Date with Samson", "27/02/15", "27/02/15", "Marina Bay Sands Restaurant"},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Task Name", "New column", "From", "To", "Description"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(28);
		table.getColumnModel().getColumn(1).setPreferredWidth(225);
		table.getColumnModel().getColumn(2).setPreferredWidth(145);
		table.getColumnModel().getColumn(3).setPreferredWidth(145);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		
		table1 = new JTable();
		tabbedPane.addTab("Completed", icon, table1, null);
		table1.setFont(new Font("Cambria", Font.PLAIN, 13));
		table1.setForeground(Color.BLACK);
		table1.setBorder(new EmptyBorder(1, 0, 1, 0));
		table1.setBackground(new Color(245, 255, 250));
		table1.setToolTipText("");
		table1.setModel(new DefaultTableModel(
			new Object[][] {
				{"ID", "Task Name", "Start", "End", "Comments"},
				{"1", "CS2010 Tutorial 1", "01/02/15", "01/02/15", "Coding for Question 1"},
				{"2", "Dinner with Damith", "03/02/15, 6:00PM", "03/02/15  8:00PM", "Concorde Hotel"},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"Task Name", "New column", "From", "To", "Description"
			}
		));
		table1.getColumnModel().getColumn(0).setPreferredWidth(28);
		table1.getColumnModel().getColumn(1).setPreferredWidth(225);
		table1.getColumnModel().getColumn(2).setPreferredWidth(145);
		table1.getColumnModel().getColumn(3).setPreferredWidth(145);
		table1.getColumnModel().getColumn(4).setPreferredWidth(130);
		
		
	}
 
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
