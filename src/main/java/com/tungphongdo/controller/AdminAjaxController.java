package com.tungphongdo.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tungphongdo.entity.AboutEntity;
import com.tungphongdo.entity.BillEntity;
import com.tungphongdo.entity.CategoryEntity;
import com.tungphongdo.entity.ColorEntity;
import com.tungphongdo.entity.ContactEntity;
import com.tungphongdo.entity.ProductDetailBillEntity;
import com.tungphongdo.entity.ProductDetailEntity;
import com.tungphongdo.entity.ProductEntity;
import com.tungphongdo.entity.RoleEntity;
import com.tungphongdo.entity.SaleOffDetailEntity;
import com.tungphongdo.entity.SizeEntity;
import com.tungphongdo.entity.UserEntity;
import com.tungphongdo.entity.UserRoleEntity;
import com.tungphongdo.repository.AboutRepository;
import com.tungphongdo.repository.BillRepository;
import com.tungphongdo.repository.CategoryRepository;
import com.tungphongdo.repository.ColorRepository;
import com.tungphongdo.repository.ContactRepository;
import com.tungphongdo.repository.ProductDetailBillRepository;
import com.tungphongdo.repository.ProductDetailRepository;
import com.tungphongdo.repository.ProductRepository;
import com.tungphongdo.repository.RoleRepository;
import com.tungphongdo.repository.SaleOffDetailRepository;
import com.tungphongdo.repository.SizeRepository;
import com.tungphongdo.repository.UserRoleRepository;
import com.tungphongdo.repository.UsersRepository;

@Controller
@RequestMapping("/adminAjax")
public class AdminAjaxController {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	@Autowired
	private SaleOffDetailRepository saleOffDetailRepository;
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private ProductDetailBillRepository productDetailBillRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Autowired
	private AboutRepository aboutRepository;
	
	
	//handle lock account
	@GetMapping("/lockAcc")
	@ResponseBody
	public void lockAccount(HttpServletRequest request) {
		UserEntity userEntity  = new UserEntity();
		userEntity.setId(Integer.parseInt(request.getParameter("id_user").trim()));
		userEntity.setUsername(request.getParameter("name_user").trim());
		userEntity.setPassword(request.getParameter("pass_user").trim());
		userEntity.setAddress(request.getParameter("address_user").trim());
		userEntity.setEmail(request.getParameter("email_user").trim());
		userEntity.setTelephone(Long.parseLong(request.getParameter("phone_user").trim()));
		userEntity.setGender(request.getParameter("gender_user").trim());
		userEntity.setEnabled(Integer.parseInt(request.getParameter("enabled_user").trim()));
		
		usersRepository.save(userEntity);
	}
	
	//handle open account
	@GetMapping("/openAcc")
	@ResponseBody
	public void openAccount(HttpServletRequest request) {
		UserEntity userEntity  = new UserEntity();
		userEntity.setId(Integer.parseInt(request.getParameter("id_user").trim()));
		userEntity.setUsername(request.getParameter("name_user").trim());
		userEntity.setPassword(request.getParameter("pass_user").trim());
		userEntity.setAddress(request.getParameter("address_user").trim());
		userEntity.setEmail(request.getParameter("email_user").trim());
		userEntity.setTelephone(Long.parseLong(request.getParameter("phone_user").trim()));
		userEntity.setGender(request.getParameter("gender_user").trim());
		userEntity.setEnabled(Integer.parseInt(request.getParameter("enabled_user").trim()));
			
		usersRepository.save(userEntity);
	}
	
	//handle delete account
	@GetMapping("/deleteAcc")
	@ResponseBody
	public void deleteAccount(HttpServletRequest request) {
		UserEntity userEntity = usersRepository.findById(Integer.parseInt(request.getParameter("userId").trim())).get();
		System.out.println(userEntity.getId());
		
		RoleEntity roleEntity = roleRepository.findById(2).get();
		System.out.println(roleEntity.getRoleName());
		
		Set<UserRoleEntity> userRoleEntities = userEntity.getUserRoleEntities();
		for (UserRoleEntity userRoleEntity : userRoleEntities) {
			userRoleRepository.delete(userRoleEntity);
		}
		
		usersRepository.delete(userEntity);
	}
	
	//handle update category
	@PostMapping("/updateCategory")
	@ResponseBody
	public void updateCategory(HttpServletRequest request) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setId(Integer.parseInt(request.getParameter("categoryId").trim()));
		categoryEntity.setCategoryName(request.getParameter("categoryName").trim());
		
