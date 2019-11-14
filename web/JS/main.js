$(document).ready(function () {
    console.log('JQuery esta funcionando');

    function Listar() {
        $.post('ServletProducto', {op: "listar"}, function (response) {
//            console.log(response);
            const datos = JSON.parse(response);
            console.log(datos);
            let template = '';
            datos.forEach(item => {
                template += `
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.nombre}</td>
                                <td>${item.cantidad}</td>
                                <td>${item.precio}</td>
                                <td>
                                    <button class="btn btn-warning btn-sm editar" id="${item.id}">
                                        E
                                    </button>
                                    <button class="btn btn-danger btn-sm eliminar" id="${item.id}">
                                        D
                                    </button>
                                </td>
                            </tr>
                            `;
            });

            $('#tbody-list').html(template);

        });
    }

    Listar();

    function Obtener() {
        var id = $('#id').val();
        var name = $('#nombre').val();
        var cantidad = $('#cantidad').val();
        var precio = $('#precio').val();

        var op;
        if (id.length === 0) {
            op = 'insertar';
        } else {
            op = 'actualizar';
        }

        const datos = {
            op, id, name, cantidad, precio
        };

        return datos;
    }

    function Limpiar() {
        $('#id').val('');
        $('#nombre').val('');
        $('#cantidad').val('');
        $('#precio').val('');
    }

    $('#guardar').click(function () {
        const datos = Obtener();
        console.log(datos);
        $.post('ServletProducto', datos, function (response) {
            console.log(response);
            Listar();
            Limpiar();
        });
    });

    $(document).on('click', '.editar', function () {
        const id = $(this).attr('id');
        $.post('ServletProducto', {op: "listarID", id}, function (response) {
            console.log(response);
            const datos = JSON.parse(response);

            $('#id').val(datos[0].id);
            $('#nombre').val(datos[0].nombre);
            $('#cantidad').val(datos[0].cantidad);
            $('#precio').val(datos[0].precio);
        });
    });


    $(document).on('click', '.eliminar', function () {
        const id = $(this).attr('id');
        $.post('ServletProducto', {op: "eliminar", id}, function (response) {
            console.log(response);
            Listar();
        });
    });

});