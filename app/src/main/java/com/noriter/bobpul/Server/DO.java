package com.noriter.bobpul.Server;

import android.widget.ImageView;

public class DO {

    /**
     * Created by gugu on 2017-05-29.
     * Update by saebyeol on 2017-06-28.
     */

	/* 사용자 개인관련 정보 */
    private String id; // 사용자 아이디
    private String pw; // 사용자 비밀번호
    private String birth; // 생년월일
    private String tel; // 전화번호
    private String addr; // 주소
    private String action; // JSP 전송타입
    private String localBoardAddr; // 게시글 작성 매개변수 URL
    private String tempPWAddr; // 임시 비밀번호 매개변수 URL
    private String NickAddr; // 닉네임 설정 매개변수 URL
    private String boardSearchAddr; // 게시글 조회 매개변수 URL
    private String chgPw; // 새로운 비밀번호
    private String chgPwAgain; // 새로운 비밀번호 확인
    private String nick; // 사용자 닉네임
    private String tempPW; // 전송용 임시비밀번호
    private String rank; // 사용자 등급
    private ImageView ivLogo; // 게시글 프로필


    /* 게시글 관련 정보 */
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private String boardMin; // 최소인원
    private String boardMax; // 최대인원
    private int boardPrice; // 참여금액
    private String boardMonth; // 개최월
    private String boardDate; // 개최일
    private String boardAlias; // 게시글 모임명
    private String boardResInfo; // 식당정보 > 현재 미사용
    private int type; // 게시글 타입 0. 주변 / 1. 맛집
    private String strLocation; // 지역 필터링 > 맛집용

    /* 댓글 관련 정보(닉네임, 댓글내용) */
    private String commentInfo; // 댓글 내용

    /* 채팅 관련 정보(프로필, 이름, 나이) */
    private String profile; // 프로필정보 > 차후 img로 교체예정
    private String age; // 나이 > 생년월일에서 연산처리

    String meetingName;  //모임명
    String meetingAfter; //리뷰
    String reviewPermission;    //리뷰권한

    public String getMeetingName() {
        return meetingName;
    }

    public void setMeetingName(String meetingName) {
        this.meetingName = meetingName;
    }

    public String getMeetingAfter() {
        return meetingAfter;
    }

    public void setMeetingAfter(String meetingAfter) {
        this.meetingAfter = meetingAfter;
    }

    public String getReviewPermission() {
        return reviewPermission;
    }

    public void setReviewPermission(String reviewPermission) {
        this.reviewPermission = reviewPermission;
    }

    /* 사용자 개인정보 GetSet */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    //mypage에 정보 요청
    public String getMypageAddr(){
        return "?strUserNick="+getNick()+"&action="+getAction();
    }

    public String getLocalBoardAddr() {
        return "?strUserID=" + id + "&strMeetName=" + boardAlias + "&strUserNick=" + nick + "&strBoardTitle="
                + boardTitle + "&strBoardContent=" + boardContent + "&strRestInfo=" + boardResInfo + "&strOpMonth="
                + boardMonth + "&strOpDate=" + boardDate + "&iType=" + type + "&strMinP=" + boardMin + "&strMaxP="
                + boardMax + "&iOpPrice=" + boardPrice + "&iType=" + type + "&iRank=" + rank
                + "&action=" + action;
    }

    public String getReviewAddr(){
        return "?review="+getMeetingAfter()+"&action="+action+"&strUserNick="+getNick()+"&meetname="+getMeetingName();
    }

    public void setLocalBoardAddr(String localBoardAddr) {
        this.localBoardAddr = localBoardAddr;
    }

    public String getTempPWAddr() {
        return "?strUserID=" + id + "&strMail=" + tempPW + "&action=" + action;
    }

    public void setTempPWAddr(String tempPWAddr) {
        this.tempPWAddr = tempPWAddr;
    }

    public String getNickAddr() {
        return "?strUserID=" + id + "&strUserNick=" + nick + "&action=" + action;
    }

    public void setNickAddr(String nickAddr) {
        NickAddr = nickAddr;
    }

    public String getBoardSearchAddr() {
        return "?strUserID=" + id + "&action=" + action;
    }

    public void setBoardSearchAddr(String boardSearchAddr) {
        this.boardSearchAddr = boardSearchAddr;
    }

    public String getChgPw() {
        return chgPw;
    }

    public void setChgPw(String chgPw) {
        this.chgPw = chgPw;
    }

    public String getChgPwAgain() {
        return chgPwAgain;
    }

    public void setChgPwAgain(String chgPwAgain) {
        this.chgPwAgain = chgPwAgain;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTempPW() {
        return tempPW;
    }

    public void setTempPW(String tempPW) {
        this.tempPW = tempPW;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public ImageView getIvLogo() {
        return ivLogo;
    }

    public void setIvLogo(ImageView ivLogo) {
        this.ivLogo = ivLogo;
    }

    /* 게시글 정보 GetSet */
    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public String getBoardMin() {
        return boardMin;
    }

    public void setBoardMin(String boardMin) {
        this.boardMin = boardMin;
    }

    public String getBoardMax() {
        return boardMax;
    }

    public void setBoardMax(String boardMax) {
        this.boardMax = boardMax;
    }

    public int getBoardPrice() {
        return boardPrice;
    }

    public void setBoardPrice(int boardPrice) {
        this.boardPrice = boardPrice;
    }

    public String getBoardMonth() {
        return boardMonth;
    }

    public void setBoardMonth(String boardMonth) {
        this.boardMonth = boardMonth;
    }

    public String getBoardDate() {
        return boardDate;
    }

    public void setBoardDate(String boardDate) {
        this.boardDate = boardDate;
    }

    public String getBoardAlias() {
        return boardAlias;
    }

    public void setBoardAlias(String boardAlias) {
        this.boardAlias = boardAlias;
    }

    public String getBoardResInfo() {
        return boardResInfo;
    }

    public void setBoardResInfo(String boardResInfo) {
        this.boardResInfo = boardResInfo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStrLocation() {
        return strLocation;
    }

    public void setStrLocation(String strLocation) {
        this.strLocation = strLocation;
    }

    /* 댓글 정보 GetSet */
    public String getCommentInfo() {
        return commentInfo;
    }

    public void setCommentInfo(String commentInfo) {
        this.commentInfo = commentInfo;
    }

    /* 채팅 정보 GetSet */
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    /* 기타 관련 기능 메소드 */
    public String getRegSimpleAddr() { // 회원가입 매개변수 URL
        return "?strUserID=" + id + "&strUserPW=" + pw + "&strBirth=" + birth + "&strTel=" + tel + "&strAddr=" + addr
                + "&action=" + action;
    }

    public String getLoginSimpleAddr() { // 로그인 매개변수 URL
        return "?strUserID=" + id + "&strUserPW=" + pw + "&action=" + action;
    }

    public String getNewPWSimpleAddr() { // 비밀번호 변경 매개변수 URL
        return "?strUserID=" + id + "&strChgPw=" + chgPw + "&action=" + action;
    }


}
