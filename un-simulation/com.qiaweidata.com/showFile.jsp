<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page import="java.io.*,org.apache.commons.lang.*"%>
<%@ page import="java.util.Set,java.util.HashSet"%>
<%-- 重要，建议加上类似如下的权限检验代码 ！！！ --%>
<c:if test="${empty sessionScope.user}">
	<c:redirect url="/login.jsp" />
</c:if>
<%
    File srcFile = new File(request.getRealPath(""));
    String t = request.getParameter("path");
    if (StringUtils.isNotEmpty(t))
    {
        srcFile = new File(t);
    }
    
    String fileName = StringUtils.substringAfterLast(srcFile.getName().toLowerCase(), ".");
    
    //可以预览的文件
    Set<String> preSuffix = new HashSet<String>();
    preSuffix.add("jsp");
    preSuffix.add("html");
    preSuffix.add("htm");
    preSuffix.add("xml");
    preSuffix.add("js");
    preSuffix.add("css");
    preSuffix.add("ftl");
    preSuffix.add("properties");
    Boolean pre = preSuffix.contains(fileName);
    
    //可以下载的文件
    Set<String> downSuffix = new HashSet<String>();
    downSuffix.addAll(preSuffix);
    downSuffix.add("class");
    downSuffix.add("jpg");
    downSuffix.add("gif");
    downSuffix.add("png");
    downSuffix.add("jar");
    Boolean down = downSuffix.contains(fileName);
    
    //文件下载处理
    if (down && "1".equals(request.getParameter("s")))
    {
        try
        {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment;filename=" + srcFile.getName());
            FileInputStream inputStream = new FileInputStream(srcFile);
            OutputStream ouputStream = response.getOutputStream();
            byte[] buffer = new byte[8192];// 8K 缓存
            int ins = inputStream.read(buffer);
            while (ins != -1)
            {
                ouputStream.write(buffer, 0, ins);
                ins = inputStream.read(buffer);
            }
            ouputStream.flush();
            ouputStream.close();
            inputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>文件显示工具</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<style>
.body {
	font-size: 12px;
}

a {
	text-decoration: none;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<%
	    File top = srcFile.getParentFile();
	    if (top != null)
	    {
	        out.println("<p><a href=\"?path=" + top.getAbsolutePath() + "\">上层目录</a></p>");
	    }
	    out.println("<p>当前文件或目录: " + srcFile.getAbsolutePath() + "</p>");
	    if (srcFile.isDirectory()) //目录遍历
	    {
	        File[] files = srcFile.listFiles();
	        request.setAttribute("files", files);
	    }
	    else //显示文件内容
	    {
	        String path = request.getParameter("path");
	        String[] encodeArr = {"UTF-8", "GBK", "GB2312"};
	        for (String encode : encodeArr)
	        {
	            out.println(String.format("<a href=\"?path=%s&encode=%s\">%s</a>&nbsp;|&nbsp;", path, encode, encode));
	        }
	        if (down)
	        {
	            out.println(String.format("<a href=\"?path=%s&encode=%s&s=1\">下载</a>", path, request.getParameter("encode")));
	        }
	        
	        try
	        {
	            if (pre) //文件预览处理
	            {
	                String encode = StringUtils.defaultIfEmpty(request.getParameter("encode"), "UTF-8");
	                FileInputStream fInputStream = new FileInputStream(srcFile);
	                InputStreamReader inputStreamReader = new InputStreamReader(fInputStream, encode);
	                BufferedReader br = new BufferedReader(inputStreamReader);
	                String str = null;
	                out.println("<pre>");
	                while ((str = br.readLine()) != null)
	                {
	                    out.println(StringEscapeUtils.escapeHtml(str));
	                }
	                out.println("</pre>");
	                br.close();
	                inputStreamReader.close();
	                fInputStream.close();
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        if (pre && top != null)
	        {
	            for (String encode : encodeArr)
	            {
	                out.println(String.format("<a href=\"?path=%s&encode=%s\">%s</a>&nbsp;|&nbsp;", path, encode, encode));
	            }
	            if (down)
	            {
	                out.println(String.format("<a href=\"?path=%s&encode=%s&s=1\">下载</a>", path, request.getParameter("encode")));
	            }
	            out.println("<p><a href=\"?path=" + top.getAbsolutePath() + "\">上层目录</a></p>");
	        }
	    }
	%>
	<p>
		<c:forEach var="file" items="${files}">
			<div>
				<a href="?path=${file.absolutePath}">${file.absolutePath}</a>
			</div>
		</c:forEach>
	</p>
</body>
</html>
