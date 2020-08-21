<%@page import="action.NametagDelete"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.io.IOException"%>
<%@page import="action.NametagOutput"%>
<%@page import="action.NametagInput"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");

	//안드로이드에서 보내준 파라미터를 받는다
	String type = request.getParameter("type");
	String key = request.getParameter("key");
	
	if( type == null ){
		return;
	}
	if( type.equals("type_submit") ){
	    //이미지를 저장할 경로 입력.
	    //String folderTypePath = "D:/android_jsp_0713/img";
	    String folderTypePath = application.getRealPath("/img");
	    String name = new String();
	    String fileName = new String();
	    System.out.println(folderTypePath);
	    int sizeLimit = 5 * 1024 * 1024; // 5메가까지 제한 넘어서면 예외발생
	    try {
	    	MultipartRequest multi = new MultipartRequest(request, folderTypePath, sizeLimit, new DefaultFileRenamePolicy());
	    	Enumeration files = multi.getFileNames();

	        //파일 정보가 있다면
	        if (files.hasMoreElements()) {
	            name = (String) files.nextElement();
	            fileName = multi.getFilesystemName(name);
	        }
	        else{
	        	System.out.println("파일이 존재하지 않습니다");
	        }
	        System.out.println("이미지를 저장하였습니다. : " + fileName);
	    } catch (IOException e) {
			e.printStackTrace();
	    }
		
		NametagInput name_input = NametagInput.getWriter();
		String res = name_input.write( key , fileName); 
		out.println(res);		
	}
	
	else if( type.equals("type_load") ){
		NametagOutput name_output = NametagOutput.getReader();
		String res = name_output.read(key);
		System.out.println(res);
		out.println(res);		
	}
	
	else if( type.equals("type_delete") ){
		NametagDelete name_delete = NametagDelete.getDeleter();
		String res = name_delete.delete(key);
		System.out.println(res);
		out.println(res);		
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>