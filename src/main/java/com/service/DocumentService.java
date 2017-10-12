package com.service;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dao.impl.CommonDaoImpl;
import com.entity.Department;
import com.entity.Document;
/**
 * 关于文档的逻辑处理
 * @author kone
 * 2017.4.19
 */
@Service
public class DocumentService {
	@Autowired
	private SessionFactory sessionFactory;
	private Session session;
	@Autowired
	private CommonDaoImpl commonDaoImpl;
	
	public  Logger logger = Logger.getLogger(DocumentService.class);
	/**
	 * 上传相关文档
	 * @param file
	 * @param departmentId
	 * @param path
	 * @return
	 */
	@Transactional
	public boolean uploadDocument(MultipartFile file, long departmentId, String path){
		try{
			Document document  = new Document();
			Department department = new Department();
			department.setId(departmentId);
			document.setDepartment(department);
			if(!file.isEmpty()) {
				String origName = file.getOriginalFilename();
				int newNameIndex = origName.lastIndexOf('.');
				String suffix = origName.substring(newNameIndex);
				long name = System.currentTimeMillis();
//				文件随机名称
				String fileName = String.valueOf(name)+suffix;
				File file2 = new File(path,fileName);
				
				document.setRandName(fileName);
				document.setDocumentName(origName);
				
				file.transferTo(file2);
			}
			
			session = sessionFactory.getCurrentSession();
			commonDaoImpl.setSession(session);
			commonDaoImpl.save(document);
			return true;
		} catch(Exception e) {
			throw new ServiceException("error");
		}
	}
	/**
	 * 查看文档
	 * @param departmentId
	 * @return
	 */
	public List<Document> viewDocument(long departmentId){
		logger.info("view document");
		List<Document> documents = null;
		try{
			session = sessionFactory.getCurrentSession();
			commonDaoImpl.setSession(session);
			documents = commonDaoImpl.findBy("Document", "departmentId", String.valueOf(departmentId));
			return documents;
		} catch(Exception e) {
			return documents;
		} 
	}
	/**
	 * 删除模板文档
	 * @param document
	 * @return
	 */
	@Transactional
	public boolean deleteDocument(Document document) {
		try{
			session = sessionFactory.getCurrentSession();
			commonDaoImpl.setSession(session);
			commonDaoImpl.delete(document);
			return true;
		} catch(Exception e) {
			throw new ServiceException("error");
		}
	}
	
}
