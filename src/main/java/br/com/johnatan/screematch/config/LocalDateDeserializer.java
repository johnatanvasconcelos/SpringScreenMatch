package br.com.johnatan.screematch.config;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);

    // Define um formato alternativo caso a API retorne "N/A" ou outro formato inesperado
    private static final DateTimeFormatter fallbackFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String dateString = jsonParser.getText();

        if (dateString == null || dateString.trim().isEmpty() || dateString.equalsIgnoreCase("N/A")) {
            return null;
        }

        try {
            return LocalDate.parse(dateString, formatter);

        } catch (DateTimeParseException e) {
            // Se falhar, tenta com um formato alternativo
            // System.err.println("Falha ao parsear data '" + dateString + "' com formato principal. Tentando fallback...");
            try {
                // Tenta o formato ISO padrão como fallback
                return LocalDate.parse(dateString, fallbackFormatter);
            } catch (DateTimeParseException e2) {
                // Se ambos falharem, loga o erro e retorna null
                System.err.println("Não foi possível parsear a data: " + dateString + ". Formatos tentados: 'dd MMM yyyy' e 'yyyy-MM-dd'. Erro: " + e2.getMessage());
                return null;
            }
        }

    }
}
