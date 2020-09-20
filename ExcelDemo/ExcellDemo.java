import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ExcellDemo extends JFrame{
	
	private JScrollPane scrollPane =null;
	private JTable table = null; 
	private JTable headerTable = null;
	private JMenuBar menuBar = null;
	private JMenu fileMenu, formulasMenu, functionMenu;
	private JMenuItem newItem, open, save, exit, sum, count, max ,min,average;
	private String title;
	private int cardinality, degree;
	
	/**
	 * 
	 * @param operation : get fomularFuction name to string 
	 * @param e ActionEvent object 
	 */
	private void fomulaFuction(String operation, ActionEvent e) // print fomulaFuction output value at selected cell   
	{
		
		String range = JOptionPane.showInputDialog(null,"Function Arguments",operation.toUpperCase(),JOptionPane.DEFAULT_OPTION); // make input dialog with operation name
		if(range == null) // when close dialog		
		{
			return;
		}
				
		String first,last; 
		int start_column =0, end_column = 0;
		int start_row = 0, end_row = 0; 
		Double sum = 0.0;
		Double min = Double.MAX_VALUE;
		Double max = Double.MIN_VALUE;
		Double count = 0.0;
		Double value;
		StringTokenizer token = new StringTokenizer(range,":"); // check input range with delimiter :
		first = token.nextToken();
		last = token.nextToken();
	    start_column = (int)first.charAt(0)-65; // ex) case of A, column index is 0
		start_row = Integer.parseInt(first.substring(1)); // ex) when start low is  "A12" , only get row information 12
		end_column = (int)last.charAt(0)-65;
		end_row = Integer.parseInt(last.substring(1));

		switch(operation) 
		{
			case "sum" :
				for(int i = 0 ; i <= end_row - start_row ; i++)
				{
					for(int j = 0 ; j <= end_column - start_column ; j++)
					{											
						if(table.getValueAt(i+start_row, j+start_column).toString().equals("")!=true) // when cell is not empty cell
						{
							sum = sum + Double.parseDouble(table.getValueAt(i+start_row, j+start_column).toString()); //get value from cell and add in sum 
							count ++;
						}
				
					}
				}
				if(count != 0) // when there is at least one number in cells
				{ 
					table.setValueAt(sum.toString(),cardinality , degree); // print sum output in selected cell
				}
				break;
			case "average" :
				for(int i = 0 ; i <= end_row - start_row ; i++)
				{
					for(int j = 0 ; j <= end_column - start_column ; j++)
					{												
						if(table.getValueAt(i+start_row, j+start_column).toString().equals("")!=true) // when cell is not empty cell
						{
							sum = sum + Double.parseDouble(table.getValueAt(i+start_row, j+start_column).toString()); //get value from cell and add in sum
							count ++;
						}
			
					}	
				}
				if(count != 0)  // when there is at least one number in cell
				{
					value = sum/count; // calculate average
					table.setValueAt(value.toString(),cardinality , degree); // print average output in selected cell
				}
				break;
			case "count":
				for(int i = 0 ; i <= end_row - start_row ; i++)
				{
					for(int j = 0 ; j <= end_column - start_column ; j++) 
					{											
						if(table.getValueAt(i+start_row, j+start_column).toString().equals("")!=true) // when cell is not empty cell
						{
							count ++;
						}
					
					}
				}
				Integer ct = count.intValue(); // type cast double to int
				table.setValueAt(ct.toString(),cardinality , degree);// print count value in selected cell
				break;
			case "min" :
				for(int i = 0 ; i <= end_row - start_row ; i++)
				{
					for(int j = 0 ; j <= end_column - start_column ; j++)
					{											
						if(table.getValueAt(i+start_row, j+start_column).toString().equals("")!=true) // when cell is not empty cell
						{
							value = Double.parseDouble(table.getValueAt(i+start_row, j+start_column).toString());
							if(value < min) // find min value from cells
							{
								min = value;
							}
							count ++;
						}
					
					}
				}
				if(count != 0) // if there is at least one number in cells
				{
					table.setValueAt(min.toString(),cardinality , degree); // print min output in selected cell
				}

				break;
			case "max" :
				for(int i = 0 ; i <= end_row - start_row ; i++)
				{
					for(int j = 0 ; j <= end_column - start_column ; j++)
					{											
						if(table.getValueAt(i+start_row, j+start_column).toString().equals("")!=true) // when cell is not empty cell
						{
							value = Double.parseDouble(table.getValueAt(i+start_row, j+start_column).toString());
							if(value > max) // find max value from cell
							{
								max = value;
							}
							count ++;
						}
					
					}
				}
				if(count != 0) // if there is at least one number in cells
				{
					table.setValueAt(max.toString(),cardinality , degree);// print max output in selected cell
				}
				break;
		}		
	}
	
	
	private void createMenu()
	{
		// set menuBar
		menuBar = new JMenuBar();
		
		// set menu
		fileMenu = new JMenu("File");
		formulasMenu = new JMenu("Formulas");
		functionMenu = new JMenu("Fuction");
		
		//add menu 
		formulasMenu.add(functionMenu);
		menuBar.add(fileMenu);
		menuBar.add(formulasMenu);
		
		//make filemMenu items 
		newItem = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		
		//make functionMenu items (
		sum = new JMenuItem("SUM");
		average = new JMenuItem("AVERAGE");;
		count = new JMenuItem("COUNT");
		max = new JMenuItem("MAX");
		min = new JMenuItem("MIN");
		
		// add fileMenu's items in fileMenu
	    fileMenu.add(newItem);
		fileMenu.add(open);
		fileMenu.addSeparator(); // separator between two menu
		fileMenu.add(save);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		
		//add fuctionMenu's items in fuctionMenu
		functionMenu.add(sum);
		functionMenu.add(average);
		functionMenu.add(count);
		functionMenu.add(max);
		functionMenu.add(min);
		
		this.setJMenuBar(menuBar); // set visible menuBar in frame
		
	}
	
	public ExcellDemo()
	{
		Character alphabet = 65; // ASCII code
		int column_size = 26; 
		int row_size = 100;
		int width = 610;
		int height = 588;
		String[] table_columnNames = new String[column_size];
		String[] headerTable_columnNames = new String[1];
		String[][] table_data = new String[row_size][column_size];
		String[][] headerTable_data = new String[row_size][1];
	    headerTable_columnNames[0] = new String(); // "" cell
	  
		
		// make table's column header name
		for(int i = 0 ; i < table_columnNames.length ; i++)
		{
			table_columnNames[i] = (alphabet++).toString(); //add A to Z in table header
		}
		
		// make default table data
		for(int i = 0 ;  i < row_size ; i++)
		{
			for(int j = 0 ; j < column_size ; j++)
			{
				table_data[i][j] = new String(); // "" cells
		    }
		}
		// make headerTable's row header value
		for(int i = 0 ; i < row_size ; i ++)
		{
			Integer row = i;
			headerTable_data[i][0] = row.toString(); // add 0 to 99 in headerTable row
		}
		 								
		
		// JFrame setting
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when close frame, deallocate and end program
		this.setSize(width, height); // frame size width : 610 height : 588
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize(); //get screen size
		Dimension framesize = this.getSize(); //get frame size
		//set frame location in center
		this.setLocation((screensize.width - framesize.width) / 2, (screensize.height - framesize.height) / 2); 
							
		this.setTitle("새 Microsoft Excel 워크시트.xlsx - Excel");
		
		// table setting
		table = new JTable(table_data,table_columnNames);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // to have origin size of cell
		table.setFillsViewportHeight(true); // use all area of pane
		table.setCellSelectionEnabled(true); // when click a cell, only select one cell
		table.getTableHeader().setReorderingAllowed(false); // can't change column by dragging
		table.getTableHeader().setEnabled(false); // can't change size , value of header;
		table.addMouseListener(new MouseAdapter() // mouse event in table
				{
					@Override
					public void mouseClicked(MouseEvent e) 
					{
						//when click the mouse, store row and column index value  
						cardinality = table.getSelectedRow(); //row
						degree = table.getSelectedColumn(); //column
					}
				});
				
	    scrollPane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); // make vertical, horizontal scrollbar , add table in scroll pane
	    scrollPane.setViewportView(table);
		
		// header table setting
		headerTable = new JTable(headerTable_data,headerTable_columnNames); // make table a~ z , 0~99 with blank cells
		headerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // to have origin size of cell
		headerTable.setBackground(new Color(238,238,238)); // set color same with table header color
		headerTable.setPreferredSize(new Dimension(50,headerTable.getMaximumSize().height)); // set size when headerTalbe added into RowHeader , to have origin height of headerTable
	    headerTable.setEnabled(false); //can't change cell's value , size
	    //set header table renderer
		headerTable.getColumnModel().getColumn(0).setCellRenderer(new TableCellRenderer(){

				@Override
			//if cell's value is changed or selected, return new label
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) 
				{
					
					JLabel label = new JLabel(value+""); 
					label.setOpaque(true);
					if(isSelected ==false) // not selected cells
				    {
						
						label.setFont(new Font("Dialog",Font.PLAIN,13));//set plain font , size 13
						label.setBackground(new Color(238,238,238));
						label.setHorizontalAlignment(SwingConstants.CENTER);// align in center
				    }
					else if(isSelected ==true) // selected cells
					{
						label.setFont(new Font("Dialog",Font.BOLD,13)); //set bold font , size 13
						label.setHorizontalAlignment(SwingConstants.CENTER); // align in center
						label.setBackground(new Color(255,255,255)); // change selected cell's color in white
					}
				
					return label; //return JLabel
				}
		
		    });

	
	    //table selection event 
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent e)
			{
				
				ListSelectionModel lsm = (ListSelectionModel)e.getSource(); // get ListSelectionModel object
				headerTable.setRowSelectionInterval(lsm.getMinSelectionIndex(), lsm.getMaxSelectionIndex());//when table's cells are selected, same headerTable row cells are selected 
			
			}
		});
		scrollPane.setRowHeaderView(headerTable); // add headerTable to scrollPane
		scrollPane.getRowHeader().setPreferredSize(new Dimension(50,588)); // make tableHeader size same with frame height 
		headerTable.setFillsViewportHeight(true); // use all area of RowHeader

		createMenu();  // create memu
		
		// ActionListener handler
		class MyActionListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e) // event handler
			{
				String command = e.getActionCommand(); // get command name to string
				JFileChooser fileChooser = null;
				int returnVal;
				String filepath;
				switch(command)
				{
					
					case "min" :
					case "max" :
					case "average" :
					case "sum" :
					case "count":	fomulaFuction(command, e); // when click fomula menu
						break;
					case "save" :
						fileChooser = new JFileChooser(); 
						BufferedWriter savefile = null;
						File writefile = null;
						returnVal = fileChooser.showSaveDialog(null); // open file chooser
						if(returnVal != JFileChooser.APPROVE_OPTION) // when not select file in file chooser
						{
							return; // exit chooser
						}
						filepath = fileChooser.getSelectedFile().getPath(); //get file name which is set in fileChooser 
						writefile = new File(filepath);
						try
						{
							savefile = new BufferedWriter(new FileWriter(writefile)); // make file which have same name with filepath
							String filecontents;
							for(int i = 0 ; i < table.getRowCount(); i ++)
							{
								for(int j = 0 ; j < table.getColumnCount(); j++)
								{
									filecontents = table.getValueAt(i, j).toString()+","; // delimit each cell with ','
									savefile.write(filecontents); // write cell contents in save file
								}
								savefile.newLine(); // when read 'Z' cell column, change line
							}
							savefile.close();  // close write file stream
						}
						catch(FileNotFoundException e1) // when failed to open file
						{
								e1.printStackTrace();
								System.out.println("File not found");
						}
						catch(IOException e2) // when failed to write file
						{
							e2.printStackTrace();
							System.out.println("File not saved");
						}
						break;
						
					case "open" :
						//make filefilter which distinguish between txt and csv
						FileNameExtensionFilter filter1 = new FileNameExtensionFilter("*.txt *.csv","txt","csv");
						FileNameExtensionFilter filter2 = new FileNameExtensionFilter("*.txt","txt");
						FileNameExtensionFilter filter3 = new FileNameExtensionFilter("*.csv","csv");
						BufferedReader openfile = null;
						File readfile = null;
						fileChooser = new JFileChooser(); 
						//apply filefilter
						fileChooser.setFileFilter(filter1);
						fileChooser.setFileFilter(filter2);
						fileChooser.setFileFilter(filter3);
						returnVal = fileChooser.showOpenDialog(null); // open fileChooser
						if(returnVal != JFileChooser.APPROVE_OPTION) // when not select file  
						{
							return;
						}
						filepath = fileChooser.getSelectedFile().getPath(); // get selected file name from fileChooser
						readfile = new File(filepath); // make File object with filepath string argument
						title = filepath; 
						ExcellDemo.this.setTitle(title); //set frame title same with filepath
						try
						{
							String input = null;
							openfile = new BufferedReader(new FileReader(readfile)); // read file selected in filechooser 
							StringTokenizer token = null;
							
							for(int i = 0 ; i < table.getRowCount(); i ++)
							{
								input = openfile.readLine();
								if(input != null) // if there are contents to be read in file
								{	
									token = new StringTokenizer(input,","); // distinguish each cell with delimiter ','
								}
								
								for(int j = 0 ; j < table.getColumnCount(); j++)
								{
									if(token.hasMoreTokens()!=false) // if there are contents to be written in cell
									{
										table.setValueAt(token.nextToken(), i, j);
									}
									else // when cell should be empty
									{
										table.setValueAt("", i, j);
									}
								}
							}
					
							openfile.close(); // close file read stream 
						}	
						catch(FileNotFoundException e1) //when failed to open file
						{
								e1.printStackTrace();
								System.out.println("File not found");
								
						}
						catch(IOException e2) // when failed to read file
						{
							e2.printStackTrace();
							System.out.println("File not opend");
					
						}
						break;
					case "new"	: // open new initial excell frame and close origin frame
						ExcellDemo newFrame = new ExcellDemo();
						ExcellDemo.this.dispose();
					case "exit" : //close excell frame
						ExcellDemo.this.dispose();
						break;
				}
			}
				
				
		}
		
		// make ActionListener class object
		MyActionListener Action = new MyActionListener();
		
		//set FileMenu item's event (command name, add ActionListenr)
	    newItem.setActionCommand("new");
		newItem.addActionListener(Action);
		open.setActionCommand("open");
		open.addActionListener(Action);
		save.setActionCommand("save");
		save.addActionListener(Action);
		exit.setActionCommand("exit");
		exit.addActionListener(Action);
				
		//set functionMenu item's event (command name, add ActionListenr)
		sum.setActionCommand("sum");
		sum.addActionListener(Action);
		average.setActionCommand("average");
		average.addActionListener(Action);
		count.setActionCommand("count");
		count.addActionListener(Action);
		max.setActionCommand("max");
		max.addActionListener(Action);
		min.setActionCommand("min");
		min.addActionListener(Action);
		
		// add pane in frame
		this.add(scrollPane);
	    this.setVisible(true); // set frame visible
	}
	public static void main(String args[])
	{
	
		ExcellDemo excell = new ExcellDemo();
		
	}

}
