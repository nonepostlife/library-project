<%@page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://pagination.js.org/dist/2.1.4/pagination.min.js"></script>
    <link rel="stylesheet" href="https://github.com/superRaytin/paginationjs/blob/master/dist/pagination.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <strong>Учет книжного фонда библиотеки</strong>
        </div>
        <div class="row">
            <div class="col-sm-offset-2 col-sm-8">
                <div class="panel-body">
                    <form>
                        <div class="form-group">
                            <button type="button" class="btn btn-primary btn btn-block" onclick="showAllBooks()">Показать все</button>
                        </div>
                        <div class="form-group">
                        <label for="name">Название/ФИО автора</label>
                        <input class="form-control" type="text" id="name" placeholder="Название" onchange="searchBooks()"></input>
                        </div >
                        <div class="form-group">
                        <label for="year">Год</label>
                        <input class="form-control" type="number" id="year" min="0" step="1" onchange="searchBooks()"></input>
                        </div>
                        <div class="form-group">
                        <label for="countAuthors">Количество авторов</label>
                        <input class="form-control" type="number" id="countAuthors" min="0" step="1" onchange="searchBooks()"></input>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">
            <div id="content"></div>
            <div id="page-selection"></div>
        </div>
    </div>
</div>
<script>
    var service = 'http://localhost:8080/';
    var output='';
    var arrData;
    var searchBooks = function () {
        var name = document.getElementById('name');
        var year = document.getElementById('year');
        var countAuthors = document.getElementById('countAuthors');
        $.ajax({
            //http://localhost:8080/books/search/0?yearWriting=2004&text=book 1&countAuthors=1
            type: 'GET',
            url: service + 'books/search/0?yearWriting=' + year.value +'&text='+name.value+'&countAuthors='+countAuthors.value,
            dataType: 'json',
            async: false,
            success: function (result) {
                output = '';
                var stringData = JSON.stringify(result);
                arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table table-bordered table-hover">';
                output+= '<thead> <tr class="success">';
                output+= '<th>Название</'+'th>';
                output+= '<th>Год</'+'th>';
                output+= '<th>Авторы</'+'th>';
                output+= '</' +'tr> </thead>';
                for (var i in arrData) {
                    var bookObj = arrData[i];
                    output += '<tr>';
                    output += '<th>' + bookObj.bookName + '</' + 'th>';
                    output += '<th>' + bookObj.bookYearWriting + '</' + 'th>';
                    var a="";
                    for(var j in bookObj.authors)
                    {
                        a+=bookObj.authors[j]+'<br>';
                    }
                    output += '<th>' + a + '</' + 'th>';
                    output += '</' + 'tr>';
                }
                output+= '</' +'table>';

                // $('#page-selection').pagination({
                //     dataSource: arrData,
                //     pageSize : arrData.length/5 + 1,
                //     callback: function(data, pagination) {
                //         var dataContainer = $('#content');
                //         dataContainer.html(output);
                //     }
                // })
                $('#content').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#content').html(JSON.stringify(jqXHR))
            }
        });
    };

    var showAllBooks = function () {
        $.ajax({
            //http://localhost:8080/books/search/0?yearWriting=2004&text=book 1&countAuthors=1
            type: 'GET',
            url: service + 'books/page/0',
            dataType: 'json',
            async: false,
            success: function (result) {
                output = '';
                var stringData = JSON.stringify(result);
                arrData = JSON.parse(stringData);
                console.log(arrData);
                output+= '<table class="table table-bordered table-hover">';
                output+= '<thead> <tr class="success">';
                output+= '<th>Название</'+'th>';
                output+= '<th>Год</'+'th>';
                output+= '<th>Авторы</'+'th>';
                output+= '</' +'tr> </thead>';
                for (var i in arrData) {
                    var bookObj = arrData[i];
                    output += '<tr>';
                    output += '<th>' + bookObj.bookName + '</' + 'th>';
                    output += '<th>' + bookObj.bookYearWriting + '</' + 'th>';
                    var a="";
                    for(var j in bookObj.authors)
                    {
                        a+=bookObj.authors[j]+'<br>';
                    }
                    output += '<th>' + a + '</' + 'th>';
                    output += '</' + 'tr>';
                }
                output+= '</' +'table>';
                $('#content').html(output);
            },
            error: function (jqXHR, testStatus, errorThrown) {
                $('#content').html(JSON.stringify(jqXHR))
            }
        });
    };
</script>
</body>
</html>
