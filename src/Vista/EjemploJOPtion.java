package Vista;
import javax.swing.JOptionPane;
public class EjemploJOPtion {
    public static void main(String[] args) {
        float base,altura;   
        /* String resposta= JOptionPane.showInutDialog 
           base= Float.parseFloat(resposta);.*/
        String resposta =JOptionPane.showInputDialog("base :");
        base= Float.parseFloat(resposta);
        JOptionPane.showMessageDialog(null,"base :"+base);
    }
    
}
