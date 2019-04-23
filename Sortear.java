import javax.swing.*;
import java.util.*;
import java.awt.Color;
/**
 * Write a description of class Sortear here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sortear
{

    public Sortear()
    {
        
    }

    public static void ejecutar(JButton[] btns){
        
        List<Integer> nms = new ArrayList<Integer>();
        
        for(Integer i = 0; i<btns.length; i++) { 
            nms.add(i);
        }
        
        for(Integer i = 0; i<btns.length-1; i++){
            
            Integer a = nms.get((int)((double)nms.size()*Math.random()));
            nms.remove(a);
            esperar(1);
            btns[a].setBackground(Color.black);
            
        }
        
    }
    
    public static void ejecutarPruebas(Integer n){
        
        List<Integer> nms = new ArrayList<Integer>();
        
        for(Integer i = 0; i<n; i++) { 
            nms.add(i);
        }
        
        for(Integer i = 0; i<n; i++){
            
            Integer a = nms.get((int)((double)nms.size()*Math.random()));
            nms.remove(a);
            //Integer x = nms.get(a);
            
            System.out.println(a);
        }
        
    }
    
    public static void esperar (int segundos) {
        try {
            Thread.sleep (segundos*1000);
        } catch (Exception e) {
        // Mensaje en caso de que falle
        }
    }
}
