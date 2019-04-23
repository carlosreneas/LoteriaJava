import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.*;
import java.lang.*;
import javax.swing.border.TitledBorder;
/**
 * Write a description of class Principal here.
 *
 * @author Carlos Rene Angarita Sanguino
 * @version 0.1
 */
public class Principal extends JFrame 
    implements ActionListener
{
    static final Integer NUMEROS = 100;
    
    public JPanel panelNumeros = new JPanel();
    public JPanel panelDatos = new JPanel();
    
    public JTextField txtNombre;
    public JTextField txtTelefono;
    public JTextField txtEmail;
    
    public JLabel lblNombre;
    public JLabel lblNumero;
    public JLabel lblTelefono;
    public JLabel lblEmail;
    
    public JButton btnRegistrar;
    public JButton btnPagar;
    public JButton btnLimpiar;
    
    public JButton btnSortear;
    
    public Integer seleccionado = 0;
    
    public Integer vendidos = 0;
    Puesto[] btns = new Puesto[NUMEROS];
    
    /**
     * Constructor for objects of class Principal
     */
    public Principal()
    {
        // initialise instance variables
        setTitle("Nataly Sorteos - Por una buena causa");
        setSize(790,550);
        
        Container c = getContentPane();
        c.setLayout(null);
        
        panelNumeros.setBounds(5,5,500,500);
        c.add(panelNumeros);
        
        panelDatos.setBounds(510,1,260,400);
        c.add(panelDatos);
        
        txtNombre = new JTextField();
        txtEmail = new JTextField();
        txtTelefono = new JTextField();
        
        lblNombre = new JLabel("Nombre");
        lblNumero = new JLabel("", SwingConstants.CENTER);
        lblEmail = new JLabel("Email");
        lblTelefono = new JLabel("Telefono");
        
        btnRegistrar = new JButton("Registrar");
        btnPagar = new JButton("Pagar");
        btnLimpiar = new JButton("Limpiar");
        
        btnSortear = new JButton("Sortear");
        
        lblNumero.setFont(new Font("SansSerif", Font.BOLD, 30));
        
        
        Integer filas = ((Double)Math.sqrt(NUMEROS)).intValue();
        
        panelDatos.setLayout(new GridLayout(13,1));
        
        panelDatos.add(lblNumero);
        panelDatos.add(lblEmail);
        panelDatos.add(txtEmail);
        panelDatos.add(lblNombre);
        panelDatos.add(txtNombre);
        panelDatos.add(lblTelefono);
        panelDatos.add(txtTelefono);
        panelDatos.add(new JSeparator());
        panelDatos.add(btnRegistrar);
        panelDatos.add(btnPagar);
        panelDatos.add(btnLimpiar);
        panelDatos.add(new JSeparator());
        
        panelDatos.add(btnSortear);
        
        btnRegistrar.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e)
          {
              Container c = ((JButton)e.getSource()).getParent().getParent();
              Puesto p = btns[Integer.parseInt(lblNumero.getText())];
              p.setNombre(txtNombre.getText());
              p.setTelefono(txtTelefono.getText());
              p.setEmail(txtEmail.getText());
              JOptionPane.showMessageDialog(c, "Compraste el numero " + 
              lblNumero.getText() + ". Gracias por participar es para una buena causa");
              p.setBackground(Color.gray);
          }
        });
        
        btnSortear.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e)
          {
              sortear();
          }
        });
        
        btnLimpiar.addActionListener(limpiarListener());
        
        TitledBorder t = new TitledBorder("Datos del Comprador");
        panelDatos.setBorder(t);
        
        
        panelNumeros.setLayout(new GridLayout(filas,filas,1,1));
        
        for(Integer i = 0; i<NUMEROS; i++) {
            btns[i] = new Puesto(i.toString());
            btns[i].setBackground(Color.white);
            btns[i].addActionListener(this);
            panelNumeros.add(btns[i]);
        }
        
        
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        
        Puesto p = (Puesto)e.getSource();
        
        if(p.getNombre()!=null){ 
            JOptionPane.showMessageDialog(this, "El numero " + 
            e.getActionCommand() + " ya esta vendido. Intenta con otro");
            txtNombre.setText(p.getNombre());
            txtEmail.setText(p.getEmail());
            txtTelefono.setText(p.getTelefono());
            return;
        }
        vendidos ++ ;
        /*
        String nombre = JOptionPane.showInputDialog(this, "Dime ti nombre:");
        String telefono = JOptionPane.showInputDialog(this, "Dime tu Telefono:");
        String email = JOptionPane.showInputDialog(this, "Dime tu Email:");

        p.setNombre(nombre);
        p.setTelefono(telefono);
        p.setEmail(email);
        */
        lblNumero.setText(e.getActionCommand());
        /*

        JOptionPane.showMessageDialog(this, "Compraste el numero " + 
            e.getActionCommand() + ". Gracias por participar es para una buena causa");
        p.setBackground(Color.gray);
        
        */
        //p.setEnabled(false);
        
        if (vendidos == NUMEROS) {
            Integer ganador = ((Double)(NUMEROS*Math.random())).intValue();
            //Integer ganadorObj = new Integer(ganador);
            JOptionPane.showMessageDialog(this, "El ganador es el numero " + 
            ganador.toString() + ". Felicitaciones a " + btns[ganador].getNombre());
            
            btns[ganador].setBackground(Color.yellow);
        }
        
    }
    
    public ActionListener limpiarListener() {
        return new ActionListener() {
          public void actionPerformed(ActionEvent e)
          {
            txtNombre.setText(null);
            txtEmail.setText(null);
            txtTelefono.setText(null);
            lblNumero.setText(null);
            txtEmail.requestFocusInWindow();
          }
        };
    }
    
    public void sortear(){
        
        List<Integer> nms = new ArrayList<Integer>();
        
        for(Integer i = 0; i<btns.length; i++) { 
            nms.add(i);
        }
        
        
        Timer timer = new Timer (5, new ActionListener () 
        { 
            public void actionPerformed(ActionEvent e) 
            { 
                Integer a = nms.get((int)((double)nms.size()*Math.random()));
                nms.remove(a);
                if(nms.isEmpty()){
                    btns[a].setBackground(Color.yellow);
                    ((Timer)e.getSource()).stop();
                    if(btns[a].getNombre() == null){
                        JOptionPane.showMessageDialog(getParent(), "Con el numero " + btns[a].getActionCommand() + " la casa gana");
                    }else{
                        JOptionPane.showMessageDialog(getParent(), "El ganador es " + btns[a].getNombre() + ". Con el numero " + btns[a].getActionCommand());
                    }
                    
                }else{
                    btns[a].setBackground(Color.black);
                    ((Timer)e.getSource()).setDelay(100-10*(nms.size()/10));
                }
               
             } 
        }); 
        timer.start();
        
        //for(Integer i = 0; i<btns.length-1; i++){
            /*
             Timer timer = new Timer (500, new ActionListener () 
            { 
                public void actionPerformed(ActionEvent e) 
                { 
                    Integer a = nms.get((int)((double)nms.size()*Math.random()));
                    nms.remove(a);
                    btns[a].setBackground(Color.black);
                 } 
            }); 
            timer.start();*/
            //Integer a = nms.get((int)((double)nms.size()*Math.random()));
            //nms.remove(a);
            /*
            Thread hilo = new Thread() {
                public void run() {
                    try {
                        Thread.sleep (10);
                    } catch (Exception e) {
                    // Mensaje en caso de que falle
                        System.out.println("Error");
                    }
                 
                  btns[a].setBackground(Color.black);
                  btns[a].repaint();
                }
             };
            
             try {
                 Thread hiloActual = Thread.currentThread();
                //Thread.sleep (10);
                hiloActual.sleep(10);
            } catch (Exception e) {
            // Mensaje en caso de que falle
                System.out.println("Error");
            }
            btns[a].setBackground(Color.black);
            btns[a].repaint();
            */
            //System.out.println(a);
            
            //repaint();
            
        //}
        
    }
    
}
