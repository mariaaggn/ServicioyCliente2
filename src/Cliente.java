import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        //Conectar a localhost:5000
        //Obtener Bufferered reader y printwriter
        try (Socket socket = new Socket("localhost", 5100);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner sc = new Scanner(System.in);
        ) {//Bucle de leer teclado, enviar, recibir respuesta y mostrar
            System.out.println(in.readLine());
            System.out.println(in.readLine());
            String line = sc.nextLine();
            boolean acertado = false;
            //Salir cuando el usuario escriba fin
            while (!acertado) {
                out.println(line);
                String respuesta = in.readLine();
                System.out.println(respuesta);
                if (respuesta.contains("Correcto")) {
                    acertado = true;
                } else {
                    line = sc.nextLine();
                }
            }
            out.println(line);
        } catch (IOException e) {
            System.out.println("Error de conexión" + e.getMessage());
        }
    }
}