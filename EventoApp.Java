import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventoApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventoManager manager = new EventoManager();

        System.out.println("Bem-vindo ao sistema de eventos!");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua cidade: ");
        String cidade = scanner.nextLine();
        Usuario usuario = new Usuario(nome, email, cidade);

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar evento");
            System.out.println("2 - Listar todos os eventos");
            System.out.println("3 - Confirmar participação em evento");
            System.out.println("4 - Ver eventos confirmados");
            System.out.println("5 - Cancelar participação");
            System.out.println("6 - Ver eventos ocorrendo agora");
            System.out.println("7 - Ver eventos passados");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do evento: ");
                    String enome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String end = scanner.nextLine();
                    System.out.print("Categoria (festa, show, esportivo...): ");
                    String cat = scanner.nextLine();
                    System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
                    String dataHora = scanner.nextLine();
                    LocalDateTime horario = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    Evento evento = new Evento(enome, end, cat, horario, desc);
                    manager.adicionarEvento(evento);
                    System.out.println("Evento cadastrado!");
                }
                case 2 -> {
                    for (Evento e : manager.listarEventos()) {
                        System.out.println(e + "\n");
                    }
                }
                case 3 -> {
                    System.out.print("Nome do evento para confirmar: ");
                    String nomeEvento = scanner.nextLine();
                    for (Evento e : manager.listarEventos()) {
                        if (e.getNome().equalsIgnoreCase(nomeEvento)) {
                            usuario.confirmarEvento(e);
                            System.out.println("Participação confirmada!");
                        }
                    }
                }
                case 4 -> {
                    for (Evento e : usuario.getEventosConfirmados()) {
                        System.out.println(e + "\n");
                    }
                }
                case 5 -> {
                    System.out.print("Nome do evento para cancelar: ");
                    String nomeEvento = scanner.nextLine();
                    for (Evento e : new ArrayList<>(usuario.getEventosConfirmados())) {
                        if (e.getNome().equalsIgnoreCase(nomeEvento)) {
                            usuario.cancelarEvento(e);
                            System.out.println("Participação cancelada!");
                        }
                    }
                }
                case 6 -> {
                    for (Evento e : manager.eventosOcorrendoAgora()) {
                        System.out.println("Ocorrendo agora: " + e + "\n");
                    }
                }
                case 7 -> {
                    for (Evento e : manager.eventosPassados()) {
                        System.out.println("Já ocorreu: " + e + "\n");
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
}
