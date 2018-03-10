package sample;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by diogosantos on 08/03/2018.
 */
public class MainPanel extends JPanel {

    Date date;

    // Responsable for  Run/Stop the clock
    boolean run = true;

    String strHoras;
    double totalDebt;
    double priceHour;

    JLabel JLHoras, JLTotalPagar, JLPrecoHora;

    public MainPanel(){
        Dimension size = getPreferredSize();
        size.width = 540;
        size.height = 380;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Contador"));


        totalDebt = 0;
        setPriceByHour(2);

        date = new Date();

        strHoras = "--:--:--";

        JLHoras = new JLabel(strHoras);
        JLHoras.setFont(JLHoras.getFont().deriveFont(30.0f));

        JLabel title1 = new JLabel("Preço Hora:");
        JLPrecoHora = new JLabel(String.valueOf(priceHour +"0€"));
        JLPrecoHora.setFont(JLPrecoHora.getFont().deriveFont(50.0f));

        JLabel title2 = new JLabel("A Pagar:");
        JLTotalPagar = new JLabel(String.valueOf(totalDebt +"0€"));
        JLTotalPagar.setFont(JLTotalPagar.getFont().deriveFont(50.0f));

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Frist Row
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 2;
        gc.gridy = 0;

        add(JLHoras,gc);

        // Second Row
        gc.gridx = 0;
        gc.gridy = 1;
        add(title1,gc);

        gc.gridx = 3;
        gc.gridy = 1;
        add(title2,gc);


        gc.gridx = 0;
        gc.gridy = 2;

        add(JLPrecoHora, gc);

        gc.gridx = 3;
        gc.gridy = 2;

        add(JLTotalPagar, gc);

    }

    public void setPriceByHour(int value){
        if(value > 0)
            this.priceHour = value;
    }

    public void startClock(){

        resetTotalPagar();
        run = true;

        new Thread(){
            public void run(){

                int contador = 0;
                double incTime = treeSimpleRule(priceHour);
                System.out.println("PrecoHora: "+ treeSimpleRule(priceHour));

                while (run){
                    Calendar cal = new GregorianCalendar();

                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    int sec = cal.get(Calendar.SECOND);

                    String time = String.format("%02d:%02d:%02d", hour, min, sec);

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    contador++;

                    if(contador==incTime){
                        contador=0;
                        incTotalPagar();
                    }

                    strHoras = time;
                    JLHoras.setText(strHoras);
                }

                strHoras = "--:--:--";
                JLHoras.setText(strHoras);
            }
        }.start();

    }

    public void stopClock(){
        run = false;
    }

    private double treeSimpleRule(double preco){
        return (0.1*3600)/preco; // time in seconds

    }

    private void incTotalPagar(){
        totalDebt = Math.round((totalDebt + 0.10)*1e12)/1e12;
        String str = String.valueOf(totalDebt);
        str += "0€";
        JLTotalPagar.setText(str);

    }

    private void resetTotalPagar(){
        totalDebt = 0.0;
        String str = String.valueOf(totalDebt);
        str += "0€";
        JLTotalPagar.setText(String.valueOf(str));
    }
}
