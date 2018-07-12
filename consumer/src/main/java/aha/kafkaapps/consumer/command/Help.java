package aha.kafkaapps.consumer.command;

import aha.kafkaapps.consumer.Consumer;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.util.function.Function;

public class Help implements Function<Void,Void> {

    final Options options;

    public Help(final Options options){
        this.options = options;
    }

    @Override public Void apply(Void aVoid) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp(Consumer.class.getCanonicalName(), options);

        return null;
    }
}
