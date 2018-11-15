package Form;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import GUI.GUI_HomeScreen;

import java.awt.Dimension;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class GUI_ContactForm extends JLayeredPane {
	
	private GUI_HomeScreen home;
	private JLabel background;
	private JButton btnSalir;
	private JTextField txt_nombre;
	private JTextField txt_email;
	
	//Archivo de texto

    // The name of the file to open.
    String archivoComentarios = "comentarios.txt";
	
	
	public GUI_ContactForm(GUI_HomeScreen frame) {
		this.home = frame;
		//this.setSize(new Dimension(938, 570))
		//this.setSize(home.getSize());
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
		
		
		JLabel lblTitulo = new JLabel("Contacte con nosotros! ");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(82, 11, 234, 40);
		add(lblTitulo);
		
		JLabel lblSubtitulo = new JLabel("Nos interesa saber tu opini\u00F3n, cr\u00EDtica o sugerencia sobre el juego");
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSubtitulo.setBounds(82, 52, 533, 40);
		add(lblSubtitulo);
		
		
		//Nombre
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(82, 133, 70, 14);
		add(lblNombre);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(236, 127, 339, 30);
		add(txt_nombre);
		txt_nombre.setColumns(10);
		
		//Email
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(82, 202, 55, 14);
		add(lblEmail);
		
		txt_email = new JTextField();
		txt_email.setColumns(10);
		txt_email.setBounds(236, 196, 333, 30);
		add(txt_email);
		
		
		//Comentario
		JLabel lblComentario = new JLabel("Tu comentario");
		lblComentario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblComentario.setBounds(82, 263, 144, 14);
		add(lblComentario);
		
		JEditorPane txt_comentario = new JEditorPane();
		txt_comentario.setBounds(236, 277, 333, 294);
		add(txt_comentario);
		

		
		//Boton Enviar
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnEnviar.setBounds(640, 594, 89, 40);
		add(btnEnviar);
		
	
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre=txt_nombre.getText();
				String email= txt_email.getText();
				String comentario= txt_comentario.getText();
				
				if(nombre.isEmpty()||(email.isEmpty())||(comentario.isEmpty()))
					JOptionPane.showMessageDialog(null, "Faltan completar datos");
				else {
					JOptionPane.showMessageDialog(null, "Gracias por contactarte!");
					agregarComentario(nombre,email,comentario);
				}
			}
		});
		
	}
	
	public Container getPanel() {
		return this;
	}
	
	public void agregarComentario (String nombrePersona, String emailPersona, String mensajePersona) {
		FileWriter fw = null;
		  try {
			  
	            fw=new FileWriter(archivoComentarios,true);
	            fw.write("Nombre: "+nombrePersona);
	            fw.write(System.getProperty( "line.separator" ));
	            fw.write("Email "+emailPersona+"\n");
	            fw.write(System.getProperty( "line.separator" ));
	            fw.write("Mensaje: "+mensajePersona+"\n");
	            fw.write(System.getProperty( "line.separator" ));
	            fw.write("----------------------Linea separadora tercermundista------------------------\n");
	            fw.write(System.getProperty( "line.separator" ));
	            
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error escribiendo el archivo"
	                + archivoComentarios + "'");
	         
	        } finally {
	        	try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	}
	
	
	

