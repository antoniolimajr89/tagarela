package br.com.caelum.tagarela.services;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.cloud.speech.v1.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TranscriptionService {

    private RecognitionConfig config;
    private RecognitionAudio recognitionAudio;

    public TranscriptionService(RecognitionConfig config, RecognitionAudio recognitionAudio) {
        this.config = config;
        this.recognitionAudio = recognitionAudio;
    }

    public List<SpeechRecognitionResult> transcribe() throws IOException {

        List<SpeechRecognitionResult> results = null;

        try (SpeechClient speechClient = SpeechClient.create()) {

            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response = speechClient.longRunningRecognizeAsync(config, recognitionAudio);

            while(!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            results = response.get().getResultsList();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException("An error occurred while getting transcription", e);
        }

        return results;
    }
}