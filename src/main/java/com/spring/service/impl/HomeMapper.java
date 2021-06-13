package com.spring.service.impl;

import java.util.List;

import com.spring.service.Book_review;
import com.spring.service.Book_review_comment;
import com.spring.service.Favorite_review;
import com.spring.service.Read_check;
import com.spring.service.User;

public interface HomeMapper {

	void signUp(User vo);

	User signln(User vo);

	User idChk(User vo);

	User getUser(String userId);

	User getUserDetail(String id);

	List<Book_review> getBookReviewList();

	Book_review getBookReviewDetail(int repostId);

	void userUpdate(User vo);

	void insertBookReview(Book_review book);

	void getBookReviewUpdate(Book_review book);

	void getBookReviewDelete(Book_review book);

	void userDelete(User vo);

	void insertComment(Book_review_comment comment);

	void updateComment(Book_review_comment comment);

	void deleteComment(Book_review_comment comment);

	Book_review_comment getCommentDetail(int commentId);

	List<Book_review> getBookReviewSearchDetail(String book_name);

	List<Book_review_comment> getCommentList(int reportId);

	List<Read_check> getGrade(String userId);

	List<Read_check> getfavoritecnt(String userId);

	List<Read_check> getreadingcnt(String userId);

	List<Book_review> getReviewCnt(String userId);

	List<Favorite_review> getFavoriteReviewList(String userId);

	int insertFavoriteReview(com.spring.service.Favorite_review favorite_review);

	Favorite_review getFavoriteReviewDetail(int favorite_reviewId);

	void FavoriteReviewDelete(Favorite_review favorite_review);

	Favorite_review favoriteReivewChk(Favorite_review favorite_review);

	Favorite_review favoriteReivewcount(int repostId);

	List<Read_check> getFavoriteListC(String userId);

	int insertFavoriteBookC(com.spring.service.Read_check read_check);

	Read_check favoriteChk(Read_check read_check);

	Read_check getFavoriteDetailC(int tblId);

	void FavoriteDeleteC(Read_check read_check);

	List<Read_check> getfavoriteCnt(String userId);

	List<Read_check> getReadingBookListC(String userId);

	Read_check getReadingBookDetailC(int tblId);

	void readingDeleteC(Read_check read_check);

	int insertReadingBookC(Read_check read_check);

	Read_check readingChkC(Read_check read_check);

	List<Read_check> getReadBookListC(String userId);

	int insertReadBookC(Read_check read_check);

	Read_check getReadBookDetailC(int tblId);

	void readDeleteC(Read_check read_check);

	Read_check readChkC(Read_check read_check);

	List<Read_check> getreadCnt(String userId);

	List<Read_check> getMychkList(String userId);

	List<Favorite_review> getFavoriteReviewRepostIdList(String userId);

	List<Read_check> getUserChkList(String userId);

	List<Read_check> getUserChkListFavorite(String userId);

	List<Read_check> getUserChkListReading(String userId);

	List<Read_check> getUserChkListRead(String userId);

}