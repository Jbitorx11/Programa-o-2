package AtividadeRevisao;
import java.util.Scanner;

public class CriptigrafiaDeMatriz {

    public static void percorreMensagem(String mensagem) {
        System.out.println("\nPercorrendo a mensagem caractere por caractere:");
        for (int i = 0; i < mensagem.length(); i++) {
            char caractere = mensagem.charAt(i);
            System.out.println("Caractere " + (i + 1) + ": " + caractere);
        }
    }

    public static void exibirMatriz(char[][] matriz) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static char[][] rotacionar90(char[][] matriz) {
        char[][] nova = new char[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nova[j][3 - i] = matriz[i][j];
            }
        }
        return nova;
    }

    public static String matrizParaString(char[][] matriz) {
        String texto = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                texto = texto + matriz[i][j];
            }
        }
        return texto;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- MENU CRIPTOGRAFIA ---");
            System.out.println("1 - Criptografar mensagem");
            System.out.println("2 - Sair");
            System.out.print("Opções: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite uma mensagem: \n(até 16 caracteres) ");
                    String msg = sc.nextLine();

                    while (msg.length() < 16) {
                        msg = msg + " ";
                    }
                    msg = msg.substring(0, 16);


                    percorreMensagem(msg);

                    char[][] matriz = new char[4][4];
                    int k = 0;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            matriz[i][j] = msg.charAt(k);
                            k++;
                        }
                    }

                    System.out.println("\nMatriz Original:");
                    exibirMatriz(matriz);

                    char[][] cripto = rotacionar90(matriz);
                    System.out.println("\nMatriz Criptografada (90°):");
                    exibirMatriz(cripto);

                    String mensagemCripto = matrizParaString(cripto);
                    System.out.println("\nMensagem Criptografada: " + mensagemCripto);

                    char[][] original = rotacionar90(rotacionar90(rotacionar90(cripto)));
                    String mensagemOriginal = matrizParaString(original);
                    System.out.println("\nMensagem Original Recuperada: " + mensagemOriginal);
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


