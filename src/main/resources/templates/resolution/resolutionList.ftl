<#import "../parts/dom.ftl" as dom>
<#include "../parts/security.ftl">

<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div class="mb-3">
        <h1 class="justify-content"> Резолюції </h1>
    </div>

    <form action="/resolutionList/filter" method="post" class="shadow-lg mb-5 bg-white rounded p-3 d-flex flex-column">

        <div class="d-flex flex-row justify-content-around">
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="exampleDropdownFormEmail1">Дата з</label>
                <input name="dateStart" value="${dataStart?ifExists}"  type="date"  class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="exampleDropdownFormEmail1">Дата по</label>
                <input name="dateStop" value="${dataStop?ifExists}"  type="date"  class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="exampleDropdownFormEmail1">Номер</label>
                <input name="number" value="${number?ifExists}"  type="text" class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">
            </div>
        </div>

        <div class="d-flex flex-row justify-content-around">
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="exampleDropdownFormEmail1">Керівник</label>
                <input name="agrees" value="${agrees?ifExists}"  type="text" class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="exampleDropdownFormEmail1">Автор</label>
                <input name="autor" value="${autor?ifExists}"  type="text" class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">
            </div>
            <div class="row p-3 mt-2 my_filter_button" >
                <button class="btn btn-primary my_button" type="submit" >Пошук</button>
            </div>

        </div>


        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </form>


    <div class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
    <table class="table mt-5 table-bordered table-hover horizontal-align" id="tableId">
        <thead class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
        <tr>
            <th>Номер документу</th>
            <th>Дата</th>
            <th>Керівник</th>
            <th>Автор</th>
            <th>Завантажити файл</th>
            <th>Show</th>
        </tr>
        </thead>
        <tbody>
        <#list resolutions?ifExists as resolution>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.resolution?ifExists.document?ifExists.number?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.resolution?ifExists.visa?ifExists.data?ifExists}|</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.resolution?ifExists.agrees?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${resolution?ifExists.resolution?ifExists.filling?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row"><a href="/pdf/${resolution?ifExists.resolution?ifExists.document?ifExists.name?ifExists}" target="_blank">Відкрити файл</a></div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row"><a class="pathC" href="/resolutionShow/${resolution?ifExists.resolution?ifExists.id?ifExists}">Перегляд</a></div></td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>
    <script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>
    <script></script>


</@dom.dom>