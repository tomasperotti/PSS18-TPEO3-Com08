package Login;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import GUI.GUI_HomeScreen;
import Login.ManejadorArchivoUsuarios;
import Login.Usuario;
import Main.Main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaAcceso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private JLabel lblLogo;
	private JButton btnEntrar;
	private ManejadorArchivoUsuarios miManejadorUsuarios;
	private ArrayList<Usuario> miListaUsuarios;
	private JLabel lblUsuario;
	private JLabel lblPassword;
	private JLabel lblAcercaDe;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAcceso frame = new VentanaAcceso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaAcceso() {
		
		//iniciarPantallaJuego();
		iniciarGui();
		setListeners();

		// Administracion de los usuarios
		miListaUsuarios = new ArrayList<Usuario>();
		miManejadorUsuarios = new ManejadorArchivoUsuarios();
		if (miManejadorUsuarios.existeArchivo() == true)
			miListaUsuarios = (ArrayList<Usuario>) cargarUsuarios();

	}

	private void controlarUsuario(Usuario u) {

		// Busco si el usuario esta en la lista de usuarios existentes
		if (esta(u.getNombreUsuario())) {
			String claveAlmacenada = getUsuario(u.getNombreUsuario()).getClave();
			if (claveAlmacenada.equals(u.getClave())) {
				if (u.getNombreUsuario().equals("admin")) {
					iniciarPantallaJuego(true);
				} else {
					iniciarPantallaJuego(false);
				}
				
			} else {
				JOptionPane.showMessageDialog(contentPane, "Clave incorrecta. Intente nuevamente", "Error",
						JOptionPane.INFORMATION_MESSAGE);
				passwordField.requestFocusInWindow();
			}
		} else {
			miListaUsuarios.add(u);
			miManejadorUsuarios.escribirArchivo(miListaUsuarios);
			JOptionPane.showMessageDialog(contentPane,
					"El usuario " + u.getNombreUsuario() + " fue agregado al sistema", "Nuevo usuario creado",
					JOptionPane.INFORMATION_MESSAGE);
			if (u.getNombreUsuario().equals("admin")) {
				iniciarPantallaJuego(true);
			} else {
				iniciarPantallaJuego(false);
			}
		}
	}
	
	private void iniciarPantallaJuego(boolean userIsAdmin) {
		dispose();
		if (userIsAdmin) {
			GUI_HomeScreen frame = GUI_HomeScreen.getInstance();
			// Muestro boton de Ver comentarios
			frame.agregarBotonVerComentarios();
			frame.setVisible(true);
		} else {
			GUI_HomeScreen frame = GUI_HomeScreen.getInstance();
			frame.setVisible(true);
		}
		

	}

	private boolean esta(String nombreUsuario) {
		for (Usuario u : miListaUsuarios) {
			if (u.getNombreUsuario().trim().contains(nombreUsuario)) {
				return true;
			}
		}
		return false;
	}

	public List<Usuario> cargarUsuarios() {
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios = (ArrayList<Usuario>) miManejadorUsuarios.leerArchivo();
		return listaUsuarios;
	}

	public Usuario getUsuario(String nombre) {
		Usuario u = null;
		Iterator<Usuario> it = miListaUsuarios.iterator();
		boolean encontre = false;
		while (it.hasNext() && !encontre) {
			Usuario usuario = it.next();
			if (usuario.getNombreUsuario().equals(nombre)) {
				u = usuario;
				encontre = true;
			}
		}
		return u;
	}

	public void iniciarGui() {

		// Propiedades de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 368);
		setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Usuario - JLabel y JTextField
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblUsuario.setBounds(144, 112, 95, 26);
		contentPane.add(lblUsuario);

		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		textUsuario.setBounds(282, 115, 153, 26);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		// Password- JLabel y JPasswordField
		lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblPassword.setBounds(144, 158, 107, 26);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(282, 161, 153, 26);
		contentPane.add(passwordField);

		// Logo de la empresa
		URL url = VentanaAcceso.class.getResource("/imagenes/logo.png");
		ImageIcon icono = new ImageIcon(url);
		lblLogo = new JLabel(icono);
		lblLogo.setBounds(42, 112, 79, 80);
		contentPane.add(lblLogo);

		// Acerca de
		url = VentanaAcceso.class.getResource("/imagenes/about.png");
		icono = new ImageIcon(url);
		lblAcercaDe = new JLabel(icono);
		lblAcercaDe.setBounds(568, 11, 56, 56);
		contentPane.add(lblAcercaDe);

		// Boton Entrar
		btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnEntrar.setBounds(347, 224, 89, 23);
		contentPane.add(btnEntrar);

		// Sirve para agregar el Enter como listener del boton Entrar
		getRootPane().setDefaultButton(btnEntrar);

	}

	public void setListeners() {

		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreUsuario = textUsuario.getText();
				String claveUsuario = new String(passwordField.getPassword());


				if (nombreUsuario.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "El usuario es obligatorio!", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (claveUsuario.equals("")) {
					JOptionPane.showMessageDialog(contentPane, "La contraseña es obligatoria!", "Error",
							JOptionPane.ERROR_MESSAGE);

				} else {
					Usuario u = new Usuario(nombreUsuario, claveUsuario);
					controlarUsuario(u);
				}

			}
		});

		lblAcercaDe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(contentPane,
						"Alumnos:" +"\n"+"Matias Massetti" + "\n" + "LU: 107954" + "\n" + "Tomas Perotti" + "\n" + "LU: 105495" + "\n"+ "Proyectos de Sistemas de Software 2018", "Acerca de",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

	}
}
