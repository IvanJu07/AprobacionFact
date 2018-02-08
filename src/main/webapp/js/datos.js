/*

CAMBIAR http://localhost:8080  POR  https://backend-dot-aprobacion-de-facturas.appspot.com
*/

function datos()
{
	$('#tTable').empty();
	$('#tableDatos').empty();
	thead = '';
	thead += '<table class="table table-hover" id="tDatos" >';
	thead += '				<thead class="fondoazul letrablanca">';
	thead += '				<tr>';
	thead += '				<th>Proyecto <img src="../img/u.gif" width="30px" height="30px" class="hidden" id="gifload"></th>';
	thead += '				<th>Proveedor</th>';
	thead += '				<th>No. documento</th>';
	thead += '				<th>Items</th>';
	thead += '				<th>Importe</th>';
	thead += '				<th>Correo PM</th>';
	thead += '				<th>Detalle</th>';
	thead += '				</tr>';
	thead += '				</thead>';
	thead += '				<tbody id="tableDatos">';
	thead += '				</tbody>';
	thead += '			</table>';
	
	$('#tTable').append(thead);
	
	
	$.get( "https://backend-dot-aprobacion-de-facturas.appspot.com/ServletGetDatos",function(result)
	{
		var datos = $.parseJSON(result);
		console.log(datos)
		var table = '';
		for (var i = 0; i < datos.length; i++) 
		{
			table += '<tr class = "fila">';
			table += '<th scope="row" class = "proyeto">'+datos[i]['proyecto']+'</th>';
			table += '<td class = "proveedor">'+datos[i]['Proveedor']+'</td>';
			table += '<td class = "">'+datos[i]['NoDocumento']+'</td>';
			table += '<td class = "items">'+datos[i]['items']+'</td>';
			table += '<td class = "importe">$'+datos[i]['importe']+'</td>';
			table += '<td class = "importe">'+datos[i]['correoPM']+'</td>';
			table += '<td class = "importe handCursor">&nbsp;&nbsp;&nbsp;&nbsp; <i class="fas fa-cog"></i></td>';
		}
		$('#tableDatos').append(table);
		
		$('#tDatos').DataTable( 
		{    
			"scrollX": true,
			"scrollY": "250px",
			"searching": false,
			"lengthMenu": [20],
			"bLengthChange": false,
			"ordering": false,
			"language":
			{
			"url":"../js/spanish.json"
			}

		});
		
	});
	
}




datos();