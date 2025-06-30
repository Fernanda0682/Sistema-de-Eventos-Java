import java.io.*;
import java.util.*;
import java.time.LocalDateTime;

public class EventoManager {
    private List<Evento> eventos;
    private final String arquivo = "events.data";

    public EventoManager() {
        eventos = new ArrayList<>();
        carregarEventos();
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public List<Evento> listarEventos() {
        eventos.sort(null);
        return eventos;
    }

    public List<Evento> eventosOcorrendoAgora() {
        List<Evento> ocorrendo = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.estaOcorrendoAgora()) {
                ocorrendo.add(e);
            }
        }
        return ocorrendo;
    }

    public List<Evento> eventosPassados() {
        List<Evento> passados = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getHorario().isBefore(LocalDateTime.now())) {
                passados.add(e);
            }
        }
        return passados;
    }

    private void salvarEventos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Evento e : eventos) {
                bw.write(e.toFileString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos.");
        }
    }

    private void carregarEventos() {
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                eventos.add(Evento.fromFileString(linha));
            }
        } catch (IOException e) {
            System.out.println("Arquivo de eventos não encontrado. Um novo será criado.");
        }
    }
}
