/********************************************************************************
 * �� �� �� : 2007�� 10�� 26�� 
 * �� �� �� : (��)�ѱ��̵𿡽� - ������ �븮
 * �ۼ���� : ���̺��� ȭ���� �л�ó���� �κ��� �ϳ��� ȭ�Ϸ� �ϰ�
 *            ó���Ͽ���.
 * ���     : �� ȭ���� ���̺��� ���ȭ���� ��� �κп� �Ʒ��� ���� 
 *            ������ �� �ش� �Լ��� ȣ���Ͽ� ����Ѵ�. 
 *  ex) <script type="text/javascript" src="/js/mybuilder_view.js"></script>
 ********************************************************************************/

/* ���̺��� ȭ���� ��ü�� �ҷ� ��ġ�۾��� �Ѵ�. 80�� ��Ʈ ��ȣ */
function myLoader80(Loader1)
{	
	 Loader1.WindowTitle = "���̺��� ��ġ";
	 Loader1.DownloadMessage = "���࿡ �ʿ��� ���ϵ��� �����ް� �ֽ��ϴ�.";
	 Loader1.RegisterMessage = "���� ����(��)�� ������Ʈ���� ��ϵǾ�� ���������� ����˴ϴ�.\n�̾ ǥ�õǴ� ��ȭâ�� ������ ��ȣ�� �Է��Ͽ� ��ġ�� ������ �Ͻʽÿ�.";
	 Loader1.ViewerTitle = "���̺��� ���";
	 Loader1.ChartTitle = "��Ʈ ���̺귯��";
	 Loader1.RegisterThis = false;
	 
	 /* Local test line */
	 //var ret = window.Loader1.LoadEx("http://localhost:8080/report/Biin");
	 //var ret = window.Loader1.LoadEx("http://kt68aqua.magicn.com:80/report/Biin");
	 
	 /* Real server line */
	 var ret = window.Loader1.LoadEx("http://221.148.242.7/report/Biin");
	 
	 if(ret == 1){
		 alert("���������� ��ġ �Ǿ����ϴ�.");
	 }	  
}

/* ���̺����� ���� ȭ�鿡�� �Ʒ��� �Լ��� ȣ���Ͽ� ȭ�鿡 ��� �Ѵ�.   */
/* �������� ��� MyBuilder ����(DB ������ Config.inf) */
function mySet(obj)
{   
	//Local test line - protected line
	//obj.View1.SetEnv("\\Program Files\\MyBuilder\\","http://kt68aqua.magicn.com/report/Files/", "");
	//obj.View1.SetEnv("\\Program Files\\MyBuilder\\","http://localhost:8080/report/Files/", "");
	 
	//Real server execution line - protected line
	obj.View1.SetEnv("\\Program Files\\MyBuilder\\","http://221.148.242.7/report/Files/", "(Server)");
    obj.View1.FileName = obj.file_name.value;//fileName;
    obj.View1.Embed = true;
    obj.View1.ToolBar = false;  		
	obj.View1.StatusBar = false;
}

/* ���̺����� ȭ�鿡 ��ȭ���� */
function mySet_Embed(obj, val)
{   
    obj.View1.Embed = val;
}

/* ���̺��� ��� ������ ȭ�� ǥ�ÿ��� */
function mySet_Toolbar(obj, val)
{
    obj.View1.ToolBar = val;
}    

/* ���̺��� ���¹� ǥ�� ���� */    
function mySet_Status(obj, val)
{
	obj.View1.StatusBar = val;
}		

/* ���̺��� �⺻Ű���� ��� ���� */
function mySet_Command(obj)
{
    //��밡���� ���
    //INSERT:�߰�, MODIFY:����, DELETE:����, UPDATE:Ȯ��,
    //BUILD:�ۼ�, COPY:����, PASTE:���̱�,
    //PREVIEW:�̸�����, PRINT:�μ�, MAIL:���Ϻ�����, EXCEL:������ ������,
    //FIRST:ù��, LAST:����, PREV:������, NEXT:������,
    //FIT:��ü����, NORMAL:ǥ�غ���, ZOOMIN:Ȯ��, ZOOMOUT: ���
    //USER1~9:���������
    obj.View1.Command(cmd.value);
    obj.View1.Command(FIT);
}	

