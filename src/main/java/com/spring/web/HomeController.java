package com.spring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.service.Book_review;
import com.spring.service.Book_review_comment;
import com.spring.service.Favorite_review;
import com.spring.service.Read_check;
import com.spring.service.User;
import com.spring.service.impl.HomeService;
import com.spring.service.impl.JwtServiceImpl;


@Controller
public class HomeController {

   @Autowired
   private HomeService service;

   private int repostId;

   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

   private static final int favorite_reviewId = 0;



   @ResponseBody
   @RequestMapping(value = "/signIn/auto", method = RequestMethod.GET)
   public Map<String, Object> signln_auto(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         JwtServiceImpl.createToken(JwtServiceImpl.getUserId(token));
         Map.put("message", "로그인 성공");
         Map.put("code", 200);
         Map.put("isSuccess", true);
      } else {
         Map.put("message", "로그인 실패");
         Map.put("code", 403);
         Map.put("isSuccess", false);

      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/signinIn", method = RequestMethod.POST)
   public Map<String, Object> signln(@RequestBody User vo) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      User rs = service.signln(vo);
      if (rs == null) {
         Map.put("code", 500);
         Map.put("message", "로그인 실패");
      } else {
         String jwt = JwtServiceImpl.createToken(rs.getId());
         Map<String, Object> result = new HashMap<String, Object>();
         result.put("jwt", jwt);
         result.put("useridx", rs.getUseridx());
         result.put("id", rs.getId());
         Map.put("result", result);
         Map.put("isSuccess", true);
         Map.put("code", 200);
         Map.put("message", "로그인 성공");
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/signout", method = RequestMethod.POST)
   public Map<String, Object> signout(@RequestBody User vo, HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();

      try {
         String token = request.getHeader("X_ACCESS_TOKEN");
         JwtServiceImpl.validateToken(token);
         String jwt = JwtServiceImpl.signoutToken(token);
         Map.put("isSuccess", true);

      } catch (Exception e) {
         // TODO: handle exception
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/signUp", method = RequestMethod.POST)
   public Map<String, Object> signUp(@RequestBody User vo) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      int result = service.idChk(vo);
      try {
         if (result == 1) {
            Map.put("isSuccess", false);
         } else if (result == 0) {
            service.signUp(vo);
            Map.put("result", vo);
            Map.put("isSuccess", true);
         }
      } catch (Exception e) {
         // TODO: handle exception
         service.signUp(vo);
         Map.put("result", vo);
         Map.put("isSuccess", false);
      }

      return Map;
   }
   
   @ResponseBody
   @RequestMapping(value = "/checkId", method = RequestMethod.POST)
   public Map<String, Object> checkId(@RequestBody User vo) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      int result = service.idChk(vo);
         if (result == 1) {
            Map.put("isSuccess", false);
         } else if (result == 0) {
            Map.put("isSuccess", true);
         }
      return Map;
   }

   
   @ResponseBody
   @RequestMapping(value = "/mypage", method = RequestMethod.GET)
   public Map<String, Object> userInfo(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      User userresult = service.getUser(JwtServiceImpl.getUserId(token));

      List<Book_review> result1 = service.getReviewCnt(JwtServiceImpl.getUserId(token));
      int b = result1.size();

      
      if (rs) {
         List<Read_check> result2 = service.getGrade(JwtServiceImpl.getUserId(token));
         List<Read_check> result3 = service.getfavoritecnt(JwtServiceImpl.getUserId(token));
         List<Read_check> result4 = service.getreadingcnt(JwtServiceImpl.getUserId(token));
         
         
         int a = result2.size();
         int c = result3.size();
         int d = result4.size();
         
         
         if (a <= 10) {
            Map.put("grade", "silver");

         } else if (a <= 20) {
            Map.put("grade", "gold");

         } else if (a <= 30) {
            Map.put("grade", "vip");

         } else {
            Map.put("grade", "vvip");

         }

         Map.put("result", userresult);
         Map.put("favoriteCnt", c);
         Map.put("readingCnt", d);
         Map.put("readCnt", a);
         Map.put("reviewCnt", b);
         Map.put("code", 200);
         Map.put("isSuccess", true);
      } else {
         Map.put("code", 403);
         Map.put("isSuccess", false);
      }
      return Map;
   }

	@ResponseBody
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public Map<String, Object> userInfo1(@RequestParam(value = "id", required = false) String id) {
		Map<String, Object> Map = new HashMap<String, Object>();
		User userresult = service.getUser(id);

		List<Book_review> result1 = service.getReviewCnt(id);
		int b = result1.size();

		List<Read_check> result2 = service.getGrade(id);
		List<Read_check> result3 = service.getfavoritecnt(id);
		List<Read_check> result4 = service.getreadingcnt(id);

		int a = result2.size();
		int c = result3.size();
		int d = result4.size();

		if (a <= 10) {
			Map.put("grade", "silver");

		} else if (a <= 20) {
			Map.put("grade", "gold");

		} else if (a <= 30) {
			Map.put("grade", "vip");

		} else {
			Map.put("grade", "vvip");

		}

		Map.put("result", userresult);
		Map.put("favoriteCnt", c);
		Map.put("readingCnt", d);
		Map.put("readCnt", a);
		Map.put("reviewCnt", b);
		Map.put("isSuccess", true);
	
		return Map;

	}


   @ResponseBody
   @RequestMapping(value = "/mypage", method = RequestMethod.PATCH)
   public Map<String, Object> userUpdate(HttpServletRequest request, @RequestBody User vo) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      vo.setId(JwtServiceImpl.getUserId(token));

      if (rs) {
         service.userUpdate(vo);
         User result = service.getUser(JwtServiceImpl.getUserId(token));
         Map.put("result", result);
         Map.put("code", 200);
         Map.put("isSuccess", true);
      } else {
         Map.put("code", 403);
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/book", method = RequestMethod.POST)
   public Map<String, Object> insertBookReview(HttpServletRequest request, @RequestBody Book_review book)
         throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         service.insertBookReview(book);
         Book_review result = service.getBookReviewDetail(book.getRepostId());
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/book/{repostId}", method = RequestMethod.PATCH)
   public Map<String, Object> getBookReviewUpdate(@PathVariable("repostId") int repostId, HttpServletRequest request,
         @RequestBody Book_review book) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      book.setRepostId(repostId);
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      book.setId(JwtServiceImpl.getUserId(token));
      if (rs) {
         service.getBookReviewUpdate(book);
         Book_review result = service.getBookReviewDetail(repostId);
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/book/{repostId}", method = RequestMethod.DELETE)
   public Map<String, Object> getBookReviewDelete(@PathVariable("repostId") int repostId, HttpServletRequest request,
         Book_review book) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      book.setRepostId(repostId);
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         Book_review result = service.getBookReviewDetail(repostId);
         service.getBookReviewDelete(book);   
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/boardlist", method = RequestMethod.GET)
   public Map<String, Object> getBookList(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      List<Book_review> result = service.getBookReviewList();
      Map.put("result", result);
      Map.put("isSuccess", true);
      return Map;

   }

   @ResponseBody
   @RequestMapping(value = "/book/{repostId}", method = RequestMethod.GET)
   public Map<String, Object> getBookDetail(@PathVariable("repostId") int repostId, HttpServletRequest request)
         throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);

      int result1 = service.favoriteReivewcount(repostId);

      if (rs) {
         Book_review result = service.getBookReviewDetail(repostId);
         Map.put("result", result);
         Map.put("isSuccess", true);
         Map.put("favoritecount", result1);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/book/{book_name}/search", method = RequestMethod.GET)
   public Map<String, Object> getBookDetailSearch(@PathVariable("book_name") String book_name,
         HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Book_review> result = service.getBookReviewSearchDetail(book_name);
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   

   @ResponseBody
   @RequestMapping(value = "/user", method = RequestMethod.DELETE)
   public Map<String, Object> userDelete(HttpServletRequest request, User vo) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         User user = service.getUser(JwtServiceImpl.getUserId(token));
         service.userDelete(vo);
         Map.put("result", user);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/comment", method = RequestMethod.POST)
   public Map<String, Object> insertComment(HttpServletRequest request, @RequestBody Book_review_comment comment)
         throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         service.insertComment(comment);
         Book_review_comment result = service.getCommentDetail(comment.getCommentId());
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.PATCH)
   public Map<String, Object> updateComment(@PathVariable("commentId") int commentId, HttpServletRequest request,
         @RequestBody Book_review_comment comment) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      comment.setCommentId(commentId);
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      comment.setId(JwtServiceImpl.getUserId(token));
      if (rs) {
         service.updateComment(comment);
         Book_review_comment result = service.getCommentDetail(commentId);
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/comment/{commentId}", method = RequestMethod.DELETE)
   public Map<String, Object> deleteComment(@PathVariable("commentId") int commentId, HttpServletRequest request,
         Book_review_comment comment) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      comment.setCommentId(commentId);
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         service.deleteComment(comment);
         Map.put("result", comment);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/commentList/{repostId}", method = RequestMethod.GET)
   public Map<String, Object> getCommentList(@PathVariable("repostId") int repostId, HttpServletRequest request)
         throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      // comment.setRepostId(repostId);
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Book_review_comment> result = service.getCommentList(repostId);
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   

   @ResponseBody
   @RequestMapping(value = "/favoriteReview", method = RequestMethod.POST)
   public Map<String, Object> insertFavoriteReview(HttpServletRequest request,
         @RequestBody Favorite_review favorite_review) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();

      String token = request.getHeader("X_ACCESS_TOKEN");

      favorite_review.setId(JwtServiceImpl.getUserId(token));
      boolean rs = JwtServiceImpl.validateToken(token);
      int resultLike = service.favoriteReivewChk(favorite_review);

      if (rs && resultLike == 1) {
         service.FavoriteReviewDelete(favorite_review);
         Map.put("status", "delete");
         Map.put("isSuccess", true);
      } else if (rs && resultLike == 0) {
         service.insertFavoriteReview(favorite_review);
         Favorite_review result = service.getFavoriteReviewDetail(favorite_review.getfavorite_reviewId());
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/favoriteReviewList", method = RequestMethod.GET)
   public Map<String, Object> getFavoriteReviewList(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Favorite_review> result = service.getFavoriteReviewList(JwtServiceImpl.getUserId(token));
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }
   
   @ResponseBody
   @RequestMapping(value = "/favoriteReviewRepostIdList", method = RequestMethod.GET)
   public Map<String, Object> getFavoriteReviewRepostIdList(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Favorite_review> result = service.getFavoriteReviewRepostIdList(JwtServiceImpl.getUserId(token));
         Map.put("result", result);
      } 
      return Map;
   }



   @ResponseBody
   @RequestMapping(value = "/user/like", method = RequestMethod.POST)
   public Map<String, Object> insertFavoriteBookC(HttpServletRequest request, @RequestBody Read_check read_check)
         throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();

      String token = request.getHeader("X_ACCESS_TOKEN");

      read_check.setId(JwtServiceImpl.getUserId(token));
      boolean rs = JwtServiceImpl.validateToken(token);
      int resultLike1 = service.favoriteChk(read_check);
      int resultLike2 = service.readingChkC(read_check);
      int resultLike3 = service.readChkC(read_check);

      if (rs && resultLike1 == 1) {
         service.FavoriteDeleteC(read_check);
         Map.put("status", "delete_like");
         Map.put("isSuccess", true);
      } else if (rs && resultLike3 == 1) {
         service.readDeleteC(read_check);
         service.insertFavoriteBookC(read_check);
         Read_check result = service.getFavoriteDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("status", "delete_read");
         Map.put("isSuccess", true);
      } else if (rs && resultLike2 == 1) {
         service.readingDeleteC(read_check);
         service.insertFavoriteBookC(read_check);
         Read_check result = service.getFavoriteDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("status", "delete_reading");
         Map.put("isSuccess", true);

      } else if (rs && resultLike1 == 0 && resultLike2 == 0 && resultLike3 == 0){
         service.insertFavoriteBookC(read_check);
         Read_check result = service.getFavoriteDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "/user/likelist", method = RequestMethod.GET)
   public Map<String, Object> getFavoriteListC(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Read_check> result = service.getFavoriteListC(JwtServiceImpl.getUserId(token));
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }



   @ResponseBody
   @RequestMapping(value = "user/reading", method = RequestMethod.POST)
   public Map<String, Object> insertReadingBookC(HttpServletRequest request, @RequestBody Read_check read_check)
         throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();

      String token = request.getHeader("X_ACCESS_TOKEN");

      read_check.setId(JwtServiceImpl.getUserId(token));
      boolean rs = JwtServiceImpl.validateToken(token);
      int resultLike1 = service.favoriteChk(read_check);
      int resultLike2 = service.readingChkC(read_check);
      int resultLike3 = service.readChkC(read_check);

      if (rs && resultLike2 == 1) {
         service.readingDeleteC(read_check);
         Map.put("status", "delete_reading");
         Map.put("isSuccess", true);
      } else if (rs && resultLike1 == 1) {
         service.FavoriteDeleteC(read_check);
         service.insertReadingBookC(read_check);
         Read_check result = service.getReadingBookDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("status", "delete_like");
         Map.put("isSuccess", true);
      } else if (rs && resultLike3 == 1) {
         service.readDeleteC(read_check);
         service.insertReadingBookC(read_check);
         Read_check result = service.getReadingBookDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("status", "delete_read");
         Map.put("isSuccess", true);

      } else if (rs && resultLike1 == 0 && resultLike2 == 0 && resultLike3 == 0){
         service.insertReadingBookC(read_check);
         Read_check result = service.getReadingBookDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "user/readinglist", method = RequestMethod.GET)
   public Map<String, Object> getReadingBookListC(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Read_check> result = service.getReadingBookListC(JwtServiceImpl.getUserId(token));
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }


   @ResponseBody
   @RequestMapping(value = "user/read", method = RequestMethod.POST)
   public Map<String, Object> insertReadBookC(HttpServletRequest request, @RequestBody Read_check read_check)
         throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();

      String token = request.getHeader("X_ACCESS_TOKEN");

      read_check.setId(JwtServiceImpl.getUserId(token));
      boolean rs = JwtServiceImpl.validateToken(token);
      
      int resultLike1 = service.favoriteChk(read_check);
      int resultLike2 = service.readingChkC(read_check);
      int resultLike3 = service.readChkC(read_check);

      if (rs && resultLike3 == 1) {
         service.readDeleteC(read_check);
         Map.put("status", "delete_read");
         Map.put("isSuccess", true);
      } else if (rs && resultLike1 == 1) {
         service.FavoriteDeleteC(read_check);
         service.insertReadBookC(read_check);
         Read_check result = service.getReadBookDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("status", "delete_like");
         Map.put("isSuccess", true);
      } else if (rs && resultLike2 == 1) {
         service.readingDeleteC(read_check);
         service.insertReadBookC(read_check);
         Read_check result = service.getReadBookDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("status", "delete_reading");
         Map.put("isSuccess", true);

      } else if (rs && resultLike1 == 0 && resultLike2 == 0 && resultLike3 == 0){
         service.insertReadBookC(read_check);
         Read_check result = service.getReadBookDetailC(read_check.getTblId());
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }

   @ResponseBody
   @RequestMapping(value = "user/readlist", method = RequestMethod.GET)
   public Map<String, Object> getReadBookListC(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Read_check> result = service.getReadBookListC(JwtServiceImpl.getUserId(token));
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }
   
   @ResponseBody
   @RequestMapping(value = "user/checklist", method = RequestMethod.GET)
   public Map<String, Object> getMychkList(HttpServletRequest request) throws Exception {
      Map<String, Object> Map = new HashMap<String, Object>();
      String token = request.getHeader("X_ACCESS_TOKEN");
      boolean rs = JwtServiceImpl.validateToken(token);
      if (rs) {
         List<Read_check> result = service.getMychkList(JwtServiceImpl.getUserId(token));
         Map.put("result", result);
         Map.put("isSuccess", true);
      } else {
         Map.put("isSuccess", false);
      }
      return Map;
   }
   
   @ResponseBody
   @RequestMapping(value = "/UserChkListFavorite", method = RequestMethod.GET)
   public Map<String, Object> getUserChkListFavorite(@RequestParam(value = "id", required = false) String id) {
      Map<String, Object> Map = new HashMap<String, Object>();

      List<Read_check> result = service.getUserChkListFavorite(id);

      Map.put("result", result);
      Map.put("isSuccess", true);

      return Map;
   }
   
   @ResponseBody
   @RequestMapping(value = "/UserChkListReading", method = RequestMethod.GET)
   public Map<String, Object> getUserChkListReading(@RequestParam(value = "id", required = false) String id) {
      Map<String, Object> Map = new HashMap<String, Object>();

      List<Read_check> result = service.getUserChkListReading(id);

      Map.put("result", result);
      Map.put("isSuccess", true);

      return Map;
   }
   
   @ResponseBody
   @RequestMapping(value = "/UserChkListRead", method = RequestMethod.GET)
   public Map<String, Object> getUserChkListRead(@RequestParam(value = "id", required = false) String id) {
      Map<String, Object> Map = new HashMap<String, Object>();

      List<Read_check> result = service.getUserChkListRead(id);

      Map.put("result", result);
      Map.put("isSuccess", true);

      return Map;
   }

}