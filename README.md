# LoteriaJava
Formulario de Loteria desarrollado utilizando Java

## Crear el formulario principal
Para crear Inicialmente el formulario se debe utilizar la siguiente sentencia:

```
public class Principal extends JFrame {
```

Es importante realizar la importación el paquete de la clase JFrame, importando solo la clase o todo el paquete.  Adicional deberíamos importar el paquete java.awt:
```
import javax.swing.*;
import java.awt.*;
```
Ahora vamos a declarar e implementar el constructor del JFrame:
```
public Principal()
{
  setTitle("Nataly Sorteos - Por una buena causa");
  setSize(790,550);
        
  Container c = getContentPane();
  c.setLayout(null);
  setVisible(true);
}
```
El resultado inicial de la ventana es el siguiente, la cual no tiene definido un gestor de posicionamiento:

![Alt text](img/imagen1.png?raw=true "Formulario con ventana vacia")

Ahora vamos a agregar 2 paneles para distribuir la ventana, primero tenemos el panel del talonario de los números y el panel de los datos:
```
public JPanel panelNumeros = new JPanel();
public JPanel panelDatos = new JPanel();
```
Ahora en el constructor vamos a incluir la ubicacion de los paneles a través del método setBounds, y a través del método add lo agregamos al JFrame:
```
panelNumeros.setBounds(5,5,500,500);
c.add(panelNumeros);
        
panelDatos.setBounds(510,1,260,400);
c.add(panelDatos);
```
## Crear el talonario de números
Ahora vamos a intentar crear el talonario con los distintos números, para lo cual vamos a declarar un arreglo de botones:
```
JButton[] btns = new JButton[100];
```
Ahora procederemos a implementar el talonario agregando todos los botones de forma dinámica.  El primer paso es definir el layout del panel definiéndolo como GridLayout con una cuadricula de 10 x 10:
```
panelNumeros.setLayout(new GridLayout(10,10,1,1));
        
for(Integer i = 0; i<100; i++) {
  btns[i] = new JButton(i.toString());
  btns[i].setBackground(Color.white);
  panelNumeros.add(btns[i]);
}
```
Como se observa, los botones fueron agregados de forma dinamica a traves del ciclo for, donde se define el background del botón a blanco y luego se agrega al panel de números.  El resultado final se observa en la imagen:

![Alt text](img/imagen2.png?raw=true "Formulario con talonario inicial")

## Crear el formulario de datos

Ahora vamos a agregar el panel de datos, para lo cual vamos a declarar los botones y los campos de texto:
```
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
```
Ahora vamos a implementar los elementos y a agregarlos al panel de datos:
```
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
```
En el caso de lblNumero lo centramos a traves de SwingConstants.CENTER y le agregamos al final una fuente y un tamaño distinto.  Ahora agregamos los elementos al panel:
```
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
```
Se le asignó un GridLayout al panel y luego se asignaron los elementos.  Para separar los elementos se utiliza new JSeparator().  Para ponerle título al panel de datos, se debe redefinir el borde:
```
TitledBorder t = new TitledBorder("Datos del Comprador");
panelDatos.setBorder(t);
```
El resultado actual es el siguiente:

![Alt text](img/imagen3.png?raw=true "Formulario con talonario de números con datos")

## Adicionar datos al JButton
Como es necesario que un JButton o el puesto almacene datos, es necesario definir una nueva clase llamada Puesto que extienda del JButton.  Se incluyen los tres campos y se le implementan los modificadores (set) y obtenedores (get) :
```
import javax.swing.*;

public class Puesto extends JButton {

    private String nombre;
    private String telefono;
    private String email;

    public Puesto() { }
    
    public Puesto(String numero) {
        super(numero);
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

}
```
Ahora debemos reemplazar el JButton por Puesto:
```
Puesto[] btns = new Puesto[100];
```

