import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;



import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class PreguntaGUI extends JFrame implements ActionListener,Runnable {
	static PreguntaGUI ventana;
	static JLabel lblContador;
    JLabel lblPuntos;
    
    //static Thread hiloContadors[] = {hilo1,hilo2,hilo3,hilo4,hilo5};
    
    JLabel titulo = new JLabel("Juego de Preguntas");
    
    boolean pararTiempo = false;
    static int valor;
    int puntos;
    int k;
    private int respuestasRealizadas = 0;
    
	private JLabel lblPregunta;

    private JButton btnRespuesta1;

    private JButton btnRespuesta2;

    private JButton btnRespuesta3;

    private JButton btnRespuesta4;

    private String correcta; // el private indica que no se le puede invocar desde otra clase

    //final static int CANTIDADPREGUNTAS = 5;
   // static Thread hilos [] = new Thread [CANTIDADPREGUNTAS];
    boolean finJuego=false;
    static Thread contador;
    public PreguntaGUI() {

        // Configuración básica de la ventana

        setTitle("Juego Preguntas");

        setSize(910, 380);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(8, 1));

        
        titulo = new JLabel("Juego de Preguntas");
        titulo.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 36));
        titulo.setForeground(new Color (15, 26, 79));;
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        add(titulo);
        lblContador = new JLabel("0");
        lblContador.setForeground(new Color (243, 187, 52));

        lblContador.setFont(new Font("Footlight MT Light",Font.BOLD,27));
        
      
    		
        add(lblContador);

        // Crear etiqueta con la pregunta

        lblPregunta = new JLabel();

        add(lblPregunta);



        // Crear botones de respuesta

        btnRespuesta1 = new JButton();
        decorarBoton(btnRespuesta1);
        btnRespuesta1.addActionListener(this);

        add(btnRespuesta1);


        btnRespuesta2 = new JButton();
        decorarBoton(btnRespuesta2);

        btnRespuesta2.addActionListener(this);

        add(btnRespuesta2);



        btnRespuesta3 = new JButton();
        decorarBoton(btnRespuesta3);

        btnRespuesta3.addActionListener(this);

        add(btnRespuesta3);



        btnRespuesta4 = new JButton();
        decorarBoton(btnRespuesta4);
        
        btnRespuesta4.addActionListener(this);

        add(btnRespuesta4);

        lblPuntos = new JLabel("0");
        
        lblPuntos.setForeground(new Color (88,143,162));

        lblPuntos.setFont(new Font("Monaco",Font.BOLD,24));
        
        add(lblPuntos);
        
   
        
        

        // Cargar la primera pregunta al iniciar

        cargarPregunta();
  

    }



    private void cargarPregunta() {
    	
       valor = 0;
       lblContador.setText(String.valueOf(valor));
       pararTiempo = false;

        try {

            // Conexión a la base de datos

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/preguntas", "root", "");



            // Obtener una pregunta aleatoria

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM preguntas_respuestas ORDER BY RAND() LIMIT 1");

            ResultSet rs = stmt.executeQuery(); // ResultSet es una matriz que tiene un puntero, pasa, pilla los datos, pasa al siguiente... en bucle hasta que no haya más datos



            if (rs.next()) { // rs.next = se coloca en el siguiente, el if busca que haya 1 pregunta siguiente, esto lo que hace si hay 1 es ejecutarse de la misma manera, y si esta existe, funciona igual

                String pregunta = rs.getString("enunciadoPregunta");
                
                //para pillar el id: int idPregunta = rs.getInt("id");
                
                String[] respuestas = {

                    rs.getString("respuesta1"),

                    rs.getString("respuesta2"),

                    rs.getString("respuesta3"),

                    rs.getString("respuesta4")
                    
                    

                };

   

                // Guardar la respuesta correcta (la primera)

                correcta = respuestas[0];

                

                // Mostrar la pregunta

                lblPregunta.setText(pregunta);



                // Mezclar las respuestas de manera aleatoria

                List<String> respuestasMezcladas = new ArrayList<>(List.of(respuestas));

                Collections.shuffle(respuestasMezcladas);



                // Asignar las respuestas a los botones

                btnRespuesta1.setText(respuestasMezcladas.get(0));

                btnRespuesta2.setText(respuestasMezcladas.get(1));

                btnRespuesta3.setText(respuestasMezcladas.get(2));

                btnRespuesta4.setText(respuestasMezcladas.get(3));
     

            }
            
            rs.close(); //cerrar el resultSet

            stmt.close();//cerrar el statement

            conn.close();//cerrar la conexión con la base de datos

        } catch (Exception e) {

            e.printStackTrace();

        }

    }



    @Override

    public void actionPerformed(ActionEvent e) {
    	JButton btn = (JButton) e.getSource();
    	String tiempo = lblContador.getText();

    	// deshabilitarBotones();

    	//contador.interrupt();

    	

        if (btn.getText().equals(correcta)) {

        	mostrarDialogo("!Respuesta Correcta!, Respuesta realizada en: " +tiempo +" segundos");
        	int puntos = Integer.parseInt(lblPuntos.getText());
            puntos +=10-Integer.parseInt(tiempo);
    		lblPuntos.setText(String.valueOf(puntos));

    		

        } else {

        	mostrarDialogo("Respuesta Incorrecta. Respuesta realizada en: " +tiempo +" segundos");
            int puntos = Integer.parseInt(lblPuntos.getText());
    		puntos -=5;// puntos = puntos - 5
    		lblPuntos.setText(String.valueOf(puntos)); 	
    	

        }      
        respuestasRealizadas++;

        lblContador.setText("0");
       
        pararTiempo=true;
        
        cargarPregunta();

    
    	if(respuestasRealizadas == 5) 
    		mostrarPuntuacionTotal();
    	
    	
    	
    }
    

      /*  JButton btn = (JButton) e.getSource();

        String tiempo = lblContador.getText();
        
 
       
        	
        if (btn.getText().equals(correcta)) {

            mostrarDialogo("¡Respuesta correcta!");
            int puntos = Integer.parseInt(lblPuntos.getText());
            puntos +=10-Integer.parseInt(tiempo);
   		 	lblPuntos.setText(String.valueOf(puntos));

        } else {

            mostrarDialogo("Respuesta incorrecta.");
            int puntos = Integer.parseInt(lblPuntos.getText());
   		 	puntos -=5;// puntos = puntos - 5
   		 	lblPuntos.setText(String.valueOf(puntos)); 	

        }
        // Cargar una nueva pregunta después de responder
     
        lblContador.setText("0");

        pararTiempo=true;
        respuestasRealizadas++;
       
        cargarPregunta();
        pararTiempo = false;
    
        }
    */
    
