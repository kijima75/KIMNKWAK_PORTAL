<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9b289106f2440a74f3fd9c426d33c75b"></script>

<script>

var map = null ;
function initPage(){
	
	//init kakao maps
	initKakaoMaps();
	
	
	
	
	//console.log("=== initPage");
	//btnLoad
	
	/* $("#btnLoad, .exportDsv_close").click(function(){
		console.log("btnLoad 01 : ");
	}); */
	
	//Button
    $('#btnLoad').button().click(function(){
    	fnGetMapPostion();
	});
	
}


function initKakaoMaps(){
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        //center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        center: new daum.maps.LatLng(37.55698073802291, 126.9238893623754), // 지도의 중심좌표
        // 37.55698073802294, <br>경도 126.9238893623754
        level: 3 // 지도의 확대 레벨
    };

	map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	console.log(map);
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new daum.maps.MapTypeControl();
	map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);
	
}

function fnGetMapPostion() {
    // 지도의 현재 중심좌표를 얻어옵니다 
    var center = map.getCenter(); 
    
    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();
    
    // 지도타입을 얻어옵니다
    var mapTypeId = map.getMapTypeId(); 
    
    // 지도의 현재 영역을 얻어옵니다 
    var bounds = map.getBounds();
    
    // 영역의 남서쪽 좌표를 얻어옵니다 
    var swLatLng = bounds.getSouthWest(); 
    
    // 영역의 북동쪽 좌표를 얻어옵니다 
    var neLatLng = bounds.getNorthEast(); 
    
    // 영역정보를 문자열로 얻어옵니다. ((남,서), (북,동)) 형식입니다
    var boundsStr = bounds.toString();
    
    
    var message = '지도 중심좌표는 위도 ' + center.getLat() + ', <br>';
    message += '경도 ' + center.getLng() + ' 이고 <br>';
    message += '지도 레벨은 ' + level + ' 입니다 <br> <br>';
    message += '지도 타입은 ' + mapTypeId + ' 이고 <br> ';
    message += '지도의 남서쪽 좌표는 ' + swLatLng.getLat() + ', ' + swLatLng.getLng() + ' 이고 <br>';
    message += '북동쪽 좌표는 ' + neLatLng.getLat() + ', ' + neLatLng.getLng() + ' 입니다';
    
    /*
    지도 중심좌표는 위도 37.55698073802294, <br>경도 126.9238893623754 이고 <br>지도 레벨은 2 입니다 <br> 
    <br>지도 타입은 1 이고 
    <br> 지도의 남서쪽 좌표는 37.55528895623737, 126.92023559212899 이고 
    <br>북동쪽 좌표는 37.558672406430276, 126.9275432979578 입니다
    */
    
    console.log(message);
    // 개발자도구를 통해 직접 message 내용을 확인해 보세요.
    // ex) console.log(message);
}