## Agregar eventos al talonario de números
Para agregar eventos en el formulario se debe implementar un ActionListener:
```
public class Principal extends JFrame 
    implements ActionListener {
```
Al definir la clase implementandola del ActionListener, la clase nos obliga a implementar el método actionPerformed, en el cual vamos a definir lo que va a realizar el evento.  Si el puesto se encuentra comprado p.getNombre()!=null se debe cargar la información del puesto:
```
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

lblNumero.setText(e.getActionCommand());        
}
```
Pero es importante asociar a los botones el ActionListener, como en este caso nuestro formulario implementa el ActionListener, entonces podemos pasar la misma clase usando el this, incluyéndolo en el mismo ciclo for:
```
for(Integer i = 0; i<NUMEROS; i++) {
  btns[i] = new Puesto(i.toString());
  btns[i].setBackground(Color.white);
  btns[i].addActionListener(this);
  panelNumeros.add(btns[i]);
}
```
Si el número no está comprado se carga:

![Alt text](img/imagen4.png?raw=true "Formulario con talonario de números con datos y numero seleccionado")

## Agregar eventos al formulario de datos
### Listener del botón Registrar
Luego se debe proceder a cargar los datos de la persona al hacer clic en el botón Registrar.  Se debe asociar como se observa :
```
btnRegistrar.addActionrListener(new ActionListener() {
  public void actionPerformed(ActionEvent e)
  {
    Container c = ((JButton)e.getSource())
      .getParent().getParent();
    Puesto p = btns[Integer.
    parseInt(lblNumero.getText())];
    p.setNombre(txtNombre.getText());
    p.setTelefono(txtTelefono.getText());
    p.setEmail(txtEmail.getText());
    JOptionPane.showMessageDialog(c, "Compraste el numero " + 
    lblNumero.getText() + ". Gracias por participar es para una buena causa");
    p.setBackground(Color.gray);
  }
});
```
### Listener del botón Limpiar
Otra forma de poder asociar un listener es utilizando un método.  Ahora al botón limpiar le asociaremos el método que permite limpiar:
```
btnLimpiar.addActionListener(limpiarListener());
```
El método es:
```
public ActionListener limpiarListener() {
  return new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      txtNombre.setText(null);
      txtEmail.setText(null);
      txtTelefono.setText(null);
      lblNumero.setText(null);
      txtEmail.requestFocusInWindow();
    }
  };
}
```

### Listener del botón Sortear
Ahora vamos a asociar al botón btnSortear el Listener a través del método sortear():
```
btnSortear.addActionListener(new ActionListener() {
  public void actionPerformed(ActionEvent e) {
    sortear();
  }
});
```
El método sortear debe tener el código del sortear():
```
public void sortear(){
        
  List<Integer> nms = new ArrayList<Integer>();
        
  for(Integer i = 0; i<btns.length; i++) { 
    nms.add(i);
  }
        
  Timer timer = new Timer (5, new ActionListener () { 
    public void actionPerformed(ActionEvent e) { 
      Integer a = nms.get((int)((double)nms.size()
          *Math.random()));
      nms.remove(a);
      if(nms.isEmpty()){
        btns[a].setBackground(Color.yellow);
        ((Timer)e.getSource()).stop();
        if(btns[a].getNombre() == null){
          JOptionPane.showMessageDialog(getParent(), 
              "Con el numero " + btns[a].getActionCommand() + 
              " la casa gana");
        }else{
          JOptionPane.showMessageDialog(getParent(), 
              "El ganador es " + btns[a].getNombre() + 
              ". Con el numero " + 
          btns[a].getActionCommand());
        }
      }else{
        btns[a].setBackground(Color.black);
        ((Timer)e.getSource()).setDelay(100-10*(nms.size()/10));
      }
    } 
  }); 
  timer.start();
} 
```

El resultado del codigo es el siguiente:
![Alt text](img/imagen7.png?raw=true "Ventana final resultado del ejercicio")

