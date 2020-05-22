package Controlador;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JTextField;
public class RelojThread extends Thread{
  private JTextField txtReloj;
  public RelojThread(JTextField txtReloj) {
    this.txtReloj = txtReloj;
  }
   RelojThread() {
        throw new UnsupportedOperationException("Not supported yet."); 
   }
  @Override
  public void run() {
    super.run(); 
    while (true) {
      Calendar cal = Calendar.getInstance();
      Date date = cal.getTime();
      DateFormat dateFormat = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
      String strDate = dateFormat.format(date);
      System.out.println("Hora: " + strDate);
      txtReloj.setText(strDate);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        //TODO something...
      }
    }
  }
}
