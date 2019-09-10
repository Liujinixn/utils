package com.jingdian.utils;

import com.alibaba.druid.util.StringUtils;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class OSSClientUtil {

	protected static final Logger log = LoggerFactory
			.getLogger(OSSClientUtil.class);

	@Value("${OOS_ENDPOINT}")
	private String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	@Value("${OOS_ACCESSKEYID}")
	private String accessKeyId = "MBxHXAPW2QXj1QqM";
	@Value("${OOS_ACCESSKEYSECRET}")
	private String accessKeySecret = "h9JoqKRO9duHK8M6p88jKQ3VJhtMem";
	@Value("${OOS_BUCKETNAME}")
	private String bucketName = "zainanjingshoptest";

	// �ļ��洢Ŀ¼
	private String filedir = "APP/";

	/**
	 * 
	 * �ϴ�ͼƬ
	 * 
	 * @param file
	 * @return
	 */
	public String uploadImg2Oss(MultipartFile file) {
		if (file.getSize() > 1024 * 1024 * 20) {
			return "ͼƬ̫��";// RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_TOO_MAX);
		}
		String originalFilename = file.getOriginalFilename();
		String substring = originalFilename.substring(
				originalFilename.lastIndexOf(".")).toLowerCase();
		Random random = new Random();
		String name = random.nextInt(10000) + System.currentTimeMillis()
				+ substring;
		try {
			InputStream inputStream = file.getInputStream();
			this.uploadFile2OSS(inputStream, name);
			return name;// RestResultGenerator.createSuccessResult(name);
		} catch (Exception e) {
			return "�ϴ�ʧ��";// RestResultGenerator.createErrorResult(ResponseEnum.PHOTO_UPLOAD);
		}
	}

	/**
	 * �ϴ�ͼƬ��ȡfileUrl
	 * 
	 * @param instream
	 * @param fileName
	 * @return
	 */
	private String uploadFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			// �����ϴ�Object��Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileName
					.substring(fileName.lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// �ϴ��ļ�

			OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
			System.out.println("���ӳɹ�");
			PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	public static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase(".bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase(".gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase(".jpeg")
				|| FilenameExtension.equalsIgnoreCase(".jpg")
				|| FilenameExtension.equalsIgnoreCase(".png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase(".html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase(".txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase(".vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase(".pptx")
				|| FilenameExtension.equalsIgnoreCase(".ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase(".docx")
				|| FilenameExtension.equalsIgnoreCase(".doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase(".xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

	/**
	 * ��ȡͼƬ·��
	 * 
	 * @param fileUrl
	 * @return
	 */
	public String getImgUrl(String fileUrl) {
		if (!StringUtils.isEmpty(fileUrl)) {
			String[] split = fileUrl.split("/");
			String url = this.getUrl(this.filedir + split[split.length - 1]);
			// log.info(url);
			// String[] spilt1 = url.split("\\?");
			// return spilt1[0];
			return url;
		}
		return null;
	}

	/**
	 * ���url����
	 * 
	 * @param key
	 * @return
	 */
	public String getUrl(String key) {
		// ����URL����ʱ��Ϊ10�� 3600l* 1000*24*365*10
		Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24
				* 365 * 10);
		// ����URL
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}

	/**
	 * ��ͼƬ�ϴ�
	 * 
	 * @param fileList
	 * @return
	 */
	public String checkList(List<MultipartFile> fileList) {
		String fileUrl = "";
		String str = "";
		String photoUrl = "";
		for (int i = 0; i < fileList.size(); i++) {
			fileUrl = uploadImg2Oss(fileList.get(i));
			str = getImgUrl(fileUrl);
			if (i == 0) {
				photoUrl = str;
			} else {
				photoUrl += "," + str;
			}
		}
		return photoUrl.trim();
	}

	/**
	 * ����ͼƬ�ϴ�
	 * 
	 * @param file
	 * @return
	 */
	public String checkImage(MultipartFile file) {
		String fileUrl = uploadImg2Oss(file);
		String str = getImgUrl(fileUrl);
		return str.trim();
	}
}
