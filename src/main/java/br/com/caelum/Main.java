package br.com.caelum;


import br.com.caelum.tagarela.interfaces.CommandLineIO;
import br.com.caelum.tagarela.services.FileIOService;
import br.com.caelum.tagarela.services.TranscriptionService;
import br.com.caelum.tagarela.util.RecognitionUtils;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechRecognitionResult;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        RecognitionConfig config = RecognitionUtils.getRecognitionConfig();
        CommandLineIO commandLine = new CommandLineIO(args);

        if(commandLine.needHelp()) {
            commandLine.showHelp();
        }

        String audioFilePath = commandLine.getUri();
        FileIOService fileIO = new FileIOService();

        System.out.println("Reading from: " + audioFilePath);
        System.out.println("Will be transcripted to: " + commandLine.getOutputPath());

        RecognitionAudio recognitionAudio = RecognitionUtils.getRecognitionAudio(audioFilePath);
        TranscriptionService transcriptionService = new TranscriptionService(config, recognitionAudio);

        List<SpeechRecognitionResult> results = transcriptionService.transcribe();

        fileIO.writeTranscriptionResults(results, commandLine.getOutputPath());
    }

}
