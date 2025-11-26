package AtividadeRevisao;
import java.util.Scanner;
public class RotacaoDeMatriz {
    public static void exibirMatriz(int[][] matriz, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static int[][] rotacionarHorario(int[][] matriz, int n) {
        int[][] nova = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nova[j][n - 1 - i] = matriz[i][j];
            }
        }
        return nova;
    }

    public static int[][] rotacionarAntiHorario(int[][] matriz, int n) {
        int[][] nova = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nova[n - 1 - j][i] = matriz[i][j];
            }
        }
        return nova;
    }

    public static int[][] rotacionar180(int[][] matriz, int n) {
        int[][] nova = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nova[n - 1 - i][n - 1 - j] = matriz[i][j];
            }
        }
        return nova;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU DE ROTAÇÃO DE MATRIZ ===");
            System.out.println("1 - Rotacionar matriz NxN");
            System.out.println("2 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite o tamanho N da matriz (ex: 3 para 3x3): ");
                    int n = sc.nextInt();
                    int[][] matriz = new int[n][n];

                    System.out.println("\nDigite os valores da matriz (" + n + "x" + n + "):");
                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                            System.out.print("Elemento [" + i + "][" + j + "]: ");
                            matriz[i][j] = sc.nextInt();
                        }
                    }

                    System.out.println("\nMatriz Original:");
                    exibirMatriz(matriz, n);

                    int[][] horario = rotacionarHorario(matriz, n);
                    System.out.println("\nMatriz Rotacionada 90° (Horário):");
                    exibirMatriz(horario, n);

                    int[][] antihorario = rotacionarAntiHorario(matriz, n);
                    System.out.println("\nMatriz Rotacionada 90° (Anti-horário):");
                    exibirMatriz(antihorario, n);

                    int[][] duzentos = rotacionar180(matriz, n);
                    System.out.println("\nMatriz Rotacionada 180°:");
                    exibirMatriz(duzentos, n);
                    break;

                case 2:
                    System.out.println("Encerrando o programa...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 2);

        sc.close();
    }
}


