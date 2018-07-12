package aha.kafkaapps.producer.command;

import aha.kafkaapps.producer.Producer;
import aha.kafkaapps.producer.ProducerArguments;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.function.Function;

public class Help implements Function<Void,Void> {

    final ProducerArguments args;

    public Help(final ProducerArguments args){
        this.args = args;
    }

    @Override public Void apply(Void aVoid) {

        Options options = new Options();
        for(Option opt : args.getCmdLine().getOptions()){
            options.addOption(opt);
        }

        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp(Producer.class.getCanonicalName(), options);

        return null;
    }
}
