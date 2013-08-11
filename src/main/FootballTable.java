package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Frame to display football data within a table
 * 
 * @author Jason
 *
 */
public class FootballTable extends JFrame implements ActionListener
{
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private JFileChooser fileChooser;
	private SmartFileReader smartReader = new SmartFileReader();
	private List<Team> teamList = new ArrayList<Team>();
	private FootballTeamTableModel tableModel;
	private JPanel mainPanel;
	
	public FootballTable()
	{
		super("Football");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		constructMenu();
		constructTable();
		pack();
		setVisible(true);
		
		fileChooser = new JFileChooser(System.getProperty("user.dir")+"\\src\\data\\");
	}
	
	public void constructTable()
	{
		tableModel = new FootballTeamTableModel();
        JTable table = new JTable();
        table.setModel(tableModel);
        
        JScrollPane scroller = new JScrollPane(table);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        
        mainPanel.add(scroller, BorderLayout.CENTER);
	}
	
	public void constructMenu()
	{
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		menuItem = new JMenuItem("Load Data",
                KeyEvent.VK_L);
		menu.add(menuItem);
		
		menuItem.addActionListener(this);
		
		setJMenuBar(menuBar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource().equals(menuItem))
		{
			selectFileToLoad();
		}
		
	}

	private void selectFileToLoad()
	{
		int retrunedInt = fileChooser.showOpenDialog(this);
		
		if(retrunedInt == JFileChooser.APPROVE_OPTION)
		{
			teamList = smartReader.readInto(fileChooser.getSelectedFile(), Team.class);
			tableModel.setColumnNames(smartReader.getHeaderNames());
			tableModel.setData(teamList);
			tableModel.fireTableStructureChanged();
		}
	}
}
