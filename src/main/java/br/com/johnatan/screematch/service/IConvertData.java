package br.com.johnatan.screematch.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass);
}
