package aha.kafkaapps.producer.command;

import aha.kafkaapps.producer.ProducerArguments;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.function.Function;

public class Produce implements Function<Void,Void> {

    private final ProducerArguments args;

    public Produce(final ProducerArguments args) {
        this.args = args;
    }

    @Override
    public Void apply(Void aVoid) {

        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, Produce.class.getCanonicalName());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, args.getbootstrapServer());
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        final String topic = args.getTopic();
        final String msg = args.getMessage();
        try {
            final ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
            producer.send(record, (metadata, exception) -> {
                if (metadata != null) {
                    System.out.println("sent " + record + " received: " + metadata);
                } else {
                    System.out.println("sent " + record + " exception: " + exception.getMessage());
                }
            });
        } finally {
            producer.flush();
            producer.close();
        }
        return null;
    }
}

