package com.edsk.framework.session;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import kr.co.kame.ssg.Defines;
//import kr.co.kame.ssg.code.service.CodeService;
//import kr.co.kame.ssg.dept.service.DepartmentService;
//import kr.co.kame.ssg.hr.dao.EmployeeDT;
//import kr.co.kame.ssg.hr.service.EmployeeService;
//import kr.co.kame.ssg.hr.service.NoSuchEmployeeException;


/**
 * UserInfoSessionBinder
 * 
 * @author mksong
 */
public class UserInfoSessionBinder implements SessionBinder {
    private static Log log = LogFactory.getLog(UserInfoSessionBinder.class);

    public Object getSessionObject(HttpServletRequest request, Locale locale) {
        log.info("Binding UserInfoSession userId="+request.getRemoteUser());

        //EmployeeService employeeService=EmployeeService.getInstance(locale);
        //CodeService codeService=CodeService.getInstance(locale);
        //DepartmentService deptService=DepartmentService.getInstance(locale);
        UserInfoSession userInfo=null;
        try {
            //EmployeeDT dt=employeeService.getEmployee(request.getRemoteUser());
            //String titleName=codeService.getSubcodeName(Defines.CODE_TITLE,dt.getTitle());
            //String deptName=deptService.getDepartmentName(dt.getDept());
            //userInfo=new UserInfoSession(dt.getEmpNo(),dt.getName(),
            //        dt.getTitle(),titleName,dt.getDept(),deptName);
            //log.info("userName="+userInfo.getUserName());
            //log.info("titleCode="+userInfo.getTitleCode());
            //log.info("titleName="+userInfo.getTitleName());
            //log.info("deptCode="+userInfo.getDeptCode());
            //log.info("deptName="+userInfo.getDeptName());
        } catch (Exception e) {
            log.error("No such employee",e);
        }
        return userInfo;
    }
}