		int id = Integer.parseInt(request.getParameter("categoryId").trim());
		CategoryEntity categoryEntity2 = categoryRepository.findById(id).get();
		
		categoryEntity.setProductEntities(categoryEntity2.getProductEntities());
		
		categoryRepository.save(categoryEntity);
		
	}
	
	//handle save new category
	@GetMapping("/saveCategory")
	@ResponseBody
	public void saveCategory(HttpServletRequest request) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setCategoryName(request.getParameter("categoryName").trim());
		
		categoryRepository.save(categoryEntity);
	}
	
	//handle delete category
	@GetMapping("/deleteCategory")
	@ResponseBody
	public void deleteCategory(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("categoryId").trim());
		
		List<ProductEntity> productEntities = productRepository.findByCategoryId(id);
		for (ProductEntity productEntity : productEntities) {
			productRepository.deleteById(productEntity.getId());
		}
		
		categoryRepository.deleteById(id);
	}
	
	@SuppressWarnings("unused")
	@Autowired
	private ServletContext context;
	
	//handle upload image
	@PostMapping("/uploadImage")
	@ResponseBody
	public String uploadImage(MultipartHttpServletRequest request) throws IOException {
		
		//tạo đường dẫn để lưu file
//		String pathSaveFile = context.getRealPath("/");
//		System.out.println(pathSaveFile);
		
		File pathSaveFile = new File(System.getProperty("user.dir") + "/src/main/resources/static/images/product/");
		
		Iterator<String> listNames = request.getFileNames();  // list này chỉ chứ tên file (FileNames) chứ k chứa file
		// để lấy được file ta dùng MultipartFile
		MultipartFile multipartFile = request.getFile(listNames.next()); // đây mới là lấy ra file
		
		/*Trường hợp trên là chỉ có 1 file, còn trường hợp có nhiều file thì phải duyệt vòng lặp foreach*/
		
		//tiếp theo chúng ta cần lưu file vừa lấy đc vào folder images
		
		String fileName = multipartFile.getOriginalFilename(); //getOriginalFilename() lấy ra cả đuôi file
		File file = new File(pathSaveFile, fileName); 
		System.out.println(file);
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	
	//handle inserting products
	@GetMapping("/insertProduct")
	@ResponseBody
	public void insertProduct(HttpServletRequest request) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductName(request.getParameter("productName").trim());
		productEntity.setPrice(Double.parseDouble(request.getParameter("productPrice").trim()));
		productEntity.setDescription(request.getParameter("productDesc").trim());
		productEntity.setImage(request.getParameter("productImageName").trim());
	
		int categoryId = Integer.parseInt(request.getParameter("productCategoryId").trim());
		 
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();
		
		productEntity.setCategoryEntity(categoryEntity);
		
		productRepository.save(productEntity);
	}
	
	//handle updating products
	@GetMapping("/updateProduct")
	@ResponseBody
	public void updateProduct(HttpServletRequest request) {
		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(Integer.parseInt(request.getParameter("productId").trim()));
		productEntity.setProductName(request.getParameter("productName").trim());
		productEntity.setPrice(Double.parseDouble(request.getParameter("productPrice").trim()));
		productEntity.setDescription(request.getParameter("productDesc").trim());
		productEntity.setImage(request.getParameter("productImageName").trim());
	
		int categoryId = Integer.parseInt(request.getParameter("productCategoryId").trim());
		 
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();
		
		productEntity.setCategoryEntity(categoryEntity);
		
		productRepository.save(productEntity);
	}
	
	//handle deleting products
	@GetMapping("/deleteProduct")
	@ResponseBody
	public void deleteProduct(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("productId").trim());
		ProductEntity productEntity = productRepository.findById(id).get();
		
		Set<ProductDetailEntity> productDetailEntities = productEntity.getProductDetailEntities();
		for (ProductDetailEntity productDetailEntity : productDetailEntities) {
			productDetailRepository.delete(productDetailEntity);
		}
		
		Set<SaleOffDetailEntity> saleOffDetailEntities = productEntity.getSaleOffDetailEntities();
		for (SaleOffDetailEntity saleOffDetailEntity : saleOffDetailEntities) {
			saleOffDetailRepository.delete(saleOffDetailEntity);
		}
		
		productRepository.delete(productEntity);
		
	}
	
	//handle inserting new size
	@GetMapping("/saveSize")
	@ResponseBody
	public void saveSize(HttpServletRequest request) {
		SizeEntity sizeEntity = new SizeEntity();
		sizeEntity.setSizeName(request.getParameter("sizeName").trim());
		
		sizeRepository.save(sizeEntity);
	}
	
	//handle updating size
	@GetMapping("/updateSize")
	@ResponseBody
	public void updateSize(HttpServletRequest request) {
		SizeEntity sizeEntity = new SizeEntity();
		sizeEntity.setId(Integer.parseInt(request.getParameter("sizeId").trim()));
		sizeEntity.setSizeName(request.getParameter("sizeName").trim());
			
		sizeRepository.save(sizeEntity);
	}
	
	//handle deleting size
	@GetMapping("/deleteSize")
	@ResponseBody
	public void deleteSize(HttpServletRequest request) {
		
		int id = Integer.parseInt(request.getParameter("sizeId").trim());
		SizeEntity sizeEntity = sizeRepository.findById(id).get();
	
		Set<ProductDetailEntity> productDetailEntities = sizeEntity.getProductDetailEntities();
		for (ProductDetailEntity productDetailEntity : productDetailEntities) {
			productDetailRepository.deleteById(productDetailEntity.getId());;
		}
		
		sizeRepository.deleteById(id);;
	}
	
	
	//handle inserting new color
		@GetMapping("/saveColor")
		@ResponseBody
		public void saveColor(HttpServletRequest request) {
			ColorEntity colorEntity = new ColorEntity();
			colorEntity.setColorName(request.getParameter("colorName").trim());
			
			colorRepository.save(colorEntity);
		}
		
		//handle updating color
		@GetMapping("/updateColor")
		@ResponseBody
		public void updateColor(HttpServletRequest request) {
			ColorEntity colorEntity = new ColorEntity();
			colorEntity.setId(Integer.parseInt(request.getParameter("colorId").trim()));
			colorEntity.setColorName(request.getParameter("colorName").trim());
				
			colorRepository.save(colorEntity);
		}
		
		//handle deleting size
		@GetMapping("/deleteColor")
		@ResponseBody
		public void deleteColor(HttpServletRequest request) {
			
			int id = Integer.parseInt(request.getParameter("colorId").trim());
			ColorEntity colorEntity = colorRepository.findById(id).get();
		
			Set<ProductDetailEntity> productDetailEntities = colorEntity.getProductDetailEntities();
			for (ProductDetailEntity productDetailEntity : productDetailEntities) {
				productDetailRepository.deleteById(productDetailEntity.getId());;
			}
			
			colorRepository.deleteById(id);;
		}
		
		//handle inserting productDetail
		@GetMapping("/insertProductDetail")
		@ResponseBody
		public void insertProductDetail(HttpServletRequest request) {
			ProductDetailEntity productDetailEntity = new ProductDetailEntity();
			
			int productId = Integer.parseInt(request.getParameter("productId").trim());
			int colorId = Integer.parseInt(request.getParameter("colorId").trim());
			int sizeId = Integer.parseInt(request.getParameter("sizeId").trim());
			
			ProductEntity productEntity = productRepository.findById(productId).get();
			ColorEntity colorEntity = colorRepository.findById(colorId).get();
			SizeEntity sizeEntity = sizeRepository.findById(sizeId).get();
			
			productDetailEntity.setProductEntity(productEntity);
			productDetailEntity.setColorEntity(colorEntity);
			productDetailEntity.setSizeEntity(sizeEntity);
			productDetailEntity.setDate(request.getParameter("date").trim());
			productDetailEntity.setProductAmount(Integer.parseInt(request.getParameter("amount").trim()));
			
			productDetailRepository.save(productDetailEntity);
		}
		
		//handle updating productDetail
		@GetMapping("/updateProductDetail")
		@ResponseBody
		public void updateProductDetail(HttpServletRequest request) {
			ProductDetailEntity productDetailEntity = new ProductDetailEntity();
			
			int productDetailId = Integer.parseInt(request.getParameter("productDetailId").trim());
			int productId = Integer.parseInt(request.getParameter("productId").trim());
			int colorId = Integer.parseInt(request.getParameter("colorId").trim());
			int sizeId = Integer.parseInt(request.getParameter("sizeId").trim());
			
			ProductEntity productEntity = productRepository.findById(productId).get();
			ColorEntity colorEntity = colorRepository.findById(colorId).get();
			SizeEntity sizeEntity = sizeRepository.findById(sizeId).get();
			
			productDetailEntity.setId(productDetailId);
			productDetailEntity.setProductEntity(productEntity);
			productDetailEntity.setColorEntity(colorEntity);
			productDetailEntity.setSizeEntity(sizeEntity);
			productDetailEntity.setDate(request.getParameter("date").trim());
			productDetailEntity.setProductAmount(Integer.parseInt(request.getParameter("amount").trim()));
			
			productDetailRepository.save(productDetailEntity);
		}
		
		//handle deleting productDetail
		@GetMapping("/deleteProductDetail")
		@ResponseBody
		public void deleteProductDetail(HttpServletRequest request) {
			int productDetailBillId = Integer.parseInt(request.getParameter("productDetailId").trim());
			
			List<ProductDetailBillEntity> productDetailBillEntities = 
					productDetailBillRepository.findByProductDetailId(productDetailBillId);
			for (ProductDetailBillEntity productDetailBillEntity : productDetailBillEntities) {
				productDetailBillRepository.delete(productDetailBillEntity);
			}
			
			productDetailRepository.deleteById(productDetailBillId);
			
		}

		//handle confirm/cancelConfirm bill
		@GetMapping("/confirmBill")
		@ResponseBody
		public void confirmBill(HttpServletRequest request) {
			int userId = Integer.parseInt(request.getParameter("userId").trim());
			UserEntity userEntity = usersRepository.findById(userId).get();
			
			BillEntity billEntity = new BillEntity();
			int billId = Integer.parseInt(request.getParameter("billId").trim());
			billEntity.setId(billId);
			billEntity.setDate(request.getParameter("date").trim());
			billEntity.setNote(request.getParameter("note").trim());
			billEntity.setPayments(request.getParameter("payments").trim());
			billEntity.setTotalMoney(request.getParameter("totalMoney").trim());
			billEntity.setUserEntity(userEntity);
			billEntity.setStatus(1);
			
			billRepository.save(billEntity);
		}
		
		@GetMapping("/cancelConfirmBill")
		@ResponseBody
		public void cancelConfirmBill(HttpServletRequest request) {
			int userId = Integer.parseInt(request.getParameter("userId").trim());
			UserEntity userEntity = usersRepository.findById(userId).get();
			
			BillEntity billEntity = new BillEntity();
			int billId = Integer.parseInt(request.getParameter("billId").trim());
			billEntity.setId(billId);
			billEntity.setDate(request.getParameter("date").trim());
			billEntity.setNote(request.getParameter("note").trim());
			billEntity.setPayments(request.getParameter("payments").trim());
			billEntity.setTotalMoney(request.getParameter("totalMoney").trim());
			billEntity.setUserEntity(userEntity);
			billEntity.setStatus(0);
			
			billRepository.save(billEntity);
		}
		
		//handle delete bill
		@GetMapping("/deleteBill")
		@ResponseBody
		public void deleteBill(HttpServletRequest request) {
			int billId = Integer.parseInt(request.getParameter("billId").trim());
			List<ProductDetailBillEntity> productDetailBillEntities = 
					productDetailBillRepository.findByBillId(billId);
			for (ProductDetailBillEntity productDetailBillEntity : productDetailBillEntities) {
				productDetailBillRepository.delete(productDetailBillEntity);
			}
			
			BillEntity billEntity = billRepository.findById(billId).get();
			billRepository.delete(billEntity);
		}
		
		//handle deleting contact
		@GetMapping("/deleteContact")
		@ResponseBody
		public void deleteContact(HttpServletRequest request) {
			int contactId = Integer.parseInt(request.getParameter("contactId"));
			ContactEntity contactEntity = contactRepository.findById(contactId).get();
			
			contactRepository.delete(contactEntity);
		}
		
		
		//handle updating about
		@GetMapping("/updateAbout")
		@ResponseBody
		public void updateAbout(HttpServletRequest request) {
			AboutEntity aboutEntity = new AboutEntity();
			int id = Integer.parseInt(request.getParameter("id2"));
			aboutEntity.setId(id);
			aboutEntity.setSelf(request.getParameter("self2"));
			aboutEntity.setHistory(request.getParameter("history2"));
			aboutEntity.setHiring(request.getParameter("hiring2"));
			
			aboutRepository.save(aboutEntity);
		}
}
