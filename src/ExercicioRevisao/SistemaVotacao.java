
package ExercicioRevisao;
import java.util.*;

public class SistemaVotacao {

    static class Candidato {
        private int numero;
        private String nome;
        private int votos;

        public Candidato(int numero, String nome) {
            this.numero = numero;
            this.nome = nome;
            this.votos = 0;
        }

        public int getNumero() { return numero; }
        public String getNome() { return nome; }
        public int getVotos() { return votos; }
        public void adicionarVoto() { votos++; }
    }

    static class Voto {
        private String idEleitor;
        private String voto; // número, "BRANCO" ou "NULO"

        public Voto(String idEleitor, String voto) {
            this.idEleitor = idEleitor;
            this.voto = voto;
        }

        public String getIdEleitor() { return idEleitor; }
        public String getVoto() { return voto; }
    }

    // Estruturas principais
    private static Map<Integer, Candidato> candidatos = new HashMap<>();
    private static List<Voto> votos = new ArrayList<>();
    private static Set<String> eleitoresQueVotaram = new HashSet<>();

    // Contadores
    private static int votosBrancos = 0;
    private static int votosNulos = 0;
    private static boolean votacaoAberta = false;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            try {
                System.out.print("Escolha uma opção: ");
                opcao = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Entrada inválida! Digite um número.");
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> cadastrarCandidato();
                case 2 -> alternarVotacao();
                case 3 -> registrarVoto();
                case 4 -> finalizarVotacao();
                case 5 -> exibirRelatorioFinal();
                case 6 -> exibirLogAuditoria();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("⚠️ Opção inválida! Tente novamente.");
            }

        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\n===== SISTEMA DE VOTAÇÃO =====");
        System.out.println("1) Mesário - Cadastrar candidato");
        System.out.println("2) Mesário - Iniciar/Encerrar votação");
        System.out.println("3) Eleitor - Registrar voto");
        System.out.println("4) Mesário - Finalizar votação");
        System.out.println("5) Mesário - Relatório final");
        System.out.println("6) Log de auditoria (eleitores e votos)");
        System.out.println("0) Sair");
    }

    // --- 1) CADASTRAR CANDIDATO ---
    private static void cadastrarCandidato() {
        if (votacaoAberta) {
            System.out.println("⚠️ Não é possível cadastrar candidatos com a votação em andamento!");
            return;
        }

        try {
            System.out.print("Número do candidato: ");
            int numero = Integer.parseInt(sc.nextLine());

            if (numero <= 0) {
                System.out.println("⚠️ O número do candidato deve ser positivo!");
                return;
            }

            if (candidatos.containsKey(numero)) {
                System.out.println("⚠️ Já existe um candidato com esse número!");
                return;
            }

            System.out.print("Nome do candidato: ");
            String nome = sc.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("⚠️ Nome do candidato não pode ser vazio!");
                return;
            }

            candidatos.put(numero, new Candidato(numero, nome));
            System.out.println("✅ Candidato cadastrado com sucesso!");

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada inválida! Digite apenas números inteiros para o número do candidato.");
        }
    }

    // --- 2) INICIAR/ENCERRAR VOTAÇÃO ---
    private static void alternarVotacao() {
        if (candidatos.isEmpty()) {
            System.out.println("⚠️ Cadastre ao menos um candidato antes de iniciar a votação.");
            return;
        }

        votacaoAberta = !votacaoAberta;
        System.out.println(votacaoAberta ? "✅ Votação INICIADA!" : "⛔ Votação ENCERRADA!");
    }

    // --- 3) REGISTRAR VOTO ---
    private static void registrarVoto() {
        if (!votacaoAberta) {
            System.out.println("⚠️ A votação ainda não foi iniciada!");
            return;
        }

        System.out.print("Digite o identificador do eleitor: ");
        String id = sc.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("⚠️ O identificador do eleitor não pode ser vazio!");
            return;
        }

        if (eleitoresQueVotaram.contains(id)) {
            System.out.println("⚠️ Este eleitor já votou!");
            return;
        }

        System.out.println("Digite o número do candidato ou:");
        System.out.println(" - 0 para voto em BRANCO");
        System.out.println(" - 9999 para voto NULO");
        System.out.print("Seu voto: ");

        String entrada = sc.nextLine();

        try {
            int numeroVoto = Integer.parseInt(entrada);
            if (numeroVoto == 0) {
                votosBrancos++;
                votos.add(new Voto(id, "BRANCO"));
                System.out.println("🗳️ Voto em BRANCO registrado.");
            } else if (numeroVoto == 9999) {
                votosNulos++;
                votos.add(new Voto(id, "NULO"));
                System.out.println("🗳️ Voto NULO registrado.");
            } else if (candidatos.containsKey(numeroVoto)) {
                candidatos.get(numeroVoto).adicionarVoto();
                votos.add(new Voto(id, String.valueOf(numeroVoto)));
                System.out.println("🗳️ Voto registrado com sucesso!");
            } else {
                votosNulos++;
                votos.add(new Voto(id, "NULO"));
                System.out.println("⚠️ Candidato inexistente. Voto considerado NULO.");
            }

            eleitoresQueVotaram.add(id);

        } catch (NumberFormatException e) {
            System.out.println("⚠️ Entrada inválida! Digite um número válido.");
        }
    }

    // --- 4) FINALIZAR VOTAÇÃO ---
    private static void finalizarVotacao() {
        if (!votacaoAberta) {
            System.out.println("⚠️ A votação já está encerrada ou ainda não foi iniciada.");
            return;
        }
        votacaoAberta = false;
        System.out.println("⛔ Votação encerrada com sucesso!");
    }

    // --- 5) RELATÓRIO FINAL ---
    private static void exibirRelatorioFinal() {
        if (votacaoAberta) {
            System.out.println("⚠️ Encerre a votação antes de gerar o relatório!");
            return;
        }

        int totalVotosValidos = candidatos.values().stream().mapToInt(Candidato::getVotos).sum();
        int totalGeral = totalVotosValidos + votosBrancos + votosNulos;

        if (totalGeral == 0) {
            System.out.println("⚠️ Nenhum voto foi registrado!");
            return;
        }

        System.out.println("\n===== RELATÓRIO FINAL =====");
        for (Candidato c : candidatos.values()) {
            double percentual = (c.getVotos() * 100.0) / totalGeral;
            System.out.printf("%s (%d): %d votos (%.2f%%)\n", c.getNome(), c.getNumero(), c.getVotos(), percentual);
        }

        System.out.printf("\nBrancos: %d (%.2f%%)\n", votosBrancos, (votosBrancos * 100.0) / totalGeral);
        System.out.printf("Nulos: %d (%.2f%%)\n", votosNulos, (votosNulos * 100.0) / totalGeral);
        System.out.printf("Total de votos: %d\n", totalGeral);

        determinarVencedor(totalGeral);
    }

    // --- Determinar vencedor e segundo turno ---
    private static void determinarVencedor(int totalGeral) {
        List<Candidato> ranking = new ArrayList<>(candidatos.values());
        ranking.sort((a, b) -> Integer.compare(b.getVotos(), a.getVotos()));

        Candidato primeiro = ranking.get(0);
        double percPrimeiro = (primeiro.getVotos() * 100.0) / totalGeral;

        if (percPrimeiro > 50) {
            System.out.printf("\n🏆 VENCEDOR: %s (%d) com %.2f%% dos votos!\n",
                    primeiro.getNome(), primeiro.getNumero(), percPrimeiro);
        } else if (ranking.size() > 1) {
            Candidato segundo = ranking.get(1);
            System.out.println("\n⚠️ Nenhum candidato obteve mais de 50% dos votos.");
            System.out.printf("➡️ Haverá segundo turno entre %s (%d) e %s (%d)\n",
                    primeiro.getNome(), primeiro.getNumero(), segundo.getNome(), segundo.getNumero());
        }
    }

    // --- 6) LOG DE AUDITORIA ---
    private static void exibirLogAuditoria() {
        if (votos.isEmpty()) {
            System.out.println("Nenhum voto registrado.");
            return;
        }

        System.out.println("\n===== LOG DE AUDITORIA =====");
        for (Voto v : votos) {
            System.out.printf("Eleitor [%s] → Voto: %s\n", v.getIdEleitor(), v.getVoto());
        }
    }
}