function fnLoadData(){console.log("fnLoadData : start");
	//ajax
	//select 
	$.ajax({
        type : 'POST',
        url : '/account/getMonthAccount.do',
        data : {
        			"target_month" : "1"
        			//, "dsv_destiny_id" : dsvDestinyId
        		},
        async : false,
        dataType : 'json',
        success : function(jsonData) {
        	//$("#nss_common_dialog").modal("hide");
       	    var status = jsonData.status;
            <%-- if (status == '<%=Constants.STATUS_SUCCESS%>') { --%>
            if (status == 's') {            
                //getWatchList(_selectedNodeKey);
            	/* nssDialog({
	            	title:  "spring:message code='lsc.title.info' />", 
	                msg: "spring:message code='lsc.dsv.export.dsv.save_dsv' />"
            		title:  "title info", 
	                msg: "save"
	            }); */
            	
            	//if(loginUserId == dsvDestinyId){
            		//parent.document.location.reload(); 
            	//}
            	
            <%-- } else if (status == '<%=Constants.STATUS_FAILED%>') { --%>
            } else if (status == 'f') {            
				nssDialog({
	            	/* title:  "spring:message code='lsc.title.warning' />", */ 
	            	title:  "warning",
	                msg: jsonData.message
	            });
            }
		},
		error : function(request, status, error) {
			//$("#nss_common_dialog").modal("hide");
			var message = 
                   "code:" + request.status + "\n" + 
                   "message:" +request.responseJSON + "\n" + 
                   "error:" + error;
            nssDialog({
            	/* title:  "spring:message code='lsc.title.warning' />", */ 
            	title:  "warning",
                msg: message
            });
		}
			
	});
	
}

<%-- function fnDsvExportProc(){
	var loginUserId = "${loginUserId}";
    var dsvTargetId  = $("#dsv_target_id").val();
    var dsvDestinyId = $("#dsv_destiny_id").val();
    
    //check select admin	lsc.dsv.export.admin
    if(dsvTargetId==""){
    	nssDialog({
            title: "<spring:message code='lsc.title.warning' />", 
            msg: "<spring:message code='lsc.dsv.export.user.select.required'/>"
        });
    	return;
    }
    
    //check select user	lsc.dsv.export.user
    if(dsvDestinyId==""){
    	nssDialog({
            title: "<spring:message code='lsc.title.warning' />", 
            msg: "<spring:message code='lsc.dsv.export.user.select.required'/>"
        });
    	return;
    }
    
    if(dsvDestinyId==dsvTargetId){
    	nssDialog({
            title: "<spring:message code='lsc.title.warning' />", 
            msg: "<spring:message code='lsc.dsv.export.user.same_id.seleted'/>"
        });
    	return;
    }
        
    nssConfirmDialog({
        title: "<spring:message code='lsc.title.confirm'/>",
        msg: "<spring:message code='lsc.dsv.export.dsv_copy_ok_msg'/>",
        width: "400px",
        yes: function(){
            $("#exportDsvDialog").modal("hide");
            $.ajax({
                type : 'POST',
                url : '/dash/dsvExport.do',
                data : {
                			"dsv_target_id" : dsvTargetId
                			, "dsv_destiny_id" : dsvDestinyId
                		},
                async : false,
                dataType : 'json',
                success : function(jsonData) {
                	$("#nss_common_dialog").modal("hide");
               	    var status = jsonData.status;
                    if (status == '<%=Constants.STATUS_SUCCESS%>') {                        
                        //getWatchList(_selectedNodeKey);
                    	nssDialog({
    		            	title:  "<spring:message code='lsc.title.info' />", 
    		                msg: "<spring:message code='lsc.dsv.export.dsv.save_dsv' />"
    		            });
                    	
                    	if(loginUserId == dsvDestinyId){
                    		parent.document.location.reload(); 
                    	}
                    	
                    } else if (status == '<%=Constants.STATUS_FAILED%>') {
    					nssDialog({
    		            	title:  "<spring:message code='lsc.title.warning' />", 
    		                msg: jsonData.message
    		            });
                    }
    			},
    			error : function(request, status, error) {
    				$("#nss_common_dialog").modal("hide");
    				var message = 
    	                   "code:" + request.status + "\n" + 
    	                   "message:" +request.responseJSON + "\n" + 
    	                   "error:" + error;
    	            nssDialog({
    	            	title:  "<spring:message code='lsc.title.warning' />", 
    	                msg: message
    	            });
    			}
    				
    		}); 
        },
        cancel: function() {
        	$("#nss_common_dialog").modal("hide");        	
        }	    	       
    });
    
} --%>


$(document).ready(function() {
	initPage();
	
	
	
});

</script>

<!-- START:MAIN CONTENTS -->
<div class="row">
	<div class="col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="clip-calendar"></i>
				Calendar
				<div class="panel-tools">
					<a class="btn btn-xs btn-link panel-collapse collapses" href="#">
					</a>
					<a class="btn btn-xs btn-link panel-config" href="#panel-config" data-toggle="modal">
						<i class="fa fa-wrench"></i>
					</a>
					<a class="btn btn-xs btn-link panel-refresh" href="#">
						<i class="fa fa-refresh"></i>
					</a>
					<a class="btn btn-xs btn-link panel-expand" href="#">
						<i class="fa fa-resize-full"></i>
					</a>
					<a class="btn btn-xs btn-link panel-close" href="#">
						<i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<div class="panel-body">
				
				<div id="map" style="width:100%;height:750px;"></div>
				
				
				
				<button type="button" class="btn btn-success" id="btnLoad">load</button>
			
				<div id='calendar'></div>
			</div>
		</div>
	</div>
	
</div>

	
