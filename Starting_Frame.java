package Connect4;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
	
	public  class Starting_Frame extends JFrame implements ActionListener{
	
		JPanel main_user_panel;
		JLabel jl_userName1, jl_userName2, jl_row, jl_col;
		JTextField jtf1, jtf2, jtf3,jtf4;
		JButton btn_ok;
		Player p1;
		Player p2;
		
		
		    public Starting_Frame() {
			
		    //setLayout(new BorderLayout());
			main_user_panel= new JPanel();
			main_user_panel.setLayout(new BorderLayout());
			
			JPanel user_name_panel = new JPanel();
			user_name_panel.setOpaque(true);
			user_name_panel.setBackground(Color.CYAN);
			
			JPanel user_input_panel = new JPanel();
			user_input_panel.setOpaque(true);
			user_input_panel.setBackground(Color.CYAN);
			
			JPanel btn_panel = new JPanel();
            btn_panel.setOpaque(true);
			btn_panel.setBackground(Color.CYAN);
			
			
			user_name_panel.setLayout(new GridLayout(4,1));
			
		    jl_userName1 = new JLabel("Player1 Name:");
			jl_userName2 = new JLabel("Player2 Name:");
			jl_row = new JLabel("Number of Rows:");
			jl_col = new JLabel("Number of Columns:");
			
			jl_userName1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			jl_userName2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			jl_row.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			jl_col.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			
			user_name_panel.add(jl_userName1);
			user_name_panel.add(jl_userName2);
			user_name_panel.add(jl_row);
			user_name_panel.add(jl_col);
			
			
			user_input_panel.setLayout(new GridLayout(4,1));
			
			 jtf1 = new JTextField(15);
			 jtf2 = new JTextField(15);
			 jtf3 = new JTextField(15);
			 jtf4 = new JTextField(15);
			
			user_input_panel.add(jtf1);
			user_input_panel.add(jtf2);
			user_input_panel.add(jtf3);
			user_input_panel.add(jtf4);
			
			
			//btn_panel.setLayout(new GridLayout(1,1));
			btn_ok = new JButton("OK");
			btn_ok.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
			btn_ok.setSize(5, 5);
			btn_ok.addActionListener(this);
		    btn_panel.add(btn_ok);
			
		    
			main_user_panel.add(user_name_panel, BorderLayout.WEST);
			main_user_panel.add(user_input_panel, BorderLayout.EAST);
			main_user_panel.add(btn_panel, BorderLayout.SOUTH);
			
			main_user_panel.setOpaque(true);
			main_user_panel.setBackground(Color.CYAN);
			this.add(main_user_panel);   //main frame adding the main panel
			
			this.setTitle("Connect4 Player Menu");
			setSize(500, 500);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
			
			
		}

			@Override
			public void actionPerformed(ActionEvent e) {
				String name1 = jtf1.getText().trim();
				String name2 = jtf2.getText().trim();
				 p1 = new Player(name1,"A");
				 p2 = new Player(name2,"P");
				 int rows = Integer.parseInt(jtf3.getText().trim());
				 int cols = Integer.parseInt(jtf4.getText().trim());
				//System.out.println(p1.toString());
				//System.out.println(p2.toString());
				 this.dispose();
				 Connect4_Gui gui = new Connect4_Gui(p1,p2,rows,cols);
				
			}
	}

	


