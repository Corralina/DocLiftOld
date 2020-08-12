<#import "../parts/dom.ftl" as dom>
<@dom.dom>

    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>
    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>
    <div class="mb-3">
        <h1 class="justify-content"> Список користувачів </h1>
    </div>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
    <table class="table mt-5 table-bordered table-hover horizontal-align" id="tableId" id="tableId">
        <thead class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
        <tr>
            <th>Домен</th>
            <th>Ім'я</th>
            <th>Редагувати</th>
        </tr>
        </thead>
        <tbody>
        <#list users?ifExists as user>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${user?ifExists.username?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${user?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</div></td>
                <td style="background: #f1f1f1"><div class="mt-2  d-flex justify-content-center" ><a href="/userEdit/${user?ifExists.id?ifExists}" title="Edit"><img style="width: 40px" src="/static/settings-line.svg" alt=""></a></div></td>
            </tr>
        </#list>
        </tbody>
    </table>
    </div>

    <script type="text/javascript" charset="utf8" src="/static/main.js"></script>

</@dom.dom>