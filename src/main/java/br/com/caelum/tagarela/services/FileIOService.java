package br.com.caelum.tagarela.services;

import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileIOService {
    private byte[] bytes = new byte[0];

    public ByteString getFileByteString(Path audioFilePath) {

        try {
            bytes = Files.readAllBytes(audioFilePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return ByteString.copyFrom(bytes);
    }

    public void writeTranscriptionResults(List<SpeechRecognitionResult> results, Path outputPath) {

        try(BufferedWriter writer = Files.newBufferedWriter(outputPath, Charset.forName("UTF-8"))) {

            for (SpeechRecognitionResult result : results) {
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);

                System.out.printf("Transcription: %s%n", alternative.getTranscript());

                writer.write(alternative.getTranscript() + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while writing the file", e);
        }

    }

}