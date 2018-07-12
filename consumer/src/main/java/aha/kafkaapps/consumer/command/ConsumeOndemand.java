package aha.kafkaapps.consumer.command;

import aha.kafkaapps.consumer.ConsumerArguments;
import com.google.common.collect.Lists;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.Console;
import java.util.Properties;
import java.util.function.Function;

public class ConsumeOndemand implements Function<Void,Void> {

    private final ConsumerArguments args;

    public ConsumeOndemand(final ConsumerArguments args){
        this.args = args;
    }


    @Override public Void apply(Void aVoid) {

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, args.getbootstrapServer());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, args.getGroupId());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        Console console = System.console();
        try {
            while (true) {
                String topic = console.readLine("Topic: ");
                int partition = Integer.valueOf(console.readLine("Partition: "));
                Long offset = Long.valueOf(console.readLine("Offset: "));

                consumer.assign(Lists.newArrayList(new TopicPartition(topic, partition)));

                // does not work properly
                //consumer.subscribe(Lists.newArrayList(topic));
                //consumer.poll(100);
                //System.out.println("Polled");
                consumer.seek(new TopicPartition(topic, partition), offset);
                ConsumerRecords<String, String> records = consumer.poll(100);

                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("received:" + record);
                }
            }
        } finally {
            consumer.close();
        }
    }

}
