import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento implements Comparable<Evento> {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public boolean estaOcorrendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        return horario.isBefore(agora.plusHours(1)) && horario.isAfter(agora.minusHours(1));
    }

    @Override
    public int compareTo(Evento outro) {
        return this.horario.compareTo(outro.horario);
    }

    @Override
    public String toString() {
        return nome + " | " + categoria + " | " + endereco + " | " + horario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n" + descricao;
    }

    public String toFileString() {
        return nome + ";" + endereco + ";" + categoria + ";" + horario.toString() + ";" + descricao;
    }

    public static Evento fromFileString(String linha) {
        String[] partes = linha.split(";");
        return new Evento(partes[0], partes[1], partes[2], LocalDateTime.parse(partes[3]), partes[4]);
    }
}
