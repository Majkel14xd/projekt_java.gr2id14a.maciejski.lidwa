import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class OknoMenu extends JFrame implements ActionListener {
    JButton poleG,poleZ,poleS;
    JLabel tekst,tytul,error;
    JTextField polet;
    Font czcionka=new Font("Dialog",Font.PLAIN,20);
    int ilosc;
    OknoMenu(){
        setTitle("Kółko&Krzyżyk");
        setSize(650,650);
        setLocation(400,300);
        setLayout(null);
        getContentPane().setBackground(Color.lightGray);

        tytul=new JLabel("Kółko & krzyżyk");
        tytul.setBounds(180,20,300,150);
        tytul.setFont(new Font("SansSerif",Font.BOLD,35));
        add(tytul);

        poleG = new JButton("Graj");
        poleG.setBounds(120,200,250,100);
        poleG.setFont(czcionka);
        poleG.setBackground(Color.white);
        add(poleG);
        poleG.addActionListener(this);
        tekst=new JLabel("Wielkosc pola 3-10: ");
        tekst.setBounds(410,190,160,50);
        tekst.setFont(new Font("SansSerif",Font.BOLD,16));
        add(tekst);
        polet=new JTextField();
        polet.setBounds(410,240,50,50);
        polet.setFont(new Font("SansSerif",Font.BOLD,16));
        polet.setText("3");
        add(polet);
        poleS = new JButton("Graj online");
        poleS.setFont(czcionka);
        poleS.setBounds(120,320,250,100);
        poleS.setBackground(Color.white);
        add(poleS);
        poleS.addActionListener(this);

        poleZ = new JButton("Zakoncz");
        poleZ.setBounds(120,450,250,100);
        poleZ.setFont(czcionka);
        poleZ.setBackground(Color.white);
        add(poleZ);
        poleZ.addActionListener(this);

        error=new JLabel("Błąd danych!");
        error.setBounds(410,280,250,50);
        error.setFont(new Font("SansSerif",Font.BOLD,12));
        error.setForeground(Color.red);
        add(error);
        error.setVisible(false);

    }
    public void actionPerformed(ActionEvent a){
        Object z=a.getSource();
        if(z==poleG){
            ilosc=Integer.parseInt(polet.getText());
            if(ilosc>=3&&ilosc<=10) {
                OknoGry OknoG = new OknoGry(ilosc);
                OknoG.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                OknoG.setVisible(true);
                setEnabled(false);
                dispose();
            }else{
                error.setVisible(true);
            }
        }
        if(z==poleS) {
            OknoGryS OknoS1 = new OknoGryS(3);
            OknoS1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoS1.setVisible(true);
            new Siec(OknoS1);
            setEnabled(false);
            dispose();
            OknoGryC OknoS2 = new OknoGryC(3);
            OknoS2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoS2.setVisible(true);
            try {
                new SiecClient(OknoS2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setEnabled(false);
            dispose();
        }
        if(z==poleZ){
            dispose();
        }

    }
}
