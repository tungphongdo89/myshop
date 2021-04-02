package com.tungphongdo.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.tungphongdo.entity.BillEntity;
import com.tungphongdo.entity.ContactEntity;
import com.tungphongdo.entity.CustomUserDetail;
import com.tungphongdo.entity.ProductDetailBillEntity;
import com.tungphongdo.entity.ProductDetailEntity;
import com.tungphongdo.entity.RoleEntity;
import com.tungphongdo.entity.UserEntity;
import com.tungphongdo.entity.UserRoleEntity;
import com.tungphongdo.repository.BillRepository;
import com.tungphongdo.repository.ContactRepository;
import com.tungphongdo.repository.ProductDetailBillRepository;
import com.tungphongdo.repository.ProductDetailRepository;
import com.tungphongdo.repository.RoleRepository;
import com.tungphongdo.repository.UserRoleRepository;
import com.tungphongdo.repository.UsersRepository;

@Controller
@RequestMapping("/ajax")
@SessionAttributes("carts")
public class AjaxController {
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	@Autowired
	private ProductDetailBillRepository productDetailBillRepository;
	
	@Autowired
	private ContactRepository contactRepository;
	
	
	//handle signup
	@GetMapping("/signup")
	@ResponseBody
	public String doSignUp(HttpServletRequest request, Model model) {
		
		UserEntity userEntity = new UserEntity();
		
		userEntity.setAddress(request.getParameter("address").trim());
		userEntity.setEmail(request.getParameter("email").trim());
		userEntity.setEnabled(1);
		userEntity.setGender(request.getParameter("gender").trim());
		String pass = BCrypt.hashpw(request.getParameter("pass").trim(), BCrypt.gensalt(12));
		userEntity.setPassword(pass);
		userEntity.setTelephone(Long.parseLong(request.getParameter("telephone").trim()));
		String username = request.getParameter("username").trim();
		
		List<UserEntity> userEntities = usersRepository.findAll();
		List<String> listUsernames = new ArrayList<String>();
		for (UserEntity userEntity2 : userEntities) {
			listUsernames.add(userEntity2.getUsername());
		}
	
		if(listUsernames.contains(username)) {
			System.out.println("tài khoản mật khẩu đã tồn tại");
			return "false";
		}
		else {
			userEntity.setUsername(username);
			usersRepository.save(userEntity);
			
			RoleEntity roleEntity = new RoleEntity();
			roleEntity  = roleRepository.findById(2).get();
			
			UserRoleEntity userRoleEntity = new UserRoleEntity(userEntity, roleEntity);
			userRoleRepository.save(userRoleEntity);
			
			return "true";
		}
	}
	
