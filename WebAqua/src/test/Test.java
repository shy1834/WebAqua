package test;


public class Test {
	
	public static void main(String[] args){
		StringBuffer findQuery = new StringBuffer();
		findQuery.append("SELECT ");
		findQuery.append("	SEQUENCE ");
		findQuery.append("	, USER_TYPE ");
		findQuery.append("	, DECODE(USER_TYPE, '01', '����', '02', '�ܺ�')  USER_CODE ");
		findQuery.append("	, USER_ID, USER_NAME ");
		findQuery.append("	, TO_CHAR(REG_DATE, 'YYYY/MM/DD') REG_DATE ");
		findQuery.append("	, DECODE(APPLY_STAT_CD, '01', '����', '02', 'Ż��', '03', '�簡��') APPLY_STAT_CD ");
		findQuery.append("	, DECODE(AUTH_TYPE, '00', '������', '01', '�м������', '02', '�����', '03', '�����', '04', '�ܺλ����') AUTH_TYPE ");
		findQuery.append("	, DECODE(PROG_STAT_CD, '01', '��û', '02', '����', '03', '�ź�') PROG_STAT_CD ");
		findQuery.append("FROM AQUA2_USER_INFO_TBL ");
		findQuery.append("ORDER BY SEQUENCE DESC ");

		
		System.out.println(findQuery.toString());

	}
}
