/**
 * 
 */

$(document).ready(function() {
	
	$(function() {
		loadEmployees();
//		$("#buttonDeleteEmployee").prop("disabled", true);
//		$("#buttonEditEmployee").prop("disabled", true);
	});
	
	$("#buttonAddEmployee").click(function(event) {
		event.preventDefault();
		var jsonParams = {};
		jsonParams['name'] = $("#fieldEmployeeName").val();
		jsonParams['role'] = $("#fieldEmployeeRole").val();
		$("#buttonAddEmployee").prop("disabled", true);		
		$.ajax({
			type: "POST",
			cache: false,
			url: "/employees/addEmployee",
			data: JSON.stringify(jsonParams),
			dataType: 'json',
			contentType: "application/json"
		}).done(function() {
			loadEmployees();
		}).fail(function() {
			alert("Failed");
		}).always(function() {
			cleanForm();
			$("#buttonAddEmployee").prop("disabled", false);
		});
	});
	
	$("#buttonEditEmployee").click(function(event) {
		event.preventDefault();		
		var id = $("#fieldEmployeeId").val();
		var jsonParams = {};
		jsonParams['name'] = $("#fieldEmployeeName").val();
		jsonParams['role'] = $("#fieldEmployeeRole").val();
		$("#buttonEditEmployee").prop("disabled", true);	
		$.ajax({
			type: "PUT",
			url: "/employees/updateEmployee/" + id,
			data: JSON.stringify(jsonParams),
			dataType: 'json',
			contentType: "application/json"
		}).done(function() {
			loadEmployees();
		}).fail(function() {
			alert("Failed");
		}).always(function() {
			cleanForm();
			$("#buttonEditEmployee").prop("disabled", false);
		});
	});

	$("#buttonDeleteEmployee").click(function(event) {
		event.preventDefault();		
		var id = $("#fieldEmployeeId").val();
		$("#buttonDeleteEmployee").prop("disabled", true);
		if (confirm('Are you sure you want to delete this entry?')) {
			$.ajax({
			    type : "DELETE",
			    url : "/employees/delete/" + id
			}).done(function() {
				loadEmployees();
			}).fail(function() {
				alert("Failed");
			}).always(function() {
				cleanForm();
				$("#buttonDeleteEmployee").prop("disabled", false);
			});
		}
	});
	
	$("#buttonRefreshEmployees").click(function(event) {
		event.preventDefault();
		cleanForm();
		if($("#selectRole option:selected").val() == "all"){
			loadEmployees();
		}else{
			var selectedRole = $("#selectRole option:selected").val();
			loadEmployeesByRole(selectedRole);
		}
	});
	
});

function loadEmployees() {
	$.get("/employees", function(data) {
		printTable(data);
	});
}

function loadEmployeesByRole(role) {
	$.ajax({
		type: "GET",
		url: "/employees/role/" + role
	}).done(function(data) {
		printTable(data);
	}).fail(function() {
		alert("Failed");
	});
}

function loadEmployee(id) {
	$.ajax({
		type: "GET",
		url: "/employees/" + id
	}).done(function(data) {
		$("#fieldEmployeeId").val(data.id);
		$("#fieldEmployeeName").val(data.name);
		$("#fieldEmployeeRole").val(data.role);
		$("#fieldEmployeeSalary").val(data.salary);
	}).fail(function() {
		alert("Failed");
	}).always(function() {
		$("#buttonAddEmployee").prop("disabled", true);
		$("#buttonEditEmployee").prop("disabled", false);
		$("#buttonDeleteEmployee").prop("disabled", false);
	});
}

function printTable(data) {
	$('#employeeData > tbody').empty(); 
	$.each(data, function(i, employee){
		var tblRow = "<tr>" + 
						"<td>" + employee.id + "</td>" +
						"<td>" + employee.name + "</td>" +
						"<td>" + employee.role + "</td>" + 
						"<td>" + employee.salary + "</td>" + 
						"<td> <a href='#' onclick='loadEmployee(" + employee.id + ")'>Select</a> </td>" + 
					 "</tr>"
		$(tblRow).appendTo("#employeeData tbody");
	});
}

function cleanForm() {
	$("#fieldEmployeeId").val("");
	$("#fieldEmployeeName").val("");
	$("#fieldEmployeeRole").val("default");
	$("#fieldEmployeeSalary").val("");
	$("#buttonAddEmployee").prop("disabled", false);
	$("#buttonEditEmployee").prop("disabled", true);
	$("#buttonDeleteEmployee").prop("disabled", true);
}