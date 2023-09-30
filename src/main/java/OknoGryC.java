import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class OknoGryC extends JFrame implements ActionListener {
    String aktywny_gracz="O";
    int wielkosc;
    boolean koniec;
    boolean sprawdz;
    JPanel plansza;
    JButton[][] pole;
    String wiadomosc;
    String ii;
    String jj;

    OknoGryC(int ilosc){
        wielkosc=ilosc;
        setTitle("Kółko&Krzyżyk - Klient");
        setSize(650,650);
        setLocation(1000,300);
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
    public void sendS(String message)throws IOException,ClassNotFoundException{
        try{
            if(Siec.wyjscie!=null){
                System.out.println("siec"+Siec.wyjscie);
                Siec.wyjscie.writeObject(message);
            }
        } catch (IOException e) {
            System.out.println("Brak klienta");
            e.printStackTrace();
        }
    }
    public void sendC(String message)throws IOException,ClassNotFoundException{
        try{
            if(SiecClient.wyjscie!=null){
                SiecClient.wyjscie.writeObject(message);
            }
        } catch (IOException e) {
            System.out.println("Brak Serwera");
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent a){
        Object z=a.getSource();
        for(int i=0;i<wielkosc;i++) {
            for (int j = 0; j < wielkosc; j++) {
                if (z==pole[i][j]) {
                    if(pole[i][j].getText()=="") {
                        sprawdz = true;
                        pole[i][j].setText(aktywny_gracz);
                        ii= String.valueOf(i);
                        jj= String.valueOf(j);
                        wiadomosc=i+" "+j;
                        try {
                            sendC(wiadomosc);
                            System.out.println(wiadomosc);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        if (aktywny_gracz == "O") {
                            pole[i][j].setForeground(Color.red);
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
            OknoZakonczOnline OknoZ=new OknoZakonczOnline(aktywny_gracz,1,wielkosc);
            OknoZ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoZ.setVisible(true);
            setEnabled(false);
            dispose();
            //System.exit(0);
        }
        if(Remis(pole,wielkosc)==true && koniec==false){

            OknoZakonczOnline OknoZ=new OknoZakonczOnline(wielkosc);
            OknoZ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            OknoZ.setVisible(true);
            setEnabled(false);
            dispose();
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
