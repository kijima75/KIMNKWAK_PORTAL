<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<script>




function initPage(){
	console.log("=== initPage");
	//btnLoad
	
	/* $("#btnLoad, .exportDsv_close").click(function(){
		console.log("btnLoad 01 : ");
	}); */
	
	//DSV Export Button
    $('#btnLoad').button().click(function(){
    	console.log("btnLoad 02 : start");
    	fnLoadData();
    	console.log("btnLoad 02 : end");
	});
	
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
			
				<button type="button" class="btn btn-success" id="btnLoad">load</button>
			
				<div id='calendar'></div>
			</div>
		</div>
	</div>
	
</div>

	
