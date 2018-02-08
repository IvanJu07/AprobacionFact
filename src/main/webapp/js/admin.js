/*

CAMBIAR http://localhost:8080  POR  https://backend-dot-aprobacion-de-facturas.appspot.com
*/



$("#datos").click(function()
{
	window.location.href = 'datos.html'
});


$("#usuarios").click(function()
{
	usuarios();
	$.magnificPopup.open({
	        closeOnBgClick: false,
	        closeOnContentClick:false,
	        showCloseBtn: false,
	        enableEscapeKey: false,
	        items:{
	        src:'#popupUsuarios',
	        type:'inline',
	        }
	    });
});
$("#carga").click(function()
{
	$.magnificPopup.open({
	        closeOnBgClick: false,
	        closeOnContentClick:false,
	        showCloseBtn: false,
	        enableEscapeKey: true,
	        items:{
	        src:'#popupCarga',
	        type:'inline',
	        }
	    });
});
$("#salir").click(function()
{
	$.magnificPopup.close();
});
$("#load").click(function()
{
	if($("#selectmes").val()=='0')
		alertmensaje('Aviso','Elija un periodo')
	else
	{
		$.magnificPopup.close();
		carga()
		
	}
});


function usuarios(){
	$("#nuevo").removeClass('hidden')
	$('#tableUsers').empty();
	$("#gifload").removeClass( "hidden" );
	
	
	$.get( "http://localhost:8080/ServletGetUsers",function(result)
	{
		
		var table = "";
		var datos = $.parseJSON(result);
		for (var i = 0; i < datos.length; i++) 
		{
			table += '<tr class = "fila">';
			table += '<th scope="row" class = "email">'+datos[i]['email']+'</th>';
			table += '<th scope="row" class = "emailprovee">'+datos[i]['correoproveedor']+'</th>';
			if(datos[i]['perfil'] == '1')
			   table += '<td class = "admin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="fas fa-check-square letraazul handCursor check" style="font-size: 30px" ></i></td>';
			else	
			   table += '<td class = "admin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="far fa-check-square handCursor check" style="font-size: 30px" ></i></td>';
		    if(datos[i]['perfil'] == '2')
			   table += '<td class = "provee">&nbsp;&nbsp;&nbsp;&nbsp;<i class="fas fa-check-square letraazul handCursor" style="font-size: 30px"></i></td>'
			else
				table += '<td class = "provee">&nbsp;&nbsp;&nbsp;&nbsp;<i class="far fa-check-square handCursor" style="font-size: 30px"></i></td>'
			if(datos[i]['perfil'] == '3')
				table += '<td class = "pmo">&nbsp;<i class="fas fa-check-square handCursor letraazul" style="font-size: 30px"></i></td>';
			else
				table += '<td class = "pmo">&nbsp;<i class="far fa-check-square handCursor" style="font-size: 30px"></i></td>';
			table += '<td class = "borrar" >&nbsp;&nbsp;<i  class="fas fa-trash-alt handCursor" style="font-size: 27px"></i></td>';
			table += '</tr>'
		}
    	$('#tableUsers').append(table);
		
		ckeck();
		updateProvee();
	});
}

