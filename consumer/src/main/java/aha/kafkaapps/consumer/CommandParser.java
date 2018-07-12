package aha.kafkaapps.consumer;

import aha.kafkaapps.consumer.command.Consume;
import aha.kafkaapps.consumer.command.ConsumeOndemand;
import aha.kafkaapps.consumer.command.Help;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Function;
import java.util.function.Supplier;


class CommandParser implements Supplier<Function<Void, Void>> {

    private final ConsumerArguments args;

    public CommandParser(final ConsumerArguments args) {
        this.args = args;
    }

    @Override
    public Function<Void, Void> get() {

        if (!validate(args)) {
            return new Help(args.getOptions());
        }

        if(args.isHelp()){
            return new Help(args.getOptions());
        }

        if(args.isOndemandConsume()){
            return new ConsumeOndemand(args);
        }

        return new Consume(args);
    }


    private boolean validate(final ConsumerArguments args){

        if(StringUtils.isBlank(args.getbootstrapServer())){
            return false;
        }

        return true;
    }

}