/*if(k == 5) {
lblPregunta.setVisible(false);
	btnRespuesta1.setVisible(false);
	btnRespuesta2.setVisible(false);
	btnRespuesta3.setVisible(false);
	btnRespuesta4.setVisible(false);
	JOptionPane.showMessageDialog(this, "Tu puntuación es: " + puntos);


}*/
    private void mostrarDialogo(String mensaje) {

        JOptionPane.showMessageDialog(this, mensaje);

    }
    
   /* private void deshabilitarBotones() {
    	btnRespuesta1.setEnabled(false);
    	btnRespuesta2.setEnabled(false);
    	btnRespuesta3.setEnabled(false);
    	btnRespuesta4.setEnabled(false);
    }
*/

    public static void main(String[] args) {
    	ventana = new PreguntaGUI();
    	ventana.setVisible(true);
    	contador = new Thread(ventana);
    	contador.start();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        }
        
 
    }
    @Override

	public void run() {		
		
		int valor=0;

		
		
		while (!finJuego) {
			
			while (!pararTiempo && Integer.parseInt(lblContador.getText())<10) {
				
				try {

					Thread.sleep(1000); 

					

				} catch (InterruptedException e) {			

					e.printStackTrace();	}
				
				valor = Integer.parseInt(lblContador.getText()); 

				valor++;
				
				
				
				lblContador.setText(String.valueOf(valor));
				 
			}
	
			
			if (valor>=10) {
				mostrarDialogo("Tiempo agotado");
			}
			
				 puntos = Integer.parseInt(lblPuntos.getText());
				 puntos -=2;// puntos = puntos - 2
				 lblPuntos.setText(String.valueOf(puntos));

				respuestasRealizadas++;
				cargarPregunta();
				if(respuestasRealizadas == 5) 
		    		mostrarPuntuacionTotal();
		    		
		}

			
		


		
		/*if(pararTiempo==false) {   
		mostrarDialogo("Tiempo agotado.");
		 
		 puntos = Integer.parseInt(lblPuntos.getText());
		 puntos -=2;// puntos = puntos - 2
		 lblPuntos.setText(String.valueOf(puntos));

		 respuestasRealizadas++;
	   
	     
	     cargarPregunta();
	    	
		}

*/
    	
}




    /*@Override
	public void run() { // el método run se invoca cuando hacemos un start
    	System.out.println("comienzo run");
		while (!finJuego) {

    	while(pararTiempo == false && valor<10) {
			try {
				Thread.sleep((long)(1000));
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			valor = Integer.parseInt(lblContador.getText());
			valor++;
			lblContador.setText(String.valueOf(valor));
			
			
		}
    	if (valor>=10)
			System.out.println("Tiempo agotado");
		
		lblContador.setText("0");
		
		cargarPregunta();
		respuestasRealizadas++;
		
		}
		
		
		if(pararTiempo==false) {   
		mostrarDialogo("Tiempo agotado.");
		 
		 puntos = Integer.parseInt(lblPuntos.getText());
		 puntos -=2;// puntos = puntos - 2
		 lblPuntos.setText(String.valueOf(puntos));

		 respuestasRealizadas++;
	   
	     
	     cargarPregunta();
	     
	     pararTiempo = false;
	
		}
		
		if(respuestasRealizadas == 5)
    		mostrarPuntuacionTotal();
	}
    */
    
	/*if(k == 5) {
		lblPregunta.setVisible(false);
   		btnRespuesta1.setVisible(false);
   		btnRespuesta2.setVisible(false);
   		btnRespuesta3.setVisible(false);
   		btnRespuesta4.setVisible(false);
   		JOptionPane.showMessageDialog(this, "Tu puntuación es: " + puntos);


	}*/
   	
    private void reiniciarJuego() {
        respuestasRealizadas = 0; 
        puntos = 0; 
        lblPuntos.setText(String.valueOf(puntos)); 
        valor = 0;
  	    lblContador.setText(String.valueOf(valor));
        cargarPregunta();
       
    }
    
    private void mostrarPuntuacionTotal() {
        int puntuacionFinal = Integer.parseInt(lblPuntos.getText());
        String mensaje = "Tu puntuación final es: " + puntuacionFinal + ", ¿Deseas volver a jugar?";
        int opcion = JOptionPane.showConfirmDialog(this, mensaje, "Puntuación Final", JOptionPane.YES_NO_OPTION);

        valor = 0;
	    lblContador.setText(String.valueOf(valor));
        if (opcion == JOptionPane.YES_OPTION) {
            
            reiniciarJuego();
        } else {
            
            JOptionPane.showMessageDialog(this,"Juego Finalizado, cierre la ventana para salir");
            btnRespuesta1.setEnabled(false);
            btnRespuesta2.setEnabled(false);
            btnRespuesta3.setEnabled(false);
            btnRespuesta4.setEnabled(false);
        	contador.stop();
            
            valor = 0;
   	     	lblContador.setText(String.valueOf(valor));
        }
    }
    
    private void decorarBoton(JButton boton) {
        boton.setBackground(new Color(27, 95, 81)); 
        boton.setForeground(Color.WHITE); 
        boton.setFocusPainted(false); // Desactivamos el pintado de enfoque al hacer clic
        boton.setFont(new Font("Arial", Font.BOLD, 14)); 
       
    }


  
    
    }    
