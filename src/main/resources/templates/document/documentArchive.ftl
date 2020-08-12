<#include "../parts/security.ftl">
<#import "../parts/dom.ftl" as dom>

<@dom.dom>
    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div class="mb-3">
        <h1 class="justify-content"> Архів документів </h1>
    </div>
    <form action="/documentArchive/filter" method="post" class="shadow-lg mb-5 bg-white rounded p-3 d-flex flex-column">

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
            </div>
        </div>

        <div class="d-flex flex-row justify-content-around">
            <div class="form-group d-flex flex-column my_filter_input" >
                <label for="exampleDropdownFormEmail1">Номер</label>
                <input name="number" value="${number?ifExists}"  type="text" class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">
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
        <thead class="table mt-5 table-bordered table-hover horizontal-align" >
        <tr>
            <th>Статус документу</th>
            <th>Номер документу</th>
            <th>Дата створення документу</th>
            <th>Автор документу</th>
            <th>Завантажити документ</th>
        </tr>
        </thead>
        <tbody>
        <#list documents as document>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                    <#if document.resolution?ifExists>
                        Документ розписано
                    <#else >
                        Документ завантажено
                    </#if>
                    </div>
                </td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${document?ifExists.number?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${document?ifExists.date?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${document?ifExists.author?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                    <#if document.author.username == username>
                        <a href="/pdf/${document?ifExists.name}" target="_blank">Відкрити файл</a>
                    <#else>
                        ---
                    </#if>
                    </div></td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>
</@dom.dom>