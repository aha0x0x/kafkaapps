package aha.kafkaapps.consumer;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ConsumerArguments
{
    private final CommandLine cmdLine;
    private final Options options;

    private final String OPTION_HELP = "help";
    private final String OPTION_ONDEMAND = "ondemand";

    private final String OPTION_BOOTSTRAP = "bootstrap";

    private final String OPTION_TOPIC = "topic";
    private final String OPTION_GROUPID = "groupId";


    private final String OPTION_AUTOCOMMIT = "autocommit";

    public ConsumerArguments(String[] args) throws ParseException {

        options = new Options();
        options.addOption(OPTION_HELP, false,"help");
        options.addOption(OPTION_ONDEMAND, true,
                "consume on demand. Consumer will ask for topic, groupId, partition, and offset before retrieving messages. Command line options will be ignored");

        options.addOption(OPTION_BOOTSTRAP, true,"bootstrap server");
        options.addOption(OPTION_TOPIC,true,"topic");
        options.addOption(OPTION_GROUPID,true,"GroupId");
        options.addOption(OPTION_AUTOCOMMIT,true, "should Consumer autocommit retrieved messages (Default:true)");

        CommandLineParser clp = new DefaultParser();
        cmdLine = clp.parse(options,args);
    }

    public Options getOptions(){
        return options;
    }

    public CommandLine getCmdLine(){
        return cmdLine;
    }


    public String getTopic(){
        return cmdLine.getOptionValue(OPTION_TOPIC);
    }

    public String getbootstrapServer(){
        return cmdLine.getOptionValue(OPTION_BOOTSTRAP);
    }


    public String getGroupId() {
        return cmdLine.getOptionValue(OPTION_GROUPID);
    }

    public boolean isHelp(){
        return cmdLine.hasOption(OPTION_HELP);
    }

    public boolean enableAutoCommit() {
        return Boolean.valueOf(cmdLine.getOptionValue(OPTION_AUTOCOMMIT));
    }

    public boolean isOndemandConsume(){
        return Boolean.valueOf(cmdLine.getOptionValue(OPTION_ONDEMAND));
    }


}


