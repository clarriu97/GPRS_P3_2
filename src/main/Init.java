package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Init {

    //Campos
    private FEL fel;

    //Constructor
    public Init(){
        pedirDatos();
    }

    //Metodos
    private void showError() {
        System.out.println("Ha debido ocurrir un error, revise que ha introducido bien los datos de entrada");
        pedirDatos();
    }

    private void pedirDatos(){
        try {
            Scanner entrada = new Scanner(System.in);
            System.out.println("Introduzca los datos de entrada");
            String frase = entrada.nextLine();
            String[] fraseDividida = frase.split(" ");
            //Ayuda:
            //"simula"
            //ficheroLlegadasPremium.txt
            //ficheroLlegadasBasicos.txt
            //tiempoServicioBloque0
            //maxColaBloque0
            //numServidoresCPD1
            //maxColaCPD1
            //numServidoresCPD2
            //maxColaCPD2
            //parametroDeRetorno
            //<instantesDeSalida.txt> opcional
            ////instante de peticion, tipo de usuario, retornada, tiempo de servicio, CPD y rechazada
            if (fraseDividida[0].equals("simula") && (fraseDividida.length== 11 || fraseDividida.length == 10)){
                File file = new File(fraseDividida[1]);
                Scanner scanner = new Scanner(file);
                //fel = new FEL();
                while (scanner.hasNextLine()){
                    String line = scanner.nextLine();
                    String[] lineSplit = line.split(" ");
                    //fel.addEvent(new Event(true, Double.parseDouble(lineSplit[0]), Double.parseDouble(lineSplit[1]), false));
                }
                scanner.close();
                try{
                    int numProcesadores = Integer.parseInt(fraseDividida[2]);
                    int numCola = Integer.parseInt(fraseDividida[3]);
                    //cpd = new CPD(numProcesadores, numCola, fel);

                } catch (NumberFormatException e){
                    showError();
                }
                //Aqui empieza a procesar
                //cpd.process();
            } else {
                showError();
            }
        } catch (FileNotFoundException e){
            showError();
            e.printStackTrace();
        }
    }
}
