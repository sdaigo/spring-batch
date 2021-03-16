package com.example.batch.helloworld;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class HelloWorldApplication {
    @Autowired
	private JobBuilderFactory jobBuilderFactory;

    @Autowired
	private StepBuilderFactory stepBuilderFactory;

    @Bean
	public Step step() {
    	return this.stepBuilderFactory.get("Step1")
					.tasklet((contribution, chunkContext) -> {
						System.out.println("Hello world!");
						return RepeatStatus.FINISHED;
					})
					.build();
	}

	@Bean
	public Job job() {
    	return this.jobBuilderFactory.get("job")
				.start(step())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldApplication.class, args);
	}

}
