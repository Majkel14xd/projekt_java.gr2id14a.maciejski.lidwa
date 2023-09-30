import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoZakonczOnline extends JFrame implements ActionListener {
    JButton WyjscieMenu;
    JLabel InfZwyciesca;
    int wielkosc;
    Font czcionka=new Font("Dialog",Font.PLAIN,20);
    OknoZakonczOnline(int ilosc){
        wielkosc=ilosc;
        setTitle("Kółko&Krzyżyk - Server");
        setSize(650,650);
        setLocation(400,300);
        setLayout(null);

        InfZwyciesca=new JLabel("Remis");
        InfZwyciesca.setBounds(250,20,300,150);
        InfZwyciesca.setFont(new Font("SansSerif",Font.BOLD,35));
        add(InfZwyciesca);

        WyjscieMenu = new JButton("Wyjscie do menu");
        WyjscieMenu.setBounds(180,450,250,100);
        WyjscieMenu.setBackground(Color.white);
        WyjscieMenu.setFont(czcionka);
        add(WyjscieMenu);
        WyjscieMenu.addActionListener(this);
    }
    OknoZakonczOnline(String aktywny_gracz,int kolor,int ilosc){
        wielkosc=ilosc;
        setTitle("Kółko&Krzyżyk");
        setSize(650,650);
        setLocation(400,300);
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);

        InfZwyciesca=new JLabel("Wygrał gracz: "+aktywny_gracz);
        InfZwyciesca.setBounds(170,20,300,150);
        InfZwyciesca.setFont(new Font("SansSerif",Font.BOLD,35));
        if(kolor==2) {
            InfZwyciesca.setForeground(Color.green);
        }else{
            InfZwyciesca.setForeground(Color.red);
        }
        add(InfZwyciesca);

        WyjscieMenu = new JButton("Wyjscie do menu");
        WyjscieMenu.setBounds(180,450,250,100);
        WyjscieMenu.setFont(czcionka);
        WyjscieMenu.setBackground(Color.white);
        add(WyjscieMenu);
        WyjscieMenu.addActionListener(this);
    }
    public void actionPerformed(ActionEvent a) {
        Object z = a.getSource();
        if(z==WyjscieMenu){
            OknoMenu OknoM=new OknoMenu();
            OknoM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoM.setVisible(true);
            setEnabled(false);
            dispose();
        }
    }
}