	//handle customer update information
	@RequestMapping("/update")
	@ResponseBody
	public String updateCustomer(HttpServletRequest request) {
		
		if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
			
					
			CustomUserDetail users = 
					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserEntity userEntity1 =  usersRepository.findByUsername(users.getUsername());
		
			UserEntity userEntity = new UserEntity();
					
			userEntity.setId(userEntity1.getId());
			userEntity.setUsername(request.getParameter("username").trim());
			userEntity.setAddress(request.getParameter("address").trim());
			userEntity.setEmail(request.getParameter("email").trim());
			userEntity.setEnabled(1);
			userEntity.setGender(request.getParameter("gender").trim());
			userEntity.setPassword(request.getParameter("password").trim());
			userEntity.setTelephone(Long.parseLong(request.getParameter("telephone").trim()));
					
			usersRepository.save(userEntity);
					
			return "true";
		}
		else {
			return "false";
		}
	}
	
	
	//handle customer change
	@GetMapping("/change")
	@ResponseBody
	public String changePass(HttpServletRequest request) {
					
			CustomUserDetail users = 
					(CustomUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			UserEntity userEntity1 =  usersRepository.findByUsername(users.getUsername());
		
			UserEntity userEntity = new UserEntity();
					
			userEntity.setId(userEntity1.getId());
			userEntity.setUsername(userEntity1.getUsername());
			userEntity.setAddress(userEntity1.getAddress());
			userEntity.setEmail(userEntity1.getEmail());
			userEntity.setEnabled(1);
			userEntity.setGender(userEntity1.getGender());
			userEntity.setTelephone(userEntity1.getTelephone());
			
			String oldPass = request.getParameter("oldPass").trim();
			
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			
			if(bCryptPasswordEncoder.matches(oldPass, userEntity1.getPassword()) == true) {
				String pass = BCrypt.hashpw(request.getParameter("newPass").trim(), BCrypt.gensalt(12));
				userEntity.setPassword(pass);
				usersRepository.save(userEntity);
				return "true";
			}
			else {
				return "false";
			}
	}
	//check exist products in the cart
	public int CheckCart(List<Cart> carts, int product_id, int size_id, int color_id) {
		for(int i=0; i<carts.size(); i++) {
			if((carts.get(i).getProduct_id() == product_id) && (carts.get(i).getSize_id() == size_id)
					&& (carts.get(i).getColor_id() == color_id)){
				return i;
			}
		}
		return -1;
	}
	
	//handle event add cart
	@GetMapping("/addCart")
	@ResponseBody
	public String addCart(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("carts") == null) {
			List<Cart> carts = new ArrayList<Cart>();
			Cart cart = new Cart();
			cart.setProductDetail_id(Integer.parseInt(request.getParameter("productDetail_id").trim()));
			cart.setProduct_id(Integer.parseInt(request.getParameter("product_id").trim()));
			cart.setSize_id(Integer.parseInt(request.getParameter("size_id").trim()));
			cart.setColor_id(Integer.parseInt(request.getParameter("color_id").trim()));
			cart.setProductName(request.getParameter("productName"));
			cart.setSizeName(request.getParameter("sizeName"));
			cart.setColorName(request.getParameter("colorName"));
			cart.setPrice(Double.parseDouble(request.getParameter("price").trim()));
			cart.setAmount(1);
			
			carts.add(cart);
			session.setAttribute("carts", carts);
			return carts.size()+"";
		}
		else {
			@SuppressWarnings("unchecked")
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			int product_id = Integer.parseInt(request.getParameter("product_id").trim());
			int size_id    = Integer.parseInt(request.getParameter("size_id").trim());
			int color_id   = Integer.parseInt(request.getParameter("color_id").trim());
			
			int site = CheckCart(carts, product_id, size_id, color_id);
			if(site==-1) {
				Cart cart = new Cart();
				cart.setProductDetail_id(Integer.parseInt(request.getParameter("productDetail_id").trim()));
				cart.setProduct_id(Integer.parseInt(request.getParameter("product_id").trim()));
				cart.setSize_id(Integer.parseInt(request.getParameter("size_id").trim()));
				cart.setColor_id(Integer.parseInt(request.getParameter("color_id").trim()));
				cart.setProductName(request.getParameter("productName"));
				cart.setSizeName(request.getParameter("sizeName"));
				cart.setColorName(request.getParameter("colorName"));
				cart.setPrice(Double.parseDouble(request.getParameter("price").trim()));
				cart.setAmount(1);
				
				carts.add(cart);
			}
			else {
				int newAmount = carts.get(site).getAmount() + 1;
				carts.get(site).setAmount(newAmount);
			}
			return carts.size()+"";
			
		}
		
	}
	
	//Handle event change cart
	@GetMapping("/changeCart")
	@ResponseBody
	public void changeCart(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("carts") != null) {
			@SuppressWarnings("unchecked")
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			int product_id = Integer.parseInt(request.getParameter("product_id").trim());
			int size_id    = Integer.parseInt(request.getParameter("size_id").trim());
			int color_id   = Integer.parseInt(request.getParameter("color_id").trim());
			
			int site = CheckCart(carts, product_id, size_id, color_id);
				//chỉ cần cập nhật lại số lượng
			int newAmout = Integer.parseInt(request.getParameter("amount").trim());
			carts.get(site).setAmount(newAmout);
		}
	}
	
	//handle event delete cart
	@GetMapping("/deleteCart")
	@ResponseBody
	public void deleteCart(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("carts") != null) {
			@SuppressWarnings("unchecked")
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			int product_id = Integer.parseInt(request.getParameter("product_id").trim());
			int size_id    = Integer.parseInt(request.getParameter("size_id").trim());
			int color_id   = Integer.parseInt(request.getParameter("color_id").trim());
			
			int site = CheckCart(carts, product_id, size_id, color_id);
		
			carts.remove(site);
		}
	}
	
	//handle event buy product
	@GetMapping("/buyProduct")
	@ResponseBody
	public void buyProduct(HttpServletRequest request, HttpSession session) {
		if(session.getAttribute("carts") != null) {
			
			 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
			 LocalDateTime now = LocalDateTime.now();  
			 //System.out.println(dtf.format(now));  
			int userId = Integer.parseInt(request.getParameter("userId"));
			UserEntity userEntity = usersRepository.findById(userId).get();
			
			BillEntity billEntity = new BillEntity();
			billEntity.setDate(dtf.format(now));
			billEntity.setNote(request.getParameter("note").trim());
			billEntity.setPayments(request.getParameter("payments").trim());
			billEntity.setTotalMoney(request.getParameter("totalMoney").trim());
			billEntity.setUserEntity(userEntity);
			billEntity.setStatus(0);
			
			billRepository.save(billEntity);
			
			@SuppressWarnings("unchecked")
			List<Cart> carts = (List<Cart>) session.getAttribute("carts");
			for (Cart cart : carts) {
				ProductDetailBillEntity productDetailBillEntity = new ProductDetailBillEntity();
				
				int productDetail_id = cart.getProductDetail_id();
				ProductDetailEntity productDetailEntity = productDetailRepository.findById(productDetail_id).get();
				
				productDetailBillEntity.setBillEntity(billEntity);
				productDetailBillEntity.setProductDetailEntity(productDetailEntity);
				productDetailBillEntity.setBoughtAmount(cart.getAmount());
				
				productDetailBillRepository.save(productDetailBillEntity);
				
				//xử lý tính số lượng sản phẩm còn lại sau khi người dùng mua
				int remainAmount = productDetailEntity.getProductAmount() - cart.getAmount();
				
				ProductDetailEntity productDetailEntity2 = new ProductDetailEntity();
				productDetailEntity2.setId(productDetail_id);
				productDetailEntity2.setDate(productDetailEntity.getDate());
				productDetailEntity2.setColorEntity(productDetailEntity.getColorEntity());
				productDetailEntity2.setSizeEntity(productDetailEntity.getSizeEntity());
				productDetailEntity2.setProductEntity(productDetailEntity.getProductEntity());
				productDetailEntity2.setProductDetailBillEntities(productDetailEntity.getProductDetailBillEntities());
				productDetailEntity2.setProductAmount(remainAmount);
				
				productDetailRepository.save(productDetailEntity2);
			}
		}
	}
	
	//handle contact
	@GetMapping("/doContact")
	@ResponseBody
	public void doContact(HttpServletRequest request) {
		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setName(request.getParameter("name"));
		contactEntity.setEmail(request.getParameter("email"));
		contactEntity.setPhone(Integer.parseInt(request.getParameter("phone")));
		contactEntity.setComment(request.getParameter("comment"));
		
		contactRepository.save(contactEntity);
	}
	
	

}
