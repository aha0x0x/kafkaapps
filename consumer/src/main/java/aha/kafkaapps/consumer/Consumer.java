package aha.kafkaapps.consumer;

import org.apache.commons.cli.ParseException;

public class Consumer {

     public static void main(String[] args) throws ParseException {
         new CommandParser(new ConsumerArguments(args)).get().apply(null);
     }
}
