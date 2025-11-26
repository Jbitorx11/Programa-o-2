import java.util.*;

public class Agenda {

    public static Map<String, List<String>> agenda = new HashMap<>();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1) Criar evento");
            System.out.println("2) Editar evento");
            System.out.println("3) Remover evento específico");
            System.out.println("4) Remover todos os eventos de uma data");
            System.out.println("5) Listar eventos de uma data");
            System.out.println("6) Pesquisar eventos por palavra-chave");
            System.out.println("7) Mostrar todos os eventos");
            System.out.println("8) Sair");
            System.out.print("Opções: ");
            opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {

                case 1:
                    criarEvento(input);
                    break;

                case 2:
                    editarEvento(input);
                    break;

                case 3:
                    removerEventoEspecifico(input);
                    break;

                case 4:
                    removerTodosEventosData(input);
                    break;

                case 5:
                    listarEventosPorData(input);
                    break;

                case 6:
                    pesquisarEventosPorPalavra(input);
                    break;

                case 7:
                    mostrarTodosEventos();
                    break;

                case 8:
                    System.out.println("Encerrando agenda...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 8);

    }

    public static void criarEvento(Scanner input) {
        System.out.print("Data (DD/MM/YYYY): ");
        String data = input.nextLine();

        System.out.print("Horário (HH:MM): ");
        String hora = input.nextLine();

        System.out.print("Título: ");
        String titulo = input.nextLine();

        List<String> eventos = agenda.getOrDefault(data, new ArrayList<>());
        String evento = hora + " - " + titulo;

        if (eventos.contains(evento)) {
            System.out.println("Já existe evento nesse horário.");
        } else {
            eventos.add(evento);
            Collections.sort(eventos);
            agenda.put(data, eventos);
            System.out.println("Evento criado com sucesso!");
        }
    }

    public static void editarEvento(Scanner input) {
        System.out.print("Data do evento (DD/MM/YYYY): ");
        String data = input.nextLine();

        List<String> eventos = agenda.get(data);

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento nessa data.");
            return;
        }

        System.out.println("\nEventos dessa data:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ") " + eventos.get(i));
        }

        System.out.print("Escolha o número do evento para editar: ");
        int num = input.nextInt();
        input.nextLine();

        if (num < 1 || num > eventos.size()) {
            System.out.println("Número inválido!");
            return;
        }

        System.out.print("Novo horário (HH:MM): ");
        String hora = input.nextLine();

        System.out.print("Novo título: ");
        String titulo = input.nextLine();

        String novoEvento = hora + " - " + titulo;

        eventos.set(num - 1, novoEvento);
        Collections.sort(eventos);
        System.out.println("Evento atualizado!");
    }

    public static void removerEventoEspecifico(Scanner input) {
        System.out.print("Data (DD/MM/YYYY): ");
        String data = input.nextLine();

        List<String> eventos = agenda.get(data);

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento nessa data.");
            return;
        }

        System.out.println("\nEventos dessa data:");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ") " + eventos.get(i));
        }

        System.out.print("Digite o número do evento para remover: ");
        int num = input.nextInt();
        input.nextLine();

        if (num < 1 || num > eventos.size()) {
            System.out.println("Número inválido.");
            return;
        }

        String removido = eventos.remove(num - 1);

        if (eventos.isEmpty()) {
            agenda.remove(data);
        }

        System.out.println("Evento removido: " + removido);
    }

    public static void removerTodosEventosData(Scanner input) {
        System.out.print("Data (DD/MM/YYYY): ");
        String data = input.nextLine();

        if (agenda.containsKey(data)) {
            agenda.remove(data);
            System.out.println("Todos os eventos dessa data foram removidos.");
        } else {
            System.out.println("Nenhum evento encontrado.");
        }
    }

    public static void listarEventosPorData(Scanner input) {
        System.out.print("Data (DD/MM/YYYY): ");
        String data = input.nextLine();

        List<String> eventos = agenda.get(data);

        if (eventos == null || eventos.isEmpty()) {
            System.out.println("Nenhum evento nessa data.");
        } else {
            System.out.println("\nEventos em " + data + ":");
            for (String e : eventos) {
                System.out.println(e);
            }
        }
    }

    public static void pesquisarEventosPorPalavra(Scanner input) {
        System.out.print("Palavra-chave: ");
        String chave = input.nextLine().toLowerCase();

        boolean achou = false;

        for (String data : agenda.keySet()) {
            for (String evento : agenda.get(data)) {
                if (evento.toLowerCase().contains(chave)) {
                    System.out.println(data + " → " + evento);
                    achou = true;
                }
            }
        }

        if (!achou) {
            System.out.println("Nenhum evento encontrado.");
        }
    }

    // MOSTRAR TODOS OS EVENTOS
    public static void mostrarTodosEventos() {
        if (agenda.isEmpty()) {
            System.out.println("Nenhum evento registrado.");
            return;
        }

        System.out.println("\n=== TODOS OS EVENTOS ===");
        for (String data : agenda.keySet()) {
            System.out.println(data + ":");
            for (String evento : agenda.get(data)) {
                System.out.println("  " + evento);
            }
        }
    }
}
