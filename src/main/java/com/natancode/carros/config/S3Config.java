package com.natancode.carros.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

 @Configuration
public class S3Config {
	
	@Value("${aws.access_key_id}")
	private String awsId; //peguei do arquivo credentials que a amazon gerou para mim e coloquei no propeties
	
	@Value("${aws.secret_access_key}")
	private String awsKey; //peguei do arquivo credentials que a amazon gerou para mim e coloquei no propeties
	
	@Value("${s3.region}")
	private String region; //está no propeties
	
 	@Bean
	public AmazonS3 s3client() { //acesso ao S3 da Amazon
		BasicAWSCredentials awsCred = new BasicAWSCredentials(awsId, awsKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region))
							.withCredentials(new AWSStaticCredentialsProvider(awsCred)).build();
		return s3client;
	}
}