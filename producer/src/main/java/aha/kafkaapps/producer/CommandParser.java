package aha.kafkaapps.producer;


import java.util.function.Function;
import java.util.function.Supplier;

import aha.kafkaapps.producer.command.Help;
import aha.kafkaapps.producer.command.Produce;
import aha.kafkaapps.producer.command.ProduceOndemand;
import org.apache.commons.lang3.StringUtils;


class CommandParser implements Supplier<Function<Void, Void>> {

    private final ProducerArguments args;

    public CommandParser(final ProducerArguments args) {
        this.args = args;
    }

    @Override
    public Function<Void, Void> get() {

        if (!validate(args)) {
            return new Help(args);
        }

        if(args.isHelp()){
            return new Help(args);
        }

        if(args.isOndemandProduce()){
            return new ProduceOndemand(args);
        }

        return new Produce(args);
    }


    private boolean validate(final ProducerArguments args){

        if(StringUtils.isBlank(args.getbootstrapServer())){
            return false;
        }
        return true;
    }
}
