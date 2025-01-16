package com.bank.demo;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

@SpringBootApplication
@ComponentScan(value= {"com.bank"})
@EnableMongoAuditing
public class BankDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDemoApplication.class, args);
	}

	@Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(List.of(
            new OffsetDateTimeReadConverter(),
            new OffsetDateTimeWriteConverter()
        ));
    }

	 static class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, String> {
        @Override
        public String convert(OffsetDateTime source) {
            return source.toInstant().atZone(ZoneOffset.UTC).toString();
        }
    }

    static class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(String source) {
            return OffsetDateTime.parse(source);
        }
    }

}
