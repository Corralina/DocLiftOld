<#import "../parts/dom.ftl" as dom>
<@dom.dom>
    <div class="mt-5 shadow-lg mb-5 bg-white rounded d-flex flex-row" style="min-height: 70vh">
        <div class="d-flex align-items-center justify-content-center" style="position: relative">
            <div class="" style="position: absolute; top: 20px; left: 15px; color: red">
                <#if error??>
                    <p>* ${error?ifExists}</p>
                    <p>* ${succes?ifExists}</p>
                </#if>
            </div>
            <div class="d-flex flex-column align-items-center justify-content-center p-4">
                    <form method="post" action="/actDocumentEdit/${document?ifExists.id?ifExists}">
                        <textarea id="mytextarea"  name="textAct">${textDocument}</textarea>
                        <div class="form-group mt-3">
                            <label for="exampleDropdownFormPassword1">Ініціалізація до файлу</label>
                            <input name="description" value="${document.description?ifExists}" type="text" class="form-control" id="exampleDropdownFormPassword1" placeholder="коментар">
                        </div>
                        <div class="form-group mt-3">

                            <button class="btn btn-primary my_button mt-3" type="submit">Зберегти</button>
                        </div>

<#--                        <input type="hidden" class="inpV" value="${document?ifExists.id?ifExists}" name="document">-->
                        <input type="hidden" value="${_csrf.token}" name="_csrf">

                    </form>
            </div>
        </div>

    </div>

    <script>

        tinymce.init({
            selector: '#mytextarea'
        });


    </script>


</@dom.dom>