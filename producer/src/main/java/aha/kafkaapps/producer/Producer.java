package aha.kafkaapps.producer;

import org.apache.commons.cli.ParseException;

public class Producer {

    public static void main(final String[] args) throws ParseException {
        new CommandParser(new ProducerArguments(args)).get().apply(null);
    }

}
