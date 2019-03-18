package br.com.caelum.tagarela.util;


import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;

import static com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding.LINEAR16;

public class RecognitionUtils {

    public static RecognitionConfig getRecognitionConfig() {
        return RecognitionConfig.newBuilder()
                .setEncoding(LINEAR16)
                .setLanguageCode("pt-BR")
                .build();
    }

    public static RecognitionAudio getRecognitionAudio(String gcsUri) {
        return RecognitionAudio.newBuilder()
                .setUri(gcsUri)
                .build();
    }
}