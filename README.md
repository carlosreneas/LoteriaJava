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

![Alt text](img/imagen1.png?raw=true "Title")
******************************************** imagen ****************************************

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




