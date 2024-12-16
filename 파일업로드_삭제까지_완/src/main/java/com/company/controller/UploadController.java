package com.company.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import java.util.ArrayList;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.company.dto.AttachFileDTO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j

public class UploadController {

	String uploadFolder = "C:\\upload";

	@GetMapping("/uploadForm")
	public void uploadForm() {

		log.info("upload form");
	}

	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {

		for (MultipartFile multipartFile : uploadFile) {
			log.info("----------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());

			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());

			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}

		}

	}

	@GetMapping("/uploadAjax")
	public void uploadAjax() {

		log.info("upload ajax");
	}

	// 제이쿼리 안맞아서 콘솔에 404 에러
	@PostMapping(value = "/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload";

		String uploadFolderPath = getFolder();
		/*
		 * } public void uploadAjaxPost(MultipartFile[] uploadFile) {
		 */
		// make folder ------------
		File uploadPath = new File(uploadFolder, getFolder());
		log.info("upload path: " + uploadPath);

		// 존재하지 않으면 만들기
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		// make yyyy/MM/dd folder

		// 로그에 Ajax 업로드 요청 기록
		log.info("update ajax post.................");

		// 업로드된 파일 배열을 반복 처리
		for (MultipartFile multipartFile : uploadFile) {

			AttachFileDTO attachDTO = new AttachFileDTO();

			log.info("--------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());

			log.info("Upload File Sizs: " + multipartFile.getOriginalFilename());

			String uploadFileName = multipartFile.getOriginalFilename();

			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);

			// 파일 이름만 출력
			log.info("only file name: " + uploadFileName);
			attachDTO.setFileName(uploadFileName);

			// 중복 방지를 위한 UUID (같은 파일을 불러와도 중복x)
			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			// 썸네일 생성
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);

				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				// check image type file
				if (checkImageType(saveFile)) {

					attachDTO.setImage(true);

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				}

					// add to List
					list.add(attachDTO);
					
				}catch (Exception e) {
				e.printStackTrace();
			} // end catch

			// 저장할 파일 경로와 이름 설정
			// File saveFile = new File(uploadFolder, uploadFileName);

			// 위의 경로를 주석 처리 하고 업로드 패스폴더로
			File saveFile = new File(uploadPath, uploadFileName);
			try {
				// 업로드된 파일을 지정된 경로에 저장
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());

			} // end catch
		} // end for
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {

		log.info("fileName: " + fileName);

		File file = new File("c:\\upload\\" + fileName);

		log.info("file: " + file);

		ResponseEntity<byte[]> result = null;

		try {

			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));

			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	// 파일 다운로드
	// 다운로드 경로 : /download?fileName=test4.png (c드라이브 확인)
	@GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName) {

		log.info("download file: " + fileName);

		Resource resource = new FileSystemResource("c:\\upload\\" + fileName);

		log.info("resource: " + resource);

		String resoureName = resource.getFilename();

		HttpHeaders headers = new HttpHeaders();
		try {
			headers.add("Content-Disposition",
					"attachment; filename=" + new String(resoureName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

	// 년/월.일 폴더 생성
	private String getFolder() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		String str = sdf.format(date);

		return str.replace("-", File.separator);
	}

	// 이미지 파일 판단
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String>deleteFile(String fileName, String type){
		
		log.info("deleteFile: " + fileName);
		
		File file;
		
		try {
			file = new File("c:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
			
			file.delete();
			
			if (type.equals("image")) {
				
				String largeFileName = file.getAbsolutePath().replace("s_","");
				
				log.info("largeFileName: " + largeFileName);
				
				file = new File(largeFileName);
				
				file.delete();				
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	

}
