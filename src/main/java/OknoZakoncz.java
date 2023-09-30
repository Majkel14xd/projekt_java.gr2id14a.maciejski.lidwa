import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoZakoncz extends JFrame implements ActionListener{
    JButton ZagrajPonownie,WyjscieMenu;
    JLabel InfZwyciesca;
    int wielkosc;
    Font czcionka=new Font("Dialog",Font.PLAIN,20);
    OknoZakoncz(int ilosc){
        wielkosc=ilosc;
        setTitle("Kółko&Krzyżyk - Server");
        setSize(650,650);
        setLocation(400,300);
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);

        InfZwyciesca=new JLabel("Remis");
        InfZwyciesca.setBounds(250,20,300,150);
        InfZwyciesca.setFont(new Font("SansSerif",Font.BOLD,35));
        add(InfZwyciesca);

        ZagrajPonownie = new JButton("Zagraj ponownie");
        ZagrajPonownie.setBounds(180,250,250,100);
        ZagrajPonownie.setFont(czcionka);
        ZagrajPonownie.setBackground(Color.white);
        add(ZagrajPonownie);
        ZagrajPonownie.addActionListener(this);

        WyjscieMenu = new JButton("Wyjscie do menu");
        WyjscieMenu.setBounds(180,450,250,100);
        WyjscieMenu.setBackground(Color.white);
        WyjscieMenu.setFont(czcionka);
        add(WyjscieMenu);
        WyjscieMenu.addActionListener(this);
    }
    OknoZakoncz(String aktywny_gracz,int ilosc){
        wielkosc=ilosc;
        setTitle("Kółko&Krzyżyk");
        setSize(650,650);
        setLocation(400,300);
        setLayout(null);

        InfZwyciesca=new JLabel("Wygrał gracz: "+aktywny_gracz);
        InfZwyciesca.setBounds(170,20,300,150);
        InfZwyciesca.setFont(new Font("SansSerif",Font.BOLD,35));
        if(aktywny_gracz=="X") {
            InfZwyciesca.setForeground(Color.red);
        }else{
            InfZwyciesca.setForeground(Color.green);
        }
        add(InfZwyciesca);

        ZagrajPonownie = new JButton("Zagraj ponownie");
        ZagrajPonownie.setBounds(180,250,250,100);
        ZagrajPonownie.setBackground(Color.white);
        ZagrajPonownie.setFont(czcionka);
        add(ZagrajPonownie);
        ZagrajPonownie.addActionListener(this);

        WyjscieMenu = new JButton("Wyjscie do menu");
        WyjscieMenu.setBounds(180,450,250,100);
        WyjscieMenu.setFont(czcionka);
        WyjscieMenu.setBackground(Color.white);
        add(WyjscieMenu);
        WyjscieMenu.addActionListener(this);
    }
    public void actionPerformed(ActionEvent a) {
        Object z = a.getSource();
        if(z==ZagrajPonownie){
            OknoGry OknoG=new OknoGry(wielkosc);
            OknoG.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoG.setVisible(true);
            setEnabled(false);
            dispose();
        }
        if(z==WyjscieMenu){
            OknoMenu OknoM=new OknoMenu();
            OknoM.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoM.setVisible(true);
            setEnabled(false);
            dispose();
        }
    }
}
