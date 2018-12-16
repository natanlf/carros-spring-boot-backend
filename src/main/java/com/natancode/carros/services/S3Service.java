package com.natancode.carros.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {
 	
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);
 	
 	@Autowired
	private AmazonS3 s3cliente;
 	
 	@Value("${s3.bucket}")
	private String bucketName;
 	
 	// MultipartFile é o tipo de arquivo que envio na requisição
 	// retorna URI, assim temos a url do novo arquivo que fizemos upload
 	public URI uploadFile(MultipartFile multipartFile) {
 		try {
 			// getOriginalFilename pega o nome do arquivo que foi enviado
 			String fileName = multipartFile.getOriginalFilename();
 			// encapsula leitura a partir de uma origem, nossa origem vai ser o arquivo
 			InputStream is = multipartFile.getInputStream();		
 			// Informação do tipo do arquivo que foi enviado, pois pode ser uma imgem, texto...
 			String contentType = multipartFile.getContentType();
 			return uploadFile(is, fileName, contentType);
 		} catch (IOException e) {
 			throw new RuntimeException("Erro de IO: "+e.getMessage());
 		}
 	}

 	public URI uploadFile(InputStream is, String fileName, String contentType) {
 		try {
 			ObjectMetadata meta = new ObjectMetadata();
 			meta.setContentType(contentType);
 			LOG.info("Iniciando upload");
 			s3cliente.putObject(bucketName, fileName, is, meta);
 			LOG.info("Upload finalizado");
 			return s3cliente.getUrl(bucketName, fileName).toURI();
 		} catch (URISyntaxException e) {
 			throw new RuntimeException("Erro ao converter URL para URI");
 		}
 	}

}