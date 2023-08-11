package com.ljy.third.util;

import com.ljy.third.vo.VOobject;

public class PageSet {
	
	private StringBuffer pageBuffer = new StringBuffer();
	
	private int currPage = 1; // 현재 위치한 페이지
	private int dbCount = 0; // 해당하는 게시물의 갯수
	private int RecordCountPerPage = 5; // 한 페이지에서 보여줄 레코드 개수
	private int groupSize = 5; //한 페이지셋 그룹을 정할 변수 

	public PageSet(int mcurrPage, int mdbCount, int RecordCountPerPage) {
		
		this.currPage = mcurrPage; //System.out.println("페이지 이동 시 생성되는 pageset의 페이지 번호 : " + this.currPage);
		this.dbCount = mdbCount;
		this.RecordCountPerPage = RecordCountPerPage;
	}

	public int getCurrPage() {
		return currPage;
	}
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	public int getDbCount() {
		return dbCount;
	}
	public void setDbCount(int dbCount) {
		this.dbCount = dbCount;
	}


	public int getRecordCountPerPage() {
		return RecordCountPerPage;
	}
	public void setRecordCountPerPage(int RecordCountPerPage) {
		this.RecordCountPerPage = RecordCountPerPage;
	}
	public int getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(int groupSize) {
		this.groupSize = groupSize;
	}
	
	//총 페이지를 설정
	public int getTotalPage() {
		
		int pages = getDbCount() / RecordCountPerPage;
		
		if(getDbCount() % RecordCountPerPage > 0) { pages++; }
		
		if(pages == 0) {return 1;}
		
		return pages;
		
	}
	
	private int getGroupOfStartPage() { //페이지셋을 구현할 것중에 시작이 되는 페이지
		
		//currpage가 페이지 추가표시할 그룹보다도 적으면 무조건 0부터 반환하게 한다
		if(getCurrPage() - getGroupSize() < 0) { return 0; }

		//currpage를 지정해서 왔다면 그룹 내의 5-10개 순번을 각 순번의 최초 순번으로 되돌리기 위해 그냥 나눈 몫을 다시 곱해서 나머지 없는 최초 페이지로 돌린다
		//6 7 8 9 10 > 6으로 시작하게 돌리기
		return (getCurrPage() / getGroupSize()) * getGroupSize();
		
	}
	private int getGroupOfEndPage() {
		
		if(getGroupOfStartPage()+groupSize-1 > getTotalPage()) { return getTotalPage(); }
		
		return getGroupOfStartPage()+groupSize-1;
	
	}//페이지셋 그룹의 시작과 끝 페이지를 설정
	
	public String getPageList() {
		int startOfPage = getGroupOfStartPage();//처음 선언할때의 시작 페이지
		int endOfPage = getGroupOfEndPage();//처음 선언할때의 끝 페이지
		
		pageBuffer.append("<ul class='pageClass'>");
		
		//처음 버튼 생성
		pageBuffer.append("<a href=javascript:fn_list('0'); class='fn_list'> [ 처음으로 ] </a> ");
		
		if(startOfPage != 0) { pageBuffer.append("<a href='javascript:fn_list( " + (startOfPage - groupSize) + " );' class='fn_list'>[ <<< ] </a> " ); }
		
		//이동할 위치의 페이지 지정
		int moveToPage = startOfPage;
		if(endOfPage > 0) {
			
			while(moveToPage <= endOfPage ) {//끝 페이지를 넘지 않게 이동할 페이지의 차수를 하나하나 따져봄
				
				//0으로 끝나지 않는 상태에서 endOfPage가 totalpage와 값이 같다면 바로 out 시키기 
				if( moveToPage == getTotalPage() ) break;
				
				if( moveToPage == getCurrPage() ) { //표현할 페이지셋 중에 해당하는 페이지에 도달하면 strong태그로 그 페이지를 강조
					
					pageBuffer.append("<strong>[ ");
					pageBuffer.append(moveToPage + 1);
					pageBuffer.append(" ]</strong>");
					
				}else {
					
					pageBuffer.append("<a href='javascript:fn_list(");
					pageBuffer.append( moveToPage);
					pageBuffer.append( ");' class='fn_list'>[ " );
					pageBuffer.append(moveToPage + 1);
					pageBuffer.append(" ]</a>");
				
				}
				
				moveToPage++;
				
			}
			
		}else {
			
			pageBuffer.append("<strong>[" +getCurrPage() +" ]</strong>");
			
		}
		/*
		System.out.println("getCurrPage : " + getCurrPage());
		System.out.println("startOfPage : " + startOfPage);
		System.out.println("endOfPage : " + endOfPage);
		System.out.println("getGroupOfEndPage : " + getGroupOfEndPage());
		System.out.println("getGroupOfEndPage() * RecordCountPerPage : " + getGroupOfEndPage() * RecordCountPerPage);
		System.out.println("getTotalPage : " + getTotalPage());
		*/
		
		//if(endOfPage != getGroupOfEndPage()) { pageBuffer.append("<a href='javascript:fn_list( " + (startOfPage + groupSize) + " );' class='fn_list'>[ >>> ] </a> " ); }
		//현재 페이지그룹 최대치가 총 폐이지 계수보다 작을떄 다음 페이지그룹 버튼을 생성, 마지막 페이지는 현재 페이즈그룹 최대치가 총 페이지 개수보다 1 작으므로 예외처리 설정  
		if(endOfPage < getTotalPage() && endOfPage != getTotalPage() - 1  ) { pageBuffer.append("<a href='javascript:fn_list( " + (startOfPage + groupSize) + " );' class='fn_list'>[ >>> ] </a> " ); }
		
		//마지막 버튼 생성
		pageBuffer.append("<a href='javascript:fn_list("+(getTotalPage()-1) + ");' class='fn_list'> [마지막]</a>");
		
		pageBuffer.append("</ul>");
		
		return pageBuffer.toString();
		
	}
	
	public VOobject recordSet(VOobject mboardVO ) {
				
				//각 vo에 담긴 index의 추출을 위해, db의 column 개수를 탐색함
				int pagestart = mboardVO.getPageIndex() * mboardVO.getRecordCountPerPage();
				int RecordCountPerPage = mboardVO.getRecordCountPerPage();
				//
				if( RecordCountPerPage + pagestart > mboardVO.getRecordCountPerPage()) { RecordCountPerPage = mboardVO.getRecordCountPerPage(); }
				
				//최종으로 받은 멤버에 시작 페이지와 표현할 레코드 개수를 입력
				mboardVO.setPageIndex(pagestart);
				mboardVO.setRecordCountPerPage(RecordCountPerPage);
		
		return mboardVO;
		
	}
	
	
}
