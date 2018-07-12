package aha.kafkaapps.producer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ProducerArguments {

    private final CommandLine cmdLine;

    private final String OPTION_HELP = "help";
    private final String OPTION_ONDEMAND = "ondemand";

    private final String OPTION_BOOTSTRAP = "bootstrap";

    private final String OPTION_TOPIC = "topic";
    private final String OPTION_MESSAGE = "message";

    public ProducerArguments(final String[] args) throws ParseException {

        final Options options = new Options();
        options.addOption(OPTION_HELP, false,"help");
        options.addOption(OPTION_ONDEMAND, true,
                "generate messages on demand. Producer will ask for topic and message to send. Command line options will be ignored");

        options.addOption(OPTION_BOOTSTRAP, true,"bootstrap server");
        options.addOption(OPTION_TOPIC,true,"message topic");
        options.addOption(OPTION_MESSAGE,true,"message to send");

        CommandLineParser clp = new DefaultParser();
        cmdLine = clp.parse(options,args);
    }

    public CommandLine getCmdLine(){
        return cmdLine;
    }

    public String getTopic(){
        return cmdLine.getOptionValue(OPTION_TOPIC);
    }

    public String getMessage(){
        return cmdLine.getOptionValue(OPTION_MESSAGE);
    }

    public String getbootstrapServer(){
        return cmdLine.getOptionValue(OPTION_BOOTSTRAP);
    }

    public boolean isHelp(){
        return cmdLine.hasOption(OPTION_HELP);
    }

    public boolean isOndemandProduce(){
        return Boolean.valueOf(cmdLine.getOptionValue(OPTION_ONDEMAND));
    }
}
