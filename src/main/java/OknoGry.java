import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OknoGry extends JFrame implements ActionListener {
    String aktywny_gracz="X";
    int wielkosc;
    boolean koniec;
    boolean sprawdz;
    JPanel plansza;
    JButton[][] pole;

    OknoGry(int ilosc){
        wielkosc=ilosc;
        setTitle("Kółko&Krzyżyk");
        setSize(650,650);
        setLocation(400,300);
        this.setLayout(new FlowLayout());
        plansza=new JPanel(new GridLayout(wielkosc,wielkosc));
        plansza.setPreferredSize(new Dimension(600,600));
        getContentPane().setBackground(Color.BLACK);

        pole=new JButton[wielkosc][wielkosc];
        for(int i=0;i<wielkosc;i++){
            for(int j=0;j<wielkosc;j++) {
                pole[i][j] = new JButton();
                pole[i][j].setName(String.valueOf(i)+String.valueOf(j));
                pole[i][j].setText("");
                pole[i][j].setBackground(Color.white);
                pole[i][j].setFont(new Font("SansSerif",Font.BOLD,20));

                pole[i][j].addActionListener(this);
                plansza.add(pole[i][j]);
            }
        }
        add(plansza);
    }

    public void actionPerformed(ActionEvent a){
        Object z=a.getSource();
        for(int i=0;i<wielkosc;i++) {
            for (int j = 0; j < wielkosc; j++) {
                if (z==pole[i][j]) {
                    if(pole[i][j].getText()=="") {
                        sprawdz = true;
                        pole[i][j].setText(aktywny_gracz);
                        if (aktywny_gracz == "X") {
                            pole[i][j].setForeground(Color.red);
                        } else {
                            pole[i][j].setForeground(Color.green);
                        }
                        //System.out.println(i+" "+j);
                    }else{
                        sprawdz=false;
                    }
                }
            }
        }
        koniec=RuchG(pole,aktywny_gracz,wielkosc);
        if(koniec==true){
            OknoZakoncz OknoZ=new OknoZakoncz(aktywny_gracz,wielkosc);
            OknoZ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoZ.setVisible(true);
            setEnabled(false);
            dispose();
            //System.exit(0);
        }
        if(Remis(pole,wielkosc)==true && koniec==false){

            OknoZakoncz OknoZ=new OknoZakoncz(wielkosc);
            OknoZ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoZ.setVisible(true);
            setEnabled(false);
            dispose();
        }
        if(sprawdz==true) {
            if (aktywny_gracz == "X") {
                aktywny_gracz = "O";
            } else {
                aktywny_gracz = "X";
            }
        }
    }
    public static boolean Wygranakolumny(JButton pole[][],String aktywny_gracz, int wielkosc){
        for(int j=0;j<wielkosc;j++){
            boolean wygrana=true;
            for(int i=0;i<wielkosc;i++){
                if(pole[i][j].getText()!=aktywny_gracz){
                    wygrana=false;
                    break;
                }
            }
            if(wygrana){
                return true;
            }
        }
        return false;
    }
    public static boolean WygranaWiersze(JButton pole[][],String aktywny_gracz, int wielkosc){
        for(int i=0;i<wielkosc;i++){
            boolean wygrana=true;
            for(int j=0;j<wielkosc;j++){
                if(pole[i][j].getText()!=aktywny_gracz){
                    wygrana=false;
                    break;
                }
            }
            if(wygrana){
                return true;
            }
        }
        return false;
    }
    public static boolean WygranaSkos1(JButton pole[][],String aktywny_gracz, int wielkosc){
        for(int i=0;i<wielkosc;i++){
            if(pole[i][i].getText()!=aktywny_gracz){
                return false;
            }
        }
        return true;
    }
    public static boolean WygranaSkos2(JButton pole[][],String aktywny_gracz, int wielkosc){
        int i=0;
        int j=wielkosc-1;
        while(i<wielkosc){
                if (pole[i][j].getText() != aktywny_gracz) {
                    return false;
                }
                i++;
                j--;
            }
        return true;
    }
    public static boolean Remis(JButton pole[][],int wielkosc){
        int ilosc=0;
        for(int i=0;i<wielkosc;i++){
            for(int j=0;j<wielkosc;j++){
                if(pole[i][j].getText()!=""){
                    ilosc++;
                }
            }
        }
        if(ilosc==(wielkosc*wielkosc)){
            return true;
        }else{
            return false;
        }
    }
    public static boolean RuchG(JButton pole[][],String aktywny_gracz,int wielkosc){
        boolean wygrana_k=Wygranakolumny(pole,aktywny_gracz,wielkosc);
        boolean wygrana_w=WygranaWiersze(pole,aktywny_gracz,wielkosc);
        boolean wygrana_s1=WygranaSkos1(pole,aktywny_gracz,wielkosc);
        boolean wygrana_s2=WygranaSkos2(pole,aktywny_gracz,wielkosc);

        if(wygrana_k || wygrana_w || wygrana_s1 ||wygrana_s2 ){
            return true;
        }else{
            return  false;
        }
    }

}