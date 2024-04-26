package BatalhaNavalCodigo;

import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    private char[][] tabuleiro;
    private int tamanhoTabuleiro;
    private boolean isPlayerTurn;
    private boolean isComputerPlayer;

    public BatalhaNaval(int tamanho, boolean isComputerPlayer) {
        tamanhoTabuleiro = tamanho;
        this.isComputerPlayer = isComputerPlayer;
        tabuleiro = new char[tamanho][tamanho];
        inicializarTabuleiro();
        isPlayerTurn = true;
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                tabuleiro[i][j] = '-';
            }
        }
    }

    public void imprimirTabuleiro() {
        System.out.print("  ");
        for (int i = 0; i < tamanhoTabuleiro; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < tamanhoTabuleiro; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tamanhoTabuleiro; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void colocarNavios(int numNavios, boolean isPlayer) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int navios = 0;

        while (navios < numNavios) {
            int x, y;
            if (isPlayer) {
                System.out.println("Digite a coordenada X para o navio " + (navios + 1) + ":");
                x = scanner.nextInt();
                System.out.println("Digite a coordenada Y para o navio " + (navios + 1) + ":");
                y = scanner.nextInt();
            } else {
                x = rand.nextInt(tamanhoTabuleiro);
                y = rand.nextInt(tamanhoTabuleiro);
            }

            if (tabuleiro[x][y] != 'B') {
                tabuleiro[x][y] = 'B';
                navios++;
            }
        }
    }

    public boolean atirar(int x, int y) {
        if (tabuleiro[x][y] == 'B') {
            tabuleiro[x][y] = 'X';
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Você gostaria de jogar contra o computador? (s/n)");
        String resposta = scanner.nextLine();
        boolean isComputerPlayer = resposta.equalsIgnoreCase("s");

        BatalhaNaval jogo = new BatalhaNaval(10, isComputerPlayer);

        jogo.colocarNavios(10, true);
        if (isComputerPlayer) {
            jogo.colocarNavios(10, false);
        } else {
            jogo.colocarNavios(10, true);
        }

        while (true) {
            jogo.imprimirTabuleiro();

            System.out.println("Digite a coordenada X:");
            int x = scanner.nextInt();

            System.out.println("Digite a coordenada Y:");
            int y = scanner.nextInt();

            if (jogo.atirar(x, y)) {
                System.out.println("Você acertou um navio!");
            } else {
                System.out.println("Você errou.");
            }
        }
    }
}
