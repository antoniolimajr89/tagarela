package br.com.caelum.tagarela.interfaces;

import org.apache.commons.cli.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class CommandLineIO {

    private final String[] args;
    private CommandLine cmd;
    private Options options = new Options();
    private HelpFormatter helper = new HelpFormatter();

    public CommandLineIO(String[] args) {
        this.args = args;

        Option uriOption = new Option("u", "uri", true, "URI to audio resource");
        Option outputOption = new Option("o", "out", true, "Path to transcribe output");
        Option helperOption = new Option("h", "help", false, "Show commands");

        options.addOption(uriOption);
        options.addOption(outputOption);
        options.addOption(helperOption);

        CommandLineParser cmdParser = new DefaultParser();

        HelpFormatter helper = new HelpFormatter();

        try {
            this.cmd = cmdParser.parse(options, args);

        } catch (ParseException e) {
            System.err.println(e.getMessage());
            helper.printHelp("Tagarela", options);
            System.exit(1);
        }
    }

    public boolean needHelp() {
        return cmd.hasOption("help");
    }

    public String getUri() {
        return cmd.getOptionValue("uri");
    }

    public Path getOutputPath() {

        String fileName = "transcription-" + LocalDateTime.now().toString() + ".txt";
        String filePath = cmd.getOptionValue("out");

        if (filePath != null) {
            return Paths.get(filePath + fileName);
        }

        return Paths.get(Paths.get("").toAbsolutePath().toString() + "/" + fileName);
    }

    public void showHelp() {
        helper.printHelp("Tagarela", options);
        System.exit(0);
    }
}