function ckeck()
{
	$("#gifload").addClass( "hidden" );
	
	$(".admin").click(function()
	{
		
		$("#gifload").removeClass( "hidden" );
		$(".fila").removeClass( "select" );
		$(this).parent().addClass( "select" );
		var email = $("#tableUsers").find(".select").find(".email").text();
		var admin = $("#tableUsers").find(".select").find(".admin").children();
		var provee = $("#tableUsers").find(".select").find(".provee").children();
		var pmo = $("#tableUsers").find(".select").find(".pmo").children();
		if(!admin.hasClass('letraazul'))
		{
			admin.addClass('fas letraazul')
			admin.removeClass('far')
			provee.removeClass('fas letraazul');
			pmo.removeClass('fas letraazul');
			provee.addClass('far')
			pmo.addClass('far')
			var item = {email: email,
					   	perfil:1}
			$.post( "http://localhost:8080/ServletUpdateServicio",item,function(result)
			{}).done(function(result) {
	 			$("#gifload").addClass( "hidden" );
	 		});
		}
	});
	$(".provee").click(function()
	{
		$("#gifload").removeClass( "hidden" );
		$(".fila").removeClass( "select" );
		$(this).parent().addClass( "select" );
		var email = $("#tableUsers").find(".select").find(".email").text();
		var admin = $("#tableUsers").find(".select").find(".admin").children();
		var provee = $("#tableUsers").find(".select").find(".provee").children();
		var pmo = $("#tableUsers").find(".select").find(".pmo").children();
		if(!provee.hasClass('letraazul'))
		{
			provee.addClass('fas letraazul')
			provee.removeClass('far')
			admin.removeClass('fas letraazul');
			admin.addClass('far')
			pmo.removeClass('fas letraazul');
			pmo.addClass('far')
			var item = {email: email,
					   	perfil:2}
			$.post( "http://localhost:8080/ServletUpdateServicio",item,function(result)
			{}).done(function(result) {
	 			$("#gifload").addClass( "hidden" );
	 		});
		}	
	});
	$(".pmo").click(function()
	{
		$("#gifload").removeClass( "hidden" );
		$(".fila").removeClass( "select" );
		$(this).parent().addClass( "select" );
		var email = $("#tableUsers").find(".select").find(".email").text();
		var admin = $("#tableUsers").find(".select").find(".admin").children();
		var provee = $("#tableUsers").find(".select").find(".provee").children();
		var pmo = $("#tableUsers").find(".select").find(".pmo").children();
		if(!pmo.hasClass('letraazul'))
		{
			pmo.addClass('fas letraazul')
			pmo.removeClass('far')
			admin.removeClass('fas letraazul');
			admin.addClass('far')
			provee.removeClass('fas letraazul');
			provee.addClass('far')
			var item = {email: email,
					   	perfil:3}
			$.post( "http://localhost:8080/ServletUpdateServicio",item,function(result)
			{}).done(function(result) {
	 			$("#gifload").addClass( "hidden" );
	 		});
		}	
	});
	$(".borrar").click(function()
	{
		$("#gifload").removeClass( "hidden" );
		$(".fila").removeClass( "select" );
		$(this).parent().addClass( "select" )
		var selected = $("#tableUsers").find(".select");
		var email = selected.find(".email").text();
		selected.remove();
		var item = {email: email}
		$.post( "http://localhost:8080/ServletDeleteUsers",item,function(result)
		{}).done(function(result) {
			$("#nuevo").removeClass('hidden')
			$("#gifload").addClass( "hidden" );
		});
	});
}
$("#nuevo").click(function()
{
	
	lista = "";
	lista += '<tr class = "fila">';
	lista += '<th class = "email"><input type = "text" class="form-control col-md-4 letraazultexto inputNuevoEmailnew" ></th>';
	lista += '<th class = "emailprovee"><input type = "text" class="form-control col-md-4 letraazultexto nuevoEmailProvee" ></th>';
	lista += '<td class = "admin">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="far fa-check-square handCursor check" style="font-size: 30px" ></i></td>'; 
	lista += '<td class = "provee">&nbsp;&nbsp;&nbsp;&nbsp;<i class="far fa-check-square handCursor" style="font-size: 30px"></i></td>';
	lista += '<td class = "pmo">&nbsp;<i class="far fa-check-square handCursor" style="font-size: 30px"></i></td>'; 
	lista += '<td class = "borrar" >&nbsp;&nbsp;<i  class="fas fa-trash-alt handCursor" style="font-size: 27px"></i></td>';
	lista += '</tr>';
    $("#tableUsers").append(lista);
	$("#nuevo").addClass('hidden');
	$(".fila").removeClass( "select" );
	$(".inputNuevoEmailnew").parent().parent().addClass( "select" );
	
	$(".inputNuevoEmailnew").blur(function(){
		$("#gifload").removeClass( "hidden" );
		var email = $(this).val();
		$('.select').find('.email').append(email)
		//alert($('.select').find('.inputNuevoEmailnew').val())
		$(this).remove();
		var item = {email: email}
		$.post( "http://localhost:8080/ServletInsertUser",item,function(result)
		{}).done(function(result) {
			$("#nuevo").removeClass('hidden')
			$("#gifload").addClass( "hidden" );
		});
    });
	$(".nuevoEmailProvee").blur(function(){
		$("#gifload").removeClass( "hidden" );
		var email = $(this).val();
		$('.select').find('.emailprovee').append(email)
		//alert($('.select').find('.inputNuevoEmailnew').val())
		$(this).remove();
		var usuario =  $("#tableUsers").find('.select').find('.email').text();
		var item = {email: usuario,
				    emailprovee: email}
		$.post( "http://localhost:8080ServletInsertProvee",item,function(result)
		{}).done(function(result) {
			$("#nuevo").removeClass('hidden')
			$("#gifload").addClass( "hidden" );
		});
    });
	
	
	
	
	ckeck();
});

