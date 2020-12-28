<#include "../parts/security.ftl">
<#import "../parts/dom.ftl" as dom>

<@dom.dom>
<h1 class="justify-content text-success"> ${succes?ifExists} </h1>
<h1 class="justify-content text-danger"> ${error?ifExists} </h1>
<div class="mb-3">
    <h1 class="justify-content"> Файли </h1>
</div>
<#--<form action="/actList/filter" method="post" class="shadow-lg mb-5 bg-white rounded p-3 d-flex flex-column">-->

<#--    <div class="d-flex flex-row justify-content-around">-->
<#--        <div class="form-group d-flex flex-column my_filter_input" >-->
<#--            <label for="exampleDropdownFormEmail1">Дата з</label>-->
<#--            <input name="dateStart" value="${dataStart?ifExists}"  type="date"  class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">-->
<#--        </div>-->
<#--        <div class="form-group d-flex flex-column my_filter_input" >-->
<#--            <label for="exampleDropdownFormEmail1">Дата по</label>-->
<#--            <input name="dateStop" value="${dataStop?ifExists}"  type="date"  class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">-->
<#--        </div>-->
<#--        <div class="form-group d-flex flex-column my_filter_input" >-->
<#--        </div>-->
<#--    </div>-->

<#--    <div class="d-flex flex-row justify-content-around">-->
<#--        <div class="form-group d-flex flex-column my_filter_input" >-->
<#--            <label for="exampleDropdownFormEmail1">Автор</label>-->
<#--            <input name="autor" value="${autor?ifExists}"  type="text" class="form-control" id="exampleDropdownFormEmail1" aria-describedby="inputGroup-sizing-default">-->
<#--        </div>-->
<#--        <div class="row p-3 mt-2 my_filter_button" >-->
<#--            <button class="btn btn-primary my_button" type="submit" >Пошук</button>-->
<#--        </div>-->

<#--    </div>-->



<#--    <input type="hidden" value="${_csrf.token}" name="_csrf">-->
<#--</form>-->
<div class="mt-5 shadow-lg mb-5 bg-white rounded p-3">
    <table class="table mt-5 table-bordered table-hover horizontal-align" id="tableId">
        <thead class="table mt-5 table-bordered table-hover horizontal-align" >
        <tr>
            <th>name документу</th>
            <th>Автор</th>
            <th>Дата створення</th>
            <th>Час створення</th>
            <th>Статус</th>
            <th>Детальніше</th>
        </tr>
        </thead>
        <tbody>

        <#list acts as act>
            <tr>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${act?ifExists.documentAct?ifExists.description?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${act?ifExists.author?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${act?ifExists.date?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">${act?ifExists.time?ifExists}</div></td>
                <td><div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                        <#if act.status?ifExists.finish?ifExists>
                            Завершено
                         <#elseIf act.status?ifExists.revers?ifExists>
                            Повернено
                         <#elseIf act.status?ifExists.draft?ifExists>
                            Пауза
                         <#else>
                             Обробка
                        </#if>

                    </div>
                </td>
                <td>
                    <div class="rounded mt-2 shadow-lg mb-2 rounded my_table_row">
                        <a class="pathC" href="/act/${act?ifExists.id}">Детальніше
                        </a>
                    </div>
                </td>
            </tr>

        </#list>
        </tbody>
    </table>
</div>

<script type="text/javascript" charset="utf8" src="/static/main.js"></script>
<script type="text/javascript" charset="utf8" src="/static/pathCorrect.js"></script>


</@dom.dom>