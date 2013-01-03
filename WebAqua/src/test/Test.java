package test;


public class Test {
	
	public static void main(String[] args){
		StringBuffer findQuery = new StringBuffer();
		findQuery.append("SELECT ");
		findQuery.append("	SEQUENCE ");
		findQuery.append("	, USER_TYPE ");
		findQuery.append("	, DECODE(USER_TYPE, '01', '내부', '02', '외부')  USER_CODE ");
		findQuery.append("	, USER_ID, USER_NAME ");
		findQuery.append("	, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE ");
		findQuery.append("	, DECODE(APPLY_STAT_CD, '01', '가입', '02', '탈퇴', '03', '재가입') APPLY_STAT_CD ");
		findQuery.append("	, DECODE(AUTH_TYPE, '00', '관리자', '01', '분석운용자', '02', '운용자', '03', '사용자', '04', '외부사용자') AUTH_TYPE ");
		findQuery.append("	, DECODE(PROG_STAT_CD, '01', '신청', '02', '승인', '03', '거부') PROG_STAT_CD ");
		findQuery.append("FROM AQUA2_USER_INFO_TBL ");
		findQuery.append("ORDER BY SEQUENCE DESC ");

		
		System.out.println(findQuery.toString());

	}
}