function carga()
{
	var mes = $("#selectmes").val();
	
	var item = {mes: mes}
	$("#logocargar").show();
	$.magnificPopup.open({
		closeOnBgClick: false,
		closeOnContentClick:false,
		showCloseBtn: false,
		enableEscapeKey: false,
		items:{
		src:'#popup',
		type:'inline',
		}
	});
	$.post( "http://localhost:8080/ServletGetFacturas",item,function(result)
	{	
		var data = $.parseJSON(result);
		var insert = '';
		var longitud = data.length;
		for(var i = 0; i < longitud; i++)
		{
		   var tarifamensual = ((data[i]['tarifamensual'])==null)?'0':data[i]['tarifamensual'];
		   insert = "'"+data[i]['proyecto']+"','"+data[i]['NumeroEmpleado']+"','"+data[i]['TarifaPorHora']+"','"+data[i]['HorasTrabajadas']+"','"+data[i]['Importe']+"','"+data[i]['NombreEmpleado']+"','"+data[i]['correoPM']+"','"+data[i]['correoFuncionario']+"','"+data[i]['NoDocumento']+"','"+data[i]['Proveedor']+"','"+data[i]['Subproyecto']+"','"+data[i]['ClavePresupuestal']+"','"+data[i]['PeriodoServicio']+"','"+tarifamensual+"'";	 
		   insertarServicios(longitud,i+1,insert)
		}	
	})
}

function insertarServicios(plong,aux,param)
{
	
	var items = { parametros: param,
				  long: plong,
				  contador: aux
				}
	$.post( "http://localhost:8080/ServletInsertFacturas",items,function(result)
	{	
	}).done(function(result) 
	{
	   console.log($.parseJSON(result))
	   if($.parseJSON(result)){
			$.magnificPopup.close();
			alertmensaje('Correcto','Datos cargados correctamente')    
	   }  
	});

}
function updateProvee()
{
	$(".emailprovee").click(function(){
		$(".fila").removeClass( "select" );
		$(this).parent().addClass( "select" );
		var email = $(this).text();
		if(!$(this).find('.nuevoEmailProvee').hasClass('nuevoEmailProvee'))
		{
			$(this).empty()
			$(this).append('<input type = "text" class="form-control col-md-4 letraazultexto nuevoEmailProvee" >')
			$('.nuevoEmailProvee').val(email)
			
			$(".nuevoEmailProvee").blur(function(){
				$("#gifload").removeClass( "hidden" );
				var email = $(this).val();
				$('.select').find('.emailprovee').append(email)
				//alert($('.select').find('.inputNuevoEmailnew').val())
				$(this).remove();
				var usuario =  $("#tableUsers").find('.select').find('.email').text();
				var item = {email: usuario,
							emailprovee: email}
				console.log(item)
				$.post( "http://localhost:8080/ServletInsertProvee",item,function(result)
				{}).done(function(result) {
					$("#nuevo").removeClass('hidden')
					$("#gifload").addClass( "hidden" );
				});
			});	
		}
	});	
}




