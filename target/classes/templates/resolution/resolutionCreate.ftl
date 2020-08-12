<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 70vh">
        <div class="d-flex align-items-center justify-content-center" style="width: 60%; position: relative">
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                </#if>
            </div>
            <form action="/resolutionCreate" method="post" enctype="multipart/form-data" class="px-4 py-3">
                <h2>Накладання резолюції</h2>
                <div class="form-group mt-4">
                    <label for="inlineFormCustomSelect">Керівник</label>
                    <select name="agrees" class="custom-select mr-sm-2" id="inlineFormCustomSelect" aria-label="Default" aria-describedby="inputGroup-sizing-default">
                        <#list confirms?ifExists as user>
                            <option  value="${user?ifExists.id?ifExists}">${user?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</option>
                        </#list>
                    </select>
                </div>
                <div class="form-group mt-4">
                    <label for="exampleDropdownFormEmail1">Коментар до резолюції</label>
                    <input name="coment" value="" type="text" class="form-control" id="exampleDropdownFormEmail1" placeholder="коментар">
                </div>
                <div class="d-flex flex-column mb-3 mt-4">
                    <div class="form-group mb-3" id="pro">
                    </div>
                    <div class="col-sm-10">
                        <a id="newF" href="#">додати виконавця</a>
                    </div>
                </div>

                <div class="form-group mt-3">

                    <button class="btn btn-primary my_button mt-3" type="submit">Зберегти</button>
                </div>

                <input type="hidden" value="${_csrf.token}" name="_csrf">
                <input type="hidden" value="${document?ifExists.id?ifExists}" name="document">

            </form>
        </div>

        <div class="d-flex flex-row align-items-center justify-content-center p-4 my_res_doc_info_block">
            <div class="my_login_text mt-5 d-flex flex-column justify-content-around my_res_doc_info" >
                <label>Документ № ${documen?ifExists.number?ifExists}</label>
                <label>Дата документу: ${document?ifExists.date?ifExists}</label>
                <label>коментар: ${document?ifExists.coment?ifExists}</label>
                <label>Документ завантажив: ${document?ifExists.author?ifExists.information?ifExists.individual?ifExists.initials?ifExists}</label>
                <label><a href="/pdf/${document?ifExists.name?ifExists}" target="_blank">Відкрити документ</a></label>
<#--                <label><a href="/img/${document?ifExists.image?ifExists}" target="_blank">Відкрити зображення</a></label>-->

            </div>
        </div>

    </div>


























<#--    <h1 class="justify-content text-success"> ${succes?ifExists} </h1>-->
<#--    <h1 class="justify-content text-danger"> ${error?ifExists} </h1>-->
<#--    <h1 class="justify-content"> Редагування резолюції </h1>-->
<#--    <table class="table mt-5">-->
<#--        <thead>-->
<#--        <tr>-->
<#--            <th>Номер документу</th>-->
<#--            <th>Дата створення документу</th>-->
<#--            <th>Автор документу</th>-->
<#--            <th>Коментар</th>-->
<#--            <th>Завантажити файл</th>-->
<#--            <th>Показати зображення</th>-->
<#--            <th></th>-->
<#--        </tr>-->
<#--        </thead>-->
<#--        <tbody>-->
<#--        <tr>-->
<#--            <td>${document.number?ifExists}</td>-->
<#--            <td>${document.date?ifExists}</td>-->
<#--            <td>${document.author.information.individual.initials?ifExists}</td>-->
<#--            <td>${document.coment?ifExists}</td>-->
<#--            <td><a href="/pdf/${document.name}" download>Завантажити</a></td>-->
<#--            <td>-->
<#--                <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample${document.id}" aria-expanded="false" aria-controls="collapseExample">-->
<#--                    зображення-->
<#--                </button>-->
<#--                <div class="collapse" id="collapseExample${document.id}">-->
<#--                    <div class="card card-body">-->
<#--                        &lt;#&ndash;                            <#if document.getImage()??>&ndash;&gt;-->
<#--                        <img src="/img/${document.getImage()}">-->
<#--                        &lt;#&ndash;                            </#if>&ndash;&gt;-->
<#--                    </div>-->
<#--                </div>-->
<#--            </td>-->

<#--        </tr>-->
<#--        </tbody>-->
<#--    </table>-->
<#--    <form action="/resolutionCreate" method="post" enctype="multipart/form-data">-->
<#--        <div class="d-flex flex-column">-->
<#--            <div class="d-flex flex-column mb-3">-->
<#--                <div class="input-group mt-5">-->
<#--                    <div class="input-group-prepend">-->
<#--                        <span class="input-group-text" id="inputGroup-sizing-default">Керівник</span>-->
<#--                    </div>-->
<#--                    <select name="agrees" class="custom-select mr-sm-2" id="inlineFormCustomSelect" aria-label="Default" aria-describedby="inputGroup-sizing-default">-->
<#--                        <#list confirms?ifExists as user>-->
<#--                            <option  value="${user.id}">${user.information?ifExists.individual?ifExists.initials?ifExists}</option>-->
<#--                        </#list>-->
<#--                    </select>-->
<#--                </div>-->
<#--                <div class="input-group mt-5">-->
<#--                    <div class="input-group-prepend">-->
<#--                        <span class="input-group-text" id="inputGroup-sizing-default">Коментар до резолюції</span>-->
<#--                    </div>-->
<#--                    <input name="coment" value="" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">-->
<#--                </div>-->
<#--            </div>-->

<#--            <div class="d-flex flex-column mb-3">-->
<#--                <div class="d-flex flex-column mb-3" id="pro">-->
<#--                </div>-->
<#--                <div class="col-sm-10">-->
<#--                    <a id="newF" href="#">додати виконавця</a>-->
<#--                </div>-->
<#--            </div>-->
<#--            <div class="row mt-3">-->

<#--                <button class="btn btn-success m-3 " type="submit">Зберегти</button>-->
<#--            </div>-->
<#--        </div>-->
<#--        <input type="hidden" value="${_csrf.token}" name="_csrf">-->
<#--        <input type="hidden" value="${document.id}" name="document">-->
<#--    </form>-->

    <script>

        count = 1;

        document.getElementById("newF").addEventListener("click", function () {
            var d = document.createElement("div");
            d.setAttribute("id","performer"+count);
            d.setAttribute("class", "input-group mt-5");
            var s = document.createElement("select");
            s.setAttribute("class", "custom-select mr-sm-2");
            s.setAttribute("name", "performs"+count);
            <#list users as user>
                var z = document.createElement("option");
                z.setAttribute("value", "${user.username}");
                var t = document.createTextNode("${user.information?ifExists.individual?ifExists.initials?ifExists}");
                z.appendChild(t);
                s.appendChild(z);
            </#list>
            var inp = document.createElement("input");
            inp.setAttribute("class","form-control");
            inp.setAttribute("name", "note"+count);
            var dr = document.createElement("a");
            dr.setAttribute("href","#");
            dr.setAttribute("id","performer"+count);
            var tdr = document.createTextNode("видалити");
            dr.appendChild(tdr);
            d.appendChild(s);
            d.appendChild(inp);
            d.appendChild(dr);
            document.getElementById("pro").appendChild(d);
            dr.addEventListener("click",function () {
                document.getElementById(this.valueOf().id).remove();
            });
            count++;
        });

        function dropPref(perfId, c) {
            console.log(perfId);

        }
    </script>

</@dom.dom>