package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class GUI_VerComentarios extends JLayeredPane {

	private GUI_HomeScreen home;
	private JLabel background;
	private JButton btnSalir;
	JTextArea textArea = new JTextArea();
	
	public GUI_VerComentarios(GUI_HomeScreen frame) {
		
		
		
		this.home = frame;
		this.setSize(1024, 700);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.setVisible(true);

		
		ImageIcon img = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		background = new JLabel();
		Icon icon = new ImageIcon(img.getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT));
		background.setIcon(icon);
		background.setBounds(0, 0, 1024, 700);
		//this.add(background);
		this.setLayer(background, 1);

		btnSalir = new JButton("Back");
		btnSalir.setVisible(true);
		btnSalir.setBounds(850, 53, 150, 40);
		btnSalir.setBackground(Color.BLACK);
		btnSalir.setFont(new Font("Unispace", Font.PLAIN, 16));
		btnSalir.setBorder(new LineBorder(Color.lightGray.darker(), 2));
		btnSalir.setForeground(Color.white);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				home.regresar();
			}
		});
		this.add(btnSalir);
		this.setLayer(btnSalir, 2);
		
		textArea = new JTextArea();
		textArea.setBounds(84, 129, 766, 560);
		add(textArea);
		
		
		
		
		//mostrarComentarios();
		try {
			String text = readFile("comentarios.txt");
			textArea.setText(text);
			textArea.setEditable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public Container getPanel() {
		return this;
	}
	
	
	private String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());        

	    try (Scanner scanner = new Scanner(file)) {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + System.lineSeparator());
	        }
	        return fileContents.toString();
	    }
	}
}
