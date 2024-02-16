package br.com.alura.ScreenMatch.Service;

public interface IConverterDados {

    <T> T obterDados (String json, Class <T> classe);
}
