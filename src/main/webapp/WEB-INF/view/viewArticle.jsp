<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글보기</title>
<style type="text/css">
#tr_btn_modify {
	display: none;
}
@font-face {
	font-family: 'LeeSeoyun';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2202-2@1.0/LeeSeoyun.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'LeeSeoyun';
}

.cls1 {
	text-decoration: none;
}

.cls2 {
	text-align: center;
	font-size: 30px;
	display: block;
}

a:hover {
	color: #153acf;
}

h1{
  position: relative;
  animation: mymove infinite;
  animation-duration: 3s;
  color: blue;
  animation-fill-mode: forwards;
}

@keyframes mymove {
  from {top: 0px;}
  to {top: 20px; color: blue;}
}
button:hover{
	background-color: #c0d9fc;
}
</style>
<script type="text/javascript">
	function backToList(frm) {
		frm.action="${contextPath}/board/listArticles";
		frm.submit();
	}
	
	function fn_enable() {
		document.querySelector("#i_title").disabled = false;
		document.querySelector("#i_content").disabled = false;
		document.querySelector("#tr_btn_modify").style.display = "table-row";
		document.querySelector("#tr_btn").style.display = "none";
	}
	
	function fn_modify_article(frm) {
		frm.action = "${contextPath}/board/editArticle";
		frm.submit();
	}
	
	/* function fn_remove_article(frm) {
		frm.action = "${contextPath}/board/removeArticle";
		frm.submit();
	} */
	
	function fn_remove_article(url, articleNo) {
		let form = document.createElement("form");
		form.setAttribute("method", "POST");
		form.setAttribute("action", url);
		let articleNoInput = document.createElement("input");
		articleNoInput.setAttribute("type", "hidden");
		articleNoInput.setAttribute("name", "articleNo");
		articleNoInput.setAttribute("value", articleNo);
		
		form.appendChild(articleNoInput);
		document.body.appendChild(form);
		form.submit();
	}
		
</script>
</head>
<body>
<h1 style="text-align: center; color:#c0d9fc;">새 글 쓰기</h1>
	<form name="frmArticle" method="post" action="">
		<table border="0" align="center">
			<tbody>
				<tr>
					<td width="150" align="center" bgcolor="#c0d9fc">글번호</td>
					<td><input name="articleNo" type="text"
						value="${article.articleNo}" disabled /> <input type="hidden"
						name="articleNo" value="${article.articleNo}" /></td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="#c0d9fc">작성자ID</td>
					<td><input name="writer" type="text"
						value="${article.writeId}" disabled /></td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="#c0d9fc">제목</td>
					<td><input name="title" type="text" value="${article.title}"
						id="i_title" disabled /></td>
				</tr>
				<tr>
					<td width="150" align="center" bgcolor="#c0d9fc">내용</td>
					<td><textarea name="content" rows="20" cols="60"
							id="i_content" disabled>${article.contents}</textarea></td>
				</tr>
				<tr>
					<td width="20%" align="center" bgcolor="#c0d9fc">등록일자</td>
					<td><input type="text" value="${article.writeDate}" disabled />
					</td>
				</tr>
				<tr id="tr_btn_modify">
					<td colspan="2" align="center"><input type="button"
						value="수정반영하기" onclick="fn_modify_article(frmArticle)" /> <input
						type="button" value="취소" onclick="backToList(frmArticle)" /></td>
				</tr>
				<tr id="tr_btn">
					<td colspan="2" align="center">
						<input type="button" value="수정하기" onclick="fn_enable()" /> 
						<input type="button" value="삭제하기" onclick="fn_remove_article('${contextPath}/board/removeArticle',
						${article.articleNo})" />
						<!-- <input type="button" value="삭제하기" onclick="fn_remove_article(frmArticle)" />  -->
						<input type="button" value="리스트로 돌아가기" onclick="backToList(frmArticle)" />
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>