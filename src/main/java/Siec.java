import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Siec implements Runnable {
    boolean koniec;
    ServerSocket polaczenie;
    public static Socket soket;
    public static ObjectInputStream wejscie;
    public static ObjectOutputStream wyjscie;
    public static int port = 6500;
    public static String wiadomosc;
    Thread thr;
    OknoGryS oknoglowne;
    int kolor=2;

    Siec(OknoGryS okno) {
        thr = new Thread(this);
        thr.start();
        oknoglowne = okno;
    }

    public void run() {
        try {
            System.out.println("Server uruchomiony");
            polaczenie = new ServerSocket(port);
        } catch (IOException i) {
            i.printStackTrace();
        }
        try {
            System.out.println("Server oczekuje na klienta");
            soket = polaczenie.accept();
        } catch (IOException i) {
            i.printStackTrace();
        }
        wiadomosc = "";
        try {
            wejscie = new ObjectInputStream(soket.getInputStream());
        } catch (IOException i) {
            i.printStackTrace();
        }
        try {
            wyjscie = new ObjectOutputStream(soket.getOutputStream());
        } catch (IOException i) {
            i.printStackTrace();
        }
        while (true) {
            try {
                wiadomosc = (String) wejscie.readObject();
            } catch (IOException i) {
                i.printStackTrace();
                System.out.println("Nie można nawiazać połączenia");
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
            if(wiadomosc.equals("wyjscie")) {
                try {
                    wiadomosc = "wyjscie";
                    wyjscie.writeObject(wiadomosc);
                    wyjscie.close();
                    wejscie.close();
                    soket.close();
                    polaczenie.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (wiadomosc != "") {
                oknoglowne.pole[Integer.parseInt(String.valueOf(wiadomosc.charAt(0)))][Integer.parseInt(String.valueOf(wiadomosc.charAt(2)))].setText("O");
                oknoglowne.pole[Integer.parseInt(String.valueOf(wiadomosc.charAt(0)))][Integer.parseInt(String.valueOf(wiadomosc.charAt(2)))].setForeground(Color.green);
                oknoglowne.pole[Integer.parseInt(String.valueOf(wiadomosc.charAt(0)))][Integer.parseInt(String.valueOf(wiadomosc.charAt(2)))].doClick();
                koniec = oknoglowne.RuchG(oknoglowne.pole, "O", 3);
                if (koniec == true) {
                    OknoZakonczOnline OknoZ = new OknoZakonczOnline("O",kolor, 3);
                    OknoZ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    OknoZ.setVisible(true);
                    oknoglowne.setEnabled(false);
                    oknoglowne.dispose();
                    try {
                        wiadomosc = "wyjscie";
                        wyjscie.writeObject(wiadomosc);
                        wyjscie.close();
                        wejscie.close();
                        soket.close();
                        polaczenie.close();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
class SiecClient implements Runnable {
    boolean koniec;
    Socket soket = null;
    public static ObjectInputStream wejscie;
    public static ObjectOutputStream wyjscie;
    public static String wiadomosc;
    int portC = 6500;
    OknoGryC oknoglowne;
    Thread thr;
    int kolor=2;
    SiecClient(OknoGryC okno) throws IOException,ClassCastException {
        thr = new Thread(this);
        oknoglowne = okno;
        try {
            soket = new Socket("Localhost", portC);
            wyjscie = new ObjectOutputStream(soket.getOutputStream());
            wyjscie.flush();
            wejscie = new ObjectInputStream(soket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        thr.start();
        wiadomosc = "";
    }
    public void run() {
        while (true) {
            try {
                wiadomosc = (String) wejscie.readObject();
            } catch (IOException i) {
                i.printStackTrace();
                System.out.println("Nie można nawiazać połączenia");
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }
            if(wiadomosc.equals("wyjscie")) {
                try {
                    wiadomosc = "wyjscie";
                    wyjscie.writeObject(wiadomosc);
                    wyjscie.close();
                    wejscie.close();
                    soket.close();
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (wiadomosc != "") {
                oknoglowne.pole[Integer.parseInt(String.valueOf(wiadomosc.charAt(0)))][Integer.parseInt(String.valueOf(wiadomosc.charAt(2)))].setText("X");
                oknoglowne.pole[Integer.parseInt(String.valueOf(wiadomosc.charAt(0)))][Integer.parseInt(String.valueOf(wiadomosc.charAt(2)))].setForeground(Color.green);
                oknoglowne.pole[Integer.parseInt(String.valueOf(wiadomosc.charAt(0)))][Integer.parseInt(String.valueOf(wiadomosc.charAt(2)))].doClick();
                koniec = oknoglowne.RuchG(oknoglowne.pole, "X", 3);
                if (koniec == true) {
                    OknoZakonczOnline OknoZ = new OknoZakonczOnline("X",kolor, 3);
                    OknoZ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    OknoZ.setVisible(true);
                    oknoglowne.setEnabled(false);
                    oknoglowne.dispose();
                    try {
                        wiadomosc = "wyjscie";
                        wyjscie.writeObject(wiadomosc);
                        wyjscie.close();
                        wejscie.close();
                        soket.close();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
