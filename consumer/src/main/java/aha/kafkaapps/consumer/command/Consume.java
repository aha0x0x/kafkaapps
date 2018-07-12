package aha.kafkaapps.consumer.command;

import aha.kafkaapps.consumer.ConsumerArguments;
import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;
import java.util.function.Function;

public class Consume implements Function<Void,Void> {

    final ConsumerArguments args;

    public Consume(final ConsumerArguments args){
        this.args = args;
    }


    @Override public Void apply(Void aVoid) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, args.getbootstrapServer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, args.getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, args.enableAutoCommit());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Lists.newArrayList(args.getTopic()));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("[new message]:" + record);
                }
            }
        }finally {
            consumer.close();
        }
    }
}
