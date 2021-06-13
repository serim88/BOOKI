package com.spring.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.service.Book_review;
import com.spring.service.Book_review_comment;
import com.spring.service.Favorite_review;
import com.spring.service.Read_check;
import com.spring.service.User;

@Repository
public class HomeService /* implements HomeDao */ {

	@Inject
	private SqlSession sqlSession;

	public void signUp(User vo) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.signUp(vo);
	}

	public User signln(User vo) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.signln(vo);
	}

	public int idChk(User vo) {
		int result = sqlSession.selectOne("com.spring.service.impl.HomeMapper.idChk", vo);
		return result;
	}

	public User getUser(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getUser(userId);
	}

	public User getUserDetail(String id) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getUserDetail(id);
	}

	public List<Book_review> getBookReviewList() {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getBookReviewList();
	}

	public Book_review getBookReviewDetail(int repostId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getBookReviewDetail(repostId);
	}

	public void userUpdate(User vo) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.userUpdate(vo);
	}

	public void insertBookReview(Book_review book) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.insertBookReview(book);
	}

	public void getBookReviewUpdate(Book_review book) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.getBookReviewUpdate(book);
	}

	public void getBookReviewDelete(Book_review book) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.getBookReviewDelete(book);
	}

	public void userDelete(User vo) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.userDelete(vo);
	}

	public void insertComment(Book_review_comment comment) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.insertComment(comment);
	}

	public void updateComment(Book_review_comment comment) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.updateComment(comment);
	}

	public void deleteComment(Book_review_comment comment) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.deleteComment(comment);
	}

	public Book_review_comment getCommentDetail(int commentId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getCommentDetail(commentId);
	}

	public List<Book_review> getBookReviewSearchDetail(String book_name) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getBookReviewSearchDetail(book_name);
	}

	public List<Book_review_comment> getCommentList(int reportId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getCommentList(reportId);
	}

	public List<Book_review> getReviewCnt(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getReviewCnt(userId);
	}

	public List<Read_check> getGrade(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getGrade(userId);
	}

	public List<Read_check> getreadingcnt(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getreadingcnt(userId);
	}

	public List<Read_check> getfavoritecnt(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getfavoritecnt(userId);
	}

	public List<Favorite_review> getFavoriteReviewList(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getFavoriteReviewList(userId);
	}

	public List<Favorite_review> getFavoriteReviewRepostIdList(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getFavoriteReviewRepostIdList(userId);
	}

	public int favoriteReivewChk(Favorite_review favorite_review) {
		int resultLike = sqlSession.selectOne("com.spring.service.impl.HomeMapper.favoriteReivewChk", favorite_review);
		return resultLike;
	}

	public int insertFavoriteReview(Favorite_review favorite_review) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.insertFavoriteReview(favorite_review);
	}

	public Favorite_review getFavoriteReviewDetail(int favorite_reviewId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getFavoriteReviewDetail(favorite_reviewId);
	}

	public void FavoriteReviewDelete(Favorite_review favorite_review) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.FavoriteReviewDelete(favorite_review);
	}

	public int favoriteReivewcount(int repostId) {
		int resultLike = sqlSession.selectOne("com.spring.service.impl.HomeMapper.favoriteReivewcount", repostId);
		return resultLike;
	}

	public List<Read_check> getFavoriteListC(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getFavoriteListC(userId);
	}

	public int favoriteChk(Read_check read_check) {
		int resultLike = sqlSession.selectOne("com.spring.service.impl.HomeMapper.favoriteChk", read_check);
		return resultLike;
	}

	public int insertFavoriteBookC(Read_check read_check) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.insertFavoriteBookC(read_check);
	}

	public Read_check getFavoriteDetailC(int tblId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getFavoriteDetailC(tblId);
	}

	public void FavoriteDeleteC(Read_check read_check) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.FavoriteDeleteC(read_check);
	}

	public List<Read_check> getReadingBookListC(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getReadingBookListC(userId);
	}

	public int readingChkC(Read_check read_check) {
		int resultLike = sqlSession.selectOne("com.spring.service.impl.HomeMapper.readingChkC", read_check);
		return resultLike;
	}

	public int insertReadingBookC(Read_check read_check) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.insertReadingBookC(read_check);
	}

	public Read_check getReadingBookDetailC(int tblId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getReadingBookDetailC(tblId);
	}

	public void readingDeleteC(Read_check read_check) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.readingDeleteC(read_check);
	}

	public List<Read_check> getReadBookListC(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getReadBookListC(userId);
	}

	public int readChkC(Read_check read_check) {
		int resultLike = sqlSession.selectOne("com.spring.service.impl.HomeMapper.readChkC", read_check);
		return resultLike;
	}

	public int insertReadBookC(Read_check read_check) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.insertReadBookC(read_check);
	}

	public Read_check getReadBookDetailC(int tblId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getReadBookDetailC(tblId);
	}

	public void readDeleteC(Read_check read_check) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		homeMapper.readDeleteC(read_check);
	}

	public List<Read_check> getMychkList(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getMychkList(userId);
	}
	
	public List<Read_check> getUserChkList(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getUserChkList(userId);
	}
	
	public List<Read_check> getUserChkListFavorite(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getUserChkListFavorite(userId);
	}
	
	public List<Read_check> getUserChkListReading(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getUserChkListReading(userId);
	}
	
	public List<Read_check> getUserChkListRead(String userId) {
		// TODO Auto-generated method stub
		HomeMapper homeMapper = sqlSession.getMapper(HomeMapper.class);
		return homeMapper.getUserChkListRead(userId);
	}
	
	
	
	
}