package com.example.protobuf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jun.models.Person;
import json.Jperson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ProtobufApplication extends  {

	public static void main(String[] args) throws IOException {

//		Person sam = Person.newBuilder()
//				.setName("sam")
//				.setAge(10)
//				.build();
//		System.out.println(sam.toString());
//
//		Path path = Paths.get("sam.ser");
//		Files.write(path,sam.toByteArray());
//
//		byte[] bytes = Files.readAllBytes(path);
//		Person newSam = Person.parseFrom(bytes);
//
//		System.out.println(
//				newSam
//		);

		Jperson person = new Jperson();
		person.setName("sam");
		person.setAge(10);
		ObjectMapper mapper = new ObjectMapper();

		Runnable json = () -> {
			try {
				byte[] bytes = mapper.writeValueAsBytes(person);
				Jperson person1 = mapper.readValue(bytes, Jperson.class);
			} catch (Exception e) {
				e.printStackTrace();

			}
		};

		Person sam = Person.newBuilder()
				.setName("sam")
				.setAge(10)
				.build();
		Runnable proto = () -> {
			try {
				byte[] bytes= sam.toByteArray();
				Person sam1 = Person.parseFrom(bytes);
			}
			catch (InvalidProtocolBufferException e){
				e.printStackTrace();
			}
		};

		runPerformanceTest(json,"JSON");
		runPerformanceTest(proto,"Proto");
	}

	private static void runPerformanceTest(Runnable runnable,String method){
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			runnable.run();
		}

		long time2 = System.currentTimeMillis();

		System.out.println(method + ":"  + (time2-time1) + "ms");
	}

}
