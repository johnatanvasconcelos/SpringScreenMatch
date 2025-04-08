package br.com.johnatan.screematch.utils;

public interface IConvertData {
    <T> T getData(String json, Class<T> tClass);
}
