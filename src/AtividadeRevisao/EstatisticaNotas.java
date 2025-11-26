package AtividadeRevisao;
import java.util.Scanner;

public class EstatisticaNotas {
    public static double calcularMedia(double[] notas) {
        double soma = 0;
        for (int i = 0; i < notas.length; i++) {
            soma += notas[i];
        }
        return soma / notas.length;
    }

    public static int contarAcimaDaMedia(double[] notas, double media) {
        int contador = 0;
        for (int i = 0; i < notas.length; i++) {
            if (notas[i] > media) {
                contador++;
            }
        }
        return contador;
    }

    public static double maiorNota(double[] notas) {
        double maior = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] > maior) {
                maior = notas[i];
            }
        }
        return maior;
    }

    public static double menorNota(double[] notas) {
        double menor = notas[0];
        for (int i = 1; i < notas.length; i++) {
            if (notas[i] < menor) {
                menor = notas[i];
            }
        }
        return menor;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== MENU DE DESEMPENHO DA TURMA ===");
            System.out.println("1 - Inserir notas e calcular estatísticas");
            System.out.println("2 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    double[] notas = new double[30];
                    for (int i = 0; i < 30; i++) {
                        System.out.print("Digite a nota do aluno " + (i + 1) + ": ");
                        notas[i] = sc.nextDouble();
                    }

                    double media = calcularMedia(notas);
                    int acima = contarAcimaDaMedia(notas, media);
                    double maior = maiorNota(notas);
                    double menor = menorNota(notas);

                    System.out.println("\n=== RESULTADOS ===");
                    System.out.printf("Média da turma: %.2f\n", media);
                    System.out.println("Alunos acima da média: " + acima);
                    System.out.printf("Maior nota: %.2f\n", maior);
                    System.out.printf("Menor nota: %.2f\n", menor);
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


