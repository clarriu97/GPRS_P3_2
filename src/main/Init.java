package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Init {

    //Campos
    private System system;

    //Constructor
    public Init(){
        pedirDatos();
    }

    //Metodos
    private void showError() {
        java.lang.System.out.println("Ha debido ocurrir un error, revise que ha introducido bien los datos de entrada");
        pedirDatos();
    }

    private void pedirDatos(){
        try {
            Scanner entrada = new Scanner(java.lang.System.in);
            java.lang.System.out.println("Introduzca los datos de entrada");
            String frase = entrada.nextLine();
            String[] fraseDividida = frase.split(" ");

            if (fraseDividida[0].equals("simula") && (fraseDividida.length == 11 || fraseDividida.length == 10)){
                List<Event> premiumEventList = new ArrayList<>();
                List<Event> basicEventList = new ArrayList<>();
                File file = new File(fraseDividida[1]);
                Scanner premium = new Scanner(file);
                while (premium.hasNextLine()){
                    String line = premium.nextLine();
                    String[] lineSplit = line.split(" ");
                    premiumEventList.add(new Event(true, Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1])));
                }
                premium.close();
                file = new File(fraseDividida[2]);
                Scanner basicos = new Scanner(file);
                while (basicos.hasNextLine()){
                    String line = basicos.nextLine();
                    String[] lineSplit = line.split(" ");
                    basicEventList.add(new Event(false, Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1])));
                }
                try{
                    Double tiempoServicioBloque0 = Double.parseDouble(fraseDividida[3]);
                    int maxColaBloque0 = Integer.parseInt(fraseDividida[4]);
                    int numServidoresCPD1 = Integer.parseInt(fraseDividida[5]);
                    int maxColaCPD1 = Integer.parseInt(fraseDividida[6]);
                    int numServidoresCPD2 = Integer.parseInt(fraseDividida[7]);
                    int maxColaCPD2 = Integer.parseInt(fraseDividida[8]);
                    double parametroDeRetorno = Double.parseDouble(fraseDividida[9]);
                    if (fraseDividida.length == 10){
                        system = new System(premiumEventList, basicEventList, tiempoServicioBloque0, maxColaBloque0, numServidoresCPD1,
                                maxColaCPD1, numServidoresCPD2, maxColaCPD2, parametroDeRetorno, null);
                    } else {
                        system = new System(premiumEventList, basicEventList, tiempoServicioBloque0, maxColaBloque0, numServidoresCPD1,
                                maxColaCPD1, numServidoresCPD2, maxColaCPD2, parametroDeRetorno, fraseDividida[10]);
                    }
                    //system.start();
                    //simula ficheroLlegadasPremium.txt ficheroLlegadasBasicos.txt 1 1 1 1 1 1 1
                } catch (NumberFormatException e){
                    showError();
                }
                for (Event event: premiumEventList){event.print();}
                for (Event event: basicEventList){event.print();}
            } else {
                showError();
            }
            entrada.close();
        } catch (FileNotFoundException e){
            showError();
            e.printStackTrace();
        }
    }
}
