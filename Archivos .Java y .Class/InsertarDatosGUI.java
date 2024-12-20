
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InsertarDatosGUI extends JFrame implements ActionListener {
    JTextField txtNombreUsuario, txtCorreoElectronico;
    JLabel lblNombreUsuario, lblCorreoElectronico, lblTitulo;
    JButton btnInsertar;


    public InsertarDatosGUI() {
        setTitle("Registro juego Preguntas");
        setSize(430, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout()); 
        
        lblTitulo = new JLabel("¡¡Regístrate Ya!!", SwingConstants.CENTER); 
        lblTitulo.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 36));
        lblTitulo.setForeground(new Color (15, 26, 79));
        panel.add(lblTitulo, BorderLayout.NORTH);

        JPanel PanelRegistros = new JPanel(new GridLayout(2, 2)); 

        lblNombreUsuario = new JLabel("Nombre de Usuario:");
        txtNombreUsuario = new JTextField();
        lblCorreoElectronico = new JLabel("Correo Electrónico:");
        txtCorreoElectronico = new JTextField();

        PanelRegistros.add(lblNombreUsuario);
        PanelRegistros.add(txtNombreUsuario);
        PanelRegistros.add(lblCorreoElectronico);
        PanelRegistros.add(txtCorreoElectronico);

        panel.add(PanelRegistros, BorderLayout.CENTER); // 

        
        
        btnInsertar = new JButton("Registrarse");

        btnInsertar.addActionListener(this);
        
    
        btnInsertar.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 19));
        btnInsertar.setForeground(new Color (12, 30, 69));
           
        panel.add(btnInsertar, BorderLayout.SOUTH); // Agregar el botón en el sur (abajo)

        add(panel);
        setVisible(true);
    }

    public void insertarDatos() {
        String nombreUsuario = txtNombreUsuario.getText();
        String correoElectronico = txtCorreoElectronico.getText();

        try {
          
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/preguntas", "root", "");

            String sql = "INSERT INTO usuarios (nombreUsuario, CorreoElectronico) VALUES ('"+ nombreUsuario+"','"+ correoElectronico+"')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println(sql);
            
         
           stmt.executeUpdate(sql);
          
           stmt.close();//cerrar el statement

           conn.close();//cerrar la conexión con la base de datos
           
           
            System.out.println("Datos insertados correctamente.");
        } catch (Exception e) {
            System.out.println("Error al insertar datos: " + e);
        }
    }

    public static void main(String[] args) {
        
        new InsertarDatosGUI();
    }
    
    @Override

    public void actionPerformed(ActionEvent e) {

       insertarDatos();
       
      
   	
       	PreguntaGUI juego = new PreguntaGUI();
      	Thread contador = new Thread(juego);
   		contador.start();
   		juego.setVisible(true);

    	

    }
    
}
