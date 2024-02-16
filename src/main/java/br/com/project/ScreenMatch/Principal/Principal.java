package br.com.project.ScreenMatch.Principal;

import br.com.project.ScreenMatch.Model.DadosEpisodio;
import br.com.project.ScreenMatch.Model.DadosSerie;
import br.com.project.ScreenMatch.Model.DadosTemporada;
import br.com.project.ScreenMatch.Model.Episodio;
import br.com.project.ScreenMatch.Service.ConsumoApi;
import br.com.project.ScreenMatch.Service.ConverterDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
private Scanner leitura = new Scanner(System.in);
private ConsumoApi consumo = new ConsumoApi();
private ConverterDados conversor = new ConverterDados();
private final String ENDERECO = "https://www.omdbapi.com/?t=";
private final String API_KEY = "&apikey=515bc55a";

public void obterDadosCompletos(){
    System.out.println("Digite o nome da sua serie");
    var titulo = leitura.nextLine();
    var json = consumo.obterDados(ENDERECO + titulo.replace(" ",  "+++++++") + API_KEY);
    DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
    System.out.println(dados);

    List<DadosTemporada> temporadas = new ArrayList<>();

    for (int i = 1; i<=dados.totalTemporadas(); i++){
        json = consumo.obterDados(ENDERECO + titulo.replace(" ", "++++")
                +"&season=" + i + API_KEY);
        DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
        temporadas.add(dadosTemporada);
    }
    temporadas.forEach(System.out::println);

    System.out.println("\nLista ordenada");
    List<Episodio> episodios = temporadas.stream()
            .flatMap(t -> t.episodios().stream()
                    .map(d -> new Episodio(t.numero(), d))
            ).collect(Collectors.toList());

    episodios.forEach(System.out::println);

    List<DadosEpisodio> dadosEpisodios = temporadas.stream()
            .flatMap(t -> t.episodios().stream())
            .collect(Collectors.toList());

    System.out.println("\nTop 5 episódios");
    dadosEpisodios.stream()
            .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
            .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
            .limit(5)
            .forEach(System.out::println);

    System.out.println("\nA partir de que ano deseja ver o episódio? ");
    var ano = leitura.nextInt();
    leitura.nextLine();

    LocalDate dataBusca = LocalDate.of(ano, 1, 1);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    episodios.stream()
            .filter(e -> e.getDataLancamento() != null && e.getDataLancamento().isAfter(dataBusca))
            .forEach(e -> System.out.println(
                    ", Temporada: " + e.getTemporada() +
                            ", Episódio - " + e.getTitulo() +
                            ", Data lançamento: " + e.getDataLancamento().format(formatter)
            ));
}

}
