package com.udemy.springbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@EnableBatchProcessing
@Configuration
public class ParImparBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private static final Integer CHUNK_SIZE = 1;

    @Bean
    public Job imprimeParOuImparJob() {
        return jobBuilderFactory
                .get("imprimeParOuImparJob")
                .start(imprimeParImparStep())
                .build();
    }

    public Step imprimeParImparStep() {

        return stepBuilderFactory
                .get("imprimeParImparStep")
                .<Integer,String >chunk(CHUNK_SIZE)
                .reader(contaAteDezReader())
                .processor(parOuImparProcessor())
                .writer(imprimeWriter())
                .build();
    }

    private ItemWriter< String> imprimeWriter() {
        return itens -> itens.forEach(System.out::println);
    }

    private FunctionItemProcessor<Integer, String> parOuImparProcessor() {
        return new FunctionItemProcessor<>(item ->
                item % 2 == 0 ?
                        String.format("Item %s é Par", item) :
                        String.format("Item %s é Impar", item));
    }

    private IteratorItemReader<Integer> contaAteDezReader() {
         List<Integer> numerosDeUmAteDez = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
         return new IteratorItemReader<>(numerosDeUmAteDez);
    }
}
