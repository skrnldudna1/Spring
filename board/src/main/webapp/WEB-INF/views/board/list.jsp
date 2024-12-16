<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<%@include file="../includes/header.jsp"%>

<!-- 게시물 번호 확인 -->
<script>
	$(document).ready(function() {
		var result = '${result}';
	});
</script>


<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Tables</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				Board List Page
				<button id='regBtn' type="button"
					class="btn btn-xs pull-right btn-info">Register New Board</button>
			</div>

			<!-- /.panel-heading -->
			<div class="panel-body">
				<table width="100%"
					class="table table-striped table-bordered table-hover"
					id="dataTables-example">

					<!-- Model에 담긴 데이터 출력 -->
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>수정일</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="board">
						<tr class="odd gradeX">
							<td><c:out value="${board.bno}" /></td>
							<td><a href='/board/get?bno=${board.bno}'> <c:out
										value="${board.title}" /></a></td>
							<td>${board.writer}</td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.regDate}" /></td>
							<td><fmt:formatDate pattern="yyyy-MM-dd"
									value="${board.updateDate}" /></td>
						</tr>
					</c:forEach>
				</table>





				<!-- 	검색조건 -->
				<div class='row'>
					<div class="col-lg-12">
						<!-- board/list 경로에 전달하여 서버에서 검색 결과를 처리 -->
						<form id='searchForm' action="/board/list" method='get'>
							<select name='type'>
								<option value=""
									<c:out value= "${pageMaker.cri.type == null?'selected':''}"/>>--</option>
								<option value="T"
									<c:out value= "${pageMaker.cri.type eq 'T'?'selected':''}"/>>제목</option>
								<option value="C"
									<c:out value= "${pageMaker.cri.type eq 'C'?'selected':''}"/>>내용</option>
								<option value="W"
									<c:out value= "${pageMaker.cri.type eq 'W'?'selected':''}"/>>작성자</option>
								<option value="TC"
									<c:out value= "${pageMaker.cri.type eq 'TC'?'selected':''}"/>>제목or
									내용</option>
								<option value="TW"
									<c:out value= "${pageMaker.cri.type eq 'TW'?'selected':''}"/>>제목or
									작성자</option>
								<option value="TWC"
									<c:out value="${pageMaker.cri.type eq 'TWC'?'selected':''}"/>>제목or
									내용 or 작성자</option>


							</select>
							<!-- 사용자가 검색할 키워드 입력 -->
							<input type='text' name='keyword'
								value='<c:out value="${pageMaker.cri.keyword}"/> ' />
							<!-- 현재 페이지 번호를 숨긴 상태로 서버에 전달 -->
							<input type='hidden' name='pageNum'
								value='<c:out value="${pageMaker.cri.pageNum}"/> ' />
							<!-- 페이지당 표시할 항목 수를 숨긴 상태로 서버에 전달 -->
							<input type='hidden' name='amount'
								value='<c:out value="${pageMaker.cri.amount}"/> ' />
							<!-- 폼 제출 후 검색 요청을 서버로 보냄 -->
							<button class='btn btn-default'>Search</button>

						</form>
					</div>

					<!-- end 검색조건 -->


					<div class="pull-right">
						<ul class="pagination">
							<c:if test="${pageMaker.prev}">
								<li class="paginate_button previous"><a
									href="${pageMaker.startPage - 1}">Previous</a></li>
							</c:if>

							<c:forEach var="num" begin="${pageMaker.startPage}"
								end="${pageMaker.endPage}">
								<li
									class="paginate_button ${pageMaker.cri.pageNum == num ? 'active' : ''}">
									<a href="${num}">${num}</a>
								</li>
							</c:forEach>

							<c:if test="${pageMaker.next}">
								<li class="paginate_button next"><a
									href="${pageMaker.endPage + 1}">Next</a></li>
							</c:if>

						</ul>
					</div>
					<!-- end Pagination -->
				</div>

				<!-- 목록 페이지에서 제목 클릭시 글번호로 읽음 -->
				<td><a href='/board/get?bno=${board.bno}"/>'> </a></td>

				</table>


				<!-- The Modal -->
				<div class="modal" id="myModal">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">Modal Heading</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>
							<!-- Modal body -->
							<div class="modal-body">처리가 완료되었습니다.</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-danger"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
					<!-- end Modal -->
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-6 -->
</div>
<!-- /.row -->
<form id='actionForm' action="/board/list" method='get'>
	<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
	<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
	<input type='hidden' name='type' value='${pageMaker.cri.type}'>
	<input type='hidden' name='keyword' value='${pageMaker.cri.keyword}'>
</form>
<!-- 게시물 번호 확인 -->
<script>
	$(document).ready(
			function() {
				var result = '${result}';
				checkModal(result);

				history.replaceState({}, null, null); // 뒤로가기 모달창을 보여준 뒤에는 더 이상 모달창이 필요하지 않음

				function checkModal(result) {
					if (result === '' || history.state) {
						return;
					} else {
						if (parseInt(result) > 0) {
							$(".modal-body").html(
									"게시글 " + parseInt(result) + "번이 등록되었습니다.");
						}

						$("#myModal").modal("show");
					}
				}

				$("#regBtn").on("click", function() {
					self.location = "/board/register";
				})

				var actionForm = $("#actionForm");
				$(".paginate_button a").on(
						"click",
						function(e) {
							e.preventDefault();
							console.log('click');
							actionForm.find("input[name='pageNum']").val(
									$(this).attr("href"));
							actionForm.submit();
						});

				var searchForm = $("#searchForm");

				$("#searchForm button").on("click", function(e) {
					if (!searchForm.find("option:selected").val()) {
						alert("검색 종류를 선택하세요.");
						return false;
					}
					if (!searchForm.find("input[name='keyword']").val()) {
						alert("키워드를 입력하세요.");
						return false;
					}
					searchForm.find("input[name='pageNum']").val("1");
					e.preventDefault();

					searchForm.submit();

				});
			});
</script>

<%@include file="../includes/footer.jsp"%>