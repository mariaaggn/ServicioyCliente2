import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Servidor {

    public static void main(String[] args) {
        // Crear ServerSocket en el puerto 5100
        final int PUERTO = 5100;
        int intentos = 1;
        int numeroRandom = (int) (Math.random() * 100) + 1;
        int clienteNumero = -5;
        boolean acertado;

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Adivina el número en puerto " + PUERTO);

            try (Socket socket = serverSocket.accept();BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 ) {
                System.out.println("Servidor 'Adivina el número' en el puerto " + PUERTO);
                System.out.println("Nuevo cliente. Número secreto: " + numeroRandom);
                out.println("Conectado al juego 'Adivina el número");
                out.println("He pensado un númeor entre 1 y 100. Adivínalo");

                 acertado = false;

                while (!acertado) {
                    clienteNumero = Integer.parseInt(in.readLine());
                    if (clienteNumero == numeroRandom) {
                        acertado = true;
                        System.out.println("Cliente acertado en " +intentos + " intentos");
                        out.println("Correcto" + intentos);
                        out.println("Enhorabuena");
                    } else {
                        intentos++;
                        if (numeroRandom > clienteNumero) {
                            out.println("El numRandom es MAYOR");
                        }else {
                            out.println("El numRandom es MENOR");
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